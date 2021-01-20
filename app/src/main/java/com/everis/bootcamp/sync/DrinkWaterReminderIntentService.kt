package com.everis.bootcamp.sync

import android.app.IntentService
import android.content.Intent


class DrinkWaterReminderIntentService : IntentService("DrinkWaterReminderIntentService"){

    override fun onHandleIntent(intent: Intent?) {
        val action = intent?.action
        DrinkWaterReminderTask.executeTask(this, action)
    }
}