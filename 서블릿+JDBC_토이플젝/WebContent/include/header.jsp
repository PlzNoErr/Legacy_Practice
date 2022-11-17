<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="dto.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<style>
#user-info {
	text-align: right;
}
</style>
<div class="p-3 text-bg-dark">
	<div class="d-flex justify-content-between">
		<div class="my-auto fs-4">
			<a href="${pageContext.request.contextPath}/index.jsp" class="my-auto mb-lg-0 text-white text-decoration-none">SSAFIT</a>
		</div>
		<div id="user-info">
			<%-- session에서 loginUser를 가져와서 존재 여부에 따라 로그인 폼 또는 사용자 정보를 출력한다. --%>
			<c:if test="${empty  loginUser}">
				<form method="post" action="main" class="col-12 col-lg-auto mb-3 mb-lg-0 me-lg-3" >
					<input type="hidden" name="action" value="login"> 
					<div style="display: inline-block; vertical-align: middle;">
					<input type="text" name="userid" placeholder="아이디"> 
					<input type="password" name="userpass" placeholder="비밀번호"> 
					</div>
					<input type="submit" value="로그인" class="btn btn-outline-light ms-1" >
					<button type="button" onclick="location.href='${pageContext.request.contextPath}/signup.jsp'"class="btn btn-outline-warning" >회원가입</button>
				</form>
			</c:if>
			<c:if test="${not empty  loginUser}">
				<div style="display: inline-block; vertical-align: middle;">
					<span style="display: inline-block; vertical-align: middle;">${loginUser.userName} 님 반갑습니다.</span>
					<a href="main?action=logout" class="btn btn-outline-warning">로그아웃</a>
					<a href="main?action=myPage&userId=${loginUser.userId}" class="btn btn-outline-danger">찜 목록</a>
				</div>
			</c:if>
		</div>
	</div>
</div>
<hr>
<script>
	// request 영역에 msg라는 이름의 attribute가 있다면 화면에 alert으로 출력한다.
	let msg = "${msg}";
	if (msg) {
		alert(msg)
	}
</script>