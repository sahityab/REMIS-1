<%@ taglib prefix="s" uri="/struts-tags"%>
<h2 align="center"> <s:text name="page.heading.assincontracts"/></h2>
<head>
	<script src="/remis/javascript/jquery-1.10.2.min.js" type="text/javascript"></script>
	<script src="/remis/javascript/jquery-ui.js" type="text/javascript"></script>
	<script type="text/javascript" src="/remis/javascript/commonscript.js"></script>
</head>


<body>
<s:fielderror cssClass="errorMsg"/>
<s:actionerror cssClass="errorMsg"/>
<s:actionmessage cssClass="infoMsg"/>
<s:form name="payrentform" method="post"  validate="true" theme="simple">

	  
   <div class="row" style='padding-bottom: 20px'>
	<div class="col-sm-1 col-xs-12">&nbsp;</div>
	<div class="col-sm-10 col-xs-12">&nbsp;
	<div class="table-responsive">
	<table class="table table-bordered">
	  <thead class="table-row">
	     <tr>
			<th class="table-cell" colspan="8">Manage Assigned Contracts</th>
		</tr>
		</thead>
	   <thead class="table-row">
	   <tr>
			<th class="table-cell">Property Name</th>
			<th class="table-cell">Contractor Name</th>
			<th class="table-cell">Contract Title</th>
			<th class="table-cell">Start Date</th>
			<th class="table-cell">End Date</th>
			<th class="table-cell">Value</th>
			<th class="table-cell">View</th>
			<th class="table-cell">Progress</th>
		</tr>
		</thead>
		<s:iterator value="contractorList" var="contractVo">
	        <tr class="table-row">
				<td class="table-cell"><s:property value="propertyName" /></td>
				<td class="table-cell"><s:property value="contractorName" /></td>
				<td class="table-cell"><s:property value="contractName" /></td>
				<td class="table-cell"> <s:date name="startDate" format="dd/MM/yyyy" /></td>
				<td class="table-cell"> <s:date name="endDate" format="dd/MM/yyyy" /></td>
				<td class="table-cell"><s:property value="price" /></td>
				<td class="table-cell"><a href="viewcontract?contractid=<s:property value="contractId"/>"> Contract</a></td>
				<td class="table-cell"><a href="viewcontractprogress?contractid=<s:property value="contractId"/>"> View</a></td>
		    </tr>
		    </s:iterator>
	   </table>
	</div>
	</div>
	</div>
	
</s:form>
</body>
<p class="min_height_200">&nbsp;</p>