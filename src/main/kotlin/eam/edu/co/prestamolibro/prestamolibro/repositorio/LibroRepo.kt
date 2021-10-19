package eam.edu.co.prestamolibro.prestamolibro.repositorio

import eam.edu.co.prestamolibro.prestamolibro.Modelo.Libro
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import javax.persistence.EntityManager
@Component //anotacion que nos dice que esta es una clase manejada por springboot
@Transactional //para que las operaciones sobre la BD funcionen.

class LibroRepo {
    //inyeccion de depencia...... el framework se encarga de asignarle valor a la depencia
    @Autowired //esta anotacion indica que springboot se encargara de instanciar esta clase.
    lateinit var em: EntityManager //clase que nos da JPA para manipular las entidades.
    fun findByEditorial(code: String): List<Libro> {
        val query = em.createQuery("SELECT boo FROM Libro boo WHERE boo.editorial.code=:codeEditorial")
        query.setParameter("codeEditorial", code)
        return query.resultList as List<Libro>
    }

    fun createLibro(libro: Libro) {
        em.persist(libro) //inserta en la tabla que define la entidad.
    }

    //? quiere decir q algo puede ser null
    fun findLirbo(code: String): Libro? {
        //se el envia la clase que quiero buscar y el valor de la llave primaria que quiero buscar.
        return em.find(Libro::class.java, code) //busca en la bd por llave primaria
    }

    fun findLirboName(name: String): Libro? {
        val query = em.createQuery("SELECT boo FROM Libro boo WHERE boo.name = :name")
        query.setParameter("name", name)

        val list = query.resultList as List<Libro>

        //asignacion condicional
        return if (list.isEmpty()) null else list[0]
    }

    fun updateLibro(libro:Libro) {
        em.merge(libro) //actualizar un registro sobre la BD
    }

    fun deleteLibro(code: String) {
        //buscan por id la entidad que quiero borrar
        val libro = findLirbo(code)

        //solo puedo borrar una persona que exista...
        if (libro!=null) {
            //borra la entidad de la BD, recibe por parametro la entidad a borrrar
            em.remove(libro)
        }
    }

}