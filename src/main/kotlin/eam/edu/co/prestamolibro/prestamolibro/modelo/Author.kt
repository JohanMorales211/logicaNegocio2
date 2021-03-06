package eam.edu.co.prestamolibro.prestamolibro.modelo

import java.io.Serializable
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name="Autor")
data class Author(
    @Id
    @Column(name="codigo_autor")
    val id:Long,
    @Column(name="nombre")
    var name:String,
    @Column(name="apellido")
    var lastname:String
):Serializable
