package com.example.baiduapistoreconnector.exception;

public class BaiduApiResponseException extends RuntimeException {

    private final int errNum;
    private final String retMsg;

    public BaiduApiResponseException(int errNum, String retMsg, String message) {
        super(message);
        this.errNum = errNum;
        this.retMsg = retMsg;
    }

    public int getErrNum() {
        return errNum;
    }

    public String getRetMsg() {
        return retMsg;
    }
}
