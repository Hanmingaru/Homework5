package com.example.homework5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class DrawActivity extends AppCompatActivity {
    private ImageView photoView;
    private Button redBut, greenBut, blueBut;
    private Button undoBut, clearBut, doneBut;
    TouchListener touchListener;
    MyCanvas myCanvas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_draw);
        redBut = findViewById(R.id.redBut);
        greenBut = findViewById(R.id.greenBut);
        blueBut = findViewById(R.id.blueBut);
        undoBut = findViewById(R.id.undoBut);
        clearBut = findViewById(R.id.clearBut);
        doneBut= findViewById(R.id.doneBut);

        myCanvas = (MyCanvas) findViewById(R.id.myCanvas);
        touchListener = new TouchListener(this);
        myCanvas.setOnTouchListener(touchListener);

        byte[] byteArray = getIntent().getByteArrayExtra("Bitmap");
        Bitmap bmp = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
        myCanvas.setBM(bmp);

        redBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myCanvas.changeColor(Color.RED);
            }
        });
        greenBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myCanvas.changeColor(Color.GREEN);
            }
        });
        blueBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myCanvas.changeColor(Color.BLUE);
            }
        });

        undoBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myCanvas.undo();
            }
        });
        clearBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myCanvas.clear();
            }
        });
        doneBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    public void addPath(int id, float x, float y) {
        myCanvas.addPath(id, x, y);
    }

    public void updatePath(int id, float x, float y) {
        myCanvas.updatePath(id, x, y);
    }

    public void removePath(int id) {
        myCanvas.removePath(id);
    }


    public void onDoubleTap(float x, float y) {
        Bitmap icon = BitmapFactory.decodeResource(getResources(),
                R.drawable.hokie_bird);
        myCanvas.addIcon(icon, x,y);
    }

    public void onLongPress(float x, float y) {
        Bitmap icon = BitmapFactory.decodeResource(getResources(),
                R.drawable.star);
        myCanvas.addIcon(icon, x,y);
    }
}