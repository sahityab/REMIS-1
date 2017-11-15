<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page language="java" import="java.util.*"%>
<html>
<head>
<title>Real Estate Management Information System</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="/remis/css/promis.css" media="all">
<script language="JavaScript" type="text/JavaScript">
<!--
	function MM_swapImgRestore() { //v3.0
	  var i,x,a=document.MM_sr; for(i=0;a&&i<a.length&&(x=a[i])&&x.oSrc;i++) x.src=x.oSrc;
	}

	function MM_preloadImages() { //v3.0
	  var d=document; if(d.images){ if(!d.MM_p) d.MM_p=new Array();
		var i,j=d.MM_p.length,a=MM_preloadImages.arguments; for(i=0; i<a.length; i++)
		if (a[i].indexOf("#")!=0){ d.MM_p[j]=new Image; d.MM_p[j++].src=a[i];}}
	}

	function MM_findObj(n, d) { //v4.01
	  var p,i,x;  if(!d) d=document; if((p=n.indexOf("?"))>0&&parent.frames.length) {
		d=parent.frames[n.substring(p+1)].document; n=n.substring(0,p);}
	  if(!(x=d[n])&&d.all) x=d.all[n]; for (i=0;!x&&i<d.forms.length;i++) x=d.forms[i][n];
	  for(i=0;!x&&d.layers&&i<d.layers.length;i++) x=MM_findObj(n,d.layers[i].document);
	  if(!x && d.getElementById) x=d.getElementById(n); return x;
	}

	function MM_swapImage() { //v3.0
	  var i,j=0,x,a=MM_swapImage.arguments; document.MM_sr=new Array; for(i=0;i<(a.length-2);i+=3)
	   if ((x=MM_findObj(a[i]))!=null){document.MM_sr[j++]=x; if(!x.oSrc) x.oSrc=x.src; x.src=a[i+2];}
	}
	//-->
  function fnSave()
  {
    if(document.permissions.userList.value == "select")
    {
      alert("Please select user");
      document.permissions.userList.focus();
    }
    else{
    	document.permissions.action = "savepermissions";
    	document.permissions.submit();
		return true;
    }
    return false;
  }
  function fnSelect()
  {
    if(document.permissions.userList.value != "select")
    {
      document.permissions.action = "displaypermissions";
      document.permissions.submit();
      return true;  
    }
    return false;
  }
  function checkAll()
  {
    if(document.permissions.checkall.checked == true)
    {
      for(var i = 0; i < document.permissions.elements.length; i++)
      {
        if(document.permissions.elements[i].type == "checkbox")
          document.permissions.elements[i].checked = true;
      }
    }else{
      for(var i = 0; i < document.permissions.elements.length; i++)
      {
        if(document.permissions.elements[i].type == "checkbox")
          document.permissions.elements[i].checked = false;
      }    
    }
  }
  function fnReset()
  {    
    document.permissions.userList.selectedIndex = 0;
    for(i = 0; i < document.permissions.length; i++)
    {
      if(document.permissions.elements[i].type == 'checkbox')
        document.permissions.elements[i].checked = false;
    }
    return false;
  }
</script>
<link rel="stylesheet" href="/remis/css/promis.css" type="text/css">
</head>
<s:fielderror cssClass="errorMsg"/>
<s:actionerror cssClass="errorMsg"/>
<s:actionmessage cssClass="infoMsg"/>
<s:set var="permBean" value="#session.permBean"/>
<jsp:useBean id="permBean" type="com.prop.mnt.admin.PermissionDAO"/>
<%
  System.out.println("in jsp 1");
  String sActp = request.getParameter("actp");
  HashMap hModules = permBean.getModules();
  HashMap hPages = permBean.getPages();
  HashMap hUserPerms = null;
  String userid = null;
  boolean bdisp = false;
 %>
 <s:if test="%{ userList != null && userList != 'select' }">
 <%
    hUserPerms = permBean.getUserPerms();
    bdisp = true;
  	System.out.println("in jsp 2");
%>
</s:if>
<body background="/remis/images/sitebg.gif" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
<s:form name="permissions" method="post" action="savepermissions">
<!-- <table valign="center" width="100%" height="100%" border="1" cellpadding="1" cellspacing="2">
  <tr><td> -->
  <div class="row">
  <!-- <table  cellspacing="2" cellpadding="1" border="1" width="100%" align="center"> -->
  <!--tr><td class="subheadb">&nbsp;</td></tr-->
  <%-- <tr>
    <td width="100%" height="100%" align="center" valign="top">
     <table width="230" border="0" cellpadding="0" cellspacing="0" class="titlebgcolor">
        <tr>
          <td><!-- <img src="/remis/images/corner.gif" width="14" height="21" align="absmiddle"> -->
          <span valign="center" class="subheadblu">Set Permissions To Login Users</span>
          </td>
        </tr>
      </table>
    </td>
  </tr> --%>
  <!--tr><td class="subheadb">&nbsp;</td></tr-->
  <!-- <tr>
  <td align="center"> -->
  <div class="row col-sm-12 col-xs-12 pull-center"><span valign="center" class="subheadblu">Set Permissions To Login Users</span></div>
  <div class="row">
  <table width="100%" border="0" cellpadding="0" cellspacing="0" class="bodybgcolor">
  <!--tr><td colspan="5" class="subheadb">&nbsp;</td></tr-->
  <tr>
    <td class="subheadb" width="14%" height="21">&nbsp;&nbsp;Users List :</td>
    <td width="86%" colspan="7" class="subheadb" height="21">
      <s:select class="TxtField" name="userList" list="usersList" listKey="key" listValue="value" headerKey="select" headerValue="Please select" onchange="return fnSelect();"/>
    </td>
  </tr>
