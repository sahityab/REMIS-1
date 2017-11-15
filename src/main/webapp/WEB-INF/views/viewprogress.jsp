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
<s:form name="contractform"   theme="simple" validate="true" >
   
	
	<div class="row" style='padding-top: 20px'>
		<div class="col-sm-4 col-xs-12"><label for="contractTitle" class="pull-right"><s:text name="label.contractTitle"/></label></div>
		<div class="col-sm-4 col-xs-12"><s:textfield id="contractName" name="contractVo.contractName"  cssClass="form-control"  /></div>
		<div class="col-sm-4 col-xs-12">&nbsp;<s:hidden id="propertyID"  name="contractVo.contractId"></s:hidden></div>
	</div>
	
	
	<div class="row" style='padding-top: 20px'>
		<div class="col-sm-4 col-xs-12"><label for="rentamount" class="pull-right"><s:text name="label.progressDesc"/></label></div>
		<div class="col-sm-4 col-xs-12"><s:textarea id="rentterms" name="contractVo.workDesc"  cssClass="form-control" rows="5"  /></div>
		<div class="col-sm-4 col-xs-12">&nbsp;</div>
	</div>
	
	<div class="row" style='padding-top: 20px'>
		<div class="col-sm-4 col-xs-12"><label for="contractTC" class="pull-right"><s:text name="label.issues"/></label></div>
		<div class="col-sm-4 col-xs-12"><s:textarea id="contractTC" name="contractVo.issues"  cssClass="form-control" rows="5"  /></div>
		<div class="col-sm-4 col-xs-12">&nbsp;</div>
	</div>
	
	<div class="row" style='padding-top: 20px'>
		<div class="col-sm-4 col-xs-12"><label for="contractTC" class="pull-right"><s:text name="label.actionneeded"/></label></div>
		<div class="col-sm-4 col-xs-12"><s:select id="contractTC" name="contractVo.issues"  cssClass="form-control" list="#{'NO':'NO', 'YES':'YES'}"  /></div>
		<div class="col-sm-4 col-xs-12">&nbsp;</div>
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