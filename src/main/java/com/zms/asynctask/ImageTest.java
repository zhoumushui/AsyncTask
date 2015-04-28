package com.zms.asynctask;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by Administrator on 2015/4/28.
 */
public class ImageTest extends Activity {
    private ImageView imageView;
    private ProgressBar progressBar;
    private static String imageUrl = "http://avatar.csdn.net/A/6/4/1_zhoumushui.jpg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.image_test);

        imageView = (ImageView) findViewById(R.id.imageView);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        new MyAsyncTask().execute(imageUrl);
    }

    class MyAsyncTask extends AsyncTask<String, Void, Bitmap> {
        @Override
        protected void onPreExecute() { // 执行前的初始化操作
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected Bitmap doInBackground(String... params) { // 处理耗时操作
            String url = params[0]; // 获取传递进来的URL参数
            Bitmap bitmap = null;
            URLConnection connection;
            InputStream inputStream;
            try {
                connection = new URL(url).openConnection();
                inputStream = connection.getInputStream();
                BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
                bitmap = BitmapFactory.decodeStream(bufferedInputStream); // 通过decodeStream解析输入流

                inputStream.close();
                bufferedInputStream.close();
                Thread.sleep(3000);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return bitmap;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) { // 传入的Bitmap是doInBackground返回的Bitmap
            super.onPostExecute(bitmap);
            progressBar.setVisibility(View.GONE);
            imageView.setImageBitmap(bitmap);
        }
    }
}
