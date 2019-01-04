package com.example.yt.myapplication;
import android.app.Application;
import android.util.Log;
public class IApplication extends Application{
    @Override
    public void onCreate(){
        super.onCreate();
        Log.d("IApplication","OnCreate");
    }
}
