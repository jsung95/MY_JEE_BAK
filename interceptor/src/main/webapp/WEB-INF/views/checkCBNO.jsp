<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>



<!DOCTYPE html>

<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>


    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-migrate/3.3.2/jquery-migrate.min.js"></script>
    
    <script>
        
        function apiTest(){
            
            var input = document.getElementById('id').value; //텍스트박스 입력값을 변수에 담는다.
            document.getElementById('box').innerHTML = input;
            var send = { //전송할 데이터
                            "b_no": [
                                // "2150893029"
                                input
                            ]
                        };

            var sendData = JSON.stringify(send); // 전송할 데이터를 json형식으로 가공
            console.log(sendData);
            //ajax 시작
            $.ajax({
                
                //api url 인코딩키 넣었음.
                url: 'https://api.odcloud.kr/api/nts-businessman/v1/status?serviceKey=SaEtDV6V2ezASF4OroNIlotaYMp84VVz%2BYxZ%2BFvB9MlN%2F7DSCPLNnNRRTZrFKSffpip1B4BvtocfTS08%2F6Hm1Q%3D%3D',

                //post 방식
                type: 'POST',

                //api 서버로 전송할 데이터를 json형식으로 가공한다.
                data: sendData,
                // data: JSON.stringify(send),

                dataType: 'json',
                // crossDomain: true,
                // jsonp: "callback",

                contentType: "application/json", //수신할 데이터의 형식은 json

                success : function(data){
                    //호출 성공하면 작성할 내용
                    
                    if(data.document != 0 ){ // 값이 있으면
                        console.log(data);
                        console.log("API 상태코드 >>>>>>> : " + data['status_code']);

                        // (valid / invalid) 대신 match_cnt로 하면될듯
                        // 등록되지않은 사업자번호 조회시에는 match_cnt가 반환되지 않음
                        //      --> 따라서, match_cnt가 null이 아니면 등록된 사업자번호.
                        console.log("사업자등록번호가 매치되는게 있는지 >>>>>>> : " + data['match_cnt']);
                        if(data['match_cnt'] != null) {
                            console.log("매치 결과값 있음")
                            
                            
                            
							var answer = confirm("확인완료! 기업회원가입을 진행하시겠습니까?");
                            if(answer == true) {
                            	location.href = "/register/register?CBNO=" + input;
                            } else {
                            	alert('취소하셨습니다.');
                            }
                        } else {
                            console.log("매치 결과값 없음")
                            alert('일치하는 사업자등록번호가 없습니다.');
                        }

                        document.getElementById('box').innerHTML = input + " 상태 코드 = " + data['status_code'];

                        // 입력 사업자 번호에 대한 결과 값 JSON 출력
                        for(var aa of Object.keys(data)){ //키값으로 순회
                            var bb = data[aa];
                            console.log(aa + " >> " + bb);
                            
                        }
                    }
                },
                error:function(request,status,error){
                    // api 호출 과정에서 에러나면 경고창으로 에러 메세지 출력 
                    alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
                }
            });	
        }


    </script>
</head>
<body>

    <p>희용이형 사업자번호 2150893029</p>
    <p>사업자번호 입력하고 전송 누르면 콘솔에 결과 나옴</p>
    
    <input type="text" name="id" id="id">
    <input type="button" value="전송" onclick="apiTest();">


    <div id="box"></div>
    
</body>
</html>