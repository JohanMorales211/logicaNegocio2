package eam.edu.co.prestamolibro.prestamolibro.controllers

import com.fasterxml.jackson.databind.ObjectMapper
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
class PublisherControllerTest {

    @Autowired
    lateinit var mocMvc: MockMvc

    @Autowired
    lateinit var objectMapper: ObjectMapper

    @Autowired
    lateinit var entityManager: EntityManager

    @Test
    fun createPublisherHappyPathTest() {
        val body = """
           {
            "code": "6",
            "name": "Libros_Ferchos"
            }
        """.trimIndent()

        val request = MockMvcRequestBuilders
            .post("/publishers")
            .contentType(MediaType.APPLICATION_JSON)
            .content(body)

        val response = mocMvc.perform(request)
        val resp = response.andReturn().response
        //println(resp.contentAsString)
        Assertions.assertEquals(200, resp.status)
    }
    @Test
    fun createPublisherNotFoundTest() {
        entityManager.persist(Publisher("6","Libros_Ferchos"))
        val body = """
           {
            "code": "6",
            "name": "Libros_Ferchos"
            }
        """.trimIndent()
        val request = MockMvcRequestBuilders
            .post("/publishers")
            .contentType(MediaType.APPLICATION_JSON)
            .content(body)
        val response = mocMvc.perform(request)
        val resp = response.andReturn().response
        //println(resp.contentAsString)
        Assertions.assertEquals(412, resp.status)
        Assertions.assertEquals("{\"message\":\"This Publisher already exists\",\"code\":412}".trimIndent(),
            resp.contentAsString)
    }
}