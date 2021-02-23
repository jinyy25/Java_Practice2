package com.kh.member.model.vo;

import java.util.Date;

public class Person {

	private String name;
	private int age;
	private double height;
	private char gender;
	private Date birthday;
	
	public Person() {
		// TODO Auto-generated constructor stub
	}

	public Person(String name, int age, double height, char gender, Date birthday) {
		super();
		this.name = name;
		this.age = age;
		this.height = height;
		this.gender = gender;
		this.birthday = birthday;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}

	public char getGender() {
		return gender;
	}

	public void setGender(char gender) {
		this.gender = gender;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	@Override
	public String toString() {
		return "Person [name=" + name + ", age=" + age + ", height=" + height + ", gender=" + gender + ", birthday="
				+ birthday + "]";
	}


	
	
}
