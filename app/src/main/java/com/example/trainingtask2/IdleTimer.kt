package com.example.trainingtask2

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.os.CountDownTimer
import com.example.trainingtask2.session.SessionManager


class IdleTimer @javax.inject.Inject constructor(var sessionManager: SessionManager) : Service() {
    var timer: CountDownTimer? = null

    override fun onCreate() {
        super.onCreate()
        timer = object : CountDownTimer(30 * 1000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
            }

            override fun onFinish() {
                // Code for Logout
                stopSelf()
                sessionManager.clear()
            }
        }
    }

    override fun onBind(intent: Intent?): IBinder? {
        TODO("Not yet implemented")
    }
}