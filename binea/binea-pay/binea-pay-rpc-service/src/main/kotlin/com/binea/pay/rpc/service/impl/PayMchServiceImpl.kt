package com.binea.pay.rpc.service.impl

import com.binea.common.annotation.BaseService
import com.binea.common.base.BaseServiceImpl
import com.binea.pay.dao.mapper.PayMchMapper
import com.binea.pay.dao.model.PayMch
import com.binea.pay.dao.model.PayMchExample
import com.binea.pay.rpc.api.PayMchService
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
open class PayMchServiceImpl : BaseServiceImpl<PayMchMapper, PayMch, PayMchExample>(), PayMchService {

    @Autowired
    internal var payMchMapper: PayMchMapper? = null

    companion object {

        private val log = LoggerFactory.getLogger(PayMchServiceImpl::class.java)
    }

}