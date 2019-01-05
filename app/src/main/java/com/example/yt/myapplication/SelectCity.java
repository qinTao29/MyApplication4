package com.example.yt.myapplication;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class SelectCity extends Activity implements View.OnClickListener{

    private ImageView ivback;
private ListView cityListLv;

private List<City> mCityList;
private MyApplication mApplication;
private ArrayList<String> mArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_city);
        ivback=findViewById(R.id.iv_select_back);
        ivback.setOnClickListener(this);

        mApplication = (MyApplication)getApplication();
        mCityList=mApplication.getCityList();
        mArrayList=new ArrayList<String>();
        for (int i=0;i<mCityList.size();i++){
            String cityName=mCityList.get(i).getCity();
            mArrayList.add(cityName);
        }
        cityListLv=findViewById(R.id.selectcity_lv);
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(SelectCity.this,android.R.layout.simple_list_item_1,mArrayList);
        cityListLv.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_select_back:
                finish();
                break;
                default:
                        break;
        }
    }
}
