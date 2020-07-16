package com.mobi.mobiaddemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;

import com.mobi.core.AdParams;
import com.mobi.core.ConstantValue;
import com.mobi.core.common.MobiPubSdk;
import com.mobi.core.feature.ExpressAdView;
import com.mobi.core.feature.FullscreenAdView;
import com.mobi.core.feature.InteractionAdView;
import com.mobi.core.feature.RewardAdView;
import com.mobi.core.listener.IExpressListener;
import com.mobi.core.listener.IFullScreenVideoAdListener;
import com.mobi.core.listener.IInteractionAdListener;
import com.mobi.core.listener.IRewardAdListener;
import com.mobi.core.strategy.StrategyError;
import com.mobi.core.utils.LogUtils;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = "MainActivity";
    private ViewGroup flContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        flContainer = findViewById(R.id.flContainer);
    }

    /**
     * 信息流
     * @param view
     */
    public void btnExpress(View view) {
        AdParams adParams = new AdParams.Builder()
                .setCodeId("1024001")
                .setAdCount(1)
                .setImageAcceptedSize(640, 320)
                .setExpressViewAcceptedSize(350, 0)
                .build();

        MobiPubSdk.loadNativeExpress(this, flContainer, adParams, new IExpressListener() {
            @Override
            public void onAdClick(String type) {
                LogUtils.e(TAG, "onAdClick type : " + type);
            }

            @Override
            public void onAdLoad(String type, ExpressAdView view) {
                LogUtils.e(TAG, "onAdLoad type : " + type);
                if (view != null) {
                    view.show();
                }
            }

            @Override
            public void onAdFail(List<StrategyError> strategyErrorList) {
                for (StrategyError strategyError : strategyErrorList) {
                    LogUtils.e(TAG, "onLoadFailed type : " + strategyError.getProviderType()
                            + " faildCode : " + strategyError.getCode() + ", faildMsg: " + strategyError.getMessage());
                }
            }

            @Override
            public void onAdClose(String type) {
                LogUtils.e(TAG, "onAdDismissed type : " + type);

            }

            @Override
            public void onAdRenderSuccess(String type) {
                LogUtils.e(TAG, "onAdRenderSuccess type : " + type);
            }

            @Override
            public void onAdExposure(String type) {
                LogUtils.e(TAG, "onAdShow type : " + type);
            }
        });
    }

    /**
     * 插屏
     * @param view
     */
    public void btnInteraction(View view) {
        AdParams adParams = new AdParams.Builder()
                .setCodeId("1024004")
                .build();
        MobiPubSdk.loadInteractionExpress(this, adParams, new IInteractionAdListener() {
            @Override
            public void onAdExposure(String s) {

            }

            @Override
            public void onAdClick(String s) {

            }

            @Override
            public void onAdClose(String s) {

            }

            @Override
            public void onAdFail(List<StrategyError> list) {

            }

            @Override
            public void onAdLoad(String s, InteractionAdView iExpressAdView) {
                if (iExpressAdView != null) {
                    iExpressAdView.show();
                }
            }
        });
    }


    /**
     * 激励视频
     * @param view
     */
    public void btnReward(View view) {
        AdParams adParams = new AdParams.Builder()
                .setAutoShowAd(false)
                .setCodeId("1024003")
                .build();

        MobiPubSdk.loadRewardView(this, adParams, new IRewardAdListener() {
            @Override
            public void onAdFail(List<StrategyError> strategyErrorList) {
                for (StrategyError strategyError : strategyErrorList) {
                    LogUtils.e(TAG, "onLoadFailed type : " + strategyError.getProviderType()
                            + " faildCode : " + strategyError.getCode() + ", faildMsg: " + strategyError.getMessage());
                }
            }

            @Override
            public void onAdLoad(String type, RewardAdView view) {
                LogUtils.e(TAG, "onAdLoad type : " + type);
                if (view != null) {
                    view.show();
                }
            }

            @Override
            public void onAdExpose(String type) {
                LogUtils.e(TAG, "onAdExpose type : " + type);
            }

            @Override
            public void onAdShow(String type) {
                LogUtils.e(TAG, "onAdGdtShow type : " + type);
            }

            @Override
            public void onAdClick(String type) {
                LogUtils.e(TAG, "onAdLoad type : " + type);
            }

            @Override
            public void onAdClose(String providerType) {
                LogUtils.e(TAG, "onAdClose type : " + providerType);
            }

            @Override
            public void onVideoComplete(String providerType) {
                LogUtils.e(TAG, "onVideoComplete type : " + providerType);
            }

            @Override
            public void onSkippedVideo(String providerType) {
                LogUtils.e(TAG, "onSkippedVideo type : " + providerType);
            }

            @Override
            public void onRewardVerify(String providerType, boolean rewardVerify, int rewardAmount, String rewardName) {
                LogUtils.e(TAG, "onRewardVerify type : " + providerType);
            }

            @Override
            public void onCached(String type) {
                LogUtils.e(TAG, "onCached type : " + type);
            }
        });
    }

    /**
     * 全屏视频
     * @param view
     */
    public void btnFullScreen(View view) {
        AdParams adParams = new AdParams.Builder()
                .setCodeId("1024002")
                .setOrientation(ConstantValue.VERTICAL)
                .build();

        MobiPubSdk.loadFullscreen(this, adParams, new IFullScreenVideoAdListener() {
            @Override
            public void onAdFail(List<StrategyError> strategyErrorList) {
                for (StrategyError strategyError : strategyErrorList) {
                    LogUtils.e(TAG, "onLoadFailed type : " + strategyError.getProviderType()
                            + " faildCode : " + strategyError.getCode() + ", faildMsg: " + strategyError.getMessage());
                }
            }

            @Override
            public void onAdExposure(String type) {
                LogUtils.e(TAG, "onAdShow ");
            }

            @Override
            public void onAdLoad(String type, FullscreenAdView view) {
                LogUtils.e(TAG, "onAdLoad ");
                if (view != null) {
                    view.show();
                }
            }

            @Override
            public void onCached(String type) {
                LogUtils.e(TAG, "onCached ");
            }

            @Override
            public void onAdClose(String providerType) {
                LogUtils.e(TAG, "onAdClose ");
            }

            @Override
            public void onVideoComplete(String providerType) {
                LogUtils.e(TAG, "onVideoComplete ");
            }

            @Override
            public void onSkippedVideo(String providerType) {
                LogUtils.e(TAG, "onSkippedVideo ");
            }

            @Override
            public void onAdClick(String providerType) {
                LogUtils.e(TAG, "onAdVideoBarClick ");
            }
        });
    }
}