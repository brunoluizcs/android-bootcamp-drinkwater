package com.everis.bootcamp.drinkwater

import android.app.AlarmManager
import android.content.*
import android.os.Build
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.everis.bootcamp.alarmmanager.*
import com.everis.bootcamp.sync.DrinkWaterReminderIntentService
import com.everis.bootcamp.sync.DrinkWaterReminderTask
import com.everis.bootcamp.sync.schedulerChargingReminder
import com.everis.bootcamp.utils.PreferencesUtils
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(),
    SharedPreferences.OnSharedPreferenceChangeListener {

    private val receiver = MainBroadcastReceiver()
    private val stretchingReceiver = StretchingBroadcastReceiver()

    private var alarmManager: AlarmManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        updateWaterCount()
        updateChargingReminderCount()
        updateStretchReminderCount()

        schedulerChargingReminder(this)

        imageview_cup_icon.setOnClickListener {
            incrementWaterHandler()
        }

        val prefs = PreferenceManager.getDefaultSharedPreferences(this)
        prefs.registerOnSharedPreferenceChangeListener(this)

        registerMainBroadcastReceiver()

        alarmManager = applicationContext.getSystemService(Context.ALARM_SERVICE) as AlarmManager

        registerStretchingReceiver(this, stretchingReceiver)
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

    fun updateStretchReminderCount() {
        val count = PreferencesUtils.getStretchingReminderCount(this)
        val message = getString(R.string.stretching_notification_count, count)
        textview_stretching_reminder.text = message
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
        unregisterStretchingReceiver(this, stretchingReceiver)
    }

    override fun onSharedPreferenceChanged(sharedPreferences: SharedPreferences?, key: String?) {
        when (key) {
            PreferencesUtils.KEY_WATER_COUNT -> {
                updateWaterCount()
            }
            PreferencesUtils.KEY_CHARGING_REMINDER_COUNT -> {
                updateChargingReminderCount()
            }
            PreferencesUtils.KEY_STRETCHING_REMINDER_COUNT -> {
                updateStretchReminderCount()
            }
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

    fun startAlarm(view: View) {
        startAlarmToStretch(applicationContext, alarmManager)
    }
    
    fun stopAlarm(view: View) {
        stopAlarmToStretch(applicationContext, alarmManager)
    }

}
