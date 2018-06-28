package com.lxt.chenjia.base.utils;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JSONUtils {
	private static ObjectMapper objectMapper = new ObjectMapper();

	public static String obj2Json(Object obj) {
		String jsonStr = "";
		try {
			DateUtils.dateFormat.applyPattern(DateUtils.LONG_DATE);
			objectMapper.setDateFormat(DateUtils.dateFormat);
			jsonStr = objectMapper.writeValueAsString(obj);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return jsonStr;
	}
	
	public static <T> T json2Obj(String jsonStr, Class<T> clazz) throws JsonParseException, JsonMappingException, IOException{
		T t = objectMapper.readValue(jsonStr.toString(), clazz);
		return t;
	}
	
	@SuppressWarnings("unchecked")
	public static Map<String,Object> json2Map(String json){
		Map<String, Object> map = null;
		 
		try {
			map = objectMapper.readValue(json, Map.class);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		 
		return map;
	}
	
	@SuppressWarnings("rawtypes")
	public static List json2List(String json){
		List list = null;
		
		try {
			list = objectMapper.readValue(json, List.class);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return list;
	}

	/**
	 * 对象转化成map
	 * @param obj
	 * @return
	 */
	public static Map<?, ?> obj2Map(Object obj){
		return new org.apache.commons.beanutils.BeanMap(obj); 
	}
	
}
