package com.binea.zookeeper

/**
 * Created by binea
 * Date: 31/3/2018
 * TIME: 3:28 PM
 */

fun main(args: Array<String>) {
    val deleteGroup = DeleteGroup()
    deleteGroup.connect(args[0])
    deleteGroup.delete(args[1])
    deleteGroup.close()
}