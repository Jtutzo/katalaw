package com.jtutzo.katalaw.domain.user

import org.axonframework.commandhandling.CommandHandler
import org.axonframework.eventsourcing.EventSourcingHandler
import org.axonframework.modelling.command.AggregateIdentifier
import org.axonframework.modelling.command.AggregateLifecycle.apply
import org.axonframework.spring.stereotype.Aggregate
import java.util.*

@Aggregate
class User {

    @AggregateIdentifier
    private lateinit var id: UUID

    constructor()

    @CommandHandler
    constructor(cmd: CreateUser) {
        apply(UserCreated(cmd.id, cmd.username, cmd.email))
    }

    @CommandHandler
    fun on(cmd: UpdateUser) {
        apply(UserUpdated(cmd.id, cmd.username, cmd.email))
    }

    @EventSourcingHandler
    fun on(evt: UserCreated) {
        id = evt.id
    }
}