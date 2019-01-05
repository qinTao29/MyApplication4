package com.example.yt.myapplication;

import android.app.Activity;
import android.os.Bundle;

import android.util.Log;
import android.widget.Button;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;



public class Locate extends Activity {
    private LocationClient mLocationClient;
    private MyLocationListener myLocationListener;

    Button locateBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_locate);
        locateBtn=findViewById(R.id.bdmap_cityname);

        mLocationClient = new LocationClient(this);
        myLocationListener = new MyLocationListener(locateBtn);
        mLocationClient.registerLocationListener(myLocationListener);
        initLocation();
        mLocationClient.start();
    }

     void initLocation() {
        LocationClientOption option = new LocationClientOption();
        option.setIsNeedAddress(true);
        option.setOpenGps(true);
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);
        option.setCoorType("BD09ll");
        option.setScanSpan(1000);
        mLocationClient.setLocOption(option);
    }

    class MyLocationListener implements BDLocationListener{
        Button locBtn;
        MyLocationListener(Button locBtn){
            this.locBtn = locBtn;
        }
        String cityName;
        @Override
        public void onReceiveLocation(BDLocation bdLocation) {
            cityName = bdLocation.getCity();
            Log.d("Locate",cityName);
            locBtn.setText(cityName);
        }
    }
}
