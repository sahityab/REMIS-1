<%@ taglib prefix="s" uri="/struts-tags"%>
<h2 align="center"> <s:text name="page.heading.attcases"/></h2>
<head>
	<script src="/remis/javascript/jquery-1.10.2.min.js" type="text/javascript"></script>
	<script src="/remis/javascript/jquery-ui.js" type="text/javascript"></script>
	<script type="text/javascript" src="/remis/javascript/commonscript.js"></script>
</head>
<script type="text/javascript">
function viewCaseInfo(id,isnew){
	    $('#isnew').val(isnew);
		$('#caseId').val(id);
	    propattform.action='viewcase';
	    propattform.submit();
	
}

</script>

<body>
<s:fielderror cssClass="errorMsg"/>
<s:actionerror cssClass="errorMsg"/>
<s:actionmessage cssClass="infoMsg"/>
<s:form name="propattform" method="post" theme="simple">

	  
   <div class="row" style='padding-bottom: 20px'><s:hidden id='caseId' name="attorneyVo.caseId"/> <s:hidden id='isnew' name="attorneyVo.isnew"/>
	<div class="col-sm-12 col-xs-12">&nbsp;
	<div class="table-responsive">
	<table class="table table-bordered">
	  <thead class="table-row">
	     <tr>
			<th class="table-cell" colspan="9">My Cases</th>
		</tr>
		</thead>
	   <thead class="table-row">
	   <tr>
			<th class="table-cell">Prop Name</th>
			<th class="table-cell">Owner</th>
			<th class="table-cell">Case No</th>
			<th class="table-cell">Court</th>
			<th class="table-cell">Start Date</th>
			<th class="table-cell">Petitioner</th>
			<th class="table-cell">Respondents</th>
			<th class="table-cell">View</th>
			<th class="table-cell">Hearing</th>
		</tr>
		</thead>
		<s:iterator value="casesList" var="attorneyVo">
	        <tr class="table-row">
				<td class="table-cell"><s:property value="propertyName" /></td>
				<td class="table-cell"><s:property value="userName" /></td>
				<td class="table-cell"><s:property value="caseName" /></td>
				<td class="table-cell"><s:property value="courtName" /></td>
				<td class="table-cell"> <s:date name="startDate" format="dd/MM/yyyy" /></td>
				<td class="table-cell"><s:property value="petitioner" /></td>
				<td class="table-cell"><s:property value="respondent" /></td>
				<td class="table-cell"> 
				   <a href='#'  onclick="JavaScript:viewCaseInfo('<s:property value="caseId"/>','false')" > View/Update</a>
				</td>
				
				
				<td class="table-cell"> <a href="createhearing?caseid=<s:property value="caseId"/>"> Create</a></td>
		    </tr>
		    </s:iterator>
	   </table>
	</div>
	</div>
	</div>
	
</s:form>
</body>
<p class="min_height_100">&nbsp;</p>