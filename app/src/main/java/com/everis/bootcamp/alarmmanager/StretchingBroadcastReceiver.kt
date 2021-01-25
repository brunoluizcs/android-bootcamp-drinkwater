package com.everis.bootcamp.alarmmanager

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.everis.bootcamp.sync.DrinkWaterReminderIntentService
import com.everis.bootcamp.sync.DrinkWaterReminderTask
import com.everis.bootcamp.utils.reminderUserToStretch

class StretchingBroadcastReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        context?.let { reminderUserToStretch(it) }

        val myIntent = Intent(context, DrinkWaterReminderIntentService::class.java)
        myIntent.action = DrinkWaterReminderTask.ACTION_STRETCHING_REMINDER
        context?.startService(myIntent)
    }
}