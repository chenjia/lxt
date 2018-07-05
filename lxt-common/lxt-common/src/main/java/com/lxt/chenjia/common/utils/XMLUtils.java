package com.lxt.chenjia.common.utils;

import com.thoughtworks.xstream.XStream;

public class XMLUtils {
	private static final XStream xstream = new XStream();
	
	/**
	 * 对象转化成xml字符串
	 * @param obj
	 * @return
	 */
	public static String obj2Xml(Object obj) {
		String xml = "";
		xml = xstream.toXML(obj);
		return xml;
	}

	/**
	 * xml字符串转化成对象
	 * @param str
	 * @return
	 */
	public Object xml2Obj(String str) {
		Object obj = null;
		obj = xstream.fromXML(str);
		return obj;
	}
}
