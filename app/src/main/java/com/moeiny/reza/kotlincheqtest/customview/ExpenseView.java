package com.moeiny.reza.kotlincheqtest.customview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class ExpenseView extends View {


    public ExpenseView(Context context) {
        super(context);
    }

    public ExpenseView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ExpenseView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public ExpenseView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    private RectF rect1 = new RectF(0,0,0,0);
    private static final int radius = 25;
    private static final int strokeWidth = 5;
    private static final int strokeWidthCircle = 25;
    private static final int circleMargin = 10;

    private static final Paint pRect;
    private static final Paint pText;
    private static final Paint pCost;
    private static final Paint pPercent;
    private static final Paint pCircle;
    private static final Paint pArc;
    static {
        pRect = new Paint(){{
            setStrokeWidth(strokeWidth);
            setColor(Color.rgb(200,200,200));
            setStyle(Style.STROKE);
        }};
        pText = new Paint(){{
            setColor(Color.rgb(20,20,20));
            setTextAlign(Align.LEFT);
            setTextSize(40);
        }};
        pCost = new Paint(){{
            setColor(Color.rgb(20,20,20));
            setTextAlign(Align.CENTER);
            setTextSize(40);
        }};
        pPercent = new Paint(){{
            setColor(Color.rgb(60,60,60));
            setTextAlign(Align.CENTER);
            setTextSize(30);
        }};
        pCircle = new Paint(){{
            setStrokeWidth(strokeWidthCircle);
            setColor(Color.rgb(200,200,200));
            setStyle(Style.STROKE);
        }};
        pArc = new Paint(){{
            setColor(Color.parseColor("#e0530e"));
            setStrokeWidth(strokeWidthCircle);
            setStyle(Style.STROKE);
        }};
    }

    static Paint[] p = new Paint[]{
            new Paint(){{
                setColor(Color.parseColor("#ffce61"));
                setStrokeWidth(strokeWidthCircle);
                setStyle(Style.STROKE);
            }},
            new Paint(){{
                setColor(Color.parseColor("#b269ff"));
                setStrokeWidth(strokeWidthCircle);
                setStyle(Style.STROKE);
            }}
            ,
            new Paint(){{
                setColor(Color.parseColor("#c7738a"));
                setStrokeWidth(strokeWidthCircle);
                setStyle(Style.STROKE);
            }}
            ,
            new Paint(){{
                setColor(Color.parseColor("#cc1f00"));
                setStrokeWidth(strokeWidthCircle);
                setStyle(Style.STROKE);
            }}
    };

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        rect1 = new RectF(strokeWidth / 2,strokeWidth /2,
                w - strokeWidth /2,h - strokeWidth /2);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int w = getWidth() ;
        int h = getHeight();
        canvas.drawRoundRect(rect1,radius, radius, pRect );
        if(bmp != null) {
            canvas.drawBitmap(bmp, rMbp, destRect, null);
        }
        canvas.drawText(title, rectMargin *2 + rectWidth,
                rectMargin + rectWidth / 2 - textBounds.exactCenterY(), pText);

        int r =  h - 3 * rectMargin-  rectWidth - strokeWidthCircle;
        r =r/2;

        canvas.drawCircle(w/2, 2 * rectMargin+  rectWidth + r, r, pCircle);
        RectF rArc = new RectF(w/2 - r, 2 * rectMargin+  rectWidth,
                w/2 + r, 2 * rectMargin+  rectWidth + 2*r);
        int index=percent/25;
        canvas.drawArc(rArc, 90,360* percent / 100,false, p[index]);
        canvas.drawText("$" + cost, w / 2, 2 * rectMargin+  rectWidth + r - 20, pCost);
        canvas.drawText( percent+" %", w / 2, 2 * rectMargin+  rectWidth + r + 20, pPercent);
    }

    private static final int rectMargin = 40;
    private static final int rectWidth = 60;
    Rect destRect = new Rect(rectMargin,rectMargin,rectMargin+rectWidth, rectMargin+rectWidth);
    private Bitmap bmp;
    private String title;
    private int cost ;
    private int percent;

    private Rect rMbp;
    public Bitmap getBmp() {
        return bmp;
    }

    public void setBmp(Bitmap bmp) {
        this.bmp = bmp;
        rMbp = new Rect(0,0,this.bmp.getWidth(), this.bmp.getHeight());
        invalidate();
    }

    public String getTitle() {
        return title;
    }

    private final Rect textBounds = new Rect();
    public void setTitle(String title) {
        this.title = title;
        pText.getTextBounds(this.title, 0, this.title.length(), textBounds);
        invalidate();
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
        invalidate();
    }

    public int getPercent() {
        return percent;
    }

    public void setPercent(int percent) {
        this.percent = percent;
        invalidate();
    }
}

