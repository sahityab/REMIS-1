// Reference http://jqueryui.com/datepicker/

// Default
$(function() {
   $( "#datepicker" ).datepicker();
});

//Display Month and Year
$(function() {
   $( "#datepickerMonthYear" ).datepicker({
	   changeMonth : true,
	   changeYear : true
   });
});

// Icon
$( function() {
	$("#datepickerIcon").datepicker( {
		showOn : "button",
		buttonImage : "/wimsot/images/calendar.gif",  //Change 'struts2' to your domain
		buttonImageOnly : true,
		buttonText : "Select date",
		dateFormat : "mm/dd/yy",
		changeMonth : true,
		changeYear : true
	});
});

// Restricts the available date ranges.
$( function() {
	$("#datepickerRestricted").datepicker( {
		showOn : "button",
		buttonImage : "/wimsot/images/calendar.gif",
		buttonImageOnly : true,
		buttonText : "Select date between January 1, 1990 and January 1, 2000",
		dateFormat : "mm/dd/yy",
		changeMonth : true,
		changeYear : true,
		minDate: new Date(1900, 0, 1)
	//	maxDate: new Date(2000, 0, 1)
	});
});

//Display Multiple Months
$(function() {
   $( "#datepickerMultipleMonths" ).datepicker({
	   numberOfMonths: 3,
	   showButtonPanel : true
   });
});

// Date Range starting one week in the future
$(function() {
    $( "#from" ).datepicker({
      defaultDate: "+1w",
      changeMonth: true,
      changeYear : true,
      numberOfMonths: 3,
      onClose: function( selectedDate ) {
        $( "#to" ).datepicker( "option", "minDate", selectedDate );
      }
    });
    $( "#to" ).datepicker({
      defaultDate: "+1w",
      changeMonth: true,
      changeYear : true,
      numberOfMonths: 3,
      onClose: function( selectedDate ) {
        $( "#from" ).datepicker( "option", "maxDate", selectedDate );
      }
    });
});
