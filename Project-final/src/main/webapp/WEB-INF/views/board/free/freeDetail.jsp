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
				<!-- 타이틀 컴포넌트 : components/board/title.vue -->
				<div class="mb-3 justify-content-center">
					<input type="text" class="form-control"
						name="title" id="exampleFormControlInput1" value="${free.title }" placeholder="제목을 입력하세요." readonly>
				</div>
		            <div class="row mb-3">
		                <div class="col-md-4">
		                    <p class="fw-bold">조회수: ${free.views}</p>
		                </div>
		                <div class="col-md-4">
		                    <p class="fw-bold">작성자: ${free.writer}</p>
		                </div>
		                <div class="col-md-4">
		                    <p class="fw-bold">카테고리: 3</p>
		                </div>
		            </div>
		            
		        <!-- 타이틀 컴포넌트 : components/board/content.vue -->
				<div>
					${free.content }
					<img src="/resources/uploads/free/${free.uploadName }">
				</div>
				
				<div class="row">
					<div class="col text-center">
						<button type="button" class="btn btn-danger" onclick="history.back()">취소</button>
						<c:if test="${sessionScope.memberName eq free.writer}">
							<button type="submit" onclick="location.href='/free/editForm.do?boardIdx=${free.idx}'" class="btn btn-primary">수정</button>
							<button type="submit" onclick="location.href='/free/delete.do?boardIdx=${free.idx}'" class="btn btn-primary">삭제</button>
						</c:if>
					</div>
				</div>
	</main>
	<%@ include file="../../common/footer.jsp"%>
</body>
</html>