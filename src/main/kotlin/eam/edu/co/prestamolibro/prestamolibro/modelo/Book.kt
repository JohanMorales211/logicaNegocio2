package eam.edu.co.prestamolibro.prestamolibro.modelo

import java.io.Serializable
import javax.persistence.*

@Entity
@Table(name="Libro")
data class Book(
    @Id
    @Column(name="codigo_autor")
    val code:String,
    @Column(name="isbn_libro")
    var isbn:String,
    @Column(name="nombre_libro")
    var name:String,
    @ManyToOne
    @JoinColumn(name ="id_editorial")
    val  publisher: Publisher
):Serializable
