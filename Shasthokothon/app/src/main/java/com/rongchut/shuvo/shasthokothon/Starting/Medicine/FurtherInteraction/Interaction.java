package com.rongchut.shuvo.shasthokothon.Starting.Medicine.FurtherInteraction;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import com.rongchut.shuvo.shasthokothon.Starting.DrugReaction.AddFeedback.AddFeedbackActivity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by ASUS on 09-Jan-17.
 */

public class Interaction {

    private  String  startDate;
    private  String endDate;
    private Context context;
    private  String  name;

    public Interaction(Context context, String endDate, String name, String startDate) {
        this.context = context;
        this.endDate = endDate;
        this.name = name;
        this.startDate = startDate;
    }

    public void interact()
    {
        SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy");

        try {
            Date tempEnd=dateFormatter.parse(endDate);
            Date tempStart=dateFormatter.parse(startDate);
            Calendar start=Calendar.getInstance();
            Calendar end=Calendar.getInstance();
            start.setTime(tempStart);
            end.setTime(tempEnd);
            Calendar start1=Calendar.getInstance();
            Calendar start2=Calendar.getInstance();
            Calendar start3=Calendar.getInstance();
            start1.set(Calendar.DATE,3);
            start2.set(Calendar.DATE,7);
            start3.set(Calendar.DATE,30);
            AlarmManager alarmManager=(AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
            if((!end.before(start1))&&(!end.after(start2)))
            {
                end.add(Calendar.DATE,3);
                Intent intent=new Intent(context, InteractionReceiver.class);
                intent.putExtra("NAME",name);
                PendingIntent pendingIntent=PendingIntent.getBroadcast(context,122,intent,PendingIntent.FLAG_ONE_SHOT);
                alarmManager.set(AlarmManager.RTC_WAKEUP,end.getTimeInMillis()+400,pendingIntent);
            }
            else if(end.after(start2))
            {
                end.add(Calendar.DATE,7);
                Intent intent=new Intent(context, InteractionReceiver.class);
                intent.putExtra("NAME",name);
                PendingIntent pendingIntent=PendingIntent.getBroadcast(context,122,intent,PendingIntent.FLAG_ONE_SHOT);
                alarmManager.set(AlarmManager.RTC_WAKEUP,end.getTimeInMillis()+600,pendingIntent);
            }
            else if(!end.before(start3))
            {

                Intent intent=new Intent(context, InteractionReceiver.class);
                intent.putExtra("NAME",name);
                PendingIntent pendingIntent=PendingIntent.getBroadcast(context,122,intent,PendingIntent.FLAG_ONE_SHOT);
                alarmManager.set(AlarmManager.RTC_WAKEUP,start3.getTimeInMillis()+800,pendingIntent);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }
}
