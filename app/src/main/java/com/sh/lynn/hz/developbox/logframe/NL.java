package com.sh.lynn.hz.developbox.logframe;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Looper;
import android.os.Process;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.io.FilenameFilter;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2016/8/15.
 * <p/>
 * 网络日志
 */
public class NL {

    //日志存储类型
    public final static int L_TYPE_FILE = 0x00;
    public final static int L_TYPE_NET = 0x01;
    public final static int L_TYPE_FILE_NET = 0x2;
    int type = L_TYPE_FILE;

    public static NL getInstance() {
        return InnerNl.istance;
    }

    static class InnerNl {
        final static NL istance = new NL();
    }

    private NL() {
    }


    ILog ilog;
    Context ctx;

    public void init(Context context, ILog log) {
        if (log == null)
            throw new IllegalArgumentException("ILog can be null");
        this.ilog = log;
        this.ctx = context;
        final Thread.UncaughtExceptionHandler uncaughtExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread thread, Throwable ex) {
                synchronized (this) {
                    if (ex == null) {
                        uncaughtExceptionHandler.uncaughtException(thread, ex);
                        return;
                    }
                    NL.getInstance().e(ex);
                    //使用Toast来显示异常信息
                    new Thread() {
                        @Override
                        public void run() {
                            Looper.prepare();
                            Toast.makeText(ctx, "系统异常,程序正在关闭...", Toast.LENGTH_LONG).show();
                            Looper.loop();
                        }
                    }.start();
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        Process.killProcess(Process.myPid());
                        System.exit(1);
                    }
                }
            }
        });
    }


    /**
     * 以文件的方式发送
     */
    public void sendLogs(String dir) {
        File file = new File(dir);
        File[] list = file.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String filename) {
                return filename.endsWith(".cr");
            }
        });

        if (null == list) return;

        for (File f : list) {
            write(Log.ERROR, f);
        }
    }

    public void e(Throwable e) {
        e.printStackTrace();
        String msg = collectInfo();
        write(Log.ERROR, msg + "\n" + e.getLocalizedMessage());
    }

    public void i(String tag, String msg) {
        write(Log.INFO, msg);
        Log.d(tag, msg);
    }

    public void w(String tag, String msg) {
        write(Log.WARN, msg);
        Log.w(tag, msg);
    }

    /**
     * 调用实现，写入信息
     */
    private void write(int level, String msg) {
        if (ilog != null)
            ilog.write(level, msg);
    }

    /**
     * 调用实现，写入信息
     */
    private void write(int level, File msg) {
        if (ilog != null)
            ilog.write(level, msg);
    }

    /**
     * 获取相关信息
     */
    private String collectInfo() {
        StringBuilder buider = new StringBuilder();
        try {
            PackageManager pm = ctx.getPackageManager();
            PackageInfo pi = pm.getPackageInfo(ctx.getPackageName(),
                    PackageManager.GET_ACTIVITIES);
            if (pi != null) {
                String versionName = pi.versionName == null ? "null"
                        : pi.versionName;
                String versionCode = pi.versionCode + "";
                buider.append("versionName:");
                buider.append(versionName);
                buider.append("\n");
                buider.append("versionCode:");
                buider.append(versionCode);
                buider.append("\n");
                buider.append("crashTime:");
                buider.append(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
                buider.append("\n");
            }
        } catch (PackageManager.NameNotFoundException e) {
            //Log.e(TAG, "an error occured when collect package info", e);
        }
        Field[] fields = Build.class.getDeclaredFields();
        for (Field field : fields) {
            try {
                field.setAccessible(true);
                buider.append(String.format("%s:", field.getName()));
                buider.append(field.get(null).toString());
                buider.append("\n");
                //Log.d(TAG, field.getName() + " : " + field.get(null));
            } catch (Exception e) {
                // Log.e(TAG, "an error occured when collect crash info", e);
            }
        }
        return buider.toString();
    }
}
