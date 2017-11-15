/************************************************************
	Description:
		This script file contains many helper methods for
		doing client-side form validation via JavaScript.

	Author: CKSmith
	Date: 2001.06.26
	Version: 1.0
*************************************************************/
/**
 * -------------------------------------------------------------------
 * Modification History
 *
 * -------------------------------------------------------------------
 * Author      Date          Comments
 * Ram		   06/18/2010     Added getCalendar function
 * Sarat       06/23/2010     Added autoTab function
 *
 * 
 * -------------------------------------------------------------------
 *
 *
 **/
/*************************
 **** GLOBAL VARIABLES ***
 *************************/  
var productionMode = true;
var clickedOnCal = false;
//added saurabhg			
var values = new Array();
var dirtyFlag = 0;
var radioChecked;// Used in selecting, un-selecting radio box
var appName = navigator.appName;
var appVersion = navigator.appVersion;
var browser = "unknown";
var restrictFields = 'false';
var version = new Number(navigator.appVersion.substring(0,1));
if (navigator.appName.indexOf("Microsoft") != -1) browser = "ie";
if (navigator.appName.indexOf("Netscape") != -1) browser = "ns";

/*************************
 **** FUNCTION DECLARATIONS ***
 *************************/  

/* Firefox supports .indexOf on arrays natively but ie does not. This function brings .indexOf array support to ie.*/
if (!Array.prototype.indexOf) {  
  Array.prototype.indexOf = function(searchElement /*, fromIndex */) {  
    "use strict";  
    
    if (this === void 0 || this === null) throw new TypeError();  

    var t = Object(this);  
    var len = t.length >>> 0;  
    if (len === 0)  
      return -1;  

    var n = 0;  
    if (arguments.length > 0) {  
      n = Number(arguments[1]);  
      if (n !== n) // shortcut for verifying if it's NaN  
        n = 0;  
      else if (n !== 0 && n !== (1 / 0) && n !== -(1 / 0))  
        n = (n > 0 || -1) * Math.floor(Math.abs(n));  
    }  

    if (n >= len)  
      return -1;  

    var k = n >= 0  
          ? n  
          : Math.max(len - Math.abs(n), 0);  

    for (; k < len; k++) {  
      if (k in t && t[k] === searchElement)  
        return k;  
    }  
    return -1;  
  };  
}
  
/************************
 **** FORM VALIDATION ***
 ************************/

/**--------------------------------------------------------------------------------
 * THIS ROUTINE CHECKS TO SEE IF ALL THE CHARACTERS IN AN OBJECTS VALUE ARE NUMERIC.
 * POSITIVE AND NEGATIVE DECIMAL VALUES ARE ACCEPTABLE.
 *---------------------------------------------------------------------------------*/
function isValidDecmial(obj) {
	 var pattern = /^((\d?)|(([-+]?\d+\.?\d*)|([-+]?\d*\.?\d+))|(([-+]?\d+\.?\d*\,\ ?)*([-+]?\d+\.?\d*))|(([-+]?\d*\.?\d+\,\ ?)*([-+]?\d*\.?\d+))|(([-+]?\d+\.?\d*\,\ ?)*([-+]?\d*\.?\d+))|(([-+]?\d*\.?\d+\,\ ?)*([-+]?\d+\.?\d*)))$/
	 return pattern.test(obj.value);
}
function isPositiveDecmial(obj) {
	 var pattern = /^((\d?)|(([+]?\d+\.?\d*)|([+]?\d*\.?\d+))|(([+]?\d+\.?\d*\,\ ?)*([+]?\d+\.?\d*))|(([+]?\d*\.?\d+\,\ ?)*([+]?\d*\.?\d+))|(([+]?\d+\.?\d*\,\ ?)*([+]?\d*\.?\d+))|(([+]?\d*\.?\d+\,\ ?)*([+]?\d+\.?\d*)))$/
	 return pattern.test(obj.value);
}
function isPositiveInt(obj) {
	 return validNumeric = /^[0-9]+$/.test(obj.value);
}

function hasClass(ele,cls) {
    return ele.className.match(new RegExp('(\\s|^)'+cls+'(\\s|$)'));
}



/**-------------------------------------------------------------------------------------
 * CHECKS THE LENGTH OF A FIELD
 *	- REQUIRED: AN OBJECT REFERENCE (THE FIELD IN QUESTION MUST BE PASSED TO THE ROUTINE)
 * 	- OPTIONAL: THE MINIMUM LENGTH THE FIELD MUST BE TO RETURN TRUE (IF OMITTED ZERO IS USED)
 *--------------------------------------------------------------------------------------*/
 function chkLen(obj, len) {
 	var tmp = obj.value;
 	if (!len) len = 1;
 	if (tmp != null){
 		if (tmp.length < len) return false;
 		else return true;
 	} else return true;
}
 
	/**
	* This function is controlled on the key event.
	* If the input is key 0-9 or '.'; the input key is added to the existing value
	*	 otherwise discarded. 
	*	 The method does not allow the user to insert only one '.' in the text.
	*/
	function checkDecimalNumber(e,obj,numericType) {
		var key;
		var keychar;
		if (window.event)
			key = window.event.keyCode;
		else if (e)
			key = e.which;
		else
			return true;
		keychar = String.fromCharCode(key);
		keychar = keychar.toLowerCase();

		// control keys
		if ((key==null) || (key==0) || (key==8) || 
				(key==9) || (key==13) || (key==27))
			return true;  
		// Allow 0-9, '.' '-' 
		var allowed = "0123456789.-";
		if(numericType =='positive'){
			// Allow 0-9, '.'  
			allowed = "0123456789.";
		}
		if (((allowed).indexOf(keychar) > -1))
		{
			// Check if the entered character is '.'
			if(keychar=='.')
			{
				// if the object already has a '.'; discard the current '.'
				if(obj.value.indexOf('.')>-1)
				{
					return false;
				}
			}
			// Check if the entered character is '-'
			if(keychar == '-')
			{
				// if the object already has a '-'; discard the current '-'
				if(obj.value.indexOf('-') > -1)
				{
					// '-' indicates negative number and is allowed only at the beginning of the characters
					return false;
				}
			}
			return true;
		}
		else
		{
			return false;
		}
	}

