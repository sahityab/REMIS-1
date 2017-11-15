<%@ taglib prefix="s" uri="/struts-tags"%>
<h2 align="center"><s:text name="page.heading.casehearing"/></h2>
<%@ include file="calendar.jsp"%>
<head>
	<script src="/remis/javascript/jquery-1.10.2.min.js" type="text/javascript"></script>
	<script src="/remis/javascript/jquery-ui.js" type="text/javascript"></script>
	<script type="text/javascript" src="/remis/javascript/commonscript.js"></script>
	<%-- <script src="/remis/javascript/usermaint.js" type="text/javascript"></script> --%>
</head>
<script type="text/javascript">
function viewCaseHearingInfo(hearid,id,isnew){
	    $('#isnew').val(isnew);
		$('#caseId').val(id);
		$('#casehearId').val(hearid);
	    propattform.action='viewhearing';
	    propattform.submit();
	
}

</script>


<body  >
<s:form name="propattform" method="post" theme="simple" style="min-height: 350px">
	 <div class="row" style='padding-bottom: 20px'>
	 <s:hidden id='caseId' name="hearingVo.caseId"/> <s:hidden id='isnew' name="hearingVo.isnew"/> <s:hidden id='casehearId' name="hearingVo.casehearId"/>
	<div class="col-sm-12 col-xs-12">&nbsp;
	<div class="table-responsive">
	<table class="table table-bordered">
	  <thead class="table-row">
	     <tr>
			<th class="table-cell" colspan="5">Hearings #&nbsp;&nbsp;&nbsp; <s:property value="hearingVo.propertyName" />&nbsp;&nbsp;&nbsp; <s:property value="hearingVo.CourtName" />&nbsp;&nbsp;&nbsp; <s:property value="hearingVo.CaseName" /></th>
		</tr>
		</thead>
	   <thead class="table-row">
	   <tr>
			<th class="table-cell">Hearing Date</th>
			<th class="table-cell">Task</th>
			<th class="table-cell">Details</th>
			<th class="table-cell">Hearing</th>
		</tr>
		</thead>
		<s:iterator value="hearingList" var="hearingVo">
	        <tr class="table-row">
				<td class="table-cell"><s:date name="caseHearingDate" format="dd/MM/yyyy" /></td>
				<td class="table-cell"><s:property value="hearingTask" /></td>
				<td class="table-cell"> <s:property value="hearinDetails"  /></td>
				<td class="table-cell">
				  <a href='#'  onclick="JavaScript:viewCaseHearingInfo('<s:property value="casehearId"/>','<s:property value="caseId"/>','<s:property value="isnew"/>')" >View </a>
					<s:if test="%{isnew==true}">
		                      <img alt="<s:property value="newreason"/>" src="./images/notify.jpg" title="<s:property value="newreason"/>">
			       </s:if>
				 <%-- <a href="viewhearing?casehearId=<s:property value="casehearId"/>"> View</a>--%>
				 </td> 
		    </tr>
		    </s:iterator>
	   </table>
	</div>
	</div>
	</div> 
	
	<div class="row" style='padding-top: 20px'>
		<div class="col-sm-4 col-xs-12">&nbsp;</div>
		<div class="col-sm-4 col-xs-12">
		<input type="button" value="Back" class="submit" onclick="JavaScript:history.go(-1)">
		</div>
		<div class="col-sm-4 col-xs-12">&nbsp;</div>
	</div>
	
</s:form>
</body>