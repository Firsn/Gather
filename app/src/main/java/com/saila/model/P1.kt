package com.saila.model

import android.os.Parcel
import android.os.Parcelable
import com.saila.util.parse.ParcelableActivityResponse

/**
 * Created by Athrun on 2018/4/23.
 */
class P1() :Parcelable {
    var status:String?=null
    var list:MutableList<ParcelableActivityResponse.Msg.Bean>?=null;
    var msgStr:String?=null

    constructor(parcel: Parcel) : this() {
        status = parcel.readString()
        msgStr = parcel.readString()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(status)
        parcel.writeString(msgStr)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<P1> {
        override fun createFromParcel(parcel: Parcel): P1 {
            return P1(parcel)
        }

        override fun newArray(size: Int): Array<P1?> {
            return arrayOfNulls(size)
        }
    }
}