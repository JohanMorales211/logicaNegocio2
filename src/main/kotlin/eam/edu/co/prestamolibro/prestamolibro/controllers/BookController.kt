package eam.edu.co.prestamolibro.prestamolibro.controllers

import eam.edu.co.prestamolibro.prestamolibro.modelo.Book
import eam.edu.co.prestamolibro.prestamolibro.services.BookService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/books")
class BookController {
    @Autowired
    lateinit var bookService: BookService

    @PostMapping
    fun createBook(@RequestBody book: Book){
        bookService.createBook(book)
    }

}