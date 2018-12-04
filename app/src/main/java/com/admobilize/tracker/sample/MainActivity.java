package com.admobilize.tracker.sample;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    public static final  String action_detector_webhook = "admobilize.broadcast.data";

    private static final String action_get_detector_id  = "ACTION_GET_ADMOBILIZE_DETECTOR_ID";
    private static final String action_on_detector_id   = "ACTION_ON_ADMOBILIZE_DETECTOR_ID";

    public static final String KEY_DETECTOR_ID = "key_adm_detector_id";

    private String detectorId;
    private TextView tvDeviceId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnGetDeviceId = findViewById(R.id.button);
        tvDeviceId = findViewById(R.id.tv_deviceId);

        btnGetDeviceId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               getDetectorId();
            }
        });

    }

    public void getDetectorId() {
        Intent intent = new Intent(action_get_detector_id);
        sendBroadcast(intent);
    }

    private BroadcastReceiver mReceiver = new BroadcastReceiver() {

        @Override
        public void onReceive (Context context, Intent intent) {

            String action = intent.getAction();
            if (action.equals(action_detector_webhook)) {
                Bundle extras = intent.getExtras();
                if (extras != null) {
                    String webhookdata = extras.getString("webhook");
                    Log.d(TAG,"webhook: "+webhookdata);
                }
            }
            else if (action.equals(action_on_detector_id)){
                Bundle extras = intent.getExtras();
                if (extras != null){
                   detectorId = extras.getString(KEY_DETECTOR_ID);
                   Log.d(TAG,"detectorId: "+detectorId);
                   tvDeviceId.setText(detectorId);
                }
            }

        }
    };

    private void configReceiver () {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(action_detector_webhook);
        intentFilter.addAction(action_get_detector_id);
        intentFilter.addAction(action_on_detector_id);

        registerReceiver(mReceiver, intentFilter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        configReceiver();
    }

    @Override
    protected void onDestroy() {
        unregisterReceiver(mReceiver);
        super.onDestroy();
    }

}
