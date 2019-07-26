package com.miti.leitner;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;
import android.widget.Toast;

import com.miti.leitner.utils.utils;

import java.util.Calendar;
import java.util.TimeZone;

import static com.miti.leitner.utils.utils.MyPREFERENCES;


public class MainActivity extends AppCompatActivity {

    SharedPreferences sp;


    Handler handler = new Handler();
    Runnable runnable = new Runnable() {
        @Override
        public void run() {

            if (utils.NAME.equals("")) {
                if (!sp.contains("name")) {
                    startActivity(new Intent(MainActivity.this, login.class));
                    finish();
                } else if (sp.contains("name")) {

                    utils.NAME = sp.getString("name", null);

                    if (sp.contains("badge"))
                        utils.badge = sp.getString("badge", null);
                    else {
                        utils.badge = "0";
                        sp.edit().putString("badge", "0").apply();
                        Log.d("checking", "fucl");
                    }

                    if (sp.contains("dark"))
                        utils.dark = sp.getString("dark", null);
                    else {
                        utils.dark = "0";
                        sp.edit().putString("dark", "0").apply();
                    }


                    if (sp.contains("words_num"))
                        utils.words_num = sp.getString("words_num", null);
                    else {
                        utils.words_num = "0";
                        sp.edit().putString("words_num", "0").apply();
                    }

                    if (sp.contains("choose"))
                        utils.choose = sp.getString("choose", null);
                    else {
                        utils.choose = "false";
                        sp.edit().putString("choose", "false").apply();
                    }

                    if (sp.contains("quiz"))
                        utils.quiz = sp.getString("quiz", null);
                    else {
                        utils.quiz = "false";
                        sp.edit().putString("quiz", "false").apply();
                    }


                    startActivity(new Intent(MainActivity.this, main.class));
                    finish();
                }
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        sp = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        handler.postDelayed(runnable, 2000);

        setRecurringAlarm(MainActivity.this);

    }

    public static void setRecurringAlarm(Context context) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 19);
        calendar.set(Calendar.MINUTE, 43);
        calendar.set(Calendar.SECOND, 0);

        Intent intent = new Intent(context, NotificationReceiver.class);
        PendingIntent recurringDownload = PendingIntent.getBroadcast(context,
                110, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        AlarmManager alarms = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);


        alarms.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), recurringDownload);
        alarms.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, recurringDownload);

        ComponentName receiver = new ComponentName(context, NotificationReceiver.class);

        PackageManager pm = context.getPackageManager();
        pm.setComponentEnabledSetting(receiver, PackageManager.COMPONENT_ENABLED_STATE_ENABLED, PackageManager.DONT_KILL_APP);
    }
}
