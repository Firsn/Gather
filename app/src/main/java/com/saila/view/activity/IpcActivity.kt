package com.saila.view.activity

import com.saila.R
import com.saila.base.BaseActivity
import com.saila.databinding.IpcLayoutBinding
import com.saila.model.OneMobile
import com.saila.util.Util
import com.saila.viewmodel.VmIpcActivity
import java.util.*

/**
 * Created by Athrun on 2018/4/18.
 */
class IpcActivity :BaseActivity<IpcActivity, IpcLayoutBinding, VmIpcActivity>() {

    override fun getIntentData() {
        var mobile1: OneMobile? = null
        try {
            mobile1 = intent.getSerializableExtra("seri") as OneMobile?
            Util.log("result9"," mobile.wid="+mobile1?.width)
            Util.log("result9"," mobile.hei="+mobile1?.height)
            Util.log("result9"," mobile.dens="+mobile1?.desity)
        } catch (e: Exception) {
        }


    }
    override fun getContentView(): Int {
        return R.layout.ipc_layout
    }

    override fun getViewModel(): VmIpcActivity {
        return VmIpcActivity(context,mUtil!!)
    }

    override fun presenterTask() {
    }

    override fun update(o: Observable?, arg: Any?) {
    }

}