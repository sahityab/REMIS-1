<%@ taglib prefix="s" uri="/struts-tags"%>
<h2 align="center"> <s:text name="page.heading.ownerCases"/></h2>
<head>
	<script src="/remis/javascript/jquery-1.10.2.min.js" type="text/javascript"></script>
	<script src="/remis/javascript/jquery-ui.js" type="text/javascript"></script>
	<script type="text/javascript" src="/remis/javascript/commonscript.js"></script>
</head>

<script type="text/javascript">
function closeNotification(id){
	 $('#'+id).hide();
		/*  $.getJSON('closenotification', {
			 notiId : id
		 }, function(response) {
		   
			console.log("response"+response); 
			 $('#'+id).hide()
			
		 });
 */
		 } 

</script>



<body>
<s:fielderror cssClass="errorMsg"/>
<s:actionerror cssClass="errorMsg"/>
<s:actionmessage cssClass="infoMsg"/>
<s:form name="propattform" method="post" theme="simple" style="min-height: 350px">

	  
   <div class="row" style='padding-bottom: 20px'><s:hidden id='notificationId' name="notiVo.notificationId"/>
	<div class="col-sm-12 col-xs-12">&nbsp;
	<div class="table-responsive">
	<table class="table table-bordered">
	  <thead class="table-row">
	     <tr>
			<th class="table-cell" colspan="3">My Notifications</th>
		</tr>
		</thead>
	   <thead class="table-row">
	   <tr>
			<th class="table-cell">Notification Desc </th>
			<th class="table-cell">Date</th>
			<th class="table-cell">Action</th>
		</tr>
		</thead>
		<s:iterator value="notifiList" >
	        <tr class="table-row" id='<s:property value="notificationId"/>' >
	        
				<td class="table-cell"><s:property value="notifDesc" /></td>
				<td class="table-cell"> <s:date name="createdDate" format="dd/MM/yyyy" /></td>
				<td class="table-cell"> 
				   <a  href='#'  onclick="JavaScript:closeNotification('<s:property value="notificationId"/>')" > Close</a>
					<s:if test="%{isnew==true}">
		                      <img alt="<s:property value="newreason"/>" src="./images/notify.jpg" title="<s:property value="newreason"/>">
			       </s:if>
				</td>
		    </tr>
		    </s:iterator>
	   </table>
	</div>
	</div>
	</div>
	
</s:form>
</body>