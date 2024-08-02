<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<style>
	table td{
		padding: 10px;
	}

</style>
<script src="https://code.jquery.com/jquery-3.7.1.js"></script>
<body>
	<jsp:include page="../common/menubar.jsp"/>
		<br>
	<div class="outer">

		<h2 align="center">회원가입</h2>
		<form action="insert.me" method="post" id="enrollForm">
			<table align="center">
				<tr>
					<td>* ID</td>
					<td>
						<input type="text" name="userId" id="userId" required>
						<div id="checkResult" style="font-size:0.8em;" ></div>
					</td>
				</tr>
				<tr>
					<td>* PWD</td>
					<td>
						<input  name="userPwd"required>
					</td>
				</tr>
				<tr>
					<td>* NAME</td>
					<td>
						<input  name="userName" required>
					</td>
				</tr>
				<tr>
					<td>* EMAIL</td>
					<td>
						<input  name="email">
					</td>
				</tr>
				<tr>
					<td> BIRTHDAY</td>
					<td>
						<input name="birthday" placeholder="생년월일(6자리)">
					</td>
				</tr>
				<tr>
					<td> GENDER</td>
					<td>
						<input type="radio" name="gender" value="M">남&emsp;
						<input type="radio" name="gender" value="F">여&emsp;
					</td>
				</tr>
				<tr>
					<td>PHONE</td>
					<td>
						<input  name="phone">
					</td>
				</tr>
				<tr>
					<td>ADDRESS</td>
					<td>
						<input  name="address">
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<input type="reset"value="초기화"> &emsp;&emsp;
						<input type="submit"value="회원가입">
					</td>
				</tr>
			</table>
			
		</form>
	</div>
		
	</body>
	<script>
		$(()=>{
			const $idInput=$('#userId');
			$idInput.keyup(function(){
				if($idInput.val().length>=5){
					$.ajax({
						url:"idCheck.me",
						data: {
							id : $idInput.val()
						},
						
						success : function(result){
							console.log(result);
							if(result=="true"){								
								$("#checkResult").show();
								$("#checkResult").css({color:"blue"}).text("사용 가능한 아이디입니다.");
								$("#enrollForm :submit").attr("disabled", false);
							}else{
								$("#checkResult").show();
								$("#checkResult").css({color:"red"}).text("해당 아이디는 사용 불가능합니다.");
								$("#enrollForm :submit").attr("disabled", true);
							}
						},
						error : function(){
							console.log("idCheck.me ajax 통신 실패");
						}
					})
				}
			})
			
		})
	</script>
	</html>