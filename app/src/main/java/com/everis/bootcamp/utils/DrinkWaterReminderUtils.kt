package com.everis.bootcamp.utils

import android.app.job.JobInfo
import android.app.job.JobScheduler
import android.content.ComponentName
import android.content.Context
import com.everis.bootcamp.sync.DrinkWaterReminderJobService
import java.util.concurrent.TimeUnit

/*TODO: 014 - Crie as seguintes constantes
* REMINDER_INTERVAL_MINUTE - Para determinar o intervalo de tempo do lembrete em minutos
* JOB_ID - Para definir o ID do job
*/

//TODO: 015 - Crie uma constante do tipo string chamada REMINDER_JOB_TAG para servir como tag

//TODO: 016 - Crie uma variavel privada chamada sInitialized para controlar se o job ja foi iniciado

/*TODO: 017 - Crie uma função sincronizada chamada scheduleChargingReminder que recebe como parametro um context
* Em scheduleChargingReminder se o serviço não tiver sido inicializado crie
*   - crie um jobInfo para DrinkWaterReminderJobService
*   - crie um job setando como true setRequiresCharging e o período de execução igual REMINDER_JOB_TAG
*   - crie um crie uma vriavel do tipo JobScheduler e faça o agendamento do job
*   - defina a variavel sInitialize = true
*/

private var sInitialize = false
@Synchronized
fun scheduleChargingReminder(context: Context) {
    if (sInitialize) return

    val jobInfo = JobInfo.Builder(123, ComponentName(context, DrinkWaterReminderJobService::class.java))
    val job = jobInfo.setRequiresCharging(true)
        .setPeriodic(TimeUnit.MINUTES.toMillis(2))
        .build()

    val jobScheduler = context.getSystemService(Context.JOB_SCHEDULER_SERVICE) as JobScheduler
    jobScheduler.schedule(job)

    sInitialize = true
}
