package com.saila.model

import android.os.Parcel
import android.os.Parcelable
import java.io.Serializable

/**
 * Created by Saila on 2018/3/18.
 */
class Info() :BaseModel(),Parcelable {
    var status:String?=""
    var msg:String?=""
    var img:String?=""
    var subject:String?=""
    var time:String?=""
    var list:MutableList<String>? =null

    constructor(parcel: Parcel) : this() {
        status = parcel.readString()
        msg = parcel.readString()
        img = parcel.readString()
        subject = parcel.readString()
        time = parcel.readString()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(status)
        parcel.writeString(msg)
        parcel.writeString(img)
        parcel.writeString(subject)
        parcel.writeString(time)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Info> {
        override fun createFromParcel(parcel: Parcel): Info {
            return Info(parcel)
        }

        override fun newArray(size: Int): Array<Info?> {
            return arrayOfNulls(size)
        }
    }


}