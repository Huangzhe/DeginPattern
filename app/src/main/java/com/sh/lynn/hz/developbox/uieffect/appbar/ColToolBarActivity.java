package com.sh.lynn.hz.developbox.uieffect.appbar;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.sh.lynn.hz.developbox.R;
import com.sh.lynn.hz.developbox.utils.ScreenUtil;
import com.statusbar.StatusBarHelper;

import java.lang.reflect.Field;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class ColToolBarActivity extends AppCompatActivity {
    //第三方工具，控制状态栏
    StatusBarHelper mStatusBarHelper;
    Toolbar toolbar;
    int toobarMaxHeight = 0;
    AppBarLayout title;
    TextView tv_tilte;
    ImageView iv_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo_col_tool_bar);
         toolbar = (Toolbar) findViewById(R.id.toolbar);
        title = (AppBarLayout) findViewById(R.id.title);
        tv_tilte = (TextView) findViewById(R.id.tv_title);
        iv_back = (ImageView) findViewById(R.id.left);
       // setSupportActionBar(toolbar);
        ButterKnife.bind(this);
        initWindow();
        initToolBar(true);
        tv_tilte.setText("Demo");
        title.addOnOffsetChangedListener(listener);

    }
    @TargetApi(19)
    private void initWindow() {
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {
            if (mStatusBarHelper == null) {
                mStatusBarHelper = new StatusBarHelper(this, StatusBarHelper.LEVEL_19_TRANSLUCENT,
                        StatusBarHelper.LEVEL_21_NORMAL_FULL);
            }
            //fasle Activity 全屏 true 状态条单独
            mStatusBarHelper.setActivityRootLayoutFitSystemWindows(false);
            mStatusBarHelper.setColor(Color.TRANSPARENT);
        }
    }

    @TargetApi(19)
    /**
     * 是否把状态条设置到toolbar的高度上
     */
    private void initToolBar(Boolean on) {
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {
            if (on) {
                CollapsingToolbarLayout.LayoutParams layoutParams = (CollapsingToolbarLayout.LayoutParams) toolbar.getLayoutParams();
                layoutParams.topMargin = getStatusBarHeight(ColToolBarActivity.this);
                toolbar.setLayoutParams(layoutParams);
            } else {
                CollapsingToolbarLayout.LayoutParams layoutParams = (CollapsingToolbarLayout.LayoutParams) toolbar.getLayoutParams();
                layoutParams.topMargin = 0;
                toolbar.setLayoutParams(layoutParams);
            }
        }
    }

    /**
     * 计算状态栏高度
     * @param context
     * @return
     */
    public static int getStatusBarHeight(Context context) {
        Class<?> c = null;
        Object obj = null;
        Field field = null;
        int x = 0, sbar = 0;
        try {
            c = Class.forName("com.android.internal.R$dimen");
            obj = c.newInstance();
            field = c.getField("status_bar_height");
            x = Integer.parseInt(field.get(obj).toString());
            sbar = context.getResources().getDimensionPixelSize(x);
            return sbar;
        } catch (Exception e1) {
            //log.e("get status bar height fail");
            e1.printStackTrace();
        }
        return ScreenUtil.dp2px(context, 64);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onAttachedToWindow();
        int barHeight = title.getMeasuredHeight();
        int toolHeight = toolbar.getMeasuredHeight();
        int mt = getStatusBarHeight(this);
        toobarMaxHeight = barHeight - toolHeight - mt;
    }

    //根据滑动的距离设置是否显示标题，已经返回按钮的图标
    AppBarLayout.OnOffsetChangedListener listener = new AppBarLayout.OnOffsetChangedListener() {
        @Override
        public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
            int offset = -verticalOffset;

            if (offset >= toobarMaxHeight / 10 * 7) {
                tv_tilte.setVisibility(View.VISIBLE);
                iv_back.setImageResource(R.drawable.ic_arrow_back_white_24dp);
            } else {
               tv_tilte.setVisibility(View.INVISIBLE);
                iv_back.setImageResource(R.drawable.ic_arrow_back_yellow_800_24dp);

            }

        }
    };
    @OnClick(R.id.left)
    public void onClickBack(){
        finish();
    }

}
