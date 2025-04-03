<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script>

	$(function() {
		$( "#accordion_condition" ).accordion({
			animated:'bounceslide'
			});
		
		$( "#from" ).datepicker({
        	defaultDate: "+1w",
          	changeMonth: true,
          	numberOfMonths: 2,
          	onClose: function( selectedDate ) {
            	$( "#to" ).datepicker( "option", "minDate", selectedDate );
          	}
      	});
	        
        $( "#to" ).datepicker({
            defaultDate: "+1w",
            changeMonth: true,
            numberOfMonths: 2,
            onClose: function( selectedDate ) {
                $( "#from" ).datepicker( "option", "maxDate", selectedDate );
            }
        });

        $("#buttonLike").button({
            icons:{primary:"ui-icon-heart"}
        });

        $( "#radio" ).buttonset();
	});

</script>
<div id="accordion_condition">
    <h3>기간 설정</h3>
    <div>
    	<table align=center>
    		<tr>
    			<td>From</td>
    			<td><input type="text" id="from" name="from" style="font-size:11px;width:80px;border:solid 1px gray"/></td>
    			<td>To</td>
    			<td><input type="text" id="to" name="to" style="font-size:11px;width:80px;border:solid 1px gray" /></td>
    			<td>System</td>
    			<td>
    				<select id="systemName">
						<option value="all">모두</option>
						<option value="1">ds1</option>
						<option value="2">Tong</option>
						<option value="3">Smartzone</option>
						<option value="4">전자결재</option>
						<option value="5">전자메일</option>
					</select>
    			</td>
    		
    			<td><button id="buttonLike">좋아요!</button></td>
    		</tr>
    	</table>
    </div>
    <h3>챠트종류</h3>
    <div>
      	<div id="radio" align=center>
       		<input type="radio" id="radio1" name="radio" /><label for="radio1">봉챠트</label>
       		<input type="radio" id="radio2" name="radio" checked="checked" /><label for="radio2">라인챠트</label>
       		<input type="radio" id="radio3" name="radio" /><label for="radio3">원챠트</label>
   		</div>
    </div>
</div>