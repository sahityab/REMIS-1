<%@ taglib prefix="s" uri="/struts-tags"%>
<h2 align="center"><s:text name="page.heading.property"/></h2>
<%@ include file="calendar.jsp"%>
<head>
	<script src="/remis/javascript/jquery-1.10.2.min.js" type="text/javascript"></script>
	<script src="/remis/javascript/jquery-ui.js" type="text/javascript"></script>
	<script type="text/javascript" src="/remis/javascript/commonscript.js"></script>
	<script src="/remis/javascript/propertymaint.js" type="text/javascript"></script>
</head>
<body>
<s:fielderror cssClass="errorMsg"/>
<s:actionerror cssClass="errorMsg"/>
<s:actionmessage cssClass="infoMsg"/>
<s:form name="propertys" method="post" action="saveproperty" validate="true" theme="simple">
  <!-- <div id="divbody" class="bodybgcolor" > -->
  <div class="row col-sm-12 col-xs-12">&nbsp;</div>
  	<div class="row">
		<div class="col-sm-4 col-xs-12"><label for="propertyListId" class="pull-right"><s:text name="label.property.list"/></label></div>
		<div class="col-sm-4 col-xs-12">
			<s:select id="propertyListId" cssClass="form-control" name="propertyVo.propCode" list="propList" listKey="key" listValue="value" emptyOption="false"  headerKey="0" headerValue="Create new" theme="xhtml"/>
		</div>
		<div class="col-sm-4 col-xs-12">&nbsp;</div>
	</div>
	<div class="row col-sm-12 col-xs-4">&nbsp;</div>
	<div class="row">
		<div class="col-sm-4 col-xs-12"><label for="propNameId" class="pull-right"><s:text name="label.prop.name"/></label></div>
		<div class="col-sm-4 col-xs-12">
			<s:textfield id="propNameId" name="propertyVo.propName" cssClass="form-control" maxlength="40" />
		</div>
		<div class="col-sm-4 col-xs-12">&nbsp;</div>
	</div>
	<div class="row col-sm-12 col-xs-4">&nbsp;</div>
	<div class="row">
		<div class="col-sm-4 col-xs-12"><label for="propTypeCodeId" class="pull-right"><s:text name="label.prop.type.code"/></label></div>
		<div class="col-sm-4 col-xs-12">
			<s:select id="propTypeCodeId" cssClass="form-control" name="propertyVo.propTypeCode" list="propTypes" listKey="key" listValue="value" />
		</div>
		<div class="col-sm-4 col-xs-12">&nbsp;</div>
	</div>
	<div class="row col-sm-12 col-xs-4">&nbsp;</div>
	<div class="row">
		<div class="col-sm-4 col-xs-12"><label for="landStatusCodeId" class="pull-right"><s:text name="label.land.status.code"/></label></div>
		<div class="col-sm-4 col-xs-12">
			<s:select id="landStatusCodeId" cssClass="form-control" name="propertyVo.landStatusCode" list="landStatus" listKey="key" listValue="value" />
		</div>
		<div class="col-sm-4 col-xs-12">&nbsp;</div>
	</div>
	<div class="row col-sm-12 col-xs-4">&nbsp;</div>
	<div class="row">
		<div class="col-sm-4 col-xs-12"><label for="propCatCodeId" class="pull-right"><s:text name="label.prop.cat.code"/></label></div>
		<div class="col-sm-4 col-xs-12">
			<s:select id="propCatCodeId" cssClass="form-control" name="propertyVo.propCatCode" list="propCatList" listKey="key" listValue="value" />
		</div>
		<div class="col-sm-4 col-xs-12">&nbsp;</div>
	</div>
	<div class="row col-sm-12 col-xs-4">&nbsp;</div>
	<div class="row">
		<div class="col-sm-4 col-xs-12"><label for="noOfDivId" class="pull-right"><s:text name="label.no.of.div"/></label></div>
		<div class="col-sm-4 col-xs-12">
			<s:textfield id="noOfDivId" name="propertyVo.noOfDiv" cssClass="form-control" maxlength="3" onkeypress="return allowNumbersOnly(this, event);"/>
		</div>
		<div class="col-sm-4 col-xs-12">&nbsp;</div>
	</div>
	<div class="row col-sm-12 col-xs-4">&nbsp;</div>
	<div class="row">
		<div class="col-sm-4 col-xs-12"><label for="propDeedNoId" class="pull-right"><s:text name="label.prop.deed.no"/></label></div>
		<div class="col-sm-4 col-xs-12">
			<s:textfield id="propDeedNoId" name="propertyVo.propDeedNo" cssClass="form-control" maxlength="20" />
		</div>
		<div class="col-sm-4 col-xs-12">&nbsp;</div>
	</div>
	<div class="row col-sm-12 col-xs-4">&nbsp;</div>
	<div class="row">
		<div class="col-sm-4 col-xs-12"><label for="fileReferId" class="pull-right"><s:text name="label.file.refer"/></label></div>
		<div class="col-sm-4 col-xs-12">
			<s:textfield id="fileReferId" name="propertyVo.fileRefer" cssClass="form-control" maxlength="20" />
		</div>
		<div class="col-sm-4 col-xs-12">&nbsp;</div>
	</div>
	<div class="row col-sm-12 col-xs-4">&nbsp;</div>
	<div class="row">
		<div class="col-sm-4 col-xs-12"><label for="addr1Id" class="pull-right"><s:text name="label.addr1"/></label></div>
		<div class="col-sm-4 col-xs-12">
			<s:textfield id="addr1Id" name="propertyVo.addr1" cssClass="form-control" maxlength="30" />
		</div>
		<div class="col-sm-4 col-xs-12">&nbsp;</div>
	</div>
	<div class="row col-sm-12 col-xs-4">&nbsp;</div>
	<div class="row">
		<div class="col-sm-4 col-xs-12"><label for="addr2Id" class="pull-right"><s:text name="label.addr2"/></label></div>
		<div class="col-sm-4 col-xs-12">
					<s:textfield id="addr2Id" name="propertyVo.addr2" cssClass="form-control" maxlength="30" />
				</div>
		<div class="col-sm-4 col-xs-12">&nbsp;</div>
	</div>
	<div class="row col-sm-12 col-xs-4">&nbsp;</div>
	<div class="row">
		<div class="col-sm-4 col-xs-12"><label for="propCityId" class="pull-right"><s:text name="label.prop.city"/></label></div>
		<div class="col-sm-4 col-xs-12">
							<s:textfield id="propCityId" name="propertyVo.propCity" cssClass="form-control" maxlength="15" />
			</div>
		<div class="col-sm-4 col-xs-12">&nbsp;</div>
	</div>
	<div class="row col-sm-12 col-xs-4">&nbsp;</div>
	<div class="row">
		<div class="col-sm-4 col-xs-12"><label for="zipId" class="pull-right"><s:text name="label.zip"/></label></div>
		<div class="col-sm-4 col-xs-12">
			<s:textfield id="zipId" name="propertyVo.zip" cssClass="form-control" maxlength="10" />
		</div>
		<div class="col-sm-4 col-xs-12">&nbsp;</div>
	</div>
	<div class="row col-sm-12 col-xs-4">&nbsp;</div>
	<div class="row">
		<div class="col-sm-4 col-xs-12"><label for="propCountryId" class="pull-right"><s:text name="label.prop.country"/></label></div>
		<div class="col-sm-4 col-xs-12">
			<s:textfield id="propCountryId" name="propertyVo.propCountry" cssClass="form-control" maxlength="40" />
		</div>
		<div class="col-sm-4 col-xs-12">&nbsp;</div>
	</div>
	<div class="row col-sm-12 col-xs-4">&nbsp;</div>
	<div class="row">
		<div class="col-sm-4 col-xs-12"><label for="propContactnameId" class="pull-right"><s:text name="label.prop.contactname"/></label></div>
		<div class="col-sm-4 col-xs-12">
			<s:textfield id="propContactnameId" name="propertyVo.propContactname" cssClass="form-control" maxlength="20" />
		</div>
		<div class="col-sm-4 col-xs-12">&nbsp;</div>
	</div>
	<div class="row col-sm-12 col-xs-4">&nbsp;</div>
	<div class="row">
		<div class="col-sm-4 col-xs-12"><label for="propContacttelId" class="pull-right"><s:text name="label.prop.contacttel"/></label></div>
		<div class="col-sm-4 col-xs-12">
			<s:textfield id="propContacttelId" name="propertyVo.propContacttel" cssClass="form-control" maxlength="12"  onkeypress="return allowNumbersOnly(this, event);"/>
		</div>
		<div class="col-sm-4 col-xs-12">&nbsp;</div>
	</div>
	<div class="row col-sm-12 col-xs-4">&nbsp;</div>
	<div class="row">
		<div class="col-sm-4 col-xs-12"><label for="propPurDateId" class="pull-right"><s:text name="label.prop.pur.date"/></label></div>
		<div class="col-sm-4 col-xs-12">
			<s:textfield id="propPurDateId" name="propertyVo.propPurDate" cssClass="form-control" cssStyle="width:92%;display:inline" maxlength="10" onchange="checkDate(this)"/>
			<img border="0" id="propPurDateCal" src="<s:url value='./images/calendar.gif'/>" title="Date Selector" class="inline-display"
			onclick="getCalendar(this,'propPurDateId')" onmouseover="this.style.background='red';" onmouseout="this.style.background=''"/>
		</div>
		<div class="col-sm-4 col-xs-12">&nbsp;</div>
	</div>
	<div class="row col-sm-12 col-xs-4">&nbsp;</div>
	<div class="row">
		<div class="col-sm-4 col-xs-12"><label for="propPurValueId" class="pull-right"><s:text name="label.prop.pur.value"/></label></div>
		<div class="col-sm-4 col-xs-12">
			<s:textfield id="propPurValueId" name="propertyVo.propPurValue" cssClass="form-control" maxlength="12"  onkeypress="return allowNumbersOnly(this, event);"/>
		</div>
		<div class="col-sm-4 col-xs-12">&nbsp;</div>
	</div>
	<div class="row col-sm-12 col-xs-4">&nbsp;</div>
	<div class="row">
		<div class="col-sm-4 col-xs-12"><label for="propMktValueId" class="pull-right"><s:text name="label.prop.mkt.value"/></label></div>
		<div class="col-sm-4 col-xs-12">
			<s:textfield id="propMktValueId" name="propertyVo.propMktValue" cssClass="form-control" maxlength="12"  onkeypress="return allowNumbersOnly(this, event);"/>
		</div>
		<div class="col-sm-4 col-xs-12">&nbsp;</div>
	</div>
	<div class="row col-sm-12 col-xs-4">&nbsp;</div>
	<div class="row">
		<div class="col-sm-4 col-xs-12"><label for="currencyCodeId" class="pull-right"><s:text name="label.currency.code"/></label></div>
		<div class="col-sm-4 col-xs-12">
			<s:select id="currencyCodeId" cssClass="form-control" name="propertyVo.currencyCode" list="currencyList" listKey="key" listValue="value" />
		</div>
		<div class="col-sm-4 col-xs-12">&nbsp;</div>
	</div>
	<div class="row col-sm-12 col-xs-4">&nbsp;</div>
	<div class="row">
		<div class="col-sm-4 col-xs-12"><label for="valDtId" class="pull-right"><s:text name="label.val.dt"/></label></div>
		<div class="col-sm-4 col-xs-12">
			<s:textfield id="valDteId" name="propertyVo.valDt" cssClass="form-control" cssStyle="width:92%;display:inline" maxlength="10" onchange="checkDate(this)"/>
			<img border="0" id="propPurDateCal" src="<s:url value='./images/calendar.gif'/>" title="Date Selector" class="inline-display"
			onclick="getCalendar(this,'valDteId')" onmouseover="this.style.background='red';" onmouseout="this.style.background=''"/>
		</div>
		<div class="col-sm-4 col-xs-12">&nbsp;</div>
	</div>
	<div class="row col-sm-12 col-xs-4">&nbsp;</div>
	<div class="row">
		<div class="col-sm-4 col-xs-12"><label for="valRemindDtId" class="pull-right"><s:text name="label.val.remind.dt"/></label></div>
		<div class="col-sm-4 col-xs-12">
			<s:textfield id="valRemindDtId" name="propertyVo.valRemindDt" cssClass="form-control" cssStyle="width:92%;display:inline" maxlength="10" onchange="checkDate(this)"/>
			<img border="0" id="propPurDateCal" src="<s:url value='./images/calendar.gif'/>" title="Date Selector" class="inline-display"
			onclick="getCalendar(this,'valRemindDtId')" onmouseover="this.style.background='red';" onmouseout="this.style.background=''"/>
		</div>
		<div class="col-sm-4 col-xs-12">&nbsp;</div>
	</div>
	<div class="row col-sm-12 col-xs-4">&nbsp;</div>
	<div class="row">
		<div class="col-sm-4 col-xs-12"><label for="valAgentId" class="pull-right"><s:text name="label.val.agent"/></label></div>
		<div class="col-sm-4 col-xs-12">
			<s:textfield id="valAgentId" name="propertyVo.valAgent" cssClass="form-control" maxlength="40" />
		</div>
		<div class="col-sm-4 col-xs-12">&nbsp;</div>
	</div>
	<div class="row col-sm-12 col-xs-4">&nbsp;</div>
	<div class="row">
		<div class="col-sm-4 col-xs-12"><label for="valBasisId" class="pull-right"><s:text name="label.val.basis.list"/></label></div>
		<div class="col-sm-4 col-xs-12">
			<s:select id="valBasisId" cssClass="form-control" name="propertyVo.valBasis" list="valusBasisList" listKey="key" listValue="value" />
		</div>
		<div class="col-sm-4 col-xs-12">&nbsp;</div>
	</div>
	<div class="row col-sm-12 col-xs-4">&nbsp;</div>
	<div class="row">
		<div class="col-sm-4 col-xs-12"><label for="propRmksId" class="pull-right"><s:text name="label.prop.rmks"/></label></div>
		<div class="col-sm-4 col-xs-12">
			<s:textfield id="propRmksId" name="propertyVo.propRmks" cssClass="form-control" maxlength="200" />
		</div>
		<div class="col-sm-4 col-xs-12">&nbsp;</div>
	</div>
	<div class="row col-sm-12 col-xs-4">&nbsp;</div>
	<div class="row">
		<div class="col-sm-4 col-xs-12"><label for="areaId" class="pull-right"><s:text name="label.area"/></label></div>
		<div class="col-sm-4 col-xs-12">
			<s:textfield id="areaId" name="propertyVo.area" cssClass="form-control" maxlength="10"  onkeypress="return allowNumbersOnly(this, event);"/>
		</div>
		<div class="col-sm-4 col-xs-12">&nbsp;</div>
	</div>
	<div class="row col-sm-12 col-xs-4">&nbsp;</div>
	<div class="row">
		<div class="col-sm-4 col-xs-12"><label for="remLandAreaId" class="pull-right"><s:text name="label.rem.land.area"/></label></div>
		<div class="col-sm-4 col-xs-12">
			<s:textfield id="remLandAreaId" name="propertyVo.remLandArea" cssClass="form-control" maxlength="10"  onkeypress="return allowNumbersOnly(this, event);"/>
		</div>
		<div class="col-sm-4 col-xs-12">&nbsp;</div>
	</div>
		<div class="row col-sm-12 col-xs-4">&nbsp;</div>
	<div class="row">
		<div class="col-sm-4 col-xs-12"><label for="uomId" class="pull-right"><s:text name="label.uom"/></label></div>
		<div class="col-sm-4 col-xs-12">
			<s:select id="uomId" cssClass="form-control" name="propertyVo.uom" list="unitOfMeasList" listKey="key" listValue="value" />
		</div>
		<div class="col-sm-4 col-xs-12">&nbsp;</div>
	</div>
	<div class="row col-sm-12 col-xs-4">&nbsp;</div>
	<div class="row">
		<div class="col-sm-4 col-xs-12"><label for="plotNoId" class="pull-right"><s:text name="label.plot.no"/></label></div>
		<div class="col-sm-4 col-xs-12">
			<s:textfield id="plotNoId" name="propertyVo.plotNo" cssClass="form-control" maxlength="15" />
		</div>
		<div class="col-sm-4 col-xs-12">&nbsp;</div>
	</div>
	<div class="row col-sm-12 col-xs-4">&nbsp;</div>
	<div class="row">
		<div class="col-sm-4 col-xs-12"><label for="sdJobNoId" class="pull-right"><s:text name="label.sd.job.no"/></label></div>
		<div class="col-sm-4 col-xs-12">
			<s:textfield id="sdJobNoId" name="propertyVo.sdJobNo" cssClass="form-control" maxlength="10" />
		</div>
		<div class="col-sm-4 col-xs-12">&nbsp;</div>
	</div>
	<div class="row col-sm-12 col-xs-4">&nbsp;</div>
	<div class="row">
		<div class="col-sm-4 col-xs-12"><label for="mapsheetId" class="pull-right"><s:text name="label.mapsheet"/></label></div>
		<div class="col-sm-4 col-xs-12">
			<s:textfield id="mapsheetId" name="propertyVo.mapsheet" cssClass="form-control" maxlength="40" />
		</div>
		<div class="col-sm-4 col-xs-12">&nbsp;</div>
	</div>
	<div class="row col-sm-12 col-xs-4">&nbsp;</div>
	<div class="row">
		<div class="col-sm-4 col-xs-12"><label for="permitNoId" class="pull-right"><s:text name="label.permit.no"/></label></div>
		<div class="col-sm-4 col-xs-12">
			<s:textfield id="permitNoId" name="propertyVo.permitNo" cssClass="form-control" maxlength="30" />
		</div>
		<div class="col-sm-4 col-xs-12">&nbsp;</div>
	</div>
	<div class="row col-sm-12 col-xs-4">&nbsp;</div>
	<div class="row">
		<div class="col-sm-4 col-xs-12"><label for="permitDtId" class="pull-right"><s:text name="label.permit.dt"/></label></div>
		<div class="col-sm-4 col-xs-12">
			<s:textfield id="permitDtId" name="propertyVo.permitDt" cssClass="form-control" cssStyle="width:92%;display:inline" maxlength="10" onchange="checkDate(this)"/>
			<img border="0" id="propPurDateCal" src="<s:url value='./images/calendar.gif'/>" title="Date Selector" class="inline-display"
			onclick="getCalendar(this,'permitDtId')" onmouseover="this.style.background='red';" onmouseout="this.style.background=''"/>
		</div>
		<div class="col-sm-4 col-xs-12">&nbsp;</div>
	</div>
 	<div class="row col-sm-12 col-xs-4">&nbsp;</div>
 	<div class="row">
		<div class="col-sm-4 col-xs-12">&nbsp;</div>
		<div class="col-sm-4 col-xs-12">
		<s:submit id="saveId" value="Save" cssClass="submit"/>
		<s:submit action="deleteproperty" id="deleteId" value="Delete" cssClass="submit"/>
		<s:reset action="propertymanagement" id="resetId" value="Reset" cssClass="submit"/>
		</div>
		<div class="col-sm-4 col-xs-12">&nbsp;</div>
	</div>
	<div class="row col-sm-12 col-xs-4">&nbsp;</div>
<!-- </div> -->
</s:form>
</body>