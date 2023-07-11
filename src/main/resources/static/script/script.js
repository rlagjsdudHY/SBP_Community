/**
 * 
 */
 
$(function(){

 
 // 회원가입 시작
	$("button#joinBtn").click(function(){
		let uidVal = $("#userid").val();
		let pwdVal = $("#password").val();
		let emailVal = $("#email").val();
		
		if(uidVal == "" || pwdVal == "" || emailVal == "" ) {
			alert("가입 정보를 모두 입력하세요.");
			$("#userid").focus();
			return false;
		} else {
		
			let user = { 
				userid: uidVal, 
				password: pwdVal, 
				email: emailVal 
			}
			alert(user.email);  	// alert() 출력은 객체의 항목을 지정해야 합니다.
			console.log(user);
			
			
			$.ajax({
				type: "POST",
				url: "/join",
				data: JSON.stringify(user),
				contentType: "application/json; charset=utf-8"
			}).done(function(response){
				console.log(response);
				location="/";     // 주석처리 하면 처리결과를 
						  // Console에서 확인할 수 있습니다.
			}).fail(function(error){
				alert("에러 발생 : " + error);
			});
			
			
		} 
	});
	
	
	// 로그인 시작	
	$("button#loginBtn").click(function(){
		let uidVal = $("#userid").val();
		let pwdVal = $("#password").val();
		
		if(uidVal == "" || pwdVal == "") {
			alert("ID와 PW를 모두 입력하세요.");
			$("#userid").focus();
		} else {
		
			let loginData = { 
				userid: uidVal, 
				password: pwdVal
			}
			
			console.log(loginData);
			
			$.ajax({
				type: "POST",
				url: "/login",
				data: JSON.stringify(loginData),
				contentType: "application/json; charset=utf-8"
			}).done(function(response){
				// 데이터 전송 정상실행
				location="/";     
			}).fail(function(error){
				alert("오류가 발생했습니다.");
			});			
			
		}    // End if
	});
	
	//	글쓰기	저장	시작
			$("button#writeBtn").click(function() {
			let titleVal = $("#title").val();
			let contentVal = $("#content").val();
			if (titleVal == "") {
				alert("글	제목을	입력하세요.");
				$("#title").focus();
			} else {
				let writeData = {
					title : titleVal,
					content : contentVal
				}
				console.log(writeData);
				$.ajax({
					type : "POST",
					url : "/write",
					data : JSON.stringify(writeData),
					contentType : "application/json;	charset=utf-8"
				}).done(function(response) {
					location = "/"; //	데이터	전송	정상실행
				}).fail(function(error) {
					alert("오류가	발생했습니다.");
				});
			} //	End	if
		});

});