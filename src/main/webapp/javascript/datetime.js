
/**------------------------------------------------------------------------------
* Checks to see if Date & Time are being entered and comply to format
* @author s9p000is
* @author PPS000IS
*-------------------------------------------------------------------------------*/
var dtCh= "/";

function DaysArray(n) {
	for (var i = 1; i <= n; i++) {
		this[i] = 31;
		if (i==4 || i==6 || i==9 || i==11) {this[i] = 30;}
		if (i==2) {this[i] = 29;}
   } 
   return this;
}

/**
* February has 29 days in any year evenly divisible by four,
* EXCEPT for centurial years which are not also divisible by 400.
*/

function daysInFebruary (year)
{
   return (((year % 4 == 0) && ( (!(year % 100 == 0)) || (year % 400 == 0))) ? 29 : 28 );
}

function isInteger(s){
	var i;
    for (i = 0; i < s.length; i++)
    {   
    	/**
    	 * Check that current character is number.
    	 */
        var c = s.charAt(i);
        if (((c < "0") || (c > "9"))) return false;
    }
    /**
     * All characters are numbers.
     */
    return true;
}

/**
* Search through string's characters one by one.
* If character is not in bag, append to returnString.
*/
function stripCharsInBag(s, bag){
	var i;
    var returnString = "";
    
    for (i = 0; i < s.length; i++){   
        var c = s.charAt(i);
        if (bag.indexOf(c) == -1) returnString += c;
    }
    return returnString;
}

/**
* This function checks the date format. 
* Returns true if the given date is in the correct format else returns false.
* @param  obj 
* @return boolean
*/ 
function isValidDate(obj){
	 if(typeof obj == "string"){
		 obj = document.getElementById(obj); document.getElementById(obj);
	 }
	var valid = true;
	var MinDate = "1/1/1900";
	var valstr = obj.value;
	var today  = serverCurrentDate;
	if ("12/31/7799" == valstr || "12317799" == valstr) {
		return false;
	}
	var yyyy   = today.getFullYear();
	if (valstr == ".") {
		// "." is shortcut for current date
		if (today < Date.parse(MinDate)) {
			valid= false;
		} else {
			obj.value = formatDate(today);
			valid= true;
		}
	}	
	// split date by delimiters (-/. \); delimiters are removed
	var splitExp  = new RegExp("[-/.\\s\\\\]+");	
	var dateParts = valstr.split(splitExp);	
	for (var x = 0; x < dateParts.length; x += 1) {		
		if (dateParts[x].length == 1) {			
			dateParts[x] = "0" + dateParts[x];
		}
	}
	valstr = dateParts.join("");
	 if (valstr.length >0 && valstr.length <= 2) {
		// day only entered; use current month and year
		var mm = today.getMonth() + 1;
		if (mm < 9) {
			valstr = "0" + mm + valstr + yyyy;
		} else {
			valstr = mm + valstr + yyyy;
		}			
	} else if (valstr.length == 4) {
		// month and day only entered; use current year
		valstr = valstr + yyyy;
	} else if (valstr.length == 6) {
		// two digit year; use current century
		var cc = parseInt(yyyy / 100);
		valstr = valstr.substr(0, 4) + cc + valstr.substr(4, 2);
		
	}	
	// something is not right if length is not 8
	if (valstr.length >0 && valstr.length != 8) {
		
		valid= false;
	}
	var m = valstr.substr(0, 2);
	var d = valstr.substr(2, 2);
	var y = valstr.substr(4, 4);	
	if (isNaN(m) || isNaN(d) || (isNaN(y))){
		
		valid= false;
	}
	if(valstr.length >0){
		if ((m < 1 || m > 12) || (d < 1 || d > daysInMonth(m, y))){
			
			valid= false;;
		}
	}
    if (MinDate.length > 0) {    	
        if (Date.parse(new Date(m + "/" + d + "/" + y)) < Date.parse(MinDate))  valid= false;
    }
    if (valid){
	    if(valstr.length >0){    	
	    	obj.value = m + "/" + d + "/" + y;
	    }
    }
    return valid;
}


