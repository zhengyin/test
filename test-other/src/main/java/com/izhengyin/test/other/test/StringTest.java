package com.izhengyin.test.other.test;

import com.alibaba.fastjson.JSON;
import scala.util.parsing.combinator.testing.Str;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringTest {


	private final static String requestUriSpecialMark = "\"request_uri\":\"";
	private final static int requestUriSpecialMarkLen = requestUriSpecialMark.length();
	private final static Pattern pattern = Pattern.compile("^/\\d+/\\d+/");
	private static Pattern pattern2 = Pattern.compile("([A-Z]+_KFZ_COM)_([0-9.]+)_(.+)");

	public static void main(String[] args) {

		String ios = "IOS_KFZ_COM_2.0.2.3_iPhone 6_11.3";
		String android = "ANDROID_KFZ_COM_2.0.26_SLA-AL00_7.0";

		Matcher matcher = pattern2.matcher(ios);
		if(matcher.find()){
			System.out.println(matcher.group(0));
			System.out.println(matcher.group(1));
			System.out.println(matcher.group(2));
			System.out.println(matcher.group(3));
		}



		System.out.println(pattern2.matcher(android).find());



		System.exit(1);


		String a = "/12321/3123123213/";

		System.out.println(JSON.toJSONString(a.substring(1).split("/")));

		System.exit(1);
		String content = "{\"request_method\":\"GET\",\"log_type\":\"nginx_access\",\"request_uri\":\"/16348670/123213213?aa=11&11=222\",\"hostname\":\"localhost\",\"http_version\":\"1.1\",\"kfz_uuid\":\"- \",\"source\":\"/data/logs/nginx/access/curr/default.access.log\",\"remote_user\":\"-\",\"kfz_uid\":\"0\",\"geoip\":{},\"@timestamp\":\"2018-07-02T10:01:35.000Z\",\"http_referer\":\"\\\"-\\\"\",\"remote_addr\":\"127.0.0.1\",\"body_bytes_sent\":200,\"request_time\":0.0,\"http_user_agent\":\"\\\"Mozilla/5.0 (Macintosh; Intel Mac OS X 10_13_3) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/67.0.3396.99 Safari/537.36\\\"\",\"host\":{\"name\":\"localhost\"},\"http_status\":404,\"@version\":\"1\"}";

		int beginIndex = content.indexOf(requestUriSpecialMark) + requestUriSpecialMarkLen;
		int endIndex   = content.indexOf("\"",beginIndex);
		if(beginIndex < requestUriSpecialMarkLen || endIndex < 0){
			return;
		}
		String requestUri = content.substring(beginIndex,endIndex);

		System.out.println(requestUri +" -> "+ pattern.matcher(requestUri).find());

		/*
		String sess = "replyNum|i:12;email|s:23:\"david70612@yahoo.com.cn\";area|i:1006000000;isDelete|i:0;sex|s:3:\"man\";mobile|s:0:\"\";loginTimes|i:23665;qqNum|i:0;userId|i:12;regIp|s:11:\"124.17.17.6\";buyerScore|i:43;menuOrder|s:50:\";;shopMan,stallMan,reviewMan,auctionMan,serverMan;\";regTime|s:19:\"2007-07-24 15:21:34\";isMobileChecked|i:0;pubNum|i:22;isForbidden|i:0;userType|s:21:\"auctioneer,shopkeeper\";username|s:10:\"david70612\";platform|i:1;loginType|i:1;reqTime|s:19:\"2018-06-26 09:52:00\";PC_VISITOR|b:1;";

		String userIdmark = "userIasdsadd|i:";
		int beginIndex = sess.indexOf(userIdmark) + userIdmark.length();

		int endIndex = sess.indexOf(";",beginIndex);
		System.out.println(Integer.valueOf(sess.substring(beginIndex,endIndex)));
		*/
	}

	public static boolean check(String basePath){
		System.out.println("basePath:"+basePath);
		return true;
	}
}
