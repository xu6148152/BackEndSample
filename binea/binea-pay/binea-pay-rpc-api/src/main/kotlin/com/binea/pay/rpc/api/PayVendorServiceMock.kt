package com.binea.pay.rpc.api

import com.binea.common.base.BaseServiceMock
import com.binea.pay.dao.mapper.PayVendorMapper
import com.binea.pay.dao.model.PayVendor
import com.binea.pay.dao.model.PayVendorExample

/**
 * Created by binea
 * Date: 28/3/2018
 * TIME: 9:26 PM
 */
class PayVendorServiceMock : BaseServiceMock<PayVendorMapper, PayVendor, PayVendorExample>(), PayVendorService {
}