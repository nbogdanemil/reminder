package com.bodo.reminder.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.bodo.reminder.enums.NotificationsType;
import com.bodo.reminder.utils.AlarmUtil;
import com.bodo.reminder.database.Database;
import com.bodo.reminder.models.Notification;
import com.bodo.reminder.utils.DateAndTimeUtil;

import java.util.Calendar;
import java.util.List;

public class BootReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Database database = new Database(context.getApplicationContext());
        List<Notification> notificationList = database.getNotificationList(NotificationsType.ACTIVE);
        database.close();
        Intent alarmIntent = new Intent(context, AlarmReceiver.class);

        for (Notification notification : notificationList) {
            Calendar calendar = DateAndTimeUtil.parseDateAndTime(notification.getDateAndTime());
            calendar.set(Calendar.SECOND, 0);
            AlarmUtil.setAlarm(context, alarmIntent, notification.getId(), calendar);
        }
    }
}