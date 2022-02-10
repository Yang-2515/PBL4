package model.bean;

public class Category {
	private int cateID;
	private String name;
	
	public Category(String name) {
		this.name = name;
	}
	public int getCateID() {
		return cateID;
	}
	public void setCateID(int cateID) {
		this.cateID = cateID;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
