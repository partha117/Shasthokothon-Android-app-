package com.rongchut.shuvo.shasthokothon.Starting.Vaccine;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;

import java.util.Calendar;

/**
 * Created by ASUS on 28-Nov-16.
 */

public class NotificationSetter {


    private  PatientData pd;
    private Context context;
    private  int year;
    private int month;
    private  int date;
    private Calendar birthDate;




    public NotificationSetter( PatientData pd, Context context) {

        this.pd = pd;
        this.context = context;
    }

    public  boolean setNotification()
    {
        VaccineData vd=pd.getVaccineData();
        String []bod=pd.getDateOfBirth().split("/");

        year=Integer.valueOf(bod[2]);
        month=Integer.valueOf(bod[1]);
        date=Integer.valueOf(bod[0]);

        Intent intent=new Intent(context,VaccineReceiver.class);
        intent.putExtra("ID",pd.getId());

        AlarmManager alarmManager=(AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
        birthDate=Calendar.getInstance();
        birthDate.set(year,month,date);



        if(vd.getBCG().compareTo("false")==0)
        {

            Calendar calendar=Calendar.getInstance();
            intent.putExtra("BCG","BCG");
            PendingIntent pendingIntent=PendingIntent.getBroadcast(context,1,intent,PendingIntent.FLAG_ONE_SHOT);
            alarmManager.set(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),pendingIntent);
        }
        if(vd.getDPT().compareTo("false")==0)
        {



            if((getAgeAsYear()==0)&&(getAgeAsMonth()<6))
            {
                   //1st Round
                    Calendar calendar=add(Calendar.MONTH,6,birthDate);

                    PendingIntent pendingIntent=PendingIntent.getBroadcast(context,2,intent,PendingIntent.FLAG_ONE_SHOT);
                    alarmManager.set(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis()+345,pendingIntent);

                    //2nd Round
                    calendar=add(Calendar.MONTH,3,calendar);
                    pendingIntent=PendingIntent.getBroadcast(context,3,intent,PendingIntent.FLAG_ONE_SHOT);
                    alarmManager.set(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis()+345,pendingIntent);

                    //3rd Round
                    calendar=add(Calendar.MONTH,3,calendar);
                    pendingIntent=PendingIntent.getBroadcast(context,4,intent,PendingIntent.FLAG_ONE_SHOT);
                    alarmManager.set(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis()+345,pendingIntent);

            }
            //greater than a year
            else
            {
                //1st round


                intent.putExtra("DPT","DPT");
                Calendar calendar=Calendar.getInstance();
                PendingIntent pendingIntent=PendingIntent.getBroadcast(context,2,intent,PendingIntent.FLAG_ONE_SHOT);
                alarmManager.set(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis()+345,pendingIntent);

                //2nd Round

                intent.putExtra("DPT","DPT");
                calendar=add(Calendar.MONTH,3,calendar);
                pendingIntent=PendingIntent.getBroadcast(context,3,intent,PendingIntent.FLAG_ONE_SHOT);
                alarmManager.set(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis()+345,pendingIntent);

                //3rd Round

                intent.putExtra("DPT","DPT");
                calendar=add(Calendar.MONTH,3,calendar);
                pendingIntent=PendingIntent.getBroadcast(context,4,intent,PendingIntent.FLAG_ONE_SHOT);
                alarmManager.set(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis()+345,pendingIntent);
            }
        }
        if(vd.getPOLIO().compareTo("false")==0)
        {
            intent.putExtra("POLIO","POLIO");
            if((getAgeAsYear()==0)&&(getAgeAsMonth()<10))
            {
                    Calendar calendar=add(Calendar.MONTH,10,birthDate);
                PendingIntent pendingIntent=PendingIntent.getBroadcast(context,5,intent,PendingIntent.FLAG_ONE_SHOT);
                alarmManager.set(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis()+600,pendingIntent);
            }
            else
            {

                Calendar calendar=Calendar.getInstance();
                PendingIntent pendingIntent=PendingIntent.getBroadcast(context,5,intent,PendingIntent.FLAG_ONE_SHOT);
                alarmManager.set(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis()+600,pendingIntent);
            }
        }
        if(vd.getHAM().compareTo("false")==0)
        {
            intent.putExtra("HAM","HAM");
            if((getAgeAsYear()==0)&&(getAgeAsMonth()<9))
            {
                Calendar calendar=add(Calendar.MONTH,9,birthDate);
                PendingIntent pendingIntent=PendingIntent.getBroadcast(context,6,intent,PendingIntent.FLAG_ONE_SHOT);
                alarmManager.set(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis()+900,pendingIntent);
            }
            else
            {

                Calendar calendar=Calendar.getInstance();
                PendingIntent pendingIntent=PendingIntent.getBroadcast(context,6,intent,PendingIntent.FLAG_ONE_SHOT);
                alarmManager.set(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis()+900,pendingIntent);
            }
        }
        if(vd.getHEPAB().compareTo("false")==0)
        {
            intent.putExtra("HEPAB","HEPAB");
            if(getAgeAsYear()<10)
            {
                Calendar calendar=add(Calendar.YEAR,10,birthDate);
                PendingIntent pendingIntent=PendingIntent.getBroadcast(context,7,intent,PendingIntent.FLAG_ONE_SHOT);
                alarmManager.set(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis()+1200,pendingIntent);
            }
            else
            {

                Calendar calendar=Calendar.getInstance();
                PendingIntent pendingIntent=PendingIntent.getBroadcast(context,7,intent,PendingIntent.FLAG_ONE_SHOT);
                alarmManager.set(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis()+1200,pendingIntent);
            }
        }
        if((vd.getHPV().compareTo("false")==0)&&(vd.isEssential()))
        {
            intent.putExtra("HPV","HPV");
            if(getAgeAsYear()<11)
            {
                Calendar calendar=add(Calendar.YEAR,11,birthDate);
                PendingIntent pendingIntent=PendingIntent.getBroadcast(context,8,intent,PendingIntent.FLAG_ONE_SHOT);
                alarmManager.set(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis()+1500,pendingIntent);
            }
            else
            {

                Calendar calendar=Calendar.getInstance();
                PendingIntent pendingIntent=PendingIntent.getBroadcast(context,8,intent,PendingIntent.FLAG_ONE_SHOT);
                alarmManager.set(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis()+1500,pendingIntent);
            }
        }
        return  true;
    }


