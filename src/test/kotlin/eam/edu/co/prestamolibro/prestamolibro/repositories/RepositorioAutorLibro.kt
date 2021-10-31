package eam.edu.co.prestamolibro.prestamolibro.repositories

import eam.edu.co.prestamolibro.prestamolibro.modelo.Author
import eam.edu.co.prestamolibro.prestamolibro.modelo.AuthorBook
import eam.edu.co.prestamolibro.prestamolibro.modelo.Publisher
import eam.edu.co.prestamolibro.prestamolibro.modelo.Book
import eam.edu.co.prestamolibro.prestamolibro.repositorio.AuthorBookRepo
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.transaction.annotation.Transactional
import javax.persistence.EntityManager

@SpringBootTest
@Transactional
class repositorioAuthorBook {
    @Autowired
    lateinit var authorBookRepo: AuthorBookRepo
    @Autowired
    lateinit var entityManager: EntityManager

    //requisitos para una buena prueba unitaria
    //1. la preuba debe ser repetible
    //2. las pruebas deben independientes entre si
    //3. la prueba siempre debe dar el mismo resultado ante los mismo parametros (deterministica)
    @Test
    fun testCreateAutorLibro() {
        //prerequisitos
        //que la persona no exista

        //la ejecucion de la prueba.. llamar el metodo que estoy probando
        val author= Author(1L,"santiago","beltran")
        entityManager.persist(author)
        val publisher= Publisher("1","Matstudios")
        entityManager.persist(publisher)
        val book= Book("1","2","One",publisher)
        entityManager.persist(book)

        authorBookRepo.createAutorLibro(AuthorBook(3L,author,book ))

        //asersiones, o las verificaciones
        val authorBook = entityManager.find(AuthorBook::class.java, 3L)
        Assertions.assertNotNull(author)
        Assertions.assertEquals(1L, author.id)
        Assertions.assertEquals("1", book.code)
        Assertions.assertEquals(3, authorBook.id)

    }


    @Test
    fun testDeleteAutorLibro(){
        //prerequisitos
        val author= Author(1L,"santiago","beltran")
        entityManager.persist(author)
        val publisher= Publisher("1","Matstudios")
        entityManager.persist(publisher)
        val book= Book("1","2","One",publisher)
        entityManager.persist(book)

        authorBookRepo.createAutorLibro(AuthorBook(3L,author,book ))

        //ejecucion de la preuba
        authorBookRepo.deleteAutorLibro(3L)

        //assersiones
        val authorBook = entityManager.find(AuthorBook::class.java, 3L)
        Assertions.assertNull(authorBook)
    }

    @Test
    fun findTestAutorLibro() {
        val author= Author(1L,"santiago","beltran")
        entityManager.persist(author)
        val publisher= Publisher("1","Matstudios")
        entityManager.persist(publisher)
        val book= Book("1","2","One",publisher)
        entityManager.persist(book)

        authorBookRepo.createAutorLibro(AuthorBook(3L,author,book ))

        val autorLibro = authorBookRepo.findAutorLibro(3L)

        Assertions.assertNotNull(author)
        Assertions.assertEquals(3L, autorLibro?.id)
        Assertions.assertEquals(1L, autorLibro?.author?.id)
        Assertions.assertEquals("1", autorLibro?.book?.code)
    }
    @Test
    fun findByAutor(){
        val author1= Author(1L,"santiago","beltran")
        entityManager.persist(author1)
        val author2= Author(2L,"sebastian","beltran")
        entityManager.persist(author2)

        val publisher= Publisher("1","Matstudios")
        entityManager.persist(publisher)

        val book1= Book("1","2","One",publisher)
        entityManager.persist(book1)
        val book2= Book("2","2","Two",publisher)
        entityManager.persist(book2)
        val book3= Book("3","2","Three",publisher)
        entityManager.persist(book3)

        entityManager.persist(AuthorBook(1L,author1,book1))
        entityManager.persist(AuthorBook(2L,author1,book2))
        entityManager.persist(AuthorBook(3L,author2,book3))

        val listaOne=authorBookRepo.findByAutor(1L)
        val listaTwo=authorBookRepo.findByAutor(2L)

        Assertions.assertEquals(2,listaOne.size)
        Assertions.assertEquals(1,listaTwo.size)

    }
    @Test
    fun findByLibro(){
        val author1= Author(1L,"santiago","beltran")
        entityManager.persist(author1)
        val author2= Author(2L,"sebastian","beltran")
        entityManager.persist(author2)
        val author3= Author(3L,"javier","beltran")
        entityManager.persist(author3)

        val publisher= Publisher("1","Matstudios")
        entityManager.persist(publisher)

        val book1= Book("1","2","One",publisher)
        entityManager.persist(book1)
        val book2= Book("2","2","Two",publisher)
        entityManager.persist(book2)
        val book3= Book("3","2","Three",publisher)
        entityManager.persist(book3)


        entityManager.persist(AuthorBook(1L,author1,book1))
        entityManager.persist(AuthorBook(2L,author2,book2))
        entityManager.persist(AuthorBook(3L,author3,book3))


        val listaOne=authorBookRepo.findBylibro("1")
        val listaTwo=authorBookRepo.findBylibro("2")
        val listaThree=authorBookRepo.findBylibro("3")

        Assertions.assertEquals(1,listaOne.size)
        Assertions.assertEquals(1,listaTwo.size)
        Assertions.assertEquals(1,listaThree.size)

    }

}