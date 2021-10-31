package eam.edu.co.prestamolibro.prestamolibro.repositories

import eam.edu.co.prestamolibro.prestamolibro.modelo.Book
import eam.edu.co.prestamolibro.prestamolibro.modelo.Borrow
import eam.edu.co.prestamolibro.prestamolibro.modelo.Publisher
import eam.edu.co.prestamolibro.prestamolibro.modelo.User
import eam.edu.co.prestamolibro.prestamolibro.repositorio.BorrowRepo
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.transaction.annotation.Transactional
import java.util.*
import javax.persistence.EntityManager

@SpringBootTest
@Transactional
class repositorioBorrow {
    @Autowired
    lateinit var borrowRepo: BorrowRepo

    @Autowired
    lateinit var entityManager:EntityManager

    //requisitos para una buena prueba unitaria
    //1. la preuba debe ser repetible
    //2. las pruebas deben independientes entre si
    //3. la prueba siempre debe dar el mismo resultado ante los mismo parametros (deterministica)
    @Test
    fun testCreatePrestamo() {
        //prerequisitos
        //que la persona no exista
        val fecha=Date(2021,2,24)
        val user= User("3", "claudia","rodrigues")
        entityManager.persist(user)
        val publisher= Publisher("3", "nachito")
        entityManager.persist(publisher)
        val book= Book("3","1","harryPotter",publisher)
        entityManager.persist(book)

        //la ejecucion de la prueba.. llamar el metodo que estoy probando
        borrowRepo.createPrestamo(Borrow(3L,book,user,fecha))

        //asersiones, o las verificaciones
        val borrow = entityManager.find(Borrow::class.java, 3L)
        Assertions.assertNotNull(borrow)
        Assertions.assertEquals("3", user.identification)
        Assertions.assertEquals("3", publisher.code)
        Assertions.assertEquals("3",book.code)
        Assertions.assertEquals(3L,borrow.id)
        Assertions.assertEquals(Date(2021,2,24,),borrow.dateTime)



    }


    @Test
    fun testDeletePrestamo(){
        //prerequisitos
        val fecha=Date(2021,2,24)
        val user= User("3", "claudia","rodrigues")
        entityManager.persist(user)
        val publisher= Publisher("3", "nachito")
        entityManager.persist(publisher)
        val book= Book("3","1","harryPotter",publisher)
        entityManager.persist(book)

        //la ejecucion de la prueba.. llamar el metodo que estoy probando
        borrowRepo.createPrestamo(Borrow(3L,book,user,fecha))

        //ejecucion de la preuba
        borrowRepo.deletePrestamo(3L)

        //assersiones
        val borrow = entityManager.find(Borrow::class.java, 3L)
        Assertions.assertNull(borrow)
    }

    @Test
    fun findTestPrestamo() {
        val fecha=Date(2021,2,24)
        val user= User("3", "claudia","rodrigues")
        entityManager.persist(user)
        val publisher= Publisher("3", "nachito")
        entityManager.persist(publisher)
        val book= Book("3","1","harryPotter",publisher)
        entityManager.persist(book)

        //la ejecucion de la prueba.. llamar el metodo que estoy probando
        borrowRepo.createPrestamo(Borrow(3L,book,user,fecha))

        val prestamo = borrowRepo.findPrestamo(3L)

        Assertions.assertNotNull(prestamo)
        Assertions.assertEquals(3L, prestamo?.id)
    }

