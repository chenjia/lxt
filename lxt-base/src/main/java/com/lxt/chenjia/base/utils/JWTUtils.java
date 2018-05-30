package com.lxt.chenjia.base.utils;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator.Builder;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.InvalidClaimException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.Verification;

public class JWTUtils {
	private static final String SECRET = "XX#$%()(#*!()!KL<><MQLMNQNQJQK sdfkjsdrow32234545fdf>?N<:{LWPW";

	public static String sign(Map<String, String> claimMap) {
		String token = null;
		try {
			Algorithm algorithm = Algorithm.HMAC256(SECRET);
			long exp = System.currentTimeMillis() + 1000 * 60 * 30;
			Builder bulider = JWT.create().withExpiresAt(new Date(exp));
			for(Entry<String, String> entry : claimMap.entrySet()){
				bulider.withClaim(entry.getKey(), entry.getValue());
			}
			token = bulider.sign(algorithm);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return token;
	}

	public static boolean unsign(String token, Map<String, String> claimMap) {
		boolean pass = true;
		try {
			Algorithm algorithm = Algorithm.HMAC256(SECRET);
			Verification verification = JWT.require(algorithm);
			for(Entry<String, String> entry : claimMap.entrySet()){
				verification.withClaim(entry.getKey(), entry.getValue());
			}
			JWTVerifier verifier = verification.build();
			verifier.verify(token);
		} catch (IllegalArgumentException e) {
			pass = false;
		} catch (UnsupportedEncodingException e) {
			pass = false;
		} catch (InvalidClaimException e) {
			pass = false;
		} catch (TokenExpiredException e) {
			pass = false;
		}
		return pass;
	}

	public static void main(String[] args) {
		Map<String, String> claimMap = new HashMap<String, String>();
		
		String token = JWTUtils.sign(claimMap);
		boolean isPass = JWTUtils.unsign(token, claimMap);
		System.out.println(isPass);
	}
}
