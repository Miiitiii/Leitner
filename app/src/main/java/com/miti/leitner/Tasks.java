package com.miti.leitner;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class Tasks extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals("android.intent.action.BOOT_COMPLETED")) {
            MainActivity.setRecurringAlarm(context);
        }

    }
}
