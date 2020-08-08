package com.izhengyin.test.other.unicode;

/**
 *
 * Java 位运算
 * Created by zhengyin on 2017/8/17 上午10:09.
 * Email  <zhengyin.name@gmail.com> .
 *
 */
public class BitOperation {

    public static void main(String[] args){

       t1();
        System.exit(1);



        shl();
        print("shl --- --- --- --- --- --- --- ");
        shr();
        print("shr --- --- --- --- --- --- --- ");
        bitAnd();
        print("bitAnd --- --- --- --- --- --- --- ");
        bitOr();
        print("bitOr --- --- --- --- --- --- --- ");
        xor();
        print("xor --- --- --- --- --- --- --- ");
        andn();
    }

    /**
     *  << 左移
     */
    public static void shl(){
        int i = 10;
        print(Integer.toBinaryString(i));    // 0000 0000 0000 0000 0000 0000 0000 1010
        int res = 10<<2;                     // 0000 0000 0000 0000 0000 0000 0010 1000
        /*
            res = 1x2^5+0x2^4+1x2^3+0x2^2+0x2^1+0x2^0 = 40
         */
        print(res);


        int n = -10;                        // 正10 0000 0000 0000 0000 0000 0000 0000 1010  1. 取反 0101 2. 加一（二进制补码得到负数） (+ 1) 0102 （遇2进1）0110
        print(Integer.toBinaryString(n));   // 负10 1111 1111 1111 1111 1111 1111 1111 0110
        int res2 = n << 2;                  //      1111 1111 1111 1111 1111 1111 1101 1000  用零进行补位
        print(Integer.toBinaryString(res2));//      1111 1111 1111 1111 1111 1111 1101 1000
        /**
         *  负 : 1111 1111 1111 1111 1111 1111 1101 1000  1.减一 1101 1000 (-1) 1101 0111 (尾数不够接着往上减，就行  1000 - 1 = 0999 = 999) 2.取反  0010 1000
         *  正 : 0000 0000 0000 0000 0000 0000 0010 1000
         *  res2 = 1x2^5+0x2^4+1x2^3+0x2^2+0x2^1+0x2^0 = 40  加上符号 - 40
         */
        print(res2);
    }

    /**
     * >> 右移
     */
    public static void shr(){
        int i=10;
        print(Integer.toBinaryString(i));    // 0000 0000 0000 0000 0000 0000 0000 1010
        int res = 10>>2;                     // 0000 0000 0000 0000 0000 0000 0000 0010  -> 10
        /*
            res = 1x2^1+0x2^0 = 2
         */
        print(res);

        int n = -10;
        print(Integer.toBinaryString(n));   // 负10 1111 1111 1111 1111 1111 1111 1111 0110
        int res2 = n >> 2;     // 右移动
        print(Integer.toBinaryString(res2));   //   1111 1111 1111 1111 1111 1111 1111 1101 用1补位
        /**
         * 负 : 1111 1111 1111 1111 1111 1111 1111 1101  1.减一   1101 （-1） 1100 2. 取反  0011
         * 正 : 0000 0000 0000 0000 0000 0000 0000 0011
         * res2 = 1*2^1+1*2^0 = 3   加上符号 -40
         */
        print(res2);
        int res3 = n >>> 2;     // 无符号右移
        print(Integer.toBinaryString(res3));   // 0011 1111 1111 1111 1111 1111 1111 1101 用0补位
        print(res3);

    }

    /**
     * & 位与
     * 第一个操作数的的第n位于第二个操作数的第n位如果都是1，那么结果的第n为也为1，否则为0
     */
    public static void bitAnd(){
        int a = 5;
        int b = 3;
        print(Integer.toBinaryString(a));    // 0000 0000 0000 0000 0000 0000 0000 0101
        print(Integer.toBinaryString(b));    // 0000 0000 0000 0000 0000 0000 0000 0011
        int res = a & b;                     // 0000 0000 0000 0000 0000 0000 0000 0001
        print(res); // 1
    }

    /**
     * | 位与
     * 第一个操作数的的第n位于第二个操作数的第n位 只要有一个是1，那么结果的第n为也为1，否则为0
     */
    public static void bitOr(){
        int a = 5;
        int b = 3;
        print(Integer.toBinaryString(a));    // 0000 0000 0000 0000 0000 0000 0000 0101
        print(Integer.toBinaryString(b));    // 0000 0000 0000 0000 0000 0000 0000 0011
        int res = a | b;                     // 0000 0000 0000 0000 0000 0000 0000 0111
        print(res); // 7
    }

    /**
     * ^ 位异或
     * 第一个操作数的的第n位于第二个操作数的第n位 相反，那么结果的第n为也为1，否则为0
     */
    public static void xor(){
        int a = 5;
        int b = 3;
        print(Integer.toBinaryString(a));    // 0000 0000 0000 0000 0000 0000 0000 0101
        print(Integer.toBinaryString(b));    // 0000 0000 0000 0000 0000 0000 0000 0011
        int res = a ^ b;                     // 0000 0000 0000 0000 0000 0000 0000 0110
        print(res); // 6
    }

    /**
     * ~ 位非
     * 操作数的第n位为1，那么结果的第n位为0，反之。
     */
    public static void andn(){
        int a = 5;
        print(Integer.toBinaryString(a));    // 0000 0000 0000 0000 0000 0000 0000 0101
        int res = ~a ;                       // 1111 1111 1111 1111 1111 1111 1111 1010
        print(res); // 6
    }


    public static void t1(){
        Long v1 = 8000000000000000L;
        print(Long.toBinaryString(v1));
        Long v2 = 8001000000000000L;
        print(Long.toBinaryString(v2));

    }


    private static void print(Object o){
        System.out.println(o);
    }
}
