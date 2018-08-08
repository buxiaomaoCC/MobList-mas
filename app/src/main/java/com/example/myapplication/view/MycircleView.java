package com.example.myapplication.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.example.myapplication.R;

public class MycircleView extends ImageView {

    private int image;//默认图片
    private Integer color;//背景颜色
    private Bitmap bitmap;
    private static final Bitmap.Config BITMAP_CONFIG = Bitmap.Config.ARGB_8888;
    private static final int COLORDRAWABLE_DIMENSION = 1;

    private static final int DEFAULT_BORDER_WIDTH = 0;
    public MycircleView(Context context) {
        super(context);

    }

    public MycircleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context,attrs);
    }

    public MycircleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context,attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Bitmap sfbitmap=null;//缩放后的bitmap
        //获取bitmap
        bitmap= BitmapFactory.decodeResource(getResources(),image);
        //对原始bitmap进行处理，看是否需要进行缩放
        if(bitmap.getWidth()!=getWidth()||bitmap.getHeight()!=getHeight()){
            sfbitmap=Bitmap.createScaledBitmap(bitmap,getWidth(),getHeight(),false);
        }else {
            sfbitmap=bitmap;
        }
        //输出的bitmap
        Bitmap outBitmap=Bitmap.createBitmap(sfbitmap.getWidth(),sfbitmap.getHeight(),Bitmap.Config.ARGB_8888);
        //实例化一张新的画布
        Canvas canvas1=new Canvas(outBitmap);
        //对画布进行裁剪
        Paint paint=new Paint();
        //抗锯齿
        paint.setAntiAlias(true);
        //抗锯齿，对位图进行滤波处理
        paint.setAntiAlias(true);
        //设置防抖动
        paint.setDither(true);
        canvas1.drawCircle(sfbitmap.getWidth()/2,sfbitmap.getHeight()/2,sfbitmap.getWidth()/2,paint);
        //绘制图形
        Rect rectF=new Rect(0,0,sfbitmap.getWidth(),sfbitmap.getHeight());
        //设置两张图片的相交模式，就是上面的circle和绘制的bitmap;
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));//取两层绘制交际，去上层
        canvas1.drawBitmap(sfbitmap,rectF,rectF,paint);
        //吧输出的bitmap放到画布上
        canvas.drawBitmap(outBitmap,0,0,null);
    }

    /**
     * 接受属性值
     * @param context
     * @param attributeSet
     */
    @SuppressLint("ResourceAsColor")
    private void init(Context context, AttributeSet attributeSet){

        TypedArray typedArray=context.obtainStyledAttributes(attributeSet, R.styleable.MycircleView);
        if(typedArray!=null){
            color=typedArray.getColor(R.styleable.MycircleView_percent_circle_color, R.color.white);
//            image=typedArray.getInt(R.styleable.MycircleView_percent_circlr_image,R.drawable.one);
            typedArray.recycle();
        }
    }
    @Override
    public void setImageResource(int resId) {
        super.setImageResource(resId);

    }
    @Override
    public void setImageDrawable(Drawable drawable) {
        super.setImageDrawable(drawable);
        bitmap=getBitmapFromDrawable(drawable);
    }

    private Bitmap getBitmapFromDrawable(Drawable drawable) {
        if (drawable == null) {
            return null;
        }

        if (drawable instanceof BitmapDrawable) {
            return ((BitmapDrawable) drawable).getBitmap();
        }

        try {
            Bitmap bitmap;

            if (drawable instanceof ColorDrawable) {
                bitmap = Bitmap.createBitmap(COLORDRAWABLE_DIMENSION, COLORDRAWABLE_DIMENSION, BITMAP_CONFIG);
            } else {
                bitmap = Bitmap.createBitmap(40, 40, BITMAP_CONFIG);
            }

            Canvas canvas = new Canvas(bitmap);
            drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
            drawable.draw(canvas);
            return bitmap;
        } catch (OutOfMemoryError e) {
            return null;
        }
    }
}
