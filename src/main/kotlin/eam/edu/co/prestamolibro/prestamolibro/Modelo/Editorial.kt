package eam.edu.co.prestamolibro.prestamolibro.Modelo

import java.io.Serializable
import javax.persistence.*

@Entity
@Table(name="Editorial")
data class Editorial(
    @Id
    @Column(name="codigo_editorial")
    val code:String,
    @Column(name="nombre_editorial")
    var name:String

):Serializable
