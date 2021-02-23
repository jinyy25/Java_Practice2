package com.jquery.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

/**
 * Servlet implementation class FileUploadAjaxServlet
 */
@WebServlet("/upload/ajaxUpload")
public class FileUploadAjaxServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FileUploadAjaxServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	      String path=getServletContext().getRealPath("/upload");
	      int maxSize=1024*1024*100;
	      
	      MultipartRequest mr=new MultipartRequest(request, path,maxSize,"UTF-8",new DefaultFileRenamePolicy());
	      //이거 하면 업로드됨
	   
	      String name=mr.getParameter("name");
	      String email=mr.getParameter("email");
	      //String FileName=mr.getFilesystemName("upload"); //imgPreview.jsp 에서 가져오는것
	      System.out.println(name+" : "+email);
	      
	      //다중파일로 넘어올때 이름 가져와서 파일면 가져오기
	      List<String> saveFiles=new ArrayList();
	      Enumeration<String> fileNames=mr.getFileNames();
	      //key로 지정한 이름을 가져올수 있음
	      while(fileNames.hasMoreElements()) {
	         saveFiles.add(mr.getFilesystemName(fileNames.nextElement()));
	      }
	      
	      System.out.println(saveFiles);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
