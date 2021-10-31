package eam.edu.co.prestamolibro.prestamolibro.controllers

import eam.edu.co.prestamolibro.prestamolibro.modelo.Author
import eam.edu.co.prestamolibro.prestamolibro.services.AuthorService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/authors")
class AuthorController {
    @Autowired
    lateinit var authorService: AuthorService

    @PostMapping
    fun createAuthor(@Validated @RequestBody author: Author){
        authorService.createAutor(author)
    }
}