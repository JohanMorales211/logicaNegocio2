package eam.edu.co.prestamolibro.prestamolibro.controllers

import com.fasterxml.jackson.databind.ObjectMapper
import eam.edu.co.prestamolibro.prestamolibro.modelo.User
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
class UserControllerTest {

    @Autowired
    lateinit var mocMvc: MockMvc

    @Autowired
    lateinit var objectMapper: ObjectMapper

    @Autowired
    lateinit var entityManager: EntityManager

    @Test
    fun createUserHappyPathTest() {
        val body = """
           {
            "identification": "10",
            "name": "Johan",
            "lastname": "Morales"
            }
        """.trimIndent()

        val request = MockMvcRequestBuilders
            .post("/users")
            .contentType(MediaType.APPLICATION_JSON)
            .content(body)

        val response = mocMvc.perform(request)
        val resp = response.andReturn().response
        //println(resp.contentAsString)
        Assertions.assertEquals(200, resp.status)

    }

    @Test
    fun createUserNotFoundTest() {
        entityManager.persist(User("10","Johan","Morales"))

        val body = """
           {
            "identification": "10",
            "name": "Johan",
            "lastname": "Morales"
            }
        """.trimIndent()

        val request = MockMvcRequestBuilders
            .post("/users")
            .contentType(MediaType.APPLICATION_JSON)
            .content(body)

        val response = mocMvc.perform(request)
        val resp = response.andReturn().response
        //println(resp.contentAsString)
        Assertions.assertEquals(412, resp.status)
        Assertions.assertEquals("{\"message\":\"This User already exists\",\"code\":412}".trimIndent(),
            resp.contentAsString)
    }
}