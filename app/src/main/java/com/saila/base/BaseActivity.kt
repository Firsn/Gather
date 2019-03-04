package com.saila.base

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.saila.BR
import com.saila.config.ConfigSla
import com.saila.util.Util
import com.saila.viewmodel.BaseViewModel
import java.util.*

/**
 * Created by Saila on 2018/3/18.
 */
open abstract class BaseActivity<Cls,V:ViewDataBinding, VM:BaseViewModel<Cls>>()
    :AppCompatActivity(), Observer {

    abstract fun getIntentData()
    abstract fun getContentView():Int
    abstract fun getViewModel():VM
    abstract fun presenterTask()


    var phoneWidOrigin:Int=1
    var phoneHeiOrigin:Int=1
    var phoneDensity:Float=1f
    open var vm: VM ?=null
    open var binding: V?=null

    var sp:SharedPreferences?=null
    var mUtil: Util?=null
    lateinit var context: Context

    init {
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initContext()
        getIntentData()
        initParams()
        initDataBinding()
        vm?.onCreate()
    }
    open fun initContext(){
        this.context=this
    }
    fun initParams(){
        sp=getSharedPreferences(ConfigSla.SHARED_PREFERENCES_0,0)
        mUtil=Util(context)
    }
    fun initDataBinding(){
        /**
         * binding
         */
        binding=DataBindingUtil.setContentView(this,getContentView())
        /**
         * presenter and task
         */
        presenterTask()
        /**
         * vm
         */
        vm=getViewModel()
        binding?.setVariable(BR.vm,vm)


        /**
         * abserver
         */
        addAbserver()

    }
    fun addAbserver(){
        vm?.addObserver(this)
    }

    override fun onRestart() {
        vm?.onRestart()
        super.onRestart()
    }

    override fun onPause() {
        vm?.onPause()
        super.onPause()
    }

    override fun onDestroy() {
        vm?.onDestroy()
        super.onDestroy()
    }

    override fun onStart() {
        vm?.onStart()
        super.onStart()
    }

    override fun onBackPressed() {
        vm?.onBackPressed()
//        super.onBackPressed()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        vm?.onActivityResult(requestCode, resultCode, data)
//        super.onActivityResult(requestCode, resultCode, data)
    }

    open fun getBIND():V?{
        return binding
    }


}