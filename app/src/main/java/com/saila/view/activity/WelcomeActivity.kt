package com.saila.view.activity

import android.databinding.DataBindingUtil
import android.os.Bundle
import com.saila.BR
import com.saila.R
import com.saila.base.BaseActivity
import com.saila.databinding.WelcomeLayoutBinding
import com.saila.viewmodel.VmWelcomeActivity
import java.util.*

/**
 * Created by Saila on 2018/3/18.
 */
class WelcomeActivity : BaseActivity<WelcomeActivity,WelcomeLayoutBinding, VmWelcomeActivity>() {
    override fun update(o: Observable?, arg: Any?) {

    }

    override fun presenterTask() {
    }

    override fun getViewModel(): VmWelcomeActivity {
        return VmWelcomeActivity(this, mUtil,this)
    }

    override fun getIntentData() {
    }

    override fun getContentView(): Int {
        return R.layout.welcome_layout
    }


}