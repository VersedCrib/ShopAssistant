package com.example.shopassistant;


import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MarketActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_market);
        TextView tw_orange = (TextView) findViewById(R.id.tw_orange);
        TextView tw_cucumber = (TextView) findViewById(R.id.tw_cucumber);
        TextView tw_apple =  (TextView) findViewById(R.id.tw_apple);

        Bundle arguments = getIntent().getExtras();
        Market market = (Market) arguments.getSerializable(Market.class.getSimpleName());

        tw_orange.setText("orange:" + market.orange);
        tw_cucumber.setText("cucumber:" + market.cucumber);
        tw_apple.setText("apple:" + market.apple);

    }

}
