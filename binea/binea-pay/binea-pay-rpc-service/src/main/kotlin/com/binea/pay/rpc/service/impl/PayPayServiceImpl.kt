package com.binea.pay.rpc.service.impl

import com.binea.common.annotation.BaseService
import com.binea.common.base.BaseServiceImpl
import com.binea.pay.dao.mapper.PayPayMapper
import com.binea.pay.dao.model.PayPay
import com.binea.pay.dao.model.PayPayExample
import com.binea.pay.rpc.api.PayPayService
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

/**
 * Created by binea
 * Date: 28/3/2018
 * TIME: 9:45 PM
 */
@Service
@Transactional
@BaseService
open class PayPayServiceImpl : BaseServiceImpl<PayPayMapper, PayPay, PayPayExample>(), PayPayService {

    @Autowired
    internal var payPayMapper: PayPayMapper? = null

    companion object {

        private val log = LoggerFactory.getLogger(PayPayServiceImpl::class.java)
    }

}