package com.example.yt.myapplication;
import android.app.Application;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class IApplication extends Application{
    private static Application mApp;
    List<City> cityList;
    CityDB mCityDB;
    @Override
    public void onCreate(){
        super.onCreate();
        Log.d("IApplication","OnCreate");
        mApp=this;
        mCityDB=openCityDB();
        initCityList();
    }

    private void initCityList() {
        new Thread(new Runnable() {
            @Override
            public void run() {
               prepareCityList();
            }

            private boolean prepareCityList() {
                cityList=mCityDB.getCityList();
                for (City city:cityList){
                    String cityName=city.getCity();
                    Log.d("CityDB",cityName);
                }
                return true;
            }
        }).start();
    }
    private List<City>getCityList(){
        return cityList;
 }
    private CityDB openCityDB() {
        String path="/data"
            +Environment.getDataDirectory().getAbsolutePath()+ File.separator+getPackageName()+File.separator+CityDB.CITY_DB_NAME;
        Log.d("fille path",path);
        File db=new File(path);
        Log.d("db",path);
        try {
            InputStream is=getAssets().open("city.db");
            FileOutputStream fos=new FileOutputStream(db);
            int len=-1;
            byte[] buffer=new byte[1024];
            while ((len=is.read(buffer))!=-1){
                fos.write(buffer,0,len);
                fos.flush();
            }
            fos.close();
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(0);
        }
        return new CityDB(this,path);
    }
    public static Application getInstance(){
        return mApp;
    }
}
