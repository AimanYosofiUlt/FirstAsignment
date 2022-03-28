package com.example.firstdayjava.ui.views.UserView;

import android.app.AlertDialog;
import android.os.CountDownTimer;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.firstdayjava.R;
import com.example.firstdayjava.databinding.ViewUserBinding;
import com.example.firstdayjava.pojo.local.entities.User;
import com.example.firstdayjava.ui.dialogs.UserInfoDialog.UserInfoDialog;
import com.example.firstdayjava.ui.dialogs.UserInfoDialog.UserInfoDialogListener;

public class UserViewHolder extends RecyclerView.ViewHolder implements UserInfoDialogListener {
    ViewUserBinding bd;
    User user;
    boolean isInDeleteMode = false;

    UserViewListener listener;

    public UserViewHolder(@NonNull View itemView, UserViewListener listener) {
        super(itemView);
        bd = ViewUserBinding.bind(itemView);
        this.listener = listener;
        initEvent();
    }

    private void initEvent() {
        bd.getRoot().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isInDeleteMode) {
                    listener.onDeleteReq(user);
                } else
                    showInfoDialog();
            }

            private void showInfoDialog() {
                UserInfoDialog viewDialog = new UserInfoDialog(bd.getRoot().getContext(),
                        user,
                        UserViewHolder.this);

                new AlertDialog.Builder(bd.getRoot().getContext())
                        .setView(viewDialog.getView())
                        .setCancelable(true)
                        .create()
                        .show();
            }
        });

        bd.getRoot().setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                new CountDownTimer(2000, 100) {
                    @Override
                    public void onFinish() {
                        setDeleteMode(false);
                    }

                    @Override
                    public void onTick(long l) {

                    }
                }.start();

                setDeleteMode(true);

                return true;
            }
        });
    }

    public void bind(User user) {
        this.user = user;
        bd.userNameTV.setText(user.getFirstName() + " " + user.getLastName());
        bd.phoneTV.setText(user.getPhone());
        bd.emileTV.setText(user.getEmail());
    }

    void setDeleteMode(boolean isInDeleteMode) {
        this.isInDeleteMode = isInDeleteMode;
        if (isInDeleteMode) {
            bd.viewBack.setBackground(ContextCompat.getDrawable(
                    bd.getRoot().getContext(),
                    R.drawable.click_userview_deletemode
            ));
        } else {
            bd.viewBack.setBackground(ContextCompat.getDrawable(
                    bd.getRoot().getContext(),
                    R.drawable.click_userview
            ));
        }
    }

    @Override
    public void onUpdateReq(User user) {
        listener.onUpdateReq(user);
    }
}
