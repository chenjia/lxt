package com.lxt.chenjia.base.utils;

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
	private static final String SECRET = "XX#$%()(#*!()!KL<><MQLMNQNQJQK sdfkjsdrow32234545fdf>?N<:{LWPW";
	private static final Key key = MacProvider.generateKey();

//	public static String sign(Map<String, String> claimMap) {
//		String token = null;
//		try {
//			Algorithm algorithm = Algorithm.HMAC256(SECRET);
//			long exp = System.currentTimeMillis() + 1000 * 60 * 30;
//			Builder bulider = JWT.create().withExpiresAt(new Date(exp));
//			for (Entry<String, String> entry : claimMap.entrySet()) {
//				bulider.withClaim(entry.getKey(), entry.getValue());
//			}
//			token = bulider.sign(algorithm);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return token;
//	}
//
//	public static boolean unsign(String token, Map<String, String> claimMap) {
//		boolean pass = true;
//		try {
//			Algorithm algorithm = Algorithm.HMAC256(SECRET);
//			Verification verification = JWT.require(algorithm);
//			for (Entry<String, String> entry : claimMap.entrySet()) {
//				verification.withClaim(entry.getKey(), entry.getValue());
//			}
//			JWTVerifier verifier = verification.build();
//			verifier.verify(token);
//		} catch (IllegalArgumentException e) {
//			pass = false;
//		} catch (UnsupportedEncodingException e) {
//			pass = false;
//		} catch (InvalidClaimException e) {
//			pass = false;
//		} catch (TokenExpiredException e) {
//			pass = false;
//		}
//		return pass;
//	}

	public static String build(Map<String, Object> claims) {
		String compactJws = Jwts.builder().setSubject("Joe")
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
		long begin = System.currentTimeMillis();
		
//		for (int i = 0; i < 1000; i++) {
//			Map<String, String> claimMap = new HashMap<String, String>();
//			claimMap.put("name", "chenjia");
//			String token = JWTUtils.sign(claimMap);
//			boolean isPass = JWTUtils.unsign(token, claimMap);
//		}

		
//		for (int i = 0; i < 1000; i++) {
			Map<String, Object> claims = new HashMap<String, Object>();
			claims.put("name", "chenjia");
			String token = build(claims);
			claims = parse(token);
			System.out.println(claims.get("name"));
//		}
		
		long end = System.currentTimeMillis();
		System.out.println(end-begin);
	}
}
