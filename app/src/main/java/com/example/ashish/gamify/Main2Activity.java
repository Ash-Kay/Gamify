package com.example.ashish.gamify;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;
import android.widget.TextView;

import nl.dionsegijn.konfetti.KonfettiView;
import nl.dionsegijn.konfetti.models.Shape;
import nl.dionsegijn.konfetti.models.Size;

public class Main2Activity extends AppCompatActivity {

    TextView textViewPrice;
    int width;
    KonfettiView konfettiView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        textViewPrice = findViewById(R.id.textViewPrice);
        konfettiView = findViewById(R.id.konfettiView);

        SingletonData singletonData = SingletonData.getInstance();
        int price = singletonData.getPrice();
        int subtract = singletonData.getCalculated();

        WindowManager wm = (WindowManager) this.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        DisplayMetrics metrics = new DisplayMetrics();
        display.getMetrics(metrics);
        width = metrics.widthPixels;

        textViewPrice.setText("₹ " + price);

        if(price>0) {

            if (subtract <= price ) {
                price -= subtract;

                if(subtract!=0) {
                    textViewPrice.setText("₹ " + price);
                    singletonData.setPrice(price);

                    makeItRain();
                }


            } else {
                textViewPrice.setText("₹ 0");
                singletonData.setPrice(0);

                makeItRain();
            }
        }


    }

    void makeItRain(){

        konfettiView.build()
                .addColors(Color.argb(1, 0, 153, 51), Color.argb(1, 204, 0, 204), Color.argb(1, 0, 0, 255))
                .setDirection(0.0, 359.0)
                .setSpeed(1f, 5f)
                .setFadeOutEnabled(true)
                .setTimeToLive(2000L)
                .addShapes(Shape.RECT, Shape.CIRCLE)
                .addSizes(new Size(12, 5f))
                .setPosition(0, width + 0f, -50, -50 + 0f)
                .streamFor(300, 1000L);
    }

}
