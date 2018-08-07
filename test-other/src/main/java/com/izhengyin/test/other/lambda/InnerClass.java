package com.izhengyin.test.other.lambda;

/**
 * 成员内部类
 * @author zhengyin
 */
class A{
	private static String say = "Hello , inner A . I am outside class";
	private static String info = "成员内部类依附外部类而存在的，也就是说，如果要创建成员内部类的对象，前提是必须存在一个外部类的对象,通过暴露给外部的成员属性进行访问。";
	private static String code = "new A().getInnerA().tail();";
	private InnerA innerA;
	
	public InnerA getInnerA(){
		if(innerA == null){
			innerA = new InnerA();
		}
		return innerA;
	}
	
	class InnerA{
		public void tail(){
			System.out.println("Say : "+say);
			System.out.println("info: "+info);
			System.out.println("code: "+code);
		}
	}
}

/**
 * 局部内部类
 * @author zhengyin
 */
class B {
	
	private static String say = "Hello , inner B . I am outside class";
	private static String info = "局部内部类是定义在一个方法或者一个作用域里面的类，它和成员内部类的区别在于局部内部类的访问仅限于方法内或者该作用域内。";
	private static String code = "new A().getInnerA().tail();";
	
	public void tail(){
		class InnerB{
			public InnerB() {
				System.out.println("Say : "+say);
				System.out.println("info: "+info);
				System.out.println("code: "+code);
			}
		}
		new InnerB();
	}
}


abstract class TestC{
	abstract void tail();
}

class C {
	private static String say = "Hello , inner C . I am outside class";
	private static String info = "匿名内部类只能使用一次，它通常用来简化代码编写,使用匿名内部类还有个前提条件：必须继承一个父类或实现一个接口。";
	private static String code = "new C().run();";
	public void run() {
		tail(new TestC() {
			void tail() {
				System.out.println("Say : "+say);
				System.out.println("info: "+info);
				System.out.println("code: "+code);
			}
		});
	}
	
	public void tail(TestC testC){
		testC.tail();
	}
}

class D {
	
	private static String say = "Hello , inner D . I am outside class";
	private static String info = "静态内部类是不需要依赖于外部类的，这点和类的静态成员属性有点类似，并且它不能使用外部类的非static成员变量或者方法。";
	private static String code = "new D.InnerD().tail();";
	
	static class InnerD{
		public void tail(){
			System.out.println("Say : "+say);
			System.out.println("info: "+info);
			System.out.println("code: "+code);
		}
	}
}


public class InnerClass {
	public static void main(String[] args) {
		new A().getInnerA().tail();
		System.out.println("-----------");
		new B().tail();
		System.out.println("-----------");
		new C().run();
		System.out.println("-----------");
		new D.InnerD().tail();
	}
}
