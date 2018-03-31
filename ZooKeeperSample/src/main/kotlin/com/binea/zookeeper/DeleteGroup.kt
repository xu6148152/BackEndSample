package com.binea.zookeeper

import org.apache.zookeeper.KeeperException

/**
 * Created by binea
 * Date: 31/3/2018
 * TIME: 3:24 PM
 */
class DeleteGroup : ConnectionWatcher() {

    @Throws(KeeperException::class, InterruptedException::class)
    fun delete(groupName: String) {
        val path = "/$groupName"

        try {
            val children = zk?.getChildren(path, false)
            children?.map { item -> zk?.delete("$path/$item", -1) }
            zk?.delete(path, -1)
        } catch (e: KeeperException.NoNodeException) {
            System.out.println("Group $groupName does not exists\n")
            System.exit(1)
        }
    }
}