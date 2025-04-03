<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp" %>

	<jsp:include page="../common/header.jsp" />
	
	<div class="page-contents">
	  <div class="container">
	   
	    <div class="page-title">
	      <div>
	        <h2>전용회선 서비스</h2>
	        <p>kt그룹 국가정보통신서비스의 <span class="kt-red-text">전용회선 서비스</span>를 알려드리겠습니다.</p>
	      </div>
	      <ul>
	        <li><a href="javascript:menuMove('<%=request.getContextPath()%>/main.do');"><img src="<%=request.getContextPath()%>/images/bullet_rocation_home.gif" alt="홈페이지 메인"></a></li>
	        <li>kt그룹서비스</li>
	        <li><a href="javascript:menuMove('<%=request.getContextPath()%>/service/ktservice.do', 1);">A. 전용회선 서비스</a></li>
	        <li><a href="javascript:menuMove('<%=request.getContextPath()%>/service/leased_lamda.do');">람다회선 서비스</a></li>
	      </ul>
	    </div>
	    
	    <div class="page-tab">
	      <div class="current-label">
	        <a href="javascript:">람다회선 서비스</a>
	      </div>
	      <ul>
	        <li>
	          <a href="javascript:menuMove('<%=request.getContextPath()%>/service/leased_basic.do');">기본회선 서비스</a>
	        </li> 
	        <li>
	          <a href="javascript:menuMove('<%=request.getContextPath()%>/service/leased_backbone.do');">백본회선 서비스</a>
	        </li>      
	        <li>
	          <a href="javascript:menuMove('<%=request.getContextPath()%>/service/leased_cctv.do');">CCTV 전송회선 서비스</a>
	        </li>
	        <li class="active">
	          <a href="javascript:menuMove('<%=request.getContextPath()%>/service/leased_lamda.do');">람다회선 서비스</a>
	        </li>
	        <li>
	          <a href="javascript:menuMove('<%=request.getContextPath()%>/service/leasedline_infra.do');">전용회선 인프라</a>
	        </li>
	      </ul>
	    </div>
	    
	    <div class="radio-tab">
	      <ul>
	        <li>
	          <input type="radio" name="radio-tab" id="t1" value="service_info" checked="checked" />
	          <label for="t1"><i class="material-icons"> live_help</i> 서비스 정보</label>
	        </li>
	        <li>
	          <input type="radio" name="radio-tab" id="t2" value="price_info" />
	          <label for="t2"><i class="material-icons">receipt</i> 요금 안내</label>
	        </li>
	      </ul>
	    </div>
	  </div>
	    
	    
	  <div class="container service_info">
	    <div class="row">
	      <div class="col s12">
	        <h3>람다회선 서비스란?</h3>
	        <ul class="list-style1">
	          <li>통합전산센터의 재해 복구망 구성을 위하여 람다를 기반으로 제공하는 전용회선 서비스</li>
	        </ul>
	        <div class="center-align">
	        	<img src="<%=request.getContextPath()%>/images/ready.png" alt="tset" class="responsive-img" />
	        </div>
	      </div>
	    </div>
	    
	  </div>
	  
	<div class="container price_info " class="display:none">
    	<div class="col s12 m3 right-align">
	    	<a href="javascript:" class="btn show-on-small" onclick="alert('서비스 준비중입니다.');">요금상세조회</a>
	    </div>
		<jsp:include page="../service/leased_lamda_fee.jsp" />
    </div>
	  
	</div>
	

<form name="popForm">
	<input type="hidden" value="<%=request.getSession().getAttribute("CSRF_TOKEN")%>" name="request_token" />
	<input type="hidden" value="" name="request_url" />
</form>
	<jsp:include page="../common/footer.jsp" />
<Script>
$(document).ready(function(){
    $(".price_info").hide();
	
    $('.radio-tab label').click(function(){
      var num = $('.radio-tab label').index($(this));
      $('.radio-tab input').each(function(index){
        $('div.'+$(this).val()).hide();
        if(index == num){
          $('div.'+$(this).val()).show();
        }
      });
      
    });
    
    
  });  

function openPop(){
	//2019.04.26 중복사용자 세션종료 후 기존 사용자 session 체크로직 추가  
	sessioncheck();
			
		
	var form = document.popForm;
	var url = "<%=request.getContextPath()%>/service/leased_lamda_detail.do";
	window.open("","popForm","width=1200, height=700, scrollbars=yes");

	form.action = url;
	form.method = "post";
	form.target = "popForm";
	form.submit();
}
</script>
</html>