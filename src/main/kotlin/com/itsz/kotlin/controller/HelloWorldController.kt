package com.itsz.kotlin.controller

import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/hello")
class HelloWorldController(
    val helloWorldService: HelloWorldService
) {
    @GetMapping
    fun hello(): String = helloWorldService.hello()
}

@Service
class HelloWorldService{
    fun hello() = "hello world"
}