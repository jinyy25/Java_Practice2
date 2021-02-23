package com.kh.notice.contoller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.kh.notice.model.service.NoticeService;
import com.kh.notice.model.vo.Notice;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

/**
 * Servlet implementation class NoticeWriteEndServlet
 */
@WebServlet("/notice/noticeWriteEnd")
public class NoticeWriteEndServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeWriteEndServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		/* 클라이언트가 보낸 파일을 저장하기 
		 * 1. 클라이언트가 보낸 요청이 multipart으로 요청을 한건지 확인하기
		 * */
		if(!ServletFileUpload.isMultipartContent(request)) {
			request.setAttribute("msg", "공지사항작성 오류![form:enctype에러 관리자에게 문의하세요!]");
			request.setAttribute("loc","/");
			request.getRequestDispatcher("/veiws/common/msg.jsp").forward(request,response);
			return;
		}
		
		//정상적인 multipart방식으로 요청이 오면 파일을 업로드하고 DB에저장하기!
		//cos.jar를 이용해서 파일을 업로드한다.
		//1. 파일 업로드 위치를 선정! 절대경로로....
		String path=getServletContext().getRealPath("/upload/notice");
		//2. 업로드파일의 최대크기를 설정
		int maxSize=1024*1024*10;//10MB
		//3. 파일명에 대한 인코딩값을 설정(UTF-8)
		String encode="UTF-8";
		//4. 파일명을 재정의할 수 있는 객체(메소드) 대입
		//cos.jar에서 기본제공하는 파일명 rename객체가 있음.
		DefaultFileRenamePolicy rename=new DefaultFileRenamePolicy();
		
	
		//cos.jar에서 제공하는 MultipartRequest객체를 생성하면 업로드가 됨!
		
		MultipartRequest mr=new MultipartRequest(request,path,maxSize,encode,rename);
		
		//MutilpartRequest객체를 생성 후에는 파라미터 값을 MulitpartRequest로 가져와야함.
		//HttpServletRequest는 사용하지 않음.
		
		
		
		
		
		Notice n=new Notice();
		n.setNoticeTitle(mr.getParameter("title"));
		n.setNoticeWriter(mr.getParameter("writer"));
		n.setNoticeContent(mr.getParameter("content"));
		//파일이름은 rename되어있는 파일이름을 가져와야하기 때문에 
		//mr.getFilesystemName("name")매소드를 이용
		//n.setFilePath(request.getParameter("upload"));
		n.setFilePath(mr.getFilesystemName("upload"));//리네임된 파일명을 가져옴
		
		int result=new NoticeService().insertNotice(n);
		
		String msg="";
		String loc="/notice/noticeList";
		msg=result>0?"공지사항등록성공":"공지사항등록실패";
		request.setAttribute("msg",msg);
		request.setAttribute("loc",loc);
		request.getRequestDispatcher("/views/common/msg.jsp")
		.forward(request, response);
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
