package com.saila.viewmodel

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.support.v7.widget.LinearLayoutManager
import com.baidu.mobstat.StatService
import com.saila.R
import com.saila.adapter.DListAdapter
import com.saila.util.Util
import com.saila.util.parse.DListResponse
import com.saila.view.activity.DList
import com.tapadoo.alerter.Alerter
import java.util.*

/**
 * Created by Athrun on 2018/3/21.
 */
class VmDList(c:Context, mutil2:Util?, cls:DList) :BaseViewModel<DList>(c,mutil2,cls) {
    init {

    }

    var list:MutableList<DListResponse.Msg.Companion.Bean>?= mutableListOf<DListResponse.Msg.Companion.Bean>()
    override fun onCreate() {
        Alerter.create(ACTIVITY as Activity)
                .setTitle("数据加载中...")
                .setText("")
                .setBackgroundColorRes(R.color.colorPrimary)
                .setDuration(5000)
                .show();
        ACTIVITY?.getBIND()?.recyclerViewDl1?.layoutManager=LinearLayoutManager(context)
        ACTIVITY?.getBIND()?.recyclerViewDl1?.adapter=DListAdapter(context)

        Thread{
//            list= mUtil?.parse?.parseDiscList(mUtil?.api?.discListData())

            list=mUtil?.api?.discListData()

//            setChanged()
//            notifyObservers()

            var message=Message()
            var bundle=Bundle()
            bundle.putString("list",list?.size.toString()!!)
            message.data=bundle
            message.what=1
            handler.sendMessage(message)
        }.start()

        StatService.onEvent(ACTIVITY,"1a111","111")

        StatService.onEvent(ACTIVITY,"1a114","222")
        StatService.onEvent(ACTIVITY,"1a114","444")
        StatService.onEvent(ACTIVITY,"1a114","333")

        StatService.onEvent(ACTIVITY,"1a112","111",1)

        var map:HashMap<String,String> =HashMap()
        map.put("大婶","大婶2")
        map.put("小婶","小婶2")
        StatService.onEvent(ACTIVITY,"1a113","111",1,map)

        var map2:HashMap<String,String> = HashMap()
        map2.put("大婶","大婶2")
        map2.put("小婶","小婶2")
        StatService.onEvent(ACTIVITY,"1a115","111",9,map2)



        com.tencent.stat.StatService.trackCustomEvent(ACTIVITY,"1a111","北京")

        var prop=Properties()
        prop.setProperty("a","a2")
        prop.setProperty("b","b2")
        prop.setProperty("c","c2")
        com.tencent.stat.StatService.trackCustomKVEvent(ACTIVITY,"1a112",prop)

        var prop2=Properties()
        prop2.setProperty("文章","第一条")
        prop2.setProperty("问答","第一条")
        prop2.setProperty("景点","第一条")
        prop2.setProperty("美食","第一条")
        com.tencent.stat.StatService.trackCustomKVEvent(ACTIVITY,"1a113",prop2)

        var prop3=Properties()
        prop3.setProperty("文章","第二条")
        prop3.setProperty("问答","第二条")
        prop3.setProperty("景点","第二条")
        prop3.setProperty("美食","第二条")
        com.tencent.stat.StatService.trackCustomKVEvent(ACTIVITY,"1a113",prop3)


        var prop4=Properties()
        prop4.setProperty("文章","第三条")
        prop4.setProperty("问答","第三条")
        prop4.setProperty("景点","第三条")
        prop4.setProperty("美食","第三条")
        com.tencent.stat.StatService.trackCustomKVEvent(ACTIVITY,"1a113",prop4)


        var prop5=Properties()
        prop5.setProperty("文章","第二条")
        prop5.setProperty("问答","第二条")
        prop5.setProperty("景点","第二条")
        prop5.setProperty("美食","第二条")
        com.tencent.stat.StatService.trackCustomKVEvent(ACTIVITY,"1a113",prop5)

    }

    fun getListData():MutableList<DListResponse.Msg.Companion.Bean>?{
        return list
    }

    override fun onBackPressed() {
        taskFinish()
    }

    override fun taskFinish() {
        ACTIVITY?.finish()
    }

    var handler=object:Handler(){
        override fun handleMessage(msg: Message?) {
            super.handleMessage(msg)

            setChanged()
            notifyObservers()

//            var imm=msg?.data?.getString("list")
//            Alerter.create(ACTIVITY as Activity)
//                    .setTitle("数据长度")
//                    .setText(imm)
//                    .setBackgroundColorRes(R.color.colorPrimary)
//                    .setDuration(1000)
//                    .show();
            if (Alerter.isShowing()){
                postDelayed(Runnable {  Alerter.hide() },1000)
            }

        }
    }
}