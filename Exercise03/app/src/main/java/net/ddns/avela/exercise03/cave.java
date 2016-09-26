package net.ddns.avela.exercise03;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class cave extends AppCompatActivity {
    private static final int MY_NOTIFICATION_ID = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cave);
        Toast toast0 = Toast.makeText(this,"RAWR, you hear", Toast.LENGTH_SHORT);
        toast0.setGravity(Gravity.TOP,0,50);
        toast0.show();

        Toast toast1 = Toast.makeText(this, "Thunderous.", Toast.LENGTH_SHORT);
        toast1.setGravity(Gravity.TOP, 0, 150);
        toast1.show();
        Handler handler0 = new Handler();
        handler0.postDelayed(new Runnable() {
            @Override
            public void run() {
                String text0 = ".. You keep seeking for hidden paths ..";
                TextView tv = (TextView) findViewById(R.id.txt1);
                tv.setText(text0);
            }
        }, 5000);

        Handler handler1 = new Handler();
        handler1.postDelayed(new Runnable() {
            @Override
            public void run() {
                String text = ".. You just seek and seek ..";
                TextView tv = (TextView) findViewById(R.id.txt2);
                tv.setText(text);
            }
        }, 7000);
        Handler handler2 = new Handler();
        handler2.postDelayed(new Runnable() {
            @Override
            public void run() {
                String text = ".. Without a trace of dragons";
                TextView tv = (TextView) findViewById(R.id.txt3);
                tv.setText(text);
            }
        }, 11000);

        Handler handler3 = new Handler();
        handler3.postDelayed(new Runnable() {
            @Override
            public void run() {
                launchPrivateNotification();
            }
        }, 15000);



    }

    public void launchPrivateNotification() {
        Intent contentIntent = new Intent(this, end.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,0,contentIntent,0);

        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        Notification notification = new Notification.Builder(this)
                .setCategory(Notification.CATEGORY_PROMO)
                .setContentTitle("Dragon seen!")
                .setContentText("RAWR heard from a hidden pathway")
                .setContentIntent(pendingIntent)
                .setSmallIcon(R.drawable.notification)
                .setAutoCancel(true)
                .setVisibility(Notification.VISIBILITY_PUBLIC)
                .setPriority(Notification.PRIORITY_HIGH)
                .setVibrate(new long[]{1000, 1000, 1000, 1000, 1000}).build();

        notificationManager.notify(MY_NOTIFICATION_ID, notification);



    }

}
