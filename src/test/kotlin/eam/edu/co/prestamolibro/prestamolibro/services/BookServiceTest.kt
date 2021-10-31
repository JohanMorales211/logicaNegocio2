package eam.edu.co.prestamolibro.prestamolibro.services

import eam.edu.co.prestamolibro.prestamolibro.modelo.Publisher
import eam.edu.co.prestamolibro.prestamolibro.modelo.Book
import eam.edu.co.prestamolibro.prestamolibro.exceptions.BusinessException
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.transaction.annotation.Transactional
import javax.persistence.EntityManager

@SpringBootTest
@Transactional
class BookServiceTest {
    @Autowired
    lateinit var bookService: BookService

    @Autowired
    lateinit var entityManager: EntityManager

    @Test
    fun testCreateBookName() {
        val publisher= Publisher("1","nacho")
        entityManager.persist(publisher)
        entityManager.persist(Book("1","1","harryPotter", publisher))

        val exception = Assertions.assertThrows(BusinessException::class.java,
            {bookService.createBook(Book("2","1","harryPotter", publisher))
            }
        )

        Assertions.assertEquals("This Book name  already exists", exception.message)

    }
    @Test
    fun testCreateBookCode() {
        val publisher= Publisher("1","nacho")
        entityManager.persist(publisher)
        entityManager.persist(Book("3","1","harryPotter", publisher))
        try {
            bookService.createBook(Book("3","1","harryPotter", publisher))
            Assertions.fail()
        } catch (e: BusinessException) {
            Assertions.assertEquals("This Book already exists", e.message)

        }

    }



}
