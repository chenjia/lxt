package com.lxt.chenjia.base.utils;

public class UUIDUtils {
	//生成全球唯一32位UUID
	public static String UUID(){
		String s = java.util.UUID.randomUUID().toString();
		s = s.substring(0,8)+s.substring(9,13)+s.substring(14,18)+s.substring(19,23)+s.substring(24);
		return s.toUpperCase();
	}
	
	public static void main(String[] args) {
		System.out.println(UUID());
		System.out.println(UUID());
		System.out.println(UUID());
		System.out.println(UUID());
		System.out.println(UUID());
		System.out.println(UUID());
		System.out.println(UUID());
		System.out.println(UUID());
		System.out.println(UUID());
		System.out.println(UUID());
		System.out.println(UUID());
		System.out.println(UUID());
		System.out.println(UUID());
		System.out.println(UUID());
		
	}
}
