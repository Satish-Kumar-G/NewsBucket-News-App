package com.dcoders.satishkumar.g.newsbucket;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v4.media.app.NotificationCompat;

public class AlarmReceiver extends BroadcastReceiver {
    private static final int NOTIFICATION_ID=0;
    private static final String NOTIFICATION_CHANNEL="notification_channel";
    private static final String NOTIFICATION_TYPE="primary";
    public AlarmReceiver()
    {

    }
    @Override
    public void onReceive(Context context, Intent intent) {
        NotificationManager notificationManager= (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        Intent intent1=new Intent(context,MainActivity.class);
        PendingIntent pendingIntent=PendingIntent.getActivity(context,NOTIFICATION_ID,intent1,PendingIntent.FLAG_UPDATE_CURRENT);
        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.O)
        {
            NotificationChannel channel=new NotificationChannel(NOTIFICATION_CHANNEL,NOTIFICATION_TYPE,NotificationManager.IMPORTANCE_HIGH);
            notificationManager.createNotificationChannel(channel);
        }

        android.support.v4.app.NotificationCompat.Builder builder=new
                android.support.v4.app.NotificationCompat.Builder(context,NOTIFICATION_CHANNEL)
                .setColor(ContextCompat.getColor(context,R.color.colorPrimary))
                .setSmallIcon(R.drawable.ic_alarm_black_24dp)
                .setContentTitle("Its Time to Read News")
                .setContentText("Tap Notification to Read News")
                .setDefaults(Notification.DEFAULT_VIBRATE)
                .setContentIntent(contentIntent(context))
                .addAction(takemeToApp(context))
                .setAutoCancel(true);
        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.O&&Build.VERSION.SDK_INT<Build.VERSION_CODES.O)
        {
          builder.setPriority(android.support.v4.app.NotificationCompat.PRIORITY_HIGH);
        }
        notificationManager.notify(NOTIFICATION_ID,builder.build());

    }
    private android.support.v4.app.NotificationCompat.Action takemeToApp(Context context)
    {
        Intent intent=new Intent(context,MainActivity.class);
        PendingIntent pendingIntent=PendingIntent.getActivity(context,0,intent,PendingIntent.FLAG_UPDATE_CURRENT);
        android.support.v4.app.NotificationCompat.Action action=new
                android.support.v4.app.NotificationCompat.Action(R.drawable.ic_check_circle_black_24dp,
                "Take Me to App",pendingIntent);
        return action;
    }
    public PendingIntent contentIntent(Context context)
    {
        Intent intent=new Intent(context,MainActivity.class);
        return PendingIntent.getActivity(context,0,intent,PendingIntent.FLAG_UPDATE_CURRENT);
    }
}
