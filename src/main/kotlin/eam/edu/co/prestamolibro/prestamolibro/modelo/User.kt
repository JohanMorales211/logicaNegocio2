package eam.edu.co.prestamolibro.prestamolibro.modelo

import java.io.Serializable
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name="Usuario")
data class User(
    @Id
    @Column(name="user_identification")
    val identification:String,
    @Column(name="nombre_usuario")
    var name:String,
    @Column(name="apellido_usuario")
    var lastname:String
):Serializable
