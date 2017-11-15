// To check the value given in textfield is already existed in list box values.
function checkForUnique(txtfld,listfld)
{
	for(var i = 0; i < listfld.length; i++)
	{
		if(fnTrim(txtfld.value)==fnTrim(listfld.options[i].value))
		{
			return true;
		}
	}
	return false;
}
//To restrict the editing of textfield.
function notAllowEdit(listBox)
{
	if(listBox.value != "create")
	{
		alert("You Cannot edit this field");
		listBox.focus();		
	}
	return false;
}
//For character validation
function restrictSpecialChars(myfld,event)
{
	var keycode;
	if(window.event)
		keycode = window.event.keyCode;
	else if(evnt)
		keycode = evnt.which;
	else 
		return true;

	if((keycode > 47 && keycode < 58)||(keycode > 64 && keycode < 91)||(keycode > 96 && keycode < 123))	
		return true;
	else
		return false;

}

//for textarea validation

function fnTextareaValidation(txtarea,len)
{
  if(fnTrim(txtarea.value) != "" && fnTrim(txtarea.value).length > parseInt(len))
  {
    alert("Text should not be more than "+len+" characters");
    txtarea.focus();
    return false;
  }
  return true;;
}
//-----------------------------------------------------------------------------------
// For price and selected currency relation
function priceCurrency(prc,curr)
{
	curr = fnTrim(curr);
	if(curr == "select")
		return false;
	else if(curr != "BD" && prc.indexOf('.') != -1 && ((prc.substring(prc.indexOf('.')+1)).length > 2))
		return false;
	else
		return true;
}
//----------------------------------------------------------------------------------------
//For price validation in text fields
function priceValidation(myfld,evnt,len,currList)
{
	var prec = 3;
	if(currList.value == "select")
	{
		if(currList.name == "uom")
			alert("Please select Unit Of Measurement.");
		else
			alert("Please select currency.");
		currList.focus();
		return false;
	}
	else if(fnTrim(currList.value) != "BD")
		prec = 2;
	var ln = len-3;
	var keycode;
	if(window.event)
		keycode = window.event.keyCode;
	else if(evnt)
		keycode = evnt.which;
	else 
		return true;

	if(keycode == 46 || (keycode > 47 && keycode < 58))
	{
		if(keycode == 46 && myfld.value.indexOf('.')!=-1)
			return false;
		if(keycode == 46 && myfld.value.length == ln)
			return true;
		if(myfld.value.indexOf('.')!=-1)
		{
			if((myfld.value.length-myfld.value.indexOf('.')) <= prec )
				return true;
			else
				return false;
		}
		if(myfld.value.length < ln)
			return true;
		else
			return false;
	}
	else
	{
		return false;
	}
}
//For price validation in text fields
function priceValidation1(myfld,evnt,len,prec)
{
	var ln = len-3;
	var keycode;
	if(window.event)
		keycode = window.event.keyCode;
	else if(evnt)
		keycode = evnt.which;
	else 
		return true;

	if(keycode == 46 || (keycode > 47 && keycode < 58))
	{
		if(keycode == 46 && myfld.value.indexOf('.')!=-1)
			return false;
		if(keycode == 46 && myfld.value.length == ln)
			return true;
		if(myfld.value.indexOf('.')!=-1)
		{
			if((myfld.value.length-myfld.value.indexOf('.')) <= prec )
				return true;
			else
				return false;
		}
		if(myfld.value.length < ln)
			return true;
		else
			return false;
	}
	else
	{
		return false;
	}
}

//For number validation in text fields
function numberValidation(myfld,evnt)
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
function decimalValidation(myfld,evnt)
{
	var keycode;
	if(window.event)
		keycode = window.event.keyCode;
	else if(evnt)
		keycode = evnt.which;
	else 
		return true;

	if(keycode == 46 || (keycode > 47 && keycode < 58))
		return true;
	else
		return false;
}
// Function for checking valid Head Line desp entry
// Function returns false if valid else true

function fnCheckHeadLine(str){
  var str = fnTrim(str);
  if(str.indexOf('\"') >= 0){
    return true;
  }else{
    return false;
  }
}

function fnCheckCM(str){
  var str = fnTrim(str);
  if(str.indexOf('\"') >= 0 || str.indexOf('\'') >= 0){
    return true;
  }else{
    return false;
  }

}


// function for internal trimming

function internalTrim(ele)
{
  // change this to your text field
  var te = ele.value;
  var t = te.split(' ');
  var ne = new Array(t.length);
  j=0;
  for(var i=0;i<t.length;i++)
  {
 	 if(t[i] !=' ')
 	   {
 	    ne[j] = t[i];
 	    j++;
 	    }
  }

  var newstr="";
  for(var i=0;i<ne.length;i++)
  {
 	 if(ne[i] !='')
 	   {
 	      newstr = newstr + ne[i] +' ';
 	    }
  }
   // change this to your text field
  ele.value = newstr;
  //return document.forms[0].txt.value;
}



//This function returns the date format to the given formate
//Input String in the format DD-MM-YYYY

function getDateFormat(str,szDateFormat){
  var a=str.split("-");
  if(a[0].length==1)
    a[0]="0"+a[0];
  if(a[1].length==1)
    a[1]="0"+a[1];
  var month = 0;
  if(a[1] == "08" || a[1] == "8")
    month = 8;
  else if(a[1] == "09" || a[1] == "9")
    month = 9;
  else
    month = parseInt(a[1]);
  var mon1=";JAN;FEB;MAR;APR;MAY;JUN;JUL;AUG;SEP;OCT;NOV;DEC";
  var mon = mon1.split(";");
  if(szDateFormat == "DD/MM/YY")
    return a[0]+"/"+a[1]+"/"+a[2].substring(a[2].length-2,a[2].length);
  if(szDateFormat == "MM/DD/YY")
    return a[1]+"/"+a[0]+"/"+a[2].substring(a[2].length-2,a[2].length);
  if(szDateFormat == "DD-MM-YY")
    return a[0]+"-"+a[1]+"-"+a[2].substring(a[2].length-2,a[2].length);
  if(szDateFormat == "MM-DD-YY")
    return a[1]+"-"+a[0]+"-"+a[2].substring(a[2].length-2,a[2].length);
  if(szDateFormat == "DD-MM-YYYY" )
    return a[0]+"-"+a[1]+"-"+a[2];
  if(szDateFormat == "MM-DD-YYYY")
    return a[1]+"-"+a[0]+"-"+a[2];
  if(szDateFormat == "DD-MON-YYYY")
    return a[0]+"-"+mon[month]+"-"+a[2];
  if(szDateFormat == "DD-MON-YY")
    return a[0]+"-"+mon[month]+"-"+a[2].substring(a[2].length-2,a[2].length);
  return str;
}


