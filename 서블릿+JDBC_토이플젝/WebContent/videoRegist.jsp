<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
<head>
<meta charset="EUC-KR">
<title>영상 등록</title>
<%@ include file="/include/bootstrap.jsp"%>
</head>
<body>
    <%@ include file="/include/header.jsp"%>
    <div style="margin: 20px">
        <form method="post" action="main">
            <fieldset>
                <legend>영상 등록하기</legend>
                <input type="hidden" name="action" value="videoRegist"> 
                <div style="margin:5px;">
                <label style="display:inlineblock; width:50px;" for="url">URL</label> <input type="text" id="url" name="url" placeholder="https://www.youtube.com"> 
                </div>
                <div style="margin:5px 5px 15px 5px;">
                <label style="display:inlineblock; width:50px;" for="title">제목</label> <input type="text" id="title" name="title">
                </div>
                
                <select class="form-select" id="floatingSelect"
                    aria-label="Floating label select example" name="part">
                    <option selected>운동부위</option>
                    <option value="전신">전신</option>
                    <option value="상체">상체</option>
                    <option value="하체">하체</option>
                    <option value="코어">코어</option>
                    <option value="기타">기타</option>
                </select> 
                <br>
                <label for="videoContent" class="form-label">영상설명</label>
                <textarea style="margin:0px 5px 15px 5px;"rows="10" class="form-control" id="videoContent" name="videoContent"></textarea>
                <input class="btn btn-outline-primary" type="submit" value="등록"> <input class="btn btn-outline-danger" type="reset" value="초기화">
            </fieldset>
        </form>
    </div>
</body>
</html>