/**
* This function checks the date format. If the format is not correct
* then an alert message is show.
* Returns true if the given date is in the correct format else returns false.
* @param  obj 
* @return boolean
*/ 
function checkDate(obj) {	
	if (!isValidDate(obj)) {
		// Incase of invalid Date, alert the message, clear the field and set focus to the date field.
		alert(commonDateErrMsg);
		obj.value = "";		
		if(obj.type != 'hidden'){
			setTimeout(function(){obj.focus();}, 10);
		}
		return false;
	}else{		
		return true;
	}
}

/**
* This function checks the time in military time format HH:MM. 
* Returns true if the given time is in the correct format else false.
* @param  obj 
* @return boolean
*/ 
function isValidTime(obj){
	 if(typeof obj == "string"){
		 obj = document.getElementById(obj);
	 }
	var valid = true;
	var valstr = obj.value;
	if (!isWhitespace(valstr)) {
		var validTimeExp = /^\d{4}$/;//0105
	  
	    var tmp = "";
	    var hr = 0;
	    var min = 0;
	    var indx = -1;
	    var flgTm = 0;
	    // STEP 1 - CHECK TO SEE IF THE LENGTH IS BETWEEN 3 AND 5
	    if (valstr.length < 3 || valstr.length > 5){
	    	flgTm = 1;
	    }
	    // STEP 2 - PARSE THE TIME INTO IT'S COMPONENTS
	    if (flgTm < 1) 
	    {
				if (valstr.indexOf(":") != -1) indx = valstr.indexOf(":");
				else if (!validTimeExp.test(obj.value)) flgTm = 1;
				
				if (flgTm < 1){
				    if (indx <0)
				    	indx =2;
				    hr = valstr.substring(0,indx);
				    if (hr.length < 2) hr = "0" + hr;
				    if (valstr.indexOf(":") != -1)
					    min = valstr.substring(indx+1);
						else
							min = valstr.substring(indx);
				    if (min.length == 0) min = "00";
				    if (min.length < 2) min = "0" + min;
				}
				
	    }
	    // STEP 3 - CHECK TO SEE THAT THE COMPONENTS ARE GOOD
	    if (flgTm < 1)
	    {
				if (isNaN(hr)) flgTm = 1;
				if (isNaN(min)) flgTm = 1;
				if (hr < 0 || hr > 23) flgTm = 1;
				if (min < 0 || min > 59) flgTm = 1;
		}
	    if (flgTm > 0)
	    {
	    	valid = false;
	    }
	    else {
			obj.value = hr + ":" + min;
			valid = true;
	    }
	}
	return valid;
}

/**
 * This function checks the time in military time format HH:MM 
 *  if not then an alert message is show.
 * Returns true if the given time is in the correct format else false.
 * @param  obj 
 * @return boolean
 */ 
function checkTime(obj){
	if (!isValidTime(obj)) {
		// Incase of invalid Time, alert the message, clear the field and set focus to the date field.
		alert(commonTimeErrMsg);
		obj.value = "";		
		if(obj.type != 'hidden'){
			setTimeout(function(){obj.focus();}, 10);	
		}
		return false;
	}else{		
		return true;
	}
	
}

function getCurrentDate() {
	var fMon, fDay;
	var datefield = serverCurrentDate;
	var fMon=datefield.getMonth() + 1;
	var fDay=datefield.getDate();
	if (fMon < 10) fMon="0"+fMon;
	if (fDay < 10) fDay="0"+fDay;
	return fMon+"/"+fDay+"/"+datefield.getFullYear();
}

function getTime() {
	var fHrs, fMin;
	var datefield = new Date();
	var fHrs=datefield.getHours();
	var fMin=datefield.getMinutes();
	if (fHrs < 10) fHrs="0"+fHrs;
	if (fMin < 10) fMin="0"+fMin;
	return fHrs+":"+fMin;
}

/**------------------------------------------------------------
 * INCREMENTS THE PASSED DATE STRING
 * NOTE: dateStr SHOULD CONFORM TO THE ACCEPTABLE
 * JAVASCRIPT FORMATS FOR CREATING A DATE OBJECT
 * (I.E. 12/1/2001)
 *------------------------------------------------------------*/
