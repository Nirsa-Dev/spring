<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!doctype html>
<html lang="ko" class="h-100">
<head>
<%@ include file="../common/head.jsp" %>
    <link rel="canonical" href="https://getbootstrap.kr/docs/5.2/examples/checkout-rtl/">
    <!-- Custom styles for this template -->
    <link href="/resources/css/auth/registerForm.css" rel="stylesheet">
  </head>
  
  <body class="text-center">
<main>
    <div class="py-5 text-center">
      <img class="d-block mx-auto mb-4" src="https://getbootstrap.kr/docs/5.2/assets/brand/bootstrap-logo.svg" alt="" width="72" height="57">
    <h2>Sign up</h2>
    </div>

    <div class="row g-3">
      <div class="">
        <form action="/member/register.do" method="post" class="needs-validation" novalidate="">
          <div class="row g-3">
            <div class="col-12">
              <label for="email" class="form-label">이메일</label>
              <input type="email" class="form-control" name="email" id="email" onkeyup="checkEmail()" placeholder="you@example.com">
              <div class="invalid-feedback">
                이메일
              </div>
              <span id="emailMsg"></span>
            </div>

            <div class="col-12">
              <label for="userName" class="form-label">이름</label>
              <input type="text" class="form-control" name="name" id="userName" placeholder="이름을 작성해주세요." required="">
              <div class="invalid-feedback">
                이름
              </div>
            </div>
            
            <div class="col-12">
              <label for="userName" class="form-label">비밀번호</label>
              <input type="password" class="form-control" name="pwd" id="password" onkeyup="validatePassword()" value="" required="">
              <div class="invalid-feedback">
                비밀번호
              </div>
              <span id="pwdMsg">대소문자 1개 이상, 특수문자 1개 이상, 6~20자리</span>
            </div>
            
            <div class="col-12">
              <label for="passwordChk" class="form-label">비밀번호 확인</label>
              <input type="password" class="form-control" id="passwordChk" onkeyup="validatePassword()" required="">
              <div class="invalid-feedback">
                비밀번호 확인
              </div>
              <span id="pwdChkMsg"></span>
            </div>

          <hr class="my-4">

          <button class="w-100 btn btn-primary btn-lg" type="submit" id="submit-btn" disabled>제출</button>
        </form>
      </div>
    </div>
  </main>
</body>
</html>

<script>
let pwdFlag = false;
let pwdCheckFlag = false;
let emailFlag = false;

function flagCheck() {
	let submitBtn = document.getElementById('submit-btn');
    if (pwdFlag && emailFlag && pwdCheckFlag) {
        submitBtn.removeAttribute("disabled");
    } else {
        submitBtn.setAttribute("disabled", "true");
    }
}

// 비밀번호 유효성 검사
function validatePassword() {
	const passwordRegex = /^(?=.*[a-zA-Z])(?=.*[@$!%*?&\#])[A-Za-z\d@$!%*?&\#]{6,20}$/;
	const password = document.getElementById("password").value;
	const msg = document.getElementById("pwdMsg");
	
	const passwordChk = document.getElementById("passwordChk").value;
	const pwdChkMsg = document.getElementById("pwdChkMsg");
	
	if(passwordRegex.test(password)) {
		msg.innerHTML = "사용 가능한 비밀번호입니다.";
		msg.style.color = "green";
		pwdFlag = true;
	} else {
		msg.innerHTML = "패스워드 정책에 맞지 않습니다.";
		msg.style.color = "red";
		pwdFlag = false;
	}
	
	if(password === passwordChk) {
		pwdChkMsg.innerHTML = "패스워드가 동일합니다.";
		pwdChkMsg.style.color = "green";
		pwdCheckFlag = true;
	} else {
		pwdChkMsg.innerHTML = "패스워드가 동일하지 않습니다.";
		pwdChkMsg.style.color = "red";
		pwdCheckFlag = false;
	}
	
	flagCheck();
}

// 이메일 중복 확인 Ajax
function checkEmail() {
	const email = $("#email").val();
	const emailMsg = $("#emailMsg");
	const emailRegex = /^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\.[A-Za-z]{2,}$/;
	
	if(emailRegex.test(email)) {
		$.ajax({
			type: "POST",
			url: "/member/checkEmail.do",
			/* dataType: 'json',
			contentType : 'application/json', */
			data: { email : email },
			success : function(response) { 
				if(response == "available") {
					emailMsg.html("사용 가능한 이메일입니다.").css("color", "green");
					emailFlag = true;
				} else {
					console.log(response);
					emailMsg.html("이미 사용중인 이메일입니다.").css("color", "red");
					emailFlag = false;
				}
			},
			error : function() { }
		});
	} else {
		emailMsg.html("이메일 형식이 맞지 않습니다.").css("color", "red");
		emailFlag = false;
	}
	
	flagCheck();
}


</script>
