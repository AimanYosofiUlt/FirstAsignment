package com.example.firstdayjava.ui.fragments.setting.more;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.navigation.fragment.NavHostFragment;

import com.example.firstdayjava.R;
import com.example.firstdayjava.databinding.FramentMoreBinding;
import com.example.firstdayjava.ui.activities.main.MainActivity;
import com.example.firstdayjava.ui.fragments.base.BaseFragment;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MoreFragment extends BaseFragment {
    @Inject
    MoreFragmentViewModel viewModel;

    FramentMoreBinding bd;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        bd = FramentMoreBinding.inflate(inflater, container, false);

        initModelView();
        initViewModel();
        initEvent();
        return bd.getRoot();
    }

    @Override
    protected void initViewModel() {

    }

    @Override
    protected void initModelView() {

    }

    @Override
    protected void initEvent() {
        bd.languageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showLangDialog((dialogInterface, i) -> {
                    String languageCode;
                    if (i == 0) {
                        languageCode = "ar";
                    } else {
                        languageCode = "eg";
                    }
                    restartActivity(languageCode);
                });

            }

            private void showLangDialog(DialogInterface.OnClickListener onClickListener) {
                final CharSequence[] items = {getString(R.string.arabic), getString(R.string.english)};
                new AlertDialog.Builder(requireContext())
                        .setTitle(getString(R.string.changeLang))
                        .setItems(items, onClickListener)
                        .create()
                        .show();
            }

            private void restartActivity(String language) {
                requireActivity().finish();
                Intent intent = new Intent(requireActivity(), MainActivity.class);
                intent.putExtra(MainActivity.LANGUAGE, language);
                requireActivity().startActivity(intent);
            }
        });
        bd.logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showLogoutDialog((dialogInterface, i) -> {
                    viewModel.logOut();
                    restartActivity();
                });
            }

            private void showLogoutDialog(DialogInterface.OnClickListener onClickListener) {
                new AlertDialog.Builder(requireContext())
                        .setTitle(getString(R.string.log_out))
                        .setMessage(getString(R.string.logout_msg))
                        .setPositiveButton(getString(R.string.yes), onClickListener)
                        .setNegativeButton(getString(R.string.cancle), (dialogInterface, i) -> dialogInterface.cancel())
                        .create()
                        .show();
            }

            private void restartActivity() {
                requireActivity().finish();
                Intent intent = new Intent(requireActivity(), MainActivity.class);
                requireActivity().startActivity(intent);
            }
        });

        bd.profileBtn.setOnClickListener(view -> NavHostFragment.findNavController(requireParentFragment()).navigate(R.id.action_moreFragment_to_profileFragment));

        bd.addressBtn.setOnClickListener(view -> NavHostFragment.findNavController(requireParentFragment())
                .navigate(R.id.action_moreFragment_to_addressFragment));

        bd.termsBtn.setOnClickListener(view -> new AlertDialog.Builder(requireContext())
                .setView(LayoutInflater.from(requireContext()).inflate(R.layout.about, null, false))
                .create()
                .show());

        bd.aboutUsBtn.setOnClickListener(view -> new AlertDialog.Builder(requireContext())
                .setView(LayoutInflater.from(requireContext()).inflate(R.layout.about, null, false))
                .create()
                .show());

        bd.contactUsBtn.setOnClickListener(view -> new AlertDialog.Builder(requireContext())
                .setView(LayoutInflater.from(requireContext()).inflate(R.layout.about, null, false))
                .create()
                .show());


    }

}
