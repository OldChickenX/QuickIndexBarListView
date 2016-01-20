package com.example.administrator.quickindexbarlistview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by Administrator on 2016/1/20.
 * 讲索引条目分为26个盒子  将字母画入
 * 自定义view画字母是从左下角开始画
 */
public class IndexBar extends View {
    private String[] words = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O"
            , "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
    private Paint paint;
    private int cellWidth,cellHeight;
    private OnIndexSelectedListener onIndexSelectedListener;
    public IndexBar(Context context) {
        this(context,null);
    }

    public IndexBar(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public IndexBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    //初始化画笔paint
    private void init() {
        paint=new Paint();
        paint.setAntiAlias(true);
        paint.setTextSize(30);
        paint.setColor(Color.WHITE);
        paint.setTypeface(Typeface.DEFAULT_BOLD);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (int i = 0; i < words.length; i++) {
            if (cellWidth==0) cellWidth=getMeasuredWidth();
            if (cellHeight==0) cellHeight=getMeasuredHeight() / 26;
            String word = words[i];
            //x:记录索引字母开始画的位置
            float x = 0;
            //y:记录索引字母开始画的位置
            float y = 0;
            //获取字母的宽度
            float wordWidth = paint.measureText(word);
            x = cellWidth / 2 - wordWidth / 2 ; //开始画的X为格子中间位置长度-字母中间到左边的长度，这个不会变
            Rect rect = new Rect();
            paint.getTextBounds(word,0,word.length(),rect);//将字母索引放入矩形中
            //获取字母的高度
            float wordHeight = rect.height();
            y = i * cellHeight + cellHeight / 2 + wordHeight / 2 ;//y为i个格子的高度+格子中间位置+字母中间高度

            paint.setColor(Color.parseColor(lastIndex==i?"#f00000":"#ffffff"));
            canvas.drawText(word, x, y, paint);

        }
    }

    //点击的索引坐标
    private int lastIndex=-1;
    /**
     * 自定义索引内的点击事件自己处理
     * @param event
     * @return
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction())
        {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
                float y = event.getY();//得到点击的y坐标点
                int index = (int) (y / cellHeight);//触摸时处于的索引
                //两次点击或者滑动的索引相等或者索引大于总长度（出现大于总长度的原因是数据的偏差导致了下面一点大于了总长度）
                if (index==lastIndex||index>=words.length){
                    return true;
                }else{              //点击的索引在总索引里面并且两次点击的不一样
                    //点击的索引
                    lastIndex=index;

                    //索引点击的监听方法
                    if (onIndexSelectedListener!=null){
                        onIndexSelectedListener.onIndexSelected(words[index]);
                    }
                }

                break;
            case MotionEvent.ACTION_UP: //  抬起时将索引复原到初始
                lastIndex=-1;
                break;
        }
        invalidate();//刷新界面
        return true;
    }

    public void setOnIndexSelectedListener(OnIndexSelectedListener onIndexSelectedListener){
        this.onIndexSelectedListener=onIndexSelectedListener;
    }
    public interface OnIndexSelectedListener{
        void onIndexSelected(String word);
    }
}