function incrementDate(dateStr, days) {
    var date = null;
    if (dateStr != null && dateStr.length > 0) {
	   date = new Date(dateStr);
    } else {
        date = serverCurrentDate;
    }
	var daysInMilliseconds = (days * 86400000);
	date.setTime(date.getTime() + daysInMilliseconds);
	return formatDate(date);
}

/**-----------------------------------------------------------
* Sets its value to the current date.
*-----------------------------------------------------------*/
function setDefaultDate(obj) {
   if (obj.value.length == 0) {
         obj.value = getCurrentDate();
         obj.select();
         /* Added this code to trigger onchange event after updating the date field.*/
         if(obj.onchange){
         		obj.onchange();
      	}
    }
}
/**-----------------------------------------------------------
* Sets its value to the current time.
*-----------------------------------------------------------*/
function setDefaultTime(obj){
	
	 if (obj.value.length == 0) {
	    obj.value = getTime();
	    obj.select();
	    if(obj.onchange){
         		obj.onchange();
      	}
	 }
}


/**-------------------------------------------------------------------------------------
* Returns true if the year is a leap year.
* @param Year The year to check
* @return boolean True if a leap year, false if not.
*-------------------------------------------------------------------------------------*/
function isLeapYear(Year)  {
 if (((Year % 4) == 0) && ((Year % 100) != 0) || ((Year % 400) == 0))
    return (true);
 else
    return (false);
}

/**-------------------------------------------------------------------------------------
* Returns the number of days in a given month.
* @param month The month in question.
* @param year The year of the month in question.
* @return int The number of days in the month.
*-------------------------------------------------------------------------------------*/
function daysInMonth(month, year){
	 var days;
	 if (month==1 || month==3 || month==5 || month==7 || month==8 || month==10 || month==12)
	   days=31;
	 else if (month==4 || month==6 || month==9 || month==11)
	   days=30;
	 else if (month==2)  {
	   if (isLeapYear(year))
	     days=29;
	   else
	     days=28;
	 }
	  return (days);
}

/**-----------------------------------------------------------------------
 * FORMATS THE PASSED DATE OBJECT IN THE MM/DD/YYYY FORMAT
 *-----------------------------------------------------------------------*/
function formatDate(date) {
	var fMon, fDay;
	var fMon = date.getMonth() + 1;
	var fDay = date.getDate();
	if (fMon < 10) fMon = "0" + fMon;
	if (fDay < 10) fDay = "0" + fDay;
	return fMon + "/" + fDay + "/" + date.getFullYear();
}
 
 /*
  *********************************************************************************************
  * Function used to check the date format in checkForm() method, which returns true or false
  * but does not show any alert message
  ********************************************************************************************/
 function checkFormDate(obj) {
 	var nm = obj.name.toLowerCase();
 	if (nm.indexOf("date") != -1) {
 		if (obj.value != "") {
 			return checkDate(obj);
 		}
 	} else if (nm.indexOf("time") != -1) {
 		if (obj.value != "") {
 			return checkTime(obj);
 		}
 	}
 	return true;
 }
 
// Is valid Date checks if given date is valid
//No min max limitations for validation
/*
 * @Deprecated : This is method is replaced with checkDate() function.
 */
function isvalidDate(dtStr){

	var daysInMonth = DaysArray(12);
	var pos1=dtStr.indexOf(dtCh);
	var pos2=dtStr.indexOf(dtCh,pos1+1);
	var strMonth=dtStr.substring(0,pos1);
	var strDay=dtStr.substring(pos1+1,pos2);
	var strYear=dtStr.substring(pos2+1);
	strYr=strYear;
	if (strDay.charAt(0)=="0" && strDay.length>1) strDay=strDay.substring(1);
	if (strMonth.charAt(0)=="0" && strMonth.length>1) strMonth=strMonth.substring(1);
	for (var i = 1; i <= 3; i++) {
		if (strYr.charAt(0)=="0" && strYr.length>1) strYr=strYr.substring(1);
	}
	month=parseInt(strMonth);
	day=parseInt(strDay);
	year=parseInt(strYr);
	if (pos1==-1 || pos2==-1){
		return false;
	}
	if (strMonth.length<1 || month<1 || month>12){
		return false;
	}
	if (strDay.length<1 || day<1 || day>31 || (month==2 && day>daysInFebruary(year)) || day > daysInMonth[month]){
		return false;
	}
	if (strYear.length != 4 || year==0 ){
		return false;
	}
	if (dtStr.indexOf(dtCh,pos2+1)!=-1 || isInteger(stripCharsInBag(dtStr, dtCh))==false){
		return false;
	}
	return true;
} 


