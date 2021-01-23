package com.everis.bootcamp.sync

import android.content.Context
import com.everis.bootcamp.utils.PreferencesUtils


class DrinkWaterReminderTask {
    companion object {
        const val ACTION_INCREMENT_WATER_COUNT = "action_increment_water_count"
        //TODO: 001 - Crie uma constante do tipo string chamada ACTION_CHARGING_REMINDER

        //TODO: 002 - Crie uma função chamada incrementChargingReminder que receba como parametro o context
        // - esta função deve chamar PreferencesUtils.incrementChargingReminderCount
        // - esta função deve chamar remindUserBecauseCharging


        //TODO: 003 - Inclua nesta função a chamada para clearAllNotifications para limpar a notificação se usuário tomar agua
        private fun incrementWaterCount(context: Context) = PreferencesUtils.incrementWaterCount(context)


        fun executeTask(context: Context, action: String?) {
            if (ACTION_INCREMENT_WATER_COUNT == action){
                incrementWaterCount(context)
            }
            //TODO: 004 - acrescente um else na função para chamar incrementChargingReminder quando a action for igual a ACTION_CHARGING_REMINDER
        }
    }
}


