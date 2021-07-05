package com.example.carmodel

import android.view.animation.Animation
import android.widget.ImageView

class Presenter(mainActivity: MainActivity) : Contract.Presenter {
    override fun animation(animation: Animation?, img: ImageView?) {
        with(animation) {
            this?.setDuration(2000)
        }
        with(img) { this?.startAnimation(animation) }
    }
}