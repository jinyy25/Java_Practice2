<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>화상채팅하기!!</title>
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<style>
    html, body{
        padding:0px;
        margin:0px;
    }
    #loading_state{
        position: absolute;
        top:45%;
        left:0px;
        width:100%;
        font-size: 20px;
        text-align:center;
    }
    #open_call_state{
        display:none;
    }
    #local_video{
        position: absolute;
        top:10px;
        left:10px;
        width:160px;
        height:120px;
        background-color: #333333;
    }
    #remote_video{
        position: absolute;
        top:0px;
        left:0px;
        width:800px;
        height: 700px;
        background-color: #999999;
    }
    #membercontainer{
        position: absolute;
        top:20px;
        left:70%;
    }
    #membercontainer>ul{
        list-style-type: none;
    }
    #membercontainer h3{
        cursor: pointer;
    }
</style>
</head>
<body onload="start();">
	<div id="membercontainer">
		<ul class="members">
		</ul>
	</div>
	<div id="loading_state">
	</div>
	<div id="open_call_state">
		<video id="remote_video"></video>
		<video id="local_video"></video>
	</div>
</body>
<script>
    var webrtc_capable=true;//WebRTC를 지원하는 브라우져인지를 체크하는 변수
    var rtc_peer_connection=null;//RTCPeerConnection객체를 담는변수 브라우저별 분기처리
    var rtc_session_decription=null;
    var get_user_media=null;//장치(사운드, 영상)에 대한 데이터를 자겨오는 변수
    var connect_stream_to_src=null;//장치가 보내주는 데이터를 src로 변환해서 web브라우저에서 출력
    var stun_server="stun.l.google.com:19302";//구글운영하는 stun서버
    //브라우저별 생성하지
    //표준방식
    if(navigator.getUserMedia){
        rtc_peer_connection=RTCPeerConnection;
        rtc_session_decription=RTCSessionDescription;
        get_user_media=navigator.getUserMedia.bind(navigator);
        connect_stream_to_src=function(media_stream,media_element){
            media_element.srcObject=media_stream;//재생할 데이터 넣기
            media_element.play();//영상재생
        }
    }else if(navigator.mediaDevices.getUserMedia){
        rtc_peer_connection=mozRTCPeerConnection;
        rtc_session_decription=mozRTCSessionDescription;
        get_user_media=navigator.mediaDevices.getUserMedia.bind(navigator);
        connect_stream_to_src=function(media_stream,media_element){
            media_element.mozSrcObject=media_stream;//재생할 데이터 넣기
            media_element.play();//영상재생
        }
        stun_server="74.125.31.127:19302";
    }else if(navigator.webkitGetUserMedia){//크롬크롬!!
        rtc_peer_connection=webkitRTCPeerConnection;
        rtc_session_decription=webkitRTCSessionDescription;
        get_user_media=navigator.webkitGetUserMedia.bind(navigator);
        connect_stream_to_src=function(media_stream,media_element){
            media_element.src=webkitURL.createObjectURL(media_stream);//재생할 데이터 넣기
            media_element.play();//영상재생
        }
    }else{
        alert("지원하지 않는 브라우저 입니다.");
        webrtc_capable=false;
    }
    //RTC를 사용하기 위한 기본설정 끝!
