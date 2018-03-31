package com.binea.zookeeper

import org.apache.zookeeper.CreateMode
import org.apache.zookeeper.KeeperException
import org.apache.zookeeper.ZooDefs

/**
 * Created by binea
 * Date: 31/3/2018
 * TIME: 3:06 PM
 */
class JoinGroup : ConnectionWatcher() {

    @Throws(KeeperException::class, InterruptedException::class)
    fun join(groupName: String, memberName: String) {
        val path = "/$groupName/$memberName"
        val createdPath = zk!!.create(path, null, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT)
        System.out.println("Created $createdPath")
    }
}