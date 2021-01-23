package com.everis.bootcamp.sync

import android.app.job.JobInfo
import android.app.job.JobScheduler
import android.content.ComponentName
import android.content.Context


private const val REMINDER_INTERVAL_MINUTE = 15 * 60 * 1000L // 15 minutes

private const val JOB_ID = 1234

private var sInitialize = false

@Synchronized
fun scheduleChargingReminder(context: Context) {
    if (sInitialize) return

    val jobInfo = JobInfo.Builder(JOB_ID, ComponentName(context, DrinkWaterReminderJobService::class.java))
    val job = jobInfo.setRequiresCharging(true)
        .setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY)
        .setPeriodic(REMINDER_INTERVAL_MINUTE)
        .build()

    val jobScheduler = context.getSystemService(Context.JOB_SCHEDULER_SERVICE) as JobScheduler
    jobScheduler.schedule(job)

    sInitialize = true
}
