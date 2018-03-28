package com.binea.pay.rpc.api

import com.binea.common.base.BaseServiceMock
import com.binea.pay.dao.mapper.PayPayMapper
import com.binea.pay.dao.model.PayPay
import com.binea.pay.dao.model.PayPayExample

/**
 * Created by binea
 * Date: 28/3/2018
 * TIME: 9:22 PM
 */
class PayPayServiceMock : BaseServiceMock<PayPayMapper, PayPay, PayPayExample>(), PayPayService {
}