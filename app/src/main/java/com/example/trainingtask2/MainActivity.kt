package com.example.trainingtask2

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import com.example.trainingtask2.session.SessionManager
import com.example.trainingtask2.ui.LoginFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), IdleTimer.Listener {

    @Inject
    lateinit var sessionManager: SessionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        IdleTimer.setTimerListener(this)
        startService(Intent(this, IdleTimer::class.java))
    }

    override fun onUserInteraction() {
        super.onUserInteraction()
        if(mainFragment !is LoginFragment){
            IdleTimer.timer.cancel()
            IdleTimer.timer.start()
        }
    }

    override fun onTimerFinished() {
        Log.d("MACS", "Timer Finished")
        if(mainFragment !is LoginFragment) {
            sessionManager.clear()
            findNavController(mainFragment.id).popBackStack(R.id.loginFragment, false)
        }
    }
}