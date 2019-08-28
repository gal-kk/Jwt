package gk.gk.Security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Date;

@Component
public class JwtTokenUtil {
    public String genToken(String username){
        Claims claims = Jwts.claims().setSubject(username);
        claims.setExpiration(new Date(System.currentTimeMillis() + Constants.ACCESS_TOKEN_VALIDITY_SECONDS * 1000));
        claims.put("scope", Arrays.asList(new SimpleGrantedAuthority("Role_Admin")));

        return Jwts.builder().setClaims(claims)
                .signWith(SignatureAlgorithm.HS256, Constants.SIGNING_KEY).compact();

    }

    public String getUsernameFromToken(String token) {
        Claims claims = getAllClaims(token);
        return claims.getSubject();

    }

    private Claims getAllClaims(String token) {
        return Jwts.parser().setSigningKey(Constants.SIGNING_KEY)
                .parseClaimsJws(token).getBody();
    }

    public boolean tokenExpirationCheck(String token, UserDetails userDetails) {
        Date expDate = getExpDateFromToken(token);
        if (userDetails.getUsername() == getUsernameFromToken(token) && expDate.after(new Date())){
            return true;
        }
        return false;
    }

    private Date getExpDateFromToken(String token) {
        Claims claims = getAllClaims(token);
        return claims.getExpiration();
    }
}
