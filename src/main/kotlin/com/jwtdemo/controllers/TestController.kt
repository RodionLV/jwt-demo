package com.jwtdemo.controllers

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.security.Principal


@RestController
@RequestMapping("/test")
class TestController {


    @GetMapping("/hello")
    fun getHello(): String{
        return "<h1>hello</h1>"
    }

    @GetMapping("/info")
    fun getInfo(principal: Principal): String{
        return principal.toString()
    }

    @GetMapping("/admin")
    fun getAdmin():String{
        return "admin"
    }


}