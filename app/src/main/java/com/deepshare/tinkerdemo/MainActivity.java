package com.deepshare.tinkerdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.tinkerpatch.sdk.TinkerPatch;

public class MainActivity extends AppCompatActivity {
    private TextView tv_hello;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TinkerPatch.with().fetchPatchUpdate(true);

        tv_hello = (TextView) findViewById(R.id.tv_hello);
        setContent();
    }

    private void setContent() {
        tv_hello.setText("5.25发了个补丁，修复了bug");
    }
}
