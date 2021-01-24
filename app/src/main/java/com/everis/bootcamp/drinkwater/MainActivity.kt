package com.everis.bootcamp.drinkwater

import android.content.*
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.everis.bootcamp.sync.DrinkWaterReminderIntentService
import com.everis.bootcamp.sync.DrinkWaterReminderTask
import com.everis.bootcamp.sync.schedulerChargingReminder
import com.everis.bootcamp.utils.PreferencesUtils
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(),
    SharedPreferences.OnSharedPreferenceChangeListener {

    private val receiver = MainBroadcastReceiver()

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

        registerMainBroadcastReceiver()
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


    fun showPowerIndicator() {
        imageview_battery.visibility = View.VISIBLE
    }

    fun hidePowerIndicator() {
        imageview_battery.visibility = View.GONE
    }

    fun registerMainBroadcastReceiver() {
        val intentFilter = IntentFilter(Intent.ACTION_POWER_CONNECTED).apply {
            this.addAction(Intent.ACTION_POWER_DISCONNECTED)
        }
        registerReceiver(receiver, intentFilter)
    }


    fun unregisterMainBroadcastReceiver() {
        unregisterReceiver(receiver)
    }

    override fun onDestroy() {
        super.onDestroy()
        val prefs = PreferenceManager.getDefaultSharedPreferences(this)
        prefs.unregisterOnSharedPreferenceChangeListener(this)
        unregisterMainBroadcastReceiver()
    }

    override fun onSharedPreferenceChanged(sharedPreferences: SharedPreferences?, key: String?) {
        if (PreferencesUtils.KEY_WATER_COUNT == key) {
            updateWaterCount()
        } else if (PreferencesUtils.KEY_CHARGING_REMINDER_COUNT == key) {
            updateChargingReminderCount()
        }
    }

    inner class MainBroadcastReceiver : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            if  (intent?.action == Intent.ACTION_POWER_CONNECTED) {
                showPowerIndicator()
            } else if (intent?.action == Intent.ACTION_POWER_DISCONNECTED) {
                hidePowerIndicator()
            }
        }
    }
}
