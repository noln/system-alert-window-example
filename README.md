# system-alert-window-example
Example project showing use of **SYSTEM_ALERT_WINDOW** permission on Android 23+, with back button interception.

* On first launch, the app will check that it has [permission to draw over other apps](https://developer.android.com/reference/android/Manifest.permission.html), and will show the user the corresponding prompt if not.
* Once permission is granted, opening the app starts a service that inflates a layout and adds it to the screen as an overlay.

<img src="https://cloud.githubusercontent.com/assets/6524043/17489319/46c4fddc-5d52-11e6-8369-b23c59d8a0f6.png" width="400">

* If any other app is running when the the app launches, they will lose focus, but *onPause* **will not be called** - the app behaves as a system alert, as the title suggests.
* It's deliberately translucent and not full screen so that other apps can be seen running in the background, but you can make the app run full window by modifying the layout XML, and making the background colour opaque.
* KeyEvent interception is shown on the *back button*; if you try to press the back button whilst the alert is shown, it'll be intecepted and a message shown in the logs.
* Touches are intercepted whilst the alert/app is shown, even though other apps might be visible in the background they can't be interacted with.
* Touch the View itself to close and kill the Service.
