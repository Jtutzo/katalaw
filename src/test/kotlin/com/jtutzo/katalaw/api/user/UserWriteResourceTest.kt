package com.jtutzo.katalaw.api.user

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.jtutzo.katalaw.provider.user.JooqUserProjectionRepository
import com.jtutzo.katalaw.util.jeremyTutzo
import com.jtutzo.katalaw.util.toCreateUserDto
import com.jtutzo.katalaw.util.toUpdateUserDto
import org.assertj.core.api.Assertions.assertThat
import org.junit.After
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.ResultActions
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import java.util.*


@RunWith(SpringRunner::class)
@SpringBootTest
@AutoConfigureMockMvc
class UserWriteResourceTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var jooqUserProjectionRepository: JooqUserProjectionRepository

    private val url = "/users"

    @After
    fun reset() {
        jooqUserProjectionRepository.deleteAll()
    }

    @Test
    fun `should create an user`() {
        val user = jeremyTutzo()

        createUser(user.toCreateUserDto())
                .andExpect(status().isCreated)
                .andExpectedResponse<UUID> {
                    assertThat(jooqUserProjectionRepository.findById(it)).isEqualToIgnoringGivenFields(user, "id")
                }
    }

    @Test
    fun `should update an user`() {
        val user = jeremyTutzo()
        val userToUpdate = jeremyTutzo().copy(username = "jtutzoUpdated", email = "jtutzo@updated.fr")

        val id = createUser(user.toCreateUserDto()).getResponse<UUID>()

        updateUser(id, userToUpdate.toUpdateUserDto()).andExpect(status().isOk)
        assertThat(jooqUserProjectionRepository.findById(id)).isEqualToIgnoringGivenFields(userToUpdate, "id")

    }

    private fun createUser(user: CreateUserDto): ResultActions =
            mockMvc.perform(post(url)
                    .content(user.buildToJson())
                    .contentType(MediaType.APPLICATION_JSON))

    private fun updateUser(id: UUID, user: UpdateUserDto): ResultActions =
            mockMvc.perform(post("$url/$id")
                    .content(user.buildToJson())
                    .contentType(MediaType.APPLICATION_JSON))

}

private fun Any.buildToJson() = ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(this)!!

private inline fun <reified T> ResultActions.andExpectedResponse(check: (value: T) -> Unit): ResultActions {
    check(this.getResponse())
    return this
}

private inline fun <reified T> ResultActions.getResponse(): T =
        ObjectMapper().readValue(this.andReturn().response.contentAsString)
