package org.techtown.project5.view.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import org.techtown.project5.R
import org.techtown.project5.databinding.ActivitySplashBinding
import org.techtown.project5.view.MainActivity
import org.techtown.project5.view.base.BaseActivity
import org.techtown.project5.viewmodel.splash.SplashViewModel

class SplashActivity : BaseActivity() {

    lateinit var binding : ActivitySplashBinding
    lateinit var viewModel : SplashViewModel

    val SPLASH_TIME : Long = 1900

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_splash)
        viewModel = ViewModelProviders.of(this).get(SplashViewModel::class.java)

        Handler().postDelayed({
            startActivity(Intent(this, MainActivity::class.java))
            overridePendingTransition(R.anim.loadfadein, R.anim.loadfadeout)
            finish()
        }, SPLASH_TIME)
    }
}
