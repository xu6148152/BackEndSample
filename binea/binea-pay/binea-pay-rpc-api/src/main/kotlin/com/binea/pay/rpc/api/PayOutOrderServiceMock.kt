package com.binea.pay.rpc.api

import com.binea.common.base.BaseServiceImpl
import com.binea.common.base.BaseServiceMock
import com.binea.pay.dao.mapper.PayOutOrderMapper
import com.binea.pay.dao.model.PayOutOrder
import com.binea.pay.dao.model.PayOutOrderExample

/**
 * Created by binea
 * Date: 28/3/2018
 * TIME: 9:20 PM
 */
class PayOutOrderServiceMock : BaseServiceMock<PayOutOrderMapper, PayOutOrder, PayOutOrderExample>(), PayOutOrderService {
}