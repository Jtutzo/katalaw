package com.jtutzo.katalaw.domain.user

import org.axonframework.config.ProcessingGroup
import org.axonframework.eventhandling.EventHandler
import org.springframework.stereotype.Service

@Service
@ProcessingGroup("user")
class UserEventHandler(private val userProjectionRepository: UserProjectionRepository) {

    @EventHandler
    fun on(evt: UserCreated) {
        userProjectionRepository.create(UserProjection(evt.id, evt.username, evt.email))
    }

    @EventHandler
    fun on(evt: UserUpdated) {
        userProjectionRepository.update(UserProjection(evt.id, evt.username, evt.email))
    }

}