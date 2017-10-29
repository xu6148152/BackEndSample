package com.binea.result;

/**
 * Created by binea
 * Date: 29/10/2017
 * TIME: 4:40 PM
 */
public class GlobalErrorInfoException extends Exception {
    private ErrorInfoInterface mErrorInfo;

    public GlobalErrorInfoException(ErrorInfoInterface errorInfo) {
        this.mErrorInfo = errorInfo;
    }

    public ErrorInfoInterface getErrorInfo() {
        return mErrorInfo;
    }

    public void setErrorInfo(ErrorInfoInterface errorInfo) {
        this.mErrorInfo = errorInfo;
    }
}
