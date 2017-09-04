package com.rongchut.shuvo.shasthokothon.Starting.Others;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by ASUS on 03-Jan-17.
 */

public class Converter {


    public  static String readyTime(String  result)
    {
        String temp="";
        String temp1="";
        DateFormat df = new SimpleDateFormat("hh:mm a");
        Date startDate=null;
        try {
            startDate = df.parse(result);
            String newDateString = new SimpleDateFormat("aa").format(startDate);
            String h1=new SimpleDateFormat("HH").format(startDate);
            int hour= Integer.parseInt(h1);
            if(newDateString.compareTo("AM")==0)
            {
                if((hour>=00)&&(hour<04))
                {
                    temp1="রাত ";
                }
                else if((hour>=04)&&(hour<=06))
                {
                    temp1="ভোর ";
                }
                else if((hour>06)&&(hour<=12))
                {
                    temp1="সকাল ";
                }



            }
            else if(newDateString.compareTo("PM")==0)
            {
                if((hour>=12)&&(hour<=16))
                {
                    temp1="দুপুর ";
                }
                else if((hour>16)&&(hour<=18))
                {
                    temp1="বিকাল ";
                }
                else if((hour>18)&&(hour<=20))
                {
                    temp1="সন্ধ্যা ";
                }
                else if((hour>=20)&&(hour<=24))
                {
                    temp1="রাত ";
                }
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return temp1+convertToBengali(result.substring(0,5));

    }
    public  static  String convertToBengali(String string)
    {
        String temp="";

        for(int i=0;i<string.length();i++)
        {
            if(Character.isDigit(string.charAt(i)))
            {
                if(string.charAt(i)=='0')
                {
                    temp+="০";
                }
                else if(string.charAt(i)=='1')
                {
                    temp+="১";
                }
                else if(string.charAt(i)=='2')
                {
                    temp+="২";
                }
                else if(string.charAt(i)=='3')
                {
                    temp+="৩";
                }
                else if(string.charAt(i)=='4')
                {
                    temp+="৪";
                }
                else if(string.charAt(i)=='5')
                {
                    temp+="৫";
                }
                else if(string.charAt(i)=='6')
                {
                    temp+="৬";
                }
                else if(string.charAt(i)=='7')
                {
                    temp+="৭";
                }
                else if(string.charAt(i)=='8')
                {
                    temp+="৮";
                }
                else if(string.charAt(i)=='9')
                {
                    temp+="৯";
                }
            }
            else
            {
                temp+=string.charAt(i);
            }
        }
        return temp;
    }
}
