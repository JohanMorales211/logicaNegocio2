package eam.edu.co.prestamolibro.prestamolibro.repositories

import eam.edu.co.prestamolibro.prestamolibro.modelo.User
import eam.edu.co.prestamolibro.prestamolibro.repositorio.UsuarioRepo
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.transaction.annotation.Transactional
import javax.persistence.EntityManager

@SpringBootTest
@Transactional
class repositorioUser {
    @Autowired
    lateinit var usuarioRepo: UsuarioRepo

    @Autowired
    lateinit var entityManager: EntityManager

    //requisitos para una buena prueba unitaria
    //1. la preuba debe ser repetible
    //2. las pruebas deben independientes entre si
    //3. la prueba siempre debe dar el mismo resultado ante los mismo parametros (deterministica)
    @Test
    fun testCreateUsuario() {
        //prerequisitos
        //que la persona no exista

        //la ejecucion de la prueba.. llamar el metodo que estoy probando
        usuarioRepo.createUsuario(User("3", "claudia","rodrigues"))

        //asersiones, o las verificaciones
        val user = entityManager.find(User::class.java,  "3")
        Assertions.assertNotNull(user)
        Assertions.assertEquals("claudia", user.name)
        Assertions.assertEquals("3", user.identification)
        Assertions.assertEquals("rodrigues", user.lastname)
    }


    @Test
    fun testDeleteUsuario(){
        //prerequisitos
        entityManager.persist(User("3", "claudia","rodrigues"))

        //ejecucion de la preuba
        usuarioRepo.deleteUsuario("3")

        //assersiones
        val user = entityManager.find(User::class.java, "3")
        Assertions.assertNull(user)
    }

    @Test
    fun findTestUsuario() {
        entityManager.persist(User("3", "claudia","rodrigues"))

        val usuario = usuarioRepo.findUsuario("3")

        Assertions.assertNotNull(usuario)
        Assertions.assertEquals("claudia", usuario?.name)
    }

    @Test
    fun testUpdateUsuario() {
        //prerequisito
        entityManager.persist(User("3", "claudia","rodrigues"))
        entityManager.flush()
        //ejecutando...
        val user = entityManager.find(User::class.java, "3")
        entityManager.clear()
        user.name = "tereza"
        user.lastname ="morales"

        usuarioRepo.updateUsuario(user)

        //assersiones
        val personToAssert = entityManager.find(User::class.java, "3")
        Assertions.assertEquals("tereza", personToAssert.name)
        Assertions.assertEquals("morales", personToAssert.lastname)
    }
}