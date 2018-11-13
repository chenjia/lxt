package com.lxt.ms.common.utils;

import io.jsonwebtoken.*;
import io.jsonwebtoken.impl.crypto.MacProvider;
import org.apache.tomcat.util.codec.binary.Base64;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JWTUtils {
	// 版本
	public static String TOKEN_VERSION = "1";
	// 设置发行人
	public static String ISSUER = "lxt";
	// 设置抽象主题
	public static String SUBJECT = "token";
	// HS256 私钥
	public static String HS256KEY = "eoRK6DnPRTbQ72oEG+ANFg==";
	public static SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
	public static Key signingKey = new SecretKeySpec(Base64.decodeBase64(HS256KEY), signatureAlgorithm.getJcaName());

	public static String build(Map<String, Object> claims) {
		long nowMillis = System.currentTimeMillis();
		claims.put(Claims.ID, TOKEN_VERSION);
		claims.put(Claims.ISSUER, ISSUER);
		claims.put(Claims.SUBJECT, SUBJECT);
		claims.put(Claims.AUDIENCE, claims.get("userId"));
		claims.put(Claims.EXPIRATION, new Date(nowMillis + (30 * 60 * 1000)));
		claims.put(Claims.ISSUED_AT, new Date(nowMillis));
		JwtBuilder jwtBuilder = Jwts.builder().setClaims(claims);
		jwtBuilder.signWith(signatureAlgorithm, signingKey);
		return jwtBuilder.compact();
	}

	public static boolean isValid(String token) {
		try {
			Jws<Claims> jwsClaims = Jwts.parser().setSigningKey(signingKey).parseClaimsJws(token.trim());
			Long exp = (Long) jwsClaims.getBody().get(Claims.EXPIRATION);
			return exp - System.currentTimeMillis() > 0;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public static Map<String, Object> parse(String token) {
		Claims claims = Jwts.parser().setSigningKey(signingKey).parseClaimsJws(token.trim()).getBody();
		return claims;
	}

//	public static void main(String[] args) {
//		Map<String, Object> claims = new HashMap<String, Object>();
//		claims.put("userId", "admin");
//		String token = build(claims);
//		Map<String, Object> map = parse(token);
//		System.out.println(map.get("userId"));
//		System.out.println(token);
//	}
}
