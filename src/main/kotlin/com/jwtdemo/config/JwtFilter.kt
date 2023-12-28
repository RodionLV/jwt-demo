package com.jwtdemo.config

import com.jwtdemo.utils.JwtTokenUtil
import jakarta.servlet.FilterChain
import jakarta.servlet.ServletException
import jakarta.servlet.ServletRequest
import jakarta.servlet.ServletResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.web.filter.GenericFilterBean
import java.io.IOException
import java.util.StringJoiner


@Component

class JwtFilter : GenericFilterBean(){

    @Autowired lateinit var jwtTokenUtil: JwtTokenUtil

    @Throws(ServletException::class, IOException::class)
    override fun doFilter(request: ServletRequest, response: ServletResponse, filterChain: FilterChain) {
        val authHeader: String? = request.getParameter("Authorization")
        var username: String? = null
        var roles: List<String>? = null
        var jwt: String? = null

        if( authHeader != null && authHeader.startsWith("Bearer ")){
            jwt = authHeader.substring(7)

            var map: Map<String, Any> = jwtTokenUtil.getClaims(jwt)

            username = map["sub"].toString()
            roles = map["roles"] as List<String>?

            println(roles)
        }


        if( username != null && roles != null ){
            val token: UsernamePasswordAuthenticationToken = UsernamePasswordAuthenticationToken(
                username,
                null,
                roles?.map { SimpleGrantedAuthority(it) }
            )

            SecurityContextHolder.getContext().authentication = token
        }

        filterChain.doFilter(request, response)
    }
}