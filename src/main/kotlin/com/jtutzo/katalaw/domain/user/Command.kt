package com.jtutzo.katalaw.domain.user

import org.axonframework.modelling.command.TargetAggregateIdentifier
import java.util.*

data class CreateUser(@TargetAggregateIdentifier val id: UUID = UUID.randomUUID(), val username: Username, val email: Email)
data class UpdateUser(@TargetAggregateIdentifier val id: UUID, val username: Username, val email: Email)
