package com.binea.constant;

import com.binea.result.ErrorInfoInterface;

/**
 * Created by binea
 * Date: 29/10/2017
 * TIME: 4:54 PM
 */
public enum CityErrorInfoEnum implements ErrorInfoInterface {
    PARAMS_NO_COMPLETE("000001", "params no complete"),
    CITY_EXIST("000002", "city exist");

    private String code;

    private String message;

    CityErrorInfoEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public String getCode() {
        return this.code;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
