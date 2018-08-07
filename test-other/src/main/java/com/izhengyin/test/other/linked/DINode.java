package com.izhengyin.test.other.linked;

public class DINode implements INode{

	private Object data;
	private DINode pre;
	private DINode next;
	


	public DINode() {
		this(null,null,null);
	}
	
	public DINode(Object data , DINode pre , DINode next){
		this.data = data;
		this.pre = pre;
		this.next = next;
	}
	
	public DINode getPre() {
		return pre;
	}

	public void setPre(DINode pre) {
		this.pre = pre;
	}

	public DINode getNext() {
		return next;
	}

	public void setNext(DINode next) {
		this.next = next;
	}
	
	

	@Override
	public Object getData() {
		return this.data;
	}

	@Override
	public void setData(Object obj) {
		
		this.data = obj;
		
	}

}
