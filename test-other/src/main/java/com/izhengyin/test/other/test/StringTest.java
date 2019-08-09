package com.izhengyin.test.other.test;

import com.alibaba.fastjson.JSON;
import scala.util.parsing.combinator.testing.Str;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringTest {

	private static Pattern pattern = Pattern.compile("^/v1/table/(\\w+)/new[\\/]?$");

	private static Pattern pattern2 = Pattern.compile("^/v1/(\\w+)/uploadItemImg/(\\w+)/byImgFileName[\\/]?$");

	private static Pattern pattern3 = Pattern.compile("^GET /v1/(w+)/item/(w+)");

	public static void main(String[] args) {
		System.out.println(pattern.matcher("/v1/table/Sss/new").find());
		System.out.println(pattern.matcher("/v1/table/Sss/new/").find());
		System.out.println(pattern2.matcher("/v1/223sddAS/uploadItemImg/123_sd2/byImgFileName").find());
		System.out.println(pattern2.matcher("/v1/223sddAS/uploadItemImg/123_sd2/byImgFileName/").find());

		System.out.println(pattern3.matcher("GET /v1/1034285/item/1").find());
	}


}
