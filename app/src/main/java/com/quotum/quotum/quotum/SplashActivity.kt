package com.quotum.quotum.quotum

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.quotum.quotum.quotum.ui.login.LoginActivity
import java.util.*
import kotlin.concurrent.schedule

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        val intent = Intent(this, LoginActivity::class.java)
        Timer("SettingUp", false).schedule(2000) {
            startActivity(intent)
            finish()
        }
    }
}
