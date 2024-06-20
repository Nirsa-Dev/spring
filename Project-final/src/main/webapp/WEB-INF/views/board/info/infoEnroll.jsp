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
			<h3>글쓰기</h3>
			<hr>
			<br />

			<form action="/info/enroll.do" method="post" enctype="multipart/form-data">
				<div class="mb-3 justify-content-center">
					<input type="text" class="form-control"
						name="title" id="exampleFormControlInput1" placeholder="제목을 입력하세요.">
				</div>
				<div id="marteditor mb-3 justify-content-center">
					<textarea name="content" id="editorTxt" class="form-control"
						rows="13" cols="10" placeholder="내용을 입력해주세요"></textarea>
				</div>
				
				<input type="file" name="upload"/>
				
				<div class="row">
					<div class="col text-center">
						<button type="button" class="btn btn-danger" onclick="history.back()">취소</button>
						<button type="submit" onclick="save()" class="btn btn-primary">작성</button>
					</div>
				</div>
			</form>
	</main>
	<%@ include file="../../common/footer.jsp"%>
</body>
</html>