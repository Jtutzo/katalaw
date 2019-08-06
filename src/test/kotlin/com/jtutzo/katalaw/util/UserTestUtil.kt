package com.jtutzo.katalaw.util

import com.jtutzo.katalaw.api.user.CreateUserDto
import com.jtutzo.katalaw.api.user.UpdateUserDto
import com.jtutzo.katalaw.domain.user.*
import java.util.UUID.fromString

fun jeremyTutzo() = UserProjection(fromString("6ad0089c-5d7c-4343-900e-131a7bb99b42"), "jtutzo", "jtutzo@email.fr")
fun UserProjection.toCreateUser() = CreateUser(id, username, email)
fun UserProjection.toUserCreated() = UserCreated(id, username, email)
fun UserProjection.toUpdateUser() = UpdateUser(id, username, email)
fun UserProjection.toUserUpdated() = UserUpdated(id, username, email)
fun UserProjection.toCreateUserDto() = CreateUserDto(username, email)
fun UserProjection.toUpdateUserDto() = UpdateUserDto(username, email)
