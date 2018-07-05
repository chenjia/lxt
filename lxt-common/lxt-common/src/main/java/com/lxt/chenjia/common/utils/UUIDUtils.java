package com.lxt.chenjia.common.utils;

public class UUIDUtils {
	public static String UUID(){
		String s = java.util.UUID.randomUUID().toString();
		s = s.substring(0,8)+s.substring(9,13)+s.substring(14,18)+s.substring(19,23)+s.substring(24);
		return s.toUpperCase();
	}
}
