package com.rongchut.shuvo.shasthokothon.Starting.Vaccine;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;

import com.rongchut.shuvo.shasthokothon.R;
import com.rongchut.shuvo.shasthokothon.Starting.Database.DataBaseOpenHelper;

import java.util.ArrayList;
import java.util.Calendar;

public class VaccineReceiver extends BroadcastReceiver {

    private static int notificationId=0;
    private static int id=0;
    private String name="";
    public VaccineReceiver() {
    }


    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.



        if(id!=intent.getIntExtra("ID",-1))
        {
            notificationId++;
            id=intent.getIntExtra("ID",-1);
        }

        if(isOkay(context,intent))
        {


            Intent myIntent=new Intent(context,DetailsActivity.class);
            myIntent.putExtra("ID",String.valueOf(intent.getIntExtra("ID",-1)));
            PendingIntent pendingIntent=PendingIntent.getActivity(context,id,myIntent,PendingIntent.FLAG_ONE_SHOT);
            String text=getText(intent);
            NotificationCompat.Builder mBuilder =
                    new NotificationCompat.Builder(context)
                            .setContentIntent(pendingIntent)
                            .setSmallIcon(R.mipmap.vaccine)
                            .setContentTitle("টিকা")
                            .setAutoCancel(true)
                            .setStyle(new NotificationCompat.BigTextStyle().bigText(name+" এর "+text+" টিকা দেওয়ার সময় হয়েছে।"))
                            .setPriority(Notification.PRIORITY_MAX);
            Uri uri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            mBuilder.setSound(uri);

            NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            mNotificationManager.notify(notificationId, mBuilder.build());
        }




    }
    String getText(Intent intent)
    {
        String text="";

        if(intent.getStringExtra("BCG")!=null)
         {
            text+="বি.সি.জি ";
         }
        if(intent.getStringExtra("DPT")!=null)
         {
            text+=",ডি.পি.টি ";
         }
        if(intent.getStringExtra("POLIO")!=null)
        {
            text+=",পোলিও ";
        }
        if(intent.getStringExtra("HAM")!=null)
        {
            text+=",হাম ";
        }
        if(intent.getStringExtra("HEPAB")!=null)
        {
            text+=",হেপাটাইটিস বি ";
        }
        if(intent.getStringExtra("HPV")!=null)
        {
            text+=",জরায়ু ক্যান্সার(H.P.V) ";
        }
        return text;

    }
    private boolean isOkay(Context context,Intent intent)
    {
        int patientID=intent.getIntExtra("ID",0);
        DataBaseOpenHelper db=new DataBaseOpenHelper(context,DataBaseOpenHelper.VaccinePatientDetails);
        PatientData pd=db.getAll(String.valueOf(patientID));
        if(pd==null)
        {
            return false;
        }
        name=pd.getName();
        ArrayList<VaccineState> state=pd.getVaccineData().getlist();

        if(intent.getStringExtra("BCG")!=null)
        {
            for(int i=0;i<state.size();i++)
            {
                if((state.get(i).getVaccineName().compareTo("বি.সি.জি.")==0)&&(state.get(i).getVaccineState().compareTo("false")==0))
                {
                    return true;
                }

            }

        }
        if(intent.getStringExtra("DPT")!=null)
        {
            for(int i=0;i<state.size();i++)
            {
                if((state.get(i).getVaccineName().compareTo("ডি.পি.টি.")==0)&&(state.get(i).getVaccineState().compareTo("false")==0))
                {
                    return true;
                }

            }

        }
        if(intent.getStringExtra("POLIO")!=null)
        {
            for(int i=0;i<state.size();i++)
            {
                if((state.get(i).getVaccineName().compareTo("পোলিও")==0)&&(state.get(i).getVaccineState().compareTo("false")==0))
                {
                    return true;
                }

            }

        }
        if(intent.getStringExtra("HAM")!=null)
        {
            for(int i=0;i<state.size();i++)
            {
                if((state.get(i).getVaccineName().compareTo("হাম")==0)&&(state.get(i).getVaccineState().compareTo("false")==0))
                {
                    return true;
                }

            }

        }
        if(intent.getStringExtra("HEPAB")!=null)
        {
            for(int i=0;i<state.size();i++)
            {
                if((state.get(i).getVaccineName().compareTo("হেপাটাইটিস বি")==0)&&(state.get(i).getVaccineState().compareTo("false")==0))
                {
                    return true;
                }

            }

        }
        if(intent.getStringExtra("HPV")!=null)
        {
            for(int i=0;i<state.size();i++)
            {
                if((state.get(i).getVaccineName().compareTo("এইচ.পি.ভি")==0)&&(state.get(i).getVaccineState().compareTo("false")==0))
                {
                    return true;
                }

            }

        }
        return false;


    }
}
