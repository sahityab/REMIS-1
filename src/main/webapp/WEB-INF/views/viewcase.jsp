<%@ taglib prefix="s" uri="/struts-tags"%>
<h2 align="center"><s:text name="page.heading.updatecase"/></h2>
<%@ include file="calendar.jsp"%>
<head>
	<script src="/remis/javascript/jquery-1.10.2.min.js" type="text/javascript"></script>
	<script src="/remis/javascript/jquery-ui.js" type="text/javascript"></script>
	<script type="text/javascript" src="/remis/javascript/commonscript.js"></script>
</head>

<body>
<s:fielderror cssClass="errorMsg"/>
<s:actionerror cssClass="errorMsg"/>
<s:actionmessage cssClass="infoMsg"/>

<s:form name="caseform" action="updatecase" method="post"  theme="simple" validate="true" >
   
  	<div class="row" style='padding-top: 10px'>
		<div class="col-sm-4 col-xs-12"><label for="propertyId" class="pull-right"><s:text name="label.relation.property.list"/></label></div>
		<div class="col-sm-4 col-xs-12">
			<s:textfield id="propertyId" cssClass="form-control" name="attorneyVo.propertyName" readonly="true"/>
		</div>
		<div class="col-sm-4 col-xs-12">&nbsp;<s:hidden name="attorneyVo.propertyId"></s:hidden><s:hidden name="attorneyVo.caseId"></s:hidden></div>
	</div>
	 
	<div class="row" style='padding-top: 20px'>
		<div class="col-sm-4 col-xs-12"><label for="caseName" class="pull-right"><s:text name="label.caseName"/></label></div>
		<div class="col-sm-4 col-xs-12"><s:textfield id="caseName" name="attorneyVo.caseName"  cssClass="form-control"  readonly="true" /></div>
		<div class="col-sm-4 col-xs-12">&nbsp;</div>
	</div>
	
	<div class="row" style='padding-top: 20px'>
		<div class="col-sm-4 col-xs-12"><label for="courtName" class="pull-right"><s:text name="label.courtName"/></label></div>
		<div class="col-sm-4 col-xs-12"><s:textfield id="courtName" name="attorneyVo.courtName"  cssClass="form-control" maxlength="50" /></div>
		<div class="col-sm-4 col-xs-12">&nbsp;</div>
	</div>
	
	<div class="row" style='padding-top: 20px'>
		<div class="col-sm-4 col-xs-12"><label for="startDate" class="pull-right"><s:text name="label.startDate"/></label></div>
		<div class="col-sm-4 col-xs-12">
		      <s:textfield id="startDate" name="attorneyVo.startDate" cssClass="form-control" cssStyle="width:92%;display:inline" maxlength="10" onchange="checkDate(this)"/>
			<img border="0" id="startDate" src="<s:url value='./images/calendar.gif'/>" title="Date Selector" class="inline-display"
			onclick="getCalendar(this,'startDate')" onmouseover="this.style.background='red';" onmouseout="this.style.background=''"/>
		
		</div>
		<div class="col-sm-4 col-xs-12">&nbsp;</div>
	</div>
	
	<div class="row" style='padding-top: 20px'>
		<div class="col-sm-4 col-xs-12"><label for="endDate" class="pull-right"><s:text name="label.endDate"/></label></div>
		<div class="col-sm-4 col-xs-12">
              <s:textfield id="closeDate" name="attorneyVo.closeDate" cssClass="form-control" cssStyle="width:92%;display:inline" maxlength="10" onchange="checkDate(this)"/>
			<img border="0" id="closeDate" src="<s:url value='./images/calendar.gif'/>" title="Date Selector" class="inline-display"
			onclick="getCalendar(this,'closeDate')" onmouseover="this.style.background='red';" onmouseout="this.style.background=''"/>
			</div>
		<div class="col-sm-4 col-xs-12">&nbsp;</div>
	</div>
	
	<div class="row" style='padding-top: 20px'>
		<div class="col-sm-4 col-xs-12"><label for="petitioner" class="pull-right"><s:text name="label.petitioner"/></label></div>
		<div class="col-sm-4 col-xs-12"><s:textfield id="petitioner" name="attorneyVo.petitioner"  cssClass="form-control" required="true"/></div>
		<div class="col-sm-4 col-xs-12">&nbsp;</div>
	</div>
	
	<div class="row" style='padding-top: 20px'>
		<div class="col-sm-4 col-xs-12"><label for="respondent" class="pull-right"><s:text name="label.respondent"/></label></div>
		<div class="col-sm-4 col-xs-12"><s:textfield id="respondent" name="attorneyVo.respondent"  cssClass="form-control" /></div>
		<div class="col-sm-4 col-xs-12">&nbsp;</div>
	</div>
	
	<div class="row" style='padding-top: 20px'>
		<div class="col-sm-4 col-xs-12"><label for=status class="pull-right"><s:text name="label.status"/></label></div>
		<div class="col-sm-4 col-xs-12"><s:select id="status" name="attorneyVo.status"  cssClass="form-control" list="#{'Filed':'Filed','Responded':'Responded','Pending':'Pending','Hearing':'Hearing','Closed':'Closed'}"  /></div>
		<div class="col-sm-4 col-xs-12">&nbsp;</div>
	</div>
	
	
 	<div class="row" style='padding-top: 20px'>
		<div class="col-sm-4 col-xs-12">&nbsp;</div>
		<div class="col-sm-4 col-xs-12">
		<s:if test="%{attorneyVo.updateaccess==true}">
	    <s:submit type="button" value="Update Case" cssClass="submit"></s:submit>
		</s:if>
		 <input type="button" value="Back" class="submit" onclick="JavaScript:history.go(-1)">
		</div>
		<div class="col-sm-4 col-xs-12">&nbsp;</div>
	</div>
	<div class="row col-sm-12 col-xs-4">&nbsp;</div>
<!-- </div> -->
</s:form>
</body>