package com.wokun.tysl.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;

import com.shantoo.widget.toast.RxToast;

import org.xml.sax.Parser;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @Author:HSJ
 * @E-mail:shengjunhu@foxmail.com
 * @Date:2018/11/29/15:07
 * @Version:1.0.0
 * @Class:ImgUtils
 * @Description:
 */
public class ImgUtils {

    //保存文件到指定路径
    public static boolean saveImageToGallery(Context context, View view) {
        // 创建对应大小的bitmap
        Bitmap  bmp = Bitmap.createBitmap(view.getWidth(), view.getHeight(),
                Bitmap.Config.RGB_565);
        Canvas canvas = new Canvas(bmp);
        view.draw(canvas);

        // 首先保存图片
        String storePath = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "dearxy";
        File appDir = new File(storePath);
        if (!appDir.exists()) {
            appDir.mkdir();
        }
        String fileName = System.currentTimeMillis() + ".jpg";
        File file = new File(appDir, fileName);
        try {
            FileOutputStream fos = new FileOutputStream(file);
            //通过io流的方式来压缩保存图片
            boolean isSuccess = bmp.compress(Bitmap.CompressFormat.JPEG, 60, fos);
            fos.flush();
            fos.close();

            //把文件插入到系统图库
            //MediaStore.Images.Media.insertImage(context.getContentResolver(), file.getAbsolutePath(), fileName, null);

            //保存图片后发送广播通知更新数据库
            Uri uri = Uri.fromFile(file);
            context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, uri));
            if (isSuccess) {
                return true;
            } else {
                return false;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }



    //保存文件到指定路径
    public static File saveImageToGallery2(Context context,Bitmap  bmp ) {

        // 首先保存图片
        String storePath = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "dearxy";
        File appDir = new File(storePath);
        if (!appDir.exists()) {
            appDir.mkdir();
        }
        String fileName = System.currentTimeMillis() + ".jpg";
        File file = new File(appDir, fileName);
        try {
            FileOutputStream fos = new FileOutputStream(file);
            //通过io流的方式来压缩保存图片
            boolean isSuccess = bmp.compress(Bitmap.CompressFormat.JPEG, 60, fos);
            fos.flush();
            fos.close();

            //把文件插入到系统图库
            //MediaStore.Images.Media.insertImage(context.getContentResolver(), file.getAbsolutePath(), fileName, null);

            //保存图片后发送广播通知更新数据库
            Uri uri = Uri.fromFile(file);
            context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, uri));
            if (isSuccess) {
                return file;
            } else {
                return file;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }


/*
    * 将布局转化为bitmap
这里传入的是你要截的布局的根View
    * */
public Bitmap getBitmapByView(View headerView) {
       int h = headerView.getHeight();
         Bitmap bitmap = Bitmap.createBitmap(headerView.getWidth(), h, Bitmap.Config.ARGB_8888);
          Canvas canvas = new Canvas(bitmap);
      headerView.draw(canvas);
        return bitmap;
}
/*
       * 压缩图片
       * */

private Bitmap compressImage(Bitmap image) {

 ByteArrayOutputStream baos = new ByteArrayOutputStream();

 image.compress(Bitmap.CompressFormat.JPEG, 10, baos);//质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中

 int options = 100;

 while (baos.toByteArray().length / 1024 > 400) { //循环判断如果压缩后图片是否大于400kb,大于继续压缩（这里可以设置大些）

 baos.reset();//重置baos即清空baos

 image.compress(Bitmap.CompressFormat.JPEG, options, baos);//这里压缩options%，把压缩后的数据存放到baos中

     options -= 10;//每次都减少10

 }

 ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());//把压缩后的数据baos存放到ByteArrayInputStream中

    Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, null);//把ByteArrayInputStream数据生成图片

 return bitmap;

}



/*
  * 把bitmap转化为file
  * */
       public static File bitMap2File(Bitmap bitmap) {


 String path = "";
 if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
 path = Environment.getExternalStorageDirectory() + File.separator;//保存到sd根目录下
 }


 //        File f = new File(path, System.currentTimeMillis() + ".jpg");
 File f = new File(path, "share" + ".jpg");
 if (f.exists()) {
f.delete();
}
try {
 FileOutputStream out = new FileOutputStream(f);
 bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);
 out.flush();
 out.close();
 bitmap.recycle();
 } catch (FileNotFoundException e) {
 e.printStackTrace();
 } catch (IOException e) {
e.printStackTrace();
} finally {
 return f;
 }
}





    //将Bitmap图片保存到sd卡

    protected void saveBitmapToSD(Bitmap bt) {
        File path = Environment.getExternalStorageDirectory();
        File file = new File(path, System.currentTimeMillis() + ".jpg");
        System.out.println(Environment.getExternalStorageState() + "/Cool/" +"000000000000000000000000000");
        try {
            FileOutputStream  out = new FileOutputStream(file);
            bt.compress(Bitmap.CompressFormat.JPEG, 90, out);
            out.flush();
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 保存bitmap到SD卡
     * @param bitName 保存的名字
     * @param mBitmap 图片对像
     * return 生成压缩图片后的图片路径
     */
    public static String saveMyBitmap(String bitName,Bitmap mBitmap) {
        File f = new File("/sdcard/" + bitName + ".png");
        try {
            f.createNewFile();
        } catch (IOException e) {
            System.out.println("在保存图片时出错：" + e.toString());
        }
        FileOutputStream fOut = null;
        try {
            fOut = new FileOutputStream(f);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            mBitmap.compress(Bitmap.CompressFormat.PNG, 100, fOut);
        } catch (Exception e) {
            return "create_bitmap_error";
        }
        try {
            fOut.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            fOut.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "/sdcard/" + bitName + ".png";
    }

    /**
     * 保存bitmap到SD卡
     * @param bitmap
     * @param imagename
     */
    public static String saveBitmapToSDCard(Bitmap bitmap, String imagename) {
        String path = "/sdcard/" + "img-" + imagename + ".jpg";
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(path);
            if (fos != null) {
                bitmap.compress(Bitmap.CompressFormat.JPEG, 90, fos);
                fos.close();
            }

            return path;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }






    /**
     * 保存到内存卡
     *
     * @param bitName
     * @param mBitmap
     */
    public static void saveBitmapForSdCard(Activity context, String bitName, Bitmap mBitmap) {
        String local_path = "/sdcard/" + "img-" + bitName ;
        File appDir = new File(local_path);
        //判断不存在就创建
        if (!appDir.exists()) {
            appDir.mkdir();
        }
        //创建file对象
        File f = new File(local_path + bitName + ".png");
        try {
            //创建
            f.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        FileOutputStream fOut = null;
        try {
            fOut = new FileOutputStream(f);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        //原封不动的保存在内存卡上
        mBitmap.compress(Bitmap.CompressFormat.PNG, 100, fOut);
        try {
            fOut.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            fOut.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 其次把文件插入到系统图库
        try {
            MediaStore.Images.Media.insertImage(context.getContentResolver(),
                    f.getAbsolutePath(), bitName, null);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        // 最后通知图库更新
        String path = Environment.getExternalStorageDirectory().getPath();
        context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.parse("file://" + path)));
        RxToast.showShort("截屏成功，请在相册中查看！");
    }



}
