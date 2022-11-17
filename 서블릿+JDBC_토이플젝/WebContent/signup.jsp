<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<html>
<head>
<meta charset="UTF-8">
<title>사용자정보사이트</title>
<style>
    label {
        display: inline-block;
        width: 80px;
    }
</style>
<%@ include file="/include/bootstrap.jsp" %>
</head>
<body>
<%--분리한 header.jsp 가져오기 --%>
	<%@ include file="include/header.jsp" %>
	<div class="container-sm text-center">
	    <p class="fs-3">회원가입</p>
	    <form method="post" action="main">
	        <input type="hidden" name="action" value="signup">
			<div class="row mb-3">
	        <label for="id" class="col col-sm-3">아이디</label>
	        <input type="text" id="id" name="id" class="form-control col"><br>
	        </div>
	        <div class="row mb-3">
	        <label for="password" class="col col-sm-3">비밀번호</label>
	        <input type="password" id="password" name="password" class="form-control col"><br>
	        </div>
	        <div class="row mb-3">
	        <label for="name" class="col col-sm-3">이름</label>
	        <input type="text" id="name" name="name" class="form-control col"><br>
	        </div>
	        <div class="row mb-3">
	        <label for="email" class="col col-sm-3">이메일</label>
	        <input type="email" id="email" name="email" class="form-control col">
	        </div>
	        
	        <br>
	        
	        <input type="submit" value="회원가입" class="btn btn-warning">
	        <input type="reset" value="초기화" class="btn btn-outline-warning">

	    </form>
    </div>
</body>
</html>