package com.saila.view.activity

import com.saila.R
import com.saila.base.BaseActivity
import com.saila.databinding.IpcDetailLayoutBinding
import com.saila.viewmodel.VmIpcDetailActivity
import java.util.*

/**
 * Created by Athrun on 2018/4/19.
 */
class IpcDetailActivity :BaseActivity<IpcDetailActivity, IpcDetailLayoutBinding, VmIpcDetailActivity>() {
    override fun getIntentData() {

    }
    override fun getContentView(): Int {
        return R.layout.ipc_detail_layout
    }

    override fun getViewModel(): VmIpcDetailActivity {
        return VmIpcDetailActivity(context,mUtil!!)
    }

    override fun presenterTask() {
    }

    override fun update(o: Observable?, arg: Any?) {
    }
}