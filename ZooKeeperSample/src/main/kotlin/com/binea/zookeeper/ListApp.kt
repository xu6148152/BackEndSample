package com.binea.zookeeper

/**
 * Created by binea
 * Date: 31/3/2018
 * TIME: 3:22 PM
 */

@Throws(Exception::class)
fun main(args: Array<String>) {
    val listGroup = ListGroup()
    listGroup.connect(args[0])
    listGroup.list(args[1])
    listGroup.close()
}