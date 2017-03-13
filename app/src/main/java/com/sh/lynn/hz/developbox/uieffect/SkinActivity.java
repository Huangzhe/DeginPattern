package com.sh.lynn.hz.developbox.uieffect;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.sh.lynn.hz.developbox.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import solid.ren.skinlibrary.SkinLoaderListener;
import solid.ren.skinlibrary.base.SkinBaseActivity;
import solid.ren.skinlibrary.loader.SkinManager;

public class SkinActivity extends SkinBaseActivity {

    Button mButton;
    @BindView(R.id.tv_skin)
    TextView mTvSkin;
    @BindView(R.id.btn_changeSkin)
    Button mBtnChangeSkin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_skin);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
//

    }


    @OnClick(R.id.btn_changeSkin)
    public void onClick1() {
        loadSkin("skin_1.skin");
    }
    @OnClick(R.id.btn_changeSkin2)
    public void onClick2() {
        loadSkin("skin_2.skin");
    }
    private void loadSkin(String skinFile) {
        SkinManager.getInstance().loadSkin(skinFile,
                new SkinLoaderListener() {
                    @Override
                    public void onStart() {
                        Toast.makeText(getApplicationContext(), "正在切换中", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onSuccess() {
                        Toast.makeText(getApplicationContext(), "切换成功", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailed(String errMsg) {
                        Toast.makeText(getApplicationContext(), "切换失败", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onProgress(int progress) {

                    }


                }

        );
    }
}
