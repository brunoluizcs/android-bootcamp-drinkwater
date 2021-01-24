package com.everis.bootcamp.drinkwater

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.preference.PreferenceManager
import androidx.appcompat.app.AppCompatActivity
import com.everis.bootcamp.sync.DrinkWaterReminderIntentService
import com.everis.bootcamp.sync.DrinkWaterReminderTask
import com.everis.bootcamp.sync.schedulerChargingReminder
import com.everis.bootcamp.utils.PreferencesUtils
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(),
    SharedPreferences.OnSharedPreferenceChangeListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        updateWaterCount()
        updateChargingReminderCount()

        schedulerChargingReminder(this)

        imageview_cup_icon.setOnClickListener {
            incrementWaterHandler()
        }

        val prefs = PreferenceManager.getDefaultSharedPreferences(this)
        prefs.registerOnSharedPreferenceChangeListener(this)

        //TODO 009 - Faça a chamada para o método registerMainBroadcastReceiver
    }

    fun updateWaterCount() {
        val count = PreferencesUtils.getWaterCount(this)
        textview_quantity.text = "$count"
    }

    fun updateChargingReminderCount() {
        val count = PreferencesUtils.getChargingReminderCount(this)
        val message = getString(R.string.charge_notification_count, count)
        textview_charging_reminder.text = message
    }

    fun incrementWaterHandler() {
        val intent = Intent(this, DrinkWaterReminderIntentService::class.java)
        intent.action = DrinkWaterReminderTask.ACTION_INCREMENT_WATER_COUNT
        startService(intent)
    }

    //TODO: 001 - Crie uma função chamada showPowerIndicator para mostrar o indicador de bateria

    //TODO: 002 - Crie uma função chamada hidePowerIndicator para esconder o indicador de bateria

    /*TODO: 006 - Crie uma função chamada registerMainBroadcastReceiver
     * - Esta função deve criar criar um IntentFilter para as actions Intent.ACTION_POWER_CONNECTED e Intent.ACTION_POWER_DISCONNECTED\
     * - Chame o método da Activity chamado registerReceiver para registrar o broadcast receiver
     */

    /*TODO: 008 - Crie uma função chamada unregisterMainBroadcastReceiver
     * - Esta função deve chamar o método da activity unRegisterReceiver
     */


    override fun onDestroy() {
        super.onDestroy()
        val prefs = PreferenceManager.getDefaultSharedPreferences(this)
        prefs.unregisterOnSharedPreferenceChangeListener(this)

        //TODO: 010 - Faça a chamada para o método unregisterMainBroadcastReceiver
    }

    override fun onSharedPreferenceChanged(sharedPreferences: SharedPreferences?, key: String?) {
        if (PreferencesUtils.KEY_WATER_COUNT == key) {
            updateWaterCount()
        } else if (PreferencesUtils.KEY_CHARGING_REMINDER_COUNT == key) {
            updateChargingReminderCount()
        }
    }

    //TODO: 003 - Crie uma classe interna chamada MainBroadcastReceiver esta classe deve herdar de BroadcastReceiver

    //TODO: 004 - Sobrescreva o metodo onReceive

    /*TODO: 005 - No método onReceive verifique faça as verificações:
     * - Se a action for igual Intent.ACTION_POWER_CONNECTED chame o método showPowerIndicator
     * - Se a action for igual Intent.ACTION_POWER_DISCONNECTED chame o método hidePowerIndicator
     */
}
