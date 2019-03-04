package com.saila

import android.app.Application
import android.content.Context
import com.tencent.stat.*

/**
 * Created by Athrun on 2018/6/5.
 */
class SApplication :Application() {

    init {

    }
    override fun onCreate() {
        super.onCreate()
        com.baidu.mobstat.StatService.setDebugOn(false)
        com.baidu.mobstat.StatService.autoTrace(this, true, false)
        com.baidu.mobstat.StatService.enableDeviceMac(this, false)



        StatService.setContext(this)

        StatConfig.setTLinkStatus(true);


        initMTAConfig()
        StatService.registerActivityLifecycleCallbacks(this)
        initMtaCrashModule(this)
    }

    fun  initMTAConfig() {

        StatConfig.setDebugEnable(false)
        StatConfig.setAutoExceptionCaught(true)
//        StatConfig.setStatSendStrategy(StatReportStrategy.PERIOD)
//        StatConfig.setSendPeriodMinutes(1)

        StatConfig.setStatSendStrategy(StatReportStrategy.APP_LAUNCH)
        StatConfig.setSendPeriodMinutes(1)

        StatCrashReporter.getStatCrashReporter(getApplicationContext()).setJavaCrashHandlerStatus(true);
        StatCrashReporter.getStatCrashReporter(getApplicationContext()).setJniNativeCrashStatus(true);
        StatCrashReporter.getStatCrashReporter(getApplicationContext()).addCrashCallback(object: StatCrashCallback {
            override fun onJniNativeCrash(p0: String?) {
            }

            override fun onJavaCrash(p0: Thread?, p1: Throwable?) {
            }
        });

    }

    fun initMtaCrashModule( app: Context){
         var crashReporter :StatCrashReporter= StatCrashReporter.getStatCrashReporter(app)
        crashReporter.setEnableInstantReporting(true)
        crashReporter.setJavaCrashHandlerStatus(true)
        crashReporter.setJniNativeCrashStatus(true)

        StatConfig.setCrashKeyValue("myTag", "myValue")

        crashReporter.addCrashCallback(object: StatCrashCallback{
            override fun onJniNativeCrash(p0: String?) {

            }

            override fun onJavaCrash(p0: Thread?, p1: Throwable?) {
            }
        });
    }

}