package com.example.carmodel

import android.view.animation.Animation
import android.widget.ImageView

interface Contract {
    interface Presenter {
        fun animation(animation: Animation?, img: ImageView?)
    }

    interface View {
        fun show(img: ImageView?)
    }

    interface Repository {
        fun loadImg(): ImageView?
    }

}