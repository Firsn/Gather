package com.saila.viewmodel

import android.content.Context
import com.saila.R
import com.saila.util.Util
import com.saila.view.activity.Ltp
import net.steamcrafted.lineartimepicker.dialog.LinearDatePickerDialog
import net.steamcrafted.lineartimepicker.dialog.LinearTimePickerDialog

/**
 * Created by Saila on 2018/3/20.
 */
class VmLtp(context2:Context,mutil2:Util?,cls:Ltp)  :BaseViewModel<Ltp>(context2,mutil2,cls){
    var dTimer:LinearTimePickerDialog?=null
    var dDate:LinearDatePickerDialog?=null
    init {

    }
    override fun onCreate() {
        dTimer=LinearTimePickerDialog.Builder.with(context).build()
        dTimer?.show()
        ACTIVITY?.getBIND()!!.tvLtpDate.setOnClickListener{
            date()
        }
        ACTIVITY?.getBIND()!!.tvLtpTime.setOnClickListener{
            time()
        }
    }

    override fun onBackPressed() {
        taskFinish()
    }
    override fun taskFinish() {
        dTimer?.dismiss()
        dDate?.dismiss()
        ACTIVITY?.finish()
    }

    fun time(){
        if (dTimer==null){
            dTimer=LinearTimePickerDialog.Builder.with(context)
                    .setDialogBackgroundColor(R.color.error_color_material)
                    .setPickerBackgroundColor(R.color.colorPrimary)
                    .build()

        }
        dTimer?.show()
    }
    fun date(){
        if (dDate==null){
            dDate=LinearDatePickerDialog.Builder.with(context)
                    .setDialogBackgroundColor(R.color.error_color_material)
                    .setPickerBackgroundColor(R.color.colorPrimary)
                    .build()
        }
        dDate?.show()
    }
}