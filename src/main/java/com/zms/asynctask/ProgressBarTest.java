package com.zms.asynctask;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ProgressBar;

/**
 * Created by Administrator on 2015/4/28.
 */
public class ProgressBarTest extends Activity {
    private ProgressBar progressBar;
    private MyAsyncTask myAsyncTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.progressbar_test);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        myAsyncTask = new MyAsyncTask();
        myAsyncTask.execute();
    }

    class MyAsyncTask extends AsyncTask<Void, Integer, Void> {
        @Override
        protected Void doInBackground(Void... params) {

            for (int i = 0; i < 100; i++) {
                if (isCancelled())
                    break;
                publishProgress(i);
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            if (isCancelled())
                return;
            super.onProgressUpdate(values);
            progressBar.setProgress(values[0]);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (myAsyncTask != null && myAsyncTask.getStatus() == AsyncTask.Status.RUNNING) {
            myAsyncTask.cancel(true); // cancel方法只是将对应的AsyncTask标记为cancel状态，并非真正的取消线程执行
        }
    }
}
