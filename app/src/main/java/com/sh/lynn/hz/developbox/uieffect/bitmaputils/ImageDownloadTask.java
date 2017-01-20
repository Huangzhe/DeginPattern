package com.sh.lynn.hz.developbox.uieffect.bitmaputils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import com.sh.lynn.hz.developbox.utils.Md5;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Administrator on 2016/7/5.
 */
public class ImageDownloadTask {

    private static ImageDownloadTask instance = null;

    public static ImageDownloadTask getInstance() {
        if (instance == null)
            instance = new ImageDownloadTask();
        return instance;
    }

    WeakReference<Context> context;

    public void init(Context context) {
        this.context = new WeakReference<Context>(context);
    }

    ExecutorService poolExecutor;

    private ImageDownloadTask() {
        poolExecutor = Executors.newFixedThreadPool(3);
    }


    public void addTask(String url) {
        if (!url.startsWith("http://")) {
            return;
        }
        File file = new File(localPath(url));
        Date d = new Date();
        if (file.exists() && (d.getTime() - file.lastModified()) < 5000) {
            return;
        }
        poolExecutor.submit(new DownloadTask(url));
    }

    public void shutdown() {
       // poolExecutor.shutdown();
    }

    public static String localPath(String url) {
        String name = Md5.getMd5(" " + ".jpg");
        File file = new File("", name);
        return file.getAbsolutePath();
    }

    class DownloadTask implements Runnable {
        String url;

        public DownloadTask(String url) {
            this.url = url;
        }

        @Override
        public void run() {
            ImageDownload download = new ImageDownload(context.get());
            try {
                InputStream input = download.getStream(url, null);
                if (input == null) {
                    return;
                }
                byte[] bytes = new byte[1024];
                String path = localPath(url);
                FileOutputStream fileOutputStream = new FileOutputStream(new File(path));
//                while (true) {
//                    if (input.read(bytes) > 0) {
//                        fileOutputStream.write(bytes);
//                    } else {
//                        break;
//                    }
//                }
                Bitmap bmp = BitmapFactory.decodeStream(input);
                bmp.compress(Bitmap.CompressFormat.JPEG,100,fileOutputStream);
                fileOutputStream.close();
                input.close();
                //发送已执行完毕
//                EventBus.getDefault().post(new DownloadEvent());
                Log.d("file-download","url:"+path+"下载完毕");
            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
