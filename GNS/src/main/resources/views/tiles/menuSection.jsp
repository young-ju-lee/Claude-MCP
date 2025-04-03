<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script>

	$(function() {
		$( "#menu" ).menu();
	});

</script>

<ul id="menu">
    <li class="ui-state-disabled"><a href="#">SR 통계</a></li>
    <li>
    	<a href="/srms/system.run">시스템별 통계</a>
    </li>
    <li>
    	<a href="/srms/person.run">담당자별 통계</a>
    </li>
    <li>
    	<a href="/srms/type.run">SR유형별 통계</a>
    </li>
    <li>
    	<a href="#">설정</a>
    	<ul>
    		<li><a href="/srms/sharepoint.run">Data Import</a></li>
        </ul>
    </li> 
</ul>

