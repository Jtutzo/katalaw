package com.jtutzo.katalaw.bootstrap

import org.jooq.conf.RenderNameStyle
import org.jooq.conf.Settings
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class JooqConfig {

    @Bean
    fun settings(): Settings = Settings().withRenderNameStyle(RenderNameStyle.LOWER)
}