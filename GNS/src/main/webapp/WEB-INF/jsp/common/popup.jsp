<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<div id="popup_Layer" class="popup-layer" style="display: none;">
	<c:choose>
		<c:when test="${empty pop_list}">
			<div id="popup_title" class="popup-title"></div>
			<div id="popup_content" class="popup-contents"></div>
		</c:when>
		<c:otherwise>
			<c:forEach var="list" items="${pop_list}">
				<div id="popup_title" class="popup-title">${list.pop_title}</div>
				<div id="popup_content" class="popup-contents">${list.pop_cont}</div>
			</c:forEach>
		</c:otherwise>
	</c:choose>
	<div class="close-panel">
		<div>
			<input type="checkbox" name="close" id="close" /> <label for="close">하루동안
				이 창을 열지 않음</label>
		</div>
		<div>
			<a href="javascript:" onclick="javascript:closePop('egov');" class="btn">닫기</a>
		</div>
	</div>
</div>