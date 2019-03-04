package com.saila.viewmodel

import android.content.Context
import com.saila.util.Util
import com.saila.view.activity.IpcActivity
import com.saila.view.activity.IpcDetailActivity

/**
 * Created by Athrun on 2018/4/18.
 */
open class VmIpcActivity(var c:Context,var u:Util):BaseViewModel<IpcActivity>(c,u) {

    init {

    }

    override fun onCreate() {

    }

    override fun taskFinish() {
        ACTIVITY?.finish()
    }

    fun toDetail(){
       mUtil?.intentToActivity(ACTIVITY!!,null,IpcDetailActivity())
    }

}