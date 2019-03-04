package com.saila.view.activity;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.databinding.generated.callback.OnClickListener;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.saila.R;
import com.saila.databinding.ServiceLayout2Binding;
import com.saila.service.SlaService2;
import com.saila.util.Util;

/**
 * Created by Athrun on 2018/4/12.
 */
public class ServiceActivity2 extends AppCompatActivity
implements View.OnClickListener{

    Context context=null;
    ServiceLayout2Binding binding=null;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.service_layout1);
        binding=  DataBindingUtil.setContentView(this,R.layout.service_layout2);
        context=this;
        Util u=new Util(this);
        binding.tvStart.setOnClickListener(this);
        binding.tvStop.setOnClickListener(this);
    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_start:
                Intent intent=new Intent(context, SlaService2.class);
                startService(intent);
                break;
            case R.id.tv_stop:
                Intent intent2=new Intent(context, SlaService2.class);
                stopService(intent2);
                break;
            case R.id.tv_bind:
                break;
            case R.id.tv_unbind:
                break;
                default:
                    break;
        }
    }
}
