package com.everis.bootcamp.utils

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import com.everis.bootcamp.drinkwater.R
import com.everis.bootcamp.drinkwater.StretchingActivity

private val WATER_REMINDER_NOTIFICATION_ID = 1138
private val WATER_REMINDER_NOTIFICATION_CHANNEL_ID = "reminder_notification_channel"
private val STRETHCING_REMINDER_NOTIFICATION_CHANNEL_ID = "stretching_reminder_notification_channel"
private val STRETCHING_REMINDER_REQUEST_CODE = 1
private val STRETCHING_NOTIFICATION_ID = 1139

fun clearAllNotifications(context: Context) {
    val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
    notificationManager.cancelAll()
}

fun reminderUserToStretch(context: Context) {
    val notificationManager = NotificationManagerCompat.from(context)

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        val mChannel = NotificationChannel(
            STRETHCING_REMINDER_NOTIFICATION_CHANNEL_ID,
            context.getString(R.string.main_notification_channel_name),
            NotificationManager.IMPORTANCE_HIGH
        )
        notificationManager.createNotificationChannel(mChannel)
    }

    val stretchingNotification = NotificationCompat.Builder(context, STRETHCING_REMINDER_NOTIFICATION_CHANNEL_ID)

    stretchingNotification.setContentTitle(context.getString(R.string.stretching_reminder_title))
    stretchingNotification.setContentText(context.getText(R.string.stretching_reminder_body))
    stretchingNotification.setSmallIcon(R.drawable.ic_drink_notification)

    val intent = Intent(context, StretchingActivity::class.java)
    val pendingIntent = PendingIntent.getActivity(context, STRETCHING_REMINDER_REQUEST_CODE, intent, 0)
    stretchingNotification.setContentIntent(pendingIntent)

    stretchingNotification.setAutoCancel(true)

    notificationManager.notify(STRETCHING_NOTIFICATION_ID, stretchingNotification.build())
}

fun remindUserBecauseCharging(context: Context) {
    val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        val mChannel = NotificationChannel(
            WATER_REMINDER_NOTIFICATION_CHANNEL_ID,
            context.getString(R.string.main_notification_channel_name),
            NotificationManager.IMPORTANCE_HIGH
        )
        notificationManager.createNotificationChannel(mChannel)
    }
    val notificationBuilder = NotificationCompat.Builder(context, WATER_REMINDER_NOTIFICATION_CHANNEL_ID)
        .setColor(ContextCompat.getColor(context, R.color.colorPrimary))
        .setSmallIcon(R.drawable.ic_drink_notification)
        .setLargeIcon(largeIcon(context))
        .setContentTitle(context.getString(R.string.charging_reminder_notification_title))
        .setContentText(context.getString(R.string.charging_reminder_notification_body))
        .setStyle(
            NotificationCompat.BigTextStyle().bigText(
                context.getString(R.string.charging_reminder_notification_body)
            )
        )
        .setDefaults(Notification.DEFAULT_VIBRATE)
        .setAutoCancel(true)

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN
        && Build.VERSION.SDK_INT < Build.VERSION_CODES.O
    ) {
        notificationBuilder.priority = NotificationCompat.PRIORITY_HIGH
    }
    notificationManager.notify(WATER_REMINDER_NOTIFICATION_ID, notificationBuilder.build())
}

private fun largeIcon(context: Context): Bitmap? {
    val res: Resources = context.resources
    return BitmapFactory.decodeResource(res, R.drawable.ic_drink_notification)
}
