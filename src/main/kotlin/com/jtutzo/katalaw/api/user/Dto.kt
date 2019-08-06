package com.jtutzo.katalaw.api.user

import com.jtutzo.katalaw.domain.user.Email
import com.jtutzo.katalaw.domain.user.Username

data class CreateUserDto(val username: Username, val email: Email)
data class UpdateUserDto(val username: Username, val email: Email)