package com.revature.ecommerce.services;

import com.revature.ecommerce.exceptions.AuthenticationException;
import com.revature.ecommerce.models.User;
import com.revature.ecommerce.services.UserService;
import com.revature.ecommerce.utils.JwtConfig;
import io.jsonwebtoken.*;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpServletRequest;
import java.util.Date;

@Service
public class TokenService {
    private static JwtConfig jwtConfig = new JwtConfig();

    public TokenService() {
        super();
    }

    public static String generateToken(String username) {
        long now = System.currentTimeMillis();
        JwtBuilder tokenBuilder = Jwts.builder()
                                          .setId(username)
                                          .setIssuer("WordsAway")
                                          .setIssuedAt(new Date(now))
                                          .setExpiration(new Date(now + jwtConfig.getExpiration()))
                                          .setSubject(username)
                                          .signWith(jwtConfig.getSigAlg(), jwtConfig.getSigningKey());

        return tokenBuilder.compact();
    }

    public static User extractRequesterDetails(HttpServletRequest request){
        String token = request.getHeader("Authorization");
        if((token == null) || (token.isEmpty())){
            throw new AuthenticationException("No Authorization token provided.");
        }
        try {
            Claims claims = Jwts.parser()
                                    .setSigningKey(jwtConfig.getSigningKey())
                                    .parseClaimsJws(token)
                                    .getBody();
            return UserService.getUserByUsername(claims.getSubject());
        } catch (ExpiredJwtException | UnsupportedJwtException | MalformedJwtException | SignatureException |
                 IllegalArgumentException e) {
            throw new AuthenticationException(e.getMessage());
        }
    }
}