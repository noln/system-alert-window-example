22/09/2019 - There's now a [Kotlin version of this project](https://github.com/noln/system-alert-window-example-kotlin).

# system-alert-window-example
Example project showing use of **SYSTEM_ALERT_WINDOW** permission on Android 23+, with back button interception.

*!!!IMPORTANT UPDATE!!! [2019-05-08] We all SAW it coming (pun intended), but the Android team have finally given us an idea of where the out-of-app UI interaction that system-alert-window enabled is going. [Confirmed at Google IO 2019: **it's going away**](https://youtu.be/l1e1gHhci70?t=1010). Part of the flakily-recorded "Whats New In Android" talk [mentions it here](https://youtu.be/td3Kd7fOROw?t=7).*

* On first launch, the app will check that it has [permission to draw over other apps](https://developer.android.com/reference/android/Manifest.permission.html), and will show the user the corresponding prompt if not.
* Once permission is granted, opening the app starts a service that inflates a layout and adds it to the screen as an overlay.

<img src="https://cloud.githubusercontent.com/assets/6524043/17489319/46c4fddc-5d52-11e6-8369-b23c59d8a0f6.png" width="400">

*Note: This screenshot shows the example app drawing over the top of another running app: [motorsport-circuits](https://github.com/noln/motorsport-circuits). So don't expect a map to appear when you run the example code! You just get the green box and bugdroid.*

* If any other app is running when the the app launches, they will lose focus, but *onPause* **will not be called** - the app behaves as a system alert, as the title suggests.

* It's deliberately translucent and not full screen so that other apps can be seen running in the background, but you can make the app run full window by modifying the layout XML, and making the background colour opaque.

* [KeyEvent interception](https://github.com/noln/system-alert-window-example/blob/master/app/src/main/java/com/mattfenlon/ghost/MainService.java#L62) is shown on the *back button*; if you try to press the back button whilst the alert is shown, it'll be intecepted and a message shown in the logs.

* Touches are intercepted whilst the alert/app is shown, even though other apps might be visible in the background they can't be interacted with.

* Touch the View itself to close and kill the Service.
