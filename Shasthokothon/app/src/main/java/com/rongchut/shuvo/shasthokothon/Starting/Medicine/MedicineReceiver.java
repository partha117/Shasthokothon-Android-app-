package com.rongchut.shuvo.shasthokothon.Starting.Medicine;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.rongchut.shuvo.shasthokothon.R;
import com.rongchut.shuvo.shasthokothon.Starting.Database.DataBase;
import com.rongchut.shuvo.shasthokothon.Starting.Vaccine.DetailsActivity;

import java.io.File;
import java.util.Timer;
import java.util.TimerTask;

public class MedicineReceiver extends BroadcastReceiver {

    private static int notificationId=0;
    private  int id;
    DataBase dataBase;

    public MedicineReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        //throw new UnsupportedOperationException("Not yet implemented");


            notificationId++;
           id=intent.getIntExtra("ID",-1);


        dataBase=new DataBase(context);
        if(dataBase.isOkay(id+""))
        {


            Intent myIntent=new Intent(context,Medicine_ItemActivity.class);
            myIntent.putExtra("ID",intent.getIntExtra("ID",-1));
            //myIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            myIntent.setAction("foo");
            Log.d("From here","foo works");
            PendingIntent pendingIntent=PendingIntent.getActivity(context,notificationId,myIntent,PendingIntent.FLAG_ONE_SHOT);

            final NotificationCompat.Builder mBuilder =
                    new NotificationCompat.Builder(context)
                            .setSmallIcon(R.mipmap.medicine)
                            .setContentTitle("ঔষধ ")
                            .setAutoCancel(true)
                            .setLights(Color.RED, 3000, 3000)
                            .setVibrate(new long[]{1000,1000,1000})
                            .setContentIntent(pendingIntent)
                            .setStyle(new NotificationCompat.BigTextStyle().bigText(createText(intent)))
                            .setPriority(Notification.PRIORITY_MAX);

            if (getBool(dataBase.getSound(id + ""))) {
                String source = dataBase.getSourcse(id + "");
                if(source.compareTo("DEFAULT")==0)
                {
                    /*Uri uri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);*/
                    Uri path = Uri.parse("android.resource://"+context.getPackageName()+"/"+ R.raw.notification);
                    mBuilder.setSound(path);
                }
                else
                {
                    mBuilder.setSound(getURI(source, context));
                }
            }

            final NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);


            new Timer().schedule(new TimerTask() {

                @Override
                public void run() {
                    mNotificationManager.notify(notificationId, mBuilder.build());

                }
            }, 1000);
        }
    }

    Uri getURI(String  st,Context context)
    {
        File ringtoneFile = new File(st); //sd-card path/songname.mp3
//Guard if user delete the file
        if(ringtoneFile.exists() && !ringtoneFile.isDirectory()) {


            ContentValues content = new ContentValues();
            content.put(MediaStore.MediaColumns.DATA, ringtoneFile.getAbsolutePath());
            content.put(MediaStore.MediaColumns.TITLE, getFileName(st));
            content.put(MediaStore.MediaColumns.SIZE, ringtoneFile.length());
            content.put(MediaStore.MediaColumns.MIME_TYPE, "audio/*");
            //  content.put(MediaStore.Audio.Media.ARTIST, "Madonna");
            content.put(MediaStore.Audio.Media.IS_RINGTONE, false);
            content.put(MediaStore.Audio.Media.IS_NOTIFICATION, true);
            content.put(MediaStore.Audio.Media.IS_ALARM, false);
            content.put(MediaStore.Audio.Media.IS_MUSIC, false);


            Uri Ringtone1 = Uri.parse(st);
            //Insert it into the database
            Log.i("TAG", "the absolute path of the file is :" +
                    ringtoneFile.getAbsolutePath());
            Uri uri = MediaStore.Audio.Media.getContentUriForPath(
                    ringtoneFile.getAbsolutePath());


            context.getContentResolver().delete(uri, MediaStore.MediaColumns.DATA + "=\"" + ringtoneFile.getAbsolutePath() + "\"",
                    null);
            Uri newUri = context.getContentResolver().insert(uri, content);
            System.out.println("uri==" + uri);
            Log.i("TAG", "the ringtone uri is :" + newUri);
            return newUri;
        }
        else
        {
            Uri path = Uri.parse("android.resource://"+context.getPackageName()+"/"+ R.raw.notification);
            return path;
        }
    }

    String getFileName(String  st)
    {
        String []pathFile=st.split("/");
        return pathFile[pathFile.length-1];
    }
    boolean getBool(String st)
    {
        if(st.compareTo("true")==0)
        {
            return  true;
        }
        else
        {
            return  false;
        }
    }
    String createText(Intent intent)
    {
        String text="আপনার "+dataBase.getName(id+"")+" ঔষধটি ";
        text+="(";
        if(intent.getStringExtra("MORNING")!=null)
        {
            text+="সকালে ";
        }
        if(intent.getStringExtra("NOON")!=null)
        {
            text+=" দুপুরে";
        }
        if(intent.getStringExtra("NIGHT")!=null)
        {
            text+=" রাতে";
        }
        text+=")খাওয়ার সময় হয়েছে।";
        return text;







    }

}
