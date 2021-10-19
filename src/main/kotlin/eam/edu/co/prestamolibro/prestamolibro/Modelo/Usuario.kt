package eam.edu.co.prestamolibro.prestamolibro.Modelo

import java.io.Serializable
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name="Usuarios")
data class Usuario(
    @Id
    @Column(name="user_identification")
    val identification:String,
    @Column(name="nombre_usuario")
    var name:String,
    @Column(name="apellido_usuario")
    var lastname:String
):Serializable