    private Calendar  add(int code, int amount)
    {
        if(code==Calendar.YEAR)
        {
            Calendar cd=Calendar.getInstance();
            cd.set(year,month,date);
            cd.add(Calendar.YEAR,amount);
            return  cd;
        }
        else if(code==Calendar.MONTH)
        {
            Calendar cd=Calendar.getInstance();
            cd.set(year,month,date);
            cd.add(Calendar.MONTH,amount);
            return  cd;
        }
        else if(code==Calendar.DATE)
        {
            Calendar cd=Calendar.getInstance();
            cd.set(year,month,date);
            cd.add(Calendar.DATE,amount);
            return  cd;
        }
        return  null;
    }
    @Nullable
    private  Calendar add(int code, int amount, Calendar dateFrom)
    {
        if(code==Calendar.YEAR)
        {
            Calendar cd=Calendar.getInstance();
            cd.set(dateFrom.get(Calendar.YEAR),dateFrom.get(Calendar.MONTH),dateFrom.get(Calendar.DATE));
            cd.add(Calendar.YEAR,amount);
            return  cd;
        }
        else if(code==Calendar.MONTH)
        {
            Calendar cd=Calendar.getInstance();
            cd.set(dateFrom.get(Calendar.YEAR),dateFrom.get(Calendar.MONTH),dateFrom.get(Calendar.DATE));
            cd.add(Calendar.MONTH,amount);
            return  cd;
        }
        else if(code==Calendar.DATE)
        {
            Calendar cd=Calendar.getInstance();
            cd.set(dateFrom.get(Calendar.YEAR),dateFrom.get(Calendar.MONTH),dateFrom.get(Calendar.DATE));
            cd.add(Calendar.DATE,amount);
            return  cd;
        }
        return  null;
    }
    private int getAgeAsYear()
    {

        Calendar dob = Calendar.getInstance();
        Calendar today = Calendar.getInstance();

        dob.set(year, month, date);

        int age = today.get(Calendar.YEAR) - dob.get(Calendar.YEAR);

        if (today.get(Calendar.DAY_OF_YEAR) < dob.get(Calendar.DAY_OF_YEAR)){
            age--;
        }



        return age;

    }
     private int getAgeAsMonth()
    {

        Calendar dob = Calendar.getInstance();
        Calendar today = Calendar.getInstance();

        dob.set(year, month, date);

        int age = today.get(Calendar.MONTH) - dob.get(Calendar.MONTH);





        return age;

    }

}
