package com.moeiny.reza.kotlincheqtest.customview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class BarChartView extends View {

    public BarChartView(Context context) {
        super(context);
    }

    public BarChartView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public BarChartView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public BarChartView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    private RectF rect1 = new RectF(0,0,0,0);
    private static final int radius = 35;
    private static final int strokeWidth = 5;
    private static final int strokeWidthCircle = 35;
    private static final int circleMargin = 10;

    private static final Paint pRect;
    private static  Paint pRectNew;
    private static final Paint pRect2;
    private static final Paint pRect3;
    private static final Paint pRect4;
    private static final Paint pText;
    private static final Paint pText2;
    private static final Paint pText3;
    private static final Paint pCost;

    static Paint[] p = new Paint[]{
            new Paint(){{
                setColor(Color.parseColor("#e0530e"));
            }},
            new Paint(){{
                setColor(Color.parseColor("#53e00e"));
            }}
            ,
            new Paint(){{
                setColor(Color.parseColor("#0e53e0"));
            }}
            ,
            new Paint(){{
                setColor(Color.parseColor("#530ee0"));
            }}
    };

    static {
        pRect = new Paint(){{
            setStrokeWidth(strokeWidth);
            setColor(Color.rgb(200,200,200));
            setStyle(Style.STROKE);
        }};
        pRectNew = new Paint(){{
            setStrokeWidth(0);
            setColor(Color.rgb(200,200,200));
            setStyle(Style.FILL);
        }};
        pRect2 = new Paint(){{
            setStrokeWidth(strokeWidth);
            setColor(Color.rgb(100,100,100));
            setStyle(Style.STROKE);
        }};
        pRect3= new Paint(){{
            setStrokeWidth(strokeWidth);
            setColor(Color.rgb(150,150,150));

            setStyle(Style.FILL_AND_STROKE);
        }};
        pRect4 = new Paint(){{
            setStrokeWidth(strokeWidth);
            setColor(Color.rgb(250,250,250));
            setStyle(Style.STROKE);
        }};
        pText = new Paint(){{
            setColor(Color.rgb(20,20,20));
            setTextAlign(Align.LEFT);
            setTextSize(40);
        }};
        pText3 = new Paint(){{
            setColor(Color.rgb(20,20,20));
            setTextAlign(Align.CENTER);
            setTextSize(40);
        }};
        pText2 = new Paint(){{
            setColor(Color.rgb(20,20,20));
            setTextAlign(Align.CENTER);
            setTextSize(38);
        }};
        pCost = new Paint(){{
            setColor(Color.rgb(20,20,20));
            setTextAlign(Align.CENTER);
            setTextSize(28);
        }};
    }


    @Override
    protected void onSizeChanged(final int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        rect1 = new RectF(strokeWidth / 2,strokeWidth /2,
                w - strokeWidth /2,h - strokeWidth /2);
        pRectNew = new Paint(){{
            setStrokeWidth(0);
            setColor(Color.rgb(200,200,200));
            setStyle(Style.FILL);
            setShader(new LinearGradient(0, 0, w,0, Color.LTGRAY, Color.YELLOW, Shader.TileMode.MIRROR));
        }};
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int w = getWidth() ;
        int h = getHeight();
        canvas.drawRoundRect(rect1,radius, radius, pRectNew );
        canvas.drawRoundRect(rect1,radius, radius, pRect );

        canvas.drawText(title, rectMargin ,2*rectMargin, pText);

        int space =  w/10;
        int shapewidth = (int) (space*1.25f);
        int maxshapeheight=h*2/5;
        int max=0;

        Iterator<Map.Entry<String, Integer>> mydata = items.entrySet().iterator();
        while(mydata.hasNext()){
            Map.Entry<String, Integer> next = mydata.next();
            int value = next.getValue();
            if (value>max){
                max=value;
            }
        }

        int base=rectMargin*3+textBounds.height();
        int startx = 0;
        int index = 0;
        Iterator<Map.Entry<String, Integer>> it = items.entrySet().iterator();
        while(it.hasNext()){
            Map.Entry<String, Integer> next = it.next();
            int value = next.getValue();
            String txt = next.getKey();
            int height=0;
            if(max!=0){
               height=value*maxshapeheight/max;
            }
            canvas.drawText("$" + value, startx+space+shapewidth/2, base+(maxshapeheight-height), pText3);

            canvas.drawRoundRect(space+startx,base+20+(maxshapeheight-height),startx+space+shapewidth,(base+30+maxshapeheight),radius, radius, p[index] );

            canvas.drawText("" + txt, space+startx+shapewidth/2, (base+65+maxshapeheight), pText2);
            startx += (space + shapewidth);
            index = (index + 1) % p.length;
        }
    }
    private static final int rectMargin = 40;
//    Rect destRect = new Rect(rectMargin,rectMargin,rectMargin+rectWidth, rectMargin+rectWidth);
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

    HashMap<String, Integer> items = new HashMap<>();

    public HashMap<String, Integer> getItems() {
        return items;
    }

    public void setItems(HashMap<String, Integer> items) {
        this.items = items;
        invalidate();
    }
}

