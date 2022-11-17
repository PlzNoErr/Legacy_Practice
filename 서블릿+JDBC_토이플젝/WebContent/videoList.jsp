<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>

<html>
<head>
<meta charset="UTF-8">
<title>SSAFIT 영상 목록</title>
<style>
#user-list {
    border-collapse: collapse;
    width: 100%;
}

#user-list td, #user-list th {
    border: 1px solid black;
}
</style>
<%@ include file="/include/bootstrap.jsp"%>
</head>
<body>
    <%@ include file="/include/header.jsp"%>
    <c:if test="${!empty videos and fn:length(videos) > 0}">
        <div style="margin-left: 15px">
        <h3 class="card-title" style="display: inline-block; vertical-align: middle;">&nbsp가장 핫한 영상&nbsp&nbsp</h3>
        <a href="videoRegist.jsp" class="btn btn-primary btn-lg">영상등록</a>
        </div>
        
        <section style="margin-left: 15px">
            <div id="slides"
                style="display: flex; left: 0; top: 0; width: 2500px; transition: left 0.5s ease-out;">
                <c:forEach items="${videos}" var="video" begin="0" end="3" step="1">
                    <div class="card"
                        style="width: 380px; height: 400px; margin: 30px 10px;">
                        <a
                            href="main?action=detail&videoId=${video.videoId}&url=${video.url}">
                            <img class="card-img-top"
                            src="https://i1.ytimg.com/vi/${fn:substring(video.url,30,41)}/sddefault.jpg"
                            id="img1">
                        </a>
                        <div class="card-body">
                            <h5 class="card-title" id="title1">${video.title}</h5>
                            <p class="card-text" id="text1">조회수 : ${video.count}</p>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </section>
        <div style="margin:10px; text-align:center;">
        <a class="btn btn-outline-primary" href="main?action=showpart&part=full">전신</a>
        <a class="btn btn-outline-danger" href="main?action=showpart&part=up">상체</a>
        <a class="btn btn-outline-success" href="main?action=showpart&part=bottom">하체</a>
        <a class="btn btn-outline-info" href="main?action=showpart&part=core">코어</a>
        <a class="btn btn-outline-warning" href="main?action=showpart&part=etc">기타</a>
        </div>
        <section style="margin-left: 15px">
            <div id="slides"
                style="display: flex; left: 0; top: 0; width: 2500px; transition: left 0.5s ease-out;">
                <c:forEach items="${videos2}" var="video" begin="0" end="3" step="1">
                    <div class="card"
                        style="width: 380px; height: 400px; margin: 30px 10px;">
                        <a
                            href="main?action=detail&videoId=${video.videoId}&url=${video.url}">
                            <img class="card-img-top"
                            src="https://i1.ytimg.com/vi/${fn:substring(video.url,30,41)}/sddefault.jpg"
                            id="img1">
                        </a>
                        <div class="card-body">
                            <h5 class="card-title" id="title1">${video.title}</h5>
                            <p class="card-text" id="text1">조회수 : ${video.count}</p>
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