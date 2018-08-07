package com.izhengyin.test.other.linked;

/**
 * 单链表实现
 * @author zhengyin 
 * 1. 长度
 * 2. 添加
 * 3. 删除
 * 4. 获取下标
 * 5. 根据下标获取元素
 */
public class MyDlinkList {
	private int size;
	private DINode head;
	private DINode tail;
	
	public MyDlinkList() {
		this.size = 0;
		this.head = new DINode();
		this.tail = new DINode();
	}
	
	public int getSize(){
		return this.size;
	}
	
	public boolean fristAdd(Object o){
		DINode node = new DINode(o , head , head.getNext());
		head.setPre(node);
		head.getNext().setPre(node);
		size ++;
		return true;
		
	}
	
	
}
