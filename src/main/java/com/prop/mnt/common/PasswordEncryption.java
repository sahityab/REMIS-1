package com.prop.mnt.common;
import java.io.*;
import java.security.*;

public class PasswordEncryption 
{
  private static PasswordEncryption pe;
  public PasswordEncryption(){ }
  public static PasswordEncryption getObject()
  {
    if(pe == null)
      pe = new PasswordEncryption();
    return pe;
  }
  /**
  * This Method returns encripted String data
  * @param str
  * @return
  */
  public String getEncriptedData(String str){
    str=str.substring(4)+str.substring(2,3)+str.substring(1,4)+str.substring(0,1);
    str=""+str.hashCode();
    String retString = "";
    int a=0;
    for(int i=0;i<str.length();i++){
      retString = retString + str.valueOf(str.charAt(i));
      if(i == 0){
        retString=retString+(a+i);
        a=a+25;
      }
      else if(i == 1){
        retString=retString+(a-i+7);
      }
      else if(i == 3){
        retString=retString+a+(i*10);
      }
    }
    return retString.trim();
  }
}

