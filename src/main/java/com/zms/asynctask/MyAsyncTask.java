package com.zms.asynctask;

import android.os.AsyncTask;

/**
 * Created by Administrator on 2015/4/28.
 * <p/>
 * 1.必须在UI线程中创建AsyncTask的实例
 * 2.必须在UI线程中调用AsyncTask的execute()方法
 * 3.重写的4个方法是系统自动调用的，不应也不能手动调用
 * 4.每个AsyncTask只能同时执行一次，多次
 */
public class MyAsyncTask extends AsyncTask<Void, Void, Void> { // <Params, Progress, Result>


    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    /**
     * 只有doInBackground方法运行在其他线程中，其他3个方法运行在UI主线程，可以更新UI
     *
     * @param params
     * @return
     */
    @Override
    protected Void doInBackground(Void... params) {

        return null;
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
    }
}
