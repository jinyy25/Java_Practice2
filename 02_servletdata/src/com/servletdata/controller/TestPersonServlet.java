package com.servletdata.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class TestPersonServlet
 */
@WebServlet("/testPerson.do")
public class TestPersonServlet extends HttpServlet {
   private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TestPersonServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

   /**
    * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
    */
   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      request.setCharacterEncoding("UTF-8");
      // TODO Auto-generated method stub
      //클라이언트가 보낸 데이터를 받아보자
      //1. 한개의 값을 받은 경우 : requst.getParameter("input태그 ||?key")->반환형 String 배열
      String name=request.getParameter("name");
      String color=request.getParameter("color");
      String animal=request.getParameter("animal");
      //클라이언트가 보낸값은 모두 String으로 받아하기 떄문에 형변환을 해줘야함
      //int age=Integer.parseInt(request.getParameter("age"));
      //double height=Double.parseDouble(request.getParameter("height"));
      int age=19;
      double height=180.5;
      
      //2. 여러개의 값을 받을 경우 : request.getParameterValues("input태그 ||?key")-> 반환형 String 배열
      String[] foods=request.getParameterValues("food");
      
      System.out.println("클라이언트가 전달한 내용");
      System.out.println("name : "+name+" color : "+color+" animal : "+animal+" age : "+age+" height : "+height);
      System.out.println("음식 :");
//      for(String f : foods) {
//         System.out.println(f+" ");
//      }
      System.out.println();
      
      //클라이언트가 보낸 데이터의 key값만 받아오기 Enumeration
      Enumeration<String> keys=request.getParameterNames();
      while(keys.hasMoreElements()) {
    	  System.out.println("key : "+keys.nextElement());
      }
      //key와 value를 한번에 가져와 처리하기
      //Map<String, String[]> 큰값을 기준으로 key, value배열
      //request.getParameterMap();
      Map<String,String[]> param=request.getParameterMap();
      
      Set<Map.Entry<String,String[]>> entry=param.entrySet();
      
      for(Map.Entry<String,String[]> v: entry) {
    	  System.out.println(v.getKey()+" : "+v.getValue());
      }
      
      Iterator<Map.Entry<String,String[]>> it=entry.iterator();
      
      while(it.hasNext()) {
    	  Map.Entry<String, String[]> pa=it.next();
    	  for(String v : pa.getValue()) {
    		  System.out.println("key : "+pa.getKey()+" value : "+v);
    	  }
      }
      
      //응답메세지 작성하기 -> 응답 html페이지를 작성하여 전달! 클라이언트(브라우저)에게
      //HttpServletRespone객체를 이용
      //1. contentType 설정 : response.setContentType(mime타입;인코딩;);
      response.setContentType("text/html;charset=utf-8");
      //2. 응답 데이터(문자,코드) 전송할 수 있는 스트림을 open
      //response.getWriter() -> 문자열/response.getOutputStream() -> 파일전송
      PrintWriter out=response.getWriter();
      
