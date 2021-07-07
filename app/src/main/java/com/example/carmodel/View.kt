package com.example.carmodel

import android.widget.ImageView

class View : Contract.Repository {
    private lateinit var imageView: ImageView
    override fun loadImg(): ImageView {
        return imageView
    }
}