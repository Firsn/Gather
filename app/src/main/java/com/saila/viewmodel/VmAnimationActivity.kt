package com.saila.viewmodel

import android.content.Context
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import com.saila.R
import com.saila.util.Util
import com.saila.view.activity.AnimationActivity

/**
 * Created by Athrun on 2018/3/25.
 */
class VmAnimationActivity (c: Context, u:Util?,cls:AnimationActivity):BaseViewModel<AnimationActivity>(c,u,cls){
    var aIn:Animation?=null
    var aOut:Animation?=null

    init {

    }

    override fun onCreate() {
//        ACTIVITY?.getBIND()?.tvAnimation
         aIn=AnimationUtils.loadAnimation(context, R.anim.alerter_slide_in_from_top)
         aOut=AnimationUtils.loadAnimation(context, R.anim.alerter_slide_out_to_top)

        aIn?.setAnimationListener(object :Animation.AnimationListener{
            override fun onAnimationRepeat(animation: Animation?) {
            }

            override fun onAnimationEnd(animation: Animation?) {
                ACTIVITY?.getBIND()?.tvAnimation?.visibility= View.VISIBLE
            }

            override fun onAnimationStart(animation: Animation?) {
                ACTIVITY?.getBIND()?.tvAnimation?.visibility= View.VISIBLE
            }
        })
        ACTIVITY?.getBIND()?.tvAnimation?.animation=aIn
    }


    override fun taskFinish() {
        ACTIVITY?.finish()
    }

    override fun onBackPressed() {
        taskFinish()
    }

    open fun commandIn(){
        ACTIVITY!!.getBIND()?.tvAnimation?.animation=aIn
    }

    open fun commandOut(){
        ACTIVITY!!.getBIND()?.tvAnimation?.animation=aOut
    }

}