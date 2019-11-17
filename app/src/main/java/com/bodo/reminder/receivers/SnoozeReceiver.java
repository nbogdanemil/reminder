package com.bodo.reminder.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.bodo.reminder.database.Database;
import com.bodo.reminder.models.Notification;
import com.bodo.reminder.utils.NotificationUtil;

public class SnoozeReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Database database = new Database(context.getApplicationContext());
        Notification notification = database.getNotification(intent.getIntExtra("NOTIFICATION_ID", 0));
        NotificationUtil.createNotification(context, notification);
        database.close();
    }
}