package com.jtutzo.katalaw.domain.user

import com.jtutzo.katalaw.util.*
import org.axonframework.test.aggregate.AggregateTestFixture
import org.junit.Before
import org.junit.Test

class UserTest {

    private lateinit var fixture: AggregateTestFixture<User>

    @Before
    fun setUp() {
        fixture = AggregateTestFixture(User::class.java)
    }

    @Test
    fun `Should create user`() {
        // Given
        val userToCreate = jeremyTutzo()

        // When / Then
        fixture.givenNoPriorActivity()
                .`when`(userToCreate.toCreateUser())
                .expectSuccessfulHandlerExecution()
                .expectEvents(userToCreate.toUserCreated())
    }

    @Test
    fun `Should update user`() {
        // Given
        val userToUpdate = jeremyTutzo().copy(username = "jtutzoUpdated", email = "jtutzo@updated.fr")

        // When / Then
        fixture.given(jeremyTutzo().toUserCreated())
                .`when`(userToUpdate.toUpdateUser())
                .expectSuccessfulHandlerExecution()
                .expectEvents(userToUpdate.toUserUpdated())
    }

}