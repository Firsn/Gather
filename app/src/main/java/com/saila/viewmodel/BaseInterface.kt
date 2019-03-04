package com.saila.viewmodel

import android.content.Intent

/**
 * Created by Saila on 2018/3/18.
 */
open abstract interface BaseInterface {
    abstract fun onCreate()
    abstract fun onRestart()
    abstract fun onStart()
    abstract fun onPause()
    abstract fun onDestroy()
    abstract fun onBackPressed()
    abstract fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?)
}