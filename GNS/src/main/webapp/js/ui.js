/* *************************************************************************
   초기화
   ************************************************************************* */
var gnbTimer;
var gnbPointerX = [-1, 191, 307, 431, 546, 661];
var gnbHeight = [80, 192, 232, 212, 192, 192];

$(document).ready(function(){
	//GNB
	$(".gnbArea > ul > li").each(function(index){
		$(this).addClass("gnb"+(index+1));
	});
	$(".sub > ul > li").each(function(index){
		$(this).addClass("d2");
	});
	$(".d2 > ul > li").each(function(index){
		$(this).addClass("d3");
	});
	$(".gnbArea ul").each(function(index){
		$("> li",$(this)).each(function(index){
			$(this).addClass("m"+(index+1));
		});
	});
	$("#gnb > li > a").click(function(){gnbShow($(this).parent());});
	$(".closed").click(function(){gnbHide();});
	gnbInit();

	//내부 탭 이동
	$('dl.tabWrap .tMenu a').click(function(){
		$(this).parent('.tMenu').addClass('on').siblings('.tMenu').removeClass('on');
		$(this).parent('.tMenu').siblings('.tCont').hide();
		$(this).parent('.tMenu').next('.tCont').show();
		return false;
	});

	//내부 서브탭 이동
	$('dl.subTabWrap .stMenu a').click(function(){
		$(this).parent('.stMenu').addClass('on').siblings('.stMenu').removeClass('on');
		$(this).parent('.stMenu').siblings('.stCont').hide();
		$(this).parent('.stMenu').next('.stCont').show();
		return false;
	});

	//accordion
	$('.accList .accTtl .btn.plus').click(function(){
		var $ttl = $(this).closest(".accTtl");
		if(!$ttl.hasClass("on")){
			$ttl.siblings("dd").slideUp();
			$ttl.addClass("on").next('dd').slideDown();
			$ttl.siblings("dt").removeClass("on");
		}
	});
	$('.accList .accTtl .btn.minus').click(function(){
		var $ttl = $(this).closest(".accTtl");
		if($ttl.hasClass("on")){
			$ttl.removeClass("on").next('dd').slideUp();
		}
	});

	//포토앨범 탭 이동
	$('.photoTab .btn a').each(function(index){
		if(!$(this).parent().is('span')){
			if(index%2 == 0){
				$(this).addClass('aPrev');
			}else{
				$(this).addClass('aNext');
			}
		}
	});
	$('.photoTab .tMenu .thumb').each(function(index){
		if($(this).find('mask').length<1){
			$(this).append('<span class="mask"></span>');
		}
	});
	$('.photoTab .tMenu a').click(function(){
		$(this).parent('.tMenu').addClass('on').siblings('.tMenu').removeClass('on');
		$(this).parent('.tMenu').siblings('.tCont').hide();
		$(this).parent('.tMenu').next('.tCont').show();

		var $list = $('.list',$(this).closest('.photoTab'));
		if($(this).parent('.tMenu').index() > 0){
			$('.photoTab .btn .prev').addClass('on');
		}else{
			$('.photoTab .btn .prev').removeClass('on');
		}
		if($(this).parent('.tMenu').index()/2 < $('.tMenu',$list).length-1){
			$('.photoTab .btn .next').addClass('on');
		}else{
			$('.photoTab .btn .next').removeClass('on');
		}
		return false;
	});
	if($('.photoTab .tMenu').length > 1){
		$('.photoTab .btn .next').addClass('on');
	}
	$('.photoTab .btn .prev a').click(function(){
		var $list = $('.list',$(this).closest('.photoTab'));
		var idx = $('.tMenu.on',$list).index()/2;
		if(idx > 0){
			idx--;
			$('.tMenu',$list).eq(idx).find('a').click();
			$('.photoTab .btn .next').addClass('on');
		}else{
			$('.photoTab .btn .prev').removeClass('on');
		}
		return false;
	});
	$('.photoTab .btn .next a').click(function(){
		var $list = $('.list',$(this).closest('.photoTab'));
		var idx = $('.tMenu.on',$list).index()/2;
		if(idx < $('.tMenu',$list).length-1){
			idx++;
			$('.tMenu',$list).eq(idx).find('a').click();
			$('.photoTab .btn .prev').addClass('on');
		}else{
			$('.photoTab .btn .next').removeClass('on');
		}
		return false;
	});

	//Life Plan 메인 탭 이동
	$('dl.lpMainTab .tMenu a').click(function(){
		$(this).parent('.tMenu').addClass('on').siblings('.tMenu').removeClass('on');
		$(this).parent('.tMenu').find(".descr").show();
		$(this).parent('.tMenu').siblings('.tMenu').find(".descr").hide();
		$(this).parent('.tMenu').siblings('.tCont').hide();
		$(this).parent('.tMenu').next('.tCont').show();
		return false;
	});

	//스마트지식 롤링탭 이동
	$(".smkRecent .rollingTabWrap").each(function(index){
		$(this).initRollingItem({rolling:true , interval:5000 , itemW:369});
	});

	//ui.js에서 제공하는 클릭이벤트를 사용하지 않을 경우 click 이벤트 핸들러 해제
	$('.notUseUI').unbind("click");
});

