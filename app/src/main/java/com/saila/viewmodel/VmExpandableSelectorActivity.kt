package com.saila.viewmodel

import android.content.Context
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.saila.R
import com.saila.databinding.ExpandableSelectorLayoutBinding
import com.saila.third.expandableselector.ExpandableItem
import com.saila.third.expandableselector.ExpandableSelector
import com.saila.third.expandableselector.ExpandableSelectorListener
import com.saila.third.expandableselector.OnExpandableItemClickListener
import com.saila.util.Util
import com.saila.view.activity.ExpandableSelectorActivity
import java.util.ArrayList

/**
 * Created by Athrun on 2019/2/28.
 */
class VmExpandableSelectorActivity(var c: Context, var u: Util?) : BaseViewModel<ExpandableSelectorActivity>(c, u) {
    private lateinit var colorsHeaderButton: View
    private lateinit var colorsExpandableSelector: ExpandableSelector
    private lateinit var sizesExpandableSelector: ExpandableSelector
    private lateinit var iconsExpandableSelector: ExpandableSelector
    private lateinit var binding: ExpandableSelectorLayoutBinding
    override fun onCreate() {
        
        binding = ACTIVITY?.getBIND()!!
        initializeColorsExpandableSelector()
        initializeSizesExpandableSelector()
        initializeIconsExpandableSelector()
        initializeCloseAllButton()
    }

    override fun taskFinish() {
        ACTIVITY?.finish()
    }


    private fun initializeColorsExpandableSelector() {
//        colorsExpandableSelector = ACTIVITY?.findViewById(R.id.es_colors) as ExpandableSelector
        colorsExpandableSelector = binding.esColors
        val expandableItems = ArrayList<ExpandableItem>()
        expandableItems.add(ExpandableItem(R.drawable.item_brown))
        expandableItems.add(ExpandableItem(R.drawable.item_green))
        expandableItems.add(ExpandableItem(R.drawable.item_orange))
        expandableItems.add(ExpandableItem(R.drawable.item_pink))
        colorsExpandableSelector.showExpandableItems(expandableItems)
//        colorsHeaderButton = ACTIVITY?.findViewById(R.id.bt_colors) as View
        colorsHeaderButton = binding.btColors
        colorsHeaderButton.setOnClickListener(View.OnClickListener {
            colorsHeaderButton.setVisibility(View.INVISIBLE)
            colorsExpandableSelector.expand()
        })
        colorsExpandableSelector.setOnExpandableItemClickListener { index, view ->
            when (index) {
                0 -> colorsHeaderButton.setBackgroundResource(R.drawable.item_brown)
                1 -> colorsHeaderButton.setBackgroundResource(R.drawable.item_green)
                2 -> colorsHeaderButton.setBackgroundResource(R.drawable.item_orange)
                else -> colorsHeaderButton.setBackgroundResource(R.drawable.item_pink)
            }
            colorsExpandableSelector.collapse()
        }
    }

    private fun initializeSizesExpandableSelector() {
//        sizesExpandableSelector = ACTIVITY?.findViewById(R.id.es_sizes) as ExpandableSelector
        sizesExpandableSelector = binding.esSizes
        val expandableItems = ArrayList<ExpandableItem>()
        expandableItems.add(ExpandableItem("XL"))
        expandableItems.add(ExpandableItem("L"))
        expandableItems.add(ExpandableItem("M"))
        expandableItems.add(ExpandableItem("S"))
        sizesExpandableSelector.showExpandableItems(expandableItems)
        sizesExpandableSelector.setOnExpandableItemClickListener(object : OnExpandableItemClickListener {
            override fun onExpandableItemClickListener(index: Int, view: View) {
                when (index) {
                    1 -> {
                        val firstItem = sizesExpandableSelector.getExpandableItem(1)
                        swipeFirstItem(1, firstItem)
                    }
                    2 -> {
                        val secondItem = sizesExpandableSelector.getExpandableItem(2)
                        swipeFirstItem(2, secondItem)
                    }
                    3 -> {
                        val fourthItem = sizesExpandableSelector.getExpandableItem(3)
                        swipeFirstItem(3, fourthItem)
                    }
                }
                sizesExpandableSelector.collapse()
            }

            private fun swipeFirstItem(position: Int, clickedItem: ExpandableItem) {
                val firstItem = sizesExpandableSelector.getExpandableItem(0)
                sizesExpandableSelector.updateExpandableItem(0, clickedItem)
                sizesExpandableSelector.updateExpandableItem(position, firstItem)
            }
        })
    }

    private fun initializeIconsExpandableSelector() {
//        iconsExpandableSelector = ACTIVITY?.findViewById(R.id.es_icons) as ExpandableSelector
        iconsExpandableSelector = binding.esIcons
        val expandableItems = ArrayList<ExpandableItem>()
        var item = ExpandableItem()
        item.setResourceId(R.mipmap.ic_keyboard_arrow_up_black)
        expandableItems.add(item)
        item = ExpandableItem()
        item.setResourceId(R.mipmap.ic_gamepad_black)
        expandableItems.add(item)
        item = ExpandableItem()
        item.setResourceId(R.mipmap.ic_device_hub_black)
        expandableItems.add(item)
        iconsExpandableSelector.showExpandableItems(expandableItems)
        iconsExpandableSelector.setOnExpandableItemClickListener(object : OnExpandableItemClickListener {
            override fun onExpandableItemClickListener(index: Int, view: View) {
                if (index == 0 && iconsExpandableSelector.isExpanded()) {
                    iconsExpandableSelector.collapse()
                    updateIconsFirstButtonResource(R.mipmap.ic_keyboard_arrow_up_black)
                }
                when (index) {
                    1 -> showToast("Gamepad icon button clicked.")
                    2 -> showToast("Hub icon button clicked.")
                }
            }
        })
        iconsExpandableSelector.setExpandableSelectorListener(object : ExpandableSelectorListener {
            override fun onCollapse() {

            }

            override fun onExpand() {
                updateIconsFirstButtonResource(R.mipmap.ic_keyboard_arrow_down_black)
            }

            override fun onCollapsed() {

            }

            override fun onExpanded() {

            }
        })
    }

    private fun initializeCloseAllButton() {
//        val closeButton = ACTIVITY?.findViewById(R.id.bt_close) as Button
        val closeButton = binding.btClose
        closeButton.setOnClickListener(View.OnClickListener {
            colorsExpandableSelector.collapse()
            sizesExpandableSelector.collapse()
            iconsExpandableSelector.collapse()
            updateIconsFirstButtonResource(R.mipmap.ic_keyboard_arrow_up_black)
        })
        colorsExpandableSelector.setExpandableSelectorListener(object : ExpandableSelectorListener {
            override fun onCollapse() {

            }

            override fun onExpand() {

            }

            override fun onCollapsed() {
                colorsHeaderButton.setVisibility(View.VISIBLE)
            }

            override fun onExpanded() {

            }
        })
    }

    private fun updateIconsFirstButtonResource(resourceId: Int) {
        val arrowUpExpandableItem = ExpandableItem()
        arrowUpExpandableItem.setResourceId(resourceId)
        iconsExpandableSelector.updateExpandableItem(0, arrowUpExpandableItem)
    }

    private fun showToast(message: String) {
        Toast.makeText(ACTIVITY, message, Toast.LENGTH_SHORT).show()
    }
}