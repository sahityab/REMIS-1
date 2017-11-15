<%@ taglib prefix="s" uri="/struts-tags" %>
<script language="javascript" type="text/javascript">
function showSubMenu(id){
	console.log(id);
	 $('.ToggleDiv').hide();
	 document.getElementById(id).style.display= 'inline';
	 $('#dv'+id).show();
	// document.getElementById('dv'+id ).style.display= 'inline';
	/* console.log(id);
	for(var i = 1; i <= 20; i++){
		if( id == 'dv'+i ){
			document.getElementById(id).style.display= 'inline';
		}else{
			document.getElementById( 'dv'+i ).style.display= 'none';
		}
	} */
}
function loadPage(url){
	
}
</script>
<s:set var="permBean" value="#session.permBean"/>
<s:set var="logonBn" value="#session.logonBn"/>
<%-- <body bgcolor="#AFF7FB" background="/remis/images/sitebg.gif" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" onLoad="MM_preloadImages(<%=sImgs%>);FnLoad('dv<%=permBean.getFirstModule()%>','<%=permBean.getFirstPage()%>')"> --%>
<div id="navigate">
	<button class="navbar-toggle collapsed" type="button" data-toggle="collapse" data-target="#navbar-main">
    	<span class="sr-only">Mobile Menu Button</span>
    	<span class="icon-bar"></span>
        <span class="icon-bar"></span>
    	<span class="icon-bar"></span>
	</button>
</div>

 <nav id="menu" role="navigation">
  <div class="container">
    <div class="row">
      <div class="navbar-collapse collapse" id="navbar-main" style="height: 1px;">
        <ul class="nav navbar-nav yamm">
		<s:iterator value="#session.modules" var="module">
		<s:iterator>
				<li><a href="#" onclick="showSubMenu( 'dv${module.moduleID}' )">${module.moduleName}</a></li>
		 </s:iterator>
		</s:iterator>
        </ul>
      </div>
    </div>
  </div>
</nav>

<s:iterator value="#session.modules" var="module">
<s:iterator>
<%-- <div class="row col-sm-12 col-xs-6" id="dv${key}" style="position:absolute; left:'12.5%'; right:'1px'; top:'81%'; width:'84%'; height:'18px'; z-index:'1'; visibility:'hidden';"> --%>
<div class='ToggleDiv' id="dv${module.moduleID}" style="display: none">
<div class="row col-sm-12 col-xs-6"  >
<s:iterator value="pages" >
	<s:iterator>
          <div class="col-xs-6" id="pg<s:property value="pageId"/>" >
          <a href="<s:property value="path"/>" id="hl<s:property value="pageId"/>" class="hyplnk1" ><s:property value="pageName"/></a> 
          </div>
          <!-- <div  style="width:1px;background-color:'#3979AD'"><img src="/remis/images/1px.gif" width="1" height="1"></div > -->
     </s:iterator>
</s:iterator>
 </div >
 </div>
 </s:iterator>
</s:iterator>

	<div class="row col-sm-12 col-xs-2 caption-thin-bar">
	      	<div class="col-sm-4 col-xs-4 pull-left"><a href="<s:url action='./logout'/>"  class="font-white">Logout</a></div>      
	      	<div class="col-sm-4 col-xs-4 pull-center">Welcome <s:property value="#session.logonBn.fName"/>&nbsp;<s:property value="#session.username"/></div>
	      	<div class="col-sm-4 col-xs-4 pull-right">Last Logged In: <s:property value="#session.logonBn.loginTime"/></div>
	</div>
