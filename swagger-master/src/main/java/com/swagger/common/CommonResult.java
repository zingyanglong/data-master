package com.swagger.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @Author: YL
 * @Date: 2023/06/08/15:17
 * @Description:
 */
@ApiModel("通用返回结果")
public class CommonResult<E> implements Serializable {
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(
            name = "state",
            notes = "状态 true：操作成功  false：操作失败"
    )
    Boolean state;
    @ApiModelProperty(
            name = "message",
            notes = "提示信息"
    )
    String message;
    @ApiModelProperty(
            name = "value",
            notes = "返回的数据"
    )
    E value;
    @ApiModelProperty("标准的http状态码：200, 400, 401, 500等")
    private Integer code;
    @ApiModelProperty("错误代码")
    private String errorCode;
    @ApiModelProperty("日志ID")
    private String logId;

    public static <E> CommonResult<E> ok() {
        CommonResult<E> cr = new CommonResult(ResponseErrorEnums.SUCCESS_OPTION);
        return cr;
    }

    public static <E> CommonResult<E> error() {
        CommonResult<E> cr = new CommonResult(ResponseErrorEnums.SYSTEM_ERROR);
        return cr;
    }

    public static <E> CommonResult<E> result(ResponseErrorEnums res) {
        CommonResult<E> cr = new CommonResult(res);
        return cr;
    }

    public CommonResult<E> message(String message) {
        this.setMessage(message);
        return this;
    }

    public CommonResult<E> value(E val) {
        this.setValue(val);
        return this;
    }

    public CommonResult<E> log(String logId) {
        this.setLogId(logId);
        return this;
    }

    public CommonResult() {
        this.state = true;
        this.code = 200;
    }

    public CommonResult(String message) {
        this(true, message, null);
    }

    public CommonResult(boolean state, String message) {
        this(state, message, null);
    }

    public CommonResult(boolean state, String message, E value) {
        this.state = true;
        this.code = 200;
        this.state = state;
        this.message = message;
        this.value = value;
    }

    public CommonResult(ResponseErrorEnums error) {
        this.state = true;
        this.code = 200;
        if (!error.getCode().equals("200")) {
            this.state = false;
        }

        this.errorCode = error.getCode();
        this.message = error.getMessage();
        this.code = error.getHttpCode();
    }

    public CommonResult(ResponseErrorEnums error, E value) {
        this.state = true;
        this.code = 200;
        if (!error.getCode().equals("200")) {
            this.state = false;
        }

        this.errorCode = error.getCode();
        this.message = error.getMessage();
        this.code = error.getHttpCode();
        this.value = value;
    }

    public Boolean getState() {
        return this.state;
    }

    public void setState(Boolean state) {
        this.state = state;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public E getValue() {
        return this.value;
    }

    public void setValue(E value) {
        this.value = value;
    }

    public String getLogId() {
        return this.logId;
    }

    public void setLogId(String logId) {
        this.logId = logId;
    }

    public Integer getCode() {
        return this.code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getErrorCode() {
        return this.errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }
}
