package eam.edu.co.prestamolibro.prestamolibro.repositories

import eam.edu.co.prestamolibro.prestamolibro.Modelo.*
import eam.edu.co.prestamolibro.prestamolibro.repositorio.PrestamoRepo
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.transaction.annotation.Transactional
import java.util.*
import javax.persistence.EntityManager

@SpringBootTest
@Transactional
class repositorioPrestamo {
    @Autowired
    lateinit var prestamoRepo: PrestamoRepo

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
        val usuario=Usuario("3", "claudia","rodrigues")
        entityManager.persist(usuario)
        val editorial=Editorial("3", "nachito")
        entityManager.persist(editorial)
        val libro=Libro("3","1","harryPotter",editorial)
        entityManager.persist(libro)

        //la ejecucion de la prueba.. llamar el metodo que estoy probando
        prestamoRepo.createPrestamo(Prestamo(3L,libro,usuario,fecha))

        //asersiones, o las verificaciones
        val prestamo = entityManager.find(Prestamo::class.java, 3L)
        Assertions.assertNotNull(prestamo)
        Assertions.assertEquals("3", usuario.identification)
        Assertions.assertEquals("3", editorial.code)
        Assertions.assertEquals("3",libro.code)
        Assertions.assertEquals(3L,prestamo.id)
        Assertions.assertEquals(Date(2021,2,24,),prestamo.dateTime)



    }


    @Test
    fun testDeletePrestamo(){
        //prerequisitos
        val fecha=Date(2021,2,24)
        val usuario=Usuario("3", "claudia","rodrigues")
        entityManager.persist(usuario)
        val editorial=Editorial("3", "nachito")
        entityManager.persist(editorial)
        val libro=Libro("3","1","harryPotter",editorial)
        entityManager.persist(libro)

        //la ejecucion de la prueba.. llamar el metodo que estoy probando
        prestamoRepo.createPrestamo(Prestamo(3L,libro,usuario,fecha))

        //ejecucion de la preuba
        prestamoRepo.deletePrestamo(3L)

        //assersiones
        val prestamo = entityManager.find(Prestamo::class.java, 3L)
        Assertions.assertNull(prestamo)
    }

    @Test
    fun findTestPrestamo() {
        val fecha=Date(2021,2,24)
        val usuario=Usuario("3", "claudia","rodrigues")
        entityManager.persist(usuario)
        val editorial=Editorial("3", "nachito")
        entityManager.persist(editorial)
        val libro=Libro("3","1","harryPotter",editorial)
        entityManager.persist(libro)

        //la ejecucion de la prueba.. llamar el metodo que estoy probando
        prestamoRepo.createPrestamo(Prestamo(3L,libro,usuario,fecha))

        val prestamo = prestamoRepo.findPrestamo(3L)

        Assertions.assertNotNull(prestamo)
        Assertions.assertEquals(3L, prestamo?.id)
    }

    @Test
    fun testUpdatePrestamo() {
        //prerequisito
        val fecha=Date(2021,2,24)
        val usuario=Usuario("3", "claudia","rodrigues")
        entityManager.persist(usuario)
        val editorial=Editorial("3", "nachito")
        entityManager.persist(editorial)
        val libro=Libro("3","1","harryPotter",editorial)
        entityManager.persist(libro)
        //la ejecucion de la prueba.. llamar el metodo que estoy probando
        prestamoRepo.createPrestamo(Prestamo(3L,libro,usuario,fecha))
        entityManager.flush()
        //ejecutando...
        val prestamo = entityManager.find(Prestamo::class.java, 3L)

        prestamo.dateTime=Date(2021,4,20)


        prestamoRepo.updatePrestamo(prestamo)

        //assersiones
        val personToAssert = entityManager.find(Prestamo::class.java, 3L)
        Assertions.assertEquals(Date(2021,4,20), personToAssert.dateTime)

    }
    @Test
    fun findByUsuario(){
        val usuario1=Usuario("1", "Sandra","florez")
        entityManager.persist(usuario1)
        val usuario2=Usuario("2", "melissa","forero")
        entityManager.persist(usuario2)

        val editorial1=Editorial("1","df")
        entityManager.persist(editorial1)

        val libro1=Libro("11","1a","las reliquias",editorial1)
        entityManager.persist(libro1)
        val libro2=Libro("12","2a","el prisionero",editorial1)
        entityManager.persist(libro2)
        val libro3=Libro("13","3a","el ganador",editorial1)
        entityManager.persist(libro3)

        entityManager.persist(Prestamo(1L,libro1,usuario1,Date(2000,1,8)))
        entityManager.persist(Prestamo(2L,libro2,usuario1,Date(2000,1,8)))
        entityManager.persist(Prestamo(3L,libro3,usuario2,Date(2000,1,8)))

        val listaOne=prestamoRepo.findByUsuario("1")
        val listaTwo=prestamoRepo.findByUsuario("2")
        Assertions.assertEquals(2,listaOne.size)
        Assertions.assertEquals(1,listaTwo.size)

    }
    @Test
    fun findByLibro(){
        val usuario1=Usuario("1", "Sandra","florez")
        entityManager.persist(usuario1)
        val usuario2=Usuario("2", "melissa","forero")
        entityManager.persist(usuario2)

        val editorial1=Editorial("1","df")
        entityManager.persist(editorial1)

        val libro1=Libro("11","1a","las reliquias",editorial1)
        entityManager.persist(libro1)
        val libro2=Libro("12","2a","el prisionero",editorial1)
        entityManager.persist(libro2)
        val libro3=Libro("13","3a","el ganador",editorial1)
        entityManager.persist(libro3)

        entityManager.persist(Prestamo(1L,libro1,usuario1,Date(2000,1,8)))
        entityManager.persist(Prestamo(2L,libro2,usuario1,Date(2000,1,8)))
        entityManager.persist(Prestamo(3L,libro3,usuario2,Date(2000,1,8)))
        entityManager.persist(Prestamo(4L,libro2,usuario2,Date(2000,1,8)))

        val listaOne=prestamoRepo.findBylibro("11")
        val listaTwo=prestamoRepo.findBylibro("12")
        val listaThree=prestamoRepo.findBylibro("13")
        Assertions.assertEquals(1,listaOne.size)
        Assertions.assertEquals(2,listaTwo.size)
        Assertions.assertEquals(1,listaThree.size)

    }
    @Test
    fun findByAutor(){
        val usuario1=Usuario("1", "Sandra","florez")
        entityManager.persist(usuario1)
        val usuario2=Usuario("2", "melissa","forero")
        entityManager.persist(usuario2)

        val editorial1=Editorial("1","df")
        entityManager.persist(editorial1)

        val libro1=Libro("11","1a","las reliquias",editorial1)
        entityManager.persist(libro1)
        val libro2=Libro("12","2a","el prisionero",editorial1)
        entityManager.persist(libro2)
        val libro3=Libro("13","3a","el ganador",editorial1)
        entityManager.persist(libro3)

        entityManager.persist(Prestamo(1L,libro1,usuario1,Date(2000,1,8)))
        entityManager.persist(Prestamo(2L,libro2,usuario1,Date(2000,1,8)))
        entityManager.persist(Prestamo(3L,libro3,usuario2,Date(2000,1,8)))
        entityManager.persist(Prestamo(4L,libro2,usuario2,Date(2000,1,8)))

        val listaOne=prestamoRepo.findBylibro("11")
        val listaTwo=prestamoRepo.findBylibro("12")
        val listaThree=prestamoRepo.findBylibro("13")
        Assertions.assertEquals(1,listaOne.size)
        Assertions.assertEquals(2,listaTwo.size)
        Assertions.assertEquals(1,listaThree.size)

    }
}