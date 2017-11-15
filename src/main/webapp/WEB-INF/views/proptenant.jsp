<%@ taglib prefix="s" uri="/struts-tags"%>
<h2 align="center"><s:text name="page.heading.managerents"/></h2>
<head>
	<script src="/remis/javascript/jquery-1.10.2.min.js" type="text/javascript"></script>
	<script src="/remis/javascript/jquery-ui.js" type="text/javascript"></script>
	<script type="text/javascript" src="/remis/javascript/commonscript.js"></script>
</head>

<script type="text/javascript">
 
 function getPropertyData(value){
		console.log('get sub properties='+value);
		if(value=='selectproperty') return false;
		propuserrelation.action='getpropertytenants';
		propuserrelation.submit();
	} 

</script>



<body>
<s:fielderror cssClass="errorMsg"/>
<s:actionerror cssClass="errorMsg"/>
<s:actionmessage cssClass="infoMsg"/>
<s:form name="subpropform" method="post"  validate="true" theme="simple">
	
	<div class="col-sm-12 col-xs-12"><div id='errorDiv'  style="color: red;"></div></div>  
	
    <div class="row" style='padding-bottom: 10px'>
		<div class="col-sm-4 col-xs-12"><label for="propertyListId" class="pull-right"><s:text name="label.relation.property.list"/></label></div>
		<div class="col-sm-4 col-xs-12">
			<s:select id="propertyListId" cssClass="form-control" name="propUserVO.propertyID" list="propList" listKey="propertyID" listValue="propertyName" emptyOption="false"  theme="xhtml" onchange="getPropertyData(this.value)"/>
		</div>
		<div class="col-sm-4 col-xs-12">&nbsp;</div>
	</div>
	
   
	  
   <div class="row" style='padding-bottom: 20px'>
	<div class="col-sm-1 col-xs-12">&nbsp;</div>
	<div class="col-sm-10 col-xs-12">&nbsp;
	<div class="table-responsive">
	<table class="table table-bordered">
	 <thead class="table-row">
	     <tr>
			<th class="table-cell" colspan="7">Tenant Properties</th>
		</tr>
		</thead>
	   <thead class="table-row">
	   <tr>
	        <th class="table-cell">Property</th>
			<th class="table-cell">User</th>
			<th class="table-cell">Division </th>
			<th class="table-cell">Rent</th>
		</tr>
		</thead>
		
		<s:iterator value="rentPropList" var="subProperty">
	        <tr class="table-row">
	        <td class="table-cell"><s:property value="propName" /></td>
				<td class="table-cell"><s:property value="userName" /></td>
				<td class="table-cell"><s:property value="divisionInfo" /></td>
				<td class="table-cell"><s:property value="rentamount" /></td>
		    </tr>
		    </s:iterator>
	   </table>
	</div>
	</div>
	</div>
	
</s:form>
</body>
<p class="min_height_100">&nbsp;</p>