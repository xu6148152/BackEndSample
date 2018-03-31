package com.binea.zookeeper

/**
 * Created by binea
 * Date: 31/3/2018
 * TIME: 3:13 PM
 */

fun main(args: Array<String>) {
    val joinGroup = JoinGroup()
    joinGroup.connect(args[1])
    joinGroup.join(args[1], args[2])
    Thread.sleep(Long.MAX_VALUE)
}