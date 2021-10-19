package eam.edu.co.prestamolibro.prestamolibro.services

import eam.edu.co.prestamolibro.prestamolibro.Modelo.Editorial
import eam.edu.co.prestamolibro.prestamolibro.Modelo.Libro
import eam.edu.co.prestamolibro.prestamolibro.Modelo.Usuario
import eam.edu.co.prestamolibro.prestamolibro.exceptions.BusinessException
import eam.edu.co.prestamolibro.prestamolibro.servirces.BookServices
import eam.edu.co.prestamolibro.prestamolibro.servirces.UserService
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.transaction.annotation.Transactional
import javax.persistence.EntityManager

@SpringBootTest
@Transactional
class UserServicesTest {
    @Autowired
    lateinit var userService: UserService

    @Autowired
    lateinit var entityManager: EntityManager
    @Test
    fun testCreateUserCode() {
        entityManager.persist(Usuario("3", "claudia","rodrigues"))
        try {
            userService.createUser(Usuario("3", "claudia","rodrigues"))
            Assertions.fail()
        } catch (e: BusinessException) {
            Assertions.assertEquals("This User already exists", e.message)

        }

    }
}