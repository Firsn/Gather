package com.saila.view.custom

import android.content.Context
import android.content.res.TypedArray
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.FrameLayout
import android.widget.ImageButton
import com.saila.R
import com.saila.third.expandableselector.ExpandableItem
import com.saila.third.expandableselector.ExpandableSelectorListener
import com.saila.third.expandableselector.OnExpandableItemClickListener
import com.saila.third.expandableselector.animation.ExpandableSelectorAnimator
import com.saila.util.expand.ExpandableAnimator
import java.util.ArrayList


class ExpandableView:FrameLayout{
    private val DEFAULT_ANIMATION_DURATION = 300

    private lateinit var mExpandableAnimator:ExpandableAnimator
    private var expandableItems: MutableList<ExpandableItem> = mutableListOf()
    private var buttons: MutableList<View> = mutableListOf()
    private lateinit var listener: ExpandableSelectorListener
    private lateinit var clickListener: OnExpandableItemClickListener

    private var hideBackgroundIfCollapsed: Boolean = false
    private var expandedBackground: Drawable? = null
    constructor(c:Context):
            this(c,null){
    }
    constructor(context: Context?, attrs: AttributeSet?) :
            this(context, attrs,0){

    }
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) :
            super(context, attrs, defStyleAttr){

    }
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) :
            super(context, attrs, defStyleAttr, defStyleRes){

    }

    fun init(attrs:AttributeSet?){
        if (attrs!=null){
            var attributes = context.obtainStyledAttributes(attrs, R.styleable.expandable_selector)
            initializeAnimationDuration(attributes)
            initializeHideBackgroundIfCollapsed(attributes)
            initializeHideFirstItemOnCollapse(attributes)
            attributes.recycle()
        }
    }
    private fun initializeAnimationDuration(attributes: TypedArray?){
        if (attributes != null) {
            val animationDuration = attributes.getInteger(R.styleable.expandable_selector_animation_duration,
                    DEFAULT_ANIMATION_DURATION)
            val expandInterpolatorId = attributes.getResourceId(R.styleable.expandable_selector_expand_interpolator,
                    android.R.anim.accelerate_interpolator)
            val collapseInterpolatorId = attributes.getResourceId(R.styleable.expandable_selector_collapse_interpolator,
                    android.R.anim.decelerate_interpolator)
            val containerInterpolatorId = attributes.getResourceId(R.styleable.expandable_selector_container_interpolator,
                    android.R.anim.decelerate_interpolator)
            mExpandableAnimator = ExpandableAnimator(this as View,
                   animationDuration,
                   expandInterpolatorId,
                   collapseInterpolatorId,
                   containerInterpolatorId)
        }
    }

    private fun initializeHideBackgroundIfCollapsed(attributes: TypedArray){
        hideBackgroundIfCollapsed = attributes.getBoolean(R.styleable.expandable_selector_hide_background_if_collapsed, false)
        expandedBackground = background
        updateBackground()
    }

    private fun initializeHideFirstItemOnCollapse(attributes: TypedArray){
        val hideFirstItemOnCollapsed = attributes.getBoolean(R.styleable.expandable_selector_hide_first_item_on_collapse, false)
        mExpandableAnimator.setHideFirstItemOnCollapse(hideFirstItemOnCollapsed)
    }

    private fun updateBackground() {
        if (!hideBackgroundIfCollapsed) {
            return
        }
        if (isExpanded()) {
            setBackgroundDrawable(expandedBackground)
        } else {
            setBackgroundResource(android.R.color.transparent)
        }
    }

    fun isCollapsed(): Boolean {
        return mExpandableAnimator.isCollapsed()
    }

    fun isExpanded(): Boolean {
        return mExpandableAnimator.isExpanded()
    }

    fun setExpandableSelectorListener(listener: ExpandableSelectorListener) {
        this.listener = listener
    }

    fun setOnExpandableItemClickListener(clickListener: OnExpandableItemClickListener) {
        this.clickListener = clickListener
    }

    fun getExpandableItem(expandableItemPosition: Int): ExpandableItem {
        return expandableItems[expandableItemPosition]
    }

    fun showExpandableItems(expandableItems: List<ExpandableItem>) {
        validateExpandableItems(expandableItems)

        reset()
        setExpandableItems(expandableItems)
        renderExpandableItems()
        hookListeners()
        bringChildsToFront(expandableItems)
    }

    fun expand() {
        expandableSelectorAnimator.expand(ExpandableSelectorAnimator.Listener { notifyExpanded() })
        notifyExpand()
        updateBackground()
    }

    fun collapse() {
        expandableSelectorAnimator.collapse(ExpandableSelectorAnimator.Listener {
            updateBackground()
            notifyCollapsed()
        })
        notifyCollapse()
    }

    fun updateExpandableItem(expandableItemPosition: Int, expandableItem: ExpandableItem) {
        validateExpandableItem(expandableItem)
        expandableItems.removeAt(expandableItemPosition)
        expandableItems.add(expandableItemPosition, expandableItem)
        val buttonPosition = buttons.size - 1 - expandableItemPosition
        configureButtonContent(buttons[buttonPosition], expandableItem)
    }

    private fun reset() {
        this.expandableItems = mutableListOf()
        for (button in buttons) {
            removeView(button)
        }
        this.buttons = mutableListOf()
        mExpandableAnimator.reset()
    }

    private fun renderExpandableItems() {
        val numberOfItems = expandableItems.size
        for (i in numberOfItems - 1 downTo 0) {
            val button = initializeButton(i)
            addView(button)
            buttons.add(button)
            mExpandableAnimator.initializeButton(button)
            configureButtonContent(button, expandableItems[i])
        }
        mExpandableAnimator.setButtons(buttons)
    }

    private fun hookListeners() {
        val numberOfButtons = buttons.size
        val thereIsMoreThanOneButton = numberOfButtons > 1
        if (thereIsMoreThanOneButton) {
            buttons[numberOfButtons - 1].setOnClickListener { v ->
                if (isCollapsed()) {
                    expand()
                } else {
                    notifyButtonClicked(0, v)
                }
            }
        }
        for (i in 0 until numberOfButtons - 1) {
            buttons[i].setOnClickListener { v ->
                val buttonIndex = numberOfButtons - 1 - i
                notifyButtonClicked(buttonIndex, v)
            }
        }
    }

    private fun notifyButtonClicked(itemPosition: Int, button: View) {
        if (clickListener != null) {
            clickListener.onExpandableItemClickListener(itemPosition, button)
        }
    }

    private fun initializeButton(expandableItemPosition: Int): View {
        val expandableItem = expandableItems[expandableItemPosition]
        var button: View? = null
        val context = context
        val layoutInflater = LayoutInflater.from(context)
        if (expandableItem.hasTitle()) {
            button = layoutInflater.inflate(R.layout.expandable_item_button, this, false)
        } else {
            button = layoutInflater.inflate(R.layout.expandable_item_image_button, this, false)
        }
        val visibility = if (expandableItemPosition == 0) View.VISIBLE else View.INVISIBLE
        button!!.visibility = visibility
        return button
    }

    private fun configureButtonContent(button: View, expandableItem: ExpandableItem) {
        if (expandableItem.hasBackgroundId()) {
            val backgroundId = expandableItem.backgroundId
            button.setBackgroundResource(backgroundId)
        }
        if (expandableItem.hasTitle()) {
            val text = expandableItem.title
            (button as Button).setText(text)
        }
        if (expandableItem.hasResourceId()) {
            val imageButton = button as ImageButton
            val resourceId = expandableItem.resourceId
            imageButton.setImageResource(resourceId)
        }
    }

    private fun notifyExpand() {
        if (hasListenerConfigured()) {
            listener.onExpand()
        }
    }

    private fun notifyCollapse() {
        if (hasListenerConfigured()) {
            listener.onCollapse()
        }
    }

    private fun notifyExpanded() {
        if (hasListenerConfigured()) {
            listener.onExpanded()
        }
    }

    private fun notifyCollapsed() {
        if (hasListenerConfigured()) {
            listener.onCollapsed()
        }
    }

    private fun hasListenerConfigured(): Boolean {
        return listener != null
    }

    private fun validateExpandableItem(expandableItem: ExpandableItem?) {
        if (expandableItem == null) {
            throw IllegalArgumentException(
                    "You can't use a null instance of ExpandableItem as parameter.")
        }
    }

    private fun validateExpandableItems(expandableItems: List<ExpandableItem>?) {
        if (expandableItems == null) {
            throw IllegalArgumentException(
                    "The List<ExpandableItem> passed as argument can't be null")
        }
    }

    private fun setExpandableItems(expandableItems: List<ExpandableItem>) {
        this.expandableItems = ArrayList(expandableItems)
    }

    private fun bringChildsToFront(expandableItems: List<ExpandableItem>) {
        val childCount = childCount
        val numberOfExpandableItems = expandableItems.size
        if (childCount > numberOfExpandableItems) {
            for (i in 0 until childCount - numberOfExpandableItems) {
                getChildAt(i).bringToFront()
            }
        }
    }
}