package com.everis.bootcamp.sync

import android.content.Context
import com.everis.bootcamp.utils.PreferencesUtils


class DrinkWaterReminderTask {
    companion object {
        const val ACTION_INCREMENT_WATER_COUNT = "action_increment_water_count"

        private fun incrementWaterCount(context: Context) = PreferencesUtils.incrementWaterCount(context)

        fun executeTask(context: Context, action: String?) {
            if (ACTION_INCREMENT_WATER_COUNT == action){
                incrementWaterCount(context)
            }
        }
    }
}


