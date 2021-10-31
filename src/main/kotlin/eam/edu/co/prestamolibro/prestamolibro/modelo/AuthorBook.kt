package eam.edu.co.prestamolibro.prestamolibro.modelo

import javax.persistence.*

@Entity
@Table(name="Autorlibro")
data class AuthorBook(
    @Id
    @Column(name="codigo_autor_libro")
    val id:Long,
    @ManyToOne
    @JoinColumn(name =  "codigo_Autor" )
    val  author: Author,
    @ManyToOne
    @JoinColumn(name =  "codigo_Libro")
    val  book: Book
)
