package com.saila.viewmodel

import android.content.Context
import com.saila.util.Util
import com.saila.view.activity.IpcDetailActivity

/**
 * Created by Athrun on 2018/4/19.
 */
class VmIpcDetailActivity(var c: Context, var u:Util):BaseViewModel<IpcDetailActivity>(c,u) {
    override fun taskFinish() {
        ACTIVITY?.finish()
    }
    init {

    }
    override fun onCreate() {
    }
}