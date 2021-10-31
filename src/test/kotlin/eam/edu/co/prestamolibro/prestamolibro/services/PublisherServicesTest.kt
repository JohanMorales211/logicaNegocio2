package eam.edu.co.prestamolibro.prestamolibro.services

import eam.edu.co.prestamolibro.prestamolibro.modelo.Publisher
import eam.edu.co.prestamolibro.prestamolibro.exceptions.BusinessException
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
        entityManager.persist(Publisher("3", "nachito"))
        try {
            publisherServices.createPublisher(Publisher("3", "nachito"))
            Assertions.fail()
        } catch (e: BusinessException) {
            Assertions.assertEquals("This Publisher already exists", e.message)

        }

    }
}