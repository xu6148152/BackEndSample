package com.binea.pay.rpc.service.impl

import com.binea.common.annotation.BaseService
import com.binea.common.base.BaseServiceImpl
import com.binea.pay.dao.mapper.PayTypeMapper
import com.binea.pay.dao.model.PayType
import com.binea.pay.dao.model.PayTypeExample
import com.binea.pay.rpc.api.PayTypeService
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
open class PayTypeServiceImpl : BaseServiceImpl<PayTypeMapper, PayType, PayTypeExample>(), PayTypeService {

    @Autowired
    internal var payTypeMapper: PayTypeMapper? = null

    companion object {

        private val log = LoggerFactory.getLogger(PayTypeServiceImpl::class.java)
    }

}