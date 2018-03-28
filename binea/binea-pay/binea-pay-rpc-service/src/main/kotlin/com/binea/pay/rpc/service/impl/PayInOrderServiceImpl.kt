package com.binea.pay.rpc.service.impl

import com.binea.common.annotation.BaseService
import com.binea.common.base.BaseServiceImpl
import com.binea.pay.dao.mapper.PayInOrderMapper
import com.binea.pay.dao.model.PayInOrder
import com.binea.pay.dao.model.PayInOrderExample
import com.binea.pay.rpc.api.PayInOrderService
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

/**
 * Created by binea
 * Date: 28/3/2018
 * TIME: 9:43 PM
 */
@Service
@Transactional
@BaseService
open class PayInOrderServiceImpl : BaseServiceImpl<PayInOrderMapper, PayInOrder, PayInOrderExample>(), PayInOrderService {

    @Autowired
    internal var payInOrderMapper: PayInOrderMapper? = null

    companion object {
        private val log = LoggerFactory.getLogger(PayInOrderServiceImpl::class.java)
    }

}