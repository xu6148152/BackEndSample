package com.binea.zookeeper

import org.apache.zookeeper.*

/**
 * Created by binea
 * Date: 31/3/2018
 * TIME: 2:37 PM
 */
class CreateGroup : ConnectionWatcher() {

    @Throws(KeeperException::class, InterruptedException::class)
    fun create(groupName: String?) {
        val path = "/" + groupName
        val createPath = zk!!.create(path, null, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT)
        System.out.println("Created " + createPath)
    }
}

fun main(args: Array<String>) {
    val createGroup = CreateGroup()
    createGroup.connect(args[0])
    createGroup.create(args[1])
    createGroup.close()
}