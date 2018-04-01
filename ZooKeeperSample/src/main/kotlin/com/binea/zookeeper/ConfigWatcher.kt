package com.binea.zookeeper

import javafx.event.EventType
import org.apache.zookeeper.KeeperException
import org.apache.zookeeper.WatchedEvent
import org.apache.zookeeper.Watcher
import java.io.IOException

/**
 * Created by binea
 * Date: 1/4/2018
 * TIME: 2:40 PM
 */
class ConfigWatcher @Throws(IOException::class, InterruptedException::class) constructor(hosts: String) : Watcher {

    var store: ActiveKeyValueStore = ActiveKeyValueStore()

    init {
        store.connect(hosts)
    }

    @Throws(IOException::class, InterruptedException::class)
    fun displayConfig() {
        val value = store.read(ConfigUpdater.PATH, this)
        System.out.println("Read ${ConfigUpdater.PATH} as $value\n")
    }

    override fun process(event: WatchedEvent?) {
        if (event?.type == Watcher.Event.EventType.NodeDataChanged) {
            try {
                displayConfig()
            } catch (e: InterruptedException) {
                System.err.println("Interrupted Exiting")
                Thread.currentThread().interrupt()
            } catch (e: KeeperException) {
                System.err.println("KeeperException $e. exiting.\n")
            }
        }
    }

    @Throws(Exception::class)
    fun main(args: Array<String>) {
        val configWatcher = ConfigWatcher(args[0])
        configWatcher.displayConfig()

        Thread.sleep(Long.MAX_VALUE)
    }
}