package com.binea.upms.server.shiro.listener

import org.apache.shiro.session.Session
import org.apache.shiro.session.SessionListener
import org.slf4j.Logger
import org.slf4j.LoggerFactory

/**
 * Created by binea
 * Date: 22/3/2018
 * TIME: 11:06 PM
 */
class ShiroSessionListener : SessionListener {
    private val LOGGER: Logger = LoggerFactory.getLogger(ShiroSessionListener::class.java)

    override fun onExpiration(p0: Session) {
        LOGGER.debug("Session expiration: " + p0.id)
    }

    override fun onStart(p0: Session) {
        LOGGER.debug("Session start: " + p0.id)
    }

    override fun onStop(p0: Session) {
        LOGGER.debug("Session stop: " + p0.id)
    }
}