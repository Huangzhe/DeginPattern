package com.sh.lynn.hz.developbox;

import android.app.Application;

import com.sh.lynn.hz.developbox.netframe.volleyutil.RequestHelper;

/**
 * Created by hyz84 on 17/1/13.
 */

public class App extends Application {
    public static App app;
    public static synchronized App getInstance() {
        if (app == null) {
            new Throwable("Application is not initialization ");
        }
        return app;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        app =this;
        RequestHelper.getInstance().init(this);
    }
}
