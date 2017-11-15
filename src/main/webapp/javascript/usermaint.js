$(document).ready(function() {
    $('#userListId').change(function fnSelect(){
	  		setTimeout(function(){
	  			alert($('#userListId').val());
	  			if($('#userListId').val() != "create" ){
	  				$('form').get(0).setAttribute('action', 'showuser');
	  				$('form').get(0).submit(function(){	});
	  			}else{
	  				$('form').get(0).setAttribute('action', 'usermanagement');
	  				$('form').get(0).submit(function(){	});
	  			}
			}, 1);    
  	});
    
/*    $(document).ready(function(){
        $("form").submit(function(event) {
            $.each($("form").find("span.errorMessage"), function() {   <tr errorfor="title"><td colspan="2" align="center"><span classname="errorMessage" class="errorMessage">Campo requerido 
                return false;
            });
            if ($(mySubmit).exists()) {
                if (!mySubmit()) 
                    return false;
            }
            return true;
        });
    });    
*/
    });

/*function mySubmit() {
    if ( $("#userListId").val() == "create"  && ( $("#newUserIdId").val() == null || $("#newUserIdId").val().trim()==''" ) ) {
        var trStrutsFieldError='<tr errorfor="text">'+
                                    '<td colspan="2" align="center" valign="top"><span class="errorMessage">'+strValidationRequired+'</span></td>'+
                                '</tr>';
        $(trStrutsFieldError).insertBefore($("#newUserIdId").parent().parent());
        return false;
    }
    return true;
}
*/