$(window).load(function(){
	//IE8 이하 - 버튼 그림자 폭 조정
	$(".le_ie8 .tBtn.def > span, .le_ie8 .tBtn.big > span").each(function(){
			var w = $(this).width()-4;
			if($("body").hasClass("ie7")){w += 4;}
			$(this).parent().prepend('<span class="shadow"></span>');
			$(this).siblings(".shadow").width(w);
	});

	//IE7 - 게시판 상세보기 다중칼럼 높이 조정
	$(".ie7 .dlTblWrap dl.col2, .ie7 .dlTblWrap dl.col3").each(function(){
		var h = $(this).height()
		$("dt,dd",this).each(function(){
			$(this).height(h-parseInt($(this).css("padding-top"))-parseInt($(this).css("padding-bottom")));
		});
	});

	//메인 비주얼 롤링
	if($("#mainVisual").length>0){
		$('#mainVisual').initRollingItem2({suffix:"비주얼", wrap:".mainVisualWrap"});
	}
});

/* *************************************************************************
   메뉴 navigation
   ************************************************************************* */
//GNB
function gnbInit() {
	var $d1 = $("#gnb > li.on");

	if($d1.length < 1){
		$("#header").css({"height":gnbHeight[0]+"px"});
		$("#gnb .sub").hide();
		$(".gnbLine").hide();
		$(".gnbPointer").hide();
	}else{
		$d1.addClass("on").siblings("li").removeClass("on");
		$(".sub", $d1).show();
		$(".sub", $d1.siblings("li")).hide();
		$(".gnbLine").show();
		$("#header").css({"height":gnbHeight[$d1.index()+1]+"px"});
		$(".gnbPointer").show().css({"left":gnbPointerX[$d1.index()+1]+"px"});
	} 
	
	$d1.addClass("on").siblings("li").removeClass("on");
	$(".sub", $d1).show();
	$(".sub", $d1.siblings("li")).hide();
	$(".gnbLine").show();
	$("#header").css({"height":gnbHeight[$d1.index()+1]+"px"});
	$(".gnbPointer").show().css({"left":gnbPointerX[$d1.index()+1]+"px"});
}
function gnbShow($d1) {
	$("#header").stop();
	$(".headerWrap").stop();
	$d1.addClass("on").siblings("li").removeClass("on");
	$(".sub", $d1).show();
	$(".sub", $d1.siblings("li")).hide();
	$(".gnbLine").show();
	$(".visual", $d1).css({"top":-300}).animate({"top":0},700);
	$("#header").animate({"height":gnbHeight[$d1.index()+1]+"px"}, 500);
	$(".gnbPointer").stop().show().animate({"left":gnbPointerX[$d1.index()+1]+"px"}, 500, "easeInOutQuad");
}
function gnbShow2($d1) {
	$("#header").stop();
	$(".headerWrap").stop();
	$d1.addClass("on").siblings("li").removeClass("on");
	$(".sub", $d1).show();
	$(".sub", $d1.siblings("li")).hide();
	$(".gnbLine").show();
	$(".visual", $d1).css({"top":-300}).animate({"top":0},0);
	$("#header").animate({"height":gnbHeight[$d1.index()+1]+"px"}, 0);
	$(".gnbPointer").stop().show().animate({"left":gnbPointerX[$d1.index()+1]+"px"}, 0, "easeInOutQuad");
}
function gnbBlur(){
	if(gnbTimer){clearTimeout(gnbTimer);}
	gnbTimer = setTimeout(function(){if($(".headerWrap a:focus").length<1){gnbHide();}}, 500);
}
function gnbHide(){
	$("#header").stop();
	$(".headerWrap").stop();
	$("#header").animate({"height":gnbHeight[0]+"px"}, 500, function(){
		$("#gnb .sub").hide();
		$(".gnbLine").hide();
		$(".gnbPointer").hide();
	});
}



/* *************************************************************************
   플러그인 : select box
   ************************************************************************* */
jQuery(function($){
	var active=false;
	var focusActive=false;
	var ListTemp=$('.selectList ul.aList');
	
	// Show, Hide
	function show_option(){
		if(active){
			ListTemp.slideUp('fast');
			focusActive=false;
		}else{
			ListTemp.slideDown('fast');
		}
		active=!active;
	}
	
	$('.familySite').focusout(function(){
		if(focusActive){
			show_option();
		}
	}).mouseleave(function(){
		if(active){
			focusActive=true;
		}
	}).mouseenter(function(){
		if(active){
			focusActive=false;
		}
	});
	
	$('.myValue').click(function(event){
		event.preventDefault();
		show_option();
	});
});