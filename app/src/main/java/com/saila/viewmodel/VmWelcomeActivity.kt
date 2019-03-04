package com.saila.viewmodel

import android.content.Context
import android.os.CountDownTimer
import android.os.Handler
import android.os.Message
import android.support.v7.app.AppCompatActivity
import com.saila.util.Util
import com.saila.view.activity.HomeActivity
import com.saila.view.activity.WelcomeActivity
import java.util.*
import java.util.concurrent.CountedCompleter

/**
 * Created by Saila on 2018/3/18.
 */
class VmWelcomeActivity(context2:Context,mUtil2:Util?,cls:WelcomeActivity) :BaseViewModel<WelcomeActivity>(context2,mUtil2,cls){
    var cdt:CountDownTimer?=null
    override fun onCreate() {
        ACTIVITY?.getBIND()!!.wwv.loadJs("hahah")
        Util.log("result9",mUtil?.phoneWid.toString())
        Util.log("result9",mUtil?.phoneHei.toString())
        Util.log("result9",mUtil?.phoneDensity.toString())
        timeSec5()
    }
    init {

    }

    override fun onBackPressed() {
       taskFinish()
    }
    override fun taskFinish() {
        cdt?.cancel()
        ACTIVITY?.finish()
    }

    fun timeSec5(){
//        var timer=Timer()
//        timer.schedule(
//                object :TimerTask(){
//                    override fun run() {
//                    }
//                },1000)

         cdt=object:CountDownTimer(5000,1000){
            override fun onFinish() {
                handler.sendEmptyMessage(1);
            }

            override fun onTick(millisUntilFinished: Long) {
            }

        }
        cdt?.start()

    }

    var handler:Handler=object: Handler(){
        override fun handleMessage(msg: Message?) {
            super.handleMessage(msg)
            when(msg?.what){
                1->{
                    mUtil?.intentToActivity(ACTIVITY!!,null,HomeActivity())
                }
            }
        }
    }



}