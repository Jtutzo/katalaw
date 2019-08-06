package com.jtutzo.katalaw.bootstrap

import org.h2.tools.Server
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile

@Configuration
@Profile("!docker")
class H2Config {

    private val logger = LoggerFactory.getLogger(H2Config::class.java)

    @Autowired
    fun h2TcpServer(): Server? {
        try {
            return Server.createTcpServer("-tcp", "-tcpAllowOthers", "-tcpPort", "9092").start()
        } catch (e: Exception) {
            logger.error("Failed to start h2 tcp server.")
        }
        return null
    }
}