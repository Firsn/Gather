package com.saila.presenter

import android.content.Context
import com.saila.base.BasePresenter
import com.saila.task.Task1

/**
 * Created by Athrun on 2018/3/21.
 */
class Presenter1(c: Context) :BasePresenter<Task1,Task1>(c) {
    override fun presentTask1(t: Task1?,t1:Task1?) {
        t?.task1()
    }

    override fun presentTask2(t: Task1?,t1:Task1?) {
    }
}