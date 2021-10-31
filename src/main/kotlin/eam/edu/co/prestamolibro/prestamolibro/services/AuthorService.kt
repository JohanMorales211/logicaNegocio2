package eam.edu.co.prestamolibro.prestamolibro.services

import eam.edu.co.prestamolibro.prestamolibro.modelo.Author
import eam.edu.co.prestamolibro.prestamolibro.exceptions.BusinessException
import eam.edu.co.prestamolibro.prestamolibro.repositorio.AuthorRepo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import javax.persistence.EntityManager
@Service
class AuthorService {
    @Autowired
    lateinit var authorRepo: AuthorRepo
    @Autowired
    lateinit var entityManager: EntityManager


    fun createAutor(author: Author) {
        val autorById = authorRepo.findAutor(author.id)

        if(autorById != null){
            throw BusinessException("This Autor already exists")
        }
        authorRepo.createAutor(author)

 }
}