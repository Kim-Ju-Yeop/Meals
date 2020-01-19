package com.project.meals.view.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.project.meals.R
import com.project.meals.view.MainActivity

class SplashActivity : AppCompatActivity() {

    /**
     * Splash_Time
     * 단위의 체계에서 1000 이 1초를 의미한다.
     */

    val SPLASH_TIME : Long = 1900

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler().postDelayed({
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }, SPLASH_TIME)
    }
}
