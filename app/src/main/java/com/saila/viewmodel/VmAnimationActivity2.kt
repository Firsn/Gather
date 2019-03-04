package com.saila.viewmodel

import android.animation.ObjectAnimator
import android.animation.TimeInterpolator
import android.content.Context
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.view.animation.Interpolator
import com.saila.databinding.AnimationLayout2Binding
import com.saila.third.expandableselector.animation.ResizeAnimation
import com.saila.util.Util
import com.saila.view.activity.AnimationActivity2

/**
 * Created by Athrun on 2019/3/4.
 */
class VmAnimationActivity2(var c: Context, var u:Util?):BaseViewModel<AnimationActivity2>(c,u) {
    lateinit var binding:AnimationLayout2Binding
    var Y_ANIMATION="translationY"
    var animationDuration=300L
    private val CONTAINER_ANIMATION_OFFSET = 1.16f
    override fun onCreate() {
        binding=ACTIVITY?.binding!!
    }
    override fun taskFinish() {
        ACTIVITY?.finish()
    }

    fun task1(){
        mUtil?.toastMes("1")
    }
    fun task2(){
        mUtil?.toastMes("2")
        val objectAnimator=createAnimatorForButton(android.R.anim.decelerate_interpolator)
        objectAnimator.start()
    }
    fun createAnimatorForButton(collapseInterpolatorId:Int):ObjectAnimator{
        var interpolator: TimeInterpolator= AnimationUtils.loadInterpolator(context, collapseInterpolatorId);
        val objectAnimator = ObjectAnimator.ofFloat(binding.btnaa22, Y_ANIMATION, 0f)
        objectAnimator.interpolator=interpolator
        objectAnimator.duration=animationDuration
        return objectAnimator
    }

    private fun createResizeAnimation(toWidth: Float,toHeight: Float): ResizeAnimation {
        var interpolator: Interpolator= AnimationUtils.loadInterpolator(context, android.R.anim.decelerate_interpolator);
        val resizeAnimation = ResizeAnimation(container, toWidth, toHeight)
        resizeAnimation.interpolator=interpolator
        resizeAnimation.duration=((animationDuration * CONTAINER_ANIMATION_OFFSET).toLong())
        return resizeAnimation
    }
}