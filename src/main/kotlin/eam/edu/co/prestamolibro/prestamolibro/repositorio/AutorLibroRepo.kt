package eam.edu.co.prestamolibro.prestamolibro.repositorio

import eam.edu.co.prestamolibro.prestamolibro.Modelo.Autor
import eam.edu.co.prestamolibro.prestamolibro.Modelo.AutorLibro
import eam.edu.co.prestamolibro.prestamolibro.Modelo.Prestamo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import javax.persistence.EntityManager

@Component //anotacion que nos dice que esta es una clase manejada por springboot
@Transactional //para que las operaciones sobre la BD funcionen.

class AutorLibroRepo {
    @Autowired //esta anotacion indica que springboot se encargara de instanciar esta clase.
    lateinit var em: EntityManager //clase que nos da JPA para manipular las entidades.

    fun findByAutor(codigoAutor:Long):List<AutorLibro>{
        val query=em.createQuery("SELECT auLi FROM AutorLibro auLi WHERE auLi.autor.id=:codeAut")
        query.setParameter("codeAut",codigoAutor)
        return query.resultList as List<AutorLibro>
    }
    fun findBylibro(code:String):List<AutorLibro>{
        val query=em.createQuery("SELECT auLi FROM AutorLibro auLi WHERE auLi.libro.code=:codeLibro")
        query.setParameter("codeLibro",code)
        return query.resultList as List<AutorLibro>
    }
    fun createAutorLibro(autorLibro: AutorLibro){
        em.persist(autorLibro) //inserta en la tabla que define la entidad.
    }
    //? quiere decir q algo puede ser null
    fun findAutorLibro(codigoAutorLibro:Long): AutorLibro?{
        //se el envia la clase que quiero buscar y el valor de la llave primaria que quiero buscar.
        return em.find(AutorLibro::class.java,codigoAutorLibro) //busca en la bd por llave primaria
    }
    fun updateAutorLibro(autorLibro: AutorLibro) {
        em.merge(autorLibro) //actualizar un registro sobre la BD
    }
    fun deleteAutorLibro(codigoAutorLibro: Long) {
        //buscan por id la entidad que quiero borrar
        val autorLibro = findAutorLibro(codigoAutorLibro)

        //solo puedo borrar una persona que exista...
        if (autorLibro!=null) {
            //borra la entidad de la BD, recibe por parametro la entidad a borrrar
            em.remove(autorLibro)
        }
    }
}