// This function trims and returns the given value
function fnTrim(sFieldValue)
{
		var nLeft;
		var nRight;
		var nLength;

		if (sFieldValue.length==0)
			{
				return sFieldValue;
			}
		else
			{
				nLeft=0;
				nLength=sFieldValue.length;
				while ( (sFieldValue.charAt(nLeft)==" ") && (nLeft < nLength))
				{
					nLeft=nLeft+1;
				}
                                if (nLeft !=nLength)
				{
					nRight=nLength-1;
					while ((sFieldValue.charAt(nRight) == ' ') && (nRight > 0 ))
					{

						nRight=nRight-1;
					}
                                        var nL = 0;
                                        while ((sFieldValue.charAt(nL)==' ') && (nL <= nLength ))
					{
						nL=nL+1;
					}

                                        //alert("nRight="+nRight);
					if (nRight >= nLeft)
					{
						nLength=nRight-nLeft+1;
                                            //alert("IN THE IF nleft="+nL+"   nLength="+nLength+"  nRight="+nRight+" sFieldValue="+sFieldValue+"'");
						//sFieldValue=sFieldValue.substr(nLeft,nLength);
                                                sFieldValue=sFieldValue.substr(nL,nLength);
                                                //sFieldValue=sFieldValue.substr(nRight,nLength);

					}
				}
				else
				{
				sFieldValue="";
				}
				return sFieldValue;
			}
} // end of fnTrim



//-------------------------------------------------------------------------------


// cheking for Null and returns true if the value is null
//This function internally uses fnTrim function


function fnCheckNull(sFieldValue)
{
	var bValidFlag;
	sFieldValue=fnTrim(sFieldValue);
    if (sFieldValue=="")
    {
		bValidFlag=true;
	}
    else if (sFieldValue==null)
    {
		bValidFlag=true;
	}
    else
    {
	bValidFlag=false;
     }
    return bValidFlag;

}  // end of fnCheckNull


//--------------------------------------------------------------------------------

//This function checks for the number and returns true if it is not a number
//This function internally uses fnTrim function

function fnCheckNumber(sFieldValue)
{
	var nLoop;
	var bValidFlag=false;
	sFieldValue=fnTrim(sFieldValue);
    if(sFieldValue.length != 0)
    {
		for(nFunLoop=0;nFunLoop<sFieldValue.length;nFunLoop++)
		{
          if ((sFieldValue.charAt(nFunLoop) < "0") || (sFieldValue.charAt(nFunLoop) > "9"))
          {
			bValidFlag=true;
          }
		}
	}
    else
    {
		bValidFlag=true;
	}

    return bValidFlag;

} // end of fnCheckNumber


//--------------------------------------------------------------------------------

//This function checks for the number which excludes Zero and returns true if it is not a number
//This function internally uses fnTrim function

function fnCheckNumberFP(sFieldValue)
{
	var nLoop;
	var bValidFlag=false;
	sFieldValue=fnTrim(sFieldValue);
    if(sFieldValue.length != 0)
    {
		for(nFunLoop=0;nFunLoop<sFieldValue.length;nFunLoop++)
		{
          if ((sFieldValue.charAt(nFunLoop) < "1") || (sFieldValue.charAt(nFunLoop) > "9"))
          {
			bValidFlag=true;
          }
		}
	}
    else
    {
		bValidFlag=true;
	}

    return bValidFlag;

} // end of fnCheckNumberFP which excludes 0

//------------------------------------------

// cheking to fit the given value in a specifed Range and returns true if the value is not in the range
//How to call ex fnCheckRange(55,"0,500")
//This function internally uses fnCheckNumber function

function fnCheckRange(sFieldValue,sRange)
{
	var nLowerValue;
    var nUpperValue;
    var nDividePosition;
    var bValidFlag=false;
    var bNumberFlag=false;

	bNumberFlag=fnCheckNumber(sFieldValue);

    if (bNumberFlag)
    {
		bValidFlag=true;
	}
    else
    {
		nDividePosition=sRange.indexOf(",");
        nLowerValue=parseInt(sRange.substr(0,nDividePosition));
        nUpperValue=parseInt(sRange.substr(nDividePosition+1,sRange.length-1));

        if ((parseInt(sFieldValue) < nLowerValue) || (parseInt(sFieldValue) > nUpperValue))
        {
			bValidFlag=true;
		}
    }
    return bValidFlag;

} // end of fnCheckRange


//--------------------------------------------------------------------------------


//This function checks for given value to be valid string and returns true if the value is not a valid string
//Valid string Sould strat with an alphabet(either case) followed by alphabet(either case) and/or any numeric value and/or - and/or _
//This function internally uses fnTrim function


function fnCheckString(sFieldValue)
{
  bValidFlag=true;
  sFieldValue=fnTrim(sFieldValue);
  if ((sFieldValue.charAt(0) >= "A" && sFieldValue.charAt(0)<= "Z" ) || (sFieldValue.charAt(0) >= "a" && sFieldValue.charAt(0) <= "z"))
  	for(i=0;i<sFieldValue.length;i++)
	{
		if (!((sFieldValue.charAt(i)>="A" && sFieldValue.charAt(i)<="Z") || (sFieldValue.charAt(i) >= "a" && sFieldValue.charAt(i)<= "z") ||(sFieldValue.charAt(i)>="0" && sFieldValue.charAt(i)<="9") || (sFieldValue.charAt(i) == "-") || (sFieldValue.charAt(i) == " ") || (sFieldValue.charAt(i) == "_") ))
		{
			bValidFlag=true;
			break;
		}
		else
			bValidFlag=false;
	}
  return bValidFlag;
}// end of fnCheckString

