<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>마이 페이지</title>
<%@ include file="/include/bootstrap.jsp"%>
</head>
<body>
	<%@ include file="include/header.jsp"%>
	<!-- 시작 -->
	<c:if test="${!empty videos and fn:length(videos) > 0}">
		<div style="margin-left: 15px">
			<h3 class="card-title"
				style="display: inline-block; vertical-align: middle;">&nbsp;찜한 영상&nbsp;&nbsp;</h3>
		</div>

		<section style="margin-left: 15px">
			<div id="slides"
				style="display: flex; left: 0; top: 0; width: 2500px; transition: left 0.5s ease-out; flex-wrap: wrap;">
				<c:forEach items="${videos}" var="video" begin="0" end="3" step="1">
					<div class="card"
						style="width: 380px; height: 400px; margin: 30px 10px;">
						<a href="main?action=detail&videoId=${video.videoId}&url=${video.url}">
							<img class="card-img-top"
							src="https://i1.ytimg.com/vi/${fn:substring(video.url,30,41)}/sddefault.jpg"
							id="img1">
						</a>
						<div class="card-body">
							<h5 class="card-title" id="title1">${video.title}</h5>
							<div class="d-flex">
								<div class="card-text" id="text1">조회수 : ${video.count}</div>
								&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
								<a href="main?action=removeLike&videoId=${video.videoId}&url=${video.url}" class="btn btn-danger">찜 삭제</a>
							</div>
						</div>
					</div>
				</c:forEach>
			</div>
		</section>
				<section style="margin-left: 15px">
			<div id="slides"
				style="display: flex; left: 0; top: 0; width: 2500px; transition: left 0.5s ease-out; flex-wrap: wrap;">
				<c:forEach items="${videos}" var="video" begin="4" end="7" step="1">
					<div class="card"
						style="width: 380px; height: 400px; margin: 30px 10px;">
						<a href="main?action=detail&videoId=${video.videoId}&url=${video.url}">
							<img class="card-img-top"
							src="https://i1.ytimg.com/vi/${fn:substring(video.url,30,41)}/sddefault.jpg"
							id="img1">
						</a>
						<div class="card-body">
							<h5 class="card-title" id="title1">${video.title}</h5>
							<div class="d-flex">
								<div class="card-text" id="text1">조회수 : ${video.count}</div>
								<a href="main?action=removeLike&videoId=${video.videoId}&url=${video.url}" class="btn btn-danger">찜 삭제</a>
							</div>
						</div>
					</div>
				</c:forEach>
			</div>
		</section>
	</c:if>
	<c:if test="${empty videos or fn:length(videos) == 0}">
		<div class="alert alert-danger" role="alert">영상이 없습니다.</div>
	</c:if>
</body>
</html>