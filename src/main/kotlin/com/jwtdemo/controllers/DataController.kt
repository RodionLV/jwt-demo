package com.jwtdemo.controllers

import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.security.Principal


@RestController
@RequestMapping("/data")
class DataController {

    @GetMapping("/info")
    fun getInfo(principal: Principal): String{
        return principal.toString()
    }

    @GetMapping("/user")
    fun getUser():String{
        return "<h1>you user</h1>"
    }

    @GetMapping("/admin")
    @PreAuthorize("hasAuthority('ADMIN')")
    fun getAdmin():String{
        return "<h1>you admin</h1>"
    }
}