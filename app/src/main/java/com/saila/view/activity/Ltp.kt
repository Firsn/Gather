package com.saila.view.activity

import android.databinding.ViewDataBinding
import com.saila.BR
import com.saila.R
import com.saila.base.BaseActivity
import com.saila.databinding.LtpLayoutBinding
import com.saila.viewmodel.BaseViewModel
import com.saila.viewmodel.VmLtp
import java.util.*

/**
 * Created by Saila on 2018/3/20.
 */
class Ltp : BaseActivity<Ltp,LtpLayoutBinding, VmLtp>() {
    override fun update(o: Observable?, arg: Any?) {

    }

    override fun presenterTask() {

    }

    override fun getIntentData() {
    }

    override fun getContentView(): Int {
        return R.layout.ltp_layout
    }


    override fun getViewModel(): VmLtp {
        return VmLtp(context,mUtil,this)
    }

}