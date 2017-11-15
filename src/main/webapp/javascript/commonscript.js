
function allowNumbersOnly(myfld,evnt)
{
	
	var keycode;
	if(window.event)
		keycode = window.event.keyCode;
	else if(evnt)
		keycode = evnt.which;
	else 
		return true;
	
	if(keycode > 47 && keycode < 58)	
		return true;
	else
		return false;
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
  function isValidSSNFormat(ssnStr){
		var RE_SSN = /^[0-9]{3}[\- ]?[0-9]{2}[\- ]?[0-9]{4}$/;
		return RE_SSN.test(ssnStr);
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
