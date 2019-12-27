package com.example.shopassistant;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Market market = new Market(5,5,5);
    boolean start = true;
    BoxOffice boxOffice = new BoxOffice();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button startMarket = (Button) findViewById(R.id.b_startMaket);

        startMarket.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View view){
                if(start) {
                    start = false;
                    Validator[] validator_ar = new Validator[6];
                    for (int i = 0; i < validator_ar.length; i++) {
                        validator_ar[i] = new Validator(100, i + 1);
                        validator_ar[i].execute();
                        try {
                            Thread.sleep(1);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                    //validator_ar[0].execute();
                    /*validator_ar[1].execute();
                    //validator_ar[2].execute();
                    validator_ar[3].execute();
                    validator_ar[4].execute();
                    validator_ar[5].execute();*/

                }
            }
        });


        Button seeMarket = (Button) findViewById(R.id.b_seeStrore);

        seeMarket.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View view){
                Intent i = new Intent(MainActivity.this, MarketActivity.class);
                i.putExtra(Market.class.getSimpleName(), market);
                startActivity(i);
            }
        });


    }


    private class Validator extends AsyncTask<Void, Void, Void> {
        boolean start = true;
        int numValidator = 0;
        int money = 0;
        int orange = 0;
        int cucumber = 0;
        int apple = 0;
        TextView tw_orange;
        TextView tw_cucumber;
        TextView tw_apple;
        TextView tw_money;
        TextView tw_condition;
        LinearLayout validator;

        public Validator(int money, int numValidator){
            this.money = money;
            this.numValidator = numValidator;
            this.start = true;

            switch (numValidator){
                case 1:
                    tw_orange = (TextView) findViewById(R.id.tw_orange1);
                    tw_cucumber = (TextView) findViewById(R.id.tw_cucumber1);
                    tw_apple = (TextView) findViewById(R.id.tw_apple1);
                    tw_money = (TextView) findViewById(R.id.tw_money1);
                    tw_condition = (TextView) findViewById(R.id.tw_condition1);
                    validator = (LinearLayout) findViewById(R.id.validator1);
                    break;

                case 2:
                    tw_orange = (TextView) findViewById(R.id.tw_orange2);
                    tw_cucumber = (TextView) findViewById(R.id.tw_cucumber2);
                    tw_apple = (TextView) findViewById(R.id.tw_apple2);
                    tw_money = (TextView) findViewById(R.id.tw_money2);
                    tw_condition = (TextView) findViewById(R.id.tw_condition2);
                    validator = (LinearLayout) findViewById(R.id.validator2);
                    break;

                case 3:
                    tw_orange = (TextView) findViewById(R.id.tw_orange3);
                    tw_cucumber = (TextView) findViewById(R.id.tw_cucumber3);
                    tw_apple = (TextView) findViewById(R.id.tw_apple3);
                    tw_money = (TextView) findViewById(R.id.tw_money3);
                    tw_condition = (TextView) findViewById(R.id.tw_condition3);
                    validator = (LinearLayout) findViewById(R.id.validator3);
                    break;

                case 4:
                    tw_orange = (TextView) findViewById(R.id.tw_orange4);
                    tw_cucumber = (TextView) findViewById(R.id.tw_cucumber4);
                    tw_apple = (TextView) findViewById(R.id.tw_apple4);
                    tw_money = (TextView) findViewById(R.id.tw_money4);
                    tw_condition = (TextView) findViewById(R.id.tw_condition4);
                    validator = (LinearLayout) findViewById(R.id.validator4);
                    break;

                case 5:
                    tw_orange = (TextView) findViewById(R.id.tw_orange5);
                    tw_cucumber = (TextView) findViewById(R.id.tw_cucumber5);
                    tw_apple = (TextView) findViewById(R.id.tw_apple5);
                    tw_money = (TextView) findViewById(R.id.tw_money5);
                    tw_condition = (TextView) findViewById(R.id.tw_condition5);
                    validator = (LinearLayout) findViewById(R.id.validator5);
                    break;

                case 6:
                    tw_orange = (TextView) findViewById(R.id.tw_orange6);
                    tw_cucumber = (TextView) findViewById(R.id.tw_cucumber6);
                    tw_apple = (TextView) findViewById(R.id.tw_apple6);
                    tw_money = (TextView) findViewById(R.id.tw_money6);
                    tw_condition = (TextView) findViewById(R.id.tw_condition6);
                    validator = (LinearLayout) findViewById(R.id.validator6);
                    break;
            }

        }

        @Override
        protected void onPreExecute() {
            //super.onPreExecute();
            validator.setBackgroundColor(Color.YELLOW);
            tw_condition.setText("Покупка началась");


        }

        @SuppressLint("WrongThread")
        protected Void doInBackground(Void... args) {
            if(this.start){
            boolean b = true;
                while (money > 0 && b){
                   synchronized(market){
                        int numFruits = Market.randFruits();
                        int priceFruits = Market.getPriceFruits(numFruits);
                        if(money-priceFruits>=0 && market.isFruits(numFruits,1)){
                            money-=priceFruits;
                            market.setFruits(numFruits,1);
                            setFruits(numFruits,1);
                        }
                        if(money < 20 || (!market.isFruits(1,1) && !market.isFruits(2,1)&& !market.isFruits(3,1))){
                            b = false;
                        }
                        /*try {
                           Thread.sleep(500);
                        } catch (InterruptedException e) {
                           e.printStackTrace();
                        }
                        onProgressUpdate();*/

                    }
                }
                this.start = false;
            }
            return null;
        }

        protected void onPostExecute(Void args) {
            synchronized(boxOffice){
                if(boxOffice.payMoney()){
                    tw_money.setText("money:" + money);
                    validator.setBackgroundColor(Color.GREEN);
                    tw_condition.setText("Покупка закончилась");
                    tw_orange.setText("orange:" + orange);
                    tw_cucumber.setText("cucumber:" + cucumber);
                    tw_apple.setText("apple:" + apple);
                    //this.start = true;
                }
            }

        }

        @Override
        protected void onProgressUpdate(Void... args) {
            //super.onProgressUpdate();
            /*try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }*/



        }


        public void setFruits(int numFruits, int num){
            switch (numFruits){
                case 1:
                    this.orange+=num;
                    break;
                case 2:
                    this.cucumber+=num;
                    break;
                case 3:
                    this.apple+=num;
                    break;
            }
        }

    }



}
