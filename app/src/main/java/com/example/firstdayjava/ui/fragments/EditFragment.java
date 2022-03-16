package com.example.firstdayjava.ui.fragments;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import com.example.firstdayjava.R;
import com.example.firstdayjava.app.OurCipher;
import com.example.firstdayjava.app.St;
import com.example.firstdayjava.databinding.FragmentEditBinding;
import com.example.firstdayjava.pojo.dbs.models.Users;
import com.example.firstdayjava.ui.activities.MapActivity;
import com.example.firstdayjava.viewmodels.EditFragmentViewModel;

import java.nio.charset.StandardCharsets;

import javax.crypto.SecretKey;

public class EditFragment extends Fragment {
    FragmentEditBinding bd;
    EditFragmentViewModel viewModel;

    boolean isFromLogin = false;

    ArrayAdapter<String> phoneAdapter;

    public static String LOCATION_STR_GEOTITLE = "locationTitle";
    public static String LOCATION_STR = "location";
    String location;

    ActivityResultLauncher<Intent> someActivityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Intent data = result.getData();
                        assert data != null;
                        bd.locationED.setText(data.getStringExtra(LOCATION_STR_GEOTITLE));
                        location = data.getStringExtra(LOCATION_STR);
                    }
                }
            });
    ;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        bd = FragmentEditBinding.inflate(inflater, container, false);
        initViewModel();

        isFromLogin = EditFragmentArgs.fromBundle(requireArguments()).getIsFromLogin();
        return bd.getRoot();
    }

    private void initViewModel() {
        viewModel = new ViewModelProvider(this).get(EditFragmentViewModel.class);
        initModelView();
    }

    private void initModelView() {
        bd.phoneCCP.registerPhoneNumberTextView(bd.phoneED);
        initEvent();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        isFromLogin = EditFragmentArgs.fromBundle(getArguments()).getIsFromLogin();
    }

    private void initEvent() {
        bd.saveBtn.setOnClickListener(view -> {
            if (isSaveAble()) {
                Users user = getTheUser();
                viewModel.addUser(user);
                Toast.makeText(requireContext(), "User Added", Toast.LENGTH_SHORT).show();

            if (isFromLogin) {
                NavHostFragment.findNavController(this).navigate(
                        EditFragmentDirections.actionEditFragmentToMainRragment()
                );
            } else
                NavHostFragment.findNavController(this)
                        .popBackStack();
            }
        });

        bd.button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(requireActivity(), MapActivity.class);
                someActivityResultLauncher.launch(intent);
            }
        });

        bd.datePikerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                DatePicker picker = new DatePicker(requireContext());
                builder.setView(picker);
                builder.setPositiveButton("Save", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String str = String.format("%04d\\%02d\\%02d",
                                picker.getYear(),
                                picker.getMonth(),
                                picker.getDayOfMonth());
                        bd.bdayED.setText(str);
                    }
                }).create().show();
            }
        });

        bd.backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(EditFragment.this)
                        .popBackStack();
            }
        });

    }


    private boolean isSaveAble() {
        if (hasEmptyFieldHandel(bd.firstNameEd)) return false;

        if (hasEmptyFieldHandel(bd.lastNameED)) return false;

        if (hasEmptyFieldHandel(bd.emailED)) return false;

        if (hasEmptyFieldHandel(bd.phoneED)) return false;

        if (hasEmptyFieldHandel(bd.locationED)) return false;

        if (hasEmptyFieldHandel(bd.bdayED)) return false;

        if (hasEmptyFieldHandel(bd.passwordED)) return false;

        if (hasEmptyFieldHandel(bd.confirmPasswordED)) return false;
        else if (!bd.passwordED.getText().toString().equals(bd.confirmPasswordED.getText().toString())) {
            bd.confirmPasswordED.setError("The Confirm is not like the password");
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

    public Users getTheUser() {

        byte[] key = St.getRandomKey();

        StringBuilder mes = new StringBuilder();
        for (int i = 0; i < key.length; i++) {
            mes.append(key[i]);
        }
        Log.e("1234", mes.toString());

        String keyStr = St.fromByteArrayToString(key);
        Log.e("1234", keyStr.toString());
        String encPassword = "00";
        try {
            SecretKey skey = OurCipher.generateKey(key);
            byte[] ourStr = OurCipher.encryptMsg(bd.passwordED.getText().toString(), skey);
            encPassword = new String(ourStr, StandardCharsets.UTF_8);
        } catch (Exception ignored) {
            Toast.makeText(requireContext(), ignored.getMessage(), Toast.LENGTH_LONG).show();
        }

        String phone = getPhone();
        return new Users(
                bd.firstNameEd.getText().toString(),
                bd.lastNameED.getText().toString(),
                bd.emailED.getText().toString(),
                phone,
                bd.bdayED.getText().toString(),
                bd.locationED.getText().toString(),
                bd.passwordED.getText().toString(),
                keyStr
        );
    }

    private String getPhone() {
        return bd.phoneCCP.getFullNumberWithPlus();
    }


}