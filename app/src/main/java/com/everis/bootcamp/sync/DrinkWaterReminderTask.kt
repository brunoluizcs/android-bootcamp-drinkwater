package com.everis.bootcamp.sync

import android.content.Context
import com.everis.bootcamp.utils.PreferencesUtils
import com.everis.bootcamp.utils.clearAllNotifications
import com.everis.bootcamp.utils.remindUserBecauseCharging


class DrinkWaterReminderTask {
    companion object {
        const val ACTION_INCREMENT_WATER_COUNT = "action_increment_water_count"
        const val ACTION_CHARGING_REMINDER = "action_charging_reminder"

        fun incrementChargingReminder(context: Context) {
            PreferencesUtils.incrementChargingReminderCount(context)
            remindUserBecauseCharging(context)
        }

        private fun incrementWaterCount(context: Context) {
            PreferencesUtils.incrementWaterCount(context)
            clearAllNotifications(context)
        }

        fun executeTask(context: Context, action: String?) {
            if (ACTION_INCREMENT_WATER_COUNT == action){
                incrementWaterCount(context)
            } else if (ACTION_CHARGING_REMINDER == action) {
                incrementChargingReminder(context)
            }
        }
    }
}


