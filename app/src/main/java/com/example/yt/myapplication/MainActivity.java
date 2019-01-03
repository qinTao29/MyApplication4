package com.example.yt.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (CheckNet.getNetState(this)==CheckNet.NET_NONE){
            Log.d("天气预报","网络不通");
            Toast.makeText(MainActivity.this,"网络不通",Toast.LENGTH_LONG).show();
        }else{
            Log.d("天气预报","网络畅通");
            Toast.makeText(MainActivity.this,"网络畅通",Toast.LENGTH_LONG).show();
        }


    }
}
