<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script>
	$(document).ready(function() {
		var yn = '${popupVo.pop_yn}';

		var blnCookie = getCookie('egov');
		if (yn == 'Y' && blnCookie != 'done') {
			$("#popup_Layer").css("display", "block");
		}
		});
		
		// 하루동안 창닫기  
	function closePop(winName) {
		//2019.04.26 중복사용자 세션종료 후 기존 사용자 session 체크로직 추가  
		sessioncheck();
				
				
		if ($("#close").prop("checked")) {
			setCookie(winName, "done", "1");
		}
		$("#popup_Layer").css("display", "none");
	}

	// 쿠키 가져오기  
	function getCookie(name) {

		var nameOfCookie = name + "=";
		var x = 0;
		while (x <= document.cookie.length) {
			var y = (x + nameOfCookie.length);
			if (document.cookie.substring(x, y) == nameOfCookie) {
				if ((endOfCookie = document.cookie.indexOf(";", y)) == -1) {
					endOfCookie = document.cookie.length;
				}
				return unescape(document.cookie.substring(y, endOfCookie));
			}
			x = document.cookie.indexOf(" ", x) + 1;
			if (x == 0)
				break;
		}
		return "";
	}

	// 24시간 기준 쿠키 설정하기  
	// expiredays 후의 클릭한 시간까지 쿠키 설정  
	function setCookie(name, value, expiredays) {
		var todayDate = new Date();
		todayDate.setDate(todayDate.getDate() + expiredays);

		document.cookie = name + "=" + escape(value) + "; path=/; expires="
		+ todayDate.toGMTString() + ";";
	}
</script>