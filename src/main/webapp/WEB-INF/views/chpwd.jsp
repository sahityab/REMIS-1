<%@ taglib prefix="s" uri="/struts-tags"%>
<h2 align="center"><s:text name="page.heading.change.pwd"/></h2>
<head>
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<link rel="stylesheet" href="/remis/css/bootstrap.css" media="all">
		<link rel="stylesheet" href="/remis/css/dnr.css" media="all">
		<link rel="stylesheet" href="/remis/css/jquery-ui.css" media="all">		
</head>
<body>
<s:fielderror cssClass="errorMsg"/>
<s:actionerror cssClass="errorMsg"/>
<s:actionmessage cssClass="infoMsg"/>
<s:form action="submitchangepwd" method="post" validate="true" theme="simple">	
	<div class="row">
		<div class="col-sm-4 col-xs-12">&nbsp;</div>
		<div class="col-sm-4 col-xs-12">&nbsp;</div>
		<div class="col-sm-4 col-xs-12">&nbsp;</div>
	</div>
	<div class="row">
		<div class="col-sm-4 col-xs-12">&nbsp;</div>
		<div class="col-sm-4 col-xs-12">&nbsp;</div>
		<div class="col-sm-4 col-xs-12">&nbsp;</div>
	</div>
	<div class="row">
		<div class="col-sm-5 col-xs-12"><label for="useridId" class="pull-right"><s:text name="label.user.id"/></label></div>
		<div class="col-sm-3 col-xs-12"><s:textfield id="useridId" name="userid" key="userid" cssClass="form-control" maxlength="10" onKeyPress="return restrictSpecialChars(this,event);"/></div>
		<div class="col-sm-4 col-xs-12">&nbsp;</div>
	</div>
	<div class="row">
		<div class="col-sm-4 col-xs-12">&nbsp;</div>
		<div class="col-sm-4 col-xs-12">&nbsp;</div>
		<div class="col-sm-4 col-xs-12">&nbsp;</div>
	</div>
	<div class="row">
		<div class="col-sm-5 col-xs-12"><label for="passwrdId" class="pull-right"><s:text name="label.user.password"/></label></div>
		<div class="col-sm-3 col-xs-12">
			<s:password id="passwrdId" name="passwrd" key="passwrd" cssClass="form-control" maxlength="10" />	
		</div>	
		<div class="col-sm-4 col-xs-12">&nbsp;</div>
	</div>
	<div class="row">
		<div class="col-sm-4 col-xs-12">&nbsp;</div>
		<div class="col-sm-4 col-xs-12">&nbsp;</div>
		<div class="col-sm-4 col-xs-12">&nbsp;</div>
	</div>
	<div class="row">
		<div class="col-sm-5 col-xs-12"><label for="newpasswrdId" class="pull-right"><s:text name="label.new.password"/>:</label></div>
		<div class="col-sm-3 col-xs-12">
			<s:password id="newpasswrdId" name="newpasswrd" key="newpasswrd" cssClass="form-control" maxlength="10" />	
		</div>	
		<div class="col-sm-4 col-xs-12">&nbsp;</div>
	</div>
	<div class="row">
		<div class="col-sm-4 col-xs-12">&nbsp;</div>
		<div class="col-sm-4 col-xs-12">&nbsp;</div>
		<div class="col-sm-4 col-xs-12">&nbsp;</div>
	</div>
	<div class="row">
		<div class="col-sm-5 col-xs-12"><label for="confirmPasswrdId" class="pull-right"><s:text name="label.confirm.new.password"/>:</label></div>
		<div class="col-sm-3 col-xs-12">
			<s:password id="confirmPasswrdId" name="confirmPasswrd" key="confirmPasswrd" cssClass="form-control" maxlength="10" />	
		</div>	
		<div class="col-sm-4 col-xs-12">&nbsp;</div>
	</div>
	<div class="row">
		<div class="col-sm-4 col-xs-12">&nbsp;</div>
		<div class="col-sm-4 col-xs-12">&nbsp;</div>
		<div class="col-sm-4 col-xs-12">&nbsp;</div>
	</div>
	<div class="row">
		<div class="col-sm-5 col-xs-12">&nbsp;</div>
		<div class="col-sm-4 col-xs-12">
			<span class="fieldLabels">&nbsp;</span>
			<s:submit id="changePwdId" value="Change Password" cssClass="submit"/>
			<s:reset action="changepwd" value="Clear" cssClass="submit"/>
			<s:submit action="login" value="Back" cssClass="submit"/>
		</div>
		<div class="col-sm-4 col-xs-12">&nbsp;</div>
	</div>
</s:form>
<p class="min_height_100">&nbsp;</p>
</body>