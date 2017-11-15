<%@ taglib prefix="s" uri="/struts-tags"%>
<h2 align="center"><s:text name="page.heading.rentfix"/></h2>
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
 */
function getRentTerms(propid){
	rentchangeform.action='getproptntrentinfo';
	rentchangeform.submit();
}

</script>



<body>
<s:fielderror cssClass="errorMsg"/>
<s:actionerror cssClass="errorMsg"/>
<s:actionmessage cssClass="infoMsg"/>
<s:form name="rentchangeform" action="/savepropertyrent" method="post"  theme="simple"  >
   
  	<div class="row" style='padding-top: 10px'>
		<div class="col-sm-4 col-xs-12"><label for="propertyListId" class="pull-right"><s:text name="label.relation.property.list"/></label></div>
		<div class="col-sm-4 col-xs-12">
			<s:select id="propertyID" cssClass="form-control" name="propRentInfoVo.propertyID" list="propList" listKey="propertyID" listValue="propertyName" emptyOption="false"   theme="xhtml" onchange="JavaScript:getRentTerms(this.value)"/>
		</div>
		<div class="col-sm-4 col-xs-12">&nbsp;</div>
	</div>
	 
  <div class="row" style='padding-top: 20px'>
		<div class="col-sm-4 col-xs-12"><label for="rentamount" class="pull-right"><s:text name="label.renttntname"/></label></div>
		<div class="col-sm-4 col-xs-12"><s:textfield id="tntName" name="propRentInfoVo.tntName"  cssClass="form-control" readonly="true"/></div>
		<div class="col-sm-4 col-xs-12"><s:hidden id="id" name="propRentInfoVo.id">  </s:hidden> <s:hidden id="userid" name="propRentInfoVo.propUserID">  </s:hidden>  </div>
	</div>
	
	<div class="row" style='padding-top: 20px'>
		<div class="col-sm-4 col-xs-12"><label for="rentamount" class="pull-right"><s:text name="label.rentamount"/></label></div>
		<div class="col-sm-4 col-xs-12"><s:textfield id="rentamount" name="propRentInfoVo.rentamount"  cssClass="form-control" maxlength="10" onKeyPress="return allowNumbersOnly(this,event);"/></div>
		<div class="col-sm-4 col-xs-12">&nbsp;</div>
	</div>
	
	<div class="row" style='padding-top: 20px'>
		<div class="col-sm-4 col-xs-12"><label for="rentamount" class="pull-right"><s:text name="label.rentdue"/></label></div>
		<div class="col-sm-4 col-xs-12"><s:textfield id="duedate" name="propRentInfoVo.duedate"  cssClass="form-control" maxlength="2" onKeyPress="return allowNumbersOnly(this,event);" /></div>
		<div class="col-sm-4 col-xs-12">&nbsp;</div>
	</div>
	
	<div class="row" style='padding-top: 20px'>
		<div class="col-sm-4 col-xs-12"><label for="rentamount" class="pull-right"><s:text name="label.rentterms"/></label></div>
		<div class="col-sm-4 col-xs-12"><s:textarea id="rentterms" name="propRentInfoVo.rentterms"  cssClass="form-control" rows="3"  /></div>
		<div class="col-sm-4 col-xs-12">&nbsp;</div>
	</div>
	
 	<div class="row" style='padding-top: 20px'>
		<div class="col-sm-4 col-xs-12">&nbsp;</div>
		<div class="col-sm-4 col-xs-12">
	    <s:submit type="button" value="Save" cssClass="submit"></s:submit>
		
		</div>
		<div class="col-sm-4 col-xs-12">&nbsp;</div>
	</div>
	<div class="row col-sm-12 col-xs-4">&nbsp;</div>
<!-- </div> -->
</s:form>
</body>