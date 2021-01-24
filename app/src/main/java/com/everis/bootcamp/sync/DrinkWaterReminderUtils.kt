package com.everis.bootcamp.sync

import android.app.job.JobInfo
import android.app.job.JobScheduler
import android.content.ComponentName
import android.content.Context


private const val REMINDER_INTERVAL_MILLIS = 15 * 60 * 1000L

private const val JOB_ID = 1234;

private var isInitialized = false


@Synchronized
fun schedulerChargingReminder(context: Context) {
    if (isInitialized) return

    val jobInfoBuilder = JobInfo.Builder(JOB_ID, ComponentName(context, DrinkWaterReminderJobService::class.java))

    val jobInfo = jobInfoBuilder
            .setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY)
            .setRequiresCharging(true)
            .setPeriodic(REMINDER_INTERVAL_MILLIS)
            .build()

    val scheduler = context.getSystemService(Context.JOB_SCHEDULER_SERVICE) as JobScheduler
    scheduler.schedule(jobInfo)

    isInitialized = true
}