<%
  ArrayList arrList = null;
  String val = "";
  ArrayList arrList1 = new ArrayList();
  if(hModules != null)
  {
  System.out.println("module size:"+hModules.size());
  for(int m = 1; m < 10; m++)
  {
  if(hModules.containsKey(m+""))
  {
%>
  <tr>
<%  
    for(int i = 0; i < 7; i++)
    {
      String mid = ""+m;
      if(hModules.containsKey(mid))
      {
      HashMap hMap = (HashMap)hModules.get(mid);
      arrList = (ArrayList)hPages.get(mid);
      if(bdisp && hUserPerms != null && hUserPerms.size() > 0)
        arrList1 = (ArrayList)hUserPerms.get(mid);
%>
    <td width="14%" valign="top">
    <table width="100%">
      <tr><td width="100%" height="21">
       <table width="85%" border="0" cellpadding="0" cellspacing="0" class="titlebgcolor">
        <tr>
          <td><!-- <img src="/remis/images/corner.gif" width="14" height="21" align="absmiddle"> -->
          <span valign="center" class="subheadblu"><%=(String)hMap.get("name")%></span></td>
        </tr>
      </table>
      </td></tr>
<%
      for(int j = 0; j < arrList.size(); j++)
      {
        val = (String)arrList.get(j);
%>
        <tr>
          <td width="100%" class="subheadb" height="21">
          <input type="checkbox" class="TxtField" <%=((bdisp && arrList1 != null && arrList1.contains(val.substring(0,val.indexOf('#'))))?"checked":"")%> name="<%=val.substring(0,val.indexOf('#'))%>" value="<%=mid%>">&nbsp;<%=val.substring(val.indexOf('#')+1,val.lastIndexOf('#'))%>
          </td>
        </tr>
<%
      }
%>
    </table>
    </td>
<%
      }else{
        if(i == 1){
%>
          <td width="14%" class="subheadb" valign="top"><input type="checkbox" class="TxtField" name="checkall" onClick="return checkAll();">&nbsp;Select All</td>
          <td width="14%">&nbsp;</td>
          <td width="14%">&nbsp;</td>
          <td width="14%">&nbsp;</td>
          <td width="14%">&nbsp;</td>
          <td width="14%">&nbsp;</td>
<%
          break;
        }else if(i == 2){
%>
          <td width="14%" class="subheadb" valign="top"><input type="checkbox" class="TxtField" name="checkall" onClick="return checkAll();">&nbsp;Select All</td>
          <td width="14%">&nbsp;</td>
          <td width="14%">&nbsp;</td>
          <td width="14%">&nbsp;</td>
          <td width="14%">&nbsp;</td>
<%
          break;
        }else if(i == 3){
%>
          <td width="14%" class="subheadb" valign="top"><input type="checkbox" class="TxtField" name="checkall" onClick="return checkAll();">&nbsp;Select All</td>
          <td width="14%">&nbsp;</td>
          <td width="14%">&nbsp;</td>
          <td width="14%">&nbsp;</td>
<%
          break;
        }else if(i == 4){
%>
          <td width="14%" class="subheadb" valign="top"><input type="checkbox" class="TxtField" name="checkall" onClick="return checkAll();">&nbsp;Select All</td>
          <td width="14%">&nbsp;</td>
          <td width="14%">&nbsp;</td>
<%
          break;
        }else if(i == 5){
%>
          <td width="14%" class="subheadb" valign="top"><input type="checkbox" class="TxtField" name="checkall" onClick="return checkAll();">&nbsp;Select All</td>
          <td width="14%">&nbsp;</td>
<%
          break;
        }else{
%>
          <td width="12%" class="subheadb" valign="top"><input type="checkbox" class="TxtField" name="checkall" onClick="return checkAll();">&nbsp;Select All</td>
<%    
    }}
    if(i != 6)
      m++;
   }
%>
  </tr>
<%
  }}}
%>
<%-- <tr><td colspan="2"><span align="center">
          	<s:submit value="Save" cssClass="submit" onclick="return fnSave();"/>
	    	<s:submit value="Cancel" cssClass="submit" onclick="return fnReset();"/>   
</span></td></tr>
 --%>
    </table>
    </div>
    <div class="row" >
    	<div class="col-sm-5 col-xs-12">&nbsp;</div>
    	<div class="col-sm-1 col-xs-12">&nbsp;
            <s:submit value="Save" cssClass="submit" onclick="return fnSave();"/>
         </div>
         <div class="col-sm-1 col-xs-12">&nbsp;
	     	<s:submit value="Cancel" cssClass="submit" onclick="return fnReset();"/>
	     </div>
	     <div class="col-sm-5 col-xs-12">&nbsp;</div>   
    </div>
<!--     </td></tr>
    <tr>
    <td align="center" class="subheadb" height="21">
    <a href="#" onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('SaveImg','','../images/save_ov.gif',1)"><input name="SaveImg" type="image" src="../images/save_n.gif" border="0" alt="Save" width="54" height="29" onClick="return fnSave();"></a>&nbsp;
    a href="#" onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('DeleteImg','','../images/delete_ov.gif',1)"><input name="DeleteImg" type="image" src="../images/delete_n.gif" border="0" width="72" height="19" alt="Delete" onClick="return fnDelete();"></a>&nbsp;
    <a href="#" onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('cl','','../images/cancel_ov.gif',1)"><input name="cl" type="image" src="../images/cancel_n.gif" border="0" width="70" height="29" alt="Cancel" onClick="return fnReset();"></a>&nbsp;
    </td>
  </tr>
    
</table>
 --><!-- </td></tr></table> -->
</div>
</s:form>
</body>
</html>