package com.example.yt.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends Activity implements View.OnClickListener{
    private   TextView tvname, tvtemperature, tvweatherState, tvupdatetime, tvwind;
    private  ImageView ivmanager, ivlocate, ivupdate;
    private TextView week1T,week2T,week3T,temperature1T,temperature2T,temperature3T,
            wind1T,wind2T,wind3T,climate1T,climate2T,climate3T;
    private Handler mHandler =new Handler(){
        public void handleMessage(android.os.Message message){
            switch (message.what){
                case 1:
                    updateTodayWeather((TodayWeather)message.obj);
                    break;
                default:
                    break;
            }
        }
    };
    TodayWeather todayWeather = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_locate);
        setContentView(R.layout.activity_main);
        ivupdate=(ImageView)findViewById(R.id.title_main_update);
        ivupdate.setOnClickListener(this);
        ivmanager=(ImageView)findViewById(R.id.title_main_manager);
        ivmanager.setOnClickListener(this);
        ivlocate=findViewById(R.id.title_main_locate);
        ivlocate.setOnClickListener(this);
        initView();
        if (CheckNet.getNetState(this) == CheckNet.NET_NONE) {
            Log.d("天气预报", "网络不通");
            Toast.makeText(MainActivity.this, "网络不通", Toast.LENGTH_LONG).show();
        } else {
            Log.d("天气预报", "网络畅通");
            Toast.makeText(MainActivity.this, "网络畅通", Toast.LENGTH_LONG).show();
            getWeatherDatafromNet("101010100");
        }
    }

    void initView() {
        tvname = (TextView) findViewById(R.id.title_main_name);
        tvtemperature = (TextView) findViewById(R.id.tv_main_temperature);
        tvupdatetime = (TextView) findViewById(R.id.tv_main_updatetime);
        tvweatherState = (TextView) findViewById(R.id.tv_main_weatherState);
        tvwind = (TextView) findViewById(R.id.tv_main_wind);

        ivmanager = (ImageView) findViewById(R.id.title_main_manager);
        ivlocate = (ImageView) findViewById(R.id.title_main_locate);

        week1T=findViewById(R.id.tv_no1_week);
        temperature1T=findViewById(R.id.tv_no1_temperature);
        climate1T=findViewById(R.id.tv_no1_weatherState);
        wind1T=findViewById(R.id.tv_no1_wind);

        week2T=findViewById(R.id.tv_no2_week);
        temperature2T=findViewById(R.id.tv_no2_temperature);
        climate2T=findViewById(R.id.tv_no2_weatherState);
        wind2T=findViewById(R.id.tv_no2_wind);

        week3T=findViewById(R.id.tv_no3_week);
        temperature3T=findViewById(R.id.tv_no3_temperature);
        climate3T=findViewById(R.id.tv_no3_weatherState);
        wind3T=findViewById(R.id.tv_no3_wind);

        tvname.setText("N/A");
        tvtemperature.setText("N/A");
        tvupdatetime.setText("N/A");
        tvweatherState.setText("N/A");
        tvwind.setText("N/A");

        temperature1T.setText("N/A");
        climate1T.setText("N/A");
        wind1T.setText("N/A");

        temperature2T.setText("N/A");
        climate2T.setText("N/A");
        wind2T.setText("N/A");

        temperature3T.setText("N/A");
        climate3T.setText("N/A");
        wind3T.setText("N/A");

    }

    private void getWeatherDatafromNet(String cityCode) {
        final String address = "http://wthrcdn.etouch.cn/WeatherApi?citykey=" + cityCode;
        Log.d("Address:", address);
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection urlConnection = null;
                try {
                    URL url = new URL(address);
                    urlConnection = (HttpURLConnection) url.openConnection();
                    urlConnection.setRequestMethod("GET");
                    urlConnection.setConnectTimeout(8000);
                    urlConnection.setReadTimeout(8000);
                    InputStream in = urlConnection.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                    StringBuffer sb = new StringBuffer();
                    String str;
                    while ((str = reader.readLine()) != null) {
                        sb.append(str);
                        Log.d("date from url", str);
                    }
                    String response = sb.toString();
                    Log.d("response", response);

                    todayWeather=parseXML(response);
                    if(todayWeather!=null){
                        Message message=new Message();
                        message.what=1;
                        message.obj=todayWeather;
                        mHandler.sendMessage(message);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private TodayWeather parseXML(String xmlData) {

        int dateCount = 0;
        int fengliCount = 0;
        int highCount = 0;
        int lowCount = 0;
        int typeCount = 0;

        try {
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            XmlPullParser xmlPullParser = factory.newPullParser();
            xmlPullParser.setInput(new StringReader(xmlData));

            int eventType = xmlPullParser.getEventType();
            Log.d("My Application","start parse xml");

            while(eventType!=xmlPullParser.END_DOCUMENT)
            {
                switch (eventType)
                {
                    case XmlPullParser.START_DOCUMENT:
                        Log.d("parse","start doc");
                        break;
                    case XmlPullParser.START_TAG:
                        if(xmlPullParser.getName().equals("resp"))
                        {
                            todayWeather = new TodayWeather();
                        }
                        if(todayWeather!=null) {
                            if (xmlPullParser.getName().equals("city")) {
                                eventType = xmlPullParser.next();
                                Log.d("city", xmlPullParser.getText());
                                todayWeather.setCity(xmlPullParser.getText());
                            } else if (xmlPullParser.getName().equals("updatetime")) {
                                eventType = xmlPullParser.next();
                                Log.d("updatetime", xmlPullParser.getText());
                                todayWeather.setUpdatetime(xmlPullParser.getText());
                            } else if (xmlPullParser.getName().equals("wendu")) {
                                eventType = xmlPullParser.next();
                                Log.d("wendu", xmlPullParser.getText());
                                todayWeather.setWendu(xmlPullParser.getText());
                            } else if (xmlPullParser.getName().equals("fengli") && fengliCount == 0) {
                                eventType = xmlPullParser.next();
                                Log.d("fengli", xmlPullParser.getText());
                                todayWeather.setFengli(xmlPullParser.getText());
                                fengliCount++;
                            } else if (xmlPullParser.getName().equals("high") && highCount == 0) {
                                eventType = xmlPullParser.next();
                                Log.d("high", xmlPullParser.getText());
                                todayWeather.setHigh(xmlPullParser.getText());
                                highCount++;
                            } else if (xmlPullParser.getName().equals("low") && lowCount == 0) {
                                eventType = xmlPullParser.next();
                                Log.d("low", xmlPullParser.getText());
                                todayWeather.setLow(xmlPullParser.getText());
                                lowCount++;
                            } else if (xmlPullParser.getName().equals("type") && typeCount == 0) {
                                eventType = xmlPullParser.next();
                                Log.d("type", xmlPullParser.getText());
                                todayWeather.setType(xmlPullParser.getText());
                                typeCount++;
                            }
                            //今天
                            else if( xmlPullParser.getName().equals("date") && dateCount == 1){
                                eventType = xmlPullParser.next();
                                Log.d("future1 date",xmlPullParser.getText());
                                todayWeather.setDate1(xmlPullParser.getText());
                                dateCount++;
                            } else if(xmlPullParser.getName().equals("low") && lowCount == 1 ){
                                eventType = xmlPullParser.next();
                                todayWeather.setLow1(xmlPullParser.getText());
                                Log.d("future1 low",xmlPullParser.getText());
                                lowCount++;
                            } else if(xmlPullParser.getName().equals("high") && highCount == 1){
                                eventType = xmlPullParser.next();
                                todayWeather.setHigh1(xmlPullParser.getText());
                                Log.d("future1 high",xmlPullParser.getText());
                                highCount++;
                            } else if(xmlPullParser.getName().equals("type") && typeCount == 1){
                                eventType = xmlPullParser.next();
                                todayWeather.setType1(xmlPullParser.getText());
                                Log.d("future1 type",xmlPullParser.getText());
                                typeCount++;
                            } else if(xmlPullParser.getName().equals("fengli") && fengliCount == 1){
                                eventType = xmlPullParser.next();
                                todayWeather.setFengli1(xmlPullParser.getText());
                                Log.d("future1 fengli",xmlPullParser.getText());
                                fengliCount++;

                            } else if( xmlPullParser.getName().equals("date") && dateCount == 2){
                                eventType = xmlPullParser.next();
                                Log.d("future2 date",xmlPullParser.getText());
                                todayWeather.setDate2(xmlPullParser.getText());
                                dateCount++;
                            } else if(xmlPullParser.getName().equals("low") && lowCount == 2 ){
                                eventType = xmlPullParser.next();
                                todayWeather.setLow2(xmlPullParser.getText());
                                Log.d("future2 low",xmlPullParser.getText());
                                lowCount++;
                            } else if(xmlPullParser.getName().equals("high") && highCount == 2){
                                eventType = xmlPullParser.next();
                                todayWeather.setHigh2(xmlPullParser.getText());
                                Log.d("future2 high",xmlPullParser.getText());
                                highCount++;
                            } else if(xmlPullParser.getName().equals("type") && typeCount == 2){
                                eventType = xmlPullParser.next();
                                todayWeather.setType2(xmlPullParser.getText());
                                Log.d("future2 type",xmlPullParser.getText());
                                typeCount++;
                            } else if(xmlPullParser.getName().equals("fengli") && fengliCount == 2){
                                eventType = xmlPullParser.next();
                                todayWeather.setFengli2(xmlPullParser.getText());
                                Log.d("future2 fengli",xmlPullParser.getText());
                                fengliCount++;

                            } else if( xmlPullParser.getName().equals("date") && dateCount == 3){
                                eventType = xmlPullParser.next();
                                Log.d("future3 date",xmlPullParser.getText());
                                todayWeather.setDate3(xmlPullParser.getText());
                                dateCount++;
                            } else if(xmlPullParser.getName().equals("low") && lowCount == 3 ){
                                eventType = xmlPullParser.next();
                                todayWeather.setLow3(xmlPullParser.getText());
                                Log.d("future3 low",xmlPullParser.getText());
                                lowCount++;
                            } else if(xmlPullParser.getName().equals("high") && highCount == 3){
                                eventType = xmlPullParser.next();
                                todayWeather.setHigh3(xmlPullParser.getText());
                                Log.d("future3 high",xmlPullParser.getText());
                                highCount++;
                            } else if(xmlPullParser.getName().equals("type") && typeCount == 3){
                                eventType = xmlPullParser.next();
                                todayWeather.setType3(xmlPullParser.getText());
                                Log.d("future3 type",xmlPullParser.getText());
                                typeCount++;
                            } else if(xmlPullParser.getName().equals("fengli") && fengliCount == 3) {
                                eventType = xmlPullParser.next();
                                todayWeather.setFengli3(xmlPullParser.getText());
                                Log.d("future3 fengli", xmlPullParser.getText());
                                fengliCount++;
                            }
                            }
                        break;
                    case XmlPullParser.END_TAG:
                        break;
                }
                eventType=xmlPullParser.next();
            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return todayWeather;
    }
    void updateTodayWeather(TodayWeather todayWeather){
        tvname.setText(todayWeather.getCity());
        tvupdatetime.setText("发布时间"+todayWeather.getUpdatetime());
        tvwind.setText("风力"+todayWeather.getFengli());
        tvtemperature.setText(todayWeather.getHigh()+"~"+todayWeather.getLow());
        tvweatherState.setText(todayWeather.getType());


        temperature1T.setText(todayWeather.getHigh1()+"~"+todayWeather.getLow1());
        temperature2T.setText(todayWeather.getHigh2()+"~"+todayWeather.getLow2());
        temperature3T.setText(todayWeather.getHigh3()+"~"+todayWeather.getLow3());
        climate1T.setText(todayWeather.getType1());
        climate2T.setText(todayWeather.getType2());
        climate3T.setText(todayWeather.getType3());
        wind1T.setText("风力"+todayWeather.getFengli1());
        wind2T.setText("风力"+todayWeather.getFengli2());
        wind3T.setText("风力"+todayWeather.getFengli3());

        Toast.makeText(MainActivity.this,"更新成功",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.title_main_update){
            getWeatherDatafromNet("101010100");
        }  if(v.getId()==R.id.title_main_manager){
            Intent intent=new Intent(this,SelectCity.class);
            startActivity(intent);
        }
        if (v.getId() == R.id.title_main_locate){
            Locate mLocation = new Locate();
            mLocation.startLocation();
            Log.d("click","title_main_locate");
            Intent intent=new Intent(this,Locate.class);
            startActivity(intent);
        }
    }
}