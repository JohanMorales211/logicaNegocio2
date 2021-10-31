package eam.edu.co.prestamolibro.prestamolibro.services

import eam.edu.co.prestamolibro.prestamolibro.modelo.Publisher
import eam.edu.co.prestamolibro.prestamolibro.exceptions.BusinessException
import eam.edu.co.prestamolibro.prestamolibro.repositorio.PublisherRepo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import javax.persistence.EntityManager
@Service
class PublisherServices {
    @Autowired
    lateinit var publisherRepo: PublisherRepo
    @Autowired
    lateinit var entityManager: EntityManager


    fun createPublisher(publisher: Publisher) {
        val publisherById = publisherRepo.findEditorial(publisher.code?:"")

        if(publisherById != null){
            throw BusinessException("This Publisher already exists")
        }
        publisherRepo.createEditorial(publisher)
    }
}