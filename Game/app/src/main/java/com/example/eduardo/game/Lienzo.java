package com.example.eduardo.game;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
/**
 * Created by eduardo on 08/06/2017.
 */

public class Lienzo extends View {
static float tama;
    //pintar linea
    private Path drawPath;
    //dibuar paint y canvas
    private Paint  canvasPaint;

    private static Paint drawPaint;
    //color inicial
    private  int paintcolor = 0xFF660000;

    private Canvas drawCanvas;

    private Bitmap canvasBitmap;

    private static int paintColor = 0xFFFF0000;

    private static boolean borrado=false;


    public Lienzo(Context context, AttributeSet attrs) {
        super(context, attrs);
        setupDrawing();
    }
    public void setColor(String newcolor){
        invalidate();
        paintcolor = Color.parseColor(newcolor);
        drawPaint.setColor(paintcolor);

    }
    public static   void settam(float tam){
     // float pixel = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,tam, getResources().getDisplayMetrics());
    //tama=pixel;
        drawPaint.setStrokeWidth(tam);

    }
    public void setupDrawing(){
        drawPath = new Path();
        drawPaint = new Paint();
        drawPaint.setColor(paintcolor);
        drawPaint.setAntiAlias(true);
       settam(20);
        drawPaint.setStrokeWidth(20);
        drawPaint.setStyle(Paint.Style.STROKE);
        drawPaint.setStrokeCap(Paint.Cap.ROUND);
        drawPaint.setStrokeJoin(Paint.Join.ROUND);

        canvasPaint = new Paint(Paint.DITHER_FLAG);




        //pintar
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float touchX = event.getX();
        float touchy= event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                drawPath.moveTo(touchX,touchy);
                break;
            case MotionEvent.ACTION_MOVE:
                drawPath.lineTo(touchX,touchy);
                break;
            case MotionEvent.ACTION_UP:
                drawPath.lineTo(touchX,touchy);
                drawCanvas.drawPath(drawPath,drawPaint);
                drawPath.reset();
                break;
            default:
                return false;
        }
        invalidate();
        return true;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        canvasBitmap=Bitmap.createBitmap(w,h,Bitmap.Config.ARGB_8888);
        drawCanvas= new Canvas(canvasBitmap);
    }

    //pinta vista desde el pain
    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawBitmap(canvasBitmap,0,0,canvasPaint);
        canvas.drawPath(drawPath,drawPaint);

    }
    public void NuevoDibujo(){
        drawCanvas.drawColor(0, PorterDuff.Mode.CLEAR);
        invalidate();

    } public static void setBorrado(boolean estaborrado){
        borrado=estaborrado;
        if(borrado) {

            drawPaint.setColor(Color.WHITE);
            //drawPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));

        }
        else {
            drawPaint.setColor(paintColor);
            //drawPaint.setXfermode(null);
        }
    }

}
