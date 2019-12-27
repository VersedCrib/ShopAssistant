package com.example.shopassistant;


public class Market {
    final static int FRUITS  = 3;
    int orange;
    int cucumber;
    int apple;

    public Market(int orange, int cucumber, int apple){
        this.orange = orange;
        this.cucumber = cucumber;
        this.apple = apple;
    }

    public void setFruits(int numFruits, int num){
        switch (numFruits){
            case 1:
                if(this.orange!=0){
                    this.orange-=num;
                }
                break;
            case 2:
                if(this.cucumber!=0) {
                    this.cucumber -= num;
                }
                break;
            case 3:
                if(this.apple!=0){
                    this.apple-=num;
                }
                break;
        }
    }

    public static int randFruits(){
        int x = (int)(Math.random()*(FRUITS))+1;
        return x;
    }

    public static int getPriceFruits(int num){
        switch (num){
            case 1:
                return 20;
            case 2:
                return 30;
            case 3:
                return 40;
        }
        return 0;
    }




}
