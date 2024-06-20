<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html lang="ko" class="h-100">
<head>
<%@ include file="../../common/head.jsp"%>
<%@ include file="../../common/smarteditor.jsp"%>

</head>
<body class="d-flex flex-column h-100">
	<!-- Fixed navbar -->
	<%@ include file="../../common/header.jsp"%>
	<!-- Begin page content -->
	<main class="flex-shrink-0">
		<div class="container">
			<h3>상세보기</h3>
			<hr>
			<br />
				<div class="mb-3 justify-content-center">
					<input type="text" class="form-control"
						name="title" id="exampleFormControlInput1" value="${info.title }" placeholder="제목을 입력하세요." readonly>
				</div>
				<%-- <div id="marteditor mb-3 justify-content-center">
					<textarea name="content"
						rows="13" cols="10" placeholder="내용을 입력해주세요">${free.content}</textarea>
				</div> --%>
				
				<div>
					${info.content }
					<img src="/resources/uploads/info/${info.uploadName }">
				</div>
				
				<div class="row">
					<div class="col text-center">
						<button type="button" class="btn btn-danger" onclick="history.back()">취소</button>
						<c:if test="${sessionScope.memberName eq info.writer}">
							<button type="submit" onclick="location.href='/info/editForm.do?boardIdx=${info.idx}'" class="btn btn-primary">수정</button>
							<button type="submit" onclick="location.href='/info/delete.do?boardIdx=${info.idx}'" class="btn btn-primary">삭제</button>
						</c:if>
					
					</div>
				</div>
	</main>
	<%@ include file="../../common/footer.jsp"%>
</body>
</html>