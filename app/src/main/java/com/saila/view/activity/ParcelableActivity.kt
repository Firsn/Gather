package com.saila.view.activity

import com.saila.R
import com.saila.base.BaseActivity
import com.saila.databinding.ParcelableLayoutBinding
import com.saila.viewmodel.VmParcelableActivity
import java.util.*

/**
 * Created by Athrun on 2018/4/20.
 */
class ParcelableActivity  :BaseActivity<ParcelableActivity, ParcelableLayoutBinding,VmParcelableActivity>(){
    override fun getIntentData() {

    }

    override fun getContentView(): Int {
        return R.layout.parcelable_layout
    }

    override fun getViewModel(): VmParcelableActivity {
        return VmParcelableActivity(context,mUtil!!)
    }

    override fun presenterTask() {
    }

    override fun update(o: Observable?, arg: Any?) {
    }
}