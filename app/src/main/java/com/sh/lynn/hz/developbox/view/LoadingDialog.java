package com.sh.lynn.hz.developbox.view;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.sh.lynn.hz.developbox.R;


/**
 * Created by Administrator on 2016/5/18.
 */
public class LoadingDialog {

    String TAG = "LoadingDialog";

    public MyDialog dialog;
    private View[] indictors;
    private int indictorCount = 3;
    private int current = 0;
    private boolean running = false;
    private Drawable hover;
    private Drawable normal;

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            indictors[0].setBackgroundDrawable(normal);
            indictors[1].setBackgroundDrawable(normal);
            indictors[2].setBackgroundDrawable(normal);
            indictors[msg.what].setBackgroundDrawable(hover);
        }
    };

    public void show() {
        dialog.show();
        if (running) return;
        new Thread(new LoadThread()).start();
    }

    public void hide() {
        running = false;
        dialog.dismiss();
    }

    public boolean isShow() {
        if (dialog == null)
            return false;
        return dialog.isShowing();
    }

    public LoadingDialog(Context context) {
        init(context, true);
    }

    public LoadingDialog(Context context, boolean cancelable) {
        init(context, cancelable);
    }

    public void setOnDismissListener(final String tag) {
        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                if (running)
                    running = false;

            }
        });
    }


    private void init(Context context, boolean cancelable) {
        View root = LayoutInflater.from(context).inflate(R.layout.layout_loadding, null);
        indictors = new View[indictorCount];
        indictors[0] = root.findViewById(R.id.loadding_indictor1);
        indictors[1] = root.findViewById(R.id.loadding_indictor2);
        indictors[2] = root.findViewById(R.id.loadding_indictor3);
        hover = context.getResources().getDrawable(R.drawable.shape_loading_indictor_hover);
        normal = context.getResources().getDrawable(R.drawable.shape_loading_indictor);

        dialog = new MyDialog(context);
        dialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawableResource(R.color.transparent_white);
        dialog.setContentView(root);
        dialog.setOnDismissListener(listener);
        dialog.setCancelable(cancelable);
    }

    DialogInterface.OnDismissListener listener = new DialogInterface.OnDismissListener() {
        @Override
        public void onDismiss(DialogInterface dialog) {
            if (running)
                running = false;
        }
    };

    class LoadThread implements Runnable {
        @Override
        public void run() {
            current = 0;
            running = true;
            long time = System.currentTimeMillis();
            Log.e("state", "start----------");
            while (running) {
                if (System.currentTimeMillis() - time < 300) {
                    continue;
                }
                time = System.currentTimeMillis();
                handler.sendEmptyMessage(current);
                current = ++current % 3;
            }
            Log.e("state", "end----------");
        }
    }

}
