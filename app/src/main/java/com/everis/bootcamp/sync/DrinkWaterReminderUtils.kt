package com.everis.bootcamp.sync

import android.app.job.JobInfo
import android.app.job.JobScheduler
import android.content.ComponentName
import android.content.Context
import java.util.concurrent.TimeUnit

/*TODO: 014 - Crie as seguintes constantes
* REMINDER_INTERVAL_MINUTE - Para determinar o intervalo de tempo do lembrete em minutos
*/


//TODO: 015 - Crie uma variavel privada chamada sInitialized para controlar se o job ja foi iniciado

/*TODO: 016 - Crie uma função sincronizada chamada scheduleChargingReminder que recebe como parametro um context
* Em scheduleChargingReminder se o serviço não tiver sido inicializado crie
*   - crie um jobInfo para DrinkWaterReminderJobService
*   - crie um job setando como true setRequiresCharging e o período de execução igual REMINDER_JOB_TAG
*   - crie um crie uma vriavel do tipo JobScheduler e faça o agendamento do job
*   - defina a variavel sInitialize = true
*/
