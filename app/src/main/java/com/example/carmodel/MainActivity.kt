package com.example.carmodel

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.view.View.*
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.delay

class MainActivity() : Activity() {
    private var imageView: ImageView? = null
    private var button: Button? = null
    private var textView: TextView? = null
    private var horizontalAnimation: Animation? = null
    private var rightDiagonalAnimation: Animation? = null
    private var leftDiagonalAnimation: Animation? = null
    private val coroutineScope = CoroutineScope(
        Dispatchers.Main
    )

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        imageView = findViewById(R.id.imageView)
        button = findViewById(R.id.button)
        textView = findViewById(R.id.textView)
        horizontalAnimation = AnimationUtils.loadAnimation(this, R.anim.right);
        rightDiagonalAnimation = AnimationUtils.loadAnimation(this, R.anim.down);
        leftDiagonalAnimation = AnimationUtils.loadAnimation(this, R.anim.left_right);

        button?.setOnClickListener(object : View.OnClickListener {
            override fun onClick(arg0: View?) {
                moving()
            }
        })
    }

    fun moving() {
        //слева направо- путь 1
        horizontalAnimation()
        //справа на лево- путь 2
        val text1: Deferred<Unit> = coroutineScope.async {
            workSeconds(2000)
            rightDiagonalAnimation()
        }
        //c лево по диагонали- путь 3
        val text2: Deferred<String> = coroutineScope.async {
            val text = workSeconds(4000)
            textView?.text = text
            leftDiagonalAnimation()
            text
        }
    }

    fun horizontalAnimation() {
        with(horizontalAnimation) {
            this?.setDuration(2000)
        }
        with(imageView) { this?.startAnimation(horizontalAnimation) }
    }

    fun rightDiagonalAnimation() {
        with(rightDiagonalAnimation) {
            this?.setDuration(2000)
        }
        with(imageView) { this?.startAnimation(rightDiagonalAnimation) }
    }

    fun leftDiagonalAnimation() {
        with(leftDiagonalAnimation) {
            this?.setDuration(2000)
        }
        with(imageView) { this?.startAnimation(leftDiagonalAnimation) }
    }

    suspend fun workSeconds(milliSeconds: Long): String {
        delay(milliSeconds)
        return "Finish"
    }

}


