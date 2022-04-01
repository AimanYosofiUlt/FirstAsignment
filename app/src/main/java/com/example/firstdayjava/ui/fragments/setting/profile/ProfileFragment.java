package com.example.firstdayjava.ui.fragments.setting.profile;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;
import androidx.navigation.fragment.NavHostFragment;

import com.example.firstdayjava.R;
import com.example.firstdayjava.databinding.FragmentProfileBinding;
import com.example.firstdayjava.pojo.local.entities.User;
import com.example.firstdayjava.pojo.remote.callpack.ResponseState;
import com.example.firstdayjava.ui.fragments.base.BaseFragment;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class ProfileFragment extends BaseFragment {
    @Inject
    ProfileFragmentViewModel viewModel;

    FragmentProfileBinding bd;

    User currentUser;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        bd = FragmentProfileBinding.inflate(inflater, container, false);

        initModelView();
        initViewModel();
        initEvent();
        return bd.getRoot();
    }

    @Override
    protected void initViewModel() {
        viewModel.appUserMDL.observe(getViewLifecycleOwner(), new Observer<User>() {
            @Override
            public void onChanged(User user) {
                currentUser = user;

                String fullName = user.getFirstName() + " " + user.getLastName();
                bd.userNameTV.setText(fullName);
                bd.userPhoneTV.setText(user.getPhone());

                bd.firstNameED.setText(user.getFirstName());
                bd.lastNameED.setText(user.getLastName());
                bd.userEmailED.setText(user.getEmail());

                changeToText(bd.firstNameED);
                changeToText(bd.lastNameED);
                changeToText(bd.userEmailED);


                bd.saveBtn.setVisibility(View.GONE);
                bd.editBtn.setVisibility(View.VISIBLE);
            }

            private void changeToText(EditText editText) {
                editText.setBackground(ContextCompat.getDrawable(requireContext(), R.drawable.back_white));
                editText.setEnabled(false);
            }
        });

        viewModel.responseStateMDL.observe(getViewLifecycleOwner(), new Observer<ResponseState>() {
            @Override
            public void onChanged(ResponseState responseState) {
                if (!responseState.isSuccessful()) {
                    showErrorDialog(responseState.getMessage());
                } else {
                    Toast.makeText(requireContext(), getString(R.string.saved), Toast.LENGTH_SHORT).show();
                    bd.saveBtn.setVisibility(View.GONE);
                    bd.editBtn.setVisibility(View.VISIBLE);
                }
            }

            private void showErrorDialog(String message) {
                new AlertDialog.Builder(requireContext())
                        .setMessage(message)
                        .setNegativeButton(getString(R.string.done), (dialogInterface, i) -> dialogInterface.cancel())
                        .create()
                        .show();
            }
        });
    }

    @Override
    protected void initModelView() {
        viewModel.getAppUser();
    }

    @Override
    protected void initEvent() {
        bd.editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeToEdit(bd.firstNameED);
                changeToEdit(bd.lastNameED);
                changeToEdit(bd.userEmailED);

                bd.firstNameED.requestFocus();
                bd.saveBtn.setVisibility(View.VISIBLE);
                bd.editBtn.setVisibility(View.GONE);
            }

            private void changeToEdit(EditText editText) {
                editText.setBackground(ContextCompat.getDrawable(requireContext(), R.drawable.back_textview));
                editText.setEnabled(true);
            }
        });

        bd.saveBtn.setOnClickListener(view -> {
            currentUser.setFirstName(bd.firstNameED.getText().toString());
            currentUser.setLastName(bd.lastNameED.getText().toString());
            currentUser.setEmail(bd.userEmailED.getText().toString());
            viewModel.updateUserProfile(currentUser);
        });

        bd.backBtn.setOnClickListener(view -> NavHostFragment.findNavController(requireParentFragment()).popBackStack());
    }
}