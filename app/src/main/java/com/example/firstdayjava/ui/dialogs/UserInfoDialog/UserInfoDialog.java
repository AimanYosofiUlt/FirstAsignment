package com.example.firstdayjava.ui.dialogs.UserInfoDialog;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;

import com.example.firstdayjava.app.OurCipher;
import com.example.firstdayjava.app.St;
import com.example.firstdayjava.databinding.DialogUserinfoBinding;
import com.example.firstdayjava.pojo.dbs.models.Users;

import javax.crypto.SecretKey;

public class UserInfoDialog {
    DialogUserinfoBinding bd;
    UserInfoDialogListener listener;
    Users user;

    public UserInfoDialog(Context context, Users user, UserInfoDialogListener listener) {
        bd = DialogUserinfoBinding.inflate(LayoutInflater.from(context));
        this.listener = listener;
        this.user = user;

        init(user);
        initEvent();
    }

    private void initEvent() {
        bd.firstNameEd.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                UserInfoDialog.this.user.setFirstName(bd.firstNameEd.getText().toString());
                listener.onUpdateReq(user);
            }

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }

        });

        bd.lastNameED.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                UserInfoDialog.this.user.setLastName(bd.lastNameED.getText().toString());
                listener.onUpdateReq(user);
            }

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });


        bd.phoneED.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                UserInfoDialog.this.user.setPhone(bd.phoneED.getText().toString());
                listener.onUpdateReq(user);
            }

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });

        bd.locationED.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                UserInfoDialog.this.user.setLocation(bd.locationED.getText().toString());
                listener.onUpdateReq(user);
            }

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });

        bd.bdayED.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                UserInfoDialog.this.user.setBirthDate(bd.bdayED.getText().toString());
                listener.onUpdateReq(user);
            }

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });

//        bd.passwordED.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//                UserInfoDialog.this.user.setLastName(bd.passwordED.getText().toString());
//                listener.onUpdateReq(user);
//            }
//
//            @Override
//            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//            }
//
//            @Override
//            public void afterTextChanged(Editable editable) {
//            }
//        });

    }

    private void init(Users user) {
        bd.firstNameEd.setText(user.getFirstName());
        bd.lastNameED.setText(user.getLastName());
        bd.emailED.setText(user.getEmail());
        bd.phoneED.setText(user.getPhone());
        bd.locationED.setText(user.getLocation());
        bd.bdayED.setText(user.getBirthDate());
        bd.passwordED.setText(getDePassword());
        bd.confirmPasswordED.setText(user.getPassword());
    }

    private String getDePassword() {
        byte[] key = St.fromStringToByteArray(user.getEncryptKey());
        byte[] cipherPassword = St.fromStringToByteArray(user.getPassword());
        try {
            SecretKey skey = OurCipher.generateKey(key);
            return OurCipher.decryptMsg(cipherPassword, skey);
        } catch (Exception ignored) {
            return ignored.getMessage();
        }
    }

    public View getView() {
        return bd.getRoot();
    }
}
