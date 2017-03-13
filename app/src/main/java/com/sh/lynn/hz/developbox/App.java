package com.sh.lynn.hz.developbox;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Build;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.sh.lynn.hz.developbox.netframe.volleyutil.RequestHelper;
import com.sh.lynn.hz.developbox.uieffect.bitmaputils.ImageDownload;
import com.sh.lynn.hz.developbox.uieffect.bitmaputils.ImageDownloadTask;
import com.sh.lynn.hz.developbox.utils.TranMd5FileNameGenerator;

import solid.ren.skinlibrary.base.SkinBaseApplication;

/**
 * Created by hyz84 on 17/1/13.
 */

public class App extends SkinBaseApplication {
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
        initImageConfiguration();
    }


    private void initImageConfiguration() {
        ImageLoader imageLoader = ImageLoader.getInstance();
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
                this).threadPriority(Thread.NORM_PRIORITY - 2)
                .imageDownloader(new ImageDownload(this))
                .threadPoolSize(3).memoryCacheSize(getMemoryCacheSize(getApplicationContext()))
                .denyCacheImageMultipleSizesInMemory()
                .defaultDisplayImageOptions(getImageOptionsConfiguration().build())
                .diskCacheFileNameGenerator(new TranMd5FileNameGenerator())
                .tasksProcessingOrder(QueueProcessingType.LIFO).build();
        imageLoader.init(config);
        ImageDownloadTask.getInstance().init(this);
    }

    public static DisplayImageOptions.Builder getImageOptionsConfiguration() {
        DisplayImageOptions.Builder options = new DisplayImageOptions.Builder();
        options.showImageOnLoading(R.drawable.img_default); // 设置图片下载期间显示的图片
        options.showImageForEmptyUri(R.drawable.img_default);  // empty URI时显示的图片
        options.showImageOnFail(R.drawable.img_default);       // 不是图片文件 显示图片
        options.cacheInMemory(true);// 设置下载的图片是否缓存在内存中
        options.cacheOnDisk(true);
        options.considerExifParams(true);//设置下载的图片是否缓存在SD卡中
        options.bitmapConfig(Bitmap.Config.ARGB_8888);    //设置图片的解码类型
        //      .setFileCachePath(Environment.getExternalStorageDirectory().toString() + "/mycache") // 设置文件缓存保存目录
        return options;
    }

    private int getMemoryCacheSize(Context context) {
        int memoryCacheSize;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ECLAIR) {
            int memClass = ((ActivityManager) context
                    .getSystemService(Context.ACTIVITY_SERVICE))
                    .getMemoryClass();
            memoryCacheSize = (memClass / 8) * 1024 * 1024; // 1/8 of app memory limit
        } else {
            memoryCacheSize = 2 * 1024 * 1024;
        }
        return memoryCacheSize;
    }

}
