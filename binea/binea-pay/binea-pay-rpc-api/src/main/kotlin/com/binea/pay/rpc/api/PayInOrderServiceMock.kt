package com.binea.pay.rpc.api

import com.binea.common.base.BaseServiceMock
import com.binea.pay.dao.mapper.PayInOrderMapper
import com.binea.pay.dao.model.PayInOrder
import com.binea.pay.dao.model.PayInOrderExample

/**
 * Created by binea
 * Date: 28/3/2018
 * TIME: 9:14 PM
 */
class PayInOrderServiceMock : BaseServiceMock<PayInOrderMapper, PayInOrder, PayInOrderExample>() {
}