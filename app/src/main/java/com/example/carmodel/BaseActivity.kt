package com.example.carmodel

import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

abstract class BaseActivity: AppCompatActivity(), CoroutineScope {
    private val job by lazy { Job() }
    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main + handler
    private val handler = CoroutineExceptionHandler { coroutineContext, throwable ->
        Log.e("Exception", ":$throwable")
    }
    override fun onDestroy() {
        super.onDestroy()
        job.cancel()
    }
}
