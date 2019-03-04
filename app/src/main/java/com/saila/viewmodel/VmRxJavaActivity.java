package com.saila.viewmodel;

import android.content.Context;
import android.util.Log;

import com.saila.util.Util;
import com.saila.view.activity.RxJavaActivity;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by Athrun on 2018/3/22.
 */

public class VmRxJavaActivity extends BaseViewModel<RxJavaActivity> {
    public VmRxJavaActivity(@NotNull Context context, @Nullable Util mUtil,RxJavaActivity ja) {
        super(context, mUtil,ja);
    }

    @Override
    public void onCreate() {
        observable_observer1();
        observable_observer2();
    }

    @Override
    public void taskFinish() {
        getACTIVITY().finish();
    }

    @Override
    public void onBackPressed() {
        taskFinish();
    }

    private void  observable_observer1(){
        Observable <Integer>observable=Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                e.onNext(1);
                e.onNext(2);
                e.onNext(3);
                e.onComplete();
            }
        });


        Observer<Integer> observer=new Observer<Integer>(){
            @Override
            public void onSubscribe(Disposable d) {
                Log.i("result9"," onSubscribe="+d);
            }

            @Override
            public void onNext(Integer value) {

                Log.i("result9"," onNext="+value);
            }

            @Override
            public void onError(Throwable e) {
                Log.i("result9"," onError="+e.toString());
            }

            @Override
            public void onComplete() {
                Log.i("result9"," onComplete=");
            }
        };
        observable.subscribe(observer);
    }

    private void observable_observer2(){

        Observable.create(new ObservableOnSubscribe<Integer>(){
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {

            }
        }).subscribe(new Observer<Integer>(){
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Integer value) {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }
}
