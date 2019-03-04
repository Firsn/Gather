package com.saila.util.api

import android.content.Context
import com.saila.model.P1
import com.saila.util.Util
import com.saila.util.parse.DListResponse
import com.saila.util.parse.ParcelableActivityResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by Athrun on 2018/3/21.
 */
class SlaApi {
    var context:Context
    constructor(c:Context){
        context=c
    }

    fun discListData() :MutableList<DListResponse.Msg.Companion.Bean>?{
       var BASE_URL_="http://" + "app2.16fan.com"+ "/";
       var URL_="app/getarticlelist.html?"+"&version=7.3.1&appcode=1391bd2414a06e8dc859a4edc0d08fa31f"+
               "&ATK="+
               "&appkey=654321"


        var mRetrofit:Retrofit=Retrofit.Builder()
                .baseUrl(BASE_URL_)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        var service:SlaRetrofitService=mRetrofit.create(SlaRetrofitService::class.java)


        var call: Call<DListResponse>

        var map:HashMap<String,String>? = hashMapOf()
        map?.put("type","all")
        map?.put("order","new")
        map?.put("page","1")
        map?.put("destination","100004")
        map?.put("fid","100004")



        try {

            if (map!=null){
                call=service.dataWithMap(URL_,map)
            }else{
                call=service.dataWithMapNo(URL_)
            }
        } catch (e: Exception) {
//            return e.toString()
            Util.log("result9"," exception="+e.toString())
            return null
        }

        var res: Response<DListResponse> =call.execute()
        if (res==null){
            Util.log("result9"," res=null=")
            return null
        }
        if (res.body()==null){
//            return res.message()
            Util.log("result9"," res.body=null"+res.message())
            return null
        }
        var secondPostCode=res.code()
        var result=res.body()?.getMsg_()?.getListBean()
        map=null
        return result

    }

//    fun parcelableActivity():MutableList<ParcelableActivityResponse.Msg.Bean>?{
    fun parcelableActivity(): P1?{
        var baseUrl_="http://app2.16fan.com/"
        var url_="app/LiveList.html?" +
                "&appcode=1391bd2414a06e8dc859a4edc0d08fa31f" +
                "&version=7.3.4&appkey=654321"

        var map:HashMap<String,String>? = hashMapOf<String,String>()
        map?.put("fid","100003")
        map?.put("sort","new")
        map?.put("page","1")

        var p1:P1=P1()

        try {
            var mRetrofit=Retrofit.Builder()
                    .baseUrl(baseUrl_)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            var service =mRetrofit.create(SlaRetrofitService::class.java)
            var call:Call<ParcelableActivityResponse>?=null
            call=service.parcelableActivityApi(url_,map!!)
            var response_:Response<ParcelableActivityResponse>?=call.execute()
//            return response_?.body()?.getMsg_()?.getList_()
            Util.log("result9"," response ="+response_?.toString())
            Util.log("result9"," response message ="+response_?.message())
            Util.log("result9"," response headers =")

            p1.status=response_?.body()?.status
            p1.list=response_?.body()?.getMsg_()?.getList_()
        } catch (e: Exception) {
            p1.msgStr=e.toString()
        }
        return p1

    }
}