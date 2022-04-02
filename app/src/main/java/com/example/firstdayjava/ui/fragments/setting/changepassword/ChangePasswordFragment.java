package com.example.firstdayjava.ui.fragments.setting.changepassword;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.Observer;
import androidx.navigation.fragment.NavHostFragment;

import com.example.firstdayjava.R;
import com.example.firstdayjava.databinding.FragmentChangePasswordBinding;
import com.example.firstdayjava.pojo.remote.callpack.ResponseState;
import com.example.firstdayjava.ui.fragments.base.BaseFragment;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class ChangePasswordFragment extends BaseFragment {

    @Inject
    ChangePasswordFragmentViewModel viewModel;

    FragmentChangePasswordBinding bd;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        bd = FragmentChangePasswordBinding.inflate(inflater, container, false);

        initModelView();
        initViewModel();
        initEvent();
        return bd.getRoot();
    }

    @Override
    protected void initViewModel() {

        viewModel.responseStateMDL.observe(getViewLifecycleOwner(), new Observer<ResponseState>() {
            @Override
            public void onChanged(ResponseState responseState) {
                if (responseState.isSuccessful()) {
                    Toast.makeText(requireContext(), getString(R.string.saved), Toast.LENGTH_SHORT).show();
                    NavHostFragment.findNavController(requireParentFragment()).popBackStack();
                } else {
                    if (responseState.getMessage().equals(ChangePasswordFragmentViewModel.INCORRECT_PASSWORD_FLAG)) {
                        bd.oldPassEd.setError(getString(R.string.incorrect_pass_msg));
                    } else {
                        showErrorDialog(responseState.getMessage());
                    }
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

    }

    @Override
    protected void initEvent() {
        bd.saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (savaAble()) {
                    viewModel.changePassword(
                            bd.oldPassEd.getText().toString(),
                            bd.newPassED.getText().toString()
                    );
                }
            }

            private boolean savaAble() {
                if (bd.newPassED.getText().toString()
                        .equals(bd.confirmEd.getText().toString()))
                    return true;
                bd.newPassED.setError(getString(R.string.not_match));
                bd.confirmEd.setError(getString(R.string.not_match));
                return false;
            }
        });

        bd.backBtn.setOnClickListener(view -> NavHostFragment.findNavController(requireParentFragment()).popBackStack());
    }
}
