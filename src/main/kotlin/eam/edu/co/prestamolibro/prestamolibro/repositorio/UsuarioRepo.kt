package eam.edu.co.prestamolibro.prestamolibro.repositorio

import eam.edu.co.prestamolibro.prestamolibro.Modelo.Editorial
import eam.edu.co.prestamolibro.prestamolibro.Modelo.Usuario
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import javax.persistence.EntityManager

@Component //anotacion que nos dice que esta es una clase manejada por springboot
@Transactional //para que las operaciones sobre la BD funcionen.
class UsuarioRepo {
    //inyeccion de depencia...... el framework se encarga de asignarle valor a la depencia
    @Autowired //esta anotacion indica que springboot se encargara de instanciar esta clase.
    lateinit var em: EntityManager //clase que nos da JPA para manipular las entidades.
    fun createUsuario(usuario: Usuario){
        em.persist(usuario) //inserta en la tabla que define la entidad.
    }
    //? quiere decir q algo puede ser null
    fun findUsuario(identification:String): Usuario?{
        //se el envia la clase que quiero buscar y el valor de la llave primaria que quiero buscar.
        return em.find(Usuario::class.java,identification) //busca en la bd por llave primaria
    }
    fun updateUsuario(usuario: Usuario) {
        em.merge(usuario) //actualizar un registro sobre la BD
    }
    fun deleteUsuario(identification: String) {
        //buscan por id la entidad que quiero borrar
        val usuario = findUsuario(identification)

        //solo puedo borrar una persona que exista...
        if (usuario!=null) {
            //borra la entidad de la BD, recibe por parametro la entidad a borrrar
            em.remove(usuario)
        }
    }
}