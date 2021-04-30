package com.example.homework5;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

public class MyCanvas extends View {
    HashMap<Integer, Path> activePaths;
    private ArrayList<Path> pathList;
    private ArrayList<Integer> colorList;
    private ArrayList<CoordedBitmap> bmList;
    private int currentColor;
    Paint pathPaint, redPaint, bluePaint, greenPaint;
    Bitmap bm;
    public MyCanvas(Context context, AttributeSet attrs) {
        super(context, attrs);
        pathList = new ArrayList<>();
        colorList = new ArrayList<>();
        bmList = new ArrayList<>();
        activePaths = new HashMap<>();


        currentColor = Color.BLACK;
        pathPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        pathPaint.setColor(Color.BLACK);
        pathPaint.setStyle(Paint.Style.STROKE);
        pathPaint.setStrokeWidth(70);

        redPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        redPaint.setColor(Color.RED);
        redPaint.setStyle(Paint.Style.STROKE);
        redPaint.setStrokeWidth(70);

        bluePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        bluePaint.setColor(Color.BLUE);
        bluePaint.setStyle(Paint.Style.STROKE);
        bluePaint.setStrokeWidth(70);

        greenPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        greenPaint.setColor(Color.GREEN);
        greenPaint.setStyle(Paint.Style.STROKE);
        greenPaint.setStrokeWidth(70);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawBitmap(bm, 0,0,null);
        int size = pathList.size();
        for(int i =0; i < pathList.size(); i++){
            Paint currentPaint;
            switch(colorList.get(i)){
                case(Color.RED):
                    currentPaint = redPaint;
                    break;
                case(Color.BLUE):
                    currentPaint = bluePaint;
                    break;
                case(Color.GREEN):
                    currentPaint = greenPaint;
                    break;
                default:
                    currentPaint = pathPaint;
                    break;
            }
            canvas.drawPath(pathList.get(i), currentPaint);
            for(CoordedBitmap cbm : bmList){
                Bitmap bm = cbm.getBitmap();
                float x = cbm.getX() - (bm.getWidth()/2);
                float y = cbm.getY() - (bm.getHeight()/2);
                canvas.drawBitmap(bm, x, y, null);
            }
        }
        super.onDraw(canvas);
    }

    public void addPath(int id, float x, float y) {
        Path path = new Path();
        path.moveTo(x, y);
        activePaths.put(id, path);
        pathList.add(path);
        colorList.add(currentColor);
        invalidate();
    }

    public void updatePath(int id, float x, float y) {
        Path path = activePaths.get(id);
        if(path != null){
            path.lineTo(x, y);
        }
        invalidate();
    }

    public void removePath(int id) {
        if(activePaths.containsKey(id)){
            activePaths.remove(id);
        }
        invalidate();
    }
    public void setBM(Bitmap bm){
        Matrix matrix = new Matrix();
        matrix.postRotate(90);
        Bitmap scaledBitmap = Bitmap.createScaledBitmap(bm, bm.getWidth() * 7, bm.getHeight() * 7, true);
        Bitmap rotatedBitmap = Bitmap.createBitmap(scaledBitmap, 0, 0, scaledBitmap.getWidth(), scaledBitmap.getHeight(), matrix, true);
        this.bm = rotatedBitmap;
    }
    public void changeColor(int color){
        currentColor = color;
    }
    public void undo(){
        int size = pathList.size();
        if(size > 0) {
            pathList.remove(pathList.size() - 1);
            colorList.remove(colorList.size() - 1);
        }
        invalidate();
    }

    public void addIcon(Bitmap bm,float x, float y){
        bmList.add(new CoordedBitmap(bm, x, y));
        invalidate();
    }
    public void clear(){
        pathList.clear();
        colorList.clear();
        bmList.clear();
        invalidate();
    }

}
