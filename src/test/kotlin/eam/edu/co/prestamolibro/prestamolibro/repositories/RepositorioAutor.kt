package eam.edu.co.prestamolibro.prestamolibro.repositories

import eam.edu.co.prestamolibro.prestamolibro.Modelo.Autor
import eam.edu.co.prestamolibro.prestamolibro.repositorio.AutorRepo
import eam.edu.co.prestamolibro.prestamolibro.repositorio.EditorialRepo
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.transaction.annotation.Transactional
import javax.persistence.EntityManager

@SpringBootTest
@Transactional
class repositorioAutor {
    @Autowired
    lateinit var autorRepo:AutorRepo
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
        autorRepo.createAutor(Autor(3L, "tafur","rodrigues"))

        //asersiones, o las verificaciones
        val autor = entityManager.find(Autor::class.java, 3L)
        Assertions.assertNotNull(autor)
        Assertions.assertEquals(3L, autor.id)
        Assertions.assertEquals("tafur", autor.name)
        Assertions.assertEquals("rodrigues", autor.lastname)

    }


    @Test
    fun testDeleteEditorial(){
        //prerequisitos
        autorRepo.createAutor(Autor(3L, "tafur","rodrigues"))

        //ejecucion de la preuba
        autorRepo.deleteAutor(3L)

        //assersiones
        val autor = entityManager.find(Autor::class.java, 3L)
        Assertions.assertNull(autor)
    }

    @Test
    fun findTestEditorial() {
        autorRepo.createAutor(Autor(3L, "tafur","rodrigues"))

        val autor = autorRepo.findAutor(3L)

        Assertions.assertNotNull(autor)
        Assertions.assertEquals(3L, autor?.id)
    }

    @Test
    fun testUpdateEditorial() {
        //prerequisito
        autorRepo.createAutor(Autor(3L, "tafur","rodrigues"))
        entityManager.flush()
        //ejecutando...
        val autor = entityManager.find(Autor::class.java, 3L)

        entityManager.clear()
        autor.name = "javi"
        autor.lastname="morales"

        autorRepo.updateAutor(autor)

        //assersiones
        val personToAssert = entityManager.find(Autor::class.java, 3L)
        Assertions.assertEquals("javi", personToAssert.name)
        Assertions.assertEquals("morales", personToAssert.lastname)

    }


}