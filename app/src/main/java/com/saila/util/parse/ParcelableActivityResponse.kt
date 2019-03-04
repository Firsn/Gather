package com.saila.util.parse

import com.google.gson.annotations.SerializedName
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by Athrun on 2018/4/23.
 */
class ParcelableActivityResponse {
    companion object{
        val sdf=SimpleDateFormat("yyyy-MM-dd")
    }
    @SerializedName("status")
    var status:String?=""

    @SerializedName("msg")
    var msg:Msg?=null

    fun getMsg_():Msg?{
        return msg
    }

    class Msg{

        @SerializedName("list")
        var list:MutableList<Bean>? =null

        fun getList_():MutableList<Bean>?{
            return list
        }
        fun setList_(l:MutableList<Bean>?){
            list=l
        }

        companion object {
            var date_:Date?=null
        }

        class Bean{
            @SerializedName("id")
            var id:String?=""

            @SerializedName("dateline")
            var dateline:String?=""

            @SerializedName("username")
            var username:String?=""

            @SerializedName("content")
            var content:String?=""

            @SerializedName("detail")
            var location:String?=""

            @SerializedName("avatar")
            var avatar:String?=""

            fun getDateline_():String?{
                dateline+="000"
                try {
                    date_=Date(dateline)
                    dateline=sdf.format(date_)
                } catch (e: Exception) {
                }
                return dateline
            }

        }

    }
}