package com.dimelthoz.mediaplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final long UPDATE_IMAGE_MS = 2000;
    private TextView imageView;
    private Handler mHandler;

    private static final int[] mediasId = {
            R.drawable.image_1, R.drawable.image_2, R.drawable.image_3,
            R.drawable.image_4, R.drawable.image_5, R.drawable.image_6
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeResources();


        mHandler = new Handler();
        mHandler.post(updateScreen);
    }

    private void initializeResources() {
        imageView = findViewById(R.id.img_view);
    }

    private final Runnable updateScreen = new Runnable() {
        public void run() {
            if (hasWindowFocus()) {
                updateImage();
                hideNavigationBar(getWindow());
            }
            mHandler.postDelayed(updateScreen, UPDATE_IMAGE_MS);
        }
    };

    private int currentMedia = 0;

    private void updateImage() {
        imageView.setBackgroundResource(mediasId[currentMedia]);
        currentMedia = currentMedia + 1 >= mediasId.length ? 0 : currentMedia + 1;
    }

    public static void hideNavigationBar(Window window){
        View view = window.getDecorView();
        view.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_HIDE_NAVIGATION |
                        View.SYSTEM_UI_FLAG_FULLSCREEN |
                        View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION |
                        View.SYSTEM_UI_FLAG_IMMERSIVE
        );
    }
}