package com.izhengyin.test.other.hash;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author zhengyin zhengyinit@outlook.com
 * Created on 2020-12-10 17:16
 */
public class HashUtils {
    public static void main(String[] args) throws Exception{
        System.out.println(hash("abc","md5"));
        System.out.println(hash("abc","hash1"));
        System.out.println("abc".hashCode());
    }

    /**
     * 计算字符串的MD5值
     * @param string 明文
     * @return       字符串的MD5值
     */
    private static String hash(String string , String algotithm) {
        if (string.isEmpty()) {
            return "";
        }
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
            byte[] bytes = md5.digest(string.getBytes("UTF-8"));
            String result = "";
            for (byte b : bytes) {
                String temp = Integer.toHexString(b & 0xff);
                if (temp.length() == 1) {
                    temp = "0" + temp;
                }
                result += temp;
            }
            return result;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "";
    }


}

