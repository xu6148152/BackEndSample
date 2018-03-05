package com.binea.cms.common.constant;


import com.binea.common.base.BaseResult;

/**
 * cms系统常量枚举类
 * Created by binea on 2017/2/19.
 */
public class CmsResult extends BaseResult {

    public CmsResult(CmsResultConstant cmsResultConstant, Object data) {
        super(cmsResultConstant.getCode(), cmsResultConstant.getMessage(), data);
    }

}
