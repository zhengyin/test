package com.izhengyin.test.hadoop.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUnits {
	private static final String FORMAT_DATE = "yyyy-MM-dd";
	private static final String FORMAT_TIME = "HH:mm:ss";
	private static final String FORMAT_DATETIME= "yyyy-MM-dd HH:mm:ss";
	private static final String TIME_ZONE = "Asia/Shanghai";
	public static String getCurrentDatetime(){
		return new SimpleDateFormat(FORMAT_DATETIME).format(new Date());
	}
	public static String getCurrentDate(){
		return new SimpleDateFormat(FORMAT_DATE).format(new Date());
	}
	public static String getCurrentTime(){
		return new SimpleDateFormat(FORMAT_TIME).format(new Date());
	}
	public static int getCurrentUnixTime(){
		return (int)(System.currentTimeMillis()/1000);
	}
	/*
    * 将时间转换为时间戳
    */
	public static String dateToTimestamp(String s) throws ParseException{
		String res;
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = simpleDateFormat.parse(s);
		long ts = date.getTime();
		res = String.valueOf(ts);
		return res;
	}
}
