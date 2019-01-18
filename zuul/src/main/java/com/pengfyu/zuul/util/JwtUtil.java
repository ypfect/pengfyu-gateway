package com.pengfyu.zuul.util;

import com.pengfyu.zuul.constant.AuthConstant;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

public class JwtUtil implements Serializable {

    private static final long serialVersionUID = -3301605591108950415L;
    private static final String CLAIM_KEY_USERNAME = "sub";
    private static final String CLAIM_KEY_CREATED = "created";
    private static final String REFRESH_TOKEN = "refresh_token";
    private static final String EXPIRE_TOKEN = "expire_token";
    private static final String ILLEGAL = "illegal";
    private static final String PASS = "pass";
    private static final String PC = "1";
    private static final String CLAIM_KEY_ID = "jti";
    private static final String USER_PLATFORM = "platform";
    private static final String USER_ROLES = "roles";
    @Value("${jwt.secret}")
    private static String secret;

    @Value("${jwt.expiration}")
    private static Long expiration;


    public static String checkToken(String token) {
        try {
            final Claims claims = getClaimsFromToken(token);
            Date created = getCreatedDateFromToken(token);
            String src = claims.getAudience();
            if (StringUtils.isBlank(src) || created == null) {
                return ILLEGAL;
            }
            if (!src.equals(PC)) {
                int days = getDays(new Date(), created);
                if (days > 15) {
                    return EXPIRE_TOKEN;
                }
            }
            if (src.equals(PC)) {
                int mins = getMins(new Date(), created);
                if (mins > 60 && mins < 120) {
                    return REFRESH_TOKEN;
                }
                if (mins >=120) {
                    return EXPIRE_TOKEN;
                }
            }
            String id = claims.getId();
            if (StringUtils.isBlank(id)) {
                return ILLEGAL;
            }
            return PASS;
        } catch (Exception e) {
            return ILLEGAL;
        }
    }



    private static Claims getClaimsFromToken(String token) {
        Claims claims;
        try {
            claims = Jwts.parser()
                    .setSigningKey(AuthConstant.SECRET)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            claims = null;
        }
        return claims;
    }

    private static Date getCreatedDateFromToken(String token) {
        Date created;
        try {
            final Claims claims = getClaimsFromToken(token);
            created = new Date((Long) claims.get(CLAIM_KEY_CREATED));
        } catch (Exception e) {
            created = null;
        }
        return created;
    }

    private static int getDays(Date start, Date end) {
        return (int) ((start.getTime() - end.getTime()) / 1000 / 60 / 60 / 24);
    }

    private static int getMins(Date start, Date end) {
        return (int) ((start.getTime() - end.getTime()) / 1000 / 60);
    }

    public static String refreshToken(String token) {
        String refreshedToken;
        try {
            final Claims claims = getClaimsFromToken(token);
            claims.put(CLAIM_KEY_CREATED, new Date());
            refreshedToken = generateToken(claims);
        } catch (Exception e) {
            refreshedToken = null;
        }
        return refreshedToken;
    }

    static String generateToken(Map<String, Object> claims) {
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(generateExpirationDate())
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }
    private static Date generateExpirationDate() {
        return new Date(System.currentTimeMillis() + expiration * 1000);
    }

    public static Long getUserIdFromToken(String token) {
        Long id;
        try {
            final Claims claims = getClaimsFromToken(token);
            id = Long.valueOf(claims.get(CLAIM_KEY_ID).toString()) ;
        } catch (Exception e) {
            id = null;
        }
        return id;
    }

    public static String getPlatformFromToken(String token) {
        String platform;
        try {
            final Claims claims = getClaimsFromToken(token);
             platform = (String) claims.get(USER_PLATFORM);
        }catch (Exception e){
            platform = null;
        }
        return platform;
    }
    public static String getUserUsernameFromToken(String token) {
        String username;
        try {
            final Claims claims = getClaimsFromToken(token);

            username = claims.get(CLAIM_KEY_USERNAME).toString();
        } catch (Exception e) {
            username = null;
        }
        return username;
    }

    public static String getRolesFromToken(String token) {
        String roles;
        try {
            final Claims claims = getClaimsFromToken(token);
            roles = (String) claims.get(USER_ROLES);
        }catch (Exception e){
            roles = null;
        }
        return roles;
    }

}

