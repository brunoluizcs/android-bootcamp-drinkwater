package com.everis.bootcamp.alarmmanager

private const val STRETCHING_BROADCAST_REQUEST_ID = 2

//TODO: 008 - Crie uma constante do tipo string chamada ACTION_DRINK_WATER_STRETCH para representar a action com.everis.bootcamp.drinkwater.stretching

/*TODO: 009 - Crie a função startAlarmToStretch
 * - A função deve receber como parâmetro um Context e um AlarmManager optional
 * - Na função crie uma Intent explicita com a action ACTION_DRINK_WATER_STRETCH e com a categoria android.intent.category.DEFAULT
 * - Crie uma PendingIntent através do método PendingIntent.getBroadcast informe como parametro contexto, STRETCHING_BROADCAST_REQUEST_ID e a intent criada, para flag pode informar 0
 * - Utilize a função setRepeating do alarmManager para rodar acada 15 minutos
 */

/*TODO: 010 - Crie a função startAlarmToStretch
* - A função deve receber como parâmetro um Context e um AlarmManager optional
* - Na função crie uma Intent explicita com a action ACTION_DRINK_WATER_STRETCH e com a categoria android.intent.category.DEFAULT
* - Crie uma PendingIntent através do método PendingIntent.getBroadcast informe como parametro contexto, STRETCHING_BROADCAST_REQUEST_ID e a intent criada, para flag pode informar 0
* - utiliza a função cancel informando a PendingIntent para cancelar o alarme
*/


/*TODO: 011 - Crie uma função chamada registerStretchReceiver
* - Esta função deve receber um context, e uma instancia de StretchingBroadcastReceiver
* - realize o registro para a intent ACTION_DRINK_WATER_STRETCH e para a action android.intent.category.DEFAULT
 */

/*TODO: 012 - Crie uma função chamada unregisterStretchReceiver
* - Esta função deve receber um context, e uma instancia de StretchingBroadcastReceiver
* - realize a chamada da função context.unregisterReceiver informando como parametro a instancia de StretchingBroadcastReceiver
 */