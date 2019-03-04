package com.saila.view.activity

import com.saila.R
import com.saila.base.BaseActivity
import com.saila.databinding.AnimationLayoutBinding
import com.saila.viewmodel.VmAnimationActivity
import java.util.*

/**
 * Created by Athrun on 2018/3/25.
 */
class AnimationActivity:BaseActivity<AnimationActivity,AnimationLayoutBinding,VmAnimationActivity>() {
    override fun update(o: Observable?, arg: Any?) {

    }

    override fun getIntentData() {

    }

    override fun getContentView(): Int {
        return R.layout.animation_layout
    }

    override fun getViewModel(): VmAnimationActivity {
        return VmAnimationActivity(context,mUtil,this)
    }

    override fun presenterTask() {
    }

}