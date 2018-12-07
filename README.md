<a href="https://github.com/matrix-io/android-tracker-control-sample/blob/master/screenshots/main.png"><img src="https://github.com/matrix-io/android-tracker-control-sample/blob/master/screenshots/main.png" align="right" width="240" ></a>

# Android Detector Control Sample

Controling and receiving data from Android Detector App (builtin, cameraIp, AndroidThings AdBeacon detectors on Android). 

## Current Intent Actions

- [X] Admobilize Broadcast Detection Data (webhook data)
- [X] Action `get_detector_id`
- [X] Action `enable`/`disable` detection service
- [X] Action `start` service notification
- [X] Action `stop`  service notification
- [ ] Action `status` service
- [ ] Action `orientation` config
- [ ] Action `setCameraIpURL` config
- [ ] Action `setRearOrFront` camera config
- [ ] Other camera settings

## Implementation Sample

### Compiling

```javascript
./gradlew assembleDebug
```

### Installing Steps

1. Install `AdBuiltin` or `AdBuiltin2` Camera
2. Install `AdLauncher` and login on it
3. [OPTIONAL] Install `AdVisor`
4. Reboot
5. Install Sample app: `./gradlew installDebug`
6. Launch Sample App
7. Click on `GET DETECTOR` ID button
8. If you got a ID detector click on `START/STOP` service

For more details please see oficial documentation

### Implementation Details

Register Broadcast Receiver with `detectorId`.

``` java
    private void configReceiver(String detectorId) {

        action_enable = "ACTION"+detectorId+"ENABLE";
        action_start  = "ACTION"+detectorId+"SERVICE_START";
        action_stop   = "ACTION"+detectorId+"SERVICE_STOP";

        IntentFilter intentFilter = new IntentFilter();

        intentFilter.addAction(action_detector_webhook);
        intentFilter.addAction(action_get_detector_id);
        intentFilter.addAction(action_on_detector_id);
        intentFilter.addAction(action_enable);
        intentFilter.addAction(action_start);
        intentFilter.addAction(action_stop);

        registerReceiver(mReceiver, intentFilter);
    }
```

Start/Stop background detection service:

``` java
    public void setServiceEnable(boolean enable) {
        Intent intent = new Intent(action_enable);
        intent.putExtra(KEY_CAMERA_ENABLE, enable);
        intent.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
        sendBroadcast(intent);
        btnStartStopService.setEnabled(false);
    }
```

You can get detectorId from dashboard, optional get detectorId via broadcast:

``` java
    public void getDetectorId() {
        Intent intent = new Intent(action_get_detector_id);
        sendBroadcast(intent);
    }
```

Handling intent actions and callback messages:


``` java
    private BroadcastReceiver mReceiver = new BroadcastReceiver() {

        @Override
        public void onReceive (Context context, Intent intent) {

            String action = intent.getAction();
            if (action.equals(action_detector_webhook)) {

            }
            else if (action.equals(action_on_detector_id)){

            }
            else if(action.equals(action_start)) {

            }
            else if(action.equals(action_stop)) {

            }

        }

    };
```


