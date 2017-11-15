<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>
<h2 align="center"><s:text name="page.heading.propuserrelation"/></h2>
<head>
	<script src="/remis/javascript/jquery-1.10.2.min.js" type="text/javascript"></script>
	<script src="/remis/javascript/jquery-ui.js" type="text/javascript"></script>
	<script type="text/javascript" src="/remis/javascript/commonscript.js"></script>
	<%-- <script src="/remis/javascript/usermaint.js" type="text/javascript"></script> --%>
</head>

<script type="text/javascript">

function saveRelation(){
	propuserrelation.action='propuserrelation_save';
	propuserrelation.submit();
}

function deleteRelation(){
	propuserrelation.action='propuserrelation_delete';
	propuserrelation.submit();
}

/* function getRelationName(relation){
	console.log('relation='+relation);
	if(relation=='3'){
		$('#rentAmountDiv').show();
	}else {
		$('#rentAmountDiv').hide();
	}
} 

function getDivisions(propId){
if(propId=='selectproperty') return false;

$.getJSON('ajaxAction', {
	propId : propId
}, function(jsonResponse) {
  $('#ajaxResponse').text(jsonResponse.dummyMsg);
  var select = $('#subpropName');
  select.find('option').remove();
  $.each(jsonResponse.divisionMap, function(key, value) {
    $('<option>').val(key).text(value).appendTo(select);
  });
});

}
*/
</script>

<body>
<s:fielderror cssClass="errorMsg"/>
<s:actionerror cssClass="errorMsg"/>
<s:actionmessage cssClass="infoMsg"/>
<s:form name="propuserrelation" method="post"  validate="true" theme="simple">
  
  	<div class="row" style='padding-top: 20px'>
		<div class="col-sm-4 col-xs-12"><label for="propertyListId" class="pull-right"><s:text name="label.relation.property.list"/></label></div>
		<div class="col-sm-4 col-xs-12">
			<s:select id="propertyListId" cssClass="form-control" name="propUserVO.propertyID" list="propList" listKey="propertyID" listValue="propertyName" emptyOption="false"  headerKey="selectproperty" headerValue="Select Property" theme="xhtml"/>
		</div>
		<div class="col-sm-4 col-xs-12">&nbsp;</div>
	</div>
  
  	<div class="row" style='padding-top: 20px'>
		<div class="col-sm-4 col-xs-12"><label for="userListId" class="pull-right"><s:text name="label.relation.user.list"/></label></div>
		<div class="col-sm-4 col-xs-12">
			<s:select id="userListId" cssClass="form-control" name="propUserVO.userID" list="userList" listKey="id" listValue="fullName" emptyOption="false"  headerKey="selectuser" headerValue="Select User" theme="xhtml"/>
		</div>
		<div class="col-sm-4 col-xs-12">&nbsp;</div>
	</div>
	
  	 <div class="row" style='padding-top: 20px'>
		<div class="col-sm-4 col-xs-12"><label for="relationListId" class="pull-right"><s:text name="label.relation.list"/></label></div>
		<div class="col-sm-4 col-xs-12">
			<s:select id="relationListId" cssClass="form-control" name="propUserVO.relationCode"  list="relList" listKey="relationCode" listValue="relationName" emptyOption="false"  headerKey="selectrelation" headerValue="Select Relation" theme="xhtml"/>
		</div>
		<div class="col-sm-4 col-xs-12">&nbsp;</div>
	</div>
	
	<%--  <div class="row" style='padding-top: 20px'>
		<div class="col-sm-4 col-xs-12"><label for="subpropName" class="pull-right"><s:text name="label.divisionNumber"/></label></div>
		<div class="col-sm-4 col-xs-12"><s:select id="subpropName" name="propUserVO.subProperty.id" list="subPropertyList" listKey="id" listValue="subpropName"  cssClass="form-control" headerKey="selectrelation" headerValue="Select Relation"/></div>
		<div class="col-sm-4 col-xs-12">&nbsp;</div>
	</div> 	
	
	<div class="row" style='padding-top: 20px'>
		<div class="col-sm-4 col-xs-12"><label for="rentamount" class="pull-right"><s:text name="label.rentamount"/></label></div>
		<div class="col-sm-4 col-xs-12"><s:textfield id="rentAmt" name="propUserVO.rentAmt"  cssClass="form-control" maxlength="7" onKeyPress="return allowNumbersOnly(this,event);"/></div>
		<div class="col-sm-4 col-xs-12"> </div>
	</div>   --%>
	
	
 	<div class="row col-sm-12 col-xs-4">&nbsp;</div>
 	<div class="row">
		<div class="col-sm-4 col-xs-12">&nbsp;</div>
		<div class="col-sm-4 col-xs-12">
	    <input type="button" value="Save" class="submit" onclick="JavaScript:saveRelation()">
		<input type="button"  value="Delete" class="submit" onclick="JavaScript:deleteRelation()"/>
		<s:reset action="propUserRelation" id="resetId" value="Reset" cssClass="submit"/>
		</div>
		<div class="col-sm-4 col-xs-12">&nbsp;</div>
	</div>
	<div class="row col-sm-12 col-xs-4">&nbsp;</div>
