package com.example.firstdayjava.pojo.remote.models.cahnge_password;

public class ChangePasswordPostBody {
    String userCode;
    String oldPassword;
    String newPassword;

    public ChangePasswordPostBody() {
    }

    public ChangePasswordPostBody(String userCode, String oldPassword, String newPassword) {
        this.userCode = userCode;
        this.oldPassword = oldPassword;
        this.newPassword = newPassword;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
