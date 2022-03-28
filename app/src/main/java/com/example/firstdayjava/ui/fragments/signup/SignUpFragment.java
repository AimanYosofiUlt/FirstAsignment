package com.example.firstdayjava.ui.fragments.signup;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.navigation.fragment.NavHostFragment;

import com.example.firstdayjava.R;
import com.example.firstdayjava.databinding.FragmentEditBinding;
import com.example.firstdayjava.pojo.local.entities.User;
import com.example.firstdayjava.ui.activities.MapActivity;
import com.example.firstdayjava.ui.fragments.ResponseState;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class SignUpFragment extends Fragment {
    FragmentEditBinding bd;

    @Inject
    SignUpFragmentViewModel viewModel;

    boolean isFromLogin = false;

    public static String LOCATION_STR_GEOTITLE = "locationTitle";
    public static String LOCATION_STR = "location";

    private final String TAG = "EditFragment";

    ActivityResultLauncher<Intent> someActivityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Intent data = result.getData();
                        assert data != null;
                        bd.locationED.setText(data.getStringExtra(LOCATION_STR_GEOTITLE));
                    }
                }
            });

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        bd = FragmentEditBinding.inflate(inflater, container, false);

        initViewModel();
        initModelView();
        initEvent();

        isFromLogin = SignUpFragmentArgs.fromBundle(requireArguments()).getIsFromLogin();
        return bd.getRoot();
    }

    private void initViewModel() {
        viewModel.signUpState.observe(getViewLifecycleOwner(), new Observer<ResponseState>() {
            @Override
            public void onChanged(ResponseState state) {
                if (state.isSuccssful())
                    finishSignUp();
                else
                    showDialog(state.getMessage());
            }

            private void finishSignUp() {
                Toast.makeText(requireContext(), "User Register Done", Toast.LENGTH_SHORT).show();
                NavHostFragment.findNavController(SignUpFragment.this).popBackStack();
            }

            private void showDialog(String message) {
                new AlertDialog.Builder(requireContext())
                        .setCancelable(true)
                        .setTitle(getString(R.string.field))
                        .setMessage(message)
                        .setNegativeButton(getString(R.string.done),
                                (dialogInterface, i) -> dialogInterface.cancel())
                        .setIcon(R.drawable.ic_baseline_error_24)
                        .create().show();
            }
        });
    }

    private void initModelView() {
        bd.phoneCCP.registerPhoneNumberTextView(bd.phoneED);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    private void initEvent() {
        bd.signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isSaveAble()) {
                    signUp();
                    bd.progressBar.setVisibility(View.VISIBLE);
                }
            }

            private void signUp() {
                User user = new User(
                        bd.firstNameEd.getText().toString(),
                        bd.lastNameED.getText().toString(),
                        bd.emailED.getText().toString(),
                        bd.phoneCCP.getFullNumberWithPlus(),
                        bd.passwordED.getText().toString()
                );
                viewModel.signUp(user);
            }
        });

        bd.getLocationBtn.setOnClickListener(view -> {
            Intent intent = new Intent(requireActivity(), MapActivity.class);
            someActivityResultLauncher.launch(intent);
        });

        bd.datePikerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDataPickerDialog();
            }

            private void showDataPickerDialog() {
                final DatePicker picker = new DatePicker(requireContext());
                new AlertDialog.Builder(getContext())
                        .setView(picker)
                        .setPositiveButton(R.string.save, (dialogInterface, i) -> setDate(picker))
                        .create()
                        .show();
            }

            private void setDate(DatePicker picker) {
                @SuppressLint("DefaultLocale") String dateStr = String.format("%04d\\%02d\\%02d",
                        picker.getYear(),
                        picker.getMonth(),
                        picker.getDayOfMonth());
                bd.bdayED.setText(dateStr);
            }
        });

        bd.backBtn.setOnClickListener(view -> NavHostFragment.findNavController(SignUpFragment.this)
                .popBackStack());

    }


    private boolean isSaveAble() {
        if (hasEmptyFieldHandel(bd.firstNameEd)) return false;

        if (hasEmptyFieldHandel(bd.lastNameED)) return false;

        if (hasEmptyFieldHandel(bd.emailED)) return false;

        if (hasEmptyFieldHandel(bd.phoneED)) return false;

        if (hasEmptyFieldHandel(bd.passwordED)) return false;

        if (hasEmptyFieldHandel(bd.confirmPasswordED)) return false;

        boolean isPW_ConfirmPW_Same = bd.passwordED.getText().toString()
                .equals(bd.confirmPasswordED.getText().toString());

        if (!isPW_ConfirmPW_Same) {
            bd.confirmPasswordED.setError(getString(R.string.confirm_password_error));
            return hasEmptyFieldHandel(bd.confirmPasswordED);
        }

        return true;
    }

    private boolean hasEmptyFieldHandel(TextView ed) {
        boolean isEmpty = ed.getText().toString().equals("");
        if (isEmpty)
            ed.setError("Empty Field");
        return isEmpty;
    }


}