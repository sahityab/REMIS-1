package com.prop.mnt.common;

import java.util.*;
import java.io.*;
//import com.oreilly.servlet.MultipartRequest;

public class StaticDataBean
{
  private static StaticDataBean sdb = null;
  private String sFileName;
  public StaticDataBean(){ }
  public static StaticDataBean getObject()
  {
    if(sdb == null)
      sdb = new StaticDataBean();
    return sdb;
  }
  public String getFileName()
  {
    return sFileName;
  }
  public String getReportPath()
  {
    return "F:/Reports";
  }
  public String getFilePath()
  {
    return "c:/projects/jdev/promis/rc_promis";
  }
  public ArrayList getFileTypes()
  {
    ArrayList<String> arrFileTypes = new ArrayList<String>();
    arrFileTypes.add("Checklists");
    arrFileTypes.add("Documents");
    arrFileTypes.add("Images");
    arrFileTypes.add("Researches");
    arrFileTypes.add("Others");
    return arrFileTypes;
  }
  public String putCommas(String num)
  {
    String prec = "";
    num = num.trim();
    if(num.indexOf('.') != -1)
    {
      prec = num.substring(num.indexOf('.'));
      num = num.substring(0,num.indexOf('.'));
    }
    String resnum = "";
    if(num.length() > 3)
    {
      for(int i = num.length(); i >= 0; i=i-3)
      {
        if(num.length() > 3)
        {
          resnum = ","+num.substring(i-3,i)+resnum;
          num = num.substring(0,i-3);
        }
        else
        {
          resnum = num+resnum;
          num = "";
        }
      }
      if(num.length() > 0)
        resnum = num+resnum;
      if(resnum.startsWith(","))
        resnum = resnum.substring(1);
    }
    else
      resnum = num;
    return resnum+prec;
  }
  /*public String fileUpload(MultipartRequest mrequest)throws Exception
  {
      String msg = null;
      sFileName = "";
      boolean flag = false;
			// Show which files are uploaded
			Enumeration files = mrequest.getFileNames();
			while (files.hasMoreElements())
			{
				String name = (String)files.nextElement();
				sFileName = mrequest.getFilesystemName(name);
				String type = mrequest.getContentType(name);
				File f = mrequest.getFile(name);
				if (f != null)
				{
					System.out.println("length: " + f.length());
          flag = true;
				}        
			}
      if(flag)
        msg = "File uploaded successfully.";
      return msg;
  } */   
  public String getMonthName(String month)
  {
    String sMon = "";
    if(month.equals("01") || month.equals("1"))
      sMon = "Jan";
    else if(month.equals("02") || month.equals("2"))
      sMon = "Feb";
    else if(month.equals("03") || month.equals("3"))
      sMon = "Mar";
    else if(month.equals("04") || month.equals("4"))
      sMon = "Apr";
    else if(month.equals("05") || month.equals("5"))
      sMon = "May";
    else if(month.equals("06") || month.equals("6"))
      sMon = "Jun";
    else if(month.equals("07") || month.equals("7"))
      sMon = "Jul";
    else if(month.equals("08") || month.equals("8"))
      sMon = "Aug";
    else if(month.equals("09") || month.equals("9"))
      sMon = "Sep";
    else if(month.equals("10"))
      sMon = "Oct";
    else if(month.equals("11"))
      sMon = "Nov";
    else if(month.equals("12"))
      sMon = "Dec";
    return sMon;
  }
  public List<String> getCountries()
  {
      List<String> arrList = new ArrayList<String>();
      arrList.add("Afghanistan");
      arrList.add("Albania");
      arrList.add("Algeria");
      arrList.add("American Samoa");
      arrList.add("Andorra");
      arrList.add("Angola");
      arrList.add("Anguilla");
      arrList.add("Antarctica");
      arrList.add("Antigua and Barbuda");
      arrList.add("Argentina");
      arrList.add("Armenia");
      arrList.add("Aruba");
      arrList.add("Australia");
      arrList.add("Austria");
      arrList.add("Azerbaijan");
      arrList.add("Bahamas");
      arrList.add("Bahrain");
      arrList.add("Bangladesh");
      arrList.add("Barbados");
      arrList.add("Belarus");
      arrList.add("Belgium");
      arrList.add("Belize");
      arrList.add("Benin");
      arrList.add("Bermuda");
      arrList.add("Bhutan");
      arrList.add("Bolivia");
      arrList.add("Bosnia and Herzegovina");
      arrList.add("Botswana");
      arrList.add("Bouvet Island");
      arrList.add("Brazil");
      arrList.add("British Indian Ocean Territory");
      arrList.add("Brunei Darussalam");
      arrList.add("Bulgaria");
      arrList.add("Burkina Faso");
      arrList.add("Burundi");
      arrList.add("Cambodia");
      arrList.add("Cameroon");
      arrList.add("Canada");
      arrList.add("Cape Verde");
      arrList.add("Cayman Islands");
      arrList.add("Central African Republic");
      arrList.add("Chad");
      arrList.add("Chile");
      arrList.add("China");
      arrList.add("Christmas Island");
      arrList.add("Cocos (Keeling) Islands");
      arrList.add("Colombia");
      arrList.add("Comoros");
      arrList.add("Congo");
      arrList.add("Cook Islands");
      arrList.add("Costa Rica");
      arrList.add("Cote D'Ivoire (Ivory Coast)");
      arrList.add("Croatia (Hrvatska");
      arrList.add("Cuba");
      arrList.add("Cyprus");
      arrList.add("Czech Republic");
      arrList.add("Denmark");
      arrList.add("Djibouti");
      arrList.add("Dominica");
      arrList.add("Dominican Republic");
      arrList.add("East Timor");
      arrList.add("Ecuador");
      arrList.add("Egypt");
      arrList.add("El Salvador");
      arrList.add("Equatorial Guinea");
      arrList.add("Eritrea");
      arrList.add("Estonia");
      arrList.add("Ethiopia");
      arrList.add("Falkland Islands (Malvinas)");
      arrList.add("Faroe Islands");
      arrList.add("Fiji");
      arrList.add("Finland");
      arrList.add("France");
      arrList.add("France - Metropolitan");
      arrList.add("French Guiana");
      arrList.add("French Polynesia");
      arrList.add("French Southern Territories");
      arrList.add("Gabon");
      arrList.add("Gambia");
      arrList.add("Georgia");
      arrList.add("Germany");
      arrList.add("Ghana");
      arrList.add("Gibraltar");
      arrList.add("Greece");
      arrList.add("Greenland");
      arrList.add("Grenada");
      arrList.add("Guadeloupe");
      arrList.add("Guam");
      arrList.add("Guatemala");
      arrList.add("Guinea");
      arrList.add("Guinea-Bissau");
      arrList.add("Guyana");
      arrList.add("Haiti");
      arrList.add("Heard and McDonald Islands");
      arrList.add("Honduras");
      arrList.add("Hong Kong");
      arrList.add("Hungary");
      arrList.add("Iceland");
      arrList.add("India");
      arrList.add("Indonesia");
      arrList.add("Iran");
      arrList.add("Iraq");
      arrList.add("Ireland");
      arrList.add("Israel");
      arrList.add("Italy");
      arrList.add("Jamaica");
      arrList.add("Japan");
      arrList.add("Jordan");
      arrList.add("Kazakhstan");
      arrList.add("Kenya");
      arrList.add("Kiribati");
      arrList.add("Korea (North)");
      arrList.add("Korea (South)");
      arrList.add("Kuwait");
      arrList.add("Kyrgyzstan");
      arrList.add("Laos");
      arrList.add("Latvia");
      arrList.add("Lebanon");
      arrList.add("Lesotho");
      arrList.add("Liberia");
      arrList.add("Libya");
      arrList.add("Liechtenstein");
      arrList.add("Lithuania");
      arrList.add("Luxembourg");
      arrList.add("Macau");
      arrList.add("Macedonia");
      arrList.add("Madagascar");
      arrList.add("Malawi");
      arrList.add("Malaysia");
      arrList.add("Maldives");
      arrList.add("Mali");
      arrList.add("Malta");
      arrList.add("Marshall Islands");
      arrList.add("Martinique");
      arrList.add("Mauritania");
      arrList.add("Mauritius");
      arrList.add("Mayotte");
      arrList.add("Mexico");
      arrList.add("Micronesia");
      arrList.add("Moldova");
      arrList.add("Monaco");
      arrList.add("Mongolia");
      arrList.add("Montserrat");
      arrList.add("Morocco");
      arrList.add("Mozambique");
      arrList.add("Myanmar");
      arrList.add("Namibia");
      arrList.add("Nauru");
      arrList.add("Nepal");
      arrList.add("Netherlands");
      arrList.add("Netherlands Antilles");
      arrList.add("New Caledonia");
      arrList.add("New Zealand");
      arrList.add("Nicaragua");
      arrList.add("Niger");
      arrList.add("Nigeria");
      arrList.add("Niue");
      arrList.add("Norfolk Island");
      arrList.add("Northern Mariana Islands");
      arrList.add("Norway");
      arrList.add("Oman");
      arrList.add("Pakistan");
      arrList.add("Palau");
      arrList.add("Panama");
      arrList.add("Papua New Guinea");
      arrList.add("Paraguay");
      arrList.add("Peru");
      arrList.add("Philippines");
      arrList.add("Pitcairn");
      arrList.add("Poland");
      arrList.add("Portugal");
      arrList.add("Puerto Rico");
      arrList.add("Qatar");
      arrList.add("Reunion");
      arrList.add("Romania");
      arrList.add("Russian Federation");
      arrList.add("Rwanda");
      arrList.add("Saint Kitts and Nevis");
      arrList.add("Saint Lucia");
      arrList.add("Saint Vincent (Grenadines)");
      arrList.add("Samoa");
      arrList.add("San Marino");
      arrList.add("Sao Tome and Principe");
      arrList.add("Saudi Arabia");
      arrList.add("Senegal");
      arrList.add("Seychelles");
      arrList.add("Sierra Leone");
      arrList.add("Singapore");
      arrList.add("Slovak Republic");
      arrList.add("Slovenia");
      arrList.add("Solomon Islands");
      arrList.add("Somalia");
      arrList.add("South Africa");
      arrList.add("South Georgia Islands");
      arrList.add("Spain");
      arrList.add("Sri Lanka");
      arrList.add("St. Helena");
      arrList.add("St. Pierre and Miquelon");
      arrList.add("Sudan");
      arrList.add("Suriname");
      arrList.add("Svalbard and Jan Mayen Islands");
      arrList.add("Swaziland");
      arrList.add("Sweden");
      arrList.add("Switzerland");
      arrList.add("Syria");
      arrList.add("Taiwan");
      arrList.add("Tajikistan");
      arrList.add("Tanzania");
      arrList.add("Thailand");
      arrList.add("Togo");
      arrList.add("Tokelau");
      arrList.add("Tonga");
      arrList.add("Trinidad and Tobago");
      arrList.add("Tunisia");
      arrList.add("Turkey");
      arrList.add("Turkmenistan");
      arrList.add("Turks and Caicos Islands");
      arrList.add("Tuvalu");
      arrList.add("Uganda");
      arrList.add("Ukraine");
      arrList.add("United Arab Emirates");
      arrList.add("United Kingdom");
      arrList.add("United States");
      arrList.add("Uruguay");
      arrList.add("US Minor Outlying Islands");
      arrList.add("Uzbekistan");
      arrList.add("Vanuatu");
      arrList.add("Vatican City State (Holy See)");
      arrList.add("Venezuela");
      arrList.add("Viet Nam");
      arrList.add("Virgin Islands (British)");
      arrList.add("Virgin Islands (US)");
      arrList.add("Wallis and Futuna Islands");
      arrList.add("Western Sahara");
      arrList.add("YAP");
      arrList.add("Yemen");
      arrList.add("Yugoslavia");
      arrList.add("Zaire");
      arrList.add("Zambia");
      arrList.add("Zimbabwe");
      return arrList;
  }
  public Map<String, String> getUSStates(){
	  Map<String, String> map = new HashMap<String, String>();
	  map.put("AL", "Alabama");
	  map.put("AK", "Alaska");
	  map.put("AZ", "Arizona");
	  map.put("AR", "Arkansas");
	  map.put("CA", "California");
	  map.put("CO", "Colorado");
	  map.put("CT", "Connecticut");
	  map.put("DE", "Delaware");
	  map.put("FL", "Florida");
	  map.put("GA", "Georgia");
	  map.put("HI", "Hawaii");
	  map.put("ID", "Idaho");
	  map.put("IL", "Illinois");
	  map.put("IN", "Indiana");
	  map.put("IA", "Iowa");
	  map.put("KS", "Kansas");
	  map.put("KY", "Kentucky");
	  map.put("LA", "Louisiana");
	  map.put("ME", "Maine");
	  map.put("MD", "Maryland");
	  map.put("MA", "Massachusetts");
	  map.put("MI", "Michigan");
	  map.put("MN", "Minnesota");
	  map.put("MS", "Mississippi");
	  map.put("MO", "Missouri");
	  map.put("MT", "Montana");
	  map.put("NE", "Nebraska");
	  map.put("NV", "Nevada");
	  map.put("NH", "New Hampshire");
	  map.put("NJ", "New Jersey");
	  map.put("NM", "New Mexico");
	  map.put("NY", "New York");
	  map.put("NC", "North Carolina");
	  map.put("ND", "North Dakota");
	  map.put("OH", "Ohio");
	  map.put("OK", "Oklahoma");
	  map.put("OR", "Oregon");
	  map.put("PA", "Pennsylvania");
	  map.put("RI", "Rhode Island");
	  map.put("SC", "South Carolina");
	  map.put("SD", "South Dakota");
	  map.put("TN", "Tennessee");
	  map.put("TX", "Texas");
	  map.put("UT", "Utah");
	  map.put("VT", "Vermont");
	  map.put("Virginia", "VA");
	  map.put("WA", "Washington");
	  map.put("WV", "West Virginia");
	  map.put("WI", "Wisconsin");
	  map.put("WY", "Wyoming");
	  map.put("AS", "American Samoa");
	  map.put("DC", "District of Columbia");
	  map.put("FM", "Federated States of Micronesia");
	  map.put("GU", "Guam");
	  map.put("MH", "Marshall Islands");
	  map.put("MP", "Northern Mariana Islands");
	  map.put("PW", "Palau");
	  map.put("PR", "Puerto Rico");
	  map.put("VI", "Virgin Islands");
	  map.put("AE", "Armed Forces");
	  map.put("AA", "Armed Forces Americas");
	  map.put("AP", "Armed Forces Pacific");
	  return map;
  }
  public Map<String, String> getOwnerTypes(){
	  Map<String, String> map = new HashMap<String, String>();
	  map.put("C", "Corporate");
	  map.put("I", "Individual");
	  return map;
  }
}