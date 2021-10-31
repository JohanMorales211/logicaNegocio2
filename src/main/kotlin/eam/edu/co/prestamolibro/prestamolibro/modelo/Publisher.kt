package eam.edu.co.prestamolibro.prestamolibro.modelo

import java.io.Serializable
import javax.persistence.*

@Entity
@Table(name="Editorial")
data class Publisher(
    @Id
    @Column(name="codigo_editorial")
    val code:String,
    @Column(name="nombre_editorial")
    var name:String

):Serializable
