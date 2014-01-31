package com.example.assignment1;

import java.util.Date;
//this is a data type 
public class HisView {
	private String name;
	private Date date1;
	private int cliced;
	public  HisView(String clikName, Date date, int clicked){
		this.cliced=clicked;
		this.name=clikName;
		this.date1=date;
	}
	
	
	public  String clikName(){
		return name;
	}
	public Date date2(){
		return date1;
		
	}
	public int clic(){
		
		return cliced;
		
	}
	@Override
	public String toString(){
		return this.name+" | "+this.cliced;
		
	}

	
}

