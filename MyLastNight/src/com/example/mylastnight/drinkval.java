package com.example.mylastnight;

public class drinkval {
	private long id;
	private String name;
	private double alval;
	
	public void setid(long id){
		this.id = id;
	}
	
	public void setname(String name){
		this.name = name;
	}
	
	public void setalval(double alval){
		this.alval = alval;
	}
	
	public String getname(){
		return this.name;
	}
	
	public double getalval(){
		return this.alval;
	}
	
	public long getid(){
		return this.id;
	}
}
