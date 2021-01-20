package com.everis.bootcamp.drinkwater

import android.content.SharedPreferences
import android.os.Bundle
import android.preference.PreferenceManager
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(),
    SharedPreferences.OnSharedPreferenceChangeListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //TODO: 008 - Realize a chamada da função updateWaterCount

        imageview_cup_icon.setOnClickListener {
            //TODO: 009 - Chame a função incrementWaterHandler
        }

        val prefs = PreferenceManager.getDefaultSharedPreferences(this)
        prefs.registerOnSharedPreferenceChangeListener(this)
    }

    /*TODO: 007 - crie uma função updateWaterCount
        - Atualize o textview_quantity com o valor da PreferencesUtils.getWaterCount
     */

    /*TODO: 008 - crie uma função chamada incrementWaterHandler
        - Crie uma intent explicita para acionar o DrinkWaterReminderIntentService
        - Defina a action da Intent com a constant ACTION_INCREMENT_WATER_COUNT
        - Chame startService e passe a intent como parametro
     */


    override fun onDestroy() {
        super.onDestroy()
        val prefs = PreferenceManager.getDefaultSharedPreferences(this)
        prefs.unregisterOnSharedPreferenceChangeListener(this)
    }

    override fun onSharedPreferenceChanged(sharedPreferences: SharedPreferences?, key: String?) {
        //TODO: 010 - Chame o método updateWaterCount se o parametro key for igual a constante PrefencesUtils.KEY_WATER_COUNT
    }
}
