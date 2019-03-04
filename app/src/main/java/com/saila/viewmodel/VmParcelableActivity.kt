package com.saila.viewmodel

import android.content.Context
import android.content.Intent
import android.util.Log
import com.saila.model.OneMobile
import com.saila.util.Util
import com.saila.util.api.SlaApi
import com.saila.view.activity.IpcActivity
import com.saila.view.activity.ParcelableActivity

/**
 * Created by Athrun on 2018/4/20.
 */
class VmParcelableActivity(var c:Context,var u:Util):BaseViewModel<ParcelableActivity>(c,u) {
    override fun taskFinish() {
        ACTIVITY?.finish()
    }
    init {
    }

    override fun onCreate() {
        Thread(Runnable {
           var p= mUtil?.api?.parcelableActivity()
//            try {
//                Util.log("result9"," vm Parcelable="+p)
//                Util.log("result9"," vm Parcelable="+p!!.status)
//                Util.log("result9"," vm Parcelable="+p!!.msgStr)
//                Util.log("result9"," vm Parcelable="+p!!.list)
//            } catch (e: Exception) {
//                Util.log("result9"," vm Parcelable Exception="+e.toString())
//
//            }
        }).start()
    }

    fun  toIpcActivity(){

        var mobile1=OneMobile()
        mobile1.width=100;
        mobile1.height=101;
        mobile1.desity=102f;

        var intentParams= Intent()
        intentParams?.putExtra("seri",mobile1)
        intentParams?.setClass(ACTIVITY,IpcActivity().javaClass)
        ACTIVITY!!.startActivity(intentParams)
    }


}