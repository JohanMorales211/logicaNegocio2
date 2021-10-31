package eam.edu.co.prestamolibro.prestamolibro.controllers

import eam.edu.co.prestamolibro.prestamolibro.modelo.User
import eam.edu.co.prestamolibro.prestamolibro.services.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/users")

class UserController {
    @Autowired
    lateinit var userService: UserService

    @PostMapping
    fun createUser(@Validated @RequestBody user: User){
        userService.createUser(user)
    }
}