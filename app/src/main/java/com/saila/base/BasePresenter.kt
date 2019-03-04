package com.saila.base

import android.content.Context
import com.saila.util.Util

/**
 * Created by Athrun on 2018/3/21.
 */
open abstract class  BasePresenter<T,M> {
     var context:Context?=null
     abstract fun presentTask1(t:T?,m:M?)
     abstract fun presentTask2(t:T?,m:M?)
     constructor(c:Context){
         this.context=c
     }
     constructor(c:Context,mUtil: Util){
         this.context=c
     }
}