      String html="<html>";
      html+="<body>";
      //movePageServlet이 넣은 데이터 가져오기
      String msg=(String)request.getAttribute("msg");
      html+="<h2 style='color:skyblue;'>"+msg+"</h2>";
      html+="<h1>당신이 선택한 취향은</h1>";
      html+="<h3>당신의 이름은"+name+"이고, 나이는"+age+"이고, 키는"+height+"</h3>";
      html+="<p>당신이 <span style='color:"+color+"'> 좋아하는 색</span>은 "+color+"이고, 좋아하는 음식은 </p>";
      html+="<ul style='list-style-type:none;'>";
//      for(String f : foods) {
//    	  html+="<li>"+f+"</li>";
//      }
      html+="</ul>";
      String src="";
//      switch(animal) {
//      	case "고래" :src="data:image/jpeg;base64,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";break;
//      	case "수호랑" :src="data:image/jpeg;base64,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";break;
//      	case "기린" :src="https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcTin7so2P-YdwncoqEBoBRPykJgH8o5KVM4Mg&usqp=CAU";break;
//      	case "라이언" :src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAQsAAAC9CAMAAACTb6i8AAABZVBMVEX////VliwAAADExMTLy8vVli3UlyvWlir8/PzUly3///3//v/ZlyXUmCnVli/AkUvRmC+7iUfTmSbYlS3///nZlSjTljIAAAbVmCR6enrUmzfo6Ojv7+8AAQDYlC/5///g4OBkSyrTnUGEhISrq6vPoEP///Wenp64kECVlZVhYWFJSUm1tbXXnEKBYjecfD+xiUy7kUvPmke7ij8iIyIwMDAOAABvVDJsbGwODRGtgz7LpFn//+3Mnk5VVVVGNyGCZTQsHhGZeUZEORwcFQ+cdz55X0HNokCCWjTV2c5LMxvGl1TTojdDRkg6KhpTQidORDEmGA5lVDurhlAXDABFKhtvWzCFaUM5MR6trKSihmRZWVI/OTQaGh0sLTGJjIZdQi4bAAB5YC4xJh8NGSObekyPjZUlFwCLc02neUIYGxdmSyThzpX35LjZpVP68tfozqHs3L3YuoSohzvUtnfw5MclKyHANkwPAAAXb0lEQVR4nO2di1/bSJKApQJ1W3Z3o0huIQTIWGAeCQZbBMwj5nUwuxNey5ANM3eZm2T3snszlxyZTHJ//3XL4Ae2gWTdSjxDLZMNIOWn/lxdXVVdXdLmBu6lJnPagHYvNRm4Z1GXexYNuWfRkHsWDbln0ZB7Fg25Z9GQexYNuWfRkHsWDbln0ZB7Fg25Z9GQpFikvtC9nyLJ6cXE3KM1GH80N3HH60enHj6G8eWRgVRSMJJgkRb/DT6CK3k0oaXTTb/OZGr/17g+k9bGRurXr82L69OaekmARSab0ubEmLa2Fxd3F7bE3+a07OXY0k1Y0unLIQsWg+Kqncru2dluXvxtc+z3w0ITH/LWWWhyglnxt38DGLmEkUlNDM5PzczNzU3NDwyOXQ44ow0A7O2HvsG54R9UAB4nMk+SYCG14ps/eY6FKKWeXfiz1AxtYn5ycw1aZXl1ZlAM+luApwfMsy2ELEwOzwCOtAQUIwl7MSFQFDmnNsWUMk6K3wjNGK+NfnjveOvkJH+y9f3OFZhHU8uwFTkYM4oQFTD8sxieckmCxSY8DcXIkGMgZBAa7f4FhsWYT1+e75ejyL+S6tL6bmWrxuO7chFjF2HHNQ0d+wsAY8ofMwkWQi3OqC7EIoQXhyorYqzP8vtDUZGZmBB0JR4h1AqrpYXvJY2txZCTIKBI3IirezCj+jETYTEDx6EnWXBeLMllAfKLhWKABQdXiCk5CENCXZcxzrnuhuXzY7mO7EaWJ9RC3CgU45Hqx0yARUpbhQUkR4Tdg+/kELerh0gsEK5rWbYQiYHGeiG+0V1DIOJutC6h7SwWXHmn7q0DqDeeCbB4AbvEZZxFYtbD8X7EMHYCC3EsbAhzAl1yMIVwzAwk54QlFhvqz4q1FP76wHccw/KWAEbVPqeWyBx5DLu6TvzSTqz2PiaOfrs4HIdDksZClRCLDg3DhOrnTILFEWxjHD4X4zqPMNEpMu/AwmWEkKAkzOhOySd4KImFJAEWq1AJh/4qVoYhnxsmptZdWFiW5TLdLiyI5fc8JMJeqH7MRFj8ADv/LtbR81CaA2pa5p3mSOCalAaBXxZLSl744f+h+jETYTEmPau9knsXBO3CI2E1XuzBvOrHTMrvhK2q76HPYuHSw21han5U/pSJrKn/EEpewFivuQqfKjZxwkWA/xxTH6mq14sZicJzXPez1EL3dIbNklCMfl9H0mltSqAIeaCbJv4sFgxRPdDLwmZkNcUZHcV6IRNU+ar3eRjqYmAZtm/2NYuUNirMZsHGhv0vsaCM27sAPzXlRFWIYhbLcDrkYPZ5ZrNJMTgJRTSj2LSpY5EWn+IkQIkGyHb4v4SCYbuIw5eq/XB1LLJpTRiLc5/+aypRF3ywJ0yGyoVV4RxJpx/DSSEwewTDlCurUu9THYs4/V32ArdHLAzPr8Cays0BhXoxBrAttMKI7eZdjOfN1zg2OViBuf5kMQI7EWaoNkSPBAQ3i+cZ18buetSguE0Yc2L3xDGRtS/MZz+yEGqxW89UMJmOsJqFBTptnT3Ic4PWa+LrXGZfeieIVk9hUtHjaipZTMJOlVyNE3OPcNIsnHheaxRPdQd7pF34ZSIMId0UitGHepESamHX3QrsDl2TcrlcbGVhGt5h9fplQ+XICQL5a5kO86JTmFFmMZSxmIdXVa8em8qdrzZZ8FtZMLvU4aq/Vim+YkHdBVhW87yaQhZH8Nxl9fRN8XQ43jdsyPDw8KtiCwvD0Z+3XBJvuQ5Dya4bWUZEwDqo5oHVsRiV3jeqG8eOelFpZYF01lEvIpvVTYoXvVRnPdWwSGlT8M//9RopC+wuPXiwJOXBpZQfLIWtUYpDqT/bfEl8QzmiNXsRs8DBvrqMuCq92ISK7zXCU4PaHElvob462I7T+LxrvGzmeM3X4Hh30UNWPeLnAa2Csl0jRXqRBViPV8G6YaQ6MqQ4GFNkC0cLtzmaSFiM+BLGCBbmw5Hf6M3/jMe8wrFYSdTkMRTpxSAMR1iagOtiWq4bBAFD3bPiyBYeFmPIbk8A4YD738DD/mIxBcdh+wcfj1RMAY8Hxg07BEY8S4RH3vYbh3G2D48VeRiKWIxAhYpIpH00GPtRNaTEvGEj0SA0rFZ9vcPmkoWDJZnSUZL4VMTiCLY7s6DRwivIHxDvBhbUKufh2XbDg68Lsk0jXINBNUlgNSzSj2G/cwRuVaT7dBx6rOOvY7VwwmPpmJ23/wuIIuR/D/Nq8uFqWIgYtdRxHwBHK8IBHYZ13H131WCLMC6uelZt+ydiFieqareUsRhq1/CYhfzEh2HxBha6vl9zvqPOLCrwDzULiRoWo11ZCPWXNCJyQxJLVp6IYORpse03koX1HEYUPLKmisUEQAfLJ4UuvRID3fdRd3uh6+GuLP8sex1/af4NVhU8sqaSReeRYKu6uD8bYvuGjDDnxQe7i1WrM81+ZNF5JI7jUYs4wU15XlfnTsA8HHT8rbnQXyy62gvhcmCEHGY6Lbaz1UgiZCIRyBmdp5FZ6S970XUdEeM0dMRMKmtTmMBiMhYEvm8R7urYw8hyGeU4vq5DyIIZ424eJvtpHUkJ/6Izi7pYpsBBfZ/ahYWtrYWqSYvFYoCp49wQqWDT5P5Wf/kXmvA7b9suIzjarbx8ufD6VHoTp6/38y/zC0O+d+NqSxEJ/65qJ1ERi01YsG5GwYv7zwB+qWU+h+Nc6H/JvF/VvaFwRZgRXlVWAqyIxSTkfSwCTdx5YAbG4QL8OJ+RmQ5JY3w8Lq6YeAjHcgHqqhquY5QBUn2Vv/gBdiKbOkQYO9o+/5FJzNewmZNXDl7leGvPMQMnRQd3S/VQl1u7ynYFFMWpwsEoW5aLmGt18Koc5lVPf8xl5cc7ccViQt6YFRp15lKjQ0YsZsH4YUWVe6GIRVYD+Jlih3ikUxAm1opdYQCnY1VfFqGHmCKP4xvT2Qz8JaROVxa4sANTCp5YiioWq5A/WzwryxRWu8kwsVsRk16ey0xdKcZEfGMurT1cKVDH7LKwusGsupMkKliktOl/PL7U/K3diDmE2tdYWCfDWuYyOzU2svxi5LISS/zoJxGrd1tKrICeX2qQAlGiFzV7uLe3EnsOiwXpVDYLxv4JXOUsU4LG1FU2V7CYjFl0poG4iPmVHc9UwUJo/d52uVooFGYX5dGgSuHaB+0h/3mzpk8t11lktaN/hhwZnVlgXFK3VaSCRepHOC67nIj/OZ5fzgOcRNccckOM6erjTUvzOXH5TSY7Ac9dbnZh4VgVOOr189al5ywywkUYHqLcshBFIpTSZZHqgo9xU+Jb/Dh8ChO10/xxjduRvDH+9gjKRkcWBtINp7Ai1p8+qr8Yh/PmVUB6mFAyidX8QxPPwrAsHhDL6hyc/Dc8Gou/GduE3W7Ou/A66AKM9/pxG9JzFulvAWZbrUO0BccFem0bzF5fgYfzE4NTy/C0KksSJgcnBiaFCoWdE2LCt3C86orK88u914sf4Nhv0XAcCIO363usBQb2Z2uVJ6+2C4T4pae1Jbjk8y6ZUDtwpIZlsj1+3ob0nsUkfNcaalqk+J3cHWrN65mM+dH6/v6DkHFkGZY/9PP+4lDR0LvtOluBLcKyqb5i8RD+1npsBgXySGWJsJYPHOkOxljm8rAsW8NxLkv82S0U0W1ePJFhmbozJL1nsQnbrXulOCCFY6jo+C7nUrsLt7YV1mpJSYIFI/427EVdbOJdRUYiI312TmAV/ta6KmIL46jbBuudBUfHsm2OSlFhO/Ot9gKbhlPcgoovOzt8FgdkUq+YV+h916T3LGZgy29TAVNMkjAuRfscFszw5HKqzuOsSe9ZDAjT0MYCLw3DuoHQZ7Ig1rlwxlSf1u09C7ln1jYaXHwKeZ99JgvdXwRlmb2G9N4H1wD22waD9H0YLjKTfs4hI+zvA2yqPY8ppecscrLfhRVYVkuek5FoBbZdZH0SC0f3kB1vH8Bmb5+yoyiIU6dgJ3RoaxWeQdxv4DQi6FNOtVPbZS7Hf6oAPMz1ZQ+6tDAYZZ20bqSbiC+JGNTin8IC4YATerAFqjbWr4kCe6Etw7nPrxWzug6VaYybSlzbxeBeuLgiArJkOniqYDEHxxG9FnyYDEc7sFPlTKgIxTeWrsk4zbJkEXScIVyb0NL9yUKLc78lSlvTECjOccJxVYzUdV3rpsWVMcMghPjRz4LE8FoqiR6NUpTsCWzCy2KHwl56BvBsMXKIfrOfIc9NuNF6RVa5nQ7DD4rbG9RF1f5I6dpgDaEaxC+Jyb+1Hlo2CbpPEkz96nrl7zLNdbIYPU+i41pN1OybPYKTIjZ0VB+wPAiCGPGGZJu904VygWJ5BBdJSDJiM3Ss13KApl9crzyL833bQ0Uuz4v80te9j4ViLApny26rxMN+6aUc5/HCeuSbBiY4cAzdMZDnUYtafmFp/7t4t23rvOzH4S4tr8CjbN/aTk0m+o6rJOjQIgebYfnPe7U87/Ptn9eXooKU6my5tL+dP445wMn2UGgRXju6XRQm9yjbx72Phb+17XPW7nEzl9p+YX3huPlw5nDTcc3T/9mPiowEgYPiDWnG3V2A5QTauipikcnOARx4rH218CjDgWm4frS+kN961nz48viksr0ehT7DnIt1NTDilcgkXijWn+FvFTzmNVHEIu4DUvQ6bIEZTB6+0w3Zs9MPo2hpXcqSmCuh71PiyNx4wJHZ2I7GulsSy+uM8Lj6LZdzKYNyf+i2NjkGjh0rw3RdygLuxEsJogy19rWkVlkEJUcTip0uVSxka1so31LvKr0qr9bkwrId1/V4vPBQ3+ItFE1CCvLg81yf6oWW+kWsJbfnvimSXS6QQ903b2epdEhQ4e0bq+VGJjyTsCTNbZ/2y5FhSd73cJfSKyS/zP+NbIINB5kcbzzRPhaEilD3rZZ9Y/GmrSVETcZJdC5W3MdTUjf6qr4zlimAhUPe5fSlp2PP2ri4+EDFx+4yXrjI5rJvC4zY76dTmSdvDH7N8DLsl+Pd6J9GtYySyaJwjqRkq7HXPu8ceVDMrY0nuVz2XeR5LrZ+zWUyufTHKhf6kc1mpt/T64XhAfcOZ6VuwIiaCEVtb8JN4Yu3b5bU9N7RZy+ywp9MX2wYHH/IptPZbC79tvwxp6XTmdyTDXytYMNyDQcfVnd3BI3JlNb7BVZtP77MEQyXOqd7EXbf5nIpCWD6jdQQoRVaOjP9RPyRyaSzuYuw9UbHMJGFHYzCXRHDjg/2foFV3LNy7DGsDVl6h9SeSzaycUG4GPr0hTAWuat7au9j0bLvLBrQRmwfLzG6I/skRK9X4l20HmuG6l6mo8NwOosDr63yH1tvr0adyWazcjOhRTK5iw3MUbuxMVxsH85uyWL6PmMhV9bj6yWNUsjGdK7GQsagmUybyuem37q8U2GKxRziVbd637VRfb9f4YyfFNqPFKJfc5eBeAwjm77mM6S13JMq6XTiQvrtzDt41fOdAtUshGYMxJW/zS4XMhD2LzI1exG/pCrdziKdy75BLG4/1MojwJRamC32XDGS6A8+I8M022mYDMooFlPk1pXgo/BMseMY7d4a84Lqaa8PTyTy7qYRgJIXNApRRCCK34sV9zYWF0KdMOeoPfRnHvd7fk41ERbaEewMNekFCjB6o2VTt7GY3uDmUsHyOqTHPLkP1+MN5wRYTGdkyq9yGLAGC2K906ZvTWKmP3Czkq+6caljizg0sLb7kIWUeeGMO6Zp19obmA4R3kU2k7vttvec/QZ7+77OHYax0eg5gy1SzPfhHIllE3Yigp1aVzpT96x3meztJbxviFl4NQwn5UMvCAxk1Q2Hw8lBz989kRSLODFOglrTdGp49L3Qi+lbbkq/J6YwkeMA35V8YUQblcOU+M973sozIRYZ7SfYO7CduBEyopaNP0zfQS8EC/wbwJGITPOlqltfibBdfA09P4+YlF6kUwDnFnHj4l9kIlydnr41O5X9QBCvrsDUgHzj6PH2bOibsg0ZjV8Y+bC/4pFmmYGVCNdPEGDrInurfzG9QRD+U34YYHV1WW4oHVden5VKpV357rzV22bYJ0tyLGTTW6txOIS+0261FxcR1l26lJdvjRxfGx9v2l5bm9f6mIU2Cd8XSJ2FcMLTt9mLt8xkQcDDaqlyGneZOpo8ikGsym5jPU8AJ8hCRO8PSL12LZCT5IarZcD2jpvMc11MPD9ait/Iuzl2mfdNpXu/3ZzgHBGe+ILbCDmdD9OZ7n31xFBlzC6c9Us1wlZY/kbQ+FZdkU6SLGbguHGIxCThR03r/lbpTC77q0uaIn3Mg+L6qcrDAkm+q/5b+a6FOgvMN55kNa2bqmdSFxuYNL0jz0LYs8o7MK6si36SLLQ12K1vEDgmYW+m092mSTqdfU8wsxols1g3mSOrdJSVQyfKYhUqjc9ZBFvu22wulc5dN4M5ASI3/c7lLflzGZuZmK4DzKTVNIlJlIU8ZqM3SyRgZLPpTH1osfmYTue0J+/8Dl1zKOMF4XGO9lP/zi4yCK/C5l007LnvnuS05vfVCxXJpHPZiw8uIX4HFo5d+Lt8u5eKx0uSheyi03rkiHB34+OTdK4xNDE90tmLN6HBCe7Q1xVRREuqXmiVKIsxgFncdP7OYJjjwoeP000spp98fB8hjxs+a6/qMaht4jCvqO9DoixS8tg/vVY2j6m18f7drxdSPv765kPB7dBhp5kH+02e2VWwsCbJYjoju8lfZxHId7TYl2+hMRxMbilsMnjhRMTrCiRJFtmOLCjFDvewTrEs3+IOu+24DZevo1BR7/nl5wgzZH1j/K4Sw5SvUbjluI1p8uhUSeFW0rZzSSwFrdsdjRetIJtaKHBs00RCQTDnHFtuew8IiuIWnr2XL8ACdTmLyFzBwfa4g91iVJ2dna2Gvk0csXa03OBg5GzDIwXP9xWxsHEgYlG/MPu6clktvpPfH/IRv/bKFgfzRSUNx74iFtQjAkRT1Xws+VKx9dyNMC+/cxaMIeYdlk7k8H8cmRocHRsbnZgf+VHSqFpYb2o7IwzO72OOzHZh4QiXKz50tDw30fJ8jwBOz3wa1Fkgk4cnvwvb2ZkFkycjdlcANq81gRHe5cCwLN9o8kWRM6SmWczXwcIwzKpQiv/rMMKMNv1IwmjYDFp9Ci9UPN/XwQLjSFiKkUyH2uZsKpMRMEpMvvLHkJcW88Nqegh9eRaB7QU4ennj9ugyPCv5lHARv/rVvwD8oOT5vjwLTDkPKzd3gBlbA/jzQWix4sH5M1D1Uq+vgIXpuItwY5+9jDb2Qh5crOS3VuKO0deL/nojicdmuI2F61X34OFNx/dlDnDql5rvNf5TVlOwZyYl+Tj1emMpEx9+I2Lwmz7qtMwOpwdnJidnBuMi4X56j1VniVlcP1enI15Q2ony7pI4C+M6C8fcBbhDyb/6Y9xfAYtwS33rrDvJl2ehFxSlcj9ZEt0rSnXKd/IzAHX9ST9FEtULrRMLrCYA/wz58ixMdS+O+ET58iz8E3Vd8T9NkmSRmY7fPNzUnQ5TlxV21J7MvrskyiI7DvsYW017xhTxWeU9Su8qSbKQ1WvbrLnuyHSI/hogqaZRt0iy9mIE8hGh9TmCzMCLjtW9fP4TJVkWA/DqgMZvG3cwku2TAl844BOJdAa6XRL1tbTUGjw/JC6mNjKY4XDsn8FXoxYJs5BHEs/swDDlfiH27PA1KHyfyKdKwnqhvYDh3QIihBPbr/78FOBFEt2i7iYJs0ilXgDsLOyWSmfnedlRaiSjoHnDZ0rCc0TgmGzaLH048ZUsp7EkzUIowejM5hrA+KOR+a9nesSSuF58cUl1rSf/I7EYm5mZGxycmZwfG+jYcOePxCI1MDggvuampgYmUrVMWkpLNVJqfyQWY6Oj4mtwMLZasvlQalT+REvFX38wFpMzk1Pzc6sjc5MPJ1cHxB8zq5NCHj4c2ZQplD8SCyFy5UqNxanmVCpV04+aimh/LBapK2n8rSHaH4vFbXLPoiED2szgvdRk5v8BvP9f/rXC/EgAAAAASUVORK5CYII=";break;
//      	case "라쿤" :src="data:image/jpeg;base64,/9j/4AAQSkZJRgABAQAAAQABAAD/2wCEAAkGBxMTEhUTExMWFRUXGRgYGBcXGBgXHhsXGBgXFxgXGhoYHSggGB0lGxUVITEiJSkrLi4uFx8zODMtNygtLisBCgoKDg0OFxAQFy0dHR0tLS0tLS0tLS0tLS0tLS0tLS0tLS0tLS0tLS0tLS0tLS0tLS0tLS0tLS0rLS0tLS0tLf/AABEIAMIBAwMBIgACEQEDEQH/xAAcAAACAgMBAQAAAAAAAAAAAAAEBQMGAAIHAQj/xABCEAABAgQDBQYCBwcDBAMAAAABAhEAAwQhEjFBBVFhcYEGEyKRobEywRRCUmLR8PEVI3KCkqLhBzNDU7LC0jRz4v/EABgBAQEBAQEAAAAAAAAAAAAAAAABAgME/8QAHhEBAQEBAQEBAAMBAAAAAAAAAAERAiESMUFRcQP/2gAMAwEAAhEDEQA/AK+ms8LGNEBzEE5DMBEwlEXgDpAAiGZMuY9lG0DrScUAXKpybxGmxaDqaaMMBlPifSAllpgefSawQFhN4mRMCouphVSUeKYkHIn0gTtRWvOUR8I8I5C0WMICTiGgPtFL2kCXNjeMf9KczC2bOfKCKQEjK0ArltcQw7PVeFZSq6VZgxyxuGmz5lwn6wy4iLZsyrwvLVdK0sX1BivGnQ+JOhy3cjG9RUEEcMuRi4pR+xsK1IIyJbk9vSCpeygGhvV1STMQvVSQ/MWjEzgtTMzW+Yj0c5jlYCTs9Iu2sSVVOGyt+EP1T0IKbOWs++PUz5anLDCx8hF1PlVaul8ItcQJU7FBRiGbWi/yqSQUBrki8KqulCUYhlk0NlPlzKbRqEbSKBSuA1i7HZONGIDIu0TTNkkMWbhxMZ+UxRailYsHyiNNOTkDF5qtkYT8LkgQNMoO7IDXOu6L8imzpBSWIjSLNtCiCs+pivT0AFhGbMRGDGwjRo9iLG4jYRo8SSJalqCUhydIKx48MdS7Pf6PTp8tC50zuAQ6gQ6uAAyHWE/b/wD04mUCUzEK72WSxLMRueKKJGRmE7oyCrcmY8HyqhxeF8pEFSwwg0LlrBjSctojxBo1CScouJomTeJEoJyEZT0ymyhjTpwaPEUrm0yjpBUigVhtDuWpBzYRFNmhIZJtAIZ+JOcViqQyjZg+kWbalUFEA2MKKiUQo2tGO1hBUO7jP36a8vyPKeQ5xJy1H2T8xx/w5VUl7iC9nILYks4zB16RmelFUEx0rBuqzeoMSrSSCNxFutoiEhSZgKRZ7cs7wfIQ7lT8fkY6SICpXJy58tRwhrR04wkJucx0LeriIl0xDkAWAfiOMSSV4QFDN2b85xr8EyZCiu+YBHC5b2iEUykjW4Pk8OKecLqOYIcG/E88zAlRNJLksQ4HqfmIaBZU1SQSzWbkXgeZWrLpV8It1husJTKJPiUA5I0JZRf1EKO8xKSAMz4uEMn8Ic7DqAEJxBizXs9/eGMysSSpwCzgdYTEKIDAk5ADfo8Ry0LCr5jMC/qIuCwKqULUkAPdieUa1tFKmHMObForf0gi6SzlmGsEU1URrfTgNYYBNo7OwqUgZakxVNs0rFki2/fF22jOKy5bpCytpgsWs9xFzxhRFJvEiqcgZXi00uypYLq32ix7H2JKUSSAr5RhqRRNk7AnT1gJQW/LR2fsj2UptnI72dhXUG974eURSK2TTpZGFJGZitbW26qYokAt1MY+msxYO0nbhZcJPQf4jep2wajZU7vXdAcFnMc0q6ok/EX3WEPPpWDZc7xNiUE+cJ16WeOdrnEkmMjxhvjI0yvBAaIyo5QOmaTYQXRyy9xaNK8p6NZL6Q8oqNs4nxgJsLwJ9LIgYcyZYOgaDe5TlaK1Kr1X/O+JUV5e5iKn2iyS0JKyaU5HOCq6rJzyhJX1MANUTC4J0MOa6Q4TMSXBAcQp7vvEHNxBmzHbCq442jn36sQyqRKzuL3G+CZdAQ6eFt/CGkulTKF8t+oPA7o0KmviYm3Dg4jXPOFRU/hKSs2Yvw0+cETiHwAC+ZHLP2iedICpRVkwLjdlprlENPKxykEDxhOQdwAbkb2DdCd0bRAhwClruPdm9o3WkJlkauC24j/ETSiFJAAdmu2rZ+5bjDGk2aZiiXdLYnDfDbrnAJ6tKgkqSCym58IgWlZDajXTO/tFppaUKTgIJIP9viIbyMQfs3GohIsQTlZnsPZ4YKwqpUymy+tuaMmTill5EgW9PlFjXstwlSwAHAUcmYJb5QP+z0rBDcEndqbdIuJoWiqSprskN5tf0jxU1iWJdR0sOAggbLAxEqYeg3npEcxGFIZm0cjK/iIzGUBtT0ueIgNw823wMWJVo4YfnSCJM0lsg+WI3zz8ogqndSQxd3PpY7oAKYrMEsNTygFNckqIN8ItpBM9CjiDhiz/AK7nEJ8CQom5D3OWumbxLaYnCiuYGy37osdJWiWgpQbmxMVSevAbMPMsPO5g2gq3OnkBGOmobTFcFKJ3BwITbQJ4p5g/Mw3l1aiMIdtwsPMQs2pK+2eQ/wAxzUpQq+/izQ27U1BRSyJItidavYQNs6hClp3PrAXaiu72eW+FACU9I1yz0UR7GRkbZXbZNFqYsUoIAZo2pZaEpuIX11YkZRWm8ytALRBiBN4XTZwVzjJSzvi4mj5MtiQ/xW65j1AjQpLRGmYdbiPaiYS6kuSPiH/l19+kBDOqNDeIJNDiLqBbjBFHTGbceW/8YdoQRLKFoy5P6xmqXy6NKLoPh1DxoCArwm3G/tE6dmqIu4TZ9LdDDrZuzFoLAFcvgArz4coSAOjlviKmDPuDfn5wMqkK1PZi+ubZ8t8WRWy3PwrGfxHhvOYgam2UACkqe7+H58dNx4RQDs+nOMS1pwpU6VK3N8J4PlGTJRQpQSnxIZac2wuyk8i+u8wxFJMIThObjxDzTwcabyGgabRql4itRYhOJrnCfiLa2v8AymAGo0lJfATLWCzZi9n4pKQki2Rh5QTQp8IZks4cfCBmN9r9c4ZbFpkEGVgIWkYgrQqyxJt4Xf15x7SoBIUoFJOJ2swsSPQtARCiUkJCSzJdJB3Nnw/GMpECWlRWAxSr8bZaN5w3o5DqSDoyLfwsR1cHyjTbOwSuVhBbN+eFrc3ygKNUVoUCLjFffdT4W6pT0JiOUs4rJYZAXyy6k5x7VUR+kMoXJA4OPFpkwALcYfbGllSgwexfcPhT6KJ8oo22ds4f7iwCwLAnwh9/EP6coT7dpwQtSQVKUyUFtThJV0SyQ+8xd/2aQcD53ZnuP19YXT6MhOJw4WWJ3qcOBvAFuEEc+ANwoYS9ydH0Aze0eLDAm75JHDJ/eLNW0YQpRZLOb3+NwOZZmc57or1UCpZbxZOQnfoBEUKZWMOAyTvLO2ZJOfIQFWbPAYqcD7I3aflody6YqLhN2+ty1P4R4atBdIcqIaKKhOpXU19Gfdyj2RJwqubnPgIfKlMTYkDMgW89YirJCEpKgnxEa+I82+rGbArqaopDA/nlAtNULJcqUfWPe7vcF+MGy5YSMaso4t4mrazupJUwExdg1mGpinkvc5wbtSrMxTnoN0BGOkmOdu15HsavGRpNdIFZjDQtq0tA/wBICcs4hVUubxqLa1CjEyFRBPmA5RlLLxFni6mGiWA4fnKI5JUlaVByDkRkd4P4RMKcNhB94sWwtm4E4lKSpJzSQfMceMRRVJTIWkKQGVmUWF94hjs/Z5nZoAw23HqIm2bTIUrFLc7y7EcwdOOUWA0KCA5ZQ+sk3tvD3iSKXo2DhL2D62Y8LiGsmhUkDCcKhk48KhuO7mIYUMzkdHG7iM4n7r7KiOBuPdxGsQunpTNAC0mXMGaVWfrrAw2YlBB7sAX8QyD63y5QfVHwur6tmjaXPwpCgbccuT6RmqXTdgscctRwkgqQ9nBDkPlv+cG0+y0Dwqv4SATmUkgkeaQfywhRttEssXA43DHcRYj2iKZ2kp3wEggnwuQCDuByPnE0xoqi7lZALA+JPA8OGYPFSYVzKj964yIS44sVDzJMTbZ7QysA8YzsX1H1TzD59YSU+0EzFqyZWEMOJHsUnoYl6WRdtjoy4HF5pA9hDeekYTo1+un54QppalKRndh6P8m84Krp+FHV89HAfyiyo5X2qnoE9k/GWKh967/L0i9diqEJlkk4iq3lmTxJjknaScoVK1k6u2ugvusRHZOyk8Kk4hckh+ZOI9GI8oBtVSUgYuHkNwivbQJfHle3M4mI/Hgd17LUoxBn1S/QgxUe0U8vhQL+QALDEeQe3OGhNtulR3YCASQHJvqbgZku7OdeMJQgpCkkHNiU6h2wg5m4OVuLRetnUYCWVck2AGZHB75PGVnZ8F1Kcqb4RruBIOXUC7boooil42BBQwYJZme+WbkjM3z6D01CtCTMIGAln3ncG9+EWKZshacU1QO9LaaEgAXYWduW8wIpCqVhIUVGyQkhxriZmSOJDwCKbMTqw3JOfCx+cCYAs203hoYVmyu7PhxFQ+Ika52GZA3wEAbqNjxZraaRQENnOtywECbYQBYGwhtTkkHjCjaFMQ/5H+I5zjLq2+KtWZwKqJqo+I6RCY0w0jI9jIMLGwiIiPCY1QCTGm00qVBsiU12jempQYJlpwlmeIuGGxKUrLhI5qMWmZKwiynyswIeBtlBBS5Sx3afrA206/Ccj6ECIqy7HqxclkkDNrcrZQ2QUTXKVDEMwks/HDHNl7VKE4gUjzPppG8jtglJSFMkOAWt4dTluho6J9Fwlwogi+Zg9O2glHjufV92ZjnEztcpJUhRBKXB4Mc2OYhBtPtYolQDMeMXR0TaPbKWygCQU5pOoOo6PFJqf9QZ0lbI8aMsOVnNwflpFZM1UwKLsSUl9G8Qv5iPJ9McPHfoTwiWLpptntuJqXCe7VvBc/j5xV5+3y7LxNvB90xLOoXQpeEsgXLakgB+pjygmypU5C5soTkJLqlk4QtsgojR7tqzZQkZtar2oFJsSxYO59XhrsLbqpKgSXS4s+4v0u0AdqlyVT++p6fuJMxIeUHw/ZUpL2+IKytbnC6VK0dxkD7QsJ06vsLteZxKSwJK8N2sciOOEe8G9o+0niWnJkghzY4QpBHm3nHKtlr7ucm7sXPJN8vLzh7Vz1TEgsSoP/3Z8buerRJGtLqqcpSi5uDryYk9VK8o6R/ptta6UG+TdWHzxRzsISTlwI35HreHuwq3uApQ+IHPe1wOTiA65M24keF+J5j/ADFWr9uoVPCEsCFO53JAGLibOOe+Ob7d2/OnTCUqKAltM2uPUF/LSFKa1TkqUXUXDG7l7cv8RLLSWR9E7Hr5AI8QfIEkegf2tFhExLWIbeY+XaDtCqRMxBaSv75UwPHfF47KdsjMmATphWdAMr9R+MX2J5XXqxCGxKBVuDHRrDVoUz6Ja8k4QeQPJgLc3hhs7acpQsseRd/5oNFYhVkKCiNQ1uu+LoqszYyGIUk2zViYHhmH84BrdkIsUpyzLpAb5xdFS0E4lALPHxeQ0gGu2OmZdQLbreyXeNI51tGjJGYt9m9up9oq215RYhi/53R1naGzJaBhSi54En/EU3beyAAWxPzy5xFcgrELCiCGaBgIsvaWRhs4AiuCIy1aMjfDGQDLvLwVTSn1gQzeEHUchRvFIOlKKcjB1Co4gWxX1gRaSRZN98R0a1IXcxK0vBnuiwDcLXiqbY2gsG2mnDnDZFeMOYHMRX9p1AVo33tPaIFq61wbsdxf5QsqqonO44xvVk7uohbMPWLEpvTqVNAw/wC8hLMCxmISLNvmJHVSQBmLxJAWHCsJ6YT6sD6fw5wsQtSWIJBBcEEuCMiCMjFhTMNSgzUpBnJBM1KQP3iRnOQB9YZrT/MLYgnTLzZyFJXgIYkYRcZuFJSX3qSkXGSobSUDC6VW1SdDwbLl6Qnp5iVgJU/BSD4k6/Ccxw8mg+oMxJEwMoLDuNSLLbXO7FiAQGdKoLoqXJCkTZSvD3gYK3LSykgk6E2fSK1UrwHDOBQtOYNv15iLDJqwqxAvvf8ATWJcAIycaZsM8n+ULNFSM0rUAAT5+Z/OkO0yUpQnJ+lw1vlDNCQnQAAdN9wIGppGMk6O3S5ifig6aQlRb6xOfF3HRg0HqThN9DY72AJ9vWMkyiCUh3YX4Bvm3rBID2zLkJzsd54C35MFDilU7EXyzdwGAg3vcKQQm93e75MBwuPTK0bppSCAqxuLDf8AgFRtNnq+A+G3hLOdxU+mfzhgTTJWqgznO5yGXD9YWz0+IABgp3ORYfVTuzixlLMSw4F92GE21qVaFBWHwgOCHNmBL+X5aFSnxn7IVKSj6AqWRmtMxSiVO+EqUL4kBR4EdRXJ1KJZUqSo92FMgnMjMO2rEDoYwTEm+Jxm3HLLfn5w+rKUSKSWlQHezF4wNQkBg40uW4xJ19fwmYWStq1JwgzpjagKOW6OldkO0YITLYBhxz5nWOaypTjjrf5xPJnlBDHC9s/m8Ma19DUtakhwTzbF6iCRWoFir+3DHP8AsnXgoGNRtbU+qrRZZk1B1xDr8rRpDeoqkKDJI5wh2lRG5dxoLfKDZE9I+ED2PV41qKniQNwb3gRyTtfsKdMNpSmzcgJH9RIAinTdkypf+9UJH3JP71XJ7IT/AFGOqdsKETElRCyzs5No5DWS2UdesRKk+mU4sKZxvXNXiPE4WHkIyAWjIiD5KXMOqZYSltYTyknSGNPKOZitN5yyMiYjkVJKha8STajDZsUQypge4YnQGJVOULGZz84B2hcfB1gqSJagyHB+8fmB7xlbIUEsHbenxDzjAqlWhQ5c4WrHCHtRIJJuFHdkfI59IVz6cpLEFJ1Bs3MRqVKAy4RNS1a5awtJKVAghSSxBGRB0MYtJ/SNEEboqLCV/SAVykgTc1yU+HE1zMkgZGzqljK5S4dKJNlbXwsmZiMpRcsxYsQJifvB+oca2S00sjxS1lJT4tXDfWdN7HXSGE+bj8UxkTFf8yLy5j/9QJshb/WAv9ZILqjWob1sooUDhQUqyUgFlWcNoXd+uucDTF5+G+4n2/IjWg2sAPo9QcKVfCoXwPfElrKS7lw4z0Jg4dm6jvMKg6ThwzUjElSTkQQXDgvqBxENXEAnFfhCLeHo4Ho8NqaWycKQ1vnvizbO7HTAkFTYiSxNrMp08bN58Imm7FYkFLtu6g/jGL06TlXJNIFXNn8Ljcwt5i4g2Ts8B7vvP+Dlyguv2aZeQIBPxBs8rje49RGIknQEjlv+ecalSwIqls5tua5Gh9LQOuR9bd6DdztlDhcoh7MxIPAtd4hOz8QUpmexYM/Ebt0LSQlnSQQ7EsTnnA3fkCzNuP4vDOqoVoFxmAMPqbcoWTVIdiCDlZ7eQb9Ic3UsxoKtKFYky5aVDJWBJN9zhxAqlFZKluo6quo+8FzKNCw1i3EfNngP9kzMTIINtXLNmcrN5CNMtpktFkseTgel4FVIzwIwjVRyHMnLk990FzAhAYjvFcCAgc1OFKbclhriMDDvJqmQ6ym7BmQDrZkoTa584JqbZ9eJZvMUriScI5Jb1PlF62HtoLSAWAyzZzu8TvHN5lUlBLkTFfZT8A/iX9fki33tI2pdpLcKJvo9gBuAFgOW+M1qV2yVOlHQAtoSP8ekSSKyWS2NQ5h/URQtkV3eDxMlXPyaLZsVaV2YE+7RFE7aoiZZKZo4ZiONbek/vFY1oBfQLP8A4x17bhTgKXAcGxsekcd20gYiMb8P/wBQSleCX/1D/R/mMiLvPuiMgycUErVnhiMRF7CAtkOSwg+ZIL5xWgsxI674i79IsA53xvVSiMr8YEDg/pEqmomhVxZRzTl/MOe78jRdSvLG45wAQo3t5iJETVGykhXIh/MfN450TzFOHLHnf3gf6YzJUnEkfVLKHQTApujRi5IOWIcx8xn5RFPpjk/mCPcRNo1XTUczNapCt7Eh/wCElXmZiRGk3s3ObFLMuol/alrS/K5ur7oKomkbEmLGI4Qj7Siw5AkXPAOeEH0ezJckhcpRXM+1iXKA5FIxqHVPKOkv9xMV5OzJqVABExKxcJUhSVWzIcXHk+6GNNshS0kpISXZSc0vucWT/Co+7CwzKycoYVKlYbnD3iJIvnZJwrPGYhUG7DKkl+7LWDhBYC+U6mSUBLE/8SBvMUKOz/ZlSlXS6HdvCQFaMCbEsbpY+x7bsHZaJUgBKQBmxAsSbjdn765xX9kSZYXiAsfiS4P/AGu3PW0WKm2gJZIV8JFnt04xFGTpoIw2cPAg2aCcR3HrG025StJGHz6cDnnCXtZ2h7qVNShYSpCAtR3FRZCWGpPtxjObcalyEHa6raYJcrxN8Rby6wL9Mws4LFi40YRX+zu01zZwSo4lFypTAvvDMw4R0NWxCW+BT9ON24R054uM3qaQmeSnxMQou9tYt1Nscd0khjZ34Zgc9IrtfSlBwqQ18wcQbytFk7G1veylJJuksRy4aRjuWNc2VHN2CJiDvZuv6Rz7tdsEyQDgUQHdg73sVXZs47LISE59Y12ns5E9BxJCgAW58d/K8OS184uENMnugEOiSLLWNCvSUniQ+4FoMlV0xSPElEqSfhchCDvIcFU08sRvoIN212fnomrWgSpIKlEzpomE8zOnJKU804fSEtd2YmBTz66ixKAuupUtRGhICFKI45R0lc6NXWUgGXfK+8cCH5JZS+bp5Qp2lWqmDCpTIBcS0AIQDvwJAST9434w9pOxstTD9o0qifqycU0+Rwk+US1PZylpwVVM6rCMrUZlEn7pnTL8wluMP1FLUh7AEk5ABzyG8w0o9lzkgEpASbutQQlO7xqIS/AEmG0vbNBL/wDjoqpdmK8EgzDv8cxcwI/kSmI0bToCrEqmqp6/t1FS5/tQPKHi+muzJyUgfvAo5fu0lbcHVhHk8W7ZMvD4mY/eIf8ApDN1iq0O0kH/AG5KkA6JmJT0JlyElXUmLdsukYA9wlAN3UpY91DF0BjKs7QzAZRKk4gBcA3845RX7P70lUnGr7rFRbpf0joPa3bM2nUAAhSDd8KeoDh+pjm239pmcbrWRnhUokdAbCBQRoSLGZKB3GYD/wBrj1jID7w74yIxq1bOWBBM0u4MAUZAHKC0Tjnh8406JkYQGwuYS1w8Ts0OVU09SXwYB9pZEsdCsh+kBT6WSm86oxH7MlKl/wBy8KR0eCFqTBFNTlRwoSVnckEn0iZFdJFpVOCftTlGZ/YnCnzBjyftCaoYVLOH7CWQn+lAA9I41UitmlP+5MTL+6DjV/Smw6kRn7RSi0tGP707x/0oHhT1xRAjhEc1L6tE0aVlWuYrEoqJ4qUW4O5YcAlo0kTQC5WkH+M/NMRKpyoskFROgcx6mgA+LPVILt/EoWHIOeUal0WCjnpWGxob/wCxB89YYbN2GgqxCYlO5YWEt6uYp02elNkkAD7IxHnmE9XJ5RPS7bCT8KlbsSvcJAP90Uda2elcpkirKxuK1KPQlz5FobftJWEhRWR97/Lxy+i7SKV9VKX4Ev8A1kw0XtJ0uoEajR+QGcS9C4Iq1pWlKVOhwc2z0yjlnavbJVPrklZvNQydMKGAAHCG/wC2lILJOV2/B4p3aGU85cwXCzi62f1hxfTr8C0m1VpLoUUkQ4pe2FWgjDOOeoBvvAiuolXKoxIjruMrvUdv5zpGLHZlFaQHfcAcucXf/TntMnwYj4p07u0ga+AlXv6RxibSghJdIzdiSq28EMIuP+l4VNr5IDiVT4pg/iIwgk7y58oW+XVkfRQlAlrNE/epYhMVv6QVKKsdj9V/lBv04JS5sPWMc1qq92p2QqaoqlBCV5d4Ay20CVWUOihHNq/vqdWAmbPP1jPmKKH4oWMuPeNvjp1XtGVMJ/eMoamx4WcvfeIpW2Jc2ZM/+RKWnIeIyz1CsI8o2yrP7XmoUTLFOknPu6aUkcgsoxHjfziKV2oq5QPdLTKfPBLlB+Ywe8WZWwlYXCXcZy3WOdiqKttCkYnIKG4fpE0yNJPaOYVFc6mo6gnNS6eUlXRUpKb83hnSVlNNLiWJCj9qnpahA692hSR/KqK7JUBYh24QzopCCX8SeMXTFw2dQTrGXMlrTulFMn+zCg/2w/p5ipd5iFJG9QPm7XhRsakSkAhQVzhpMqAgEhwWvhJHtEUm7YrkzpTBSSdH9uEco2nLKCxDcYvO2O0KiohE5aeC7j+6KlX7dqHbvlD+E4fZojPRXLo5qgCmVMIORCFEHkQLxkRzZqlEqUSScySSfMxkGPFxpZSRdlK5skeQcnzEFitUkgJAT/CGP9WfrA8iUoMVG0bTcAu55xXVBWrDla7niXMKZ0zFYCJ6wgglzAdNiGgaJRiAXZIgqWgxux4dI0UprC54Rzo9WtuUTS6cNiX4Ru+seT2A4n1iJJSi6rq3ZgfifSIjOKy5JA/OcQTzaqxSBhT9kHP+JWa/QQunqxZ5DIZDygpYtnEEy8NAEwcOka4N9oPlytTEc+W8WUZR1hSXHhA1zUeH6NFhpNqhWbYjqwfnFZ7v0jRKC9oeUW6op8YLKu2n5vCSegjwkWvnw3RpQ16gWBLDOGEmtC1MpLDTWJ+BGuQDe6RyeNE0eJ8Jy3mHM4IWGTnuyt9rc0ZLoUod1i59m6Rv6TC+TsSYosVoSN5L7/wjofZuTKpJWCQrxquteqiMugfIb4r9NstGHGT4swPO/UXiZe25aEJSgMGIBPDO/WJbqr9S7WY3F/J+XOMnbdJLoJO9BDny16Rzs9pFTR3ZZKh8KtDwO5/nzhbU7RXM8KiUqGSr/wBKtbb/AD4WQdDn9oKWeQ6+7UHci4za6FXIL74FrFhCgZRTNCrsLqI3pQbzOSX5xz3v1KLTgQvLvNf5yPjDZKufNwfs1OJJppx8Ki8teeBZ90q3fi8aQdtbaKkquk4TcEc7tqL/AOWNoHl7cmn/AJllP2V+Mf0rdMSIn1Mt5M9IqEaomEk8FS5o8abZXIG7SJj2b7xJXTEqAzlrZMxHl4Zg+8GfcIf4IpNYi2OVLVxAwekspHmIayKtAyTMSN/hmf8Aq3rC+i2MollJIORGUWCg2UUsxA4GJ+q32diJ8K+YFj0BYnpEu0lqQ+a/QiNlSgkHEGP5yhJtjbpSMIuNB+coor22lu5e3GK5OucmhjtSpCy4s+YgFKIMX1EER7BARGRNTFsEpx4l9BAtRKw5EmGcuV9gX3x4JDXUXMadCSoIzIc7oylSToOUTzqYqUdY1nJAAYsYgjnF7NGhmhIwoDnfGi0LIt1g2RLAS6myjIAQHLAYlamCTSqZgAN5iaStg4SOEeT61QsUiGAOakJzuYG70k5NE6VlSrho2lI3XifMEYlGMXTE8YK7sgRsk6AQwDyqS++MVIFxrDATGDCMkU7qeJgDk0mHTO8S/RyApXBh1z9H84OUoAtE85IAAbj5xcCQU7hxnG4kF1DQ3HPMe5HWCEJZTQQpDMTu9vyIYE6gXsbMAfQjlcCPESDhL87eR93hpNpmJIyN48SQlrWyPtFCoSwM4JVTuQXv7wTMpQTlGd0W5QweCSlSWNiMoLo6Nxe404RqJOIAiJ6YqT+EA5ppYmAJV8SfhO8bvz8zBtBJwkEFiMiIApZ+LRjDiQk/E3PnvihoJKVhyllcP/H/ANfKE20SwYFtx3wxXMU1oWbVIWDiYHfx0J/H9RqBTVV5KWVpqIqe0J9zd4LrahaFEEMd3z4iE9VMxaMYVm0IsuYkSIjAiVMc7UbhMZHsexBa6VR7vOBKlZfMxkZHVtHTGx5RBJ+KMjIgNSPDAU7WMjIlBFF8PnESxaMjIAVGSo1RkI8jIUHzcxHpEZGRBrKieSfFGRkQeT/ig9ecZGRYF3/IIIrPhHM+yYyMijSR8JgaXkY9jIgIknLkI9XnGRkAQgeGJkZiMjIoZyBDuhjIyLBPMyio7ZWcZDnKPYyKE+0704JuymD6C9huEV5cexkTplCRHojIyOVRtGRkZFH/2Q==";break;
//      }
      html+="<p>당신이 좋아하는 동물은 "+animal+"입니다.</p>";
      html+="<img src='"+src+"' width='200' height='200'>";
      html+="</body>";
      html+="</html>"; 
      
      out.print(html);
   }

   /**
    * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
    */
   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      // TODO Auto-generated method stub
      doGet(request, response);
   }

}
