package com.example.ashish.gamify;

public class SingletonData {

    private static SingletonData instance = null;
    private int click1=0;
    private int click2=0;
    private int click3=0;
    private int price=20000;

    public static SingletonData getInstance(){
        if(instance == null){
            instance = new SingletonData();
            return instance;
        }
        return instance;
    }

    public void clicked1(){
        click1++;
    }
    public void clicked2(){
        click2++;
    }
    public void clicked3(){
        click3++;
    }

    public int getClick1() {
        return click1;
    }
    public int getClick2() {
        return click2;
    }
    public int getClick3() {
        return click3;
    }

    public int[] getDataReset(){
        int []  data= {click1, click2, click3};
        click1 = 0;
        click2 = 0;
        click3 = 0;
        return data;
    }

    public int getCalculated(){
        double discount = Math.floor(click1/10)*5000 + Math.floor(click2/15)*2000 + Math.floor(click3/20)*3000;
        click1 = 0;
        click2 = 0;
        click3 = 0;
        return ((int) discount);
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
