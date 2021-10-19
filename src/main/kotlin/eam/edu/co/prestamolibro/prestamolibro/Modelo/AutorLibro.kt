package eam.edu.co.prestamolibro.prestamolibro.Modelo

import javax.persistence.*

@Entity
@Table(name="Autorlibro")
data class AutorLibro(
    @Id
    @Column(name="codigo_autor_libro")
    val id:Long,
    @ManyToOne
    @JoinColumn(name =  "codigo_Autor" )
    val  autor: Autor,
    @ManyToOne
    @JoinColumn(name =  "codigo_Libro")
    val  libro:Libro
)
