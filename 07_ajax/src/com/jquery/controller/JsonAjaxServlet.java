package com.jquery.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.google.gson.Gson;
import com.kh.member.model.vo.Person;

/**
 * Servlet implementation class JasonAjaxServlet
 */
@WebServlet("/json/jsonTest")
public class JsonAjaxServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public JsonAjaxServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//ajax로 요청했을때 데이터를 자바스크립트 객체로 받을 수 있게 하려면 JSON을 이용
		
		//기본은 json-simple라이브러리를 이용하면 자바스크립트객체로 데이터를 전송 할 수 있음
		//json-simple의 JASONObject객체를 이용해서 key:value형식으로 값을 넣고
		//JSONObject를 전송하면 script에서 javascript객체로 받을 수 있음 
		//자료형을 그대로 유지
		
		//멤버변수에 다른 객체가 들어가면 객체로 변경되지 못함(Date, 다른객체...)
		
		
		
		//단일 객체를 보낼때
		Person p=new Person("유병승",20,180.2,'남',new Date());
		
		
		
		//JSONObject 각 멤버변수의 값을 키,value형식으로 대입
		JSONObject jo=new JSONObject();
		//put()메소드를 이용
		jo.put("name",p.getName());
		jo.put("age",p.getAge());
		jo.put("height",p.getHeight());
		jo.put("gender", String.valueOf(p.getGender()));			//char형도 안됨
		
		
		SimpleDateFormat sdf=new SimpleDateFormat("yyy-MM-dd");		
		jo.put("birthday",sdf.format(p.getBirthday()));				//Date객체도 안됨. String으로 변환해서 전송
		//형식 : "{"+"name"+"유병승,"+"...."+"}
		
		//다수의 자료를 보내려면
		List<Person> list=new ArrayList();
		list.add(new Person("조현",29,170.3,'남',new Date()));
		list.add(new Person("설진호",31,173.3,'남',new Date()));
		list.add(new Person("남지선",18,160.3,'여',new Date()));
		list.add(new Person("유다해",20,160.3,'여',new Date()));
		
		//JSONArray객체를 이용
		//List에 있는 객체를 JSONObject로 변환하고 그 JSONObject를 JSONArray에 대입
//		JSONArray arr=new JSONArray();
//		for(Person p1: list ) {
//			JSONObject jobj=new JSONObject();
//			jobj.put("name",p1.getName());
//			jobj.put("age",p1.getAge());
//			jobj.put("height",p1.getHeight());
//			jobj.put("gender", String.valueOf(p1.getGender()));
//			SimpleDateFormat sdf2=new SimpleDateFormat("yyy-MM-dd");		
//			jobj.put("birthday",sdf2.format(p1.getBirthday()));
//			arr.add(jobj);			
//		}
		
		response.setContentType("application/json;charset=utf-8");
		//response.getWriter().print(jo);
		//response.getWriter().print(arr);
		//Gson라이브러리를 이용하면 더 편리하게 객체를 js로 넘길 수 있음
		new Gson().toJson(list,response.getWriter()); //리스트에 있는 person 변수명이 키값으로 들어감
		
		
		
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
