package com.binea.pay.rpc.service.impl

import com.binea.common.annotation.BaseService
import com.binea.common.base.BaseServiceImpl
import com.binea.pay.dao.mapper.PayInOrderDetailMapper
import com.binea.pay.dao.model.PayInOrderDetail
import com.binea.pay.dao.model.PayInOrderDetailExample
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

/**
 * Created by binea
 * Date: 28/3/2018
 * TIME: 9:35 PM
 */
@Service
@Transactional
@BaseService
open class PayInOrderDetailServiceImpl : BaseServiceImpl<PayInOrderDetailMapper, PayInOrderDetail, PayInOrderDetailExample>() {
    private val _log = LoggerFactory.getLogger(PayInOrderDetailServiceImpl::class.java)

    @Autowired
    var payInOrderDetailMapper: PayInOrderDetailMapper? = null
}