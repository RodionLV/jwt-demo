package com.jwtdemo.config

import com.jwtdemo.exceptions.UnauthorizedException
import com.jwtdemo.utils.JwtTokenUtil
import io.jsonwebtoken.ExpiredJwtException
import io.jsonwebtoken.MalformedJwtException
import io.jsonwebtoken.security.SignatureException
import jakarta.servlet.FilterChain
import jakarta.servlet.ServletException
import jakarta.servlet.ServletRequest
import jakarta.servlet.ServletResponse
import jakarta.servlet.http.HttpServletRequest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.web.filter.GenericFilterBean
import java.io.IOException



@Component
class JwtFilter : GenericFilterBean(){

    @Autowired lateinit var jwtTokenUtil: JwtTokenUtil


    override fun doFilter(request: ServletRequest, response: ServletResponse, filterChain: FilterChain) {
        var username: String? = null
        var roles: List<String>? = null

        val jwt: String? = getToken(request as HttpServletRequest)

        if( jwt != null ){
            var map: Map<String, Any>

            try {
                map = jwtTokenUtil.getClaims(jwt)

                username = map["sub"].toString()
                roles = map["roles"] as List<String>?
            }catch(e: ExpiredJwtException){
                println("Время жизни токена истекло")
            }catch(e: SignatureException){
                println("ошибка подписи")
            }catch(e: MalformedJwtException){
                println("некоректный токен")
            }
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


    private fun getToken(request: HttpServletRequest): String?{
        val authHeader: String? = request.getHeader("authorization")

        if(authHeader != null && authHeader.startsWith("Bearer ")){
            return authHeader.substring(7)
        }

        return null
    }
}