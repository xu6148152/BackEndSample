package com.binea.zookeeper

import org.apache.zookeeper.WatchedEvent
import org.apache.zookeeper.Watcher
import org.apache.zookeeper.ZooKeeper
import java.io.IOException
import java.util.concurrent.CountDownLatch

/**
 * Created by binea
 * Date: 31/3/2018
 * TIME: 3:08 PM
 */
open class ConnectionWatcher : Watcher {

    companion object {
        val SESSION_TIMEOUT: Int = 5000
    }

    var zk: ZooKeeper? = null
    var connectedSignal: CountDownLatch = CountDownLatch(1)

    @Throws(IOException::class, InterruptedException::class)
    fun connect(hosts: String) {
        zk = ZooKeeper(hosts, SESSION_TIMEOUT, this)
        connectedSignal.await()
    }

    override fun process(event: WatchedEvent?) {
        if (event!!.state == Watcher.Event.KeeperState.SyncConnected) {
            connectedSignal.countDown()
        }
    }

    @Throws(InterruptedException::class)
    fun close() {
        zk?.close()
    }
}