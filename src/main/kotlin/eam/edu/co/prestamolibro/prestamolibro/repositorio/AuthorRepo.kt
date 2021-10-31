package eam.edu.co.prestamolibro.prestamolibro.repositorio

import eam.edu.co.prestamolibro.prestamolibro.modelo.Author
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import javax.persistence.EntityManager

@Component //anotacion que nos dice que esta es una clase manejada por springboot
@Transactional //para que las operaciones sobre la BD funcionen.

class AuthorRepo {
    //inyeccion de depencia...... el framework se encarga de asignarle valor a la depencia
    @Autowired //esta anotacion indica que springboot se encargara de instanciar esta clase.
    lateinit var em: EntityManager //clase que nos da JPA para manipular las entidades.
    fun createAutor(author: Author){
        em.persist(author) //inserta en la tabla que define la entidad.
    }
    //? quiere decir q algo puede ser null
    fun findAutor(id:Long): Author?{
        //se el envia la clase que quiero buscar y el valor de la llave primaria que quiero buscar.
        return em.find(Author::class.java,id) //busca en la bd por llave primaria
    }
    fun updateAutor(author: Author) {
        em.merge(author) //actualizar un registro sobre la BD
    }
    fun deleteAutor(id:Long) {
        //buscan por id la entidad que quiero borrar
        val autor = findAutor(id)

        //solo puedo borrar una persona que exista...
        if (autor!=null) {
            //borra la entidad de la BD, recibe por parametro la entidad a borrrar
            em.remove(autor)
        }
    }
}