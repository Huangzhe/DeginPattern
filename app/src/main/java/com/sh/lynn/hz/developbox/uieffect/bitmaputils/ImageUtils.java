package com.sh.lynn.hz.developbox.uieffect.bitmaputils;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.util.Base64;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class ImageUtils {

    public static final int Max_Image_size = 100;

    /**
     * 压缩签名图片
     *
     * @param bitmap
     * @return
     */
    public static Bitmap scaleBitMap(Bitmap bitmap) {

        Matrix matrix = new Matrix();
        matrix.postScale(0.4f, 0.4f); // 长和宽放大缩小的比例
        Bitmap resizeBmp = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);

        return resizeBmp;

    }


    /**
     * * 压缩签名图片
     *
     * @param ctx  activity
     * @param path 路径
     * @return
     */
    public static Bitmap scaleBitMap(Activity ctx, String path) {

        ImageCompress compress = new ImageCompress();
        ImageCompress.CompressOptions options = new ImageCompress.CompressOptions();
        // options.uri = Uri.parse();
        File file = new File(path);
        if (!file.exists()) {
            return null;
        }
        options.uri = Uri.fromFile(file);

        options.maxWidth = ctx.getWindowManager().getDefaultDisplay().getWidth();
        options.maxHeight = ctx.getWindowManager().getDefaultDisplay().getHeight();
        Bitmap bitmap = compress.compressFromUri(ctx, options);
        if (bitmap != null) {
            bitmap = rotateBitmap(path, bitmap);
        }
        return bitmap;

    }

    public static void getScaleBitMap(String path, double inSampleSize, String outPutFile) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = (int) Math.sqrt(inSampleSize);

        Bitmap bitmap = BitmapFactory.decodeFile(path, options);
        ByteArrayOutputStream bos;
        bitmap = rotateBitmap(path, bitmap);
        bos = compress(bitmap);
        try {
            FileOutputStream fos = new FileOutputStream(outPutFile);
            fos.write(bos.toByteArray());
            fos.flush();
            fos.close();

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        bitmap.recycle();
        bitmap = null;

    }

    //把bitmap转换成String
    public static String bitmapToString(String filePath) {
        Bitmap bm = getSmallBitmap(filePath);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.JPEG, 40, baos);
        byte[] b = baos.toByteArray();
        Log.d("d", "压缩后的大小=" + b.length);//1.5M的压缩后在100Kb以内，测试得值,压缩后的大小=94486,压缩后的大小=74473
        return Base64.encodeToString(b, Base64.NO_WRAP);
    }


    // 根据路径获得图片并压缩，返回bitmap用于显示
    public static Bitmap getSmallBitmap(String filePath) {
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(filePath, options);

        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, 480, 800);

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;

        return BitmapFactory.decodeFile(filePath, options);
    }

    //计算图片的缩放值
    public static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {
            final int heightRatio = Math.round((float) height/ (float) reqHeight);
            final int widthRatio = Math.round((float) width / (float) reqWidth);
            inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;
        }
        return inSampleSize;
    }



    // 在图片预览的过程中将图片统一翻转
    public static Bitmap rotateBitmap(String path, Bitmap bitmap) {

        int rotate = 0;
        ExifInterface exifInterface;
        try {
            exifInterface = new ExifInterface(path);
            int orientationResult = exifInterface.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_UNDEFINED);

            switch (orientationResult) {
                case ExifInterface.ORIENTATION_ROTATE_90:
                    rotate = 90;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_180:
                    rotate = 180;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_270:
                    rotate = 270;
                    break;
                default:
                    break;
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        Matrix matrix = new Matrix();
        matrix.postRotate(rotate);
        bitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
        return bitmap;
    }

    /**
     * 图片压缩方法
     *
     * @param bitmap 图片文件
     * @return 压缩后的字节流
     * @throws Exception
     */
    public static ByteArrayOutputStream compress(Bitmap bitmap) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);// 质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中
        int options = 100;
        while (baos.toByteArray().length / 1024 > Max_Image_size) { // 循环判断如果压缩后图片是否大于maxSize,大于继续压缩
            options -= 5;// 每次都减少10
            if (options < 0) {
                break;
            }
            baos.reset();// 重置baos即清空baos
            bitmap.compress(Bitmap.CompressFormat.JPEG, options, baos);// 这里压缩options%，把压缩后的数据存放到baos中
        }

        return baos;
    }


    /**
     * 保存图片
     *
     * @param bm
     * @param file
     * @return
     */
    public static boolean saveBitmap(Bitmap bm, File file) {
        boolean result = false;

        //if(file)
        try {

            // BitmapFactory factory = BitmapFactory.decodeFile(pathName);

            FileOutputStream out = new FileOutputStream(file);
            result = bm.compress(Bitmap.CompressFormat.JPEG, 100, out);

            out.flush();
            out.close();

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return result;

    }

    /**
     * bitmap转为base64
     *
     * @param imgFilePath
     * @return
     */
    public static String bitmapToBase64(File imgFilePath) {

        String result = null;
        //ByteArrayOutputStream baos = null;
        byte[] data = null;
        try {

            InputStream in = new FileInputStream(imgFilePath);
            data = new byte[in.available()];
            in.read(data);
            in.close();
            result = Base64.encodeToString(data, Base64.NO_WRAP);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }


    /**
     * base64转为bitmap
     *
     * @param base64Data
     * @return
     */
    public Bitmap base64ToBitmap(String base64Data) {
        byte[] bytes = Base64.decode(base64Data, Base64.NO_WRAP);
        return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
    }
}
