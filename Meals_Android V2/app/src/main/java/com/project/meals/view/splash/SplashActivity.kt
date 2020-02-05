package com.project.meals.view.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.project.meals.R
import com.project.meals.view.MainActivity
import com.project.meals.view.base.BaseActivity

class SplashActivity : BaseActivity() {

    val SPLASH_TIME : Long = 1900

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler().postDelayed({
            startActivity(Intent(this, MainActivity::class.java))
            overridePendingTransition(R.anim.loadfadein, R.anim.loadfadeout)
            finish()
        }, SPLASH_TIME)
    }
}