</script>
<script>
    var call_token="${loginMember.userId}";//사용자를 구분하기 위한 구분자
    var signalingServer;//시그널링 서버 저장변수 -> websocket 서버
    var peerConnection;//connection정보(객체) 저장하는 변수

    //온로드함수 작성하기
    function start(){
        peerConnection=new RTCPeerConnection({
            "iceServers" : [
            	{
               		"url":"stun:"+stun_server
            	}
            ]
        });
        //peerConnection pTop연결에 대한 설정
        peerConnection.onicecandidate=function(ice_event){
        	console.log(ice_event.candidate);
            if(ice_event.candidate){
                signalingServer.send(JSON.stringify({
                    type:"new_ice_candidate",
                    candidate:ice_event.candidate
                }));
            }
        };
        //영상,음성에 대한 스트림이 넘어왔을때 설정
        peerConnection.onaddstream=function(event){
            connect_stream_to_src(event.stream,document.getElementById("remote_video"));
            $("#loading_state").css("display","none");
            $("#open_call_state").css("display","block");
        }
        //미디어장치정보 받아오기
        setupVideo();//미디어장치를 세팅하는 함수


        //시그널링 서버구성(연결) -> websocket설정

        signalingServer=new WebSocket("wss://192.168.20.34:8443/${pageContext.request.contextPath}/viewChatServer");
        signalingServer.onopen=function(event){
            //서버접속완료했을때 설정
            signalingServer.onmessage=caller_signal_handler;//요청이 오면 처리하는 함수

            //접속정보 전달하기
            signalingServer.send(JSON.stringify({
                type:"join",
                token:call_token
            }));
        }

        document.title="접속대기중............";
        document.getElementById("loading_state").innerHTML="접속대기중.........<br><br>";

    }//start()함수끝

    //미디어장치정보 가져오기
    function setupVideo(){
        get_user_media({
            "audio":true,
            "video":true
        },function(local_stream){//성공시 실행되는 callback함수
            //미디어장치를 가져와서 로컬video태그에 넣어줌
            connect_stream_to_src(local_stream,document.getElementById("local_video"));

            peerConnection.addStream(local_stream);
        },function(error){//에러발생처리-> 실패시 실행되는 callback함수
            console.log(error);
        });
    }

    //상대방에게 ptop연결요청을 하는 함수 -> 자신의 스트림정보를 넘기면서 영상을 서로 교환
    function new_description_create(description){
        peerConnection.setLocalDescription(description,function(){//성공시 callback함수
            signalingServer.send(JSON.stringify({
                type:"new_description",
                token:call_token,
                sdp:description
            }))
        },function(error){
            console.log(error);
        });
    }

    //소켓메세지처리 핸들러 등록하기
    function caller_signal_handler(event){
        var signal=JSON.parse(event.data);
        if(signal.type=="callee_arrived"){
            peerConnection.createOffer(new_description_create,
                function(error){console.log(error)}
            )
        }else if(signal.type=="new_ice_candidate"){
            peerConnection.addIceCandidate(new RTCIceCandidate(signal.candidate));
        }else if(signal.type=="new_description"){
            peerConnection.setRemoteDescription(new rtc_session_decription(signal.sdp),
                                    function(){//session 연결이 성공했을때 실행되는 함수
                                        if(peerConnection.remoteDescription.type=="answer"){
                                            if(confirm("화상채팅요청이 들어왔습니다. 허용하시겠습니까?")){
                                                peerConnection.createAnswer(new_description_create,
                                                function(error){
                                                    console.log(error);
                                                });
                                            }
                                        }
                                    },
                                    function(error){
                                        console.log(error);
                                    })
        }else if(signal.type=="members"){
            //접속자현황을 출력
            let membercontainer=$(".members");
            membercontainer.html("");
            membercontainer.append($("<li>").html("<h3>연결가능한 회원</h3>"));
            for(let i=0;i<signal.members.length;i++){
                let li=$("<li>");
                let h=$("<h3>").html(signal.members[i]).css("color","gray");
                if(call_token!=signal.members[i]){
                    h.css("color","green");
                    h.click(function(event){
                        if(confirm($(this).html()+"님과 연결하시겠습니까?")){
                            sendArrived($(this).html());
                        }
                    })
                }
	            li.html(h);
    	        membercontainer.append(li);
            }//for문끝
        }
    }
    //다른 클라이언트와 접속이 된 후에는 핸들러를 변경함.
    function callee_signal(event){
        var signal=JSON.parse(event.data);
        console.log(signal);
        if(signal.type=="new_description"){
            peerConnection.setRemoteDescription(new rtc_session_decription(signal.sdp),function(){
                            if(peerConnection.remoteDescription.type=="offer"){
                                peerConnection.createAnswer(new_description_create,
                                    function(error){
                                        console.log(error);
                                    })
                            }
                        },function(error){
                            console.log(error);
                        })
        }else if(signal.type=="new_ice_candidate"){
            peerConnection.addIceCandidate(new RTCIceCandidate(signal.candidate));
        }else if(signal.type=="callee_arrived"){
            peerConnection.createOffer(new_description_create,
                    function(error){
                        console.log(error);
                    })
        }
    }

    function sendArrived(receiver){
        signalingServer.onmessage=callee_signal;//이벤트처리핸들러 변경
        signalingServer.send(JSON.stringify({
            type:"callee_arrived",
            token:call_token,
            receiver:receiver
        }))
    }
</script>








</html>
