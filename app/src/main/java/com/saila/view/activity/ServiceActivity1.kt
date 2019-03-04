package com.saila.view.activity

import android.databinding.ViewDataBinding
import com.saila.R
import com.saila.base.BaseActivity
import com.saila.databinding.ServiceLayout1Binding
import com.saila.util.Util
import com.saila.viewmodel.VmServiceActivity1
import java.util.*

/**
 * Created by Athrun on 2018/4/12.
 */
class ServiceActivity1 :BaseActivity<ServiceActivity1,ServiceLayout1Binding,VmServiceActivity1>(){
    override fun getIntentData() {
    }

    override fun getContentView(): Int {
        return R.layout.service_layout1
    }

    override fun getViewModel(): VmServiceActivity1 {
        return VmServiceActivity1(context,Util(context))
    }

    override fun presenterTask() {
    }

    override fun update(o: Observable?, arg: Any?) {
    }
}