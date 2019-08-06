package com.jtutzo.katalaw.domain.user

import com.jtutzo.katalaw.util.jeremyTutzo
import com.jtutzo.katalaw.util.toUserCreated
import com.jtutzo.katalaw.util.toUserUpdated
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.*

class UserEventHandlerTest {

    private lateinit var userEventHandler: UserEventHandler

    @Mock
    private val userProjectionRepository: UserProjectionRepository = mock(UserProjectionRepository::class.java)

    @Before
    fun setUp() {
        userEventHandler = UserEventHandler(userProjectionRepository)
    }

    @Test
    fun `Should create user in db`() {
        // Given
        val userToCreated = jeremyTutzo()

        // When
        userEventHandler.on(userToCreated.toUserCreated())

        // Then
        verify(userProjectionRepository, times(1)).create(userToCreated)
    }

    @Test
    fun `Should update user in db`() {
        // Given
        val userToUpdated = jeremyTutzo()

        // When
        userEventHandler.on(userToUpdated.toUserUpdated())

        // Then
        verify(userProjectionRepository, times(1)).update(userToUpdated)
    }

}