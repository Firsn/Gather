package com.saila.util.expand

import android.animation.Animator
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.animation.TimeInterpolator
import android.view.Gravity
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.view.animation.Interpolator
import android.widget.FrameLayout
import com.saila.third.expandableselector.animation.AbstractAnimationListener
import com.saila.third.expandableselector.animation.ExpandableSelectorAnimator
import com.saila.third.expandableselector.animation.ResizeAnimation
import java.util.ArrayList

/**
 * Created by Athrun on 2019/3/4.
 */
open class ExpandableAnimator(
        view: View,
        animationDuration: Int,
        expandInterpolatorId: Int,
        collapseInterpolatorId: Int,
        containerInterpolatorId: Int) {

    private val Y_ANIMATION = "translationY"
    private val CONTAINER_ANIMATION_OFFSET = 1.16f

    private lateinit var  container: View
    private var animationDuration: Int=0
    private var expandInterpolatorId:Int=0
    private var collapseInterpolatorId:Int=0
    private var containerInterpolatorId:Int=0

    private lateinit var buttons: List<View>
    private var isCollapsed = true
    private var hideFirstItemOnCollapse: Boolean = false

    init {
        this.container = view
        this.animationDuration = animationDuration
        this.expandInterpolatorId = expandInterpolatorId
        this.collapseInterpolatorId = collapseInterpolatorId
        this.containerInterpolatorId = containerInterpolatorId
    }

    fun isCollapsed(): Boolean {
        return isCollapsed
    }

    fun isExpanded(): Boolean {
        return !isCollapsed()
    }

    fun setButtons(buttons: List<View>) {
        this.buttons = buttons
    }

    fun expand(listener: Listener) {
        setCollapsed(false)
        changeButtonsVisibility(View.VISIBLE)
        expandButtons()
        expandContainer(listener)
    }

    fun collapse(listener: Listener) {
        setCollapsed(true)
        collapseButtons()
        collapseContainer(listener)
    }

    fun initializeButton(button: View) {
        changeGravityToBottomCenterHorizontal(button)
    }

    fun setHideFirstItemOnCollapse(hideFirstItemOnCollapsed: Boolean) {
        this.hideFirstItemOnCollapse = hideFirstItemOnCollapsed
    }

    fun reset() {
        this.buttons = ArrayList()
        this.isCollapsed = true
    }

    private fun setCollapsed(isCollapsed: Boolean) {
        this.isCollapsed = isCollapsed
    }

    private fun expandButtons() {
        val numberOfButtons = buttons.size
        val animations = arrayOfNulls<Animator>(numberOfButtons)
        for (i in 0 until numberOfButtons) {
            val button = buttons.get(i)
            val interpolator = getExpandAnimatorInterpolation()
            val toY = calculateExpandedYPosition(i)
            animations[i] = createAnimatorForButton(interpolator, button, toY)
        }
        playAnimatorsTogether(animations)
    }

    private fun collapseButtons() {
        val numberOfButtons = buttons.size
        val interpolator = getCollapseAnimatorInterpolation()
        val animations = arrayOfNulls<Animator>(numberOfButtons)
        for (i in 0 until numberOfButtons) {
            val button = buttons.get(i)
            val toY = 0f
            animations[i] = createAnimatorForButton(interpolator, button, toY)
        }
        playAnimatorsTogether(animations)
    }

    private fun expandContainer(listener: Listener) {
        val toWidth = container.width.toFloat()
        val toHeight = getSumHeight().toFloat()
        val interpolator = getContainerAnimationInterpolator()
        val resizeAnimation = createResizeAnimation(toWidth, interpolator, toHeight, listener)
        container.startAnimation(resizeAnimation)
    }

    private fun collapseContainer(listener: Listener) {
        val toWidth = container.width.toFloat()
        val toHeight = getFirstItemHeight()
        val interpolator = getContainerAnimationInterpolator()
        val resizeAnimation = createResizeAnimation(toWidth, interpolator, toHeight, object : Listener {
            override fun onAnimationFinished() {
                changeButtonsVisibility(View.INVISIBLE)
                listener.onAnimationFinished()
            }
        })
        container.startAnimation(resizeAnimation)
    }

    private fun createAnimatorForButton(interpolator: TimeInterpolator, button: View,
                                        toY: Float): ObjectAnimator {
        val objectAnimator = ObjectAnimator.ofFloat(button, Y_ANIMATION, toY)
        objectAnimator.interpolator = interpolator
        objectAnimator.duration = animationDuration.toLong()
        return objectAnimator
    }

    private fun createResizeAnimation(toWidth: Float, interpolator: Interpolator,
                                      toHeight: Float, listener: Listener): ResizeAnimation {
        val resizeAnimation = ResizeAnimation(container, toWidth, toHeight)
        resizeAnimation.interpolator = interpolator
        resizeAnimation.duration = (animationDuration * CONTAINER_ANIMATION_OFFSET).toLong()
        resizeAnimation.setAnimationListener(object : AbstractAnimationListener() {
            override fun onAnimationEnd(animation: Animation) {
                listener.onAnimationFinished()
            }
        })
        return resizeAnimation
    }

    private fun playAnimatorsTogether(animations: Array<Animator>) {
        val animatorSet = AnimatorSet()
        animatorSet.playTogether(*animations)
        animatorSet.start()
    }

    private fun calculateExpandedYPosition(buttonPosition: Int): Float {
        val numberOfButtons = buttons.size
        var y = 0f
        for (i in numberOfButtons - 1 downTo buttonPosition + 1) {
            val button = buttons.get(i)
            y = y - button.height.toFloat() - getMarginRight(button).toFloat() - getMarginLeft(button).toFloat()
        }
        return y
    }

    private fun changeButtonsVisibility(visibility: Int) {
        val lastItem = if (hideFirstItemOnCollapse) buttons.size else buttons.size - 1
        for (i in 0 until lastItem) {
            val button = buttons.get(i)
            button.visibility = visibility
        }
    }

    private fun getExpandAnimatorInterpolation(): TimeInterpolator {
        return AnimationUtils.loadInterpolator(container.context, expandInterpolatorId)
    }

    private fun getCollapseAnimatorInterpolation(): TimeInterpolator {
        return AnimationUtils.loadInterpolator(container.context, collapseInterpolatorId)
    }

    private fun getContainerAnimationInterpolator(): Interpolator {
        return AnimationUtils.loadInterpolator(container.context, containerInterpolatorId)
    }

    private fun getSumHeight(): Int {
        var sumHeight = 0
        for (button in buttons) {
            sumHeight += button.getHeight() + getMarginRight(button) + getMarginLeft(button)
        }
        return sumHeight
    }

    private fun getMarginRight(view: View): Int {
        val layoutParams = view.layoutParams as FrameLayout.LayoutParams
        return layoutParams.rightMargin
    }

    private fun getMarginLeft(view: View): Int {
        val layoutParams = view.layoutParams as FrameLayout.LayoutParams
        return layoutParams.leftMargin
    }

    private fun getFirstItemHeight(): Float {
        val firstButton = buttons.get(0)
        val height = firstButton.height
        val layoutParams = firstButton.layoutParams as FrameLayout.LayoutParams
        val topMargin = layoutParams.topMargin
        val bottomMargin = layoutParams.bottomMargin
        return (height + topMargin + bottomMargin).toFloat()
    }

    private fun changeGravityToBottomCenterHorizontal(view: View) {
        (view.layoutParams as FrameLayout.LayoutParams).gravity = Gravity.BOTTOM or Gravity.CENTER_HORIZONTAL
    }

    interface Listener {
        fun onAnimationFinished()
    }
}