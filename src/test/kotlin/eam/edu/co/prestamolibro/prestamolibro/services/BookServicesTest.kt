package eam.edu.co.prestamolibro.prestamolibro.services

import eam.edu.co.prestamolibro.prestamolibro.Modelo.Editorial
import eam.edu.co.prestamolibro.prestamolibro.Modelo.Libro
import eam.edu.co.prestamolibro.prestamolibro.exceptions.BusinessException
import eam.edu.co.prestamolibro.prestamolibro.servirces.BookServices
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.transaction.annotation.Transactional
import javax.persistence.EntityManager

@SpringBootTest
@Transactional
class BookServicesTest {
    @Autowired
    lateinit var bookServices: BookServices

    @Autowired
    lateinit var entityManager: EntityManager

    @Test
    fun testCreateBookName() {
        val editorial= Editorial("1","nacho")
        entityManager.persist(editorial)
        entityManager.persist(Libro("1","1","harryPotter", editorial))

        val exception = Assertions.assertThrows(BusinessException::class.java,
            {bookServices.createBook(Libro("2","1","harryPotter", editorial))
            }
        )

        Assertions.assertEquals("This Book name  already exists", exception.message)

    }
    @Test
    fun testCreateBookCode() {
        val editorial= Editorial("1","nacho")
        entityManager.persist(editorial)
        entityManager.persist(Libro("3","1","harryPotter", editorial))
        try {
            bookServices.createBook(Libro("3","1","harryPotter", editorial))
            Assertions.fail()
        } catch (e: BusinessException) {
            Assertions.assertEquals("This Book already exists", e.message)

        }

    }



}