<!-- </div> -->
</s:form>
</body>





<%-- 

<h2 align="center"><s:text name="page.heading.propuserrelation"/></h2>
<head>
	<script src="/remis/javascript/jquery-1.10.2.min.js" type="text/javascript"></script>
	<script src="/remis/javascript/jquery-ui.js" type="text/javascript"></script>
	<script type="text/javascript" src="/remis/javascript/commonscript.js"></script>

</head>

<script type="text/javascript">

function saveRelation(){
	propuserrelation.action='propuserrelation_save';
	propuserrelation.submit();
}

function getPropertyData(value){
	console.log('get sub properties='+value);
	if(value=='selectproperty') return false;
	propuserrelation.action='getsubproperties';
	propuserrelation.submit();
}
</script>



<body>
<s:fielderror cssClass="errorMsg"/>
<s:actionerror cssClass="errorMsg"/>
<s:actionmessage cssClass="infoMsg"/>
  <!-- <div id="divbody" class="bodybgcolor" > -->
   <div class="row col-sm-12 col-xs-12">&nbsp;</div>
  	<div class="row">
		<div class="col-sm-4 col-xs-12"><label for="userListId" class="pull-right"><s:text name="label.relation.user.list"/></label></div>
		<div class="col-sm-4 col-xs-12">
			<s:select id="userListId" cssClass="form-control" name="propUserVO.userID" list="userList" listKey="id" listValue="fullName" emptyOption="false"  headerKey="selectuser" headerValue="Select User" theme="xhtml"/>
		</div>
		<div class="col-sm-4 col-xs-12">&nbsp;</div>
	</div> 
	 <div class="row col-sm-12 col-xs-12">&nbsp;</div>
  	<div class="row">
		<div class="col-sm-4 col-xs-12"><label for="propertyListId" class="pull-right"><s:text name="label.relation.property.list"/></label></div>
		<div class="col-sm-4 col-xs-12">
			<s:select id="propertyListId" cssClass="form-control" name="propUserVO.propertyID" list="propList" listKey="propertyID" listValue="propertyName" emptyOption="false"  headerKey="selectproperty" headerValue="Select Property" theme="xhtml" onchange="getPropertyData(this.value)"/>
		</div>
		<div class="col-sm-4 col-xs-12">&nbsp;</div>
	</div>
	 <div class="row col-sm-12 col-xs-12">&nbsp;</div>
  	<div class="row">
		<div class="col-sm-4 col-xs-12"><label for="relationListId" class="pull-right"><s:text name="label.relation.list"/></label></div>
		<div class="col-sm-4 col-xs-12">
			<s:select id="relationListId" cssClass="form-control" name="propUserVO.proUserRelID"  list="relList" listKey="id" listValue="relationName" emptyOption="false"  headerKey="selectrelation" headerValue="Select Relation" theme="xhtml"/>
		</div>
		<div class="col-sm-4 col-xs-12">&nbsp;</div>
	</div> --%>
<!-- 	<div class="row">
		<div class="col-sm-4 col-xs-12">&nbsp;</div>
		<div class="col-sm-4 col-xs-12"><font color="#FF0000">*</font>shows Mandatory</div>
		<div class="col-sm-4 col-xs-12">&nbsp;</div>
	</div>
	
 	<div class="row col-sm-12 col-xs-4">&nbsp;</div>
 	<div class="row">
		<div class="col-sm-4 col-xs-12">&nbsp;</div>
		<div class="col-sm-4 col-xs-12">
	    <input type="button" value="Save" class="submit" onclick="JavaScript:saveRelation()">
		<%-- <s:submit action="deleteRelation" id="deleteId" value="Delete" cssClass="submit" /> --%>
		<s:reset action="propUserRelation" id="resetId" value="Reset" cssClass="submit"/>
		</div>
		<div class="col-sm-4 col-xs-12">&nbsp;</div>
	</div>
	 
	<div class="row col-sm-12 col-xs-4">&nbsp;</div>-->
<!-- </div> 
</body> -->

