package eam.edu.co.prestamolibro.prestamolibro.controllers

import com.fasterxml.jackson.databind.ObjectMapper
import eam.edu.co.prestamolibro.prestamolibro.modelo.Book
import eam.edu.co.prestamolibro.prestamolibro.modelo.Publisher
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.transaction.annotation.Transactional
import javax.persistence.EntityManager
import org.springframework.http.MediaType
import org.junit.jupiter.api.Assertions

@SpringBootTest
@Transactional
@AutoConfigureMockMvc
class BookControllerTest {

    @Autowired
    lateinit var mocMvc: MockMvc

    @Autowired
    lateinit var objectMapper: ObjectMapper

    @Autowired
    lateinit var entityManager: EntityManager

    @Test
    fun createBookHappyPathTest() {
        //prerequisitos..
        val publisher = Publisher("6", "Libros_Ferchos")
        entityManager.persist(publisher)
        val body = """
           {
            "code": "1",
            "isbn": "20",
            "name": "50 sombras de Javier",
            "publisher":{
                        "code": "6",
                        "name": "Libros Ferchos"
                }
            }
        """.trimIndent()

        val request = MockMvcRequestBuilders
            .post("/books")
            .contentType(MediaType.APPLICATION_JSON)
            .content(body)
        val response = mocMvc.perform(request)
        val resp = response.andReturn().response
        //println(resp.contentAsString)
        Assertions.assertEquals(200, resp.status)
    }
    @Test
    fun createBookNotFoundTest() {
        val publisher= Publisher("6","Libros_Ferchos")
        entityManager.persist(publisher)
        entityManager.persist((Book("60","5454654","Tio_Nacho",publisher)))
        val body = """
           {
            "code": "60",
            "isbn": "5454654",
            "name":  "Tio_Nacho",
            "editorial":{
                "code": "1",
                "name": "HotBooks"
            }
            }
        """.trimIndent()
        val request = MockMvcRequestBuilders
            .post("/books")
            .contentType(MediaType.APPLICATION_JSON)
            .content(body)
        val response = mocMvc.perform(request)
        val resp = response.andReturn().response
        //println(resp.contentAsString)
        Assertions.assertEquals(412, resp.status)
        Assertions.assertEquals("{\"message\":\"This Book already exists\",\"code\":412}".trimIndent(),
            resp.contentAsString)
    }

    @Test
    fun editBookHappyPathTest() {
        //prerequisitos..
        val publisher= Publisher("1","Nacho")
        entityManager.persist(publisher)
        entityManager.persist((Book("60","5454654","Tio_Nacho",publisher)))

        val body = """
           {
            "code": "60",
            "isbn": "5454654",
            "name":  "Tio_Nacho",
            "editorial":{
                "code": "1",
                "name": "HotBooks"
            }
            }
        """.trimIndent()

        val request = MockMvcRequestBuilders
            .put("/books/6")
            .contentType(MediaType.APPLICATION_JSON)
            .content(body)

        val response = mocMvc.perform(request)
        val resp = response.andReturn().response
        //println(resp.contentAsString)
        Assertions.assertEquals(200, resp.status)
    }
}