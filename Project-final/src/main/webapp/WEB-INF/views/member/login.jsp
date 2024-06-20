<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!doctype html>
<html lang="ko" class="h-100">
<head>
<%@ include file="../common/head.jsp" %>
    <link rel="canonical" href="https://getbootstrap.kr/docs/5.2/examples/sign-in/">
    <!-- Custom styles for this template -->
    <link href="/resources/css/member/login.css" rel="stylesheet">
  </head>
 
  <body class="text-center">
<main class="form-signin w-100 m-auto">

  <form action="/member/login.do" method="post">
    <img class="mb-4" src="https://getbootstrap.kr/docs/5.2/assets/brand/bootstrap-logo.svg" alt="" width="72" height="57">
    <h1 class="h3 mb-3 fw-normal">Please sign in</h1>
    <div class="form-floating">
      <input type="email" class="form-control" id="floatingInput" name="email" placeholder="name@example.com">
      <label for="floatingInput">Email address</label>
    </div>
    <div class="form-floating">
      <input type="password" class="form-control" id="floatingPassword" name="pwd" placeholder="Password">
      <label for="floatingPassword">Password</label>
    </div>
    
    <a href="/member/registerForm.do">Register</a>
    <!-- <div class="checkbox mb-3">
      <label>
        <input type="checkbox" value="remember-me"> Remember me
      </label>
    </div> -->
    
    
    <button class="w-100 btn btn-lg btn-primary" type="submit">Sign in</button>
    <p class="mt-5 mb-3 text-muted">© 2017–2023</p>
  </form>
</main></body>
</html>