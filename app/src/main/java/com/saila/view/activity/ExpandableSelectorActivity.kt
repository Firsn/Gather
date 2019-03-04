package com.saila.view.activity

import com.saila.R
import com.saila.base.BaseActivity
import com.saila.viewmodel.VmExpandableSelectorActivity
import com.saila.databinding.ExpandableSelectorLayoutBinding
import java.util.*

/**
 * Created by Athrun on 2019/2/28.
 */
class ExpandableSelectorActivity():BaseActivity<ExpandableSelectorActivity,ExpandableSelectorLayoutBinding,VmExpandableSelectorActivity>() {
    override fun getIntentData() {

    }

    override fun getContentView(): Int {
        return R.layout.expandable_selector_layout
    }

    override fun getViewModel():VmExpandableSelectorActivity {
        return VmExpandableSelectorActivity(context,mUtil)
    }
    override fun presenterTask() {
    }

    override fun update(o: Observable?, arg: Any?) {
    }
}