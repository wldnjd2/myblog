<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
    String ctxPath = (String) request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<link href="https://cdnjs.cloudflare.com/ajax/libs/semantic-ui/2.2.2/semantic.min.css" rel="stylesheet" type="text/css">
	<title>회원가입</title>
	
	<!-- 시멘틱 UI 조건 -->
	<script
	  src="https://code.jquery.com/jquery-3.1.1.min.js"
	  integrity="sha256-hVVnYaiADRTO2PzUGmuLJr8BLUSjGIZsDYGmIJLv2b8="
	  crossorigin="anonymous"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/semantic-ui/2.2.2/semantic.min.js"></script>
	
	<!-- insertUser.js파일, css파일 연결 -->
	<link href="<c:url value='/'/>css/account/insertUser.css" rel="stylesheet" type="text/css" >
	<script type="text/javascript" src="<c:url value='/'/>js/account/insertUser.js"></script>
	
</head>
<body>
	<div id="wrap">
		<div id="header">
			<h1>회원가입</h1>
		</div>
		<form name = "insertUser" id="frm" enctype="multipart/form-data" >
			<div class="ui form success error">
				<div id="main_left">
					 <div class="field" id="image_field">
	                    <label>대표 이미지</label>
	                    <label for="uploadFile" id="image_field_upload">업로드</label>
	                    <input type="file" id="uploadFile" accept="image/jpeg, image/jpg, image/png" onchange="toBase64(event);">
	                    <img class="thumb" src="<c:url value='/'/>images/라이언 사진.png" style="width: 200px; height: 200px;">
	                    <a href="javascript:void(0);" class="dellink">파일 삭제</a>
	                    <input type="hidden" id="user_img" name="user_img" >
	                </div>
				</div>
	
				<div id="main_right">
					<div class="field">
						<label>아이디 *</label> 
						<input type="text" id="inputID" name="user_id" placeholder="ex) HongGilDong">
						<button class="ui primary button fr" id="btn_idcheck">중복확인</button>
						<div class="cjeck_font" id="id_check"></div>
					</div>
	
					<div class="field">
						<label>이름 *</label> 
						<input type="text" id="user_name" name="user_name" placeholder="ex) 홍길동">
					</div>
	
					<div class="field">
						<label>비밀번호 *</label>
						<input type="password" id="user_pw" name="user_pw" 
							placeholder="ex) 비밀번호는 영문, 숫자, 특수문자 조합으로 6~20 자리를 사용" onchange="check_pw()">
					</div>
	
					<div class="field">
						<label>비밀번호 확인 *</label> 
						<input type="password" id="user_pw_collect" onchange="check_pw()">
						<span id="check"></span>
					</div>
	
					<div class="field">
						<label>이메일</label> 
						<input type="email" id="user_email" name="user_email" placeholder="ex) naver@naver.com">
					</div>
	
					<div class="field">
						<label>전화번호</label> 
						<input type="text" id="user_pnum" name="user_pnum" placeholder="ex) 010-1234-5678" maxlength='13'>
					</div>
	
					<div class="field">
						<label>생년월일 *</label> 
						<input class="user_birth" type="text" oninput="this.value = this.value.replace(/[^0-9.]/g, '').replace(/(\..*)\./g, '$1');"
							id="user_year" name="user_year" placeholder=0000 maxlength='4'>/
						<input class="user_birth" type="text" oninput="this.value = this.value.replace(/[^0-9.]/g, '').replace(/(\..*)\./g, '$1');"
							id="user_month" name="user_month" placeholder=00 maxlength='2'>/
						<input class="user_birth" type="text" oninput="this.value = this.value.replace(/[^0-9.]/g, '').replace(/(\..*)\./g, '$1');"
							id="user_day" name="user_day" placeholder=00 maxlength='2'>
						<br><p>*로 표시된 곳은 필수 입력칸 입니다.</p>
					</div>
				</div>
			</div>
	
			<div class="clear"></div>
	
			<div id="footer">
				<button type="button" class="ui submit button" id="btn_insertUser">회원가입</button>
				<a class="ui button" href="<c:url value="/login/logIn.do"/>">취소</a>
			</div>
		</form>
	</div>
</body>
</html>