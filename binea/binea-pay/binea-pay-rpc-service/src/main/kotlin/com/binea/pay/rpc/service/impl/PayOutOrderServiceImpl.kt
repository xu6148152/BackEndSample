package com.binea.pay.rpc.service.impl

import com.binea.common.annotation.BaseService
import com.binea.common.base.BaseServiceImpl
import com.binea.pay.dao.mapper.PayOutOrderMapper
import com.binea.pay.dao.model.PayOutOrder
import com.binea.pay.dao.model.PayOutOrderExample
import com.binea.pay.rpc.api.PayOutOrderService
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

/**
 * Created by binea
 * Date: 28/3/2018
 * TIME: 9:44 PM
 */
@Service
@Transactional
@BaseService
open class PayOutOrderServiceImpl : BaseServiceImpl<PayOutOrderMapper, PayOutOrder, PayOutOrderExample>(), PayOutOrderService {

    @Autowired
    internal var payOutOrderMapper: PayOutOrderMapper? = null

    companion object {

        private val log = LoggerFactory.getLogger(PayOutOrderServiceImpl::class.java)
    }

}