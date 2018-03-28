package com.binea.pay.rpc.service.impl

import com.binea.common.annotation.BaseService
import com.binea.common.base.BaseServiceImpl
import com.binea.pay.dao.mapper.PayVestMapper
import com.binea.pay.dao.model.PayVest
import com.binea.pay.dao.model.PayVestExample
import com.binea.pay.rpc.api.PayVestService
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
open class PayVestServiceImpl : BaseServiceImpl<PayVestMapper, PayVest, PayVestExample>(), PayVestService {

    @Autowired
    internal var payVestMapper: PayVestMapper? = null

    companion object {

        private val log = LoggerFactory.getLogger(PayVestServiceImpl::class.java)
    }

}