    @Test
    fun testUpdatePrestamo() {
        //prerequisito
        val fecha=Date(2021,2,24)
        val user= User("3", "claudia","rodrigues")
        entityManager.persist(user)
        val publisher= Publisher("3", "nachito")
        entityManager.persist(publisher)
        val book= Book("3","1","harryPotter",publisher)
        entityManager.persist(book)
        //la ejecucion de la prueba.. llamar el metodo que estoy probando
        borrowRepo.createPrestamo(Borrow(3L,book,user,fecha))
        entityManager.flush()
        //ejecutando...
        val borrow = entityManager.find(Borrow::class.java, 3L)

        borrow.dateTime=Date(2021,4,20)


        borrowRepo.updatePrestamo(borrow)

        //assersiones
        val personToAssert = entityManager.find(Borrow::class.java, 3L)
        Assertions.assertEquals(Date(2021,4,20), personToAssert.dateTime)

    }
    @Test
    fun findByUsuario(){
        val user1= User("1", "Sandra","florez")
        entityManager.persist(user1)
        val user2= User("2", "melissa","forero")
        entityManager.persist(user2)

        val publisher1= Publisher("1","df")
        entityManager.persist(publisher1)

        val book1= Book("11","1a","las reliquias",publisher1)
        entityManager.persist(book1)
        val book2= Book("12","2a","el prisionero",publisher1)
        entityManager.persist(book2)
        val book3= Book("13","3a","el ganador",publisher1)
        entityManager.persist(book3)

        entityManager.persist(Borrow(1L,book1,user1,Date(2000,1,8)))
        entityManager.persist(Borrow(2L,book2,user1,Date(2000,1,8)))
        entityManager.persist(Borrow(3L,book3,user2,Date(2000,1,8)))

        val listaOne=borrowRepo.findByUsuario("1")
        val listaTwo=borrowRepo.findByUsuario("2")
        Assertions.assertEquals(2,listaOne.size)
        Assertions.assertEquals(1,listaTwo.size)

    }
    @Test
    fun findByLibro(){
        val user1= User("1", "Sandra","florez")
        entityManager.persist(user1)
        val user2= User("2", "melissa","forero")
        entityManager.persist(user2)

        val publisher1= Publisher("1","df")
        entityManager.persist(publisher1)

        val book1= Book("11","1a","las reliquias",publisher1)
        entityManager.persist(book1)
        val book2= Book("12","2a","el prisionero",publisher1)
        entityManager.persist(book2)
        val book3= Book("13","3a","el ganador",publisher1)
        entityManager.persist(book3)

        entityManager.persist(Borrow(1L,book1,user1,Date(2000,1,8)))
        entityManager.persist(Borrow(2L,book2,user1,Date(2000,1,8)))
        entityManager.persist(Borrow(3L,book3,user2,Date(2000,1,8)))
        entityManager.persist(Borrow(4L,book2,user2,Date(2000,1,8)))

        val listaOne=borrowRepo.findBylibro("11")
        val listaTwo=borrowRepo.findBylibro("12")
        val listaThree=borrowRepo.findBylibro("13")
        Assertions.assertEquals(1,listaOne.size)
        Assertions.assertEquals(2,listaTwo.size)
        Assertions.assertEquals(1,listaThree.size)

    }
    @Test
    fun findByAutor(){
        val user1= User("1", "Sandra","florez")
        entityManager.persist(user1)
        val user2= User("2", "melissa","forero")
        entityManager.persist(user2)

        val publisher1= Publisher("1","df")
        entityManager.persist(publisher1)

        val book1= Book("11","1a","las reliquias",publisher1)
        entityManager.persist(book1)
        val book2= Book("12","2a","el prisionero",publisher1)
        entityManager.persist(book2)
        val book3= Book("13","3a","el ganador",publisher1)
        entityManager.persist(book3)

        entityManager.persist(Borrow(1L,book1,user1,Date(2000,1,8)))
        entityManager.persist(Borrow(2L,book2,user1,Date(2000,1,8)))
        entityManager.persist(Borrow(3L,book3,user2,Date(2000,1,8)))
        entityManager.persist(Borrow(4L,book2,user2,Date(2000,1,8)))

        val listaOne=borrowRepo.findBylibro("11")
        val listaTwo=borrowRepo.findBylibro("12")
        val listaThree=borrowRepo.findBylibro("13")
        Assertions.assertEquals(1,listaOne.size)
        Assertions.assertEquals(2,listaTwo.size)
        Assertions.assertEquals(1,listaThree.size)

    }
}