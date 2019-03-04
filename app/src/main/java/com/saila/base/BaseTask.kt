package com.saila.base

import android.content.Context
import com.saila.util.Util

/**
 * Created by Athrun on 2018/3/21.
 */
open abstract class BaseTask {
    abstract fun task1()
    abstract fun task2()
    constructor(c:Context)
    constructor(c:Context,mUtil:Util)
}