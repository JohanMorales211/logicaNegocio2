package eam.edu.co.prestamolibro.prestamolibro.servirces

import eam.edu.co.prestamolibro.prestamolibro.Modelo.Autor
import eam.edu.co.prestamolibro.prestamolibro.Modelo.Libro
import eam.edu.co.prestamolibro.prestamolibro.exceptions.BusinessException
import eam.edu.co.prestamolibro.prestamolibro.repositorio.AutorRepo
import eam.edu.co.prestamolibro.prestamolibro.repositorio.LibroRepo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import javax.persistence.EntityManager
@Service
class AutorService {
    @Autowired
    lateinit var autorRepo: AutorRepo
    @Autowired
    lateinit var entityManager: EntityManager


    fun createAutor(autor: Autor) {
        val autorById = autorRepo.findAutor(autor.id)

        if(autorById != null){
            throw BusinessException("This Autor already exists")
        }
        autorRepo.createAutor(autor)

 }
}