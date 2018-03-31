package com.binea.zookeeper

import org.apache.zookeeper.*
import java.io.IOException
import java.util.concurrent.CountDownLatch

/**
 * Created by binea
 * Date: 31/3/2018
 * TIME: 2:37 PM
 */
class CreateGroup : Watcher {
    companion object {
        val SESSION_TIMEOUT: Int = 5000
    }

    var zk: ZooKeeper? = null
    var connectedSignal: CountDownLatch = CountDownLatch(1)

    @Throws(IOException::class, InterruptedException::class)
    fun connect(hosts: String?) {
        zk = ZooKeeper(hosts, SESSION_TIMEOUT, this)
        connectedSignal.await()
    }

    override fun process(event: WatchedEvent?) {
        if (event!!.state == Watcher.Event.KeeperState.SyncConnected) {
            connectedSignal.countDown()
        }
    }

    @Throws(KeeperException::class, InterruptedException::class)
    fun create(groupName: String?) {
        val path = "/" + groupName
        val createPath = zk!!.create(path, null, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT)
        System.out.println("Created " + createPath)
    }

    @Throws(InterruptedException::class)
    fun close() {
        zk!!.close()
    }
}