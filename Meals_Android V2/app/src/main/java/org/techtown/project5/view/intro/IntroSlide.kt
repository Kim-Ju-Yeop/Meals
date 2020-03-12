package org.techtown.project5.view.intro

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.fragment.app.Fragment

class IntroSlide : Fragment() {

    val ARG_LAYOUT_RES_ID = "layoutResId"
    var layoutResId : Int = 0


    fun newInstance(layoutResId: Int): IntroSlide {
        val sampleSlide = IntroSlide()
        val args = Bundle()

        args.putInt(ARG_LAYOUT_RES_ID, layoutResId)
        sampleSlide.arguments = args

        return sampleSlide
    }

    @Override
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if(arguments != null && arguments!!.containsKey(ARG_LAYOUT_RES_ID))
            layoutResId = arguments!!.getInt(ARG_LAYOUT_RES_ID)
    }

    @Override
    @NonNull
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(layoutResId, container, false)
    }
}