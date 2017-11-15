<%@ taglib prefix="s" uri="/struts-tags"%>
<h2 align="center"><s:text name="page.heading.workprogress"/></h2>
<%@ include file="calendar.jsp"%>
<head>
	<script src="/remis/javascript/jquery-1.10.2.min.js" type="text/javascript"></script>
	<script src="/remis/javascript/jquery-ui.js" type="text/javascript"></script>
	<script type="text/javascript" src="/remis/javascript/commonscript.js"></script>
	<%-- <script src="/remis/javascript/usermaint.js" type="text/javascript"></script> --%>
</head>

<body>
<s:fielderror cssClass="errorMsg"/>
<s:actionerror cssClass="errorMsg"/>
<s:actionmessage cssClass="infoMsg"/>
<s:form  theme="simple" validate="true" >
   

	<div class="row" style='padding-bottom: 20px'>
	<div class="col-sm-1 col-xs-12">&nbsp;</div>
	<div class="col-sm-10 col-xs-12">&nbsp;
	<div class="table-responsive">
	<table class="table table-bordered">
	  <thead class="table-row">
	     <tr>
			<th class="table-cell" colspan="5">Progress</th>
		</tr>
		</thead>
	   <thead class="table-row">
	   <tr>
			<th class="table-cell">Progress Description</th>
			<th class="table-cell">Issues</th>
			<th class="table-cell">Created </th>
			<th class="table-cell">Action Needed</th>
			<th class="table-cell">View</th>
		</tr>
		</thead>
		<s:iterator value="contractorList" var="contractVo">
	        <tr class="table-row">
				<td class="table-cell"><s:property value="workDesc" /></td>
				<td class="table-cell"><s:property value="issues" /></td>
				<td class="table-cell"> <s:date name="startDate" format="dd/MM/yyyy" /></td>
				<td class="table-cell"> <s:property value="actionneeded" /></td>
				<td class="table-cell"> <a href="viewsingleprogress?workProgId=<s:property value="workProgId"/>"> View</a></td>
		    </tr>
		    </s:iterator>
	   </table>
	</div>
	</div>
	</div>
	
	<div class="row" style='padding-bottom: 20px'>
		<div class="col-sm-4 col-xs-12">&nbsp;</div>
		<div class="col-sm-4 col-xs-12">
		<input type="button" value="Back" class="submit" onclick="JavaScript:history.go(-1)">
		</div>
		<div class="col-sm-4 col-xs-12">&nbsp;</div>
	</div>
</s:form>
</body>