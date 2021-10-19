package eam.edu.co.prestamolibro.prestamolibro.repositorio

import eam.edu.co.prestamolibro.prestamolibro.Modelo.Libro
import eam.edu.co.prestamolibro.prestamolibro.Modelo.Prestamo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import javax.persistence.EntityManager
@Component //anotacion que nos dice que esta es una clase manejada por springboot
@Transactional //para que las operaciones sobre la BD funcionen.


class PrestamoRepo {
    @Autowired //esta anotacion indica que springboot se encargara de instanciar esta clase.
    lateinit var em: EntityManager //clase que nos da JPA para manipular las entidades.
    fun findByUsuario(id:String):List<Prestamo>{
        val query=em.createQuery("SELECT prest FROM Prestamo prest WHERE prest.usuario.id=:codeUsuario")
        query.setParameter("codeUsuario",id)
        return query.resultList as List<Prestamo>
    }
    fun findBylibro(code:String):List<Prestamo>{
        val query=em.createQuery("SELECT prest FROM Prestamo prest WHERE prest.libro.code=:codeLibro")
        query.setParameter("codeLibro",code)
        return query.resultList as List<Prestamo>
    }

    fun createPrestamo(prestamo: Prestamo){
        em.persist(prestamo) //inserta en la tabla que define la entidad.
    }

    //? quiere decir q algo puede ser null
    fun findPrestamo(id:Long): Prestamo?{
        //se el envia la clase que quiero buscar y el valor de la llave primaria que quiero buscar.
        return em.find(Prestamo::class.java,id) //busca en la bd por llave primaria
    }

    fun updatePrestamo(prestamo: Prestamo) {
        em.merge(prestamo) //actualizar un registro sobre la BD
    }

    fun deletePrestamo(id: Long) {
        //buscan por id la entidad que quiero borrar
        val prestamo = findPrestamo(id)

        //solo puedo borrar una persona que exista...
        if (prestamo!=null) {
            //borra la entidad de la BD, recibe por parametro la entidad a borrrar
            em.remove(prestamo)
        }
    }
}