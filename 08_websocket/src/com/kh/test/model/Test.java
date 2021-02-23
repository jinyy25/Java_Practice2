package com.kh.test.model;

import java.util.Date;

public class Test {

	private int SEQ;
	private String writer;
	private String title;
	private String content;
	private Date regdate;
	public Test(int sEQ, String writer, String title, String content, Date regdate) {
		super();
		SEQ = sEQ;
		this.writer = writer;
		this.title = title;
		this.content = content;
		this.regdate = regdate;
	}
	public int getSEQ() {
		return SEQ;
	}
	public void setSEQ(int sEQ) {
		SEQ = sEQ;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	@Override
	public String toString() {
		return "Test [SEQ=" + SEQ + ", writer=" + writer + ", title=" + title + ", content=" + content + ", regdate="
				+ regdate + "]";
	}
	
	
	
	
}
