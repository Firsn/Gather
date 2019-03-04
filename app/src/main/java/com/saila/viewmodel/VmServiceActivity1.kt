package com.saila.viewmodel

import android.content.Context
import android.content.Intent
import com.saila.service.SlaService1
import com.saila.util.Util
import com.saila.view.activity.ServiceActivity1

/**
 * Created by Athrun on 2018/4/12.
 */
class VmServiceActivity1(var c:Context,var u:Util?) :BaseViewModel<ServiceActivity1>(c,u) {

    init {

    }

    override fun onCreate() {
        ACTIVITY!!.getBIND()!!.tvDes.setText("Service1 activity1");
        ACTIVITY?.getBIND()!!.tvStart.setOnClickListener{
            var intent= Intent()
            intent.setClass(context,SlaService1::class.java)

            ACTIVITY!!.startService(intent)
        }
        ACTIVITY?.getBIND()!!.tvStop.setOnClickListener{
            var intent= Intent()
            intent.setClass(context,SlaService1::class.java)
            ACTIVITY!!.stopService(intent)
        }

    }

    override fun taskFinish() {
        ACTIVITY?.finish()
    }
}