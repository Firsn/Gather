package com.saila.view.activity

import com.saila.R
import com.saila.adapter.DListAdapter
import com.saila.base.BaseActivity
import com.saila.databinding.DiscListLayoutBinding
import com.saila.util.Util
import com.saila.viewmodel.VmDList
import java.util.*

/**
 * Created by Athrun on 2018/3/21.
 */
class DList : BaseActivity<DList,DiscListLayoutBinding, VmDList>() {
    override fun update(o: Observable?, arg: Any?) {

        if (o is VmDList){
//            var adapter=DListAdapter(context)
            Util.log("result9"," DList update="+binding)

            var adapter:DListAdapter?=(binding!!.recyclerViewDl1.adapter )as DListAdapter
            adapter?.updateData((o as VmDList).getListData())
        }
    }

    override fun presenterTask() {
    }

    override fun getIntentData() {

    }

    override fun getContentView(): Int {
        return R.layout.disc_list_layout
    }

    override fun getViewModel(): VmDList {
        return VmDList(context, mUtil,this)
    }
}