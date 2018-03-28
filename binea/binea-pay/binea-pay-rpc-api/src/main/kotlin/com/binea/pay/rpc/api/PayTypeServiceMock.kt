package com.binea.pay.rpc.api

import com.binea.common.base.BaseServiceMock
import com.binea.pay.dao.mapper.PayTypeMapper
import com.binea.pay.dao.model.PayType
import com.binea.pay.dao.model.PayTypeExample

/**
 * Created by binea
 * Date: 28/3/2018
 * TIME: 9:23 PM
 */
class PayTypeServiceMock : BaseServiceMock<PayTypeMapper, PayType, PayTypeExample>(), PayTypeService {
}