package com.saila.view.activity

import com.saila.R
import com.saila.base.BaseActivity
import com.saila.databinding.AnimationLayout2Binding
import com.saila.viewmodel.VmAnimationActivity2
import java.util.*

/**
 * Created by Athrun on 2019/3/4.
 */
class AnimationActivity2() :BaseActivity<AnimationActivity2,AnimationLayout2Binding, VmAnimationActivity2>() {
    override fun getIntentData() {

    }

    override fun getContentView(): Int {
        return R.layout.animation_layout2
    }

    override fun getViewModel(): VmAnimationActivity2 {
        return VmAnimationActivity2(context,mUtil)
    }

    override fun presenterTask() {
    }

    override fun update(o: Observable?, arg: Any?) {
    }
}