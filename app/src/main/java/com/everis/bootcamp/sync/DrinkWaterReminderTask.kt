package com.everis.bootcamp.sync

import android.content.Context
import com.everis.bootcamp.utils.PreferencesUtils
import com.everis.bootcamp.utils.clearAllNotifications
import com.everis.bootcamp.utils.remindUserBecauseCharging

class DrinkWaterReminderTask {
    companion object {
        const val ACTION_INCREMENT_WATER_COUNT = "action_increment_water_count"
        const val ACTION_CHARGING_REMINDER = "action_charging_reminder"
        //TODO: 001 - Criar uma constante do tipo string chamada ACTION_STRETCHING_REMINDER para representar a action do contador de alongamento

        fun incrementChargingReminder(context: Context) {
            PreferencesUtils.incrementChargingReminderCount(context)
            remindUserBecauseCharging(context)
        }

        private fun incrementWaterCount(context: Context) {
            PreferencesUtils.incrementWaterCount(context)
            clearAllNotifications(context)
        }

        //TODO: 002 - Criar função privada chamada incrementStretchingReminder para incrementar o contador de alongamentos com PreferencesUtils.incrementStretchingReminderCount
        //A função deve receber um context como parametro


        fun executeTask(context: Context, action: String?) {
            if (ACTION_INCREMENT_WATER_COUNT == action){
                incrementWaterCount(context)
            } else if (ACTION_CHARGING_REMINDER == action) {
                incrementChargingReminder(context)
            }
            //TODO: 003 - Alterar este if e acrescentar a condição para a action for igual a ACTION_STRETCHING_REMINDER chamar a função incrementStretchingReminder
        }
    }
}


