package com.everis.bootcamp.alarmmanager

import android.app.AlarmManager
import android.content.Context

private const val STRETCHING_BROADCAST_REQUEST_ID = 2

//TODO: 008 - Crie uma constante do tipo string chamada ACTION_DRINK_WATER_STRETCH para representar a action com.everis.bootcamp.drinkwater.stretching

/*TODO: 009 - Crie a função startAlarmToStretch
 * - A função deve receber como parâmetro um Context e um AlarmManager optional
 * - Na função crie uma Intent explicita com a action ACTION_DRINK_WATER_STRETCH e com a categoria android.intent.category.DEFAULT
 * - Crie uma PendingIntent através do método PendingIntent.getBroadcast informe como parametro contexto, STRETCHING_BROADCAST_REQUEST_ID e a intent criada, para flag pode informar 0
 * - Utilize a função setRepeating do alarmManager para rodar acada 30 segundos
 */

/*TODO: 009 - Crie a função startAlarmToStretch
* - A função deve receber como parâmetro um Context e um AlarmManager optional
* - Na função crie uma Intent explicita com a action ACTION_DRINK_WATER_STRETCH e com a categoria android.intent.category.DEFAULT
* - Crie uma PendingIntent através do método PendingIntent.getBroadcast informe como parametro contexto, STRETCHING_BROADCAST_REQUEST_ID e a intent criada, para flag pode informar 0
* - utiliza a função cancel informando a PendingIntent para cancelar o alarme
*/
fun stopAlarmToStretch(alarmManager: AlarmManager) {


}