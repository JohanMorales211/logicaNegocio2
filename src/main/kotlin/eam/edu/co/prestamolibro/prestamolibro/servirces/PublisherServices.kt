package eam.edu.co.prestamolibro.prestamolibro.servirces

import eam.edu.co.prestamolibro.prestamolibro.Modelo.Editorial
import eam.edu.co.prestamolibro.prestamolibro.Modelo.Usuario
import eam.edu.co.prestamolibro.prestamolibro.exceptions.BusinessException
import eam.edu.co.prestamolibro.prestamolibro.repositorio.EditorialRepo
import eam.edu.co.prestamolibro.prestamolibro.repositorio.UsuarioRepo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import javax.persistence.EntityManager
@Service
class PublisherServices {
    @Autowired
    lateinit var editorialRepo: EditorialRepo
    @Autowired
    lateinit var entityManager: EntityManager


    fun createPublisher(editorial:Editorial) {
        val publisherById = editorialRepo.findEditorial(editorial.code?:"")

        if(publisherById != null){
            throw BusinessException("This Publisher already exists")
        }
        editorialRepo.createEditorial(editorial)
    }
}