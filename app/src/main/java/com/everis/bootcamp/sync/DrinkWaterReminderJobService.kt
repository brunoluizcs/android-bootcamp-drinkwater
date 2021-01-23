package com.everis.bootcamp.sync

import android.os.AsyncTask


/*TODO: 005 -
    Crie a classe DrinkWaterReminderJobService e faça com que ela extenda a classe JobService
 */
class DrinkWaterReminderJobService {
    var backgroundTask: AsyncTask<Void,Void,Void>? = null

    //TODO: 006 - Sobrescreva o método onStartJob

    /*TODO: 007 - Por padrão jobs são executados na thread principal
       Dentro de onStartJob crie a classe anonima backgroundTask
    */

    //TODO: 008 - Sobrescreva o método doInBackground e faça a chamada do método DrinkWaterRemiderTasks.executeTask informando a action ReminderTasks.ACTION_CHARGING_REMINDER

    /*
     * TODO: 009 - Sobrescreva o método onPostExecute chame o método jobFinished informando o params e false
        desta forma informamos ao scheduler que o seu job foi concluído e não precisa ser reagendado
     */

    //TODO: 010 - Execute a tarefa backgroundTask


    //TODO: 011 - Sobrescreva o método onStopJob, este método é chamado quando o motor de agendamento decide interromper a execução do job
    //TODO: 012 - Em onStopJob se vc tiver uma backgroundTask válida cancele a execução
}