package eam.edu.co.prestamolibro.prestamolibro.repositories

import eam.edu.co.prestamolibro.prestamolibro.Modelo.Usuario
import eam.edu.co.prestamolibro.prestamolibro.repositorio.UsuarioRepo
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.transaction.annotation.Transactional
import javax.persistence.EntityManager

@SpringBootTest
@Transactional
class repositorioUsuario {
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
        usuarioRepo.createUsuario(Usuario("3", "claudia","rodrigues"))

        //asersiones, o las verificaciones
        val usuario = entityManager.find(Usuario::class.java,  "3")
        Assertions.assertNotNull(usuario)
        Assertions.assertEquals("claudia", usuario.name)
        Assertions.assertEquals("3", usuario.identification)
        Assertions.assertEquals("rodrigues", usuario.lastname)
    }


    @Test
    fun testDeleteUsuario(){
        //prerequisitos
        entityManager.persist(Usuario("3", "claudia","rodrigues"))

        //ejecucion de la preuba
        usuarioRepo.deleteUsuario("3")

        //assersiones
        val usuario = entityManager.find(Usuario::class.java, "3")
        Assertions.assertNull(usuario)
    }

    @Test
    fun findTestUsuario() {
        entityManager.persist(Usuario("3", "claudia","rodrigues"))

        val usuario = usuarioRepo.findUsuario("3")

        Assertions.assertNotNull(usuario)
        Assertions.assertEquals("claudia", usuario?.name)
    }

    @Test
    fun testUpdateUsuario() {
        //prerequisito
        entityManager.persist(Usuario("3", "claudia","rodrigues"))
        entityManager.flush()
        //ejecutando...
        val usuario = entityManager.find(Usuario::class.java, "3")
        entityManager.clear()
        usuario.name = "tereza"
        usuario.lastname ="morales"

        usuarioRepo.updateUsuario(usuario)

        //assersiones
        val personToAssert = entityManager.find(Usuario::class.java, "3")
        Assertions.assertEquals("tereza", personToAssert.name)
        Assertions.assertEquals("morales", personToAssert.lastname)
    }
}