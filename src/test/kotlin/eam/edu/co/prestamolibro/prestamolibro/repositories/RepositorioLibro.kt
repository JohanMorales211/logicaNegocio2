package eam.edu.co.prestamolibro.prestamolibro.repositories

import eam.edu.co.prestamolibro.prestamolibro.Modelo.Editorial
import eam.edu.co.prestamolibro.prestamolibro.Modelo.Libro
import eam.edu.co.prestamolibro.prestamolibro.repositorio.LibroRepo
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.transaction.annotation.Transactional
import javax.persistence.EntityManager



@SpringBootTest
@Transactional

class repositorioLibro {

    @Autowired
    lateinit var libroRepo: LibroRepo

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
        val editorial= Editorial("1","nacho")
        entityManager.persist(editorial)
        libroRepo.createLibro(Libro("3","1","harryPotter", editorial))
        //asersiones, o las verificaciones
        val libro = entityManager.find(Libro::class.java,  "3")
        Assertions.assertNotNull(libro)
        Assertions.assertEquals("3", libro.code)
        Assertions.assertEquals("1", libro.isbn)
        Assertions.assertEquals("harryPotter", libro.name)
        Assertions.assertEquals("1", editorial.code)
    }


    @Test
    fun testDeleteLibro(){
        //prerequisitos
        val editorial= Editorial("1","nacho")
        entityManager.persist(editorial)
        libroRepo.createLibro(Libro("3","1","harryPotter", editorial))

        //ejecucion de la preuba
        libroRepo.deleteLibro("3")

        //assersiones
        val libro = entityManager.find(Libro::class.java, "3")
        Assertions.assertNull(libro)
    }

    @Test
    fun findTestLibro() {
        val editorial= Editorial("1","nacho")
        entityManager.persist(editorial)
        libroRepo.createLibro(Libro("3","1","harryPotter", editorial))

        val libro = libroRepo.findLirbo("3")

        Assertions.assertNotNull(libro)
        Assertions.assertEquals("harryPotter", libro?.name)
        Assertions.assertEquals("1", libro?.isbn)
        Assertions.assertEquals("nacho", libro?.editorial?.name)
        Assertions.assertEquals("1",libro?.editorial?.code)
    }

    @Test
    fun testUpdateLibro() {
        //prerequisito
        val editorial= Editorial("1","nacho")
        entityManager.persist(editorial)
        libroRepo.createLibro(Libro("3","1","harryPotter", editorial))
        entityManager.flush()
        //ejecutando...
        val libro = entityManager.find(Libro::class.java, "3")
        entityManager.clear()
        libro.name = "tontin"
        libro.isbn ="2"
        editorial.name="nachito"

        libroRepo.updateLibro(libro)

        //assersiones
        val personToAssert = entityManager.find(Libro::class.java, "3")
        Assertions.assertEquals("tontin",libro.name)
        Assertions.assertEquals("2", libro.isbn)
        Assertions.assertEquals("nachito", editorial.name)

    }
    @Test
    fun finByPublisherTest(){
        val editorial1=Editorial("1","df")
        entityManager.persist(editorial1)
        val editorial2=Editorial("2","dg")
        entityManager.persist(editorial2)
        entityManager.persist(Libro("11","1a","las reliquias",editorial1))
        entityManager.persist(Libro("12","2a","El prisionero",editorial1))
        entityManager.persist(Libro("13","3a","la camara",editorial2))

        val listaOne=libroRepo.findByEditorial("1")
        val listaTwo=libroRepo.findByEditorial("2")
        Assertions.assertEquals(2,listaOne.size)
        Assertions.assertEquals(1,listaTwo.size)

    }
}