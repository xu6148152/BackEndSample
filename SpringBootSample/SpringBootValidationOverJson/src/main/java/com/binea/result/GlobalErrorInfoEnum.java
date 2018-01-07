package com.binea.result;

/**
 * Created by binea
 * Date: 29/10/2017
 * TIME: 4:45 PM
 */
public enum GlobalErrorInfoEnum implements ErrorInfoInterface {
    SUCCESS("0", "success"),
    NOT_FOUND("-1", "service not found"),;

    private String code;
    private String message;

    GlobalErrorInfoEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
