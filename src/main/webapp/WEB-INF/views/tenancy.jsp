<%@ taglib prefix="s" uri="/struts-tags"%>
<h2 align="center"><s:text name="page.heading.payrent"/></h2>
<head>
	<script src="/remis/javascript/jquery-1.10.2.min.js" type="text/javascript"></script>
	<script src="/remis/javascript/jquery-ui.js" type="text/javascript"></script>
	<script type="text/javascript" src="/remis/javascript/commonscript.js"></script>
</head>

<script type="text/javascript">

/* function payRent(){
	payrentform.action='payRent';
	payrentform.submit();
}
 */
</script>



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
			<th class="table-cell" colspan="7">Manage Rents</th>
		</tr>
		</thead>
	   <thead class="table-row">
	   <tr>
			<th class="table-cell">Property Name</th>
			<th class="table-cell">Owner Name</th>
			<th class="table-cell">Amount</th>
			<th class="table-cell">Due Date</th>
			<th class="table-cell">Paid Date</th>
			<th class="table-cell">Paid Status</th>
		</tr>
		</thead>
		<s:iterator value="propRentList" var="propRentInfoVo">
	        <tr class="table-row">
				<td class="table-cell"><s:property value="propName" /></td>
				<td class="table-cell"><s:property value="tntName" /></td>
				<td class="table-cell"><s:property value="rentamount" /></td>
				<td class="table-cell"><s:property value="duedate" /></td>
				<td class="table-cell"><s:property value="rentterms" /></td>
				<td class="table-cell">
				<s:if test="%{status.equalsIgnoreCase('Not Paid')}"><a href="payment?payrentid=<s:property value="id" />"> Pay Rent</a></s:if></td>
		    </tr>
		    </s:iterator>
	   </table>
	</div>
	</div>
	</div>
	
</s:form>
</body>
<p class="min_height_100">&nbsp;</p>