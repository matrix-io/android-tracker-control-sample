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


