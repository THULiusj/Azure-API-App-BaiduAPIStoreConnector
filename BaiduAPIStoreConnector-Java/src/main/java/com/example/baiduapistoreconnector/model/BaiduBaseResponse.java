package com.example.baiduapistoreconnector.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Standard wrapper for all Baidu API responses.")
public class BaiduBaseResponse<T> {

    @JsonProperty("errNum")
    @Schema(description = "Error number. 0 indicates success.", example = "0")
    private int errNum;

    @JsonProperty("retMsg")
    @Schema(description = "Return message. 'success' or an error message.", example = "success")
    private String retMsg;

    @JsonProperty("retData")
    @Schema(description = "The actual data payload, type varies by API.")
    private T retData;

    // Default constructor for Jackson
    public BaiduBaseResponse() {
    }

    public int getErrNum() {
        return errNum;
    }

    public void setErrNum(int errNum) {
        this.errNum = errNum;
    }

    public String getRetMsg() {
        return retMsg;
    }

    public void setRetMsg(String retMsg) {
        this.retMsg = retMsg;
    }

    public T getRetData() {
        return retData;
    }

    public void setRetData(T retData) {
        this.retData = retData;
    }
}
