package com.izhengyin.test.other.test;

/**
 * 进制转换
 * @author zhengyin
 *
 */
public class Jinzhi {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		decimalToBinary();
		byteToBinary();
		shortToInt();
	}
	
	
	/**
	 *  整形与二进制转换
	 */
	static void decimalToBinary(){
		int i1 = 124;
		/**
		 *  124 / 2  62 => 0
		 *  62  / 2  31 => 0
		 *  31  / 2  15 => 1
		 *  15  / 2  7  => 1
		 *  7   / 2  3  => 1
		 *  3   / 2  1  => 1
		 *  1   / 2  0  => 1 
 		 */
		System.out.println(Integer.toBinaryString(i1));
		
		// 二进制补码表示负数
		
		int i2 = 90;
		System.out.println(Integer.toBinaryString(i2));
		//0000 0000 0000 0000 0000 0000 0101 1010
		int i3 = -90;
		System.out.println(Integer.toBinaryString(i3));
		//0000 0000 0000 0000 0000 0000 0101 1010	+1
		//1111 1111 1111 1111 1111 1111 1010 0110
		System.out.println(Integer.valueOf("01111100", 2));

		System.out.println(Integer.toBinaryString(3));
		//  0000 0000 0000 0000 0000 0000 0000 0011

		//-3 ->		 1111 1111 1111 1111 1111 1111 1111 1101


		System.out.println(Integer.toBinaryString(-3));
		System.out.println("-----decimalBinary-----");
	}
	
	static void byteToBinary(){
		byte b1 = 124;
		/**
		 * --
		 * 		1 / 2 0 => 1
		 * 
		 * 		001
		 * --
		 * 		2 / 2 1 => 0
		 * 		1 / 2 0 => 1
		 * 		
		 * 		010
		 * --
		 *		4 / 2 2 => 0
		 *		2 / 2 1 => 0
		 * 		1 / 2 0 => 1 	
		 * 		
		 * 		100
		 */
		
		
		
		System.out.println(Integer.toBinaryString(0124));
		System.out.println(Integer.valueOf("001010100", 2));
		int i1 = Integer.valueOf("001010100", 2);
		//Byte.
	}
	
	static void shortToInt(){
		short i = 12221;
		System.out.println(i == 12221);
	}

}
