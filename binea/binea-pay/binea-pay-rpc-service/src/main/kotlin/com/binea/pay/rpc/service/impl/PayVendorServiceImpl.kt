package com.binea.pay.rpc.service.impl

import com.binea.common.annotation.BaseService
import com.binea.common.base.BaseServiceImpl
import com.binea.pay.dao.mapper.PayVendorMapper
import com.binea.pay.dao.model.PayVendor
import com.binea.pay.dao.model.PayVendorExample
import com.binea.pay.rpc.api.PayVendorService
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

/**
 * Created by binea
 * Date: 28/3/2018
 * TIME: 9:46 PM
 */
@Service
@Transactional
@BaseService
class PayVendorServiceImpl : BaseServiceImpl<PayVendorMapper, PayVendor, PayVendorExample>(), PayVendorService {

    @Autowired
    internal var payVendorMapper: PayVendorMapper? = null

    companion object {

        private val log = LoggerFactory.getLogger(PayVendorServiceImpl::class.java)
    }

}