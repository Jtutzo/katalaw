package com.jtutzo.katalaw.domain.user

interface UserProjectionRepository {
    fun create(user: UserProjection)
    fun update(user: UserProjection)
}