package com.mattfenlon.ghost;


import android.content.Intent;
import android.net.Uri;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    if (Settings.canDrawOverlays(this)) {

      // Launch service right away - the user has already previously granted permission
      launchMainService();
    }
    else {

      // Check that the user has granted permission, and prompt them if not
      checkDrawOverlayPermission();
    }
  }

  private void launchMainService() {

    Intent svc = new Intent(this, MainService.class);

    startService(svc);

    finish();
  }

  public final static int REQUEST_CODE = 10101;

  public void checkDrawOverlayPermission() {

    // Checks if app already has permission to draw overlays
    if (!Settings.canDrawOverlays(this)) {

      // If not, form up an Intent to launch the permission request
      Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION, Uri.parse("package:" + getPackageName()));

      // Launch Intent, with the supplied request code
      startActivityForResult(intent, REQUEST_CODE);
    }
  }

  @Override
  protected void onActivityResult(int requestCode, int resultCode,  Intent data) {

    // Check if a request code is received that matches that which we provided for the overlay draw request
    if (requestCode == REQUEST_CODE) {

      // Double-check that the user granted it, and didn't just dismiss the request
      if (Settings.canDrawOverlays(this)) {

        // Launch the service
        launchMainService();
      }
      else {

        Toast.makeText(this, "Sorry. Can't draw overlays without permission...", Toast.LENGTH_SHORT).show();
      }
    }
  }
}
