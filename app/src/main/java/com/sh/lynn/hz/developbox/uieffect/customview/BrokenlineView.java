package com.sh.lynn.hz.developbox.uieffect.customview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.sh.lynn.hz.developbox.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * Created by hyz84 on 16/12/29.
 */

public class BrokenlineView extends View {
    public static final String TAG = "BrokenlineView";
    private int lineColor;
    private int bodyColor;
    private int textColor;
    private Paint linePaint, bodyPaint, textPaint;
    private List<Float> points = new ArrayList<Float>();
    private int mHeight, mWidth, mSize;

    //注意下两个构造函数的实现方法，是this,不是super
    public BrokenlineView(Context context) {
        this(context, null);
    }

    public BrokenlineView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    //注意构造函数的实现方法，是super！
    public BrokenlineView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //从xml文件中获取样式属性值
        TypedArray array = context.getTheme().obtainStyledAttributes(attrs, R.styleable.MyChartView, defStyleAttr, 0);
        int n = array.getIndexCount();
        for (int i = 0; i < n; i++) {
            int attr = array.getIndex(i);
            switch (attr) {
                case R.styleable.MyChartView_lineColor:
                    // 默认颜色设置为红色
                    lineColor = array.getColor(attr, Color.RED);
                    break;
                case R.styleable.MyChartView_bodyColor:
                    // 默认颜色设置为灰色
                    bodyColor = array.getColor(attr, Color.GRAY);
                case R.styleable.MyChartView_textColor:
                    // 默认颜色设置为红色
                    textColor = array.getColor(attr, Color.RED);
                    break;
                default:
                    break;
            }
        }
        array.recycle();
        init();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //设置X轴的间隔，根据传递进来的坐标点来决定，-5为调整值，以免折线画X轴终点
        mSize = mWidth / (points.size() == 0 ? 1 : (points.size() - 1)) - 5;
        //这里是对称坐标，所以定义X轴的位置，为高度的一半
        int x_axis = mHeight / 2;
        //折线的画笔
        linePaint.setColor(lineColor);
        linePaint.setStrokeWidth(5f);
        linePaint.setStyle(Paint.Style.FILL);
        //区域线的画笔
        bodyPaint.setColor(bodyColor);
        bodyPaint.setStrokeWidth(2f);
        bodyPaint.setStyle(Paint.Style.FILL);
        //坐标值画笔
        textPaint.setColor(textColor);
        textPaint.setTextSize(20f);
        textPaint.setStyle(Paint.Style.FILL);

        int max_y_axis = getMaxAxis();
        float ratio = getRatio();
        //默认设置X轴上下为4个间隔
        int index = 5;
        for (int x = 1; x < index; x++) {
            //画X轴上面的区域线 和 区域坐标轴值
            canvas.drawLine(0f, x * x_axis / index, mWidth, x * x_axis / index, bodyPaint);
            canvas.drawText((int) (x * x_axis / (index * ratio)) + "", 5f, (index - x) * x_axis / index - 8f, textPaint);
            //画X轴下面的区域线 和 区域坐标轴值
            canvas.drawLine(0f, mHeight - x * x_axis / index, mWidth, mHeight - x * mHeight / 2 / index, bodyPaint);
            canvas.drawText(-(int) ((index - x) * x_axis / (index * ratio)) + "", 5f, mHeight - x * x_axis / index - 8f, textPaint);
        }
        //画X轴
        bodyPaint.setColor(Color.BLACK);
        canvas.drawLine(0f, x_axis, mWidth, x_axis, bodyPaint);

       //画折线
        for (int x = 0; x < points.size(); x++) {
            Log.e(TAG, "points" + points.get(x));
            //将坐标全部还原成正值
            float point = points.get(x) + max_y_axis;
            //绘制坐标点
            canvas.drawCircle((float) x * mSize, mHeight - ratio * point, 4f, linePaint);
            if (x < points.size() - 1)
                //画折线
                canvas.drawLine((float) x * mSize, (mHeight - ratio * point), (float) (x + 1) * mSize, (mHeight - ratio * (points.get(x + 1) + max_y_axis)), linePaint);


        }


    }

    /**
     * 对称坐标轴，获取Y轴坐标的最大值
     *
     * @return
     */
    private int getMaxAxis() {

        Object[] floats = points.toArray();
        Arrays.sort(floats);
        //取坐标点中绝对值最大的数
        float max = (float) floats[floats.length - 1] > Math.abs((float) floats[0]) ? (float) floats[floats.length - 1] : Math.abs((float) floats[0]);
        Log.e(TAG, "max: " + max);
        //平分坐标轴，取得Y轴的最大值
        String value  = String.valueOf(max);
        String[] values =  value.split("\\.");
        String len ="1";
        for(int x=0;x<values[0].length()-1;x++){
            len+="0";
        }
        double max_y_axis = Math.ceil(max / Integer.parseInt(len)) * Integer.parseInt(len);

        Log.e(TAG, "max_y_axis: " + max_y_axis);
        return (int) max_y_axis;
    }

    /**
     * 获取实际坐标值放到图标中的缩放比例
     *
     * @return
     */
    private float getRatio() {
        int zero_x = mHeight / 2;
        double ratio = ((double) zero_x) / getMaxAxis();
        Log.e(TAG, "ratio: " + ratio);
        return (float) ratio;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width;
        int height;
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);

        if (widthMode == MeasureSpec.EXACTLY) {
            width = widthSize;
        } else {
            width = widthSize * 1 / 2;
        }
        if (heightMode == MeasureSpec.EXACTLY) {
            height = heightSize;
        } else {
            height = heightSize * 1 / 2;
        }

        setMeasuredDimension(width, height);
        Log.e(TAG, "width:" + width + "   height:" + height);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    //获取自身的宽高
        mWidth = getWidth();
        mHeight = getHeight();
        Log.e(TAG, "mHeight:" + mHeight + " mWidth:" + mWidth);
        // mSize = mWidth / (13);

    }


    //初始化画笔
    private void init() {
        linePaint = new Paint();
        linePaint.setAntiAlias(true);

        bodyPaint = new Paint();
        bodyPaint.setAntiAlias(true);

        textPaint = new Paint();
        textPaint.setAntiAlias(true);

    }

    /**
     * 设置坐标点
     * @param _points
     */
    public void setPoints(List<Float> _points) {
        points = _points;
    }
}