/**
 * This function check if the given date is a past date and alerts 
 * a message if its a past date.
 * Returns true if the given date is past date else returns false.
 * @param  obj 
 * @return boolean
 */ 
function checkPastDate(obj, str){
	// Check first if the given date is valid.
	if(checkDate(obj)){
		if(isPastDate(obj)){
			alert(str+commonPastDateErrMsg);
			obj.value = "";		
			setTimeout(function(){obj.focus();}, 10);
			return false;
		} else {
			return true;
		}
		
	}
}

 /**
  * This function check if the given date is a past date.
  * Returns true if the given date is past date else returns false.
  * Tha paramerter to method can be an object or an Id of the element.
  * @param  obj (It can be an object or an Id of the element).
  * @return boolean
  */  
function isPastDate(obj){
	//Check if the obj is string which means its an ID then get the element.
	 if(typeof obj == "string"){
		 obj = document.getElementById(obj);
	 }
	// Date.parse(date) function returns the number of milliseconds from January 1, 1970 to the date
	// passed as parameter.
	if(Date.parse(obj.value) < Date.parse(getCurrentDate())){
		return true;
	}else{
		return false;
	}
}

/**
  * This function check if the given date is a future date and alerts 
  * a message if its a future date.
  * Returns true if the given date is future date else returns false.
  * @param  obj 
  * @return boolean
  */   
function checkFutureDate(obj, str){
	 //Check first if the given date is valid.		
	 if(checkDate(obj) && isWhitespace(obj.value) == false){
			if(isFutureDate(obj)){
				alert(str+commonFutureDateErrMsg);
				obj.value = "";		
				setTimeout(function(){obj.focus();}, 10);
				return false;
			}else {
				return true;
			}
	 }
}
  
/**
 * This function check if the given date is a future date.
 * Returns true if the given date is future date else returns false.
 * Tha paramerter to method can be an object or an Id of the element.
 * @param  obj (It can be an object or an Id of the element).
 * @return boolean
 */ 
