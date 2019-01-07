package com.example.yt.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

public class Banben extends AppCompatActivity {
    private TextView tvbanbenxx;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banben);
        tvbanbenxx=(TextView)findViewById(R.id.tv_banben_xx);
        Intent intent=new Intent(this,MainActivity.class);
        startActivity(intent);

    }
}
