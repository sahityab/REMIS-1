<%@ taglib prefix="s" uri="/struts-tags"%>
<h2 align="center"><s:text name="page.heading.creathearing"/></h2>
<%@ include file="calendar.jsp"%>
<head>
	<script src="/remis/javascript/jquery-1.10.2.min.js" type="text/javascript"></script>
	<script src="/remis/javascript/jquery-ui.js" type="text/javascript"></script>
	<script type="text/javascript" src="/remis/javascript/commonscript.js"></script>
	<%-- <script src="/remis/javascript/usermaint.js" type="text/javascript"></script> --%>
</head>
<script type="text/javascript">

function viewCaseHearingInfo(hearid,isnew){
	    $('#isnew').val(isnew);
		$('#casehearId').val(hearid);
	    propattform.action='viewhearing';
	    propattform.submit();
	
}

</script>


<body>
<s:fielderror cssClass="errorMsg"/>
<s:actionerror cssClass="errorMsg"/>
<s:actionmessage cssClass="infoMsg"/>
<s:form name="propattform" action="savehearing" method="post"  theme="simple" validate="true" >
   
	<div class="row" style='padding-top: 10px'>
		<div class="col-sm-4 col-xs-12"><label for="propertyId" class="pull-right"><s:text name="label.relation.property.list"/></label></div>
		<div class="col-sm-4 col-xs-12">
			<s:textfield id="propertyId" cssClass="form-control" name="hearingVo.propertyName" readonly="true"/>
		</div>
		<div class="col-sm-4 col-xs-12">&nbsp;<s:hidden name="hearingVo.propertyId"></s:hidden><s:hidden name="hearingVo.caseId">
		<s:hidden id='isnew' name="hearingVo.isnew"/> <s:hidden id='casehearId' name="hearingVo.casehearId"/>
		</s:hidden></div>
	</div>
	 
	<div class="row" style='padding-top: 20px'>
		<div class="col-sm-4 col-xs-12"><label for="caseName" class="pull-right"><s:text name="label.caseName"/></label></div>
		<div class="col-sm-4 col-xs-12"><s:textfield  name="hearingVo.caseName"  cssClass="form-control"  readonly="true" /></div>
		<div class="col-sm-4 col-xs-12">&nbsp;</div>
	</div>
	
	<div class="row" style='padding-top: 20px'>
		<div class="col-sm-4 col-xs-12"><label for="courtName" class="pull-right"><s:text name="label.courtName"/></label></div>
		<div class="col-sm-4 col-xs-12"><s:textfield name="hearingVo.courtName"  cssClass="form-control" readonly="true"/></div>
		<div class="col-sm-4 col-xs-12">&nbsp;</div>
	</div>
	
       <div class="row" style='padding-top: 20px'>
		<div class="col-sm-4 col-xs-12"><label for="startDate" class="pull-right"><s:text name="label.hearingDate"/></label></div>
		<div class="col-sm-4 col-xs-12">
		      <s:textfield id="caseHearingDate" name="hearingVo.caseHearingDate" cssClass="form-control" cssStyle="width:92%;display:inline" />
			<img border="0" id="caseHearingDate" src="<s:url value='./images/calendar.gif'/>" title="Date Selector" class="inline-display"
			onclick="getCalendar(this,'caseHearingDate')" onmouseover="this.style.background='red';" onmouseout="this.style.background=''"/>
		
		</div>
		<div class="col-sm-4 col-xs-12">&nbsp;</div>
	</div>
	
	<div class="row" style='padding-top: 20px'>
		<div class="col-sm-4 col-xs-12"><label for="hearingTask" class="pull-right"><s:text name="label.hearingTask"/></label></div>
		<div class="col-sm-4 col-xs-12"><s:textarea  name="hearingVo.hearingTask"  cssClass="form-control" rows="3"  /></div>
		<div class="col-sm-4 col-xs-12">&nbsp;</div>
	</div>
	
	<div class="row" style='padding-top: 20px'>
		<div class="col-sm-4 col-xs-12"><label for="hearingVo" class="pull-right"><s:text name="label.hearingDetails"/></label></div>
		<div class="col-sm-4 col-xs-12"><s:textarea name="hearingVo.hearingDetails"  cssClass="form-control" rows="3"  /></div>
		<div class="col-sm-4 col-xs-12">&nbsp;</div>
	</div>
	
	
	
 	<div class="row" style='padding-top: 20px'>
		<div class="col-sm-4 col-xs-12">&nbsp;</div>
		<div class="col-sm-4 col-xs-12">
	    <s:submit type="button" value="Save" cssClass="submit"></s:submit>
		<input type="button" value="Back" class="submit" onclick="JavaScript:history.go(-1)">
		</div>
		<div class="col-sm-4 col-xs-12">&nbsp;</div>
	</div>
	
	 <div class="row" style='padding-bottom: 20px'>
	<div class="col-sm-12 col-xs-12">&nbsp;
	<div class="table-responsive">
	<table class="table table-bordered">
	  <thead class="table-row">
	     <tr>
			<th class="table-cell" colspan="5">Hearings</th>
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
				  <a href='#'  onclick="JavaScript:viewCaseHearingInfo('<s:property value="casehearId"/>','false' )">View/Update </a>
					
				 <%-- <a href="viewhearing?casehearId=<s:property value="casehearId"/>"> View</a>--%>
				 </td> 
				
				
				<%-- <td class="table-cell"> <a href="viewhearing?casehearId=<s:property value="casehearId"/>"> View</a></td> --%>
		    </tr>
		    </s:iterator>
	   </table>
	</div>
	</div>
	</div> 
	
</s:form>
</body>