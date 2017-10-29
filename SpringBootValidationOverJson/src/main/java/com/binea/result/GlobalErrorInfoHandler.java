package com.binea.result;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by binea
 * Date: 29/10/2017
 * TIME: 4:39 PM
 */
@RestControllerAdvice
public class GlobalErrorInfoHandler {

    @ExceptionHandler(value = GlobalErrorInfoException.class)
    public ResultBody errorHandlerOverJson(HttpServletRequest request, GlobalErrorInfoException exception) {
        ErrorInfoInterface errorInfo = exception.getErrorInfo();
        return new ResultBody(errorInfo);
    }
}
