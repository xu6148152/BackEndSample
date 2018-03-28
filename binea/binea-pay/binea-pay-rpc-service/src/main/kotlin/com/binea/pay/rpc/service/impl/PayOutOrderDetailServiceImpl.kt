package com.binea.pay.rpc.service.impl

import com.binea.common.annotation.BaseService
import com.binea.common.base.BaseServiceImpl
import com.binea.pay.dao.mapper.PayOutOrderDetailMapper
import com.binea.pay.dao.model.PayOutOrderDetail
import com.binea.pay.dao.model.PayOutOrderDetailExample
import com.binea.pay.rpc.api.PayOutOrderDetailService
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
open class PayOutOrderDetailServiceImpl : BaseServiceImpl<PayOutOrderDetailMapper, PayOutOrderDetail, PayOutOrderDetailExample>(), PayOutOrderDetailService {

    @Autowired
    internal var payOutOrderDetailMapper: PayOutOrderDetailMapper? = null

    companion object {

        private val log = LoggerFactory.getLogger(PayOutOrderDetailServiceImpl::class.java)
    }

}