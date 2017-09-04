package com.rongchut.shuvo.shasthokothon.Starting.Medicine.FurtherInteraction;

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
import com.rongchut.shuvo.shasthokothon.Starting.DrugReaction.AddFeedback.AddFeedbackActivity;
import com.rongchut.shuvo.shasthokothon.Starting.Medicine.Medicine_ItemActivity;

public class InteractionReceiver extends BroadcastReceiver {
    public InteractionReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
       // throw new UnsupportedOperationException("Not yet implemented");
        String name=intent.getStringExtra("NAME");
        if(name==null)
        {
            name="";
        }

        Intent myIntent=new Intent(context,AddFeedbackActivity.class);
        myIntent.putExtra("ID",intent.getIntExtra("ID",-1));
        PendingIntent pendingIntent=PendingIntent.getActivity(context,124,myIntent,PendingIntent.FLAG_ONE_SHOT);

        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(context)
                        .setSmallIcon(R.mipmap.medicine)
                        .setContentTitle("ঔষধ ")
                        .setAutoCancel(true)
                        .setLights(Color.RED, 3000, 3000)
                        .setVibrate(new long[]{1000,1000,1000})
                        .setContentIntent(pendingIntent)
                        .setStyle(new NotificationCompat.BigTextStyle().bigText("আপনি কিছুদিন আগে "+name+" \nঔষধটি গ্রহন করেছিলেন \nআপনি কি সুস্থ হয়েছেন?"))
                        .setPriority(Notification.PRIORITY_MAX);

                Uri uri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
                mBuilder.setSound(uri);




        NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(123, mBuilder.build());
    }
}
