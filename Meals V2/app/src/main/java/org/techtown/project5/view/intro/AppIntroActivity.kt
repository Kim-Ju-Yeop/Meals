package org.techtown.project5.view.intro

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.github.paolorotolo.appintro.AppIntro
import org.techtown.project5.R
import org.techtown.project5.view.search_school.SearchSchoolActivity

class AppIntroActivity : AppIntro() {

    val introSlide = IntroSlide()

    @Override
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        addSlide(introSlide.newInstance(R.layout.intro_layout1))
        addSlide(introSlide.newInstance(R.layout.intro_layout2))
        addSlide(introSlide.newInstance(R.layout.intro_layout3))
        addSlide(introSlide.newInstance(R.layout.intro_layout4))
        addSlide(introSlide.newInstance(R.layout.intro_layout5))
    }

    @Override
    override fun onSkipPressed(currentFragment: Fragment?) {
        super.onSkipPressed(currentFragment)
        finish()
        startActivity(Intent(this@AppIntroActivity, SearchSchoolActivity::class.java))
    }

    @Override
    override fun onDonePressed() {
        super.onDonePressed()
        finish()
        startActivity(Intent(this@AppIntroActivity, SearchSchoolActivity::class.java))
    }
}