package com.jwtdemo.utils


import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Component
import java.time.Duration
import java.util.*

@Component
class JwtTokenUtil {
    @Value("\${jwt.secret}")
    private lateinit var secret: String
    @Value("\${jwt.lifetime}")
    private lateinit var lifetime: Duration

    fun generateToken(userDetails: UserDetails): String{
        var map = mutableMapOf<String, Any>()
        map["roles"] = userDetails.authorities.map { it.authority.toString() }

        val issuedTime = Date(System.currentTimeMillis());
        val expiredTime = Date(System.currentTimeMillis()+lifetime.toMillis())

        return Jwts.builder()
            .setClaims(map)
            .setSubject(userDetails.username)
            .setIssuedAt(issuedTime)
            .setExpiration(expiredTime)
            .signWith(Keys.hmacShaKeyFor(secret.toByteArray()), SignatureAlgorithm.HS256)
            .compact()
    }

    fun getClaims(token: String): Claims{
        return getAllClaimsOnJwtToken(token)
    }

    private fun getAllClaimsOnJwtToken(token: String): Claims{
        return Jwts.parserBuilder()
            .setSigningKey(Keys.hmacShaKeyFor(secret.toByteArray()))
            .build()
            .parseClaimsJws(token).body

    }
}