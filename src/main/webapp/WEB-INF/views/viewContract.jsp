<%@ taglib prefix="s" uri="/struts-tags"%>
<h2 align="center"><s:text name="page.heading.viewcontract"/></h2>
<head>
	<script src="/remis/javascript/jquery-1.10.2.min.js" type="text/javascript"></script>
	<script src="/remis/javascript/jquery-ui.js" type="text/javascript"></script>
	<script type="text/javascript" src="/remis/javascript/commonscript.js"></script>
</head>

<body>

<s:form name="contractform"  theme="simple" >
   
  	<div class="row" style='padding-top: 10px'>
		<div class="col-sm-4 col-xs-12"><label for="propertyListId" class="pull-right"><s:text name="label.relation.property.list"/></label></div>
		<div class="col-sm-4 col-xs-12">
			<s:textfield id="propertyID" cssClass="form-control" name="contractVo.propertyName"/>
		</div>
		<div class="col-sm-4 col-xs-12">&nbsp;</div>
	</div>
	 
  <div class="row" style='padding-top: 20px'>
		<div class="col-sm-4 col-xs-12"><label for="contractorname" class="pull-right"><s:text name="label.contractorname"/></label></div>
		<div class="col-sm-4 col-xs-12"><s:textfield  id="contractorName" name="contractVo.contractorName" cssClass="form-control"/> </div>
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
		      <s:textfield id="startDate" name="contractVo.startDate" cssClass="form-control" cssStyle="width:92%;display:inline" maxlength="10"/>
			
		
		</div>
		<div class="col-sm-4 col-xs-12">&nbsp;</div>
	</div>
	
	<div class="row" style='padding-top: 20px'>
		<div class="col-sm-4 col-xs-12"><label for="endDate" class="pull-right"><s:text name="label.endDate"/></label></div>
		<div class="col-sm-4 col-xs-12">
              <s:textfield id="endDate" name="contractVo.endDate" cssClass="form-control" cssStyle="width:92%;display:inline" maxlength="10" />
			
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
	    <input type="button" value="Back" class="submit" onclick="JavaScript:history.go(-1)">
		
		</div>
		<div class="col-sm-4 col-xs-12">&nbsp;</div>
	</div>
	<div class="row col-sm-12 col-xs-4">&nbsp;</div>
<!-- </div> -->
</s:form>
</body>