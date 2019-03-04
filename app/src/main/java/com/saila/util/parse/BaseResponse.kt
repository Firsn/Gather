package com.saila.util.parse

import com.google.gson.annotations.SerializedName

/**
 * Created by Athrun on 2018/4/23.
 */
class BaseResponse {

    @SerializedName("status")
     var status:String?=null

    @SerializedName("msg")
     var msgStr:String?=null
}