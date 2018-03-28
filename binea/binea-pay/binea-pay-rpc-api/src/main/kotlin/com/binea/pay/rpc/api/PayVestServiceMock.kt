package com.binea.pay.rpc.api

import com.binea.common.base.BaseServiceMock
import com.binea.pay.dao.mapper.PayVestMapper
import com.binea.pay.dao.model.PayVest
import com.binea.pay.dao.model.PayVestExample

/**
 * Created by binea
 * Date: 28/3/2018
 * TIME: 9:27 PM
 */
class PayVestServiceMock : BaseServiceMock<PayVestMapper, PayVest, PayVestExample>(), PayVestService {
}