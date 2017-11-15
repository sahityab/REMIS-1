$(document).ready(function() {
    $('#propertyListId').change(function fnSelect(){
	  		setTimeout(function(){
	  			if( $('#propertyListId').val() != "0" ){
	  				$('form').get(0).setAttribute('action', 'showproperty');
	  				$('form').get(0).submit(function(){	});
	  			}else{
	  				$('form').get(0).setAttribute('action', 'propertymanagement');
	  				$('form').get(0).submit(function(){	});
	  			}
			}, 1);    
  	});
});