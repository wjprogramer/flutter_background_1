package com.wjprogramer.flutter_background_1

import android.app.Service
import android.content.Intent
import android.os.Build
import android.os.IBinder
import androidx.annotation.Nullable
import androidx.core.app.NotificationCompat.Builder

class MyService : Service() {
    override fun onCreate() {
        super.onCreate()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val builder: Builder = Builder(this, "messages")
                    .setContentText("This is running in Background")
                    .setContentTitle("Flutter Background")
                    .setSmallIcon(R.drawable.ic_android_black_24dp)
            startForeground(101, builder.build())
        }
    }

    @Nullable
    override fun onBind(intent: Intent): IBinder? {
        return null
    }
}