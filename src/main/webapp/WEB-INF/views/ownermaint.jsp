<%@ taglib prefix="s" uri="/struts-tags"%>
<h2 align="center"><s:text name="page.heading.owners"/></h2>
<head>
	<script src="/remis/javascript/jquery-1.10.2.min.js" type="text/javascript"></script>
	<script src="/remis/javascript/jquery-ui.js" type="text/javascript"></script>
	<script type="text/javascript" src="/remis/javascript/commonscript.js"></script>
	<script src="/remis/javascript/ownermaint.js" type="text/javascript"></script>
</head>
<body>
<s:fielderror cssClass="errorMsg"/>
<s:actionerror cssClass="errorMsg"/>
<s:actionmessage cssClass="infoMsg"/>
<s:form name="owners" method="post" action="saveowner" validate="true" theme="simple">
  <!-- <div id="divbody" class="bodybgcolor" > -->
  <div class="row col-sm-12 col-xs-12">&nbsp;</div>
  	<div class="row">
		<div class="col-sm-4 col-xs-12"><label for="ownerListId" class="pull-right"><s:text name="label.owner.list"/></label></div>
		<div class="col-sm-4 col-xs-12">
			<s:select id="ownerListId" cssClass="form-control" name="ownerVo.ownCode" list="ownerList" listKey="ownCode" listValue="fullName" emptyOption="false"  headerKey="0" headerValue="Create new" theme="xhtml"/>
		</div>
		<div class="col-sm-4 col-xs-12">&nbsp;</div>
	</div>
	<div class="row col-sm-12 col-xs-4">&nbsp;</div>
	<div class="row">
		<div class="col-sm-4 col-xs-12"><label for="firstNameId" class="pull-right"><s:text name="label.user.first.name"/></label></div>
		<div class="col-sm-4 col-xs-12">
			<s:textfield id="firstNameId" name="ownerVo.ownFName" cssClass="form-control" maxlength="40" />
		</div>
		<div class="col-sm-4 col-xs-12">&nbsp;</div>
	</div>
	<div class="row col-sm-12 col-xs-4">&nbsp;</div>
	<div class="row">
		<div class="col-sm-4 col-xs-12"><label for="middleNameId" class="pull-right"><s:text name="label.owner.middle.name"/></label></div>
		<div class="col-sm-4 col-xs-12">
			<s:textfield id="middleNameId" name="ownerVo.ownMName" cssClass="form-control" maxlength="40" />
		</div>
		<div class="col-sm-4 col-xs-12">&nbsp;</div>
	</div>
	<div class="row col-sm-12 col-xs-4">&nbsp;</div>
	<div class="row">
		<div class="col-sm-4 col-xs-12"><label for="lastNameId" class="pull-right"><s:text name="label.user.last.name"/></label></div>
		<div class="col-sm-4 col-xs-12">
			<s:textfield id="lastNameId" name="ownerVo.ownLName" cssClass="form-control" maxlength="40" />
		</div>
		<div class="col-sm-4 col-xs-12">&nbsp;</div>
	</div>
	<div class="row col-sm-12 col-xs-4">&nbsp;</div>
	<div class="row">
		<div class="col-sm-4 col-xs-12"><label for="ownTypeId" class="pull-right"><s:text name="label.owner.own.type"/></label></div>
		<div class="col-sm-4 col-xs-12">
			<s:select id="ownTypeId" cssClass="form-control" name="ownerVo.ownType" list="ownTypeList" listKey="key" listValue="value" />
		</div>
		<div class="col-sm-4 col-xs-12">&nbsp;</div>
	</div>
	<div class="row col-sm-12 col-xs-4">&nbsp;</div>
	<div class="row">
		<div class="col-sm-4 col-xs-12"><label for="ownSsnId" class="pull-right"><s:text name="label.owner.own.ssn"/></label></div>
		<div class="col-sm-4 col-xs-12">
			<s:textfield id="ownSsnId" name="ownerVo.ownSsn" cssClass="form-control" maxlength="9" />
		</div>
		<div class="col-sm-4 col-xs-12">&nbsp;</div>
	</div>
	<div class="row col-sm-12 col-xs-4">&nbsp;</div>
	<div class="row">
		<div class="col-sm-4 col-xs-12"><label for="address1Id" class="pull-right"><s:text name="label.user.address1"/></label></div>
		<div class="col-sm-4 col-xs-12">
			<s:textfield id="address1Id" name="ownerVo.address1" cssClass="form-control" maxlength="20" />
		</div>
		<div class="col-sm-4 col-xs-12">&nbsp;</div>
	</div>
	<div class="row col-sm-12 col-xs-4">&nbsp;</div>
	<div class="row">
		<div class="col-sm-4 col-xs-12"><label for="address2Id" class="pull-right"><s:text name="label.user.address2"/></label></div>
		<div class="col-sm-4 col-xs-12">
			<s:textfield id="address2Id" name="ownerVo.address2" cssClass="form-control" maxlength="20" />
		</div>
		<div class="col-sm-4 col-xs-12">&nbsp;</div>
	</div>
	<div class="row col-sm-12 col-xs-4">&nbsp;</div>
	<div class="row">
		<div class="col-sm-4 col-xs-12"><label for="cityId" class="pull-right"><s:text name="label.user.city"/></label></div>
		<div class="col-sm-4 col-xs-12">
			<s:textfield id="cityId" name="ownerVo.city" cssClass="form-control" maxlength="20" />
		</div>
		<div class="col-sm-4 col-xs-12">&nbsp;</div>
	</div>
	<div class="row col-sm-12 col-xs-4">&nbsp;</div>
	<div class="row">
		<div class="col-sm-4 col-xs-12"><label for="stateId" class="pull-right"><s:text name="label.user.state"/></label></div>
		<div class="col-sm-4 col-xs-12">
			<s:select id="stateId" cssClass="form-control" name="ownerVo.stateCd" list="stateList" listKey="key" listValue="value" headerKey="select" headerValue="Please select"/>
		</div>
		<div class="col-sm-4 col-xs-12">&nbsp;</div>
	</div>
	<div class="row col-sm-12 col-xs-4">&nbsp;</div>
	<div class="row">
		<div class="col-sm-4 col-xs-12"><label for="countryId" class="pull-right"><s:text name="label.owner.country"/></label></div>
		<div class="col-sm-4 col-xs-12">
			<s:select id="countryId" cssClass="form-control" name="ownerVo.country" list="countryList" headerKey="select" headerValue="Please select"/>
		</div>
		<div class="col-sm-4 col-xs-12">&nbsp;</div>
	</div>
	<div class="row col-sm-12 col-xs-4">&nbsp;</div>
	<div class="row">
		<div class="col-sm-4 col-xs-12"><label for="zipId" class="pull-right"><s:text name="label.user.zip"/></label></div>
		<div class="col-sm-4 col-xs-12">
			<s:textfield id="zipId" name="ownerVo.zip" cssClass="form-control" maxlength="5" />
		</div>
		<div class="col-sm-4 col-xs-12">&nbsp;</div>
	</div>
	<div class="row col-sm-12 col-xs-4">&nbsp;</div>
	<div class="row">
		<div class="col-sm-4 col-xs-12"><label for="phoneId" class="pull-right"><s:text name="label.owner.phone"/></label></div>
		<div class="col-sm-4 col-xs-12">
			<s:textfield id="phoneId" name="ownerVo.phone" cssClass="form-control" maxlength="10" />
		</div>
		<div class="col-sm-4 col-xs-12">&nbsp;</div>
	</div>
	<div class="row col-sm-12 col-xs-4">&nbsp;</div>
	<div class="row">
		<div class="col-sm-4 col-xs-12"><label for="faxId" class="pull-right"><s:text name="label.owner.fax"/></label></div>
		<div class="col-sm-4 col-xs-12">
			<s:textfield id="faxId" name="ownerVo.fax" cssClass="form-control" maxlength="10" />
		</div>
		<div class="col-sm-4 col-xs-12">&nbsp;</div>
	</div>
	<div class="row col-sm-12 col-xs-4">&nbsp;</div>
	<div class="row">
		<div class="col-sm-4 col-xs-12"><label for="emailId" class="pull-right"><s:text name="label.user.email"/></label></div>
		<div class="col-sm-4 col-xs-12">
			<s:textfield id="emailId" name="ownerVo.email" cssClass="form-control" maxlength="40" />
		</div>
		<div class="col-sm-4 col-xs-12">&nbsp;</div>
	</div>
<!-- 	<div class="row">
		<div class="col-sm-4 col-xs-12">&nbsp;</div>
		<div class="col-sm-4 col-xs-12"><font color="#FF0000">*</font>shows Mandatory</div>
		<div class="col-sm-4 col-xs-12">&nbsp;</div>
	</div>
 -->	
 	<div class="row col-sm-12 col-xs-4">&nbsp;</div>
 	<div class="row">
		<div class="col-sm-4 col-xs-12">&nbsp;</div>
		<div class="col-sm-4 col-xs-12">
		<s:submit id="saveId" value="Save" cssClass="submit"/>
		<s:submit action="deleteowner" id="deleteId" value="Delete" cssClass="submit"/>
		<s:submit action="ownermanagement" id="resetId" value="Reset" cssClass="submit"/>
		</div>
		<div class="col-sm-4 col-xs-12">&nbsp;</div>
	</div>
	<div class="row col-sm-12 col-xs-4">&nbsp;</div>
<!-- </div> -->
</s:form>
</body>