function isFutureDate(obj){
	//Check if the obj is string which means its an ID then get the element.
	if(typeof obj == "string"){
		obj = document.getElementById(obj);
	}
	// Date.parse(date) function returns the number of milliseconds from January 1, 1970 to the date
	// passed as parameter.
	if(Date.parse(obj.value) > Date.parse(getCurrentDate())){
		return true;
	}else{
		return false;
	}
} 

 
  function checkDependentDateFields(obj){
	 var err = new Array();
	$("."+obj.id).each(function (i,element) {
		if(!isWhitespace(element.value)){ 
			if(isGreateDateByValue(obj.value,element.value)){
				err[err.length]= element.title+" "+ commonDateGreaterEqualMsg +" "+obj.title+".";
			}
		}
	});
	return checkErrors(err);
} 

  /**
   * This function checks if compareDtId (End date) is greater than compareToDtId (Start Date).
   * Date which must be greater is passed to compareDtId and the other date to compareToDtId
   * compareDtName and compareToDtName are corresponding field names.
   * clearFieldId field is used to clear the filed if date is less then provided date.
   * A error message is alerted if the compareDtId (End Date) is smaller then  compareToDtId (Start).
   * @param  compareDtId,compareToDtId,clearFieldId,compareDtName,compareToDtName
   * @return boolean
   */
   function checkGreaterEqualDate(compareDtId, compareToDtId,clearFieldId,compareDtName,compareToDtName) {	 
 		// Check first if the given dates are valid.
 		var clearFieldObj = document.getElementById(clearFieldId);
 		var compareDtObj =  document.getElementById(compareDtId);
 		var compareToDtObj =  document.getElementById(compareToDtId);
 		
  		if(checkDate(compareDtObj) && checkDate(compareToDtObj) && (!isWhitespace(compareDtObj.value))  && (!isWhitespace(compareToDtObj.value))  ){   
 	   		if(!isGreaterDate(compareDtId, compareToDtId) && !isDateEqual(compareDtId,compareToDtId)){   	
 	   			var msg = compareDtName +" "+ commonDateGreaterEqualMsg +" "+ compareToDtName+ ".";
 				alert(msg);
 				clearFieldObj.value = "";
 				setTimeout(function(){clearFieldObj.focus();}, 10);
 					return false;
 			} else {
 					return true;
 			}
  	    }
  		return false;
    }
   
   /**
    * This function checks if compareDtId (End date) is less than compareToDtId (Start Date).
    * Date which must be less than is passed to compareDtId and the other date to compareToDtId
    * compareDtName and compareToDtName are corresponding field names.
    * clearFieldId field is used to clear the filed if date is greater than provided date.
    * A error message is alerted if the compareDtId (End Date) is greater then  compareToDtId (Start).
    * @param  compareDtId,compareToDtId,clearFieldId,compareDtName,compareToDtName
    * @return boolean
    */
    function checkLessThanEqualDate(compareDtId, compareToDtId,clearFieldId,compareDtName,compareToDtName) {	 
  		// Check first if the given dates are valid.
  		var clearFieldObj = document.getElementById(clearFieldId);
  		var compareDtObj =  document.getElementById(compareDtId);
  		var compareToDtObj =  document.getElementById(compareToDtId);
   		if(checkDate(compareDtObj) && checkDate(compareToDtObj) && (!isWhitespace(compareDtObj.value))  && (!isWhitespace(compareToDtObj.value))  ){   
  	   		if(!isGreaterDate(compareToDtId, compareDtId) && !isDateEqual(compareToDtId,compareDtId)){   	
  	   			var msg = compareDtName +" "+ commonDateLessThanEqualMsg +" "+ compareToDtName+ ".";
  				alert(msg);
  				clearFieldObj.value = "";
  				setTimeout(function(){clearFieldObj.focus();}, 10);
  					return false;
  			} else {
  					return true;
  			}
   	    }
   		return false;
     }
  
/**
 * This function checks if compareDtId (End date) is greater than compareToDtId (Start Date).
 * Returns true if compareDtId (End date) is greater then compareToDtId (Start Date) else returns false.
 * @param  compareDtId,compareToDtId
 * @return boolean
 */
function isGreaterDate(compareDtId, compareToDtId){
	var	compareDtObj = document.getElementById(compareDtId);
	var	compareToDtObj = document.getElementById(compareToDtId);
	return isGreateDateByValue(compareDtObj.value, compareToDtObj.value);
 }

/**
 * @param compareDtObj
 * @param compareToDtObj
 */
function isGreateDateByValue(compareDtVal, compareToDtVal) {
	if(Date.parse(compareDtVal) > Date.parse(compareToDtVal)){
		return true;
	}else{
		return false;
	}
}

/**
 * @param compareDtObj
 * @param compareToDtObj
 */
function isLessDateByValue(compareDtVal, compareToDtVal) {
	if(Date.parse(compareDtVal) < Date.parse(compareToDtVal)){
		return true;
	}else{
		return false;
	}
}



/**
* This function checks if two dates are equal.
* Returns true if two dates are equal else returns false.
* @param  compareDtId,compareToDtId
* @return boolean
*/
function isDateEqual(date1Id,date2Id){
	var date1Obj = document.getElementById(date1Id);
	var date2Obj = document.getElementById(date2Id);
	if(Date.parse(date1Obj.value) == Date.parse(date2Obj.value)){
		return true;
	}else{
		return false;
	}
}

