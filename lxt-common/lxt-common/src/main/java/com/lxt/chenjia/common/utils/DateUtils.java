package com.lxt.chenjia.common.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.util.StringUtils;

public class DateUtils {
	public static final String SHORT_DATE = "yyyy-MM-dd";

	public static final String LONG_DATE = "yyyy-MM-dd HH:mm:ss";

	public static SimpleDateFormat dateFormat = new SimpleDateFormat();
	
	/**
	 * 日期对象转化成字符串
	 * @param date 日期对象
	 * @param pattern 日期字符串格式
	 * @return
	 */
	public static String date2Str(Date date, String pattern) {
		String dateStr = "";
		if(StringUtils.isEmpty(pattern)){
			pattern = SHORT_DATE;
		}
		dateFormat.applyPattern(pattern);
		dateStr = dateFormat.format(date);
		return dateStr;
	}
	
	/**
	 * 日期对象转化成字符串
	 * @param date 日期对象
	 * @param pattern 日期字符串格式
	 * @return
	 */
	public static String date2Str(Date date) {
		String dateStr = null;
		dateFormat.applyPattern(SHORT_DATE);
		dateStr = dateFormat.format(date);
		return dateStr;
	}

	/**
	 * 字符串转化成日期对象
	 * @param dateStr 日期字符串
	 * @param pattern 日期格式
	 * @return
	 */
	public static Date str2Date(String dateStr, String pattern) {
		Date date = null;
		if(StringUtils.isEmpty(pattern)){
			pattern = SHORT_DATE;
		}
		dateFormat.applyPattern(pattern);
		try {
			date = dateFormat.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
	
	/**
	 * 字符串转化成日期对象
	 * @param dateStr 日期字符串
	 * @param pattern 日期格式
	 * @return
	 */
	public static Date str2Date(String dateStr) {
		Date date = null;
		dateFormat.applyPattern(SHORT_DATE);
		try {
			date = dateFormat.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
}
