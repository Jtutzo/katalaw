package com.jtutzo.katalaw.domain.user

import java.util.*

typealias Username = String
typealias Email = String

data class UserProjection(val id: UUID, val username: Username, val email: Email)