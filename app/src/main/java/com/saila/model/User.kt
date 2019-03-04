package com.saila.model

import com.google.gson.annotations.SerializedName

/**
 * Created by Athrun on 2018/3/26.
 */
class User : BaseModel() {
    @SerializedName("username")
    var username: String? = ""

    @SerializedName("avatar")
    var avatar: String? = ""

    @SerializedName("uid")
    var uid: String? = ""
}