//------------------------------------------------------------------------------------------------------------------------------------

//This function checks for single quote  and replaces with two single quotes
//This is for both text box and text area
//To use the above function call it as:
//                       fieldvalue = txtval(fieldvalue,"'","'");
function txtval(txtarea,char,esc)
{
	var textvalue = "";
	var len=txtarea.length;
	var tmp = '';
	for(var i=0;i<len;++i){
		tmp=txtarea.charAt(i);
		if(tmp==char){
			textvalue += (esc + tmp);
		}else{
			textvalue += tmp;
		}
	}
	return textvalue;
}


//--------------------------------------------------------------------------------


//This function checks for given value to be valid string and returns true if the value is not a valid string
//Valid string Should strat with an alphabet(either case) followed by alphabet(either case) and/or any numeric value and/or - and/or _
//This function internally uses fnTrim function
//This function explicitly written to be used in fnAddListItem1() for option maintanance screen
// and allows open and close braces.


function fnCheckString2(sFieldValue)
{
  bValidFlag=true;
  sFieldValue=fnTrim(sFieldValue);
  if ((sFieldValue.charAt(0) >= "A" && sFieldValue.charAt(0)<= "Z" ) || (sFieldValue.charAt(0) >= "a" && sFieldValue.charAt(0) <= "z"))
  	for(i=0;i<sFieldValue.length;i++)
	{
		if (!((sFieldValue.charAt(i)>="A" && sFieldValue.charAt(i)<="Z") || (sFieldValue.charAt(i) >= "a" && sFieldValue.charAt(i)<= "z") ||(sFieldValue.charAt(i)>="0" && sFieldValue.charAt(i)<="9") || (sFieldValue.charAt(i) == "-") || (sFieldValue.charAt(i) == " ") || (sFieldValue.charAt(i) == "(") || (sFieldValue.charAt(i) == ")") || (sFieldValue.charAt(i) == "_") ))
		{
			bValidFlag=true;
			break;
		}
		else
			bValidFlag=false;
	}
  return bValidFlag;
}// end of fnCheckString2

//------------------------------------------------------------------------------------------------------------------------------------

//This function checks for given value to be valid string and returns true if the value is not a valid string
//Valid string Sould strat with an alphabet(either case) followed by alphabet(either case) and/or any numeric value and/or any Special character
//Valid String does not allow Single quotes and Double Quotes
//This function internally uses fnTrim function

function fnCheckStr(sFieldValue)
{
  var sq,dq
  bValidFlag=true;
  sFieldValue=fnTrim(sFieldValue);
  if ((sFieldValue.charAt(0) >= "A" && sFieldValue.charAt(0)<= "Z" ) || (sFieldValue.charAt(0) >= "a" && sFieldValue.charAt(0) <= "z"))
  {
  	sq=sFieldValue.indexOf("'")
  	dq=sFieldValue.indexOf("\"")
	if(sq == -1 && dq == -1)
		bValidFlag=false;
	}
	return bValidFlag;
} // end of fnCheckStr

//--------------------------------------------------------------------------------

//This function checks for valid Date and returns true if date is not a valid date.
//Date should be passed to this function in 01 feb 2000 format only.
//This function internally uses fnTrim function
function fnCheckDate(sFieldValue)
{
	var dGivenDate;
    var dGenDate;
    var bValidFlag=false;

	sFieldValue=fnTrim(sFieldValue);
    dGivenDate=new Date(sFieldValue);
    dGenDate=new Date(sFieldValue);
    dGivenDate=sFieldValue;
	if ((parseInt(dGivenDate.substr(0,2)))!=dGenDate.getDate())
	{
		bValidFlag=true;
	}

	return bValidFlag;
} // end of fnCheckDate
//--------------------------------------------------------------------------------
// when pass month in number it returns month name in 3 letter form.

  function getMonthName(month)
  {
    var sMon = "";
    if(month == "01" || month == "1")
      sMon = "Jan";
    else if(month == "02" || month == "2")
      sMon = "Feb";
    else if(month == "03" || month == "3")
      sMon = "Mar";
    else if(month == "04" || month == "4")
      sMon = "Apr";
    else if(month == "05" || month == "5")
      sMon = "May";
    else if(month == "06" || month == "6")
      sMon = "Jun";
    else if(month == "07" || month == "7")
      sMon = "Jul";
    else if(month == "08" || month == "8")
      sMon = "Aug";
    else if(month == "09" || month == "9")
      sMon = "Sep";
    else if(month == "10")
      sMon = "Oct";
    else if(month == "11")
      sMon = "Nov";
    else if(month == "12")
      sMon = "Dec";
    return sMon;
  }

//--------------------------------------------------------------------------------
//given date should not be greater than future date

function fnCompare2CurrentDate(sFieldValue)
{
	var dt1=new Date(sFieldValue);
	var dt2=new Date();
	if (dt1 < dt2)
		return -1;
	else if(dt2 == dt1)
		return 0;
	else
		return 1;
	
} // end of fnCompare2CurrentDate

//--------------------------------------------------------------------------------

//This function compares given 2 Dates and returns
//0 - if both the dates are equeal
//1 - if first date is less than second date
//2 - if second date is less than first date
//3 - if first date is invalid
//4 - if second date is invalid
//Dates should be passed to this function in 01 feb 2000 format only.

function fnCompare2Dates(sFieldValue1,sFieldValue2)
{
/*	if (fnCheckDate(sFieldValue1))
		return 3;
	if (fnCheckDate(sFieldValue2))
		return 4;
*/
	var nReturnvalue="";
	var dt1=new Date(sFieldValue1);
	var dt2=new Date(sFieldValue2);
	if (dt1+"" == dt2+"")
		nReturnvalue=0;
	else if (dt1<dt2)
		nReturnvalue=1;
	else
		nReturnvalue=2;

	return nReturnvalue;
} // end of fnCompare2Dates

