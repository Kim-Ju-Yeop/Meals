package org.techtown.project5.Handler;

import android.app.Activity;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;

public class BackPressCloseHandler {

    private long backKeyPressedTime = 0;
    private Toast toast;
    private Activity activity;

    public BackPressCloseHandler(Activity activity) {
        this.activity = activity;
    }

    public void onBackPressed() {
        if (System.currentTimeMillis() > backKeyPressedTime + 2000) {
            backKeyPressedTime = System.currentTimeMillis(); showGuide();
            return;
        } if (System.currentTimeMillis() <= backKeyPressedTime + 2000) {

            ActivityCompat.finishAffinity(activity);
        }
    }

    public void showGuide() {

        Toast.makeText(activity, "뒤로 버튼을 한번 더 누르시면\n          앱이 종료됩니다.", Toast.LENGTH_SHORT).show();
    }
}
