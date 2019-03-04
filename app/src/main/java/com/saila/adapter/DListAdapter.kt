package com.saila.adapter

import android.content.Context
import android.databinding.DataBindingComponent
import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.saila.BR
import com.saila.R
import com.saila.databinding.DlistItemBinding
import com.saila.util.SlaComponent
import com.saila.util.parse.DListResponse
import com.saila.viewmodel.item.VmItemDList

/**
 * Created by Athrun on 2018/3/26.
 */
class DListAdapter(var c:Context?) : RecyclerView.Adapter<DListAdapter.HolderDList>(){


    var inflater:LayoutInflater?=null
    init {
        inflater= LayoutInflater.from(c)
    }
    var list:MutableList<DListResponse.Msg.Companion.Bean>?=null

    open fun updateData(list2 : MutableList<DListResponse.Msg.Companion.Bean>?){
        this.list=list2
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HolderDList {

        return HolderDList(DataBindingUtil.inflate(inflater!!,R.layout.dlist_item
                ,parent
                ,false,SlaComponent()))
    }

    override fun getItemCount(): Int {
        if (list==null){
            return 0
        }
        return list!!.size
    }


    override fun onBindViewHolder(holder: HolderDList, position: Int) {
        if (list==null){
            return
        }
        if (holder is HolderDList){
            holder.bindSth(list!!.elementAt(position))
        }
    }
    class HolderDList : RecyclerView.ViewHolder {
       lateinit var  itemBinding:DlistItemBinding
        constructor( itemBinding2 :DlistItemBinding) : super(itemBinding2.root) {
            this.itemBinding=itemBinding2
        }

        fun bindSth(info22: DListResponse.Msg.Companion.Bean){
            if (itemBinding.vmItem==null){
                itemBinding.setVariable(BR.vmItem,VmItemDList(info22,itemView.context))
            }else{
                itemBinding!!.vmItem?.setData(info22)
            }

        }
    }
}