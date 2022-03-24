package com.example.firstdayjava.pojo.dbs.models.responses.callpack;

public class Result {
    int ErrNo;
    String ErrMsg;

    public Result(int errNo, String errMsg) {
        ErrNo = errNo;
        ErrMsg = errMsg;
    }

    public int getErrNo() {
        return ErrNo;
    }

    public void setErrNo(int errNo) {
        ErrNo = errNo;
    }

    public String getErrMsg() {
        return ErrMsg;
    }

    public void setErrMsg(String errMsg) {
        ErrMsg = errMsg;
    }
}
