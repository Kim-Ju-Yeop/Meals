package org.techtown.project5.view.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import org.techtown.project5.R
import org.techtown.project5.view.MainActivity
import org.techtown.project5.view.base.BaseActivity

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
