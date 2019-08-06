package com.jtutzo.katalaw.provider.user

import com.jtutzo.katalaw.util.jeremyTutzo
import org.assertj.core.api.Assertions.assertThat
import org.jooq.Configuration
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jooq.JooqTest
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@JooqTest
class JooqUserProjectionRepositoryTest {

    @Autowired
    private lateinit var configuration: Configuration

    private lateinit var userProjectionRepository: JooqUserProjectionRepository

    @Before
    fun setUp() {
        userProjectionRepository = JooqUserProjectionRepository(configuration)
    }

    @After
    fun reset() {
        userProjectionRepository.deleteAll()
    }

    @Test
    fun `Should create user in db`() {
        val userToCreated = jeremyTutzo()

        userProjectionRepository.create(userToCreated)

        val result = userProjectionRepository.findById(userToCreated.id)
        assertThat(result).isEqualTo(userToCreated)
    }

    @Test
    fun `Should update user in db`() {
        val userToUpdated = jeremyTutzo().copy(username = "jtutzoUpdated", email = "jtutzo@updated.fr")
        userProjectionRepository.create(jeremyTutzo())

        userProjectionRepository.update(userToUpdated)

        val result = userProjectionRepository.findById(userToUpdated.id)
        assertThat(result).isEqualTo(userToUpdated)
    }
}