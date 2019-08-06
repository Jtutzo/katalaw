package com.jtutzo.katalaw.domain.user

import java.util.*

data class UserCreated(val id: UUID, val username: Username, val email: Email)
data class UserUpdated(val id: UUID, val username: Username, val email: Email)