//--------------------------------------------------------------------------------

//This function checks for valid Telephone number and returns true if the value is not a valid telephone number
//This function internally uses fnTrim function

function fnCheckTeleFax(sFieldValue)
{
		var bValidFlag=false;
		var nLoop;
		sFieldValue=fnTrim(sFieldValue);
		if (sFieldValue.length==0 || sFieldValue.charAt(0)=="-")
			{
				return true;
			}
		else
			{
				nLength=sFieldValue.length;
				for(nLoop=0;nLoop<nLength;nLoop++)
					{
						cChar=sFieldValue.charAt(nLoop);
						if ( ((cChar>="0") && (cChar <="9")) || (cChar==" ") || (cChar==".") || (cChar=="(") || (cChar==")") || (cChar=="-") || (cChar=="+") )
							{
								cchar=0;
							}
						else
							{
								bValidFlag=true;
							}
					}
				return bValidFlag;
			}
} // end of fnCheckTeleFax

//--------------------------------------------------------------------------------

// This function checks for a valid  eMail id and returns true if the value is not a valid mail id
//This function internally uses fnTrim function
function fnCheckMail(checkThisEmail)
{
    var myEMailIsValid = false;
    var myAtSymbolAt = checkThisEmail.indexOf('@');
    var myDotAt = checkThisEmail.indexOf('.');
    var myLastDotAt = checkThisEmail.lastIndexOf('.');
    var mySpaceAt = checkThisEmail.indexOf(' ');
    var myLength = checkThisEmail.length;
    //Check for the special characters entry Check for consequtive dots Check for Double at the rates
    for(var i=0;i<checkThisEmail.length;i++){
          //Check for the special characters entry

          if(!((checkThisEmail.charAt(i)>="A" && checkThisEmail.charAt(i)<="Z") || (checkThisEmail.charAt(i) >= "a" && checkThisEmail.charAt(i)<= "z") ||(checkThisEmail.charAt(i)>="0" && checkThisEmail.charAt(i)<="9") || (checkThisEmail.charAt(i) == "_") || (checkThisEmail.charAt(i) == "@") || (checkThisEmail.charAt(i) == ".") || (checkThisEmail.charAt(i) == "/") || (checkThisEmail.charAt(i) == "-"))){
                  myEMailIsValid = true;
                  break;
          }

          //Check for consequtive dots
          if( (checkThisEmail.charAt(i) == "." & checkThisEmail.charAt(i+1) =="." )){
                  myEMailIsValid = true;
                  break;
          }
          //Check for Double at the rates
          if(checkThisEmail.charAt(i)=="@"){
                  if(myAtSymbolAt != i){
                          myEMailIsValid = true;
                          break;
                  }
          }
          if(checkThisEmail.charAt(i)=="."){
                  if(myAtSymbolAt == (i+1) || myDotAt == 0 || myAtSymbolAt == (i-1)){
                          myEMailIsValid = true;
                          break;
                  }
          }
    }
    //Check for characters before and after '/' and '_' Check for index of  '/' , '_' and '-' fall after '@'

    for(var i=0;i<checkThisEmail.length;i++){
          if( ( checkThisEmail.charAt(i)=="/") || ( checkThisEmail.charAt(i)=="_") || ( checkThisEmail.charAt(i)=="-")){

              if( !(((checkThisEmail.charAt(i+1)>="A" && checkThisEmail.charAt(i+1)<="Z") || (checkThisEmail.charAt(i+1) >= "a" && checkThisEmail.charAt(i+1)<= "z") || (checkThisEmail.charAt(i+1)>="0" && checkThisEmail.charAt(i+1)<="9"))  &&  ( (checkThisEmail.charAt(i-1)>="A" && checkThisEmail.charAt(i-1)<="Z") || (checkThisEmail.charAt(i-1) >= "a" && checkThisEmail.charAt(i-1)<= "z") || (checkThisEmail.charAt(i+1)>="0" && checkThisEmail.charAt(i+1)<="9") )) )
              {
                myEMailIsValid = true;
                break;
              }
          }
    }
    // at least one @ must be present and not before position 2  @yellow.com : NOT valid  x@yellow.com : VALID
    if (myAtSymbolAt < 1 )
    {myEMailIsValid = true}
    
    // at least one . (dot) afer the @ is required  x@yellow : NOT valid  x.y@yellow : NOT valid  x@yellow.org : VALID 
     if ((myDotAt == (myAtSymbolAt+1)) || (myLastDotAt < myAtSymbolAt))
    {myEMailIsValid = true}

    // at least two characters [com, uk, fr, ...] must occur after the last . (dot)
    // x.y@yellow. : NOT valid
    // x.y@yellow.a : NOT valid
    // x.y@yellow.ca : VALID

    if (myLength - myLastDotAt <= 2)
    {myEMailIsValid = true}


    // no empty space " " is permitted (one may trim the email)
    // x.y@yell ow.com : NOT valid

    if (mySpaceAt != -1)
    {myEMailIsValid = true}

    return myEMailIsValid
} // end of fnCheckMail

//--------------------------------------------------------------------------------

//This function checks for the maximum limit in text area
function fnChecktextareaLength(sFieldValue,nMaxlimit)
{
	if (fnTrim(sFieldValue).length >parseInt(nMaxlimit))
		return true;

	return false;
}

//--------------------------------------------------------------------------------

//This function checks for password and Confirm password and returns true if they were not correct
function fnCheckPassword(sFieldValue1,sFieldValue2)
{
	if (sFieldValue1!=sFieldValue2)
		return true;

	return false;
}

//--------------------------------------------------------------------------------


//This function checks for password and Confirm password and returns true if they were not correct
function fnCheckPasswordLength(sFieldValue1)
{
	if (fnTrim(sFieldValue1.value).length<6){
		return true;
	}
	return false;
}

//--------------------------------------------------------------------------------


