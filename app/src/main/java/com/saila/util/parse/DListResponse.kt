package com.saila.util.parse

import com.google.gson.annotations.SerializedName
import okhttp3.MediaType
import okhttp3.ResponseBody
import okio.BufferedSource
import org.json.JSONObject
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by Athrun on 2018/3/27.
 */
class DListResponse  {

    companion object {
        open val sdf=SimpleDateFormat("yyyy-MM-dd")
    }


    @SerializedName("msg")
    var msg:Msg?=null

    fun getMsg_():Msg?{
        return msg
    }

    class Msg{
        @SerializedName("list")
        var list:MutableList<Bean>?=null

        fun getListBean():MutableList<Bean>?{
            return list
        }
        fun setListBean( l:MutableList<Bean>?){
            list=l
        }


        companion object {
            var date: Date?=null

            class  Bean{

                var user:User?=null

                fun getUser_():User?{
                    return user
                }
                fun setUser_(u:User?){
                    this.user=u
                }

                fun getCtime_(): String? {


                    try {
                        ctime+="000"
                        date = Date(ctime!!.toLong())
                        ctime=sdf.format(date)
                    } catch (e: Exception) {
                        return ctime
                    }

                    return ctime
                }


                @SerializedName("ctime")
                var ctime:String?=""

                @SerializedName("imgapp")
                var imgapp:String?=""

                @SerializedName("uid")
                var uid:String?=""

                @SerializedName("subject")
                var subject:String?=""

                class User{

                    @SerializedName("username")
                    var username:String?=""

                    @SerializedName("avatar")
                    var avatar:String?=""

                }
            }
        }
    }






//    tid": "146894",
//    "attachment": "1",
//    "count_reply": "2722",
//    "ctime": "1375435091",
//    "essence": "0",
//    "fave": "181",
//    "from": "1",
//    "imgurl": "http:\/\/img6.16fan.com\/attachments\/wenzhang\/201702\/09\/148657235752656ge.jpeg",
//    "lasttime": "1518245825",
//    "subject": "\u3010\u767e\u5bb6\u798f\u73e0\u5b9d\u65fa\u89d2\u9673\u751f\u3011GIA\u94bb\u77f3\u653b\u7565! \u9999\u6e2f\u5fc5\u8bbf\u73e0\u5b9d\u5546\uff018\u6298\u4f18\u60e0\u540e\uff01\u518d\u9001\u6700\u591a1500\u6e2f\u5e63\uff01",
//    "type": "0",
//    "uid": "215164",
//    "zan": "74",
//    "view": "18047",
//    "isTop": 1,
//    "imgapp": "http:\/\/img6.16fan.com\/attachments\/wenzhang\/201702\/09\/148657235752656ge.jpeg-280fengmian",
//    "imgweb": "http:\/\/img6.16fan.com\/attachments\/wenzhang\/201702\/09\/148657235752656ge.jpeg-280fengmian",
//    "user": {
//        "username": "\u767e\u5bb6\u798fGIA\u947d\u6212",
//        "avatar": "http:\/\/bbs.16fan.com\/uc_server\/avatar.php?uid=215164&size=middle"
//    }
}