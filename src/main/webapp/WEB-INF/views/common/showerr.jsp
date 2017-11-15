<%@ page contentType="text/html;charset=windows-1252"%>
<html>
<head>
<title>PROMIS</title>
<meta HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=windows-1256">
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
-->
</script>
</head>
<link rel="stylesheet" href="/remis/css/promis.css" type="text/css">
<body background="/remis/images/sitebg.gif" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
<table align="center" height="100%" width="100%" border="0" cellpadding="0" cellspacing="0">
<tr><td valign="middle" align="center">
	<table width="80%" border="0" cellpadding="0" cellspacing="0" class="bodybgcolor">
		<tr>
			<td class="ErrStr" align="center" colspan="2">Problem while loading page. Please contact administrator at: 573-291-2091</td>
		</tr>
		<tr>
			<td class="Msg" colspan="2">
				The problem may be caused due to: <br>
				1. Incorrect data entered<br>
				2. Required data is missing<br>
				3. System error<br>
				If the problem persists please contact the support
				quoting the exact error message and the message context.
			</td>
		</tr>
		<tr>
		<td align="center" colspan="2">
		 <a href="#" onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('SaveImg','','/remis/images/back_ov.gif',1)"><input name="SaveImg" type="image" src="/remis/images/back_n.gif" border="0" alt="Back" width="54" height="19" onClick="javascript:window.history.back();"></a>
		</td>
		</tr>
	</table>
</td></tr>
</table>
</body>
</html>