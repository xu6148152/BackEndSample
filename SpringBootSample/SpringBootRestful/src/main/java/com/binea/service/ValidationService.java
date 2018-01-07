package com.binea.service;

import com.binea.model.ValidationModel;

/**
 * Created by binea
 * Date: 28/10/2017
 * TIME: 5:41 PM
 */
public interface ValidationService {
    ValidationModel verifyAccessToken(String token);
}
