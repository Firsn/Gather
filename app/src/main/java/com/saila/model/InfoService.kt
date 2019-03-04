package com.saila.model

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by Athrun on 2018/4/13.
 */
class InfoService() :BaseModel(),Parcelable {
    constructor(parcel: Parcel) : this() {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {

    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<InfoService> {
        override fun createFromParcel(parcel: Parcel): InfoService {
            return InfoService(parcel)
        }

        override fun newArray(size: Int): Array<InfoService?> {
            return arrayOfNulls(size)
        }
    }

}