<%@ taglib prefix="s" uri="/struts-tags"%>
<h2 align="center"><s:text name="page.heading.assigncontrat"/></h2>
<%@ include file="calendar.jsp"%>
<head>
	<script src="/remis/javascript/jquery-1.10.2.min.js" type="text/javascript"></script>
	<script src="/remis/javascript/jquery-ui.js" type="text/javascript"></script>
	<script type="text/javascript" src="/remis/javascript/commonscript.js"></script>
	<%-- <script src="/remis/javascript/usermaint.js" type="text/javascript"></script> --%>
</head>

<script type="text/javascript">

/* function savePropertyRentTerms(){
	rentchangeform.action='savepropertyrent';
	rentchangeform.submit();
}
 
function getContractors(propid){
	 contractform.action='getcontractrsinfo';
	 contractform.submit();
}*/

 function getContractors(propId){
	 if(propId=='selectproperty') { var select = $('#contratorId'); select.find('option').remove();return false;}

	 $.getJSON('ajaxAction', {
	 	propId : propId
	 }, function(jsonResponse) {
	   $('#ajaxResponse').text(jsonResponse.dummyMsg);
	   var select = $('#contratorId');
	   select.find('option').remove();
	   $.each(jsonResponse.divisionMap, function(key, value) {
	     $('<option>').val(key).text(value).appendTo(select);
	   });
	 });

	 } 
 
 
</script>


<body>
<s:fielderror cssClass="errorMsg"/>
<s:actionerror cssClass="errorMsg"/>
<s:actionmessage cssClass="infoMsg"/>
<s:form name="contractform" action="/savecontract" method="post"  theme="simple" validate="true" >
   
  	<div class="row" style='padding-top: 10px'>
		<div class="col-sm-4 col-xs-12"><label for="propertyListId" class="pull-right"><s:text name="label.relation.property.list"/></label></div>
		<div class="col-sm-4 col-xs-12">
			<s:select id="propertyID" cssClass="form-control" name="contractVo.propertyId" list="propList" listKey="propertyID" listValue="propertyName" emptyOption="false"  headerKey="selectproperty" headerValue="Select Property"   theme="xhtml" onchange="JavaScript:getContractors(this.value)"/>
		</div>
		<div class="col-sm-4 col-xs-12">&nbsp;</div>
	</div>
	 
  <div class="row" style='padding-top: 20px'>
		<div class="col-sm-4 col-xs-12"><label for="contractorname" class="pull-right"><s:text name="label.contractorname"/></label></div>
		<div class="col-sm-4 col-xs-12"><s:select  id="contratorId" name="contractVo.contratorId" list="contractorList" listKey="contratorId" listValue="contractorName" cssClass="form-control"/> </div>
		<div class="col-sm-4 col-xs-12">  </div>
	</div>
	
	<div class="row" style='padding-top: 20px'>
		<div class="col-sm-4 col-xs-12"><label for="contractTitle" class="pull-right"><s:text name="label.contractTitle"/></label></div>
		<div class="col-sm-4 col-xs-12"><s:textfield id="contractName" name="contractVo.contractName"  cssClass="form-control" maxlength="50" /></div>
		<div class="col-sm-4 col-xs-12">&nbsp;</div>
	</div>
	
	<div class="row" style='padding-top: 20px'>
		<div class="col-sm-4 col-xs-12"><label for="startDate" class="pull-right"><s:text name="label.startDate"/></label></div>
		<div class="col-sm-4 col-xs-12">
		      <s:textfield id="startDate" name="contractVo.startDate" cssClass="form-control" cssStyle="width:92%;display:inline" maxlength="10" onchange="checkDate(this)"/>
			<img border="0" id="startDate" src="<s:url value='./images/calendar.gif'/>" title="Date Selector" class="inline-display"
			onclick="getCalendar(this,'startDate')" onmouseover="this.style.background='red';" onmouseout="this.style.background=''"/>
		
		</div>
		<div class="col-sm-4 col-xs-12">&nbsp;</div>
	</div>
	
	<div class="row" style='padding-top: 20px'>
		<div class="col-sm-4 col-xs-12"><label for="endDate" class="pull-right"><s:text name="label.endDate"/></label></div>
		<div class="col-sm-4 col-xs-12">
              <s:textfield id="endDate" name="contractVo.endDate" cssClass="form-control" cssStyle="width:92%;display:inline" maxlength="10" onchange="checkDate(this)"/>
			<img border="0" id="endDate" src="<s:url value='./images/calendar.gif'/>" title="Date Selector" class="inline-display"
			onclick="getCalendar(this,'endDate')" onmouseover="this.style.background='red';" onmouseout="this.style.background=''"/>
			</div>
		<div class="col-sm-4 col-xs-12">&nbsp;</div>
	</div>
	
	<div class="row" style='padding-top: 20px'>
		<div class="col-sm-4 col-xs-12"><label for="price" class="pull-right"><s:text name="label.price"/></label></div>
		<div class="col-sm-4 col-xs-12"><s:textfield id="price" name="contractVo.price"  cssClass="form-control" maxlength="8" onKeyPress="return allowNumbersOnly(this,event);" /></div>
		<div class="col-sm-4 col-xs-12">&nbsp;</div>
	</div>
	
	<div class="row" style='padding-top: 20px'>
		<div class="col-sm-4 col-xs-12"><label for="rentamount" class="pull-right"><s:text name="label.workDesc"/></label></div>
		<div class="col-sm-4 col-xs-12"><s:textarea id="rentterms" name="contractVo.workDesc"  cssClass="form-control" rows="3"  /></div>
		<div class="col-sm-4 col-xs-12">&nbsp;</div>
	</div>
	
	<div class="row" style='padding-top: 20px'>
		<div class="col-sm-4 col-xs-12"><label for="contractTC" class="pull-right"><s:text name="label.contractTC"/></label></div>
		<div class="col-sm-4 col-xs-12"><s:textarea id="contractTC" name="contractVo.contractTC"  cssClass="form-control" rows="3"  /></div>
		<div class="col-sm-4 col-xs-12">&nbsp;</div>
	</div>
	
	
	
 	<div class="row" style='padding-top: 20px'>
		<div class="col-sm-4 col-xs-12">&nbsp;</div>
		<div class="col-sm-4 col-xs-12">
	    <s:submit type="button" value="Assign Contract" cssClass="submit"></s:submit>
		
		</div>
		<div class="col-sm-4 col-xs-12">&nbsp;</div>
	</div>
	<div class="row col-sm-12 col-xs-4">&nbsp;</div>
<!-- </div> -->
</s:form>
</body>