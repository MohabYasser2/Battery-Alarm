package com.mohab.batteryalarm

import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat




class BatteryPercentReceiver ( val isLow: (Boolean) -> Unit) : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent) {
        val action = intent.action

        if (Intent.ACTION_BATTERY_LOW == action)
        {
        isLow(true)
        }
        else if (Intent.ACTION_BATTERY_OKAY == action)
        {
        isLow(false)
        }
    }

}