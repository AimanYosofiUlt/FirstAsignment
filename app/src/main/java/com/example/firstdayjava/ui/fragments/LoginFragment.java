package com.example.firstdayjava.ui.fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavAction;
import androidx.navigation.fragment.FragmentNavigator;
import androidx.navigation.fragment.NavHostFragment;

import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.example.firstdayjava.R;
import com.example.firstdayjava.app.OurCipher;
import com.example.firstdayjava.databinding.FragmentLoginBinding;
import com.example.firstdayjava.pojo.dbs.models.Users;
import com.example.firstdayjava.ui.activities.MainActivity;
import com.example.firstdayjava.viewmodels.LoginFragmentViewModel;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Locale;

import javax.crypto.SecretKey;

public class LoginFragment extends Fragment {

    FragmentLoginBinding bd;
    LoginFragmentViewModel viewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        bd = FragmentLoginBinding.inflate(inflater, container, false);
        initModelView();
        initViewModel();
        return bd.getRoot();
    }

    private void initViewModel() {
        viewModel = new ViewModelProvider(this).get(LoginFragmentViewModel.class);
        viewModel.user.observe(requireActivity(), new Observer<Users>() {
            @Override
            public void onChanged(Users user) {
                if (user == null) {
                    bd.emailED.setError("No such user");
                } else if (user.getPassword().equals(bd.passwordED.getText().toString())) {
                    Toast.makeText(requireActivity(), "Login Done", Toast.LENGTH_SHORT).show();
                } else {
                    bd.passwordED.setError("Wrong Password");
                    Toast.makeText(requireActivity(), "Uncorrect User Password " + user.getPassword(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void initModelView() {
        initEvent();
    }

    private void initEvent() {
        bd.loginBtn.setOnClickListener(view -> {
            if (isLoginAble()) {
                viewModel.checkByEmail(bd.emailED.getText().toString());
            }
        });

        bd.signUpBtn.setOnClickListener(view -> {
            LoginFragmentDirections.ActionLoginFragmentToEditFragment action = LoginFragmentDirections.actionLoginFragmentToEditFragment();
            action.setIsFromLogin(true);
            NavHostFragment.findNavController(this).navigate(action);
        });

        bd.languageOpt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requireActivity().finish();

                Intent intent = new Intent(requireActivity(), MainActivity.class);

                intent.putExtra(MainActivity.LANGUAGE, "ar");

                requireActivity().startActivity(intent);
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    private boolean isLoginAble() {
        if (hasEmptyFieldHandel(bd.emailED))
            return false;

        if (hasEmptyFieldHandel(bd.passwordED))
            return false;

        return true;
    }


    private boolean validateFields() {
        if (hasEmptyFieldHandel(bd.emailED)) {
            return false;
        }

        if (hasEmptyFieldHandel(bd.passwordED)) {
            return false;
        }

        return true;
    }

    private boolean hasEmptyFieldHandel(EditText ed) {
        boolean has = ed.getText().toString().equals("");
        if (has) {
            ed.setError("Empty field");
        }
        return has;
    }


}