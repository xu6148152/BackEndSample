package com.binea.pay.common.constant

import com.binea.common.base.BaseResult

/**
 * Created by binea
 * Date: 26/3/2018
 * TIME: 10:57 PM
 */
class PayResult constructor(payResultConstant: PayResultConstant, data: Any)
    : BaseResult(payResultConstant.code, payResultConstant.message, data) {

}