package eam.edu.co.prestamolibro.prestamolibro.repositories

import eam.edu.co.prestamolibro.prestamolibro.Modelo.Editorial
import eam.edu.co.prestamolibro.prestamolibro.repositorio.EditorialRepo
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.transaction.annotation.Transactional
import javax.persistence.EntityManager
@SpringBootTest
@Transactional
class repositorioEditorial {
    @Autowired
    lateinit var editorialRepo: EditorialRepo
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
        editorialRepo.createEditorial(Editorial("3", "nachito"))

        //asersiones, o las verificaciones
        val editorial = entityManager.find(Editorial::class.java, "3")
        Assertions.assertNotNull(editorial)
        Assertions.assertEquals("3", editorial.code)
        Assertions.assertEquals("nachito", editorial.name)

    }


    @Test
    fun testDeleteEditorial(){
        //prerequisitos
        entityManager.persist(Editorial("3", "nachito"))

        //ejecucion de la preuba
        editorialRepo.deleteEditorial("3")

        //assersiones
        val editorial = entityManager.find(Editorial::class.java, "3")
        Assertions.assertNull(editorial)
    }

    @Test
    fun findTestEditorial() {
        entityManager.persist(Editorial("3", "nachito"))

        val editorial = editorialRepo.findEditorial("3")

        Assertions.assertNotNull(editorial)
        Assertions.assertEquals("3", editorial?.code)
    }

    @Test
    fun testUpdateEditorial() {
        //prerequisito
        entityManager.persist(Editorial("3", "nachito",))
        entityManager.flush()
        //ejecutando...
        val editorial = entityManager.find(Editorial::class.java, "3")

        entityManager.clear()
        editorial.name = "nachote"

        editorialRepo.updateEditorial(editorial)

        //assersiones
        val personToAssert = entityManager.find(Editorial::class.java, "3")
        Assertions.assertEquals("nachote", personToAssert.name)

    }
}