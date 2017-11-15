$(document).ready(function() {
    
    $('#nextId').click(function fnSubmit(){
    	var str = "";
    	if($('#waterWellId').is(':checked')){
    		str += $("label[for='waterWellId']").text()+"\n";
    	}
    	if($('#pumpInstallId').is(':checked')){
    		str += $("label[for='pumpInstallId']").text()+"\n";
    	}
    	if($('#heatPumpId').is(':checked')){
    		str += $("label[for='heatPumpId']").text()+"\n";
    	}
    	if($('#monitoringId').is(':checked')){
    		str += $("label[for='monitoringId']").text()+"\n";
    	}
    	if($('#monitoringTestholeAlsoId').is(':checked')){
    		str += $("label[for='monitoringTestholeAlsoId']").text()+"\n";
    	}
    	if($('#monitoringTestholeOnlyId').is(':checked')){
    		str += $("label[for='monitoringTestholeOnlyId']").text()+"\n";
    	}
    	if(str.length > 0){
	    	if(confirm("You have selected the following exams:\n"+str+"\nDo you wish to proceed with the exam?")){
	    		return true;
	    	}else{
	    		return false;
	    	}
    	}
    	return true;
    });
    
});

function checkMonitoring(){
	if($('#monitoringTestholeAlsoId').is(':checked')){
		$('#monitoringId').prop('checked',true);
	}
}
function uncheckMonitoring(){
	if($('#monitoringId').is(':checked') == false){			
		$('#monitoringTestholeAlsoId').prop('checked',false);
		$('#monitoringId').prop('checked',false);
	}	
}
function fnExamRulesWindow(){
	window.open("http://www.sos.mo.gov/adrules/csr/current/10csr/10csr.asp#10-23", "_blank", "directories=no, status=no, width=1400, height=870, top=0, left=0");
}
