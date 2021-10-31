package eam.edu.co.prestamolibro.prestamolibro.services


import eam.edu.co.prestamolibro.prestamolibro.modelo.User
import eam.edu.co.prestamolibro.prestamolibro.exceptions.BusinessException
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


    fun createUser(user: User) {
        val userById = usuarioRepo.findUsuario(user.identification?:"")

        if(userById != null){
            throw BusinessException("This User already exists")
        }
        usuarioRepo.createUsuario(user)
    }
}
