package com.dcoders.satishkumar.g.newsbucket;

import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

public class AlarmActivity extends AppCompatActivity {
    NotificationManager notificationManager;
    public static final int NOTIFICATION_ID=0;
    Intent intent;
    PendingIntent pendingIntent;
    AlarmManager alarmManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);
        alarmManager= (AlarmManager) getSystemService(ALARM_SERVICE);
        notificationManager= (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        intent=new Intent(this,AlarmReceiver.class);
        pendingIntent=PendingIntent.getBroadcast(this,NOTIFICATION_ID,intent,PendingIntent.FLAG_UPDATE_CURRENT);
    }
    public void setAlarm(View view)
    {
        long triggerTime= SystemClock.elapsedRealtime()+3600*1000;
        long repeatPeriod=3600*1000;
        alarmManager.setInexactRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP,triggerTime,repeatPeriod,pendingIntent);


    }
    public void cancelAlarm(View view)
    {
        alarmManager.cancel(pendingIntent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()==android.R.id.home)
        {
            finish();
        }
        return  true;
    }
}
