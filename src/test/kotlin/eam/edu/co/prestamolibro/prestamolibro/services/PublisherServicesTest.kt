package eam.edu.co.prestamolibro.prestamolibro.services

import eam.edu.co.prestamolibro.prestamolibro.Modelo.Editorial
import eam.edu.co.prestamolibro.prestamolibro.Modelo.Usuario
import eam.edu.co.prestamolibro.prestamolibro.exceptions.BusinessException
import eam.edu.co.prestamolibro.prestamolibro.servirces.PublisherServices
import eam.edu.co.prestamolibro.prestamolibro.servirces.UserService
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.transaction.annotation.Transactional
import javax.persistence.EntityManager

@SpringBootTest
@Transactional
class PublisherServicesTest {
    @Autowired
    lateinit var publisherServices: PublisherServices

    @Autowired
    lateinit var entityManager: EntityManager
    @Test
    fun testCreateUserCode() {
        entityManager.persist(Editorial("3", "nachito"))
        try {
            publisherServices.createPublisher(Editorial("3", "nachito"))
            Assertions.fail()
        } catch (e: BusinessException) {
            Assertions.assertEquals("This Publisher already exists", e.message)

        }

    }
}