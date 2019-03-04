package com.saila.service

import android.app.Service
import android.content.Intent
import android.os.IBinder
import com.saila.util.Util

/**
 * Created by Athrun on 2018/4/12.
 */
class SlaService2 :Service() {
    init {
        Util.log("result9","init")
    }
    override fun onCreate() {
        super.onCreate()
        Util.log("result9","onCreate")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Util.log("result9","onStartCommand")
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onBind(intent: Intent?): IBinder? {
        Util.log("result9","onBind")
        return null
    }

    override fun onRebind(intent: Intent?) {
        Util.log("result9","onRebind")
        super.onRebind(intent)
    }

    override fun onDestroy() {
        Util.log("result9","onDestroy")
        super.onDestroy()
    }
}