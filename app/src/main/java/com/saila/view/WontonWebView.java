package com.saila.view;

import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.saila.view.activity.WelcomeActivity;

/**
 * Created by Saila on 2018/3/20.
 */

public class WontonWebView extends WebView{

    public interface BehaviorWebView{
        public void  beHaviorClickUrl(WebView view,String url,int code,String type);
        public void  beHaviorPageFinished();
        public void  beHaviorPrompt(String message,String value);
    }
    public BehaviorWebView mBehavior=null;
    public void setBehaviorListener(BehaviorWebView mBehavior) {
        this.mBehavior = mBehavior;
    }

    private Context contextC=null;
    public WontonWebView(Context context) {
        super(context);
        init(context);
    }

    public WontonWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public WontonWebView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public WontonWebView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    public WontonWebView(Context context, AttributeSet attrs, int defStyleAttr, boolean privateBrowsing) {
        super(context, attrs, defStyleAttr, privateBrowsing);
        init(context);
    }

    private void init(Context context){
       this.contextC=context;
        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.HONEYCOMB){
            removeJavascriptInterface("searchBoxJavaBridge_");
            removeJavascriptInterface("accessibility");
            removeJavascriptInterface("accessibilityTraversal");
        }


        setVerticalScrollBarEnabled(false);
        setHorizontalScrollBarEnabled(false);

        getSettings().setJavaScriptEnabled(true);
        getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
        getSettings().setAppCacheEnabled(false);
        getSettings().setDatabaseEnabled(false);
        getSettings().setDomStorageEnabled(true);

        getSettings().setLoadsImagesAutomatically(true);
        // getSettings().setDatabasePath(cacheWebPath);
        // getSettings().setAppCachePath(cacheWebPath);
        getSettings().setUseWideViewPort(true);
        getSettings().setLoadWithOverviewMode(true);
        getSettings().setDefaultTextEncodingName("UTF-8");

        getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        getSettings().setBuiltInZoomControls(false); // 设置显示缩放按钮
        getSettings().setSupportZoom(false); // 支持缩放
        setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);

//        addJavascriptInterface(new RelevantJS(), "mRelevantJS");

        setWebChromeClient(new WontonChromeClient());
        setWebViewClient(new WontonClient());

        if (contextC instanceof WelcomeActivity){
            loadUrl("file:///android_asset/aw_.html");
//            loadUrl("file:///android_asset/articleDetail.html");
            //
        }

    }

    class WontonClient extends WebViewClient {
        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
//            loadUrl("file:///android_asset/aw_.html");
            if(mBehavior!=null){
                mBehavior.beHaviorPageFinished();
            }
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            if(mBehavior!=null){
                mBehavior.beHaviorClickUrl(view,url,1,"");
            }
            return true;
        }

        public WontonClient() {
            super();
        }
    }

    class WontonChromeClient extends WebChromeClient {
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            super.onProgressChanged(view, newProgress);
            if(newProgress==100){
            }
        }

        @Override
        public void onReceivedTitle(WebView view, String title) {
            super.onReceivedTitle(view, title);
        }

        @Override
        public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
            result.confirm();
            return super.onJsAlert(view, url, message, result);
        }

        @Override
        public boolean onJsConfirm(WebView view, String url, String message, JsResult result) {
            return super.onJsConfirm(view, url, message, result);
        }

        @Override
        public boolean onJsPrompt(WebView view, String url, String message, String defaultValue
                , JsPromptResult result) {

            result.confirm("android callback");
            if (mBehavior!=null){
                mBehavior.beHaviorPrompt(message,defaultValue);
            }
            return true;
//            return super.onJsPrompt(view, url, message, defaultValue, result);
        }

        @Override
        public boolean onJsBeforeUnload(WebView view, String url, String message, JsResult result) {
            return super.onJsBeforeUnload(view, url, message, result);
        }

        public WontonChromeClient() {
            super();
        }
    }

    public void loadJs(String trigger) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {//4.4
//		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {// 4.0
            evaluateJavascript(trigger, null);
        } else {
            loadUrl(trigger);
        }
    }

}
