package com.everis.bootcamp.utils

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import com.everis.bootcamp.drinkwater.R


private const val WATER_REMINDER_NOTIFICATION_ID = 1138
private const val WATER_REMINDER_NOTIFICATION_CHANNEL_ID = "reminder_notification_channel"

fun clearAllNotifications(context: Context) {
    val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
    notificationManager.cancelAll()
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
