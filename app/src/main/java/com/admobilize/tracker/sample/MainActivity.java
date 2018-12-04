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

    private String action_enable;
    private String action_start;
    private String action_stop;

    public static final String KEY_DETECTOR_ID   = "key_adm_detector_id";
    public static final String KEY_CAMERA_ENABLE = "key_camera_enable";

    private String detectorId;
    private TextView tvDeviceId;
    private Button btnGetDeviceId;
    private Button btnStartStopService;
    private boolean toggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnGetDeviceId      = findViewById(R.id.btn_get_device_id);
        btnStartStopService = findViewById(R.id.btn_start_stop);
        tvDeviceId = findViewById(R.id.tv_deviceId);

        btnGetDeviceId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               getDetectorId();
            }
        });

        btnStartStopService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setServiceEnable(toggle);
            }
        });

    }

    public void getDetectorId() {
        Intent intent = new Intent(action_get_detector_id);
        sendBroadcast(intent);
    }

    public void setServiceEnable(boolean enable) {
        Intent intent = new Intent(action_enable);
        intent.putExtra(KEY_CAMERA_ENABLE, enable);
        intent.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
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
                    if(webhookdata!=null){
                        Log.d(TAG,"webhook: "+webhookdata);
                    }
                }
            }
            else if (action.equals(action_on_detector_id)){
                Bundle extras = intent.getExtras();
                if (extras != null){
                   detectorId = extras.getString(KEY_DETECTOR_ID);
                   Log.d(TAG,"detectorId: "+detectorId);
                   tvDeviceId.setText(detectorId);
                   configReceiver(detectorId);
                }
            }
            else if(action.equals(action_start)) {

                Log.d(TAG,"service start");
                btnStartStopService.setText("STOP BACKGROUND SERVICE");

            }
            else if(action.equals(action_stop)) {

                Log.d(TAG,"service stop");
                btnStartStopService.setText("START BACKGROUND SERVICE");

            }

        }
    };

    private void configReceiver(String detectorId) {

        action_enable = "ACTION"+detectorId+"ENABLE";
        action_start =  "ACTION"+detectorId+"SERVICE_START";
        action_stop = "ACTION"+detectorId+"SERVICE_STOP";

        IntentFilter intentFilter = new IntentFilter();

        intentFilter.addAction(action_detector_webhook);
        intentFilter.addAction(action_get_detector_id);
        intentFilter.addAction(action_on_detector_id);
        intentFilter.addAction(action_enable);
        intentFilter.addAction(action_start);
        intentFilter.addAction(action_stop);

        registerReceiver(mReceiver, intentFilter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        configReceiver(detectorId);
    }

    @Override
    protected void onDestroy() {
        unregisterReceiver(mReceiver);
        super.onDestroy();
    }

}
