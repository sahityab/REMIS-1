<%@ taglib prefix="s" uri="/struts-tags"%>
<h2 align="center"><s:text name="page.heading.users"/></h2>
<head>
	<script src="/remis/javascript/jquery-1.10.2.min.js" type="text/javascript"></script>
	<script src="/remis/javascript/jquery-ui.js" type="text/javascript"></script>
	<script type="text/javascript" src="/remis/javascript/commonscript.js"></script>
	<script src="/remis/javascript/usermaint.js" type="text/javascript"></script>
</head>
<body>
<s:fielderror cssClass="errorMsg"/>
<s:actionerror cssClass="errorMsg"/>
<s:actionmessage cssClass="infoMsg"/>
<s:form name="users1" method="post" action="saveuser" validate="true" theme="simple">
  <!-- <div id="divbody" class="bodybgcolor" > -->
  <div class="row col-sm-12 col-xs-12">&nbsp; <s:hidden id="userVo.id" name="userVo.id"></s:hidden> </div>
  	<div class="row">
		<div class="col-sm-4 col-xs-12"><label for="userListId" class="pull-right"><s:text name="label.user.list"/></label></div>
		<div class="col-sm-4 col-xs-12">
			<s:select id="userListId" cssClass="form-control" name="userVo.userId" list="userList" listKey="userId" listValue="fullName" emptyOption="false"  headerKey="create" headerValue="Create new" theme="xhtml"/>
		</div>
		<div class="col-sm-4 col-xs-12">&nbsp;</div>
	</div>
	<div class="row col-sm-12 col-xs-4">&nbsp;</div>
	<div class="row">
		<div class="col-sm-4 col-xs-12"><label for="newUserIdId" class="pull-right"><s:text name="label.new.user.id"/></label></div>
		<div class="col-sm-4 col-xs-12">
			<s:textfield id="newUserIdId" name="userVo.newUserId" cssClass="form-control" maxlength="10" />
		</div>
		<div class="col-sm-4 col-xs-12">&nbsp;</div>
	</div>
	<div class="row col-sm-12 col-xs-4">&nbsp;</div>
	<div class="row">
		<div class="col-sm-4 col-xs-12"><label for="passwordId" class="pull-right"><s:text name="label.user.password"/></label></div>
		<div class="col-sm-4 col-xs-12">
			<s:password id="passwordId" name="userVo.password" cssClass="form-control" maxlength="10" />
		</div>
		<div class="col-sm-4 col-xs-12">&nbsp;</div>
	</div>
	<div class="row col-sm-12 col-xs-4">&nbsp;</div>
	<div class="row">
		<div class="col-sm-4 col-xs-12"><label for="confirmPasswordId" class="pull-right"><s:text name="label.user.confirm.password"/></label></div>
		<div class="col-sm-4 col-xs-12">
			<s:password id="confirmPasswordId" name="userVo.confirmPassword" cssClass="form-control" maxlength="10" />
		</div>
		<div class="col-sm-4 col-xs-12">&nbsp;</div>
	</div>
	<div class="row col-sm-12 col-xs-4">&nbsp;</div>
	<div class="row">
		<div class="col-sm-4 col-xs-12"><label for="firstNameId" class="pull-right"><s:text name="label.user.first.name"/></label></div>
		<div class="col-sm-4 col-xs-12">
			<s:textfield id="firstNameId" name="userVo.firstName" cssClass="form-control" maxlength="20" />
		</div>
		<div class="col-sm-4 col-xs-12">&nbsp;</div>
	</div>
	<div class="row col-sm-12 col-xs-4">&nbsp;</div>
	<div class="row">
		<div class="col-sm-4 col-xs-12"><label for="lastNameId" class="pull-right"><s:text name="label.user.last.name"/></label></div>
		<div class="col-sm-4 col-xs-12">
			<s:textfield id="lastNameId" name="userVo.lastName" cssClass="form-control" maxlength="20" />
		</div>
		<div class="col-sm-4 col-xs-12">&nbsp;</div>
	</div>
	<div class="row col-sm-12 col-xs-4">&nbsp;</div>
	<div class="row">
		<div class="col-sm-4 col-xs-12"><label for="contNoId" class="pull-right"><s:text name="label.user.contact.no"/></label></div>
		<div class="col-sm-4 col-xs-12">
			<s:textfield id="contNoId" name="userVo.contNo" cssClass="form-control" maxlength="12" />
		</div>
		<div class="col-sm-4 col-xs-12">&nbsp;</div>
	</div>
	<div class="row col-sm-12 col-xs-4">&nbsp;</div>
	<div class="row">
		<div class="col-sm-4 col-xs-12"><label for="address1Id" class="pull-right"><s:text name="label.user.address1"/></label></div>
		<div class="col-sm-4 col-xs-12">
			<s:textfield id="address1Id" name="userVo.address1" cssClass="form-control" maxlength="20" />
		</div>
		<div class="col-sm-4 col-xs-12">&nbsp;</div>
	</div>
	<div class="row col-sm-12 col-xs-4">&nbsp;</div>
	<div class="row">
		<div class="col-sm-4 col-xs-12"><label for="address2Id" class="pull-right"><s:text name="label.user.address2"/></label></div>
		<div class="col-sm-4 col-xs-12">
			<s:textfield id="address2Id" name="userVo.address2" cssClass="form-control" maxlength="20" />
		</div>
		<div class="col-sm-4 col-xs-12">&nbsp;</div>
	</div>
	<div class="row col-sm-12 col-xs-4">&nbsp;</div>
	<div class="row">
		<div class="col-sm-4 col-xs-12"><label for="cityId" class="pull-right"><s:text name="label.user.city"/></label></div>
		<div class="col-sm-4 col-xs-12">
			<s:textfield id="cityId" name="userVo.city" cssClass="form-control" maxlength="20" />
		</div>
		<div class="col-sm-4 col-xs-12">&nbsp;</div>
	</div>
	<div class="row col-sm-12 col-xs-4">&nbsp;</div>
	<div class="row">
		<div class="col-sm-4 col-xs-12"><label for="stateId" class="pull-right"><s:text name="label.user.state"/></label></div>
		<div class="col-sm-4 col-xs-12">
			<s:select id="stateId" cssClass="form-control" name="userVo.state" list="stateList" listKey="key" listValue="value" headerKey="select" headerValue="Please select"/>
		</div>
		<div class="col-sm-4 col-xs-12">&nbsp;</div>
	</div>
	
	
	<div class="row col-sm-12 col-xs-4">&nbsp;</div>
	<div class="row">
		<div class="col-sm-4 col-xs-12"><label for="zipId" class="pull-right"><s:text name="label.user.zip"/></label></div>
		<div class="col-sm-4 col-xs-12">
			<s:textfield id="zipId" name="userVo.zip" cssClass="form-control" maxlength="5" />
		</div>
		<div class="col-sm-4 col-xs-12">&nbsp;</div>
	</div>
	<div class="row col-sm-12 col-xs-4">&nbsp;</div>
	<div class="row">
		<div class="col-sm-4 col-xs-12"><label for="emailId" class="pull-right"><s:text name="label.user.email"/></label></div>
		<div class="col-sm-4 col-xs-12">
			<s:textfield id="emailId" name="userVo.email" cssClass="form-control" maxlength="40" />
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
		<s:submit action="deleteuser" id="deleteId" value="Delete" cssClass="submit"/>
		<s:submit action="usermanagement" id="resetId" value="Reset" cssClass="submit"/>
		</div>
		<div class="col-sm-4 col-xs-12">&nbsp;</div>
	</div>
	<div class="row col-sm-12 col-xs-4">&nbsp;</div>
<!-- </div> -->
</s:form>
</body>