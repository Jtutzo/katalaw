package com.jtutzo.katalaw.api.user

import com.jtutzo.katalaw.domain.user.CreateUser
import com.jtutzo.katalaw.domain.user.UpdateUser
import org.axonframework.commandhandling.gateway.CommandGateway
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import java.util.*
import javax.inject.Inject

@RestController
@RequestMapping("/users")
class UserWriteResource @Inject constructor(private val commandGateway: CommandGateway) {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody dto: CreateUserDto) =
            commandGateway.sendAndWait<UUID>(CreateUser(username = dto.username, email = dto.email))!!

    @PostMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun update(@PathVariable("id") id: UUID, @RequestBody dto: UpdateUserDto) {
        commandGateway.sendAndWait<Void>(UpdateUser(id, dto.username, dto.email))
    }

}