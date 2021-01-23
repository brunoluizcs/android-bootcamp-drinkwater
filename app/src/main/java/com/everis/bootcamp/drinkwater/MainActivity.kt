package com.everis.bootcamp.drinkwater

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.preference.PreferenceManager
import androidx.appcompat.app.AppCompatActivity
import com.everis.bootcamp.sync.DrinkWaterReminderIntentService
import com.everis.bootcamp.sync.DrinkWaterReminderTask
import com.everis.bootcamp.utils.PreferencesUtils
import com.everis.bootcamp.sync.scheduleChargingReminder
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(),
    SharedPreferences.OnSharedPreferenceChangeListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        updateWaterCount()
        updateChargingReminderCount()

        scheduleChargingReminder(this)

        imageview_cup_icon.setOnClickListener {
            incrementWaterHandler()
        }

        val prefs = PreferenceManager.getDefaultSharedPreferences(this)
        prefs.registerOnSharedPreferenceChangeListener(this)
    }

    fun updateWaterCount() {
        val count = PreferencesUtils.getWaterCount(this)
        textview_quantity.text = "$count"
    }

    fun updateChargingReminderCount() {
        val count : Int = PreferencesUtils.getChargingReminderCount(this)
        val chargingFormatted = getString(R.string.charge_notification_count, count)
        textview_charging_reminder.text = chargingFormatted
    }

    fun incrementWaterHandler() {
        val intent = Intent(this, DrinkWaterReminderIntentService::class.java)
        intent.action = DrinkWaterReminderTask.ACTION_INCREMENT_WATER_COUNT
        startService(intent)
    }

    override fun onDestroy() {
        super.onDestroy()
        val prefs = PreferenceManager.getDefaultSharedPreferences(this)
        prefs.unregisterOnSharedPreferenceChangeListener(this)
    }

    override fun onSharedPreferenceChanged(sharedPreferences: SharedPreferences?, key: String?) {
        if (PreferencesUtils.KEY_WATER_COUNT == key) {
            updateWaterCount()
        } else if (PreferencesUtils.KEY_CHARGING_REMINDER_COUNT == key) {
            updateChargingReminderCount()
        }
    }
}
