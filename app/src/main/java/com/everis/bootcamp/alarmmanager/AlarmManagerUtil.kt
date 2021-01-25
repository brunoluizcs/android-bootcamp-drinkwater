package com.everis.bootcamp.alarmmanager

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.IntentFilter

private const val STRETCHING_BROADCAST_REQUEST_ID = 2
private const val ACTION_DRINK_WATER_STRETCH = "com.everis.bootcamp.drinkwater.stretching"

fun startAlarmToStretch(context: Context, alarmManager: AlarmManager?) {
    val intent = Intent().apply {
        action = ACTION_DRINK_WATER_STRETCH
        addCategory("android.intent.category.DEFAULT")
    }
    val pendingIntent = PendingIntent.getBroadcast(context, STRETCHING_BROADCAST_REQUEST_ID, intent,0)
    alarmManager?.setInexactRepeating(
        AlarmManager.RTC_WAKEUP,
        System.currentTimeMillis() + 5000,
        AlarmManager.INTERVAL_FIFTEEN_MINUTES,
        pendingIntent
    )
}

fun stopAlarmToStretch(context: Context, alarmManager: AlarmManager?) {
    val intent = Intent().apply {
        action = ACTION_DRINK_WATER_STRETCH
        addCategory("android.intent.category.DEFAULT")
    }
    val pendingIntent = PendingIntent.getBroadcast(context, STRETCHING_BROADCAST_REQUEST_ID, intent,0)
    alarmManager?.cancel(pendingIntent)
}

fun registerStretchingReceiver(context: Context, receiver: StretchingBroadcastReceiver) {
    val intentFilter = IntentFilter(ACTION_DRINK_WATER_STRETCH).apply {
        addCategory("android.intent.category.DEFAULT")
    }
    context.registerReceiver(receiver,intentFilter)
}

fun unregisterStretchingReceiver(context: Context, receiver: StretchingBroadcastReceiver) {
    context.unregisterReceiver(receiver)
}