package com.chrisbees.e_commerce.security;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;

@Service
public class JwtService {

    private static final String TOKEN_TYPE = "token_type";
    private final PrivateKey privateKey;
    private final PublicKey publicKey;
    @Value("${app.security.jwt.access-token-expiration}")
    private long accessTokenExpiration;
    @Value("${app.security.jwt.refresh-token-expiration}")
    private long refreshTokenExpiration;

    public JwtService() throws Exception {
        this.privateKey = KeyUtils.loadPrivateKey("keys/local-only/private_key.pem");
        this.publicKey = KeyUtils.loadPublicKey("keys/local-only/public_key.pem");
    }

    public String generateAccessToken(final String username) {
        final Map<String, Object> claims = Map.of(TOKEN_TYPE, "ACCESS_TOKEN");
        return buildToken(username, claims, this.accessTokenExpiration);
    }

    public String generateRefreshToken(final String username) {
        final Map<String, Object> claims = Map.of(TOKEN_TYPE, "REFRESH_TOKEN");
        return buildToken(username, claims, this.refreshTokenExpiration);
    }

    private String buildToken(final String username, final Map<String, Object> claims, final long expiration) {
        return Jwts.builder()
                .claims(claims)
                .subject(username)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(this.privateKey)
                .compact();
    }

    // public boolean isTokenValid(final String token, final String expectedUsername) {
    //     final String username = extractUsername(token);
    //     return username.equals(expectedUsername) && !isTokenExpired(token);
    // }

    public boolean isTokenValid(
        final String token,
        final String expectedUsername) {

    final Claims claims = extractClaims(token);

    return expectedUsername.equals(claims.getSubject())
            && "ACCESS_TOKEN".equals(claims.get(TOKEN_TYPE))
            && claims.getExpiration() != null
            && claims.getExpiration().after(new Date());
}

    private boolean isTokenExpired(final String token) {
        return extractClaims(token).getExpiration()
                .before(new Date());
    }

    public String extractUsername(final String token) {
        return extractClaims(token).getSubject();
    }

    // private Claims extractClaims(final String token) {
    //     try {
    //         return Jwts.parser()
    //                 .verifyWith(this.publicKey)
    //                 .build()
    //                 .parseSignedClaims(token)
    //                 .getPayload();
    //     } catch (final JwtException e) {
    //         throw new RuntimeException("Invalid Jwt Token", e);
    //     }
    // }

    private Claims extractClaims(final String token) {
    return Jwts.parser()
            .verifyWith(this.publicKey)
            .build()
            .parseSignedClaims(token)
            .getPayload();
}

    // public String refreshAccessToken(final String refreshToken) {
    //     final Claims claims = extractClaims(refreshToken);
    //     if (!"REFRESH_TOKEN".equals(claims.get(TOKEN_TYPE))) {
    //         throw new RuntimeException("Invalid Token Type");
    //     }
    //     if (isTokenExpired(refreshToken)) {
    //         throw new RuntimeException("Refresh Token Expired");
    //     }
    //     final String username = claims.getSubject();
    //     return generateAccessToken(username);

    // }

    public String refreshAccessToken(final String refreshToken) {

    final Claims claims = extractClaims(refreshToken);

    if (!"REFRESH_TOKEN".equals(claims.get(TOKEN_TYPE))) {
        throw new IllegalArgumentException("Invalid token type");
    }

    final String username = claims.getSubject();

    if (username == null || username.isBlank()) {
        throw new IllegalArgumentException("Invalid token subject");
    }

    return generateAccessToken(username);
}

}
