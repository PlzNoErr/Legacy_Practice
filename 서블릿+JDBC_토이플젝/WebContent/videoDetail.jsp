<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>영상 상세 화면</title>
<%@ include file="/include/bootstrap.jsp"%>
</head>
<body>
<%@ include file="/include/header.jsp"%>
	<div class="col-lg-6 mx-auto py-5">
		<div class="mb-3 text-center">
			<h5>${video.title}</h5>
		</div>
		<div class="mb-3 text-center">
			<iframe width="560" height="315"
				src="https://www.youtube.com/embed/${fn:substring(video.url,30,41)}"
				title="YouTube video player" frameborder="0"
				allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture"
				allowfullscreen></iframe>
		</div>
		<div class="mb-3 d-flex justify-content-around">
			<div>
				<div class="row">	
					<span class="fs-5 col col-sm-7">
					영상 설명
					</span>
					<c:if test="${empty loginUser}">
					<div class="col col-sm-5">
					<form method="post" action="main">
						<input type="hidden" name="action" value="gosignup"> 
						<input type="submit" value="로그인 후 찜하기" class="btn btn-outline-danger">
					</form>
					</div>
					</c:if>
					<c:if test="${!empty loginUser}">
					<div class="col col-sm-5">
						<form method="post" action="main">
							<input type="hidden" name="action" value="addLike"> 
							<input type="hidden" name="videoId" value="${video.videoId}">
							<input type="hidden" name="url" value="${video.url}">
							<input type="submit" value="찜하기" class="btn btn-outline-danger">
						</form>
					</div>
					</c:if>
				</div>
				<br>
				<p>${video.content}</p>
			</div>
			<div>
				<form method="post" action="main">
					<input type="hidden" name="action" value="removeVideo"> 
					<input type="hidden" name="videoId" value="${video.videoId}">
					<input type="submit" value="영상 삭제하기" class="btn btn-dark">
				</form>
			</div>
		</div>
		<hr>
		<div class="container">
			<form action="main" method="post">
				<input type="hidden" name="action" value="addReview"> 
				<input type="hidden" name="url" value="${video.url}"> 
				<input type="hidden" name="videoId" value="${video.videoId}">

				<div class="row mx-auto">
					<div class="col-sm-12 d-flex justify-content-between">
						<h3>댓글 작성</h3>
						<div>
							<label for="writer">작성자</label> 
							<input type="text" id="writer" name="writer"><br>
						</div>
					</div>
					<div class="col-sm-10">
						<textarea class="form-control" id="content" name="content"></textarea>
					</div>
					<div class="col-sm-2 my-auto">
						<input type="submit" value="저장" class="btn btn-dark">
					</div>
				</div>
			</form>
			<hr>
			<c:if test="${!empty reviews}">
				<h3 class="row mx-auto">댓글 목록</h3>
				<table class="table">
					<thead>
						<th>번호</th>
						<th>내용</th>
						<th>작성자</th>
						<th>수정</th>
						<th>삭제</th>
					</thead>
					<tbody>
						<c:forEach items="${reviews}" var="review" varStatus="varStatus">
							<tr>
								<td>${varStatus.count}</td>
								<td>${review.content}</td>
								<td>${review.writer}</td>
								<td>
									<button type="button" class="btn btn-outline-dark"
										data-bs-toggle="modal" data-bs-target="#staticBackdrop${varStatus.count}"
										id="modi-btn">리뷰수정</button> 
										<!-- Modal -->
									<div class="modal fade" id="staticBackdrop${varStatus.count}"
										data-bs-backdrop="static" data-bs-keyboard="false"
										tabindex="-1" aria-labelledby="staticBackdropLabel"
										aria-hidden="true">
										<div class="modal-dialog">
											<div class="modal-content">
												<div class="modal-header">
													<h5 class="modal-title" id="staticBackdropLabel">리뷰 수정하기</h5>
													<button type="button" class="btn-close"
														data-bs-dismiss="modal" aria-label="Close"></button>
												</div>
												<form action="main" method="post">
													<input type="hidden" name="action" value="reviewModify">
													<input type="hidden" name="reviewId" value="${review.reviewId}">
													<input type="hidden" name="url" value="${review.url}">
													<input type="hidden" name="url" value="${video.url}"> 
													<input type="hidden" name="videoId" value="${video.videoId}">
													<div class="modal-body">
														<div class="mb-3">
															<label for="witer" class="form-label">작성자</label> 
															<input type="text" class="form-control" id="writer" name="writer" value="${review.writer}">
														</div>
														<div class="mb-3">
															<label for="InputContent" class="form-label">리뷰 내용</label>
															<input type="text" class="form-control" id="InputContent" name="content" value="${review.content}">
														</div>
													</div>
													<div class="modal-footer">
														<button type="submit" class="btn btn-dark" id="save"
															data-bs-dismiss="modal">수정하기</button>
														<button type="button" class="btn btn-outline-dark"
															data-bs-dismiss="modal">취소</button>
													</div>
												</form>
											</div>
										</div>
									</div>
								</td>
								<td>
									<form action="main" method="post">
										<input type="hidden" name="action" value="reviewDelete">
										<input type="hidden" name="url" value="${video.url}"> 
										<input type="hidden" name="videoId" value="${video.videoId}">
										<input type="hidden" name="reviewId" value="${review.reviewId}">
										<input type="submit" class="btn btn-dark" value="리뷰삭제">
									</form>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</c:if>
		</div>
	</div>

</body>
</html>