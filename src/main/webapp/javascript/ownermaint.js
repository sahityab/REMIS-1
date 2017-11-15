$(document).ready(function() {
    $('#ownerListId').change(function fnSelect(){
    	alert('hi');
	  		setTimeout(function(){
	  			alert($('#ownerListId').val());
	  			if( $('#ownerListId').val() != "0" ){
	  				$('form').get(0).setAttribute('action', 'showowner');
	  				$('form').get(0).submit(function(){	});
	  			}else{
	  				$('form').get(0).setAttribute('action', 'ownermanagement');
	  				$('form').get(0).submit(function(){	});
	  			}
			}, 1);    
  	});
});