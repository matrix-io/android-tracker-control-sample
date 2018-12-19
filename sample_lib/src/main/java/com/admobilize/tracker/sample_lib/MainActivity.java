package com.admobilize.tracker.sample_lib;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import admobilize.adtrackerlib.control.ConfigManager;
import admobilize.adtrackerlib.control.OnDetectorActionListener;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = MainActivity.class.getSimpleName();

    private ConfigManager configManager;
    private Button btnStartStopService;
    private TextView tvDeviceId;
    private TextView tvStatus;
    private TextView tvWebhookData;
    private boolean toggle=true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnStartStopService = findViewById(R.id.btn_start_stop);
        tvDeviceId          = findViewById(R.id.tv_deviceId);
        tvStatus            = findViewById(R.id.tv_status);
        tvWebhookData       = findViewById(R.id.tv_webhook_data);

        OnDetectorActionListener onDetectorActionListener = new OnDetectorActionListener() {

            @Override
            public void onChangeName(String name) {

            }

            @Override
            public void onChangeUrl(String url) {

            }

            @Override
            public void onServiceEnable(boolean enable) {

                Log.d(TAG,"onServiceEnable: "+enable);

            }

            @Override
            public void onServiceStart() {

                Log.d(TAG,"onServiceStart");
                btnStartStopService.setText("STOP BACKGROUND SERVICE");
                toggle=false;
                btnStartStopService.setEnabled(true);
                tvStatus.setText("");

            }

            @Override
            public void onServiceStop(String msg) {

                Log.d(TAG,"onServiceStop: "+msg);
                btnStartStopService.setText("START BACKGROUND SERVICE");
                toggle=true;
                btnStartStopService.setEnabled(true);
                tvStatus.setText("");

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
                tvDeviceId.setText(detectorId);

            }

            @Override
            public void onDetectorData(String data) {
                if(data!=null){
                    Log.d(TAG,"display webhook..");
                    int length = data.length();
                    tvWebhookData.setText(StringUtils.formatString(data, 0, length));
                }
            }
        };

        configManager = new ConfigManager(this, onDetectorActionListener);

        btnStartStopService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                configManager.setServiceEnable(toggle);
            }
        });

    }

    @Override
    protected void onStop() {
        super.onStop();
        configManager.setServiceStop("stopping");
    }
}
