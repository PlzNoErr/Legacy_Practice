<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%@ include file="/include/bootstrap.jsp" %>
</head>
<body>
	<%@ include file="include/header.jsp" %>
	<!-- 시작화면 -->
	<div class="text-center">
		<a href="main?action=list" class="btn btn-dark">영상 목록</a>
		<a href="videoRegist.jsp"  class="btn btn-dark">영상 등록</a>		
	</div>
</body>
</html>