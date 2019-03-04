package com.saila.util

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Build
import android.util.DisplayMetrics
import android.util.Log
import android.view.WindowManager
import android.webkit.WebView
import android.widget.Toast
import com.saila.config.ConfigSla
import com.saila.model.BaseModel
import com.saila.util.api.SlaApi
import com.saila.util.parse.SlaParse

/**
 * Created by Saila on 2018/3/18.
 */
class Util {
    var context :Context?=null
    var mToast : Toast?=null
    var intentParams : Intent?=null

    var phoneWid:Int=1
    var phoneHei:Int=1
    var phoneDensity:Float=1f
    var api:SlaApi?=null
    var parse:SlaParse?=null

    constructor(){
    }
    constructor( context: Context){
        this.context=context
        getPhoneParams()
        api= SlaApi(context)
        parse= SlaParse(context)
    }


    companion object {
        fun log(tag:String,str:String?){
            Log.i(tag,str)
        }
    }



    fun toastMes(str:String){
        if (mToast==null){
            mToast=Toast.makeText(context,str,Toast.LENGTH_SHORT)
        }else{
            mToast?.setText(str)
        }
        mToast?.show()
    }

    fun isNullStr(str:String?):Boolean{
        if(str==null||"".contentEquals(str)
                ||"null".contentEquals(str)){
            return true
        }
        return false
    }

    fun forNullStr(str:String?):String{
        if(str==null||"".contentEquals(str)
                ||"null".contentEquals(str)){
            return ""
        }
        return str
    }

    fun forNullStr_0(str:String?):String{
        if(str==null||"".contentEquals(str)
                ||"null".contentEquals(str)){
            return "0"
        }
        return str
    }
    fun forNullStr_point(str:String?):String{
        if(str==null||"".contentEquals(str)
                ||"null".contentEquals(str)){
            return "···"
        }
        return str
    }

    fun <T,M: BaseModel> intentToActivity(a: Activity, m:M?, t:T):Intent?{

        try {
            if (t==null){
                toastMes("Class is null")
                return intentParams
            }

            if (intentParams==null){
                intentParams= Intent()
            }
//            if (m!=null){
//                intentParams?.putExtra(ConfigSla.INTENT_EXTRAS,m)
//            }
            intentParams?.putExtra(ConfigSla.INTENT_EXTRAS,m)
            intentParams?.setClass(a,(t as Activity).javaClass)
            a.startActivity(intentParams)
        } catch (e: Exception) {
            toastMes(e.toString())
        }
        return intentParams
    }

    fun insetJs(wv: WebView, trigger:String){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {//4.4
//		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {// 4.0
            wv.evaluateJavascript(trigger, null)
        } else {
            wv.loadUrl(trigger)
        }
    }

    fun setPhoneParams(){
        var wm :WindowManager?= context?.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        var dm:DisplayMetrics?= DisplayMetrics()
        wm?.defaultDisplay?.getMetrics(dm)
        var sp:SharedPreferences?=context?.getSharedPreferences(ConfigSla.SHARED_PREFERENCES_0,
                0)
        var ed:SharedPreferences.Editor?=sp?.edit()
        ed?.putInt(ConfigSla.PHONE_WID_ORIGIN,dm?.widthPixels!!)
        ed?.putInt(ConfigSla.PHONE_HEI_ORIGIN,dm?.heightPixels!!)
        ed?.putFloat(ConfigSla.PHONE_DENSITY,dm?.density!!)
        ed?.commit()
        wm=null
        dm=null
        ed=null
    }

    fun getPhoneParams(){
        var wm :WindowManager?= context?.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        var dm:DisplayMetrics?= DisplayMetrics()
        wm?.defaultDisplay?.getMetrics(dm)
        phoneWid=dm?.widthPixels!!
        phoneHei=dm?.heightPixels!!
        phoneDensity=dm?.density!!
        wm=null
        dm=null
    }

}