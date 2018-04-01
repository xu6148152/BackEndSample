package com.binea.zookeeper

import org.apache.zookeeper.CreateMode
import org.apache.zookeeper.KeeperException
import org.apache.zookeeper.Watcher
import org.apache.zookeeper.ZooDefs
import java.nio.charset.Charset
import java.util.concurrent.TimeUnit

/**
 * Created by binea
 * Date: 1/4/2018
 * TIME: 2:25 PM
 */
class ActiveKeyValueStore : ConnectionWatcher() {
    companion object {
        val CHARSET: Charset = Charset.forName("UTF-8")
        val MAX_RETRIES = 10
        val RETRY_PERIOD_SECONDS = 10L
    }

    @Throws(InterruptedException::class, KeeperException::class)
    fun write(path: String, value: String) {
        var retries = 0
        while (true) {
            try {
                val state = zk?.exists(path, false)
                if (state == null) {
                    zk?.create(path, value.toByteArray(CHARSET), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT)
                } else {
                    zk?.setData(path, value.toByteArray(CHARSET), -1)
                }
            } catch (e: KeeperException.SessionExpiredException) {
                throw e
            } catch (e: KeeperException) {
                if (retries++ == MAX_RETRIES) {
                    throw e
                }

                TimeUnit.SECONDS.sleep(RETRY_PERIOD_SECONDS)
            }
        }

    }

    @Throws(InterruptedException::class, KeeperException::class)
    fun read(path: String, watcher: Watcher): String {
        val data: ByteArray = zk?.getData(path, watcher, null)!!
        return String(data, CHARSET)
    }
}