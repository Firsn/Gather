package com.saila.util.api

import com.saila.util.parse.DListResponse
import com.saila.util.parse.ParcelableActivityResponse
import retrofit2.Call
import okhttp3.ResponseBody
import retrofit2.http.*

/**
 * Created by Athrun on 2018/3/21.
 */
interface SlaRetrofitService {

    @POST("ali/{url}")
    fun <T> dataWithUrl(@Path("url")  url:String): Call<T>

    @POST("ali/{url}")
    fun dataWithUrl2(@Path("url")  url:String): Call<ResponseBody>

    @POST
    @FormUrlEncoded
    fun  dataWithMap(@Url  url:String, @FieldMap  map:Map<String, String>):Call<DListResponse>

    @POST
    fun dataWithMapNo(@Url url:String ):Call<DListResponse>

    @POST
    @FormUrlEncoded
    fun parcelableActivityApi(@Url ur:String,@FieldMap map:Map<String,String>):Call<ParcelableActivityResponse>



//     @POST("ali/{url}")
//    Call<ResponseBody> dataWithUrl(@Path("url") String url);
//
////    @POST
////    Call<ResponseBody> dataWithMap(@Url String url, @QueryMap Map<String,String> map);
//
//    //@FormUrlEncoded
//
//    @POST
//    @FormUrlEncoded
//    Call<ResponseBody> dataWithMap(@Url String url, @FieldMap Map<String, String> map);
//
//    @POST
//    Call<ResponseBody> dataWithMapNo(@Url String url);
}