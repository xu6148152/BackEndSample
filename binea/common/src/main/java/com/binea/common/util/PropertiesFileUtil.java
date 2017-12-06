package com.binea.common.util;

import java.util.ResourceBundle;

/**
 * Created by binea
 * Date: 6/12/2017
 * TIME: 10:02 PM
 */
public class PropertiesFileUtil {

    private ResourceBundle resourceBundle = null;

    public PropertiesFileUtil(String bundleFile) {
        resourceBundle = ResourceBundle.getBundle(bundleFile);
    }

    public String getValue(String key) {
        if (null == resourceBundle) {
            return null;
        }
        return resourceBundle.getString(key);
    }

}

