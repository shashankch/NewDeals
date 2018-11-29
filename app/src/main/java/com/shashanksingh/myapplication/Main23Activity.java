package com.shashanksingh.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Main23Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main23);


        TextView textView=(TextView)findViewById(R.id.text);
        textView.setText("This app is still in beta mode.Payment and buying feature will be soon added.Thanks for showing ur interest !");
    }
}
