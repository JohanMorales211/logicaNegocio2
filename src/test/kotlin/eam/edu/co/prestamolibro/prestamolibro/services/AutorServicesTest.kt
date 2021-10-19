package eam.edu.co.prestamolibro.prestamolibro.services

import eam.edu.co.prestamolibro.prestamolibro.Modelo.Autor
import eam.edu.co.prestamolibro.prestamolibro.Modelo.Usuario
import eam.edu.co.prestamolibro.prestamolibro.exceptions.BusinessException
import eam.edu.co.prestamolibro.prestamolibro.servirces.AutorService
import eam.edu.co.prestamolibro.prestamolibro.servirces.UserService
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.transaction.annotation.Transactional
import javax.persistence.EntityManager

@SpringBootTest
@Transactional
class AutorServicesTest {
    @Autowired
    lateinit var autorService: AutorService

    @Autowired
    lateinit var entityManager: EntityManager
    @Test
    fun testCreateUserCode() {
        entityManager.persist(Autor(3L, "tafur","rodrigues"))

        try {
            autorService.createAutor(Autor(3L, "tafur","rodrigues"))
            Assertions.fail()
        } catch (e: BusinessException) {
            Assertions.assertEquals("This Autor already exists", e.message)

        }

    }
}