//This function moves an list box item to one position up
function fnMoveup(frm,lstSelect)
{

	var sindx=lstSelect.selectedIndex;
	if (sindx== -1)
	{
		alert("Please select a value");
		lstSelect.focus();
		return false;
	}
	if (sindx== 0)
		return false;

	var opt=new Option(lstSelect.options[sindx].text,true,true);
	var optval=lstSelect.options[sindx].value;
	var opt1=new Option(lstSelect.options[sindx-1].text,true,true);
	var optval1=lstSelect.options[sindx-1].value;
	lstSelect.options[sindx-1]=opt;
	lstSelect.options[sindx-1].value=optval;
	lstSelect.options[sindx]=opt1;
	lstSelect.options[sindx].value=optval1;
	lstSelect.options[sindx-1].selected=true;
	return true;
}

//--------------------------------------------------------------------------------



//This function moves an list box item to one position Down
function fnMovedown(frm,lstSelect)
{
	var sindx=lstSelect.selectedIndex;
	if (sindx== -1)
	{
		alert("Please select a value");
		lstSelect.focus();
		return false;
	}
	if (sindx==lstSelect.length-1)
		return false;

	var opt=new Option(lstSelect.options[sindx].text,true,true);
	var optval=lstSelect.options[sindx].value;
	var opt1=new Option(lstSelect.options[sindx+1].text,true,true);
	var optval1=lstSelect.options[sindx+1].value;
	lstSelect.options[sindx+1]=opt;
	lstSelect.options[sindx+1].value=optval;
	lstSelect.options[sindx]=opt1;
	lstSelect.options[sindx].value=optval1;
	lstSelect.options[sindx+1].selected=true;
	return true;
}

//--------------------------------------------------------------------------------

function fnCheckCardNumber(form)
{
	var Cards = new makeArray(8);
	Cards[0] = new CardType("MasterCard", "51,52,53,54,55", "16");
	var MasterCard = Cards[0];
	Cards[1] = new CardType("VisaCard", "4", "13,16");
	var VisaCard = Cards[1];
	Cards[2] = new CardType("AmExCard", "34,37", "15");
	var AmExCard = Cards[2];
	Cards[3] = new CardType("DinersClubCard", "30,36,38", "14");
	var DinersClubCard = Cards[3];
	Cards[4] = new CardType("DiscoverCard", "6011", "16");
	var DiscoverCard = Cards[4];
	Cards[5] = new CardType("enRouteCard", "2014,2149", "15");
	var enRouteCard = Cards[5];
	Cards[6] = new CardType("JCBCard", "3088,3096,3112,3158,3337,3528", "16");
	var JCBCard = Cards[6];
	var LuhnCheckSum = Cards[7] = new CardType();

	var tmpyear;
	if (form.CardNumber.value.length == 0)
	{
		alert("Please enter a Card Number.");
		form.CardNumber.focus();
		return false;
	}
	if (form.ExpYear.value.length == 0)
	{
		alert("Please enter the Expiration Year.");
		form.ExpYear.focus();
		return false;
	}
	if (form.ExpYear.value > 96)
		tmpyear = "19" + form.ExpYear.value;
	else
		if (form.ExpYear.value < 21)
			tmpyear = "20" + form.ExpYear.value;
		else
		{
			alert("The Expiration Year is not valid.");
			return false;
		}
	tmpmonth = form.ExpMon.options[form.ExpMon.selectedIndex].value;
	// The following line doesn't work in IE3, you need to change it
	// to something like "(new CardType())...".
	// if (!CardType().isExpiryDate(tmpyear, tmpmonth)) {
	if (!(new CardType()).isExpiryDate(tmpyear, tmpmonth))
	{
		alert("This card has already expired.");
		return false;
	}
	card = form.CardType.options[form.CardType.selectedIndex].value;
	var retval = eval(card + ".checkCardNumber(\"" + form.CardNumber.value + "\", " + tmpyear + ", " + tmpmonth + ");");
	cardname = "";
	if (retval)
	{
		alert("This card number appears to be valid.");
		return true;
	}
	else
	{
		// The cardnumber has the valid luhn checksum, but we want to know which
		// cardtype it belongs to.
		for (var n = 0; n < Cards.size; n++)
		{
			if (Cards[n].checkCardNumber(form.CardNumber.value, tmpyear, tmpmonth))
			{
				cardname = Cards[n].getCardType();
				break;
			}
		}
		if (cardname.length > 0)
		{
			alert("This looks like a " + cardname + " number, not a " + card + " number.");
			return false;
		}
		else
		{
			alert("This card number is not valid.");
			return false;
		}
	}
}


/*************************************************************************\
Object CardType([String cardtype, String rules, String len, int year,int month])
cardtype    : type of card, eg: MasterCard, Visa, etc.
rules       : rules of the cardnumber, eg: "4", "6011", "34,37".
len         : valid length of cardnumber, eg: "16,19", "13,16".
year        : year of expiry date.
month       : month of expiry date.
eg:
var VisaCard = new CardType("Visa", "4", "16");
var AmExCard = new CardType("AmEx", "34,37", "15");
\*************************************************************************/


function CardType()
{
	var n;
	var argv = CardType.arguments;
	var argc = CardType.arguments.length;
	this.objname = "object CardType";
	var tmpcardtype = (argc > 0) ? argv[0] : "CardObject";
	var tmprules = (argc > 1) ? argv[1] : "0,1,2,3,4,5,6,7,8,9";
	var tmplen = (argc > 2) ? argv[2] : "13,14,15,16,19";

	this.setCardNumber = setCardNumber;  // set CardNumber method.
	this.setCardType = setCardType;  // setCardType method.
	this.setLen = setLen;  // setLen method.
	this.setRules = setRules;  // setRules method.
	this.setExpiryDate = setExpiryDate;  // setExpiryDate method.

	this.setCardType(tmpcardtype);
	this.setLen(tmplen);
	this.setRules(tmprules);
	if (argc > 4)
		this.setExpiryDate(argv[3], argv[4]);

	this.checkCardNumber = checkCardNumber;  // checkCardNumber method.
	this.getExpiryDate = getExpiryDate;  // getExpiryDate method.
	this.getCardType = getCardType;  // getCardType method.
	this.isCardNumber = isCardNumber;  // isCardNumber method.
	this.isExpiryDate = isExpiryDate;  // isExpiryDate method.
	this.luhnCheck = luhnCheck;// luhnCheck method.
	return this;
}

