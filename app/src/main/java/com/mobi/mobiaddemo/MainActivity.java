package com.mobi.mobiaddemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.mobi.core.AdParams;
import com.mobi.core.common.MobiPubSdk;
import com.mobi.core.feature.InteractionAdView;
import com.mobi.core.listener.IInteractionAdListener;
import com.mobi.core.strategy.StrategyError;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

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
}