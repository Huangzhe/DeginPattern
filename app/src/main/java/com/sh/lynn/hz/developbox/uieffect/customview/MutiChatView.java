package com.sh.lynn.hz.developbox.uieffect.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Huangz on 17/6/2.
 */

public class MutiChatView extends View {
   private  Paint linePaint,rectPaint,bodyPaint,textPaint;
    private Path path;
    private int lineColor;
    private int bodyColor;
    private int textColor;
    private List<MutiChatBean> points ;
    public MutiChatView(Context context) {
        super(context);
        init();
    }

    public void setElements(List<MutiChatBean> _points ){
        points = _points;
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    private void init() {
        linePaint = new Paint();
        linePaint.setAntiAlias(true);

        bodyPaint = new Paint();
        bodyPaint.setAntiAlias(true);

        textPaint = new Paint();
        textPaint.setAntiAlias(true);
        path = new Path();
    }
}
