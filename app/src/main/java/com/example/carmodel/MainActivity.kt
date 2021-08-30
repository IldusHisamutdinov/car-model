package com.example.carmodel

import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.delay

class MainActivity() : AppCompatActivity(){
    private lateinit var mPresenter: Contract.Presenter
    private lateinit var imageView: ImageView
    private lateinit var textView: TextView
    private lateinit var horizontalAnimation: Animation
    private lateinit var rightDiagonalAnimation: Animation
    private lateinit var leftDiagonalAnimation: Animation
    private val coroutineScope = CoroutineScope(
        Dispatchers.Main
    )

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mPresenter = Presenter()
        imageView = findViewById(R.id.imageView)
        textView = findViewById(R.id.textView)
        horizontalAnimation = AnimationUtils.loadAnimation(this, R.anim.right)
        rightDiagonalAnimation = AnimationUtils.loadAnimation(this, R.anim.down)
        leftDiagonalAnimation = AnimationUtils.loadAnimation(this, R.anim.left_right)
        show()
        imageView.setOnClickListener(object : View.OnClickListener {
            override fun onClick(arg0: View?) {
                moving()
            }
        })
    }

    fun moving() {
//        //слева направо- путь 1
        mPresenter.animation(horizontalAnimation, imageView)
//        //справа на лево- путь 2
        val text1: Deferred<Unit> = coroutineScope.async {
            workSeconds(2000)
            mPresenter.animation(rightDiagonalAnimation, imageView)
        }
//        //c лево по диагонали- путь 3
        val text2: Deferred<String> = coroutineScope.async {
            val text = workSeconds(4000)
            textView.text = text
            mPresenter.animation(leftDiagonalAnimation, imageView)
            text
        }
    }

    suspend fun workSeconds(milliSeconds: Long): String {
        delay(milliSeconds)
        return "Finish"
    }

    fun show() {
        imageView.setImageResource(R.drawable.auto)
    }

}


