<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>

<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-migrate/3.3.2/jquery-migrate.min.js"></script>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>

<script>
    var emailCheck;

	$(function() {
		
		$('#cbno').attr('readonly', true);
		
        
        $('.email_error').css('display','none');
        $('#sendBtn').on('click', function(){
            var isEmailOK = isEmail($('#email').val());
            if(isEmailOK == true) {
                $('#email_error').text('');
                $('.email_error').css('display','none');
                $.ajax({
                    url : "/register/sendMail",
                    type : "post",
                    data : {'email':$('#email').val()},
                    success : function(data){
                        console.log(data)
                        alert('메일발송완료');
                        $('#code').show(); // 숨겨져있던 인증번호 입력창 표시 
                        startTimer();
                        
                    }, error : function() {
                        alert('메일발송오류');
                    }//success-error
                });//ajax
            } else {
                $('.email_error').show();
                $('#email_error').css('color','red');
                $('#email_error').text('옳바른 이메일 형식을 입력해주세요.');
            }//if-else


        });//onclick
        
        
		$('#checkBtn').on('click', function() {
			
            var inputCode = $('#codeCheck').val();
			
			$.ajax({
                url : "/register/emailCertification",
                type : "POST",
                data : {'inputCode' : inputCode},

                success : function(result) {
                    console.log("코드체크결과 : " + result);
                    if(result == "ok") {
                        alert('인증완료');
                        emailCheck = result;

                        $('.email_error').css('display','none');
                        $('#email_error').text('');

                        $('#email').attr('readonly', true);
                        $('#codeCheck').attr('readonly', true);
                        $('#sendBtn').attr('disabled', true);
                        $('#checkBtn').attr('disabled', true);
                        
                        stopTimer();
                        $('#timer').css('color','RED');
                        
                    } else {
                        alert('인증실패, 옳바른 코드를 입력해주세요.');
                        
                    }//if-else
                }//success

            })//end ajax
			
		});


	});//end jq

    function isEmail(value){
        var regExp = /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-0-9a-zA-Z]([-_\.]?[0-0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i;
        return regExp.test(value);
    }//이메일 정규식 체크


    var mytimer;
	function startTimer() {
    	count = 70;
    	mytimer = setInterval(function() {
    		timer();
    	}, 1000);
    }
	function stopTimer() {
		clearInterval(mytimer);

        var inputCode = $('#codeCheck').val();
		$.ajax({
            url: "/register/removeSession",
            type: "POST",
            data : {'inputCode' : inputCode},
            success: function(result) {
            	console.log(result);
                if(result == "ok"){
                	alert('removed success!-이 알림코드 삭제해라')
                }
            }
        })//ajax

	}
    //var count = 20;
    
    function timer() {
        count--;

        if(count <= 0) {
            //clearInterval(counter);
            stopTimer();

            document.getElementById('timer').innerHTML = "0:00";
            
            return;
        }
        var min = Math.floor(count/60);
        var sec = count - (min*60);
        if(sec < 10){
        	sec = "0" + sec;
        }
        document.getElementById("timer").innerHTML = min + ":" + sec;

    }

	
	
    function execDaumPostcode() {
        new daum.Postcode({
            oncomplete: function(data) {
                // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

                // 각 주소의 노출 규칙에 따라 주소를 조합한다.
                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
                var addr = ''; // 주소 변수
                var extraAddr = ''; // 참고항목 변수

                //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
                if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                    addr = data.roadAddress;
                } else { // 사용자가 지번 주소를 선택했을 경우(J)
                    addr = data.jibunAddress;
                }

                // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
                if(data.userSelectedType === 'R'){
                    // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                    // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                    if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                        extraAddr += data.bname;
                    }
                    // 건물명이 있고, 공동주택일 경우 추가한다.
                    if(data.buildingName !== '' && data.apartment === 'Y'){
                        extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                    }
                    // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                    if(extraAddr !== ''){
                        extraAddr = ' (' + extraAddr + ')';
                    }
                    // 조합된 참고항목을 해당 필드에 넣는다.
                    //document.getElementById("extraAddress").value = extraAddr;
                
                } else {
                    //document.getElementById("extraAddress").value = '';
                }

                // 우편번호와 주소 정보를 해당 필드에 넣는다.
                document.getElementById('postcode').value = data.zonecode;
                document.getElementById("address").value = addr + extraAddr;
                
                document.getElementById("memberaddress").value = data.zonecode + " " + addr + extraAddr;
                // 커서를 상세주소 필드로 이동한다.
                document.getElementById("detailAddress").focus();
            }
        }).open();

    }


	$(function() {

        $('.file_error').css('display','none');
        $('#file').change(function(){
            if($('#file').val() == ""){
                $('.file_error').show();
                $('#file_error').css('color','red');
                $('#file_error').text('파일을 첨부해주세요.');
            } 

            else if($('#file').val() != "") {
                //============파일 확장자 검사==================//
                var ext = $('#file').val().split('.').pop().toLowerCase(); //파일 확장자만 추출해서 ext 변수에 저장 
                if($.inArray(ext, ['gif', 'png', 'jpg', 'jpeg']) == -1 ) {
                    alert('gif / png / jpg / jpeg 파일만 업로드 가능합니다.');
                    $('#file').val('');
                    return false;
                }

                //=========================================//
                
                //============파일 용량 검사==================//
                var maxSize = 2 * 1024 * 1024; // 2 MB
                var fileSize = $('#file')[0].files[0].size;
                if(fileSize > maxSize) {
                    alert('첨부파일 업로드는 2MB 이내로 가능합니다.');
                    $('#file').val("");
                    return false;
                }
                $('#file_error').text('');
                $('.file_error').css('display','none');
            }
            
        });

        //===============================================================================//
        $('.id_error').css('display','none');
        $('#memberid').keyup(function(){
            if($('#memberid').val().length < 6 || $('#memberid').val().length > 12) {
            	$('.id_error').show();
            	$('#id_error').css('color','red');
                $('#id_error').text('아이디는 6~12자여야 합니다.');
            } 
            else if(/[^a-z0-9]+|^([a-z]+|[0-9]+)$/.test($('#memberid').val())){
                    $('.id_error').show();
                    $('#id_error').css('color','red');
                    $('#id_error').text('아이디는 영문(소문자),숫자 조합으로 구성하여야 합니다.');
            }
            else {
                $('#id_error').text('');
                $('.id_error').css('display','none');
            }

        });//id_error
        //===============================================================================//
        $('.pw_error').css('display','none');
        $('#memberpw').keyup(function(){
            var pattern = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[$@$!%*#?&])[A-Za-z\d$@$!%*#?&]{6,12}$/;

            if($('#memberpw').val().length < 6 || $('#memberpw').val().length > 12) {
            	$('.pw_error').show();
            	$('#pw1_error').css('color','red');
                $('#pw1_error').text('암호는 6~12자여야 합니다.');
            } 
            else if (!pattern.test($('#memberpw').val())) {
                    $('.pw_error').show();
                    $('#pw1_error').css('color','red');
                    $('#pw1_error').text('암호는 영문/숫자/특수문자를 모두 포함해야 합니다.');
            }
            else {
                $('#pw1_error').text('');
                $('.pw_error').css('display','none');
            }

        });//암호유효성검사

        $('#memberpw2').keyup(function(){
            if($('#memberpw').val() != $('#memberpw2').val()){
                $('.pw_error').show();
                $('#pw2_error').css('color','red');
                $('#pw2_error').text('암호를 확인해주세요.');
            }
            else {
                $('#pw2_error').text('');
                $('.pw_error').css('display','none');
            }
        });//암호확인
        //===============================================================================//
        $('.name_error').css('display','none');
        $('#membername').keyup(function(){
            if($('#membername').val() == "" || $('#membername').val().length == 0) {
                $('.name_error').show();
                $('#name_error').css('color','red');
                $('#name_error').text('기업명을 입력해주세요.');
            }
            else {
                $('#name_error').text('');
                $('.name_error').css('display','none');
            }
        });//기업명확인

        //===============================================================================//
        $('.phone_error').css('display','none');
        $('#phone').keyup(function(){        
            var pattern = /^0([0-9]{1,2})-?([0-9]{3,4})-?([0-9]{4})$/;
            if(!pattern.test($('#phone').val())){
                $('.phone_error').show();
                $('#phone_error').css('color','red');
                $('#phone_error').text('옳바른 연락처를 입력해주세요.');
            }
            else {
                $('#phone_error').text('');
                $('.phone_error').css('display','none');
            }

        });//연락처확인
        //===============================================================================//
        
		$('.address_error').css('display','none');                
        $('#detailAddress').keyup(function(){
            if($('#detailAddress').val() == "" || $('#detailAddress').val().length == 0) {               
                $('.address_error').show();
                $('#address_error').css('color','red');
                $('#address_error').text('상세주소를 입력해주세요.');    
            } 
            else {
                $('#address_error').text('');    
                $('.address_error').css('display','none');
            }
        });









        //===============================================================================//
        //가입하기 버튼 눌렀을때.. 유효성 검사 
        $('#signup_btn').on('click', function(e){

            //파일 체크
            if($('#file').val() == ""){
                e.preventDefault();
                $('.file_error').show();
                $('#file_error').css('color','red');
                $('#file_error').text('파일을 첨부해주세요.');
            } 
            else {
                $('#file_error').text('');
                $('.file_error').css('display','none');
            }

            //아이디 체크
            if($('#memberid').val().length < 6 || $('#memberid').val().length > 12) {
                e.preventDefault();
            	$('.id_error').show();
            	$('#id_error').css('color','red');
                $('#id_error').text('아이디는 6~12자여야 합니다.');
            }
            else if(/[^a-z0-9]+|^([a-z]+|[0-9]+)$/i.test($('#memberid').val())){
                e.preventDefault();
            	$('.id_error').show();
                $('#id_error').css('color','red');
                $('#id_error').text('아이디는 영문,숫자 조합으로 구성하여야 합니다.');
            }
            else {
                $('#id_error').text('');
                $('.id_error').css('display','none');
            }

            //암호1 체크
            var pattern = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[$@$!%*#?&])[A-Za-z\d$@$!%*#?&]{6,12}$/;
            if($('#memberpw').val().length < 6 || $('#memberpw').val().length > 12) {
                e.preventDefault();
                $('.pw_error').show();
                $('#pw1_error').css('color','red');
                $('#pw1_error').text('암호는 6~12자여야 합니다.');
            } 
            else if (!pattern.test($('#memberpw').val())) {
                e.preventDefault();
                $('.pw_error').show();
                $('#pw1_error').css('color','red');
                $('#pw1_error').text('암호는 영문/숫자/특수문자를 모두 포함해야 합니다.');
            }
            else {
                $('#pw1_error').text('');
                $('.pw_error').css('display','none');
            }

            //암호2 체크
            if($('#memberpw').val() != $('#memberpw2').val()){
                e.preventDefault();
                $('.pw_error').show();
                $('#pw2_error').css('color','red');
                $('#pw2_error').text('암호를 확인해주세요.');
            }
            

            //기업명 체크
            if($('#membername').val() == "" || $('#membername').val().length == 0) {
                e.preventDefault();
                $('.name_error').show();
                $('#name_error').css('color','red');
                $('#name_error').text('기업명을 입력해주세요.');
            }
            else {
                $('#name_error').text('');
                $('.name_error').css('display','none');
            }

            //연락처 체크
            var pattern = /^0([0-9]{1,2})-?([0-9]{3,4})-?([0-9]{4})$/;
            if(!pattern.test($('#phone').val())){
                e.preventDefault();
                $('.phone_error').show();
                $('#phone_error').css('color','red');
                $('#phone_error').text('옳바른 연락처를 입력해주세요.');
            }
            else {
                $('#phone_error').text('');
                $('.phone_error').css('display','none');
            }

            //이메일 체크
            var pattern = /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-0-9a-zA-Z]([-_\.]?[0-0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i;
            if(!pattern.test($('#email').val())) {
                e.preventDefault();
                $('.email_error').show();
                $('#email_error').css('color','red');
                $('#email_error').text('옳바른 이메일 형식을 입력해주세요.');
            } else if(emailCheck != 'ok'){
                e.preventDefault();
                $('.email_error').show();
                $('#email_error').css('color','red');
                $('#email_error').text('이메일 인증이 필요합니다.');
            } else {
                $('.email_error').css('display','none');
                $('#email_error').text('');
            }


            //주소 체크
            $('.address_error').css('display','none');
            if( $('#postcode').val() == "" || $('#address').val() == "" ) {
                e.preventDefault();
                $('.address_error').show();
                $('#address_error').css('color','red');
                $('#address_error').text('주소를 입력해주세요.');
            }
            else if($('#detailAddress').val() == "" || $('#detailAddress').val().length == 0) {
            	e.preventDefault();
                $('.address_error').show();
                $('#address_error').css('color','red');
                $('#address_error').text('상세주소를 입력해주세요.');    
            }
            else {
                $('#address_error').text('');    
                $('.address_error').css('display','none');
                
                var address = $('#memberaddress').val();
                var detailAddress = $('#detailAddress').val();
                $('#memberaddress').val(address + " " + detailAddress);
            }



        });//signup_btn

    });//end jq
	
</script>

<body>
    <h1>회원가입</h1>
    <form action="/register/register" method="POST" enctype="multipart/form-data">
    	<input type="hidden" name="membertype" value="기업">
    	<input type="hidden" name="memberaddress" id="memberaddress">
        <fieldset>
            <legend>회원가입 양식</legend>
            <table>
                <thead>
                    <!-- =================== -->
                    <tr>
                        <th>사업자등록번호</th>
                        <td><input type="number" name="cbno" id="cbno" value="${param.cbno}"></td>
                        <th>사업자등록증 </th>
                        <td><input type="file" name="file" id="file"></td>
                    </tr>
                    <tr class="file_error">
                        <th>&nbsp;</th>
                        <td>&nbsp;</td>
                        <th>&nbsp;</th>
                        <td><div id="file_error" class="error"></div></td>
                    </tr>
                    <!-- =================== -->
                    <tr>
                        <th>아이디</th>
                        <td><input type="text" name="memberid" id="memberid"></td>
                    </tr>
                    <tr class="id_error">
                        <th>&nbsp;</th>
                        <td><div id="id_error"></div></td>
                    </tr>
                    <!-- =================== -->
                    <tr>
                        <th>암호</th>
                        <td><input type="password" name="memberpw" id="memberpw" autocomplete="off"></td>
                        <th>암호확인</th>
                        <td><input type="password" id="memberpw2" autocomplete="off"></td>
                    </tr>
                    <tr class="pw_error">
                        <th>&nbsp;</th>
                        <td><div id="pw1_error" class="error"></div></td>
                        <th>&nbsp;</th>
                        <td><div id="pw2_error" class="error"></div></td>
                    </tr>
                    <!-- =================== -->
                    <tr>
                        <th>기업명</th>
                        <td><input type="text" name="membername" id="membername"></td>
                    </tr>
                    <tr class="name_error">
                        <th>&nbsp;</th>
                        <td><div id="name_error" class="error"></div></td>
                    </tr>
                    <!-- =================== -->
                    <tr>
                        <th>연락처</th>
                        <td><input type="tel" name="phone" id="phone"></td>
                    </tr>
                    <tr class="phone_error">
                        <th>&nbsp;</th>
                        <td><div id="phone_error" class="error"></div></td>
                    </tr>
                    <!-- =================== -->
                    <tr>
                        <th>이메일</th>
                        <td><input type="email" name="email" id="email"><button type="button" id="sendBtn">sendmail</button></td>
                    </tr>
                    <tr id="code" style="display: none;"><!-- 인증 코드 -->
                    	<th>&nbsp;</th>
                    	<td><input type="text" name="codeCheck" id="codeCheck"><button type="button" id="checkBtn">checkbtn</button><span id="timer"></span></td>
                    </tr>
                    <tr class="email_error">
                        <th>&nbsp;</th>
                        <td><div id="email_error" class="error"></div></td>
                    </tr>
                    <!-- =================== -->
                    <tr>
                        <th>주소</th>
                        <td><input type="text" id="postcode" placeholder="우편번호" readonly><input type="button" onclick="execDaumPostcode()" value="우편번호 찾기"></td>
                    </tr>
                    <tr>
                    	<th>&nbsp;</th>
                    	<td><input type="text" id="address" placeholder="주소" readonly><br></td>
                    </tr>
                    <tr>
                    	<th>&nbsp;</th>
                    	<td><input type="text" id="detailAddress" placeholder="상세주소"></td>
                    </tr>    
                    <tr class="address_error">
                        <th>&nbsp;</th>
                        <td><div id="address_error" class="error"></div></td>
                    </tr>
                    <!-- =================== -->                
                    
                </thead>
                <tbody>
                	<tr>
                        <td><input type="submit" value="가입하기" id="signup_btn"></td>
                    </tr>
                </tbody>
            </table>
        </fieldset>
    </form>
    
</body>
</html>