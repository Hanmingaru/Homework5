package com.example.homework5;

import android.graphics.Bitmap;

public class CoordedBitmap {
    private Bitmap bitmap;
    private float x, y;
    public CoordedBitmap(Bitmap bm, float x, float y){
        this.bitmap = bm;
        this.x = x;
        this.y = y;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }
}
