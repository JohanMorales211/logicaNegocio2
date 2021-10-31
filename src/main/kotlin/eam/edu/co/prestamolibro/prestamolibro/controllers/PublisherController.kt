package eam.edu.co.prestamolibro.prestamolibro.controllers

import eam.edu.co.prestamolibro.prestamolibro.modelo.Publisher
import eam.edu.co.prestamolibro.prestamolibro.services.PublisherServices
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/publishers")
class PublisherController {
    @Autowired
    lateinit var publisherServices: PublisherServices

    @PostMapping
    fun createPublisher(@Validated @RequestBody publisher: Publisher){
        publisherServices.createPublisher(publisher)
    }
}