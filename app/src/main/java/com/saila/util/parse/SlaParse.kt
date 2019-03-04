package com.saila.util.parse

import android.content.Context
import android.util.Log
import com.saila.model.Info
import com.saila.util.Util
import org.json.JSONArray
import org.json.JSONObject

/**
 * Created by Athrun on 2018/3/21.
 */
open class SlaParse {

    var context: Context
    constructor(c: Context){
        context=c
    }

    fun parseDiscList(str:String?): MutableList<Info>?{
        Util.log("result9"," str="+str)
        var info=Info()
        var list:MutableList<Info>?= mutableListOf<Info>()
        try {
            var obj:JSONObject?=null
            obj= JSONObject(str)
            if (obj==null){
                return list
            }
            var status:String=obj.optString("status")
            var msg_:String=obj.optString("msg")
            info.status=status
            info.msg=msg_
            if (!"1".contentEquals(status)){
                return list
            }
            var objMsg: JSONObject?=null
            objMsg=obj.optJSONObject("msg")
            if (objMsg==null||objMsg.length()==0){
                return list
            }
            var arrayList: JSONArray?=null
            arrayList=objMsg.optJSONArray("list")
            if (arrayList==null||arrayList.length()==0){
                return list
            }
            var obj2:JSONObject?=null
            var objUser:JSONObject?=null
            var subject:String?=""
            var img:String?=""
            var time:String?=""
            var username:String?=""
            var avatar:String?=""
            var info3:Info?=null
            for(i in 0..(arrayList.length()-1)){
                obj2=arrayList.optJSONObject(i)
                if (obj2==null){
                    break
                }
                subject=obj2.optString("subject")
                img=obj2.optString("imgapp")
                time=obj2.optString("ctime")
                objUser=obj2.optJSONObject("user");
                if(objUser!=null){
                    username=objUser.optString("username")
                    avatar=objUser.optString("avatar")
                }

                info3=Info()
                info3.subject=subject
//                info3.imgapp=img
//                info3.ctime=time
//                info3.user?.username=username
//                info3.user?.avatar=avatar
                list?.add(info3)
                info3=null
            }
        } catch (e: Exception) {
        }


        return list
    }


}