<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<style>
	body{
		font: 62.5% "Trebuchet MS", sans-serif;
		margin-top: 0px;
		margin-bottom: 0px;
		margin-left: 0px;
		margin-right: 0px;
	}
	
	.topLine		{
		margin:2em;
		border:solid 1px #336699;
	}
	
	.middleLine	{
		margin:2em;
		border:dashed 1px #336699;
	}
	
	#HeaderSection{
		position:relative;
		background:#336699;
		font-family:Verdana;
		font-weight:bold;
		width:100%;
		height:30px;
		font-size:18px;
		padding-top:5px;
		padding-bottom:0px;
	}
	
	#HeaderLogo {
		position:absolute;
		top:12px;
		left:5px;
		width:300px;
		text-decoration:none;
		
		color:white;
		valign:bottom;
		vertical-align:bottom;
		text-align:bottom;
	}
	
	#ProfileSection {
		font-size:12px;
		width:700px;
		text-align:right;
		padding-top:10px;
		vertical-align:bottom;
		color:white;
	}
	
	#BodySection {
		position:relative;
		text-align:left;
		padding:5px;
		border-top:0px;
		border-left:solid 1px #336699;
		border-right:solid 1px #336699;
		border-bottom:solid 1px #336699;
		width:700px;
	}
	
	#MenuSection {
		position:absolute;
		left:-160px;width:170px;
		border-top:0px;
		border-bottom:0px;
		border-left:0px;
		border-right:0px;
		font-size:9pt;
		color:#6eaf99;
		z-index:1;
	}
	
	#ConditionSection {
		border-top:0px;
		border-bottom:0px;
		border-left:0px;
		border-right:0px;
		font-size:9pt;
		color:#6eaf99;
		z-index:1;
	}
	
	
	#FooterSection {
		font-family:Verdana;
		width:100%;height:100px;
		font-size:9px;
		text-align:center;color:#336699;
	}
	
	
	
	#dialog-link {
		padding: .4em 1em .4em 20px;
		text-decoration: none;
		position: relative;
	}
	
	#dialog-link span.ui-icon {
		margin: 0 5px 0 0;
		position: absolute;
		left: .2em;
		top: 50%;
		margin-top: -8px;
	}
	
	
    .ui-menu { width: 150px; }
    
    
    .HeaderText {
    	color:grey;
    	font-weight:bold;
    	font-size:18px;
    }
    
    #icons {
		margin: 0;
		padding: 0;
	}
	#icons li {
		margin: 2px;
		position: relative;
		padding: 4px 0;
		cursor: pointer;
		float: left;
		list-style: none;
	}
	#icons span.ui-icon {
		float: left;
		margin: 0 4px;
	}
	
	.tabs-bottom .ui-tabs-nav { clear: left; padding: 0 .2em .2em .2em; }
    .tabs-bottom .ui-tabs-nav li { top: auto; bottom: 0; margin: 0 .2em 1px 0; border-bottom: auto; border-top: 0; }
    .tabs-bottom .ui-tabs-nav li.ui-tabs-active { margin-top: -1px; padding-top: 1px; }
    
</style>
<link rel="stylesheet" type="text/css" media="screen" href="/tms/scripts/jquery.jqGrid-4.4.1/css/ui.jqgrid.css" />
<link rel="stylesheet" type="text/css" media="screen" href="/tms/scripts/jquery.jqGrid-4.4.1/css/jquery-ui-1.9.2.custom.css" />
<script type="text/javascript" src="/tms/scripts/jquery.jqGrid-4.4.1/js/jquery-1.8.3.js"></script>
<script type="text/javascript" src="/tms/scripts/jquery.jqGrid-4.4.1/js/i18n/grid.locale-en.js"></script>
<script type="text/javascript" src="/tms/scripts/jquery.jqGrid-4.4.1/js/jquery.jqGrid.min.js"></script> 
  
