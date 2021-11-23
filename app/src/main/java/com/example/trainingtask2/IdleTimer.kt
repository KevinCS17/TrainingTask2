package com.example.trainingtask2

import android.app.Service
import android.content.Intent
import android.os.CountDownTimer
import android.os.IBinder
import android.view.View
import androidx.navigation.findNavController
import com.example.trainingtask2.session.SessionManager
import kotlin.concurrent.timer

class IdleTimer @javax.inject.Inject constructor() : Service() {
    companion object {
        lateinit var timer: CountDownTimer
        var listener: Listener? = null

        fun setTimerListener(listener: Listener) {
            this.listener = listener
        }
    }

    override fun onCreate() {
        super.onCreate()
        timer = object : CountDownTimer(30 * 1000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
            }

            override fun onFinish() {
                // Code for Logout
                stopSelf()
                listener?.onTimerFinished()
            }
        }.start()
    }

    override fun onBind(p0: Intent?): IBinder? = null

    interface Listener {
        fun onTimerFinished()
    }
}