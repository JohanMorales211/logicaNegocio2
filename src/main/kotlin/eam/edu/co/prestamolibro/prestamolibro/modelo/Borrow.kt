package eam.edu.co.prestamolibro.prestamolibro.modelo

import java.io.Serializable
import java.util.*
import javax.persistence.*

@Entity
@Table(name="Prestamo")
data class Borrow(
    @Id
    @Column(name="id")
    val id:Long,
    @ManyToOne
    @JoinColumn(name="id_libro")
    val book: Book,
    @ManyToOne
    @JoinColumn(name="id_usuario")
    val user: User,
    @Column(name="fecha_prestamo")
    var dateTime:Date
):Serializable
