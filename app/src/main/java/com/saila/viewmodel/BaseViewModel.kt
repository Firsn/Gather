package com.saila.viewmodel

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import com.saila.util.Util
import java.util.*

/**
 * Created by Saila on 2018/3/18.
 */
open abstract  class BaseViewModel<T>(): BaseInterface, Observable() {
    var context:Context?=null
    var mUtil: Util?=null
    var ACTIVITY:T?=null

    init {

    }
    constructor(context:Context) : this() {
        this.context=context
    }
    constructor(context:Context,mUtil: Util?) : this() {
        this.context=context
        this.mUtil=mUtil
        ACTIVITY=context as T
    }

    constructor(context:Context,mUtil: Util?,cls:T) : this() {
        this.context=context
        this.mUtil=mUtil
        ACTIVITY=cls

    }

    override fun onCreate() {
    }

    override fun onRestart() {
    }

    override fun onStart() {
    }

    override fun onPause() {
    }

    override fun onDestroy() {
    }

    override fun onBackPressed() {
        taskFinish()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
    }

    abstract fun taskFinish()
}