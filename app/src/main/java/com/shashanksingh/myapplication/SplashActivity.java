package com.shashanksingh.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ImageView;

/**
 * Created by shashank singh on 6/30/2017.
 */

public class SplashActivity extends Activity {


@Override
protected void onCreate(Bundle savedInstanceState){
    super.onCreate(savedInstanceState);
    try {
        Thread.sleep(5000);


    } catch (InterruptedException e) {
        e.printStackTrace();
    }
    startActivity(new Intent(SplashActivity.this,MainActivitysq.class));

    finish();
}



}
