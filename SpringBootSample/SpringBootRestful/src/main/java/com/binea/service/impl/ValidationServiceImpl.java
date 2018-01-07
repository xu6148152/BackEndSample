package com.binea.service.impl;

import com.binea.model.ValidationModel;
import com.binea.service.ValidationService;
import org.springframework.stereotype.Service;

/**
 * Created by binea
 * Date: 28/10/2017
 * TIME: 5:41 PM
 */
@Service
public class ValidationServiceImpl implements ValidationService {

    @Override
    public ValidationModel verifyAccessToken(String token) {
        ValidationModel model = null;
        if ("1".equals(token)) {
            model = new ValidationModel();
            model.setUid(1);
        }
        return model;
    }

}
