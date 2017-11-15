<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<!--[if lt IE 7 ]><html class="no-js ie6" lang="en"><![endif]-->
<!--[if IE 7 ]><html class="no-js ie7" lang="en"><![endif]-->
<!--[if IE 8 ]><html class="no-js ie8" lang="en"><![endif]-->
<!--[if (gte IE 9)|!(IE)]><!--><html class="no-js" lang="en"><!--   UMlogo.png <![endif]-->
    <head>
        <meta charset='UTF-8'>
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title><tiles:insertAttribute name="title" ignore="true" /></title>
		<script src="/remis/javascript/jquery-1.10.2.min.js" type="text/javascript"></script>
		<script src="/remis/javascript/jquery-ui.js" type="text/javascript"></script>
		<script type="text/javascript" src="/remis/javascript/commonscript.js"></script>
		<script src="/remis/javascript/promis.js" type="text/javascript"></script>
		<link rel="stylesheet" href="/remis/css/bootstrap.css" media="all">
		<link rel="stylesheet" href="/remis/css/dnr.css" media="all">
		<link rel="stylesheet" href="/remis/css/jquery-ui.css" media="all">
		<link rel="icon" href="/remis/images/calendar.png" type="image/png"/> 
	</head>
    <body>
    <div id="header-region" class="navbar navbar-default" role="banner">
    	<div class="container">
				<div id='header' class="row"><tiles:insertAttribute name="header" /></div>
				<div id="menu" class="row"><tiles:insertAttribute name="menubar"/></div>
		</div>
	 </div>
	<!-- <div> -->
	<div class="container">
		<div class="content">
 			<div class="row"><tiles:insertAttribute name="content" flush="true" /></div>
		</div>
	</div>
	<!-- </div> -->
	</body>
	<footer>
	<div class="container">
    	<div id="footer" class="row"><tiles:insertAttribute name="footer" /></div>
    </div>
    </footer>
    
</html>