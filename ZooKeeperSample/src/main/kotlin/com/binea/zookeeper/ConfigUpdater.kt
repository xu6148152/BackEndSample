package com.binea.zookeeper

import org.apache.zookeeper.KeeperException
import java.io.IOException
import java.util.*
import java.util.concurrent.TimeUnit

/**
 * Created by binea
 * Date: 1/4/2018
 * TIME: 2:29 PM
 */
class ConfigUpdater @Throws(IOException::class, InterruptedException::class) constructor(hosts: String) {
    companion object {
        val PATH = "/config"
    }

    var store: ActiveKeyValueStore = ActiveKeyValueStore()
    var random = Random()

    init {
        store.connect(hosts)
    }

    @Throws(InterruptedException::class, KeeperException::class)
    fun run() {
        while (true) {
            val value = random.nextInt(100).toString()
            store?.write(PATH, value)
            System.out.println("Set $PATH to $value \n")
            TimeUnit.SECONDS.sleep(random.nextInt(10).toLong())
        }
    }
}

@Throws(Exception::class)
fun main(args: Array<String>) {
    val configUpdater = ConfigUpdater(args[0])
    configUpdater.run()
}