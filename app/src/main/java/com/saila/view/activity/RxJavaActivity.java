package com.saila.view.activity;

import com.saila.R;
import com.saila.base.BaseActivity;
import com.saila.databinding.RxjavaLayoutBinding;
import com.saila.presenter.Presenter1;
import com.saila.task.Task1;
import com.saila.viewmodel.BaseViewModel;
import com.saila.viewmodel.VmRxJavaActivity;

import org.jetbrains.annotations.NotNull;

import java.util.Observable;

/**
 * Created by Athrun on 2018/3/22.
 */

public class RxJavaActivity extends BaseActivity<RxJavaActivity,RxjavaLayoutBinding,VmRxJavaActivity> {
    @Override
    public void getIntentData() {

    }

    @Override
    public int getContentView() {
        return R.layout.rxjava_layout;
    }

    @NotNull
    @Override
    public VmRxJavaActivity getViewModel() {
        return new VmRxJavaActivity(context,getMUtil(),this);
    }

    @Override
    public void presenterTask() {
        getBinding().setPresenter(new Presenter1(context));
        getBinding().setTask(new Task1(context));
    }

    @Override
    public void update(Observable o, Object arg) {

    }
}