/**
* This function is controlled on the key event.
* If the input is key 0-9; the input key is added to the existing value
* otherwise discarded. 
*	 
*/
function checkIntegerNumber(e,obj) {
	var key;
	var keychar;
	if (window.event)
		key = window.event.keyCode;
	else if (e)
		key = e.which;
	else
		return true;
	keychar = String.fromCharCode(key);
	keychar = keychar.toLowerCase();

	// control keys
	if ((key==null) || (key==0) || (key==8) || 
			(key==9) || (key==13) || (key==27))
		return true;  
	// Allow 0-9
	if ((("0123456789").indexOf(keychar) > -1))
	{
		return true;
	}
	else
	{
		return false;
	}
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

/**------------------------------------------------------------
 * INCREMENTS (OR DECREMENTS) THE PASSED DATE STRING
 * NOTE: dateStr SHOULD CONFORM TO THE ACCEPTABLE
 * JAVASCRIPT FORMATS FOR CREATING A DATE OBJECT
 * (I.E. 12/1/2001)
 *------------------------------------------------------------*/
function incrementDate(dateStr, days) {
    var date = null;
    if (dateStr != null && dateStr.length > 0) {
	   date = new Date(dateStr);
    } else {
        date = new Date();
    }
	var daysInMilliseconds = (days * 86400000);
	date.setTime(date.getTime() + daysInMilliseconds);
	return formatDate(date);
}

function onCalender(flag, txtDt){
	clickedOnCal = eval(flag);
	document.getElementById(txtDt).focus();
	return;
}

/**-----------------------------------------------------------------------------
 * CHECKS EACH FIELD IN THE FORM FOR SINGLE QUOTES (WHICH MESS UP DATABASE CALLS)
 *-----------------------------------------------------------------------------*/
 function checkForQuotes(formObj){
 	var ok = true; 	for (i=0; i<formObj.elements.length; i++){
 		var elemVal = formObj.elements[i].value;
 		if (elemVal != null){
 			if (elemVal.indexOf("'") != -1){
 				ok = false;
 				var msg = "A single quote was found in the highlighted field.\n";
 				msg += "Please remove/replace this character since it will\n";
 			   	msg += "cause an error in the attempt to update the database.";
 				if (navigator.userAgent.indexOf("Netscape")) formObj.elements[i].focus();
 				if (navigator.userAgent.indexOf("Microsoft")) formObj.elements[i].select();
 				alert(msg);
 				break;
 			}
 		}
 	}
 	return ok;
 }

/**-----------------------------------------------------------------------------
 * FINDS ALL THE SINGLE QUOTES AND INSERTS AN ESCAPE CHARACTER FOR EVERY ELEMENT
 * IN THE FORM
 *-----------------------------------------------------------------------------*/
function cleanQuotes(formObj){
 	var newStr = "";
 	var hit = false;
 	var pos = -1;
	var sStr = "";
	var eStr = "";
	var pStr = "";
	for (i=0; i<formObj.elements.length; i++){
 		var eVal = formObj.elements[i].value;
 		if (eVal != null && eVal.length > 0){
 			pos = eVal.indexOf("'");
 			if (pos > -1) hit = true;
 			while (pos > -1){
 				sStr = eVal.substring(0, pos);
 				eStr = eVal.substring(pos+1);
 				pStr = eVal.substring(pos-1, pos);
 				if (pStr != "\\") newStr += sStr + "\\'";
 				pos = eStr.indexOf("'");
 				eVal = eStr;
 			}
 			if (hit) formObj.elements[i].value = newStr + eStr;
 			hit = false;
 		}
 	}
 	return true;
}

/*-----------------------------------------------------
*Reformat method
*------------------------------------------------------*/

// reformat (TARGETSTRING, STRING, INTEGER, STRING, INTEGER ... )       
//
// Handy function for arbitrarily inserting formatting characters
// or delimiters of various kinds within TARGETSTRING.
//
// reformat takes one named argument, a string s, and any number
// of other arguments.  The other arguments must be integers or
// strings.  These other arguments specify how string s is to be
// reformatted and how and where other strings are to be inserted
// into it.
//
// reformat processes the other arguments in order one by one.
// * If the argument is an integer, reformat appends that number 
//   of sequential characters from s to the resultString.
// * If the argument is a string, reformat appends the string
//   to the resultString.
//
// NOTE: The first argument after TARGETSTRING must be a string.
// (It can be empty.)  The second argument must be an integer.
// Thereafter, integers and strings must alternate.  This is to
// provide backward compatibility to Navigator 2.0.2 JavaScript
// by avoiding use of the typeof operator.
//
// It is the caller's responsibility to make sure that we do not
// try to copy more characters from s than s.length.
//
// EXAMPLES:
//
// * To reformat a 10-digit U.S. phone number from "1234567890"
//   to "(123) 456-7890" make this function call:
//   reformat("1234567890", "(", 3, ") ", 3, "-", 4)
//
// * To reformat a 9-digit U.S. Social Security number from
//   "123456789" to "123-45-6789" make this function call:
//   reformat("123456789", "", 3, "-", 2, "-", 4)
//
// HINT:
//
// If you have a string which is already delimited in one way
// (example: a phone number delimited with spaces as "123 456 7890")
// and you want to delimit it in another way using function reformat,
// call function stripCharsNotInBag to remove the unwanted 
// characters, THEN call function reformat to delimit as desired.
//
// EXAMPLE:
//
// reformat (stripCharsNotInBag ("123 456 7890", digits),
//           "(", 3, ") ", 3, "-", 4)

function reformat (s)

{   var arg;
    var sPos = 0;
    var resultString = "";

    for (var i = 1; i < reformat.arguments.length; i++) {
       arg = reformat.arguments[i];
       if (i % 2 == 1) resultString += arg;
       else {
           resultString += s.substring(sPos, sPos + arg);
           sPos += arg;
       }
    }
    return resultString;
}

/**----------------------------------------------------
 * Used to Check if Upload File has file extension
 * gif, jpg, jpeg. png
 *-----------------------------------------------------*/
 function LimitAttach(form, file) {
 	extArray = new Array(".gif", ".jpg", ".png", ".jpeg");
 	allowLoad = false;
	if (!file) return;

	ext = file.substring(file.lastIndexOf('.'),file.length).toLowerCase();

	for (var i = 0; i < extArray.length; i++) {
		if (extArray[i] == ext)
			allowLoad = true; break;
	}
	if (allowLoad)
		return true;
	else {
		alert("Please only upload files that end in types:  "
		+ (extArray.join("  ")) + "\nPlease select a new "
		+ "file to upload and submit again.");
		return false;
	}
}



/***********************
 **** HELPER METHODS ***
 ***********************/

function dirtyFormConfirmation() {
    var msg = "The form has changed and you are moving off this screen.";
    msg += "\n\nIf you would like to proceed\n to the next screen click \"OK\". ";
    msg += "\nAll your changes will be lost. Otherwise click \"Cancel\"";
    return confirm(msg);
}

/**----------------------------------------------------------
 * ASKS THE USER IF THEY ARE SURE THEY WANT TO RESET THE FORM
 *-----------------------------------------------------------*/
function clearWarning(obj) {
    var msg = "!!! WARNING !!!\n";
    msg += "You are about to clear the form\n";
    msg += "-- ALL THE DATA ON THE SCREEN WILL BE CLEARED! --\nClick \"OK\" to proceed, otherwise click \"Cancel\".";
    if (confirm(msg)) {
        clearForm(obj);
        cleanUpWindows();
        //clearErrors();
    	return true;
    } else
    	return false;

}
/**----------------------------------------------------------
 * ASKS THE USER IF THEY ARE SURE THEY WANT TO DELETE THE ITEM FROM LIST
 *-----------------------------------------------------------*/
function deleteConfirmation() {
    var msg = "About to delete, are you sure?";
    msg += "\n\nTo continue with delete click \"OK\". ";
    msg += "\nOtherwise click \"Cancel\" \n";
    
    return confirm(msg);
}

/**-----------------------------------------------------------
 * Determines what type of object is calling the function
 * and changes the visibility of the target "elem" accordingly
 *-----------------------------------------------------------*/
function switchVis(obj, elem) {
    if (obj.type == "checkbox") {
       if (obj.checked) {
           elem.style.visibility="visible";
       } else {
           elem.style.visibility="hidden";
       }
    }
    if (obj.type == "select-one") {
       if (obj.selectedIndex > 0) {
           elem.style.visibility="visible";
       } else {
           elem.style.visibility="hidden";
       }
    }
 }

/******************************************************
 * USED TO SHOW HELP SCREENS AS POPUP WINDOWS
 ******************************************************/
var helpWin = null;

function showHelp(page){
//  alert("showHelp: " + page);
    
    var pageName = contextPath + "/helpAction.do?method=view&sfFileName=" + page;
//    alert("pageName: " + pageName);
 	var h = 400;
    var w = 650;		    
	helpWin = popup(w , h ,pageName, null,null, true);
    helpWin.moveTo(((screen.availWidth/2)-(w/2)), ((screen.availHeight/3)-(h/3)));
	helpWin.focus();
}

/*
    showDialog(page, h, w):
        page = page or text you would like to display
            To display text the string should be prefixed
            as follows...
            title(Page Title)text to display would follow here
        h = height of the window
        w = width of the window
*/

var dialogWin = null;
var pgHead1 = "<html><head><title>";
var pgHead2 = "</title><link rel=stylesheet href=ie.css></head><body>";
var pgFoot = "</body></html>";

function showDialog(page, h, w){
    if (h == null || h < 50) h = 200;
    if (w == null || w < 50) w = 400;
    if (page.indexOf("title(") == 0) {
        title = page.substring(page.indexOf("(")+1, page.indexOf(")"));
        page = page.substring(page.indexOf(")")+1);
        var pgHead = pgHead1 + title + pgHead2;
        dialogWin = window.open('blank.jsp', 'dialogScreen', 'height=' + h + ',width=' + w + ',toolbars=no,status=no,menubar=no,scrollbars=yes,resizable=yes');
        dialogWin.document.open();
        dialogWin.document.write(pgHead + page + pgFoot);
        dialogWin.document.close();
    } else {
        dialogWin = window.open(page, 'dialogScreen', 'height=' + h + ',width=' + w + ',toolbars=no,status=no,menubar=no,scrollbars=yes,resizable=yes');
    }
    dialogWin.moveTo(((screen.availWidth/2)-(w/2)), ((screen.availHeight/2)-(h/2)));
    dialogWin.focus();
    return dialogWin;
}

/*****************************************************
 * CLEARS THE FORM BY RESETTING THE EVERY ELEMENT
 * IN THE FORM
 ****************************************************/
function clearForm(formObj){
 	for (i=0; i<formObj.elements.length; i++){
 		var type = formObj.elements[i].type;
 		obj = formObj.elements[i];
 		//alert("Name: " + obj.name + " Type: " + type);
 		if (type == "checkbox")
 			obj.checked = false; //uncheck it
 		else if (type == "hidden")
 			obj.value = "";
		else if (type == "radio") {
 		   /*
 		   for (var j=0; j < obj.length; j++) {
 		   	var item = eval(obj + "[" + j + "]");
 		   	alert(obj[j].checked); 
 		   	if (item.defaultChecked)
 		   		item.checked = true;
 		   	else
 		   		item.checked = false;	
 		   }*/
 		   if (obj.defaultChecked)
 		   		obj.checked = true;
 		   else
 		   		obj.checked = false;	

		}
		else if (type == "select-one")
			obj.selectedIndex = 0;
		else if (type == "select-multiple")
		  obj.selectedIndex = 0;
		else if (type == "text")
			obj.value = "";
		else if (type == "textarea")
 			obj.value = "";
 		else if (type == "file")
 			obj.value = "";
 	}
}

// Checks the max length for the text area
function textCounter(field, maxlimit) {
	if (field.value.length > maxlimit){ 
		if (field.type == "textarea") {
			var endString = field.value.substring(maxlimit,field.value.length);
			var endStringReturnChars =endString.split("\n").length;
			field.value = field.value.substring(0, (maxlimit- (field.value.split("\n").length -endStringReturnChars)));
			if((field.value.split("\n").length + field.value.length ) > maxlimit ){
				field.value = field.value.substring(0, (maxlimit- field.value.split("\n").length));
				alert("You have reached the maximum characters allowed");
			}
		} else {
			field.value = field.value.substring(0, maxlimit);
			alert("You have reached the maximum characters allowed");
		}
	}
}

/**
 * REMOVES ANY STRAGGLER WINDOWS CREATED USING
 * showErrors() or showHelp()
 */
function cleanUpWindows(){

    //alert("cleanUpWindows()");
    /*
	if (errWin != null){
		if (!errWin.closed) errWin.close();
	}
	*/
	if (helpWin != null){
		if (!helpWin.closed) helpWin.close();
	}

    if (dialogWin != null){
        if (!dialogWin.closed) dialogWin.close();
    }
}

/*******************************
 **** USED TO ROLL THE IMAGE ***
 *******************************
var bGood = false;
var vGood = false;
if (browser == "ns" || browser == "ie") bGood = true;
if (version >= 4) vGood = true;
if (browser == "ns" && version == 3) vGood = true;
function rollon(imgName){
	if (bGood && vGood && imgName != null && imgName.length > 0){
		imgOn = eval(imgName + "on.src");
		document[imgName].src = imgOn;
	}
}
function rolloff(imgName){
	if (bGood && vGood && imgName != null && imgName.length > 0){
		imgOff = eval(imgName + "off.src");
		document[imgName].src = imgOff;
	}
}
function rollImages(){
	var msg = "";
	for (i=0; i<imgArray.length; i++){
		msg += imgArray[i] + "\n";
	}
	for (i=0; i<imgArray.length; i++){
		rollon(imgArray[i]);
	}
	// alert(msg);
}
function resetImages(){
	for (i=0; i<imgArray.length; i++){
		rolloff(imgArray[i]);
	}
}
*/

var dirty = false;

/*
 ********************************************
 * Used to display the user Info on the dialog
 *  window
 ********************************************
 */
 function userInfo(user) {
  	showDialog('userInfo.do?method=loadUserInfo&userinfoid=' + user, 200, 450);
 }


/*
 ********************************************
 * Used to check the valid phone number
 ********************************************
 */
 function checkUSPhoneNumber(number) {
 		var validPhoneExp = /^\d{7}$|^\d{3}[\-\s]?\d{4}$/;
		if (number == '' || number == null)
			return true;
		if (!validPhoneExp.test(number)) {
	
			//alert(number+"\nPhone Number must be of of either the format\n ####### or ###-#####, where '#' is a digit between '0' and '9'.");
			
			return false;
		} else
			return true;
	}

	/*
	************************************************
	*WhiteSpace manipulation in a  string
	************************************************
	*/
	// whitespace characters
var whitespace = " \t\n\r";

// Check whether string s is empty.

function isEmpty(s)
{   return ((s == null) || (s.length == 0))
}



// Returns true if string s is empty or 
// whitespace characters only.

function isWhitespace (s)

{   var i;

    // Is s empty?
    if (isEmpty(s)) return true;

    // Search through string's characters one by one
    // until we find a non-whitespace character.
    // When we do, return false; if we don't, return true.

    for (i = 0; i < s.length; i++)
    {   
        // Check that current character isn't whitespace.
        var c = s.charAt(i);

        if (whitespace.indexOf(c) == -1) return false;
    }

    // All characters are whitespace.
    return true;
}

/*
 * Returns the label to be used with validation error messages for the field. If a label is defined with a for attribute for  the input field,  
 * label value is used. If the title is specified for the field, uses the title for generating the message  else uses field name.
 * 
 * For a given field, a label, title or name may be specified using the format below. 
 * Ex: <label for="firstNameId">First Name</label><input type="text" id="firstNameId" title="First Name" name="firstName"/>
 */
function getFieldMsgLabel(inFieldId) {
	// For the message, get the label associated with the field
	var msgLabel = $('label[for=' + inFieldId + ']').html();
	if(msgLabel != null && msgLabel != '') {
		// if the label has colon, remove the colon from the label
		msgLabel = msgLabel.replace(':', '');
		
	} else {
		// if the field has no label, get the title specified for the field
		var exp = "#" + inFieldId;
		msgLabel = $(exp).attr('title');
		if (msgLabel == null || msgLabel == '') {
			// if the title is not specified for the field, get the field name
			msgLabel = $(exp).attr('name');
		}
	}
	return msgLabel;
}

/*
 * Returns error message that should be used for a required field if the value is not specified for the field. Gets the message label for the 
 * field and returns the message in the format, "First Name is required" if the field message label is First Name. Uses the error message in the 
 * format {0} is required from application resources file.
 * 
 * JavScript variable containing the message should be defined in a common JSP file. (Common.jsp). 
 */
function getFieldRequiredMsg(inFieldId) {
	var msgLabel = getFieldMsgLabel(inFieldId);		
	var msgTemplate = commonFieldRequiredMsg;
	msgTemplate = msgTemplate.replace("{0}", msgLabel);
	return msgTemplate;
}

/*
 * Validates that a value is specified for the field with id fieldId. If a value is not specified for the field, returns an error message else  
 * returns empty message. If an array for the error messages is specified as input, adds error message to the end of the array. Message is 
 * generated using field title or field name if the title is not specified. 
 * For some scenarios, messages generated may be identical for different fields. Ex: A list of results. If restrictDupMsgs is true, function 
 * checks if the error message already exists in the error message array and adds the message only if the message does not exists.
 * If the error message generated is the first error message, sets the focus to the field specified by inFocusFieldId. If inFocusFieldId i not 
 * sepcified, sets the focus on the inFieldId element.
 */
function validateRequired(inFieldId, inErrMsgs, restrictDupMsgs, inFocusFieldId) {
	var exp = "#" + inFieldId;
	var value = $(exp).val();
	if (($.isArray(value) && isEmpty(value)) 
			|| (!$.isArray(value) && isWhitespace($(exp).val()) )) {
		var message = getFieldRequiredMsg(inFieldId);
		
		if ($.isArray(inErrMsgs) 
				&& !(restrictDupMsgs && $.inArray(message, inErrMsgs) != -1) ) {
			inErrMsgs[inErrMsgs.length] = message;
			if(inErrMsgs.length == 1) {
				if(!inFocusFieldId) {
					$(exp).focus();
				} else {
					$("#" + inFocusFieldId).focus();
				}
			}
		}
		return message;
	}
}

// Removes all characters which appear in string bag from string s.

function stripCharsInBag (s, bag)

{   var i;
    var returnString = "";

    // Search through string's characters one by one.
    // If character is not in bag, append to returnString.

    for (i = 0; i < s.length; i++)
    {   
        // Check that current character isn't whitespace.
        var c = s.charAt(i);
        if (bag.indexOf(c) == -1) returnString += c;
    }

    return returnString;
}



// Removes all characters which do NOT appear in string bag 
// from string s.

function stripCharsNotInBag (s, bag)

{   var i;
    var returnString = "";

    // Search through string's characters one by one.
    // If character is in bag, append to returnString.

    for (i = 0; i < s.length; i++)
    {   
        // Check that current character isn't whitespace.
        var c = s.charAt(i);
        if (bag.indexOf(c) != -1) returnString += c;
    }

    return returnString;
}


// Removes all whitespace characters from s.
// Global variable whitespace (see above)
// defines which characters are considered whitespace.

function stripWhitespace (s)

{   return stripCharsInBag (s, whitespace)
}


 /*
  **********************************************************************
  * Function used to load the form values (of all the elements) on Load
  **********************************************************************
  */

  function storeValuesOnLoad(theForm)
  {
   var frm = eval("document."+theForm);
   for(i=0;i<frm.elements.length;i++)
  	{
  			if(frm.elements[i].type == "radio")
  			{
				if(frm.elements[i].checked)
				{
  					values[values.length] = frm.elements[i].value;
  				}
  			}
  			else if(frm.elements[i].type == "checkbox")
  			{
				if(frm.elements[i].checked)
				{
  					values[values.length] = "Y";
  				}
  				else
  				{
  					values[values.length] = "N";
  				}
  			}
  			else
  			{
  				values[values.length] = frm.elements[i].value;
  			}	
  	}
  	return values;
  }
  
  /*
   *********************************************************************************************
   * Used to check for Dirty Form.
   * Function to compare the values of elements stored on storeValuesOnLoad(in values array) 
   * and current values on the form so as to check for change in values(if any) and accordingly 
   * flash the message to 'save' the changes
   * or just close the window.
   *********************************************************************************************
   */
  
  function isDirty(theForm)
  {
  	var frm = eval("document."+theForm);
  	var val = new Array();
  	for(i=0;i<frm.elements.length;i++)
  	{	
  		if(frm.elements[i].type == "radio")
  		{
			if(frm.elements[i].checked)
			{
				val[val.length] = frm.elements[i].value;
			}
		}
		else if(frm.elements[i].type == "checkbox")
  			{
				if(frm.elements[i].checked)
				{
  					val[val.length] = "Y";
  				}
  				else
  				{
  					val[val.length] = "N";
  				}
  			}	
		else
		{
	  		val[val.length] = frm.elements[i].value;
	  	}	
  	}

  	for(i=0;i<val.length;i++)
  	{
  		if(val[i] != values[i])
		{ 
			dirtyFlag=1;
			break;
		}
  	}
  	if(dirtyFlag==1)
  	{
       	 if(dirtyFormConfirmation())
	       	 return true;
	     else
	     	 return false;  	 
  	}
  	else
  	{
  		return false;
  	}	
  }
  
  /*
   *********************************************************************************************
   * Function used to check for the pattern of SSN number
   * Checked SSN for mask (######### or ###-##-####)
   *********************************************************************************************
   */
  
  function checkSSNumber(number)
	{
 		var validSSNNumber = /^\d{9}$|^\d{3}[\-\s]\d{2}[\-\s]\d{4}$/;
		if (number == '' || number == null)
			return true;
		if (!validSSNNumber.test(number)) {
	  	    return false;
		} else
			return true;
  	}	

  /*
   *********************************************************************************************
   * Function used to check for the pattern of TIN number
   * Checked TIN for mask (######### or ##-#######)
   *********************************************************************************************
   */
  
  function checkTINumber(number)
	{
 		var validTINNumber = /^\d{9}$|^\d{2}[\-\s]\d{7}$/;
		if (number == '' || number == null)
			return true;
		if (!validTINNumber.test(number)) {
	  	    return false;
		} else
			return true;
  	}	
  	
 
   /*
   *********************************************************************************************
   * Function used to check for the pattern of ZIP code
   * Checked ZIP for mask (##### or #####-####)
   *********************************************************************************************
   */	
  function checkUSZip(number)
 {
	var validZip = /^\d{5}$|^\d{5}[\-\s]\d{4}$/;
	if (!validZip.test(number))
	{
		return false;
	}
	else
	{
		return true;
	}	
 }	
 
   /*
   *********************************************************************************************
   * Function used to change the case of the value in the textObject
   * to UpperCase. 
   *********************************************************************************************
   */	
 
 function changeCaseToUpper(textObject)
 {
	textObject.value = textObject.value.toUpperCase();
 }
 
/*****************************************************************************
 * Set the specified checkboxes to the specified state
 ****************************************************************************/
function selectBoxesAll(boxes, boxState) {
	if (!boxes) {
		return;	// nothing to check/uncheck
	}
	if (!boxes.length) {
		boxes = new Array(boxes);	// ensure for loop will work
	}
	if (boxes[0].type != "checkbox") {
		return;	// can't check/uncheck
	}

	var onOff = true;
	if (boxState == undefined) {
		onOff = true;
	} else if (boxState.type == "checkbox") {
		onOff = boxState.checked;
	} else if (typeof(boxState) == "boolean") {
		onOff = boxState;
	}

	for (var i = 0; i < boxes.length; i += 1) {
		boxes[i].checked = onOff;
	}
}
/*****************************************************************************
 * makes a new xml http request
 ****************************************************************************/
 function newXMLHttpRequest() {

	  var xmlreq = false;

	  // Create XMLHttpRequest object in non-Microsoft browsers
	  if (window.XMLHttpRequest) {
	    xmlreq = new XMLHttpRequest();

	  } else if (window.ActiveXObject) {

	    try {
	      // Try to create XMLHttpRequest in later versions
	      // of Internet Explorer

	      xmlreq = new ActiveXObject("Msxml2.XMLHTTP");
	      
	    } catch (e1) {

	      // Failed to create required ActiveXObject
	      
	      try {
	        // Try version supported by older versions
	        // of Internet Explorer
	      
	        xmlreq = new ActiveXObject("Microsoft.XMLHTTP");

	      } catch (e2) {

	        // Unable to create an XMLHttpRequest by any means
	        xmlreq = false;
	      }
	    }
	  }

	return xmlreq;
}
 function changeFocus(source, target){
		if(source.value.length == source.maxLength){
			if (determineCursorPosition(source) ==  source.maxLength){
				target.focus();
			}
		}
	}


 function evaluateStr(str){
	str = "" + str;
	str = str.replace(/&#39;/g,"\'");
	str = str.replace(/&quot;/g,"\"");
	str = str.replace(/&amp;/g,"\&");
	str = str.replace(/&lt;/g,"\<");
	str = str.replace(/&gt;/g,"\>");
	str = str.replace(/&#010;/g,"\n");
	str = str.replace(/&#013;/g,"\r");
	 
	return str;
}
 function htmlEncode(str){
	 str = str.replace(/&/g,"&amp;");
	 str = str.replace(/</g,"&lt;");
	 str = str.replace(/>/g,"&gt;");
	 str = str.replace(/\"/g,"&quot;");
	 
	 return str;
 }
 
 RegExp.escape = function(str){
     var specials = new RegExp("[.*+?|()\\[\\]{}\\\\]", "g"); // .*+?|()[]{}\
	  return str.replace(specials, "\\$&");
 }
 
 /*****************************************************************************
 * This method is used to evaluate whether the number entered is 
 * in the desired format #####.### or ##### or .###
 * input params
 * fieldObject is this object
 * maxIntLen is the max number of characters allowed for integer part of the decimal number
 * maxDecLen is the max number of characters allowed for fractional part of the decimal number 
 ****************************************************************************/ 
 function decimalCheck(fieldObject, maxIntLen, maxDecLen) {
	    //If the number is negative, check to make sure '-' is only at the beginning
	    var fieldValue = fieldObject.value;	
	    var negIndex = fieldValue.indexOf("-");
	 	if(negIndex > 0) {
	 		return false;
	 	} else if(negIndex == 0) {
	 		if(fieldValue.length == 1) {
	 			return false;
	 		}
	 		fieldValue = fieldValue.split("-")[1];
	 	}
	 
		if(fieldValue.indexOf(".") < 0 ) {
			if(fieldValue.length > maxIntLen) {
				return false;
			}else {
				return true;
			}
		}else {
		  	var val = new Array();
		  	val=fieldValue.split(".");
		  	if(val[0].length > maxIntLen || val[1].length > maxDecLen || (val[0].length == 0 && val[1].length == 0)) {
				return false;
		  	}	
		 	return true;	
		}
} 
 String.prototype.trim = function() {
		return this.replace(/^\s+|\s+$/g,"");
	}
 
 /**
  * This method is used to submit the data on click 
  * of the pagination. the data is submitted to the
  * updateListPageData method in the otrackDispatchAction class.
  * 
  * Include this JavaScript when you have HTML controls (like 
  * text box or check box etc on your listing page)
  * 
  * 
  * */
 function submitListPageData(actionPath){
 	if (validate()){
 		var formElements = document.forms[0].elements;
		
 		for (var i = 0; i < formElements.length; i++) {
			if (formElements[i].tagName == 'INPUT'
				|| formElements[i].tagName == 'SELECT'
					|| formElements[i].tagName == 'TEXTAREA'){
				
				formElements[i].disabled = false;
			}
		}
 		
 		document.forms[0].action = actionPath;
 	 	document.forms[0].submit();
 	}
 }
  
  /**
  * Overwrite this method on your page to validate the form data 
  *  
  * */
 function validate (){
	 return true;
 }
 
 /**
  * Returns false if the event is keypress which indicates whitespace (space or tab)
  * Whitespace keys Space (Key 32), Tab (Key 9).
  * 
  * @param e event
  * @returns {Boolean}
  */
 function filterWhiteSpaceInput(e) {
	 var key;
	 if (window.event)
		 key = window.event.keyCode;
	 else if (e)
		 key = e.which;
	 else
		 return true;

	 var result = key != 32 &&  key != 9; 
	 return result;

 }
  /**
   * This method will restrict the user from entering
   * Numbers or Alphabets or AlphaNumeric depending on
   * the value you send and returns true or false for the
   * event handler "onkeypress".
   * 
   * 
   * */
  function FilterAlphaNumericInput(e,requestorId)
  {
	  var key;
	  var keychar;
	  var requestId = requestorId;

	  if (window.event)
		  key = window.event.keyCode;
	  else if (e)
		  key = e.which;
	  else
		  return true;
	  keychar = String.fromCharCode(key);
	  keychar = keychar.toLowerCase();

	  // control keys
	  if ((key==null) || (key==0) || (key==8) || 
		(key==9) || (key==13) || (key==27) || (key==32))
		  return true;   
	  // Alphabets
	  else if(requestId == "alpha")
	  {
		  if ((("abcdefghijklmnopqrstuvwxyz").indexOf(keychar) > -1))
			  return true;
		  else
			  return false;
	  }
	  // Numbers
	  else if(requestId == "num")
	  {
		  if ((("0123456789").indexOf(keychar) > -1))
			  return true;
		  else
			  return false;
	  }
	  // Alphabets and Numbers
	  else if ((("abcdefghijklmnopqrstuvwxyz0123456789").indexOf(keychar) > -1))
		  return true;
	  else
		  return false;
  }
     
   /**
   *This generic function add/remove the Anchor tag link and onClick event
   *@author r9g000is
   *This method disables the anchor tag. 
   *Modified by Anurag on 09/22/2010
   **/
   function disableAnchor(obj, disable)
   {
   	var href = obj.getAttribute("href");
   	var onclick = obj.getAttribute("onclick");
   	var href_bak = obj.getAttribute("href_bak");
   	var onclick_bak = obj.getAttribute("onclick_bak");
   	
   	if(disable)
   	{
   		if(href_bak == null)
   		{
   			href_bak = document.createAttribute('href_bak');
   			obj.setAttributeNode(href_bak);
   		}
   		if(onclick_bak == null)
   		{
   			onclick_bak = document.createAttribute('onclick_bak');
   			obj.setAttributeNode(onclick_bak);
   		}
   		if(href && href != "" )
   		{
   			obj.setAttribute('href_bak', href);
   		}
   		if(onclick && onclick != "" )
   		{
   			obj.setAttribute('onclick_bak', onclick);
   		}
   		obj.removeAttribute('href');
   		obj.setAttribute('onclick','');
   		obj.style.color="gray";
   	} 
   	else
   	{
   	  if(obj.attributes['href_bak'] != null && obj.attributes['href_bak'].nodeValue != '') 
   	  {
   		  obj.setAttribute('href', obj.attributes['href_bak'].nodeValue);
   	  }
   	  if(obj.attributes['onclick_bak'] != null && obj.attributes['onclick_bak'].nodeValue != '') 
   	  {
   		  obj.setAttribute('onclick', obj.attributes['onclick_bak'].nodeValue);
   	  }
   	  obj.style.color="blue";
   	}
   }

/**--------------------------------------------------------------------------------
* @author Ram Vangala
* @param Object
* @return 
* THIS ROUTINE DISABLES ALL THE INPUT ELEMENTS (TEXTbOX, SELECT, CHECKBOX 
* AND THE BUTTONS {EXCEPT 'BACK' BUTTON}) WHEN THE RESTRICT FLAG IS SET TO TRUE
* 
* 04/27/2011 -- This function modified by ITSD to accept param/integer indicating
* which form to be restricted when multiple forms are on the jsp.  -- JEM01#IS
* @param formIndex (if the param is not passed in, default equals form[0])
*---------------------------------------------------------------------------------*/
function disableAllInputsOnRestrict(formIndex) {
	 if (restrict == 'true' || restrictFields == 'true' ) {
		 /**This is to make sure if restrict is true then restrictFields is also true
		 this variable is used in the calender-setup.js*/
		 restrictFields = 'true';
		 if(formIndex == null || formIndex == 'undefined'){
			 formIndex = 0;
		 }
		 if(document.forms[formIndex] != null){
	 		 var formElements = document.forms[formIndex].elements;
	 		 var namesToSkip = ['footerRetrieveTextbox','footerRetrieveButton','cycyleList','cancel','Back','back'];
	 		 var tagsToDisable = ['INPUT','SELECT'];
	 		 for (var i = 0; i < formElements.length; i++) {
			 	if ( namesToSkip.indexOf(formElements[i].name) == -1 && formElements[i].type != 'hidden' && formElements[i].className != "enableWithRestrict") {	
				 	if (tagsToDisable.indexOf(formElements[i].tagName) >= 0 || formElements[i].type == 'button'){
						 formElements[i].disabled = true;
				 	}				 				 
				 	if (formElements[i].tagName == 'TEXTAREA'){
						 formElements[i].readOnly = true;
				 	}
				}
		 	 }

		 	var allLinks = document.getElementsByTagName("a");
		 	for (var i=0; i<allLinks.length; i++) {
				if(allLinks[i].href != null && !allLinks[i].href.match("window.print") && !allLinks[i].href.match("showHelp")&& !allLinks[i].className.match("enableWithRestrict")){
				//If the anchor is apart of the left hand navigation, don't disable the link.
					if (allLinks[i].parentNode.className !="menuItem" && 
						allLinks[i].parentNode.className !="navLink" && 
						allLinks[i].parentNode.id !="offenderSearchSpan"){
							disableAnchor(allLinks[i], true);
					}		  		
				}			
		 	}
		 //End of ITSD's added if block
		 }
	 }
}

//TODO disableAllInputsOnRestrict and disableEnableAllFormElements were modified by separate teams.
//This has not been fully integrated at time of merge to keep things working. This needs to 
//have all the features refactored into one function again. See versions 1.24.2.52 and Head changes.
/*
* Sets the disabled attribute for the form elements to true or false specified by input.
*/
function disableEnableAllFormElements(disabled) {
	 var formElements = document.forms[0].elements;
	 for (var i = 0; i < formElements.length; i++) {
		 if (formElements[i].tagName == 'INPUT'
			 || formElements[i].tagName == 'SELECT'
				 || formElements[i].type == 'button'
					 || formElements[i].tagName == 'TEXTAREA') 
		 {
			 if ( formElements[i].name != 'footerRetrieveTextbox' && formElements[i].name != 'footerRetrieveButton' && 
					 formElements[i].name != 'cycyleList' &&   formElements[i].type != 'hidden'&&
					 !((formElements[i].name == 'cancel' || formElements[i].name == 'Back')) && !hasClass(formElements[i],"enableWithRestrict"))
			 {
				 formElements[i].disabled = disabled;
			 }
		 }
	 }

	 var allLinks = document.getElementsByTagName("a");
	 for (var i=0; i<allLinks.length; i++) {
		if(allLinks[i].href != null && !allLinks[i].href.match("window.print") && !allLinks[i].href.match("showHelp")&& !hasClass(allLinks[i],"enableWithRestrict")){
		//If the anchor is apart of the left hand navigation, don't disable the link.
			if (allLinks[i].parentNode.className !="menuItem" && 
				allLinks[i].parentNode.className !="navLink" && 
				allLinks[i].parentNode.id !="offenderSearchSpan" && 
				allLinks[i].parentNode.id !="accountSearchSpan"){
				disableAnchor(allLinks[i], disabled);
			}		  		
		}			
	 }
}

  /***
   * @author Ram Vangala
   * @date 11/18/2009
   * @param str
   * @return 
   * The function checks for '@' and '.' in the email id 
   * True if valid email id
   * False otherwise
   */
  function emailcheck(str) {

		var at="@";
		var dot=".";
		var lat=str.indexOf(at);
		var lstr=str.length;
		var ldot=str.indexOf(dot);
		if (str.indexOf(at)==-1){
		   return false
		}

		if (str.indexOf(at)==-1 || str.indexOf(at)==0 || str.indexOf(at)==lstr){
		   return false
		}

		if (str.indexOf(dot)==-1 || str.indexOf(dot)==0 || str.indexOf(dot)==lstr){
		    return false
		}

		 if (str.indexOf(at,(lat+1))!=-1){
		    return false
		 }

		 if (str.substring(lat-1,lat)==dot || str.substring(lat+1,lat+2)==dot){
		    return false
		 }

		 if (str.indexOf(dot,(lat+2))==-1){
		    return false
		 }

		 if (str.indexOf(" ")!=-1){
		    return false
		 }

			 return true;
} 
   
   function handleSaveButton(saveButton, disable){
	   saveButton.disabled = disable;
   }
    
    /**
     * Check for only alpha characters in the name. ie. Last name, first name and middle name.
     **/
     function checkField(field, msg) {
		if (/[^a-z\s&]/gi.test(field.value)) {	
			alert(msg +" Name must contain only alpha characters.");			 
			field.value = "";
			field.focus();
			return false;				
		}
		return true;
	 }
   
/**
 * @param requestId -- User First & Last Name (id of input box)
 * @param userRefId -- User Id of selected User (id of input (hidden or visible) box) 
 * @param locationRefId -- Logged in user location 
 * @param formNumber -- The number of the form on the page that contain the element to store the user name 
 * @return
 */
function findUser(requestId, userRefId, locationRefId, formNumber) {	 	
 	// if form number not passed, set the form value to 0
 	if(undefined == formNumber){
 		formNumber = 0;
 	}
	var path = contextPath+"/findUserAction.do?method=findUser&requestId="+requestId+"&locationId="+locationRefId+"&userRefIdField="+userRefId+"&formNumber="+formNumber;
	newWin = popup(650, 650, path , null, null, true);
	newWin.focus();				
}

/**
 * @param requestId -- User First & Last Name (id of input box)
 * @param userRefId -- User Id of selected User (id of input (hidden or visible) box) 
 * @param locationRefId -- Logged in user location 
 * @param emailId -- Email Id of the selected user (id of input (hidden or visible box))
 * @param formNumber -- The number of the form on the page that contain the element to store the user name 
 * @return
 */
function findUserAndEmailId(requestId, userRefId, locationRefId, emailId, formNumber) {	 	
 	// if form number not passed, set the form value to 0
 	if(undefined == formNumber){
 		formNumber = 0;
 	}
 	
	var path = contextPath+"/findUserAction.do?method=findUser&requestId="+requestId+"&locationId="+locationRefId+"&userRefIdField="+userRefId+"&userEmailIdField="+emailId+"&formNumber="+formNumber;
	newWin = popup(650, 650, path , null, null, true);
	newWin.focus();				
}
  	 
 /**
  * @param requestId -- User First & Last Name (id of input box)
  * @param userRefId -- User Id of selected User (id of input (hidden or visible) box) 
  * @param locationRefId -- Logged in user location 
  * @param locationName -- Location name (in text) of the selected user
  * @return
  */
// Dan Deschenes (12-08-2011) overloading not supported, so the following function supersedes this one. 
// function findUserAndLocation(requestId, userRefId, locationRefId, locationName)
// {
//	 // var path = contextPath+"/findUserAction.do?method=findUser&requestId=referringStaff&locationId=0&locationName=locName&userRefIdField=referredByUserRefId&formNumber=0";
//	var path = contextPath+"/findUserAction.do?method=findUser&requestId="+requestId+"&locationId="+locationRefId+"&locationName="+locationName+"&userRefIdField="+userRefId+"&formNumber=0";
//	newWin = popup(650, 650, path , null, null, true);
//	newWin.focus();
// }
  	
  /**
   * This method returns the User refId, UserName, locationRefId, locationName
   * @param requestId -- User First & Last Name (id of input box)
   * @param userRefId -- User Id of selected User (id of input (hidden or visible) box) 
   * @param locationRefId -- Logged in user location 
   * @param locationName -- Location name (in text) of the selected user
   * @param requestLocationRefId Sets the user location to this Id.
   * @return
   */
  function findUserAndLocation(requestId, userRefId, locationRefId, locationName, requestLocationRefId)
  {
 	var path = contextPath+"/findUserAction.do?method=findUser&requestId="+requestId+"&locationName="+locationName+"&userRefIdField="+userRefId+"&formNumber=0&requestLocationRefId="+requestLocationRefId;
 	 if(locationRefId != undefined && !isEmpty(locationRefId)) {
 		 path = path+"&locationId="+locationRefId;
 	 }
 	newWin = popup(650, 650, path , null, null, true);
 	newWin.focus();
  }
  
//TODO Dan Deschenes 01-20-2012 This is the ftrack version of the findUserAndLocation.
//Parameters are different from head version and must be refactored into one method. 
 /**
  * @param requestId -- User First & Last Name (id of input box)
  * @param userRefId -- User Id of selected User (id of input (hidden or visible) box) 
  * @param locationRefId -- Logged in user location 
  * @param locNameId -- Location name ID of the selected user,This can be DIV ID OR a text field ID.
  * @param selectedUserLocationRefIdField -- Location dropdown  field ID OR a text field ID.  
  * @return
  */
 /* 
 function findUserAndLocation(requestId, userRefId, locationRefId, locNameId,selectedUserLocationRefIdField){

	 var path = contextPath+"/findUserAction.do?method=findUser&requestId="+requestId+"&locationId="+locationRefId+"&userRefIdField="+userRefId+"&formNumber=0&locNameId="+locNameId+"&selectedUserLocationRefIdField="+selectedUserLocationRefIdField;
	 newWin = popup(650, 650, path , null, null, true);
	 newWin.focus();				
 }
 */
  
function currentUser(objName, objRefId){
	objName.value  = currUserName;
	objRefId.value = currUserRefId;
	var objNameId = objName.id;
	$('#' + objNameId).trigger('change');
}

function currentUserAndLocation(userNameId,userRefId,locNameId,locRefId){
	if(!isWhitespace(locRefId)){
		document.getElementById(locRefId).value = currUserLocRefId;
	}
	if(!isWhitespace(locNameId)){
	
		if(document.getElementById(locNameId).nodeName=='DIV'){		
			document.getElementById(locNameId).innerHTML = currUserlocNameId;
		}else{
		   document.getElementById(locNameId).value = currUserlocNameId;
		}
	}
	currentUser(document.getElementById(userNameId),document.getElementById(userRefId));
	// trigger on change event for location
	if(!isWhitespace(locRefId)) {
		$('#' + locRefId).trigger('change');
	}
}

/*
 * Sets the current user and email id
 */
function currentUserAndEmailId(userNameId,userRefId,emailId) {
	currentUser(document.getElementById(userNameId),document.getElementById(userRefId));
	// Set the value of the user email
	if(document.getElementById(emailId).value) {
		document.getElementById(emailId).value = currUserId;
	} else {
		document.getElementById(emailId).innerText = currUserId;
	}
}

   /**--------------------------------------------------------------------------------
    * @author Ram Vangala
    * @param Object
    * @return 
    * THIS ROUTINE CHECKS TO SEE IF ALL THE CHARACTERS IN AN OBJECTS VALUE ARE ONLY NUMERIC (NO DECIMALS)
    * True if valid number with no decimals or comma, etc
    * False otherwise
    *  
    *---------------------------------------------------------------------------------*/
   function isOnlyNumber(obj) {
   	var v = obj.value;
   	if((isNaN(v))||(v.indexOf(".")>=0)||(v.indexOf("+")>=0)||(v.indexOf("-")>=0))
   	{
   		return false;
   	}
   	return true;
   }
    
    /**
     * This function selects all the values in the MultiTransfer box irrespective of the existing selections.
     * The parent item will be unselected and all its children will be selected.
     * The parent id will be removed from the children's id during the process 
     * of selection as we will be just saving the children id.
     * @author Ram Vangala
     */
    function selectAllMultiLevelOptions(selectBoxId){ 
   	 $('#'+selectBoxId+' option').each(function(i){
    		var tempidValue = $(this).attr('value');
 		var tempvalueArray=tempidValue.split("$!");
 		if(tempvalueArray.length>1 )
 		{
 			var newObj = $(this).attr();
 			var optionText = $(this).html();
 			var labelText=optionText.split("&nbsp;&nbsp;");
 			if(labelText.length>1)
 			{
 				newObj.text(evaluateStr("  "+labelText[1]));
 			}
 			else
 			{
 				newObj.text(evaluateStr(labelText));
 			}
 			newObj.val(tempvalueArray[0]);
 			$(this).attr("selected", "selected");
 		}
 		else
 		{
 			$(this).attr("selected", ""); 
 		}
     });
   	}
/* Calendar code starts*/

/**
 * This method creates calendar dynamically and popups on the page.
 * @param button [Mandatory] - Calendar button object
 * @param inputDateField [Mandatory] - Input text field id to display date.
 * @param dateFormat [Optional] - Date format to display on input date field. Default format is '%m/%d/%Y'
 * @param inputTimeField [Optional] - Input text field id to display Time.
 * @param timeFormat [Optional] - Time format to display on input time field. Default format is '%H:%M'.
 * @param time24 [Optional] - True - 24hrs display, False - 12hrs display.
 * @param align [Optional] - Alignment of calendar with button position. Default value is 'Rb'.
 * @param showTime [Optional] - To display time on the calendar. Default value is 'false'.
 * @return Calendar
 */
function getCalendar(button, inputDateField, dateFormat, inputTimeField, timeFormat,time24,align,showTime){
	if(restrictFields == 'true' || button.getAttribute("disable") == 'true'){
	 return null;
	}
	var inputDateFld = document.getElementById(inputDateField);
	var inputTimeFld = document.getElementById(inputTimeField);
	var calen = null;
	if(validateCalFields(button,inputDateFld,inputTimeField)){
		calen = new Calendar(0, null, onDateSelect, onCalClose);
		calen.inputDateField = inputDateFld;
		if(inputTimeFld != null) calen.inputTimeField = inputTimeFld;
		if(dateFormat == undefined  || dateFormat == null || dateFormat.trim() == '') dateFormat = "%m/%d/%Y";
		if(time24 != undefined && time24 != null) calen.time24 = time24;
		if(timeFormat == undefined || timeFormat == null || timeFormat.trim() == ''){
			timeFormat = "%H:%M";
		}
		timeFormat = getTimeFormat(timeFormat,calen.time24);
		calen.inputFieldDateFormat = dateFormat;
		calen.inputFieldTimeFormat = timeFormat;
		calen.setDateFormat(dateFormat+" "+timeFormat);
		if(showTime != undefined && showTime != null) calen.showsTime = eval(showTime);
		calen.create();
		if(align == null) align = "Rb";
		calen.showAtElement(button, align);
	}
	inputDateFld.focus();
	return calen;
}

	/**  Month Date Year Calendar code starts*/

  /**
   *	This will dynamically pop-ups the calendar to select Month, Date and Year in a separate fields.
   * 
   * @param button 				[Mandatory] -- Calendar button object
   * @param inputMonthField 	[Mandatory] -- Input text field id to display month.
   * @param inputDayField 		[Mandatory] -- Input text field id to display date.
   * @param inputYearField		[Mandatory] -- Input text field id to display year.
   * @param monthFormat  		[Optional]	-- Month format to display on input month field. Default format is '%m'.
   * @param dayFormat 			[Optional] 	-- Day format to display on input day field. Default format is '%d'.
   * @param yearFormat 			[Optional]	-- Year format to display on input year field. Default format is '%Y'.
   * @param inputTimeField 		[Optional]	-- Input text field id to display Time.
   * @param timeFormat  		[Optional] 	-- Time format to display on input time field. Default format is '%H:%M'.
   * @param time24  			[Optional] 	-- True - 24hrs display, False - 12hrs display.
   * @param align  				[Optional] 	-- Alignment of calendar with button position. Default value is 'Rb'.
   * @param showTime  			[Optional] 	-- To display time on the calendar. Default value is 'false'.
   * @return Calendar
   */

 function getMonthDateYearCalendar(button, inputMonthField, inputDayField, inputYearField, monthFormat, dayFormat, yearFormat, inputTimeField, timeFormat,time24,align,showTime){
		if(restrictFields == 'true' || button.getAttribute("disable") == 'true'){
		 return null;
		}
		
		var inputMonthFld = document.getElementById(inputMonthField);
		var inputDayFld = document.getElementById(inputDayField);
		var inputYearFld = document.getElementById(inputYearField);
		var inputTimeFld = document.getElementById(inputTimeField);
		var calen = null;
			
				
			if(validateMonthDateYearCalFields(button,inputMonthFld, inputDayFld, inputYearFld, inputTimeField)){
				calen = new Calendar(0,  null, onMonthDateYearSelect, onCalClose);
				calen.inputMonthField = inputMonthFld;
				calen.inputDayField = inputDayFld;
				calen.inputYearField = inputYearFld;
				
				if(inputTimeFld != null) calen.inputTimeField = inputTimeFld;
				if(monthFormat == undefined  || monthFormat == null || monthFormat.trim() == '') monthFormat = "%m";
				if(dayFormat == undefined  || dayFormat == null || dayFormat.trim() == '') dayFormat = "%d";
				if(yearFormat == undefined  || yearFormat == null || yearFormat.trim() == '') yearFormat = "%Y";
				
				if(time24 != undefined && time24 != null) calen.time24 = time24;
				if(timeFormat == undefined || timeFormat == null || timeFormat.trim() == ''){
					timeFormat = "%H:%M";
				}
				timeFormat = getTimeFormat(timeFormat,calen.time24);
				calen.inputFieldDayFormat = dayFormat;
				calen.inputFieldMonthFormat = monthFormat;
				calen.inputFieldYearFormat = yearFormat;
				calen.inputFieldTimeFormat = timeFormat;
				calen.setDateFormat(monthFormat+ " " + dayFormat + " " + yearFormat + " "+timeFormat);
				if(showTime != undefined && showTime != null) calen.showsTime = eval(showTime);
				calen.create();
				if(align == null) align = "Rb";
				calen.showAtElement(button, align);
			}
			inputYearFld.focus();
			
			return calen;
	}
 

 function getTimeFormat(timeFormat, time24){
	 if(time24 != undefined && time24 != null){
		 if(time24){
			 timeFormat = timeFormat.replace("I","H");
			 timeFormat = timeFormat.replace("l","k");
			 timeFormat = timeFormat.replace("%p","");
		 }else{
			 timeFormat = timeFormat.replace("H","I");
			 timeFormat = timeFormat.replace("k","l");
			 if(timeFormat.indexOf("%p") == -1){
				 timeFormat +=" %p"; 
			 }
		 }
	 }
	 return timeFormat; 
 }
 function onDateSelect(calendar, date) {
	var input_date_field = calendar.inputDateField;
	var input_time_field = calendar.inputTimeField;
	if(input_time_field != undefined && input_time_field != null && input_date_field.id == input_time_field.id){
		input_date_field.value = date;
	}else{
		input_date_field.value = calendar.date.print(calendar.inputFieldDateFormat);
		if(input_time_field != undefined && input_time_field != null){
			input_time_field.value = calendar.date.print(calendar.inputFieldTimeFormat);
		}
	}
	if (calendar.dateClicked) {
		calendar.callCloseHandler();
	}
}
 
 function onMonthDateYearSelect(calendar, month, day, year) {
		var input_month_field = calendar.inputMonthField;
		var input_day_field = calendar.inputDayField;
		var input_year_field = calendar.inputYearField;
		
		var input_time_field = calendar.inputTimeField;
		if(input_time_field != undefined && input_time_field != null && input_day_field.id == input_time_field.id){
			input_month_field.value = month;
			input_day_field.value = day;
			input_year_field.value = year;
		}else{
			input_day_field.value = calendar.date.print(calendar.inputFieldDayFormat);
			input_month_field.value = calendar.date.print(calendar.inputFieldMonthFormat);
			input_year_field.value = calendar.date.print(calendar.inputFieldYearFormat);
		
			if(input_time_field != undefined && input_time_field != null){
				input_time_field.value = calendar.date.print(calendar.inputFieldTimeFormat);
			}
		}
		if (calendar.dateClicked) {
			calendar.callCloseHandler();
		}
	}
	 

function onCalClose(calendar) {
	calendar.hide();
}

	/**
	 * 
	 * @param button
	 * @param inputMonthFld
	 * @param inputDayFld
	 * @param inputYearFld
	 * @param inputTimeField
	 * @return
	 */
function validateMonthDateYearCalFields(button, inputMonthFld, inputDayFld, inputYearFld, inputTimeField){
	var err = new Array();
	if(button == null || button == undefined){
		err[err.length] ="Button is undefied.";
	}
	if(inputDayFld == null || inputDayFld == undefined || inputMonthFld == null || inputMonthFld == undefined || inputYearFld == null || inputYearFld == undefined){
		err[err.length] = "Input Date field is undefined.";
	}
	if(inputTimeField != undefined && inputTimeField != null && inputTimeField.trim() != ''){
		var inputTimeFld = document.getElementById(inputTimeField);
		if(inputTimeFld == null || inputTimeFld == undefined){
			err[err.length] = "Input Time field is undefined.";
		}
	}
	if(err.length > 0){
	 	var msg = "Calendar setup errors...\n";
       	for (i=0; i<err.length; i++) {
	       	msg += " - " + err[i] + "\n";
     	}
       	alert(msg);
       	return false;
	}else {
		return true;
	}
}

function validateCalFields(button, inputDateFld, inputTimeField){
	var err = new Array();
	if(button == null || button == undefined){
		err[err.length] ="Button is undefied.";
	}
	if(inputDateFld == null || inputDateFld == undefined){
		err[err.length] = "Input Date field is undefined.";
	}
	if(inputTimeField != undefined && inputTimeField != null && inputTimeField.trim() != ''){
		var inputTimeFld = document.getElementById(inputTimeField);
		if(inputTimeFld == null || inputTimeFld == undefined){
			err[err.length] = "Input Time field is undefined.";
		}
	}
	if(err.length > 0){
	 	var msg = "Calendar setup errors...\n";
       	for (i=0; i<err.length; i++) {
	       	msg += " - " + err[i] + "\n";
     	}
       	alert(msg);
       	return false;
	}else {
		return true;
	}
}
function changeCursor(button){
	if(restrictFields == 'true' || button.getAttribute("disable") == 'true'){
		button.style.cursor = 'default';
	}else{
		button.style.cursor = 'pointer';
	}
}
 /* Calendar code ends*/

/*
 * autoTab 
 * Tabs to the next field filtering the special keys and sets the focus.
 * This function accepts three parameters
 * @param 1:  object of current field.
 * @param 2:  window key event.  
 * @param 3: object of next field.
 * Created by Sarat Kasa on 06/23/2010
 * Modified by Ajay Kumar on 06/23/2010 to use JQuery inArray function

 */

function autoTab(obj,e, nextobj)
{  
	var len = obj.maxLength;   
	var isNN = (navigator.appName.indexOf("Netscape")!=-1);                     
	var keyCode = (isNN) ? e.which : e.keyCode;                       
	var filter = (isNN) ? [0,8,9] : [0,8,9,16,17,18,37,38,39,40,46];
	
	if (obj.value.length >= len && $.inArray(keyCode,filter)==-1)
	{ 
		//only advance to the next field it the cursor is on the end of the textbox
		if (determineCursorPosition(obj) ==  obj.maxLength){
		    obj.value = obj.value.slice(0, len);                      
		    $('#'+nextobj).focus();  
		    $('#'+nextobj).select();
		}
	}                
}

function toggleHandler(cnt){     
    var currentSrc = $("#img"+cnt).attr('src');
    if(currentSrc == colapse){
            $("#img"+cnt).attr('src',expand);
            $("#"+cnt).show();                         

    } else {
            $("#img"+cnt).attr('src',colapse);
            $("#"+cnt).hide();
    }
}

/***
* @author Ajay
* This method toggles HTML content with a specified className.
* @param showHideFlag (boolean value)
* @return
*/
function toggleByClassName(showHideFlag,className) {
	if (showHideFlag == true) {
	     $('.'+className).removeClass('hidden');
		} else if(showHideFlag == false) {
			 $('.'+className).addClass('hidden');
		}
}

/***
 * This method selects all the options from the select box.
 * Should be used for multiTransfer Box.
 * @param selectBoxId
 * @return
 */
function selectAllOptions(selectBoxId){ 
	 $('#'+selectBoxId+' option').each(function(i){ 
		 $(this).attr("selected", "selected"); 
	 }); 
}

/*
 * Clears all selected options from the select box with specified id.
 */
function clearSelectedOptions(selectBoxId) {
	$('#'+selectBoxId+' option').each(function(i){ 
		 $(this).attr("selected", ""); 
	 }); 
}

/***
 * This method disabled the enter key event when called.
 * Calling :: onkeypress="disableEnterKey()"
 * @param e
 * @return
 */ 
function disableEnterKey(e){
     var key;     
     if(window.event)
          key = window.event.keyCode; //IE
     else
          key = e.which; //firefox     
     return (key != 13);
} 

 /**
  * Selecting/Unselecting all checkBoxes 
  * 
  * @author Sarita Sampgaonkar
  * @param applyButtonId This parameter is required to identify the button check/uncheck of which, other checkboxes need to be changed
  * @param listRequired If this parameter is true, then a list of checkboxes value will be constructed and returned back to the calling function
  * @param checkBoxClassName - This paremter is required to allow check and uncheck of multiple group of checkboxes based on their classname
  * Note : If any checkbox need to be opted out of check/uncheck functionality, then 'notSelect' class should be used for that checkbox
  */
 function selectAllCheckBox(applyButtonId, listRequired, checkBoxClassName){
 			var checkVal = false;
 			var resultList;
 			if($('#'+applyButtonId).attr('checked')){
 				checkVal = true;
 			}
 			$("input[type='checkbox']").each(function(i){
 				if( (isWhitespace(checkBoxClassName) || $(this).attr('class') == checkBoxClassName) &&  $(this).attr('class') != 'notSelect') {
	 				$(this).attr('checked',checkVal);
	 				
	                if(listRequired == "true" && ($(this).attr('id')  != applyButtonId)){
		                val = $(this).val();
		                if(resultList == null){
		                	resultList = new Array();
		                }
		                if(checkVal){ 
		                      if($.inArray(val, resultList) == -1){
		                            resultList[resultList.length]=val;
		                      }
		                } else { 
		                      resultList.splice($.inArray(val, resultList), 1);
		                }
	                }
 				}
 			});
 			
 			if(listRequired == "true") {
 				return resultList;
 			}
 }
	

//Used for displaying a common alert when form validation errors were encountered.
function checkErrors(err){
	var isValidData = true;
	if (err.length > 0) 
	{
		var msg = "Please correct the following problems..." + "\n";
 	for (var i=0; i<err.length; i++) 
     {
 		msg += " - " + err[i] + "\n";
 	}
 	alert(msg);
 	isValidData = false;
	}
	return isValidData;
}

//Code to find the cursor position in textboxes
function determineCursorPosition(obj)
{
     //To find the cursor position
     var varObject=obj;
    var sel, rng, r2, cPosition=-1;
     if(typeof varObject.selectionStart=="number") 
    {
        cPosition=varObject.selectionStart;
    }
    else
    {
        sel=document.selection;
        if(sel)
        {
            r2=sel.createRange();
            rng=varObject.createTextRange();
            rng.setEndPoint("EndToStart", r2);
            cPosition=rng.text.length;
        }
    } 

    return cPosition;
}


/***
 * @author NKR000IS
 * @param {Object} Id the common part of the ID should be passsed. 
 * This method is used by display tag and it can also be used
 * by other pages which needs expand all and collapse all.
 */
function expandAll(tableId){
	$("img[id ^= img"+tableId+" ]").each(function(){
		$(this).attr('src',expand);
	});
	$( "tr[id ^= '"+tableId+"']" ).show();
}

function collapseAll(tableId){
	$("img[id ^= img"+tableId+" ]").each(function(){
		$(this).attr('src',colapse);
	});
	$( "tr[id ^= '"+tableId+"']" ).hide();
}

/** 
 * This function disable/enable all elements in given element.
 * Usage: To disable --> desableEnableElement('sectionDiv', 'div', true);
          To enable  --> desableEnableElement('sectionDiv', 'div', false);
 * The 'disable' parameter is optional.
   Usage: desableEnableElement('sectionDiv', 'div');
 * If you call this function with out 'disable' parameter it will check the element is already disabled or not.
 * If its already disabled then it will enable it, if its already enabled then it will disable it.
 * Required libraries : JQuery
 * @author: Rameshwar Gudimalla
 */
function disableEnableElement(idPrefix, elementType, disable) {
  $( elementType+"[id ^= '"+idPrefix+"']" ).each(function (i,element) {
	  if(disable == undefined){
	  	element.style.display = element.style.display == "block" ? "" : "block";
	  }else{
		element.style.display = disable ? "block" : "";
	  }
  	  disableElement(element, disable);
  });
}

function disableElement(el, disable) {
	try {
		if(disable == undefined){
			el.disabled = el.disabled ? false : true;
    	}else if(el.nodeName == 'A'){
    	  disableAnchor(el, disable);
		}else if(isCanlendarObject(el)){
			disableCalendar(el, disable);
    	}else{
    	  el.disabled = disable;
    	}
    }
    catch(E){}
    
    if (el.childNodes && el.childNodes.length > 0) {
        for (var x = 0; x < el.childNodes.length; x++) {
        	disableElement(el.childNodes[x], disable);
        }
    }
}
/**
* @param obj
* @return boolean
*/
function isCanlendarObject(obj) {
	return obj.nodeName == 'IMG' && obj.getAttribute("src") == contextPath+'/jsp/images/calendar.png';
}
/**
* @param obj
* @param disable
*/
function disableCalendar(obj, disable){
	var calDisable = obj.getAttribute("disable");
	if(disable){
		 if(calDisable == null){
			calDisable = document.createAttribute('disable');
	  		obj.setAttributeNode(calDisable);
	 	 }
		 obj.setAttribute("disable",'true');
	}else if(calDisable != null){
		 obj.removeAttribute("disable");
	}
}

/**
 * Call this function on onmousedown and onkeydown of radio box tag
 * onmousedown="setCurrentRadio(this)" 
 * onkeydown="setCurrentRadio(this)"
 * @author r9g000is
 */
function setCurrentRadio(obj){
	radioChecked = $(obj).attr('checked');
}
/**
* Call this function on click of radio box tag
* onclick="switchRadioSelection(this)"
* This function is dependent on setCurrentRadio(obj) function
* @author r9g000is 
*/
function switchRadioSelection(obj){
	obj.checked = !radioChecked; 
}
/**
 * This function redirects to given URL with out submitting the page.
 * WARNING: Be careful while changing this function, because this function is used in Back button functionality.
 * @author r9g000is 
 * @param url - Any URL with out context path
 * 				Ex: /action.do?method=view&.....
 * @return false
 */
function redirectURL(url){
	location.replace(contextPath+url);
	return false;
}
/**
 * This function is used to check or un-check 'Apply ALL' check box depends on check box list selection.
 * Ex: <code>onclick="switchApplyAllButton('chkBx', 'apply');"</code> 
 * @param checkBoxIdPrefix - prefix of check boxes id
 * @param applyButtonId - apply all check box id
 * @author r9g000is
 */
function switchApplyAllButton(checkBoxIdPrefix, applyButtonId){
	var applyAll = true;
	$("input[id ^='"+checkBoxIdPrefix+"']").each(function(i, element){
		if(element.checked == false){
			applyAll = false;
		}
	});
	if($("input[id ^='"+checkBoxIdPrefix+"']").length > 0){
		$('#'+applyButtonId).attr('checked',applyAll);
	}
}

/**
 * This function is used to expand text areas
 * @param obj
 * @return
 */
 function taExpand(obj) {
	 var i, r, c = 0;
	 var a = obj.value.split('\n');
	 for (i = 0; i < a.length; i++) {
		 if (a[i].length > obj.cols) {// find number of wrapped lines
			 c += Math.floor(a[i].length / obj.cols);
		 }
	 }
	 r = c + a.length; // add number of wrapped lines to number of lines
	 if (r < 5) {
		 r = 5;
	 }
	 obj.rows = r;
 }

 /**
  * This function is used to collapse text areas
  * @param obj
  * @return
  */
 function taCollapse(obj,rowSize) {
	 obj.rows = rowSize;
 }

  /*
  * Function calls spell checker application and spell checks a form element.
  * 
  * Depends: spellChecker.js file in Spell Check application. 
  *                Included in Common.jsp and menuCommon.jsp
  */
  var spellCheckConfigAlert = false;
  function spellCheck(formElementId) {      
        var textElement = document.getElementById(formElementId);      
        if(window.spellCheckConfig) {
     	   var speller = new spellChecker(textElement);
     	   speller.openChecker();  
        } else {
     	   if(!spellCheckConfigAlert) alert("Spell checker not configured !");
     	   spellCheckConfigAlert = true;
        }
  }
  
function finalFormList(reportNameKey){
	document.forms[0].action = contextPath+"/finalFormListAction.do?method=list&reportName="+reportNameKey;
	document.forms[0].submit();
}

/**
* This method replaces all the occurrences of one substring with another.
* 
* @author Anurag Sinha
* @param strTarget - The substring to be replaced.
* @param strSubString - The string to be replace by.
* @return String 
*/
String.prototype.replaceAll = function(strTarget, strSubString){
		var strText = this;
		var intIndexOfMatch = strText.indexOf(strTarget);		 
		while (intIndexOfMatch != -1) {
			strText = strText.replace(strTarget, strSubString);			 
			intIndexOfMatch = strText.indexOf(strTarget);
		}		
		return strText;
};

function isValidSSNFormat(ssnStr){
	var RE_SSN = /^[0-9]{3}[\- ]?[0-9]{2}[\- ]?[0-9]{4}$/;
	return RE_SSN.test(ssnStr);
}

function formatSSN(ssnStr){
	var newSSN = "";
	var ssnStr1 = ssnStr.substr(0, 3);
	var ssnStr2 = ssnStr.substr(3, 2);
	var ssnStr3 = ssnStr.substr(5, 4);
	newSSN = ssnStr1+"-"+ssnStr2+"-"+ssnStr3;
    return newSSN;
}

/**
 * This function does not allow users to use backspace.
 * It can be used on read only fields where backspace takes to previous page.
 * This function is controlled on the key event.
 */
function disableBackspace(e) {
	var key;
	if (window.event)
	  key = window.event.keyCode;
	else if (e)
	  key = e.which;
	else
	  return true;
	
	if(key == 8) {
	   return false;
	} else {
	   return true;
	}
}
 
 
/**
 * This method is used by the check-box tag to synch the hidden variable and check-box value
 * @param id element Id
 * @return
 * @author nkr000is
 */
function updateCheckBoxHiddenValue(id){
	if($('#'+id).attr('checked')){
		$('#'+"hidden"+id).val('true');
	}else{
		$('#'+"hidden"+id).val('flase');
	}
}

/**
 * This method performs ajax call to set all the check box values to true or false 
 * @param obj applyAll check box object
 * @param partialCheckboxId id of the 
 * 	check boxes (ex: selectCH01, selectCH02 are the id's of the check box then pass selectCH as the id.
 * @param actionClassName action class name (ex: associateCriminalHistoryAction.do).
 * @param methodName method name in the action class.
 * @return
 * @author nkr000is
 */
function applyAllClicked(obj,partialCheckboxId,actionClassName,methodName){
	var isChecked =obj.checked; 
	
	var applyAllParams = {
	        type:"POST",
	        url:contextPath+"/"+actionClassName+"?method="+methodName+"&applyAll="+isChecked,
	        async:false,
	        // Success callback function
	        success: function(){}
  	};
	ajaxCall(applyAllParams);
	
	if(isChecked){
		$("input[id ^= '"+partialCheckboxId+"']").each(function() {
			var rowId = $(this).attr("id").replace(partialCheckboxId,"");
			if(!$("#"+partialCheckboxId + rowId).attr("disabled")){
				$("#"+partialCheckboxId+ rowId).attr("checked","checked");
				$("#hidden"+partialCheckboxId+ rowId).val("true");
			}
		});
		
	}else{
		$("input[id ^= '"+partialCheckboxId+"']").attr("checked","");
		$("input[id ^= 'hidden"+partialCheckboxId+"']").val("false");
	}
}
/**
 * Changes the state of the applyAll check box
 * @param obj check box element
 * @param applyAllId apply all id
 * @return
 * @author nkr000is
 */
function changeApplyAll(obj,applyAllId){
	if(!obj.checked){
		$("#"+applyAllId).attr("checked",false);
	}
}

/**-------------------------------------------------------------------
* ASKS THE USER IF THEY ARE SURE THEY WANT TO SAVE THE FINAL FORM DATA
*----------------------------------------------------------------------*/
function finalFormConfirmation() {
   var msg = "About to save a Final Form version, are you sure?";
   msg += "\n\nPLEASE NOTE: The information on this page will be saved during this process. ";
   
   msg += "\n\nTo continue with Final Form click \"OK\". ";
   msg += "\nOtherwise click \"Cancel\" \n";
   
   return confirm(msg);
}
/**
 * This method will be called when the user clicks 'FinalForm' button on the detail page.
 * The method first shows the finalFormConfirmation and the validates the page as per the page validation rules
 * sets the finalFormParam and saves the page information.
 * 
 */
function finalForm(){
	 if(doAction())
	 {
		 if(finalFormConfirmation())
		{
			 $("input[id ^= 'finalformId']").attr('disabled','disabled');
 		    // Final form caller is another URI and should be encoded
			document.forms[0].action=document.forms[0].action+"&finalFormParam=true&finalFormCaller="+encodeURIComponent($('#finalFormCaller').val());
			document.forms[0].submit();
		}else{
			$('#saveButton').attr('disabled','');
		}
	}
}

 
/**
 * This function resizes all the drop-downs if styleClass="longDropDown".
 * 
 * @author Anurag Sinha
 * @author Ram Vangala
 */
function longDropDown() {
	 $('select').filter('.longDropDown').each(function(){
		if(this.offsetWidth >(document.body.clientWidth-$(this).position().left)) {
			this.style.width = (document.body.clientWidth-$(this).position().left)+"";
			}
		});
 }
 
 /**
  * This is a window resize function which is used to resize the long drop-downs.
  * 
  * @author Anurag Sinha
  * @author Ram Vangala
  */
 window.onresize =  function() {
	$('select').filter('.longDropDown').each(function(){
			var maxWidth = 0;
			var allowedWidth = document.body.clientWidth - $(this).position().left;
			var obj =  $(this).get(0);
		    for (var i = 0; i < obj.length; i++) {
		    	var widthInPixel = getWidth(obj.options[i].text); 
		        if (widthInPixel > maxWidth) {        	
		        	maxWidth = widthInPixel;        	
		        }        
		    }
		    maxWidth = (maxWidth <= 36 ? 36 : maxWidth+30);			    
		    obj.style.width = (maxWidth < allowedWidth) ? (maxWidth + "px") : (allowedWidth + "px");
		});
}

/**
 * This function gets the width in pixel.
 * 
 * @author Anurag Sinha
 * @param element - The element for width in pixel we need.
 */
function getWidth(element) {
	if (document.getElementById) {
	var len;
	var rulerSpan = document.getElementById('ruler');
	rulerSpan.innerHTML = element;
	len = rulerSpan.offsetWidth;
	rulerSpan.innerHTML = ""; //Reset the rulerSpan.innerHTML as we do not need it anymore.
	return len;
	}
}
/**
* This function prepares the Options for the drop-down.
* It adds the title for each value.
* 
* @author Anurag Sinha
* @author Ram Vangala
* @param data: option values that need to be set to the drop-down.
* @param id: Form element to which the options need to be set.
* @param adjustFlag: boolean value which decides if dropdown resize is needed. 
*/
function prepareOptions(data,id, adjustFlag) {
	var maxWidth = 0;
	var defaultOption = '<option value="">...</option>';
	var options = '';
	var dataContent = (data+"").trim();
	if(dataContent != "null" && !isWhitespace(dataContent)) {
		for (var i = 0; i < data.length; i++) {
			options += '<option value="' + data[i].key + '" title="'+data[i].val +'">' + data[i].val+ '</option>';
			if (adjustFlag) {
				var widthInPixel = getWidth(data[i].val);
				if (widthInPixel > maxWidth) {        	
		        	maxWidth = widthInPixel;        	
		        } 
			}
		}
	}else {
		data="";
	}
	if(data.length == 0 ) {
		$("select#"+id).html(defaultOption);
	} else {		
		$("select#"+id).html(options);
	}
	if(adjustFlag) {
		var obj = $("#"+id).get(0);
		var allowedWidth = document.body.clientWidth - $(obj).position().left;
		maxWidth = (maxWidth <= 36 ? 36 : (maxWidth + 20));	
		obj.style.width = ((maxWidth-4) < allowedWidth) ? (maxWidth + 4 + "px") : (allowedWidth + "px");
	}
}

 /**If the Functional user is not within the counties he is supposed to be and something has changed in
	* the form, check below. If he clicks OK, proceed to save and write in audit table of what fields have 
	* changed */
function checkIfUserAllowedSave(functionTypeCd,userLocCountyInd){
		 if ("F" == functionTypeCd && !userLocCountyInd) {
		   return confirm(recordSaveNotFromUsersLocMsg);
		 } 
		 return true;
}

/** If its a Functional User and his counties match the Location Xref County and his caseType is either
* Inmate/Parole/ConditionalRelease, he is not supposed to edit the Charge.Warn him and Audit this
*	 information in the Audit Table. */
function checkWithCaseType(functionTypeCd, userLocCountyInd, caseType){

		if("F" == functionTypeCd && userLocCountyInd && (caseType == "INM" || caseType == "PAR" || caseType == "CRL")){
			return confirm(recordSaveNotAssignedToUserLocMsg);
		}else {
			return checkIfUserAllowedSave(functionTypeCd,userLocCountyInd);
		}
		return false;
} 


/**
* This function checks if compareNoId (Ending Number) is greater than compareToNoId (Starting Number).
* Number which must be greater is passed to compareNoId and the other Number to compareToNoId
* compareNoName and compareToNoName are corresponding field names.
* clearFieldId field is used to clear the filed if Number is less then provided Number.
* A error message is alerted if the compareNoId (Ending Number) is smaller then  compareToNoId (Starting Number).
* @param  compareNoId,compareToNoId,clearFieldId,compareNoName,compareToNoName
* @return boolean
*/
function checkGreaterThanEqualNumber(compareNoId, compareToNoId, clearFieldId, compareNoName, compareToNoName) {	 
		// Check first if the given Numbers are valid.
		var clearFieldObj = document.getElementById(clearFieldId);
		var compareNoObj =  document.getElementById(compareNoId);
		var compareToNoObj =  document.getElementById(compareToNoId);
		
		if((!isWhitespace(compareNoObj.value) && !isWhitespace(compareToNoObj.value))  && (isInteger(compareNoObj.value) && isInteger(compareToNoObj.value))){   
	   		if(!isGreaterNumber(compareNoId, compareToNoId) && !isNumberEqual(compareNoId, compareToNoId)){   	
	   			var msg = compareNoName +" "+ commonDateGreaterEqualMsg +" "+ compareToNoName;
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
 * This function checks if compareNoId (Ending Number) is less than compareToNoId (Starting Number).
 * Number which must be less than is passed to compareNoId and the other Number to compareToNoId
 * compareNoName and compareToNoName are corresponding field names.
 * clearFieldId field is used to clear the filed if Number is greater than provided Number.
 * A error message is alerted if the compareNoId (Ending Number) is greater then  compareToNoId (Starting Number).
 * @param  compareNoId,compareToNoId,clearFieldId,compareNoName,compareToNoName
 * @return boolean
 */
 function checkLessThanEqualNumber(compareNoId, compareToNoId,clearFieldId,compareNoName,compareToNoName) {	 
		// Check first if the given dates are valid.
		var clearFieldObj = document.getElementById(clearFieldId);
		var compareNoObj =  document.getElementById(compareNoId);
		var compareToNoObj =  document.getElementById(compareToNoId);
		if((!isWhitespace(compareNoObj.value)  && !isWhitespace(compareToNoObj.value)) &&  (isInteger(compareNoObj.value) && isInteger(compareToNoObj.value))){   
	   		if(!isGreaterNumber(compareToNoId, compareNoId) && !isNumberEqual(compareToNoId,compareNoId)){   	
	   			var msg = compareNoName +" "+ commonDateLessThanEqualMsg +" "+ compareToNoName;
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
  * This function checks if one number is greater than the other
  * */
 function isGreaterNumber(compareNoId, compareToNoId){
	 var compareNo = parseInt(document.getElementById(compareNoId).value);
	 var compareToNo = parseInt(document.getElementById(compareToNoId).value);
	 
	 if(compareNo > compareToNo){
		 return true;
	 }
	 return false;
 }
 /**
  * This function checks if Numbers for the given Ids are equal.
  * */
 function isNumberEqual(compareNoId, compareToNoId){
	 var compareNo = parseInt(document.getElementById(compareNoId).value);
	 var compareToNo = parseInt(document.getElementById(compareToNoId).value);
	 if(compareNo == compareToNo){
		 return true;
	 }
	 return false;
 }

 /**
  * This function validates if amount for the given object is valid.
  * */
 function validateAmount(obj, label){
	var amountVal =obj.value;
	var regex = /^[0-9]\d*(?:\.\d{0,2})?$/; 
	
	if(!regex.test(amountVal)){
		alert("Enter a valid amount for "+label);
		obj.value="";
		setTimeout(function(){obj.focus();}, 10);
		return false;
	}
	return true;
}
 
 /**
  * This function checks if compareAmtId (Max Amount) is greater than compareToAmtId (Min Amount).
  * Amount which must be greater is passed to compareAmtId and the other Amount to compareToAmtId
  * compareAmtName and compareToAmtName are corresponding field names.
  * clearFieldId field is used to clear the fied if date is less then provided date.
  * A error message is alerted if the compareAmtId (Max Date) is smaller then  compareToAmtId (Start).
  * @param  compareAmtId,compareToAmtId,clearFieldId,compareAmtName,compareToAmtName
  * @return boolean
  */
  function checkGreaterEqualAmount(compareAmtId, compareToAmtId,clearFieldId,compareAmtName,compareToAmtName) {	 
		// Check first if the given currencies are valid.
		var clearFieldObj = document.getElementById(clearFieldId);
		var compareAmtObj =  document.getElementById(compareAmtId);
		var compareToAmtObj =  document.getElementById(compareToAmtId);
		
 		if(!isWhitespace(compareAmtObj.value) && validateAmount(compareAmtObj, compareAmtName) 
 				&& !isWhitespace(compareToAmtObj.value) && validateAmount(compareToAmtObj, compareToAmtName)){   
	   		if(!isGreaterAmount(compareAmtId, compareToAmtId) && !isAmountEqual(compareAmtId, compareToAmtId)){   	
	   			var msg = compareAmtName +" "+ commonDateGreaterEqualMsg +" "+ compareToAmtName;
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
   * This function checks if compareAmtId (Min Amount) is less than compareToAmtId (Max Amount).
   * Amount which must be less than is passed to compareAmtId and the other Amount to compareToAmtId
   * compareAmtName and compareToAmtName are corresponding field names.
   * clearFieldId field is used to clear the field if Amount is greater than provided date.
   * A error message is alerted if the compareAmtId (Min Amount) is greater then  compareToAmtId (Max Amount).
   * @param  compareAmtId,compareToAmtId,clearFieldId,compareAmtName,compareToAmtName
   * @return boolean
   */
   function checkLessThanEqualAmount(compareAmtId, compareToAmtId,clearFieldId,compareAmtName,compareToAmtName) {	 
 		// Check first if the given dates are valid.
 		var clearFieldObj = document.getElementById(clearFieldId);
 		var compareAmtObj =  document.getElementById(compareAmtId);
 		var compareToAmtObj =  document.getElementById(compareToAmtId);
  		if((!isWhitespace(compareAmtObj.value) && validateAmount(compareAmtObj, compareAmtName) 
  				&& !isWhitespace(compareToAmtObj.value)) && validateAmount(compareToAmtObj, compareToAmtName)){   
 	   		if(!isGreaterAmount(compareToAmtId, compareAmtId) && !isAmountEqual(compareToAmtId,compareAmtId)){   	
 	   			var msg = compareAmtName +" "+ commonDateLessThanEqualMsg +" "+ compareToAmtName;
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
 * This function checks if compareAmtId is greater than compareToAmtId
 * */
function isGreaterAmount(compareAmtId, compareToAmtId) {
	var compareAmtVal =  document.getElementById(compareAmtId).value;
	var compareToAmtVal =  document.getElementById(compareToAmtId).value;
	if(!isWhitespace(compareAmtVal) && !isWhitespace(compareToAmtVal)){
	   var compareAmtArray = compareAmtVal.split(".");
	   var compareToAmtArray = compareToAmtVal.split(".");
	   if(parseInt(compareToAmtArray[0]) > parseInt(compareAmtArray[0]) || 
		   (parseInt(compareToAmtArray[0]) == parseInt(compareAmtArray[0]) && parseInt(compareToAmtArray[1]) > parseInt(compareAmtArray[1]))){
		   return false;
	   }else {
		   return true;
	   }
   }
   return false;
}
/**
 * This function checks if two Amounts are equal
 */  
function isAmountEqual(compareAmtId, compareToAmtId) {
	var compareAmtVal =  parseInt(document.getElementById(compareAmtId).value);
	var compareToAmtVal =  parseInt(document.getElementById(compareToAmtId).value);
	if(!isWhitespace(compareAmtVal) && !isWhitespace(compareToAmtVal)){
	   if(parseInt(compareAmtVal) == parseInt(compareToAmtVal)){
		   return true;
	   }
	}
	return false;
}

/**
 * This function changes a given number into Amount format. 
 * Adds a decimal/zero(s) if it doesn't exist 
 */
function switchNumberToAmount(obj){
	var amount = obj.value;
	if(!isWhitespace(amount)){
		var amountArray = amount.split(".");
		if(amountArray.length == 1){
			obj.value = amount + ".00";
		}else if(amountArray.length == 2){
			if(amountArray[1].length == 0){
				obj.value = amount + "00";
			}else if(amountArray[1].length == 1){
				obj.value = amount + "0";
			}
		}
	}
}
 
 /**
  * This function checks a given object value. 
  * If value is alphanumeric it clears value and show alert.
  */
 function validateNumericField(obj){
		var qtyVal = obj.value;
		 var strChar;
		 for (var i = 0; i < qtyVal.length; i++) {
			 strChar = qtyVal.charAt(i);
			 if (("0123456789").indexOf(strChar) == -1) {
				 obj.value = "";	
				 setTimeout(function(){obj.focus()}, 10);
				 alert(numericValueRequiredMsg);
				 break;
	        }
	     }
}

 function addCommas(nStr)
 {
     nStr += '';
     x = nStr.split('.');
     x1 = x[0];
     x2 = x.length > 1 ? '.' + x[1] : '';
     var rgx = /(\d+)(\d{3})/;
     while (rgx.test(x1)) {
         x1 = x1.replace(rgx, '$1' + ',' + '$2');
     }
     return x1 + x2;
 }
 
 function add2Decimals(inStr){
	 if (inStr == '')
		 return '';
	 
	 var amount = parseFloat(inStr);
	 return amount.toFixed(2);
 }
 /*****************************************************************************
  *
  * This method is just like decimalCheck only it removes commas then evaluate whether the number entered is 
  * in the desired format #####.### or ##### or .###
  * input params first removes commas
  * fieldObject is this object
  * maxIntLen is the max number of characters allowed for integer part of the decimal number
  * maxDecLen is the max number of characters allowed for fractional part of the decimal number 
  ****************************************************************************/ 

  function decimalCheckCommas(fieldObject, maxIntLen, maxDecLen) {
	//If the number is negative, check to make sure '-' is only at the beginning
	var fieldValue = fieldObject.value;
	fieldValue = fieldValue.replace(/,/g, '');
	var negIndex = fieldValue.indexOf("-");
	if (negIndex > 0) {
		return false;
	} else if (negIndex == 0) {
		if (fieldValue.length == 1) {
			return false;
		}
		fieldValue = fieldValue.split("-")[1];
	}

	if (fieldValue.indexOf(".") < 0) {
		if (fieldValue.length > maxIntLen) {
			return false;
		} else {
			return true;
		}
	} else {
		var val = new Array();
		val = fieldValue.split(".");
		if (val[0].length > maxIntLen || val[1].length > maxDecLen
				|| (val[0].length == 0 && val[1].length == 0)) {
			return false;
		}
		return true;
	}
} 
