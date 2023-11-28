package vn.group04.animeweb.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtService {
    private static final String KEY_SECRET = "MTIzNDU2NDU5OThEMzIxM0F6eGMzNTE2NTQzMjEzMjE2NTQ5OHEzMTNhMnMxZDMyMnp4M2MyMQ==";
    private final UserService userService;
@Autowired
    public JwtService(UserService userService) {
        this.userService = userService;

    }

    public boolean isTokenValid(String token, UserDetails userDetails){
        final String userName = extractUsername(token);
        return userName.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return extractClaim(token, Claims::getExpiration).before(new Date());
    }

    public String extractUsername(String token){
        return extractClaim(token, Claims::getSubject);
    }

    private Claims extractAllClaims(String token){
        return Jwts.parser()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsTFunction){
        final Claims claims = extractAllClaims(token);
        return claimsTFunction.apply(claims);
    }

    private Key getSignInKey() {
        byte[] bytesKey = Decoders.BASE64.decode(KEY_SECRET);
        return Keys.hmacShaKeyFor(bytesKey);
    }
    public String generateToken(UserDetails userDetails){


        return generateToken(new HashMap<>(), userDetails);
    }
    public String generateToken(Map<String, Object> extraClaims, UserDetails userDetails){
        UserDetails nguoiDung = userService.loadUserByUsername(userDetails.getUsername());
        if(nguoiDung != null && !nguoiDung.getAuthorities().isEmpty()){
            List<? extends GrantedAuthority> list  = nguoiDung.getAuthorities().stream().toList();
            for(var grantedAuthority:list){
                if(grantedAuthority.getAuthority().equals("ADMIN")){
                    extraClaims.put("isAdmin", true);
                }
            }
        }
        return Jwts.builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000*60*60*24 ))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

}
