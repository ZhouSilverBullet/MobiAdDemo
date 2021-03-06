package com.mobi.mobiaddemo;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;

import com.mobi.core.AdParams;
import com.mobi.core.common.MobiPubSdk;
import com.mobi.core.feature.SplashAdView;
import com.mobi.core.listener.ISplashAdListener;
import com.mobi.core.splash.BaseSplashSkipView;
import com.mobi.core.splash.DefaultSplashSkipView;
import com.mobi.core.strategy.StrategyError;

import java.util.List;

public class SplashActivity extends AppCompatActivity {

    public static final String TAG = "SplashActivity";
    ViewGroup clRoot;
    private boolean mIsSelfSplash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        clRoot = findViewById(R.id.flRoot);
        clRoot.post(() -> showSplash(clRoot));

        if (getIntent() != null) {
            mIsSelfSplash = getIntent().getBooleanExtra("isSelfSplash", false);
        }
    }

    boolean canSkip;

    private void showSplash(ViewGroup clRoot) {
        BaseSplashSkipView view = null;
        if (mIsSelfSplash) {
            view = new DefaultSplashSkipView();
        }

        AdParams adParams = new AdParams.Builder()
                .setCodeId("3014001")
                .setImageAcceptedSize(1080, 1920)
                .setSupportDeepLink(true)
                .build();

        MobiPubSdk.loadSplash(this, clRoot, view, adParams, new ISplashAdListener() {
            @Override
            public void onAdFail(List<StrategyError> strategyErrorList) {
                for (StrategyError strategyError : strategyErrorList) {
                    Log.e(TAG, "onLoadFailed type : " + strategyError.getProviderType()
                            + " faildCode : " + strategyError.getCode() + ", faildMsg: " + strategyError.getMessage());
                }
                delayToHome(1000);
            }

            @Override
            public void onAdStartRequest(@NonNull String providerType) {
                Log.e(TAG, "onAdStartRequest providerType: " + providerType);
            }

            @Override
            public void onAdClick(String type) {
                Log.e(TAG, "onAdClicked " + "providerType: " + type);
            }

            @Override
            public void onAdExposure(String type) {
                Log.e(TAG, "onAdExposure " + "providerType: " + type);
            }

            @Override
            public void onAdClose(String type) {
                Log.e(TAG, "onAdDismissed " + "providerType: " + type);

                next();

            }

            @Override
            public void onAdLoad(String providerType, SplashAdView view) {
                Log.e(TAG, "onAdLoaded " + "providerType: " + providerType);
                if (view != null) {
                    view.show();
                }
            }
        });
    }

    private void delayToHome(int delayMillis) {
        clRoot.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
                finish();
            }
        }, delayMillis);
    }

    @Override
    protected void onPause() {
        super.onPause();
        canSkip = false;
    }

    @Override
    protected void onResume() {
        super.onResume();
        next();
        canSkip = true;
    }

    /**
     * 设置一个变量来控制当前开屏页面是否可以跳转，当开屏广告为普链类广告时，点击会打开一个广告落地页，此时开发者还不能打开自己的App主页。当从广告落地页返回以后，
     * 才可以跳转到开发者自己的App主页；当开屏广告是App类广告时只会下载App。
     */
    private void next() {
        if (canSkip) {
            this.startActivity(new Intent(this, MainActivity.class));
            this.finish();
        } else {
            canSkip = true;
        }
    }
}