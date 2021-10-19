package eam.edu.co.prestamolibro.prestamolibro.servirces


import eam.edu.co.prestamolibro.prestamolibro.Modelo.Usuario
import eam.edu.co.prestamolibro.prestamolibro.exceptions.BusinessException
import eam.edu.co.prestamolibro.prestamolibro.repositorio.LibroRepo
import eam.edu.co.prestamolibro.prestamolibro.repositorio.UsuarioRepo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import javax.persistence.EntityManager
@Service
class UserService {
    @Autowired
    lateinit var usuarioRepo: UsuarioRepo
    @Autowired
    lateinit var entityManager: EntityManager


    fun createUser(usuario: Usuario) {
        val userById = usuarioRepo.findUsuario(usuario.identification?:"")

        if(userById != null){
            throw BusinessException("This User already exists")
        }
        usuarioRepo.createUsuario(usuario)
    }
}
