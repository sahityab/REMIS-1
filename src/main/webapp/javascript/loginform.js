$().ready(function() {
	setTimeout(function(){$('#testid').focus();}, 1);
});

function ValidatePassKey(myfld, maxlength)
{	
	if(myfld.value.length >= maxlength){ 		
		document.getElementById((parseInt(myfld.id)+1)+"").focus();
		return false;
	}else
		return true;
}