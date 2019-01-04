package com.example.yt.myapplication;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class Select_city extends Activity implements View.OnClickListener {
private ImageView ivback;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_city);
        ivback=(ImageView)findViewById(R.id.title_selectCity_back);
        ivback.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.title_selectCity_back:
                finish();;
                break;
                default:
                    break;
        }
    }
}
