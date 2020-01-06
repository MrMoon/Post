package com.moon.squad.configuration.security.jwt;

import com.moon.squad.configuration.security.exception.JwtTokenMalformedException;
import com.moon.squad.model.user.Role;
import com.moon.squad.service.user.implementation.UserServiceImp;

import org.jetbrains.annotations.NotNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Base64;
import java.util.Date;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

import static com.moon.squad.configuration.ConfigurationConstants.HEADER_AUTHORIZATION;
import static com.moon.squad.configuration.ConfigurationConstants.INVALID_DATE;
import static com.moon.squad.configuration.ConfigurationConstants.INVALID_EMPTY;
import static com.moon.squad.configuration.ConfigurationConstants.INVALID_SIGNATURE;
import static com.moon.squad.configuration.ConfigurationConstants.INVALID_TOKEN;
import static com.moon.squad.configuration.ConfigurationConstants.INVALID_UNSUPPORTED;
import static com.moon.squad.configuration.ConfigurationConstants.ROLES;
import static com.moon.squad.configuration.ConfigurationConstants.SECRET_KEY;
import static com.moon.squad.configuration.ConfigurationConstants.TOKEN_PREFIX;
import static com.moon.squad.configuration.ConfigurationConstants.VALIDITY_IN_MILLESECONDS;
import static io.jsonwebtoken.SignatureAlgorithm.HS512;

@Component
public class JwtUtil implements Serializable {

    private static final long serialVersionUID = -2550185165626007488L;
    private final UserServiceImp userService;
    private String key = SECRET_KEY;

    public JwtUtil(UserServiceImp userService) {
        this.userService = userService;
    }

    @PostConstruct
    protected void init() {
        key = Base64.getEncoder().encodeToString(key.getBytes());
    }

    public String getEmailFromToken(@NotNull String token) {
        return getClaimsFromToken(token, Claims::getSubject);
    }

    public Date getExpirationDateFromToken(@NotNull String token) {
        return getClaimsFromToken(token, Claims::getExpiration);
    }

    private <T> T getClaimsFromToken(@NotNull String token, @NotNull Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    private <T> Claims getAllClaimsFromToken(@NotNull String token) {
        return Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody();
    }

    @NotNull
    private Boolean isTokenExpired(@NotNull String token) {
        return getExpirationDateFromToken(token).before(new Date());
    }

    public String createToken(String email, Set<Role> roles) {
        Claims claims = Jwts.claims().setSubject(email);
        claims.put(ROLES, roles);
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + VALIDITY_IN_MILLESECONDS))
                .signWith(HS512, key)
                .compact();
    }

    public Boolean validateToken(@NotNull String token) {
        try {
            Jwts.parser().setSigningKey(key).parseClaimsJws(token);
        } catch (SignatureException e) {
            throw new JwtTokenMalformedException(INVALID_SIGNATURE);
        } catch (MalformedJwtException e) {
            throw new JwtTokenMalformedException(INVALID_TOKEN);
        } catch (ExpiredJwtException e) {
            throw new JwtTokenMalformedException(INVALID_DATE);
        } catch (UnsupportedJwtException e) {
            throw new JwtTokenMalformedException(INVALID_UNSUPPORTED);
        } catch (IllegalArgumentException e) {
            throw new JwtTokenMalformedException(INVALID_EMPTY);
        }
        return (!isTokenExpired(token));
    }

    public Authentication getAuthentication(@NotNull String token) {
        UserDetails userDetails = userService.loadUserByUsername(getEmailFromToken(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    public String resolveToken(@NotNull HttpServletRequest httpServletRequest) {
        Optional<String> bearerToken = Optional.ofNullable(httpServletRequest.getHeader(HEADER_AUTHORIZATION));
        return (bearerToken.isPresent() && bearerToken.get().startsWith(TOKEN_PREFIX)) ? bearerToken.get().substring(TOKEN_PREFIX.length()) : null;
    }
}
