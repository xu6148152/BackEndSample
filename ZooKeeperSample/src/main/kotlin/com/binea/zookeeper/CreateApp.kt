package com.binea.zookeeper

/**
 * Created by binea
 * Date: 31/3/2018
 * TIME: 3:02 PM
 */

fun main(args: Array<String>) {
    val createGroup = CreateGroup()
    createGroup.connect(args[0])
    createGroup.create(args[1])
    createGroup.close()
}