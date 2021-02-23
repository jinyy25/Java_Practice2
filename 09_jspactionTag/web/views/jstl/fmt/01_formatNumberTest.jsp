<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>숫자표현하기</title>
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
</head>
<body>
	<h1>기본숫자표현하기</h1>
	<c:set var="numtest" value="12345019823"/>
	<c:set var="numtest2" value="19873000"/>
	<c:set var="numtest3" value="1"/>
	<c:set var="numtest4" value="124.567"/>
	
	<p> 저장된 기본 숫자출력하기 : ${numtest} </p>
	<p> 저장된 기본 숫자출력하기 : <c:out value="${ numtest}"/></p>
	<h3>fmt:formatNumber태그를 이용해서 출력하기</h3>
	<p><fmt:formatNumber value="${numtest }"/>원 </p>
	<p><fmt:formatNumber value="123456000"/> </p>
	<h4>fmt:formatNumber태그의 groupingUse속성 때문에 쉼표가 표시됨</h4>
	<p><fmt:formatNumber value="${numtest }" groupingUsed="false"/> </p>
	<p><fmt:formatNumber value="${numtest }" groupingUsed="true"/> </p>
	
	<h3>화폐관련 출력할때</h3>
	<p>type속성에 currency를 설정하면 화폐로 표시됨. *기준 : locale </p>
	<%=request.getLocale()%> 
	<p> <fmt:formatNumber value="${numtest2 }" type="currency"/>원 </p>
	<p> <fmt:formatNumber value="${numtest2 }" type="currency" currencySymbol=":))"/>Jyy </p>
	<fmt:setLocale value="ja_JP"/>
	<p> <fmt:formatNumber value="${numtest2 }" type="currency"/>엔 </p>
	<fmt:setLocale value="en_US"/>
	<p> <fmt:formatNumber value="${numtest2 }" type="currency"/>doller </p>
	
	<h3>퍼센트표시</h3>
	<p> 
		type속성에 percent설정하면 percent가 표시됨
		0 ~ 1 값 1==100%		
	</p>
	<p> <fmt:formatNumber value="${numtest3 }" type="percent"/> </p>
	<p> <fmt:formatNumber value="${0.5 }" type="percent"/> </p>
	
	
	<h3>숫자를 패턴으로 표시</h3>
	<p>
		자리수에 대한 패턴
		pattern 속성
		0 : pattern으로 지정한 자리에 수가 없으면 없는 자리에 0표시 (소수점 반올림처리)
		# : pattern으로 지정한 자리에 수가 없으면 없는 자리를 공란처리 (소수점 반올림처리)
	</p>
	<p> 0 : ${numtest4 } -> <fmt:formatNumber value="${numtest4 }" pattern="0,00.0"/> </p>
	<p> 0 : ${124.547} -> <fmt:formatNumber value="${124.547 }" pattern="0,00.0"/> </p>
	<p> 0 : ${numtest4 } -> <fmt:formatNumber value="${numtest4 }" pattern="0,000,000.00000"/> </p>
	<p> # : ${numtest4 } -> <fmt:formatNumber value="${numtest4 }" pattern="#,##.#"/> </p>
	<p> # : ${numtest4 } -> <fmt:formatNumber value="${numtest4 }" pattern="#,###,###.#####"/> </p>
	<p> 0# : ${numtest4 } -> <fmt:formatNumber value="${numtest4 }" pattern="#,###,###.00000"/> </p> <!-- 패턴섞기 가능 -->
	
	
	<h3>소수점 자리 표시하기</h3>
	<p>
		minFractionDigits : 최소소수점자리
		maxFractionDigits : 최대소수점자리
	</p>
	<p>최소소수점자리 : <fmt:formatNumber value="123.14124214241" pattern="#,###.######" minFractionDigits="5"/>  (minFractionDigits:무조건최소 다섯자리)</p>
	<p>최소소수점자리 : <fmt:formatNumber value="123.11231241241" pattern="#,###.###" maxFractionDigits="5"/>  (maxFractionDigits:무조건최대 다섯자리)</p>
	
	
	
	
	
	
	
	
	
	
</body>
</html>