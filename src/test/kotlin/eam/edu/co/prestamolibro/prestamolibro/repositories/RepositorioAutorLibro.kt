package eam.edu.co.prestamolibro.prestamolibro.repositories

import eam.edu.co.prestamolibro.prestamolibro.Modelo.Autor
import eam.edu.co.prestamolibro.prestamolibro.Modelo.AutorLibro
import eam.edu.co.prestamolibro.prestamolibro.Modelo.Editorial
import eam.edu.co.prestamolibro.prestamolibro.Modelo.Libro
import eam.edu.co.prestamolibro.prestamolibro.repositorio.AutorLibroRepo
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.transaction.annotation.Transactional
import javax.persistence.EntityManager

@SpringBootTest
@Transactional
class repositorioAutorLibro {
    @Autowired
    lateinit var autorLibroRepo: AutorLibroRepo
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
        val autor=Autor(1L,"santiago","beltran")
        entityManager.persist(autor)
        val editorial=Editorial("1","Matstudios")
        entityManager.persist(editorial)
        val libro=Libro("1","2","One",editorial)
        entityManager.persist(libro)

        autorLibroRepo.createAutorLibro(AutorLibro(3L,autor,libro ))

        //asersiones, o las verificaciones
        val autorLibro = entityManager.find(AutorLibro::class.java, 3L)
        Assertions.assertNotNull(autor)
        Assertions.assertEquals(1L, autor.id)
        Assertions.assertEquals("1", libro.code)
        Assertions.assertEquals(3, autorLibro.id)

    }


    @Test
    fun testDeleteAutorLibro(){
        //prerequisitos
        val autor=Autor(1L,"santiago","beltran")
        entityManager.persist(autor)
        val editorial=Editorial("1","Matstudios")
        entityManager.persist(editorial)
        val libro=Libro("1","2","One",editorial)
        entityManager.persist(libro)

        autorLibroRepo.createAutorLibro(AutorLibro(3L,autor,libro ))

        //ejecucion de la preuba
        autorLibroRepo.deleteAutorLibro(3L)

        //assersiones
        val autorLibro = entityManager.find(AutorLibro::class.java, 3L)
        Assertions.assertNull(autorLibro)
    }

    @Test
    fun findTestAutorLibro() {
        val autor=Autor(1L,"santiago","beltran")
        entityManager.persist(autor)
        val editorial=Editorial("1","Matstudios")
        entityManager.persist(editorial)
        val libro=Libro("1","2","One",editorial)
        entityManager.persist(libro)

        autorLibroRepo.createAutorLibro(AutorLibro(3L,autor,libro ))

        val autorLibro = autorLibroRepo.findAutorLibro(3L)

        Assertions.assertNotNull(autor)
        Assertions.assertEquals(3L, autorLibro?.id)
        Assertions.assertEquals(1L, autorLibro?.autor?.id)
        Assertions.assertEquals("1", autorLibro?.libro?.code)
    }
    @Test
    fun findByAutor(){
        val autor1=Autor(1L,"santiago","beltran")
        entityManager.persist(autor1)
        val autor2=Autor(2L,"sebastian","beltran")
        entityManager.persist(autor2)

        val editorial=Editorial("1","Matstudios")
        entityManager.persist(editorial)

        val libro1=Libro("1","2","One",editorial)
        entityManager.persist(libro1)
        val libro2=Libro("2","2","Two",editorial)
        entityManager.persist(libro2)
        val libro3=Libro("3","2","Three",editorial)
        entityManager.persist(libro3)

        entityManager.persist(AutorLibro(1L,autor1,libro1))
        entityManager.persist(AutorLibro(2L,autor1,libro2))
        entityManager.persist(AutorLibro(3L,autor2,libro3))

        val listaOne=autorLibroRepo.findByAutor(1L)
        val listaTwo=autorLibroRepo.findByAutor(2L)

        Assertions.assertEquals(2,listaOne.size)
        Assertions.assertEquals(1,listaTwo.size)

    }
    @Test
    fun findByLibro(){
        val autor1=Autor(1L,"santiago","beltran")
        entityManager.persist(autor1)
        val autor2=Autor(2L,"sebastian","beltran")
        entityManager.persist(autor2)
        val autor3=Autor(3L,"javier","beltran")
        entityManager.persist(autor3)

        val editorial=Editorial("1","Matstudios")
        entityManager.persist(editorial)

        val libro1=Libro("1","2","One",editorial)
        entityManager.persist(libro1)
        val libro2=Libro("2","2","Two",editorial)
        entityManager.persist(libro2)
        val libro3=Libro("3","2","Three",editorial)
        entityManager.persist(libro3)


        entityManager.persist(AutorLibro(1L,autor1,libro1))
        entityManager.persist(AutorLibro(2L,autor2,libro2))
        entityManager.persist(AutorLibro(3L,autor3,libro3))


        val listaOne=autorLibroRepo.findBylibro("1")
        val listaTwo=autorLibroRepo.findBylibro("2")
        val listaThree=autorLibroRepo.findBylibro("3")

        Assertions.assertEquals(1,listaOne.size)
        Assertions.assertEquals(1,listaTwo.size)
        Assertions.assertEquals(1,listaThree.size)

    }

}