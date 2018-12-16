package com.example.ashish.gamify;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import nl.dionsegijn.konfetti.KonfettiView;
import nl.dionsegijn.konfetti.models.Shape;
import nl.dionsegijn.konfetti.models.Size;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button buttonView;
    private Button button1;
    private Button button2;
    private Button button3;
    private Button buttonInfo;

    SingletonData singletonData;
    View layout;
    KonfettiView konfettiView;
    int width,height;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonView = findViewById(R.id.buttonView);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        buttonInfo = findViewById(R.id.buttonInfo);

        buttonView.setOnClickListener(this);
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        buttonInfo.setOnClickListener(this);

        singletonData = SingletonData.getInstance();

        LayoutInflater inflater = getLayoutInflater();
        layout = inflater.inflate(R.layout.check_toast,
                (ViewGroup) findViewById(R.id.toast_layout_root));

        konfettiView = findViewById(R.id.konfettiView);

        WindowManager wm = (WindowManager) this.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        DisplayMetrics metrics = new DisplayMetrics();
        display.getMetrics(metrics);
        width = metrics.widthPixels;
        height = metrics.heightPixels;

    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        Intent intent = new Intent(this,Main2Activity.class);

        switch (id){
            case R.id.buttonView:
                startActivity(intent);
                break;

            case R.id.button1:
                singletonData.clicked1();
                checkToShowToast();
                break;

            case R.id.button2:
                singletonData.clicked2();
                checkToShowToast();
                break;

            case R.id.button3:
                singletonData.clicked3();
                checkToShowToast();
                break;

            case R.id.buttonInfo :
                Toast.makeText(this, "btn1X10 = ₹5000, btn2X15 = ₹2000, btn3X20 = ₹3000, ", Toast.LENGTH_LONG).show();
        }

    }

    void checkToShowToast(){
        if(singletonData.getPrice()!=0 &&
                singletonData.getClick1()!= 0 && singletonData.getClick1()%10 == 0 ||
                singletonData.getClick2()!= 0 && singletonData.getClick2()%15==0 ||
                singletonData.getClick3()!= 0 && singletonData.getClick3()%20==0){
            Toast toast = new Toast(this);
            toast.setGravity(Gravity.CENTER_VERTICAL, 0, 400);
            toast.setDuration(Toast.LENGTH_LONG);
            toast.setView(layout);
            toast.show();

            konfettiView.build()
                    .addColors(Color.argb(1, 255, 0, 0), Color.argb(1, 0, 0, 204), Color.argb(1, 102, 255, 51))
                    .setDirection(0.0, 359.0)
                    .setSpeed(1f, 5f)
                    .setFadeOutEnabled(true)
                    .setTimeToLive(2000L)
                    .addShapes(Shape.RECT, Shape.CIRCLE)
                    .addSizes(new Size(12, 5f))
                    .setPosition(width/2,height/4)
                    .burst(100);
        }
    }

}
