package eam.edu.co.prestamolibro.prestamolibro.repositories

import eam.edu.co.prestamolibro.prestamolibro.modelo.Publisher
import eam.edu.co.prestamolibro.prestamolibro.modelo.Book
import eam.edu.co.prestamolibro.prestamolibro.repositorio.BookRepo
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.transaction.annotation.Transactional
import javax.persistence.EntityManager



@SpringBootTest
@Transactional

class repositorioBook {

    @Autowired
    lateinit var bookRepo: BookRepo

    @Autowired
    lateinit var entityManager: EntityManager

    //requisitos para una buena prueba unitaria
    //1. la preuba debe ser repetible
    //2. las pruebas deben independientes entre si
    //3. la prueba siempre debe dar el mismo resultado ante los mismo parametros (deterministica)
    @Test
    fun testCreateLibro() {
        //prerequisitos
        //que la persona no exista
        //la ejecucion de la prueba.. llamar el metodo que estoy probando
        val publisher= Publisher("1","nacho")
        entityManager.persist(publisher)
        bookRepo.createLibro(Book("3","1","harryPotter", publisher))
        //asersiones, o las verificaciones
        val book = entityManager.find(Book::class.java,  "3")
        Assertions.assertNotNull(book)
        Assertions.assertEquals("3", book.code)
        Assertions.assertEquals("1", book.isbn)
        Assertions.assertEquals("harryPotter", book.name)
        Assertions.assertEquals("1", publisher.code)
    }


    @Test
    fun testDeleteLibro(){
        //prerequisitos
        val publisher= Publisher("1","nacho")
        entityManager.persist(publisher)
        bookRepo.createLibro(Book("3","1","harryPotter", publisher))

        //ejecucion de la preuba
        bookRepo.deleteLibro("3")

        //assersiones
        val book = entityManager.find(Book::class.java, "3")
        Assertions.assertNull(book)
    }

    @Test
    fun findTestLibro() {
        val publisher= Publisher("1","nacho")
        entityManager.persist(publisher)
        bookRepo.createLibro(Book("3","1","harryPotter", publisher))

        val libro = bookRepo.findLirbo("3")

        Assertions.assertNotNull(libro)
        Assertions.assertEquals("harryPotter", libro?.name)
        Assertions.assertEquals("1", libro?.isbn)
        Assertions.assertEquals("nacho", libro?.publisher?.name)
        Assertions.assertEquals("1",libro?.publisher?.code)
    }

    @Test
    fun testUpdateLibro() {
        //prerequisito
        val publisher= Publisher("1","nacho")
        entityManager.persist(publisher)
        bookRepo.createLibro(Book("3","1","harryPotter", publisher))
        entityManager.flush()
        //ejecutando...
        val book = entityManager.find(Book::class.java, "3")
        entityManager.clear()
        book.name = "tontin"
        book.isbn ="2"
        publisher.name="nachito"

        bookRepo.updateLibro(book)

        //assersiones
        val personToAssert = entityManager.find(Book::class.java, "3")
        Assertions.assertEquals("tontin",book.name)
        Assertions.assertEquals("2", book.isbn)
        Assertions.assertEquals("nachito", publisher.name)

    }
    @Test
    fun finByPublisherTest(){
        val publisher1= Publisher("1","df")
        entityManager.persist(publisher1)
        val publisher2= Publisher("2","dg")
        entityManager.persist(publisher2)
        entityManager.persist(Book("11","1a","las reliquias",publisher1))
        entityManager.persist(Book("12","2a","El prisionero",publisher1))
        entityManager.persist(Book("13","3a","la camara",publisher2))

        val listaOne=bookRepo.findByEditorial("1")
        val listaTwo=bookRepo.findByEditorial("2")
        Assertions.assertEquals(2,listaOne.size)
        Assertions.assertEquals(1,listaTwo.size)

    }
}