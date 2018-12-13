package com.admobilize.tracker.sample_lib;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import admobilize.adtrackerlib.control.ConfigParameters;
import admobilize.adtrackerlib.control.ConfigManager;
import admobilize.adtrackerlib.control.OnDetectorActionListener;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        OnDetectorActionListener onDetectorActionListener = new OnDetectorActionListener() {

            @Override
            public void onChangeName(String name) {

            }

            @Override
            public void onChangeUrl(String url) {

            }

            @Override
            public void onServiceEnable(boolean enable) {

            }

            @Override
            public void onServiceStart() {

            }

            @Override
            public void onServiceStop(String msg) {

            }

            @Override
            public void onRequestConfigDetails() {

            }

            @Override
            public void onUpdateConfigDetails(ConfigParameters params) {

            }

            @Override
            public void onRequestPreview() {

            }

            @Override
            public void onUpdatePreview(byte[] preview) {

            }

            @Override
            public void onUpdateOrientationAllowed(boolean isOrientationAllowed) {

            }

            @Override
            public void onUpdateOrientation(String orientation) {

            }

            @Override
            public void onDetectorId(String detectorId) {

                Log.d(TAG,"onDetectorId: "+detectorId);

            }

            @Override
            public void onDetectorData(String data) {

            }
        };

        ConfigManager configManager = new ConfigManager(this, onDetectorActionListener);



    }
}
