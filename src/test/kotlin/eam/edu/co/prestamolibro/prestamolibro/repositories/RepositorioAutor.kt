package eam.edu.co.prestamolibro.prestamolibro.repositories

import eam.edu.co.prestamolibro.prestamolibro.modelo.Author
import eam.edu.co.prestamolibro.prestamolibro.repositorio.AuthorRepo
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.transaction.annotation.Transactional
import javax.persistence.EntityManager

@SpringBootTest
@Transactional
class repositorioAuthor {
    @Autowired
    lateinit var authorRepo:AuthorRepo
    @Autowired
    lateinit var entityManager: EntityManager

    //requisitos para una buena prueba unitaria
    //1. la preuba debe ser repetible
    //2. las pruebas deben independientes entre si
    //3. la prueba siempre debe dar el mismo resultado ante los mismo parametros (deterministica)
    @Test
    fun testCreateAutor() {
        //prerequisitos
        //que la persona no exista

        //la ejecucion de la prueba.. llamar el metodo que estoy probando
        authorRepo.createAutor(Author(3L, "tafur","rodrigues"))

        //asersiones, o las verificaciones
        val author = entityManager.find(Author::class.java, 3L)
        Assertions.assertNotNull(author)
        Assertions.assertEquals(3L, author.id)
        Assertions.assertEquals("tafur", author.name)
        Assertions.assertEquals("rodrigues", author.lastname)

    }


    @Test
    fun testDeleteEditorial(){
        //prerequisitos
        authorRepo.createAutor(Author(3L, "tafur","rodrigues"))

        //ejecucion de la preuba
        authorRepo.deleteAutor(3L)

        //assersiones
        val author = entityManager.find(Author::class.java, 3L)
        Assertions.assertNull(author)
    }

    @Test
    fun findTestEditorial() {
        authorRepo.createAutor(Author(3L, "tafur","rodrigues"))

        val autor = authorRepo.findAutor(3L)

        Assertions.assertNotNull(autor)
        Assertions.assertEquals(3L, autor?.id)
    }

    @Test
    fun testUpdateEditorial() {
        //prerequisito
        authorRepo.createAutor(Author(3L, "tafur","rodrigues"))
        entityManager.flush()
        //ejecutando...
        val author = entityManager.find(Author::class.java, 3L)

        entityManager.clear()
        author.name = "javi"
        author.lastname="morales"

        authorRepo.updateAutor(author)

        //assersiones
        val personToAssert = entityManager.find(Author::class.java, 3L)
        Assertions.assertEquals("javi", personToAssert.name)
        Assertions.assertEquals("morales", personToAssert.lastname)

    }


}