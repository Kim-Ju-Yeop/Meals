package org.techtown.project5.view.base

import android.os.CountDownTimer
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import java.text.SimpleDateFormat
import java.util.*

open class BaseActivity : AppCompatActivity(){

    // Timer Setting
    lateinit var timer : CountDownTimer

    fun setTime(timeTextView : TextView){
        timer = object : CountDownTimer((10*1000), 1000){
            override fun onTick(millisUntilFinished: Long) {
                timeTextView.text = getTime()
            }
            override fun onFinish() {
                timer.cancel()
                timer.start()
            }
        }
        timer.start()
    }
    fun getTime() : String{
        var mFormat = SimpleDateFormat("kk : mm : ss a", Locale.KOREA)
        val mNow = System.currentTimeMillis()
        val mDate = Date(mNow)

        return mFormat.format(mDate)
    }

    // Toolbar Setting
    fun setToolbar(toolbar : Toolbar){
        setSupportActionBar(toolbar)
        val actionBar = supportActionBar
        actionBar!!.setDisplayShowCustomEnabled(true)
        actionBar.setDisplayShowTitleEnabled(false)
    }
}