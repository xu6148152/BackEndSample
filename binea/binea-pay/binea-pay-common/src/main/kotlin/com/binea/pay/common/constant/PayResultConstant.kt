package com.binea.pay.common.constant

/**
 * Created by binea
 * Date: 26/3/2018
 * TIME: 10:58 PM
 */
enum class PayResultConstant(code: Int, message: String) {

//    var code: Int = code
//    var message: String = message

    FAILED(0, "failed"),
    SUCCESS(1, "success");

    val code = code
    val message = message
}