/*************************************************************************\
boolean checkCardNumber([String cardnumber, int year, int month])
return true if cardnumber pass the luhncheck and the expiry date is
valid, else return false.
\*************************************************************************/


function checkCardNumber()
{
	var argv = checkCardNumber.arguments;
	var argc = checkCardNumber.arguments.length;
	var cardnumber = (argc > 0) ? argv[0] : this.cardnumber;
	var year = (argc > 1) ? argv[1] : this.year;
	var month = (argc > 2) ? argv[2] : this.month;

	this.setCardNumber(cardnumber);
	this.setExpiryDate(year, month);

	if (!this.isCardNumber())
		return false;
	if (!this.isExpiryDate())
		return false;

	return true;
}


/*************************************************************************\
String getCardType()
return the cardtype.


\*************************************************************************/
function getCardType()
{
	return this.cardtype;
}


/*************************************************************************\
String getExpiryDate()
return the expiry date.
\*************************************************************************/
function getExpiryDate()
{
	return this.month + "/" + this.year;
}
/*************************************************************************\
boolean isCardNumber([String cardnumber])
return true if cardnumber pass the luhncheck and the rules, else return
false.
\*************************************************************************/

function isCardNumber()
{
	var argv = isCardNumber.arguments;
	var argc = isCardNumber.arguments.length;
	var cardnumber = (argc > 0) ? argv[0] : this.cardnumber;
	if (!this.luhnCheck())
		return false;

	for (var n = 0; n < this.len.size; n++)
		if (cardnumber.toString().length == this.len[n])
		{
			for (var m = 0; m < this.rules.size; m++)
			{
				var headdigit = cardnumber.substring(0, this.rules[m].toString().length);
				if (headdigit == this.rules[m])
					return true;
			}
			return false;
		}
return false;
}

/*************************************************************************\
boolean isExpiryDate([int year, int month])
return true if the date is a valid expiry date,
else return false.
\*************************************************************************/

function isExpiryDate()
{
	var argv = isExpiryDate.arguments;
	var argc = isExpiryDate.arguments.length;

	year = argc > 0 ? argv[0] : this.year;
	month = argc > 1 ? argv[1] : this.month;

	if (!isNum(year+""))
		return false;
	if (!isNum(month+""))
		return false;
	today = new Date();
	expiry = new Date(year, month);
	if (today.getTime() > expiry.getTime())
		return false;
	else
		return true;
}

/*************************************************************************\
boolean isNum(String argvalue)
return true if argvalue contains only numeric characters,
else return false.
\*************************************************************************/

function isNum(argvalue)
{
	argvalue = argvalue.toString();

	if (argvalue.length == 0)
		return false;

	for (var n = 0; n < argvalue.length; n++)
		if (argvalue.substring(n, n+1) < "0" || argvalue.substring(n, n+1) > "9")
			return false;
return true;
}

/*************************************************************************\
 boolean luhnCheck([String CardNumber])
return true if CardNumber pass the luhn check else return false.
\*************************************************************************/

function luhnCheck()
{
	var argv = luhnCheck.arguments;
	var argc = luhnCheck.arguments.length;

	var CardNumber = argc > 0 ? argv[0] : this.cardnumber;

	if (! isNum(CardNumber))
	{
		return false;
	}

	var no_digit = CardNumber.length;
	var oddoeven = no_digit & 1;
	var sum = 0;

	for (var count = 0; count < no_digit; count++)
	{
		var digit = parseInt(CardNumber.charAt(count));
		if (!((count & 1) ^ oddoeven))
		{
			digit *= 2;
			if (digit > 9)
				digit -= 9;
		}
		sum += digit;
	}
	if (sum % 10 == 0)
		return true;
	else
		return false;
}

/*************************************************************************\
ArrayObject makeArray(int size)
return the array object in the size specified.
\*************************************************************************/

function makeArray(size)
{
	this.size = size;
	return this;
}

/*************************************************************************\
CardType setCardNumber(cardnumber)
return the CardType object.
\*************************************************************************/

function setCardNumber(cardnumber)
{
	this.cardnumber = cardnumber;
	return this;
}

/*************************************************************************\
CardType setCardType(cardtype)
return the CardType object.
\*************************************************************************/

function setCardType(cardtype)
{
	this.cardtype = cardtype;
	return this;
}

/*************************************************************************\
CardType setExpiryDate(year, month)
return the CardType object.
\*************************************************************************/

function setExpiryDate(year, month)
{
	this.year = year;
	this.month = month;
	return this;
}

/*************************************************************************\
CardType setLen(len)
return the CardType object.
\*************************************************************************/

function setLen(len)
{
	// Create the len array.
	if (len.length == 0 || len == null)
	len = "13,14,15,16,19";

	var tmplen = len;
	n = 1;
	while (tmplen.indexOf(",") != -1)
	{
		tmplen = tmplen.substring(tmplen.indexOf(",") + 1, tmplen.length);
		n++;
	}
	this.len = new makeArray(n);
	n = 0;
	while (len.indexOf(",") != -1)
	{
		var tmpstr = len.substring(0, len.indexOf(","));
		this.len[n] = tmpstr;
		len = len.substring(len.indexOf(",") + 1, len.length);
		n++;
	}
	this.len[n] = len;
	return this;
}

/*************************************************************************\
CardType setRules()
return the CardType object.
\*************************************************************************/

function setRules(rules)
{
	// Create the rules array.
	if (rules.length == 0 || rules == null)
		rules = "0,1,2,3,4,5,6,7,8,9";

	var tmprules = rules;
	n = 1;
	while (tmprules.indexOf(",") != -1)
	{
		tmprules = tmprules.substring(tmprules.indexOf(",") + 1, tmprules.length);
		n++;
	}
	this.rules = new makeArray(n);
	n = 0;
	while (rules.indexOf(",") != -1)
	{
		var tmpstr = rules.substring(0, rules.indexOf(","));
		this.rules[n] = tmpstr;
		rules = rules.substring(rules.indexOf(",") + 1, rules.length);
		n++;
	}
	this.rules[n] = rules;
	return this;
}

//--------------------------------------------------------------------------------

//This function moves from listbox1 to listbox2
//n indicates maximumnumber of elements can be moved. pass n=0 in case of no limit.
//select1 is the name of the listbox1
//select2 is the name of the listbox2
//frm is the name of form

function fnMoveToTarget(n,select1,select2,frm)
{
	var j=0;
	if(select2.length > 0 && select1.length != 0)
		if (select2[0].value =="0")
			select2.length=0;

	for(var i=0;i<select1.length;i++)
	{
		if(select1.options[i].text.charAt(0)!='*')
		{
			if(select1.options[i].selected==true )
				j++;
		}
	}

	if ((select2.length + j ) > n && n != 0)
	{
		alert("More than " + n +" elements cannot be moved")
		return false;
	}

	for(i=0;i<select1.length;i++)
	{
		if(select1.options[i].selected==true  && select1.options[i].text  != "")
		{
			for(m=0; m<select2.length; m++)
			{
				if(select1.options[i].text == select2.options[m].text)
				{
					var flag = "N";
					break;
				}
			}
			if(flag != "N" && select1.options[i].text.charAt(0)!= '*')
			{
				var opt=new Option(select1.options[i].text,true,true);
				select2.options[select2.options.length]=opt;
				select2.options[select2.options.length-1].value=select1.options[i].value;
				var opt1=new Option("* "+select1.options[i].text,true,true);
				select1.options[i]=opt1;
			}
			else
			{
				alert(select1.options[i].text+" already exists, Please select another one");
			}
			if (select2.length == 0 )
			{
				var opt=new Option(select1.options[i].text,true,true);
				select2.options[select2.options.length]=opt;
			}
		}
		select1.options[i].selected=false;
	}
return true;
}

//--------------------------------------------------------------------------------

//This function moves from listbox2 to listbox1
//select1 is the name of the listbox1
//select2 is the name of the listbox2
//frm is the name of form

function fnMoveToSource(select1,select2,frm)
{
	var k=1;
	for(j=0;j<select2.length;j++)
	{
		if(select2.options[j].selected != true)
			k++;
	}

	var a=new Array(k);
	var b=new Array(k);

	k=0;
	for(j=0;j<select2.length;j++)
	{
		if(select2.options[j].selected==true)
		{
			var opt=new Option(select2.options[j].text,true,true);
			for(var z=0;z<select1.length;z++)
			{
				if(select1.options[z].text=="* "+opt.text)
				{
					select1.options[z]=opt;
					select1.options[z].value=select2.options[j].value;
				}
			}
		}
	}
	for(var j=0;j<select2.length;j++)
	{
		if(select2.options[j].selected!=true)
		{
			a[k]=select2.options[j].text;
			b[k]=select2.options[j].value;
			k++;
		}
	}
	select2.length=0;
	for(var j=0;j<k;j++)
	{
		var opt=new Option("",true,true);
		opt.text=a[j];
		opt.value=b[j];
		select2.options[j]=opt;
	}
	if (select2.length == 0)
	{
		var opt=new Option("",true,true);
		opt.text="No Topic Selected...";
		opt.value="0";
		select2.options[j]=opt;
	}
return true;
}
//-------------------------------------------------------------------------------------

//This function calls a page
function fnCallPage(sPageName)
{
	window.location.href=sPageName
}

//--------------------------------------------------------------------------------------
//This function checks for valid file name
function fnCheckValidFile(sFileName)
{
	var fso,sFile;
	sFile=sFileName;
	fso = new ActiveXObject("Scripting.FileSystemObject");
	if(fso.FileExists(sFile))
		return true;
}
//-----------------------------------------------------------------------------------------------
//Function For Checking Spaces in Editor

function fnCheckSpaces(sFieldValue)
{
	//alert("sFieldValue=" + sFieldValue)
	bValidFlag=true;
	sFieldValue=fnTrim(sFieldValue);
	for(i=0;i<sFieldValue.length-1;i++)
	{
		if(sFieldValue.substr(i,6) != "&nbsp;")
		{
			bValidFlag=false;
			break;
		}
		else
		{
			i=i+5
		}
	}

		return (bValidFlag)
 }
//-----------------------------------------------------------------------------------------------
//This Function Used for CHecking valid URL

function fnCheckURL(sFieldValue)
{
	bValidFlag=true;
	sFieldValue=fnTrim(sFieldValue)
	for(i=0;i<sFieldValue.length-1;i++)
	{
		if(sFieldValue.substr(i,4) == "www.")
		{
			for(j=i+5;j<sFieldValue.length-1;j++)
			{
				if(sFieldValue.substr(j,1) == ".")
				{
					bValidFlag=false;
					break;
				}
			}
		}
	}
	return (bValidFlag)
 }

//-----------------------------------------------------------------------------------------------

//This function checks for given value to be valid string and returns true if the value is not a valid string
//Valid string Sould strat with an alphabet(either case) followed by alphabet(either case) and/or any numeric value and/or - and/or _
//This function internally uses fnTrim function
function fnCheckvalidString(sFieldValue)
{
  bValidFlag=true;
  sFieldValue=fnTrim(sFieldValue);
  	for(i=0;i<sFieldValue.length;i++)
	{
		if (!((sFieldValue.charAt(i)>="A" && sFieldValue.charAt(i)<="Z") || (sFieldValue.charAt(i) >= "a" && sFieldValue.charAt(i)<= "z") ||(sFieldValue.charAt(i)>="0" && sFieldValue.charAt(i)<="9") || (sFieldValue.charAt(i) == "-") || (sFieldValue.charAt(i) == "_")|| (sFieldValue.charAt(i) == ".")))
		{
			bValidFlag=true;
			break;
		}
		else
			bValidFlag=false;
	}
  return bValidFlag;
}// end of fnCheckValidString

//This function checks for given value to be valid string and returns true if the value is not a valid string
//Valid string Sould strat with an alphabet(either case) followed by alphabet(either case) and/or any numeric value and/or - and/or _
//This function internally uses fnTrim function
function fnCheckString1(sFieldValue)
{
  bValidFlag=true;
  sFieldValue=fnTrim(sFieldValue);
  	for(i=0;i<sFieldValue.length;i++)
	{
		if (!((sFieldValue.charAt(i)>="A" && sFieldValue.charAt(i)<="Z") || (sFieldValue.charAt(i) >= "a" && sFieldValue.charAt(i)<= "z") ||(sFieldValue.charAt(i)>="0" && sFieldValue.charAt(i)<="9") || (sFieldValue.charAt(i) == "-") || (sFieldValue.charAt(i) == " ") || (sFieldValue.charAt(i) == "_")|| (sFieldValue.charAt(i) == ".")))
		{
			bValidFlag=true;
			break;
		}
		else
			bValidFlag=false;
	}
  return bValidFlag;
}// end of fnCheckString1


//This function adds content of a text box as an item to a list box
//It takes two argument .Frist one text object and second one os list object.

function fnAddListItem(txtObj,lstOjb)
{
	var sRgn=fnTrim(txtObj.value);
	if (sRgn=="")
	{
		txtObj.select();
		txtObj.focus();
		return false;
	}
	if(fnCheckString2(sRgn))
	{
		txtObj.focus();
		txtObj.select();
		return false;
	}
	var nLstIndex=lstOjb.length;
	var opt=new Option(sRgn,true,true);
	lstOjb.options[nLstIndex]=opt;
	lstOjb.options[nLstIndex].value=sRgn;
	txtObj.value="";
        return true;
}

//This function adds content of a text box as an item to a list box
//It takes two argument .Frist one text object and second one os list object.
//This function is explicitly used in option maitenance screen
function fnAddListItem1(txtObj,lstOjb)
{
	var sRgn=fnTrim(txtObj.value);
	if (sRgn=="")
	{
		txtObj.select();
		txtObj.focus();
		return false;
	}
	if(fnCheckString2(sRgn))
	{
		txtObj.focus();
		txtObj.select();
		return false;
	}
	var nLstIndex=lstOjb.length;
	var opt=new Option(sRgn,true,true);
	lstOjb.options[nLstIndex]=opt;
	lstOjb.options[nLstIndex].value=sRgn;
	txtObj.value="";
        return true;
}
//----------------------------------------------------------------------------------
//This function delets a selected item from list box

function fnDelListItem(lstObj)
{
	if(lstObj.selectedIndex==-1)
	{
		alert("Please select item");
		return false;
	}
	var a=new Array(k);
	var b=new Array(k);
	var k=0;
	for(var j=0;j<lstObj.length;j++)
	{
		if(lstObj.options[j].selected!=true)
		{
			a[k]=lstObj.options[j].text;
			b[k]=lstObj.options[j].value;
			k++;
		}
	}
	var s=lstObj.length;
	lstObj.length=0;
	for(var j=0;j<k;j++)
	{
		var opt=new Option("",true,true);
		opt.text=a[j];
		opt.value=b[j];
		lstObj.options[j]=opt;
	}
	return false;
}


//----------------------------------------------------------------------------------
//This function restricts a form from getting submitted when "Enter key" is pressed


function checkchar(myfield,e)
  {
	  var keycode;
	  pass=false;
	  if (window.event) keycode = window.event.keyCode;
	  else if (e) keycode = e.which;
	  else return true;

	  if(keycode != 34 && keycode != 39&& keycode != 32)
	  {
		return true;
	  }
	  else
	  {
		return false;
	  }

	if(pass)
	  {
		   return true;
	  }
	 else
	 {
		  return false;
	 }

}

//This function checks for given value to be valid string and returns true if the value is not a valid string
//Valid string Sould strat with an alphabet(either case) followed by alphabet(either case) and/or any numeric value and/or - and/or _
//This function internally uses fnTrim function


function fnCheckString3(sFieldValue)
{
  bValidFlag=true;
  sFieldValue=fnTrim(sFieldValue);
  if ((sFieldValue.charAt(0) >= "A" && sFieldValue.charAt(0)<= "Z" ) || (sFieldValue.charAt(0) >= "a" && sFieldValue.charAt(0) <= "z"))
  	for(i=0;i<sFieldValue.length;i++)
	{
		if (!((sFieldValue.charAt(i)>="A" && sFieldValue.charAt(i)<="Z") || (sFieldValue.charAt(i) >= "a" && sFieldValue.charAt(i)<= "z") ||(sFieldValue.charAt(i)>="0" && sFieldValue.charAt(i)<="9") || (sFieldValue.charAt(i) == "-") || (sFieldValue.charAt(i) == "_") ))
		{
			bValidFlag=true;
			break;
		}
		else
			bValidFlag=false;
	}
  return bValidFlag;
}// end of fnCheckString


//This Function returns User Defined Error Message dynamically replacing
//(alt 135)'ç' in the User Defined Error Message from Database with the Value returned from servlet
//It takes two argument .Frist one "form name" and second one "concatnated value with Error id"
//Ex. alert(fnUserMessage(test.name,val)); where val="kmurthyæUM061" (æ=alt 145)
// "ç is logged in, cannot be deleted." will become "kmurthy is logged in, cannot be deleted."

function fnUserMessage(sFormName,sErrorValue)
{
	var sConcatValue = sErrorValue.split('æ'); //alt 145
	var sUserVar = sConcatValue[0];
	var sErr='hid'+ sConcatValue[1];
	var sUserErrorVal= eval('document.'+sFormName+'.'+sErr+'.value');
	sUserErrorVal=sUserErrorVal.replace("ç",sUserVar);
	return sUserErrorVal;
}