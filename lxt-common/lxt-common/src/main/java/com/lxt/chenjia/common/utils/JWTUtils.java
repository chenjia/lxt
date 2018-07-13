package com.lxt.chenjia.common.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.impl.crypto.MacProvider;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JWTUtils {
	private static final Key key = MacProvider.generateKey();

	public static String build(Map<String, Object> claims) {
		String compactJws = Jwts.builder()
				.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 30))
				.addClaims(claims)
				.signWith(SignatureAlgorithm.HS512, key).compact();
		return compactJws;
	}

	public static Map<String, Object> parse(String compactJws) {	
		Claims claims = null;
		try {
			claims = Jwts.parser().setSigningKey(key)
					.parseClaimsJws(compactJws).getBody();
		} catch (SignatureException e) {
			e.printStackTrace();
		}

		return claims;
	}
	
	public static void main(String[] args) {
		Map<String, Object> claims = new HashMap<String, Object>();
		claims.put("name", "chenjia");
		String token = build(claims);
		Map<String, Object> map = parse(token);
		System.out.println(map.get("name"));
	}
}
