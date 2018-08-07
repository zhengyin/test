package com.izhengyin.test.other.enums;

public enum Enum2 {
	GREEN("绿色",1), YELLOW("黄色",2), RED("红色",3);
	
	private String name;
	private int index;
	private Enum2(String name , int index) {
		this.setName(name);
		this.setIndex(index);
	}
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
}
