<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>KPI Pool Web Page</title>
  <!-- 부트스트랩 CDN 추가 -->
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

<div class="container mt-5">
  <h1 class="mb-4">KPI Pool</h1>

  <table class="table">
    <thead>
      <tr>
        <th>SUBKPI</th>
        <th>KPINAME</th>
        <th>MAINKPI</th>
      </tr>
    </thead>
    <tbody>
      <c:forEach var="item" items="${result }">
	      <tr>
	        <td>${item.subKpi}</td>
	        <td>${item.kpiName}</td>
	        <td>${item.mainKpi}</td>
	      </tr>
      </c:forEach>
    </tbody>
  </table>
</div>

<!-- 부트스트랩 자바스크립트 및 팝퍼 추가 -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>