package eam.edu.co.prestamolibro.prestamolibro.repositorio

import eam.edu.co.prestamolibro.prestamolibro.Modelo.Autor
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import javax.persistence.EntityManager

@Component //anotacion que nos dice que esta es una clase manejada por springboot
@Transactional //para que las operaciones sobre la BD funcionen.

class AutorRepo {
    //inyeccion de depencia...... el framework se encarga de asignarle valor a la depencia
    @Autowired //esta anotacion indica que springboot se encargara de instanciar esta clase.
    lateinit var em: EntityManager //clase que nos da JPA para manipular las entidades.
    fun createAutor(autor: Autor){
        em.persist(autor) //inserta en la tabla que define la entidad.
    }
    //? quiere decir q algo puede ser null
    fun findAutor(id:Long): Autor?{
        //se el envia la clase que quiero buscar y el valor de la llave primaria que quiero buscar.
        return em.find(Autor::class.java,id) //busca en la bd por llave primaria
    }
    fun updateAutor(autor: Autor) {
        em.merge(autor) //actualizar un registro sobre la BD
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