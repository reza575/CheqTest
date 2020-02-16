package com.moeiny.reza.kotlincheqtest.customview;

import android.content.Context;
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

public class PieChartView extends View {

    public PieChartView(Context context) {
        super(context);
    }

    public PieChartView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public PieChartView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public PieChartView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    private String title;
    public String getTitle() {
        return title;
    }

    private final Rect textBounds = new Rect();
    public void setTitle(String title) {
        this.title = title;
        pText.getTextBounds(this.title, 0, this.title.length(), textBounds);
        invalidate();
    }

    private RectF rect1 = new RectF(0,0,0,0);
    private static final int radius = 35;
    private static final int strokeWidth = 5;
    private static final int strokeWidthCircle = 35;
    private static final int circleMargin = 10;

    private static final int rectMargin = 40;

    static final Paint pText= new Paint(){{
        setColor(Color.WHITE);
        setTextSize(35);
        setTextAlign(Align.CENTER);
    }};

    private static Paint pRect = new Paint(){{
        setStrokeWidth(strokeWidth);
        setColor(Color.rgb(200,200,200));
        setStyle(Style.STROKE);
    }};

    static final Paint pText2= new Paint(){{
        setColor(Color.rgb(20,20,20));
        setTextAlign(Align.LEFT);
        setTextSize(40);
    }};
    static final Paint pCircle = new Paint(){{
        setColor(Color.WHITE);
        }};

    private static Paint pRectNew = new Paint(){{
        setStrokeWidth(0);
        setColor(Color.rgb(200,200,200));
        setStyle(Style.FILL);
    }};
    static final Paint pCircle2 = new Paint(){{
        setColor(Color.argb(128,255,255,255));
        }};
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
        int w = getWidth();
        int h = getHeight();
        int minR = Math.min(w,h) / 2;
        canvas.drawRoundRect(rect1,radius, radius, pRectNew );
        canvas.drawRoundRect(rect1,radius, radius, pRect );
        RectF r = new RectF(w/2 -minR,  h/2 - minR,w/2 +minR,  h/2 + minR);
        canvas.drawText(title, rectMargin ,rectMargin * 2 , pText2);
        int startAngle = 0;
        int index = 0;
        Iterator<Map.Entry<String, Integer>> it = items.entrySet().iterator();
        while(it.hasNext()){
            Map.Entry<String, Integer> next = it.next();
            int percent = next.getValue();
            String txt = next.getKey();
            int angle = percent * 360 / 100;
            canvas.drawArc(r, startAngle + 1, angle -1, true, p[index]);
            canvas.drawCircle(w/2, h/2, minR/2, pCircle2 );
            canvas.drawCircle(w/2, h/2, minR/3, pCircle );
            double rad = (startAngle + angle/2) * Math.PI / 180;
            float txtX = w / 2 + 0.8f * minR * (float)Math.cos(rad);
            float txtY = h / 2 + 0.8f * minR * (float)Math.sin(rad);
            canvas.drawText(txt, txtX,txtY,pText);
            canvas.drawText("$"+ percent, txtX,txtY+40,pText);
            startAngle = startAngle + angle;
            index = (index + 1) % p.length;
        }
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
