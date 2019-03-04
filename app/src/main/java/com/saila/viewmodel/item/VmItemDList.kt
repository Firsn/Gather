package com.saila.viewmodel.item

import android.content.Context
import android.databinding.BaseObservable
import android.databinding.BindingAdapter
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.saila.util.parse.DListResponse

/**
 * Created by Athrun on 2018/3/27.
 */
open class VmItemDList(var info2: DListResponse.Msg.Companion.Bean, var c:Context) :BaseObservable() {
    var info: DListResponse.Msg.Companion.Bean?=null
    companion object {
        @BindingAdapter("imageUrl")
        open fun setImageUrl(view: ImageView, url:String){
            Glide.with(view.context).load(url).into(view)
        }
    }
    init {
        info=info2
    }

    fun subject():String?{
        return info?.subject
    }

    fun imgapp():String?{
        return info?.imgapp
    }
    fun ctime():String?{
        return info?.getCtime_()
    }

    fun uid():String?{
        return info?.uid
    }

    fun username():String?{
        return info?.getUser_()?.username
    }

    open fun avatar():String?{
        return info?.getUser_()?.avatar
    }



    fun setData(info22:DListResponse.Msg.Companion.Bean){
        info=info22
        notifyChange()
    }

}