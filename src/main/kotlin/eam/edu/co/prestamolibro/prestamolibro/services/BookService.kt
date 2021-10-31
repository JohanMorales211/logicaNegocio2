package eam.edu.co.prestamolibro.prestamolibro.services

import eam.edu.co.prestamolibro.prestamolibro.modelo.Book
import eam.edu.co.prestamolibro.prestamolibro.exceptions.BusinessException
import eam.edu.co.prestamolibro.prestamolibro.repositorio.BookRepo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import javax.persistence.EntityManager

@Service
class BookService {
    @Autowired
    lateinit var bookRepo: BookRepo
    @Autowired
    lateinit var entityManager: EntityManager

    fun createBook(book: Book) {
        val productById = bookRepo.findLirbo(book.code?:"")
        if(productById != null){
            throw BusinessException("This Book already exists")
        }
        val productByNamE = bookRepo.findLirboName(book.name)

        if(productByNamE != null){
            throw BusinessException("This Book name  already exists")
        }
        bookRepo.createLibro(book)
    }
}