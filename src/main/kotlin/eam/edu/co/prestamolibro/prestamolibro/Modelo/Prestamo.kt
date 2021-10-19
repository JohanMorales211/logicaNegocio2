package eam.edu.co.prestamolibro.prestamolibro.Modelo

import java.io.Serializable
import java.util.*
import javax.persistence.*

@Entity
@Table(name="Prestamo")
data class Prestamo(
    @Id
    @Column(name="id")
    val id:Long,
    @ManyToOne
    @JoinColumn(name="id_libro")
    val libro:Libro,
    @ManyToOne
    @JoinColumn(name="id_usuario")
    val usuario:Usuario,
    @Column(name="fecha_prestamo")
    var dateTime:Date
):Serializable
