package com.everis.bootcamp.sync

import android.app.job.JobParameters
import android.app.job.JobService
import android.os.AsyncTask
import com.everis.bootcamp.sync.DrinkWaterReminderTask.Companion.ACTION_CHARGING_REMINDER

class DrinkWaterReminderJobService : JobService() {
    companion object {
        private var backgroundTask: AsyncTask<Void,Void,Void>? = null
    }

    override fun onStartJob(params: JobParameters?): Boolean {
        backgroundTask = object: AsyncTask<Void,Void,Void>() {
            override fun doInBackground(vararg params: Void?): Void? {
                DrinkWaterReminderTask.executeTask(this@DrinkWaterReminderJobService, ACTION_CHARGING_REMINDER)
                return null
            }

            override fun onPostExecute(result: Void?) {
                super.onPostExecute(result)
                jobFinished(params, false)
            }
        }

        backgroundTask?.execute()
        return true
    }

    override fun onStopJob(params: JobParameters?): Boolean {
        backgroundTask?.cancel(true)
        return true
    }
}