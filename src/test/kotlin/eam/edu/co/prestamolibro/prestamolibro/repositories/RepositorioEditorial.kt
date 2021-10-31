package eam.edu.co.prestamolibro.prestamolibro.repositories

import eam.edu.co.prestamolibro.prestamolibro.modelo.Publisher
import eam.edu.co.prestamolibro.prestamolibro.repositorio.PublisherRepo
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.transaction.annotation.Transactional
import javax.persistence.EntityManager
@SpringBootTest
@Transactional
class repositorioPublisher {
    @Autowired
    lateinit var publisherRepo: PublisherRepo
    @Autowired
    lateinit var entityManager: EntityManager

    //requisitos para una buena prueba unitaria
    //1. la preuba debe ser repetible
    //2. las pruebas deben independientes entre si
    //3. la prueba siempre debe dar el mismo resultado ante los mismo parametros (deterministica)
    @Test
    fun testCreateEditorial() {
        //prerequisitos
        //que la persona no exista

        //la ejecucion de la prueba.. llamar el metodo que estoy probando
        publisherRepo.createEditorial(Publisher("3", "nachito"))

        //asersiones, o las verificaciones
        val publisher = entityManager.find(Publisher::class.java, "3")
        Assertions.assertNotNull(publisher)
        Assertions.assertEquals("3", publisher.code)
        Assertions.assertEquals("nachito", publisher.name)

    }


    @Test
    fun testDeleteEditorial(){
        //prerequisitos
        entityManager.persist(Publisher("3", "nachito"))

        //ejecucion de la preuba
        publisherRepo.deleteEditorial("3")

        //assersiones
        val publisher = entityManager.find(Publisher::class.java, "3")
        Assertions.assertNull(publisher)
    }

    @Test
    fun findTestEditorial() {
        entityManager.persist(Publisher("3", "nachito"))

        val editorial = publisherRepo.findEditorial("3")

        Assertions.assertNotNull(editorial)
        Assertions.assertEquals("3", editorial?.code)
    }

    @Test
    fun testUpdateEditorial() {
        //prerequisito
        entityManager.persist(Publisher("3", "nachito",))
        entityManager.flush()
        //ejecutando...
        val publisher = entityManager.find(Publisher::class.java, "3")

        entityManager.clear()
        publisher.name = "nachote"

        publisherRepo.updateEditorial(publisher)

        //assersiones
        val personToAssert = entityManager.find(Publisher::class.java, "3")
        Assertions.assertEquals("nachote", personToAssert.name)

    }
}