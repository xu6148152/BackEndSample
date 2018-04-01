package com.binea.zookeeper

import org.apache.zookeeper.KeeperException

/**
 * Created by binea
 * Date: 31/3/2018
 * TIME: 3:17 PM
 */
class ListGroup : ConnectionWatcher() {

    @Throws(KeeperException::class, InterruptedException::class)
    fun list(groupName: String) {
        val path = "/$groupName"

        try {
            val children = zk?.getChildren(path, false)
            if (children?.isEmpty()!!) {
                System.out.println("No members in group $groupName")
                System.exit(1)
            }

            children.map { item -> System.out.println(item) }
        } catch (e: KeeperException.NoNodeException) {
            System.out.printf("Group %s does not exist\n", groupName)
            System.exit(1)
        }
    }
}

@Throws(Exception::class)
fun main(args: Array<String>) {
    val listGroup = ListGroup()
    listGroup.connect(args[0])
    listGroup.list(args[1])
    listGroup.close()
}