package com.izhengyin.test.other.unicode;


import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhengyin on 2017/8/17 下午2:43.
 * Email  <zhengyin.name@gmail.com> .
 *
 * 16 进制速查表
 * 0000 0
 * 0001 1
 * 0010 2
 * 0011 3
 * 0100 4
 * 0101 5
 * 0110 6
 * 0111 7
 * 1000 8
 * 1001 9
 * 1010 A
 * 1011 B
 * 1100 C
 * 1101 D
 * 1110 E
 * 1111 F
 */
public class Converter {

    private static final Map<String,String> binaryToHexMap;
    static {
        binaryToHexMap = new HashMap<>();
        binaryToHexMap.put("0000","0");
        binaryToHexMap.put("0001","1");
        binaryToHexMap.put("0010","2");
        binaryToHexMap.put("0011","3");
        binaryToHexMap.put("0100","4");
        binaryToHexMap.put("0101","5");
        binaryToHexMap.put("0110","6");
        binaryToHexMap.put("0111","7");
        binaryToHexMap.put("1000","8");
        binaryToHexMap.put("1001","9");
        binaryToHexMap.put("1010","A");
        binaryToHexMap.put("1011","B");
        binaryToHexMap.put("1100","C");
        binaryToHexMap.put("1101","D");
        binaryToHexMap.put("1110","E");
        binaryToHexMap.put("1111","F");

    }

    public static void main(String[] args) throws UnsupportedEncodingException {
        utf8Convert("a");
        utf8Convert("知");
        utf8Convert("韓");
        utf8Convert("\uD83D\uDE01");
        utf8Convert("\uD83D\uDC69");
    }



    /**
     *  UTF-8 字节编码范围
     *  U+ 0000 ~ U+ 007F: 0XXXXXXX
        U+ 0080 ~ U+ 07FF: 110XXXXX 10XXXXXX
        U+ 0800 ~ U+ FFFF: 1110XXXX 10XXXXXX 10XXXXXX
        U+10000 ~ U+1FFFF: 11110XXX 10XXXXXX 10XXXXXX 10XXXXXX

        a  : 二进制 ：01100001 编码范围: U+ 0000 ~ U+ 007F (1100001) 16进制表示 0111 0001 （前补0） -> 61
        知 : 二进制 ：11100111 10011111 10100101  编码范围:  U+ 0080 ~ U+ 07FF (0111 011111 100101) 16进制表示 0111 0111 1110 0101 -> 77e5
        韓 : 二进制 ：11101001 10011111 10010011  编码范围:  U+ 0080 ~ U+ 07FF (1001 011111 010011) 16进制表示 1001 0111 1101 0011 -> 97d3
     "\uD83D\uDE01" : 二进制 ：11110000 10011111 10011000 10000001  编码范围: U+10000 ~ U+1FFFF (000 011111 011000 000001) 0 0001 1111 0110 0000 0001 -> 1f601
                                                                                                                         0 0001 1111 0110 0000 0001
     */
    public static void utf8Convert(String s) throws UnsupportedEncodingException {
        byte[] bytes = s.getBytes("UTF-8");
        for(byte b : bytes){
            System.out.println(b+" to Bin "+Integer.toBinaryString(b & 0xff));
            System.out.println(b+" to Hex "+Integer.toHexString(b & 0xff));
        }
        if(s.length()>1){
            System.out.println(s + "  to Unicode "+charToUnicode(s.charAt(0))+charToUnicode(s.charAt(1)));
        }else{
            System.out.println(s + "  to Unicode "+charToUnicode(s.charAt(0)));
        }

    }



    public static String charToUnicode(char in){
        char x10 = 0x10;
        char x100 = 0x100;
        char x1000 = 0x1000;
        String out = Integer.toHexString(in).toUpperCase();
        if(in < x10){ // 1位  01 ~ 09
            out = "\\u000"+out;
        }else if(in < x100){
            out = "\\u00"+out;
        }else if(in < x1000){
            out ="\\u0"+out;
        }else{
            out = "\\u"+out;
        }
        return out;
    }


    public static char byteToChar(byte b){
        Charset charset = Charset.forName("UTF-8");
        ByteBuffer byteBuffer = ByteBuffer.allocate(1);
        byteBuffer.put(b);
        byteBuffer.flip();
        CharBuffer charBuffer = charset.decode(byteBuffer);
        return charBuffer.charAt(0);
    }

    public static char[] bytesToChars(byte[] bytes){
        Charset charset = Charset.forName("UTF-8");
        ByteBuffer byteBuffer = ByteBuffer.allocate(bytes.length);
        byteBuffer.put(bytes);
        byteBuffer.flip();
        CharBuffer charBuffer = charset.decode(byteBuffer);
        byteBuffer.clear();
        return charBuffer.array();
    }


    /*
    String myString = "\u0048\u0065\u006C\u006C\u006F World";
        System.out.println(myString);
     */

    public static void test(){
        char space = ' ';   // 空格
        char a = 'a';       // a
        char A = 0x41;      // A
        char z = '中';      // 中
        char z1 = 0x4e2d;   // 中
        char z2 = '\u4e2d'; // 中
        System.out.println(space+"=>"+Integer.toHexString(space));
        System.out.println(a+"=>"+Integer.toHexString(a));
        System.out.println(A+"=>"+Integer.toHexString(A));
        System.out.println(z+"=>"+Integer.toHexString(z));
        System.out.println(z1+"=>"+Integer.toHexString(z1));
        System.out.println(z2+"=>"+Integer.toHexString(z2));
        System.out.println("--- --- --- --- Char to Integer --- --- --- --- ");
        int  i = 26;
        String binarys = "11010";
        System.out.println("十转二："+Integer.toBinaryString(i));
        System.out.println("十转八："+Integer.toOctalString(i));
        System.out.println("十转十六："+Integer.toHexString(i));
        System.out.println("二转十："+Integer.valueOf(binarys,2));
        System.out.println("八转十："+Integer.valueOf("125",8));
        System.out.println("十六转十："+Integer.valueOf("77E5",16));
        // byte 转 int
        byte b = -28;
        int b_int = b & 0xff;
        System.out.println(Integer.toBinaryString(b_int));
        System.out.println("--- --- --- --- decimal convert --- --- --- --- ");

    }
}
