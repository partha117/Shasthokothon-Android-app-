package com.rongchut.shuvo.shasthokothon.Starting.Medicine;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import com.rongchut.shuvo.shasthokothon.Starting.Vaccine.VaccineReceiver;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

/**
 * Created by ASUS on 05-Jan-17.
 */

public class SetNotification {


    private  String startDate;
    private  String endDate;
    private  String timeMorning;
    private  String timeNoon;
    private  String timeNight;
    private Context context;
    private  int id;
    private  int[] quantity;

    public SetNotification(Context context, String endDate, int id, int[] quantity, String startDate, String timeMorning, String timeNight, String timeNoon) {
        this.context = context;
        this.endDate = endDate;
        this.id = id;
        this.quantity = quantity;
        this.startDate = startDate;
        this.timeMorning = timeMorning;
        this.timeNight = timeNight;
        this.timeNoon = timeNoon;
    }

    public  void setNotification()
    {
        SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat timeFormatter=new SimpleDateFormat("hh:mm a");
        Date tempStart = null;



        AlarmManager alarmManager=(AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
        try {
            tempStart = dateFormatter.parse(startDate);
            Date tempEnd= dateFormatter.parse(endDate);
            Calendar start = Calendar.getInstance();
            start.setTime(tempStart);
            Calendar end = Calendar.getInstance();
            end.setTime(tempEnd);


            Calendar morning=Calendar.getInstance();
            Calendar noon=Calendar.getInstance();
            Calendar night=Calendar.getInstance();



            Date tempTime=timeFormatter.parse(timeMorning);
            morning.setTime(tempTime);
            tempTime=timeFormatter.parse(timeNoon);
            noon.setTime(tempTime);
            tempTime=timeFormatter.parse(timeNight);
            night.setTime(tempTime);





            for (; !start.after(end); start.add(Calendar.DATE, 1))
            {
                Intent intent=new Intent(context,MedicineReceiver.class);
                intent.putExtra("ID",id);
                intent.putExtra("QUANTITY_MORNING",quantity[0]);
                intent.putExtra("QUANTITY_NOON",quantity[1]);
                intent.putExtra("QUANTITY_NIGHT",quantity[2]);

                morning.set(start.get(Calendar.YEAR),start.get(Calendar.MONTH),start.get(Calendar.DATE));
               // intent=getIntent();
                intent.putExtra("MORNING","MORNING");
                PendingIntent pendingIntent=PendingIntent.getBroadcast(context, UUID.randomUUID().hashCode(),intent,PendingIntent.FLAG_ONE_SHOT);
                alarmManager.set(AlarmManager.RTC_WAKEUP,morning.getTimeInMillis()+345,pendingIntent);


                intent=new Intent(context,MedicineReceiver.class);
                intent.putExtra("ID",id);
                intent.putExtra("QUANTITY_MORNING",quantity[0]);
                intent.putExtra("QUANTITY_NOON",quantity[1]);
                intent.putExtra("QUANTITY_NIGHT",quantity[2]);

                noon.set(start.get(Calendar.YEAR),start.get(Calendar.MONTH),start.get(Calendar.DATE));
                //intent=getIntent();
                intent.putExtra("NOON","NOON");
                pendingIntent=PendingIntent.getBroadcast(context,UUID.randomUUID().hashCode(),intent,PendingIntent.FLAG_ONE_SHOT);
                alarmManager.set(AlarmManager.RTC_WAKEUP,noon.getTimeInMillis()+450,pendingIntent);


                intent=new Intent(context,MedicineReceiver.class);
                intent.putExtra("ID",id);
                intent.putExtra("QUANTITY_MORNING",quantity[0]);
                intent.putExtra("QUANTITY_NOON",quantity[1]);
                intent.putExtra("QUANTITY_NIGHT",quantity[2]);

                night.set(start.get(Calendar.YEAR),start.get(Calendar.MONTH),start.get(Calendar.DATE));
                //intent=getIntent();
                intent.putExtra("NIGHT","NIGHT");
                pendingIntent=PendingIntent.getBroadcast(context,UUID.randomUUID().hashCode(),intent,PendingIntent.FLAG_ONE_SHOT);
                alarmManager.set(AlarmManager.RTC_WAKEUP,night.getTimeInMillis()+700,pendingIntent);

            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }
    Intent getIntent()
    {
        Intent intent=new Intent(context,MedicineReceiver.class);
        intent.putExtra("ID",id);
        intent.putExtra("QUANTITY_MORNING",quantity[0]);
        intent.putExtra("QUANTITY_NOON",quantity[1]);
        intent.putExtra("QUANTITY_NIGHT",quantity[2]);
        return intent;
    }
}