/**
* This function calculates number of days between two dates (Date Objects).
* Returns Number of days.
* @param  date1, date2
* @return int
*/
function dateDifference(date1, date2){
	//Number of Milliseconds in a day.
	var dayInMS = 1000 * 60 * 60 * 24;
	var date1_MS = date1.getTime();
	var date2_MS = date2.getTime();
	var difference = 0;
	if(date1_MS > date2_MS){
		difference = date1_MS - date2_MS;
	}else if(date2_MS > date1_MS){
		difference = date2_MS - date1_MS;
	}
	var difference_MS = Math.abs(difference);
	return Math.round(difference_MS/dayInMS);
}

/**
* This function checks if startTime(String) is greater than stopTime (String).
* This function is invoked only when Start & Stop Dates are equal.
* @param  startTime, stopTime, startTimeText, stopTimeText
* @return boolean
*/
function isGreaterTime(stopTime, startTime) {
   if(!isWhitespace(stopTime) && !isWhitespace(startTime)){
	   var stopTmArray = stopTime.split(":");
	   var startTmArray = startTime.split(":");
	   if(startTmArray[0] > stopTmArray[0] || (startTmArray[0] == stopTmArray[0] && startTmArray[1] > stopTmArray[1])){
		   return false;
	   }else {
		   return true;
	   }
   }
   return false;
}

/**
* This function shows an error message if startTime(String) is greater than stopTime (String).
* This function is invoked only when Start & Stop Dates are equal.
* @param  startTime, stopTime, startTimeText, stopTimeText
* @return boolean
*/
function isStartTimeGreaterThanStopTime(stopTime, startTime, stopTimeText, startTimeText){
	if(!isGreaterTime(stopTime, startTime)){
		var msg = stopTimeText +" "+ commonDateGreaterEqualMsg +" "+ startTimeText;
		alert(msg);
		return false;
	}
	return true;
}


/**
* Checks if the input time is greater than current time.
* This should be used after comparison with dates,If the date
* is current day then check for the future time.
* 
* @param  inTime input time object
* @return boolean returns  true if input time is less than current time.
*/
function checkFutureTime(inTime){
	checkTime(inTime);
	var today = new Date();
	var currentHours = today.getHours();
	var currentMinutes = today.getMinutes();
	var inTimeValue = inTime.value;
	var inTimeHours = inTimeValue.substring(0,2);
	var inTimeMinutes = inTimeValue.substring(3);
	if (inTimeHours > currentHours || (inTimeHours == currentHours && inTimeMinutes > currentMinutes)){
		alert(commonFutureTimeErrMsg);
		inTime.value = "";
		setTimeout(function(){inTime.focus();},10);
		return false;
	} else {
		return true;
	}
}

/**
 * This function checks to make sure Stop Time is later/greater than Start Time
 * */
function checkLaterTime(stopTmId, startTmId, clearFieldId, stopTmTxt, startTmTxt){
	var startTmObj = document.getElementById(startTmId);
	var endTmObj = document.getElementById(stopTmId);
	var clearFieldObj = document.getElementById(clearFieldId);
	
	if(!isWhitespace(startTmObj.value) && checkTime(startTmObj) 
			&& !isWhitespace(endTmObj.value) && checkTime(endTmObj)){
		if(!isGreaterTime(endTmObj.value, startTmObj.value)){
			var msg = stopTmTxt + " must be later than " + startTmTxt;
			alert(msg);
			clearFieldObj.value = "";
			setTimeout(function(){clearFieldObj.focus();}, 10);
		}else {
			return true;
		}
	}
	return false;
}

/**
* This function checks to make sure Start Time is before/less than Stop Time
* */
function checkBeforeTime(startTmId, stopTmId, clearFieldId, startTmTxt, stopTmTxt){
	var startTmObj = document.getElementById(startTmId);
	var endTmObj = document.getElementById(stopTmId);
	var clearFieldObj = document.getElementById(clearFieldId);
	
	if(!isWhitespace(startTmObj.value) && checkTime(startTmObj) 
			&& !isWhitespace(endTmObj.value) && checkTime(endTmObj)){
		if(!isGreaterTime(endTmObj.value, startTmObj.value)){
			var msg = startTmTxt + " must be before " + stopTmTxt;
			alert(msg);
			clearFieldObj.value = "";
			setTimeout(function(){clearFieldObj.focus();}, 10);
		}else {
			return true;
		}
	}
	return false;
}