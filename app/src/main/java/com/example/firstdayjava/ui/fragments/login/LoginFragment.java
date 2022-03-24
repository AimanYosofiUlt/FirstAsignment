package com.example.firstdayjava.ui.fragments.login;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import com.example.firstdayjava.R;
import com.example.firstdayjava.databinding.FragmentLoginBinding;
import com.example.firstdayjava.ui.activities.MainActivity;
import com.example.firstdayjava.ui.fragments.ResponseState;
import com.github.razir.progressbutton.ButtonTextAnimatorExtensionsKt;
import com.github.razir.progressbutton.DrawableButtonExtensionsKt;
import com.github.razir.progressbutton.ProgressButtonHolderKt;

import javax.inject.Inject;

import dagger.hilt.EntryPoint;
import dagger.hilt.InstallIn;
import dagger.hilt.android.AndroidEntryPoint;
import dagger.hilt.android.components.FragmentComponent;
import kotlin.Unit;

@AndroidEntryPoint
public class LoginFragment extends Fragment {

    FragmentLoginBinding bd;

    @Inject
    LoginFragmentViewModel viewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        bd = FragmentLoginBinding.inflate(inflater, container, false);
        initViewModel();
        initModelView();
        initEvent();
        return bd.getRoot();
    }

    private void initViewModel() {
        viewModel.loginState.observe(getViewLifecycleOwner(), new Observer<ResponseState>() {
            @Override
            public void onChanged(ResponseState loginState) {
                hideProgressCustom();
                if (loginState.isLoginDone()) {
                    NavHostFragment
                            .findNavController(LoginFragment.this)
                            .navigate(LoginFragmentDirections.actionLoginFragmentToMainFragment());
                } else {
                    showDialog(loginState.getMessage());
                }
                showDialog(loginState.getMessage());
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

        ProgressButtonHolderKt.bindProgressButton(this, bd.loginBtn);
        ButtonTextAnimatorExtensionsKt.attachTextChangeAnimator(bd.loginBtn);

        ButtonTextAnimatorExtensionsKt.attachTextChangeAnimator(bd.loginBtn, textChangeAnimatorParams -> {
            textChangeAnimatorParams.setFadeInMills(300);
            textChangeAnimatorParams.setFadeOutMills(300);
            return Unit.INSTANCE;
        });

    }

    private void initEvent() {
        bd.loginBtn.setOnClickListener(view -> {
            if (isLoginAble()) {
                showProgressCustom(bd.loginBtn);
                viewModel.login(bd.phoneCCP.getFullNumberWithPlus(), bd.passwordED.getText().toString());
            }

        });

        bd.signUpBtn.setOnClickListener(view -> {
            LoginFragmentDirections.ActionLoginFragmentToEditFragment action = LoginFragmentDirections.actionLoginFragmentToEditFragment();
            action.setIsFromLogin(true);
            NavHostFragment.findNavController(this).navigate(action);
        });

        bd.languageOpt.setOnClickListener(view -> {
            requireActivity().finish();

            Intent intent = new Intent(requireActivity(), MainActivity.class);

            intent.putExtra(MainActivity.LANGUAGE, "ar");

            requireActivity().startActivity(intent);
        });
    }

    private boolean isLoginAble() {
        if (hasEmptyFieldHandel(bd.phoneED, "Enter Phone Number"))
            return false;

        return !hasEmptyFieldHandel(bd.passwordED, "Enter Password");
    }

    private boolean hasEmptyFieldHandel(EditText ed, String error) {
        boolean has = ed.getText().toString().equals("");
        if (has) {
            ed.setError(error);
            ed.requestFocus();
        }
        return has;
    }


    private void showProgressCustom(final Button button) {
        setViewsEnabled(false);

        DrawableButtonExtensionsKt.showProgress(button, progressParams -> {
            progressParams.setButtonTextRes(R.string.loading);
            progressParams.setProgressColors(new int[]{Color.WHITE, Color.WHITE, Color.WHITE});
            return Unit.INSTANCE;
        });
    }

    private void setViewsEnabled(boolean enabled) {
        bd.loginBtn.setEnabled(enabled);
        bd.phoneED.setEnabled(enabled);
        bd.passwordED.setEnabled(enabled);
        bd.phoneCCP.setEnabled(enabled);
        bd.signUpBtn.setEnabled(enabled);
        bd.brawseBtn.setEnabled(enabled);
    }

    private void hideProgressCustom() {
        DrawableButtonExtensionsKt.hideProgress(bd.loginBtn, getString(R.string.login));
        setViewsEnabled(true);
    }
}