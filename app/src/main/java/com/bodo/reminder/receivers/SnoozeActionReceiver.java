package com.bodo.reminder.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.bodo.reminder.R;
import com.bodo.reminder.utils.AlarmUtil;
import com.bodo.reminder.utils.NotificationUtil;

import java.util.Calendar;

public class SnoozeActionReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        int notificationId = intent.getIntExtra("NOTIFICATION_ID", 0);
        NotificationUtil.cancelNotification(context, notificationId);

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        int defaultMinutes = context.getResources().getInteger(R.integer.default_snooze_minutes);
        int snoozeLength = sharedPreferences.getInt("snoozeLength", defaultMinutes);
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MINUTE, snoozeLength);

        Intent alarmIntent = new Intent(context, SnoozeReceiver.class);
        AlarmUtil.setAlarm(context, alarmIntent, notificationId, calendar);
    }
}