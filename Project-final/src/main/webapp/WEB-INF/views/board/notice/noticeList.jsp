<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!doctype html>
<html lang="ko" class="h-100">
<head>
<%@ include file="/WEB-INF/views/common/head.jsp"%>
</head>


<body class="d-flex flex-column h-100">
	<%@ include file="/WEB-INF/views/common/header.jsp"%>
	<!-- Begin page content -->
	<main class="flex-shrink-0">
		<div class="container">
			<h3>
				공지사항
				</h2>
				<hr>
				<br />
				<table class="table table-hover">
					<thead>
						<tr>
							<td>번호</td>
							<td>제목</td>
							<td>작성자</td>
							<td>작성일</td>
							<td>조회수</td>
						</tr>
					</thead>
					<tbody>

						<c:choose>
							<c:when test="${empty list}">
								<tr>
									<td colspan="5">
										<h3 class="text-center">등록된 글이 없습니다.</h3>
									</td>
								</tr>
							</c:when>
							<c:otherwise>
								<c:forEach var="item" items="${list }">
									<tr>
										<td>${row}</td>
										<td>${item.title}</td>
										<td>${item.writer}</td>
										<td>${item.indate}</td>
										<td>${item.views}</td>
									</tr>
									<c:set var="row" value="${row-1}" />
								</c:forEach>
							</c:otherwise>
						</c:choose>



					</tbody>
					<div class="d-grid gap-2d-md-flex justify-content-end float-right ">
						<button class="btn btn-dark me-md-2" type="button">글쓰기</button>
					</div>
				</table>
				<nav aria-label="Page navigation example ">
					<ul class="pagination justify-content-center">

						<c:choose>
							<c:when test="${pi.cpage eq 1}">
								<li class="page-item"><a class="page-link" href="#"
									aria-label="Previous"> <span aria-hidden="true">&laquo;</span>
								</a></li>
							</c:when>
							<c:otherwise>
								<li class="page-item"><a class="page-link"
									href="list.do?cpage=${pi.cpage-1}" aria-label="Previous"> <span
										aria-hidden="true">&laquo;</span>
								</a></li>
							</c:otherwise>
						</c:choose>

						<c:forEach var="page" begin="${pi.startPage}" end="${pi.endPage}">
							<li class="page-item"><a class="page-link"
								href="list.do?cpage=${page}">${page}</a></li>
						</c:forEach>

						<c:choose>
							<c:when test="${pi.cpage eq pi.maxPage}">
								<li class="page-item"><a class="page-link" href="#"
									aria-label="Next"> <span aria-hidden="true">&raquo;</span>
								</a></li>
							</c:when>
							<c:otherwise>
								<li class="page-item"><a class="page-link"
									href="list.do?cpage=${pi.cpage+1}" aria-label="Next"> <span
										aria-hidden="true">&raquo;</span>
								</a></li>
							</c:otherwise>
						</c:choose>

					</ul>
				</nav>
		</div>
	</main>
	<svg xmlns="http://www.w3.org/2000/svg" style="display: none;">
			<symbol id="bootstrap" viewBox="0 0 118 94">
			  <title>Bootstrap</title>
			  <path fill-rule="evenodd" clip-rule="evenodd"
			d="M24.509 0c-6.733 0-11.715 5.893-11.492 12.284.214 6.14-.064 14.092-2.066 20.577C8.943 39.365 5.547 43.485 0 44.014v5.972c5.547.529 8.943 4.649 10.951 11.153 2.002 6.485 2.28 14.437 2.066 20.577C12.794 88.106 17.776 94 24.51 94H93.5c6.733 0 11.714-5.893 11.491-12.284-.214-6.14.064-14.092 2.066-20.577 2.009-6.504 5.396-10.624 10.943-11.153v-5.972c-5.547-.529-8.934-4.649-10.943-11.153-2.002-6.484-2.28-14.437-2.066-20.577C105.214 5.894 100.233 0 93.5 0H24.508zM80 57.863C80 66.663 73.436 72 62.543 72H44a2 2 0 01-2-2V24a2 2 0 012-2h18.437c9.083 0 15.044 4.92 15.044 12.474 0 5.302-4.01 10.049-9.119 10.88v.277C75.317 46.394 80 51.21 80 57.863zM60.521 28.34H49.948v14.934h8.905c6.884 0 10.68-2.772 10.68-7.727 0-4.643-3.264-7.207-9.012-7.207zM49.948 49.2v16.458H60.91c7.167 0 10.964-2.876 10.964-8.281 0-5.406-3.903-8.178-11.425-8.178H49.948z"></path>
			</symbol>
		</svg>

	<%@ include file="/WEB-INF/views/common/footer.jsp"%>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4"
		crossorigin="anonymous"></script>


</body>
</html>
