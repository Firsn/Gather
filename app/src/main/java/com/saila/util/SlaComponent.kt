package com.saila.util

import android.databinding.DataBindingComponent
import com.saila.viewmodel.item.VmItemDList

/**
 * Created by Athrun on 2018/3/27.
 */
class SlaComponent :DataBindingComponent {
    override fun getCompanion(): VmItemDList.Companion {
        return VmItemDList.Companion
    }
}