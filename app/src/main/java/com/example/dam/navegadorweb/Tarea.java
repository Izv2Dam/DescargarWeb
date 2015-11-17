package com.example.dam.navegadorweb;

import android.os.AsyncTask;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by 2dam on 16/11/2015.
 */
public class Tarea extends AsyncTask<String, Integer, String> {

    private TextView tv;
    private ProgressBar pb;
    public Tarea(TextView tv, ProgressBar pb) {
        this.tv=tv;
        this.pb= pb;
    }

    @Override
    protected String doInBackground(String... params) {

        URL url = null;
        try {
            url = new URL(params[0]);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        BufferedReader in = null;
        try {
            in = new BufferedReader(new InputStreamReader(url.openStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        String linea, out = "";
        try {
            while ((linea = in.readLine()) != null) {
                linea = linea.trim();


                out += linea + "\n";

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


        for(int i=1; i<=100; i++){
            if(i%1==0){
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                publishProgress(i);

            }
        }
        return out;

    }

    @Override
    protected void onPostExecute(String result) {
        tv.setText(result);
        System.out.println("a");
    }

    @Override
    protected void onPreExecute() {
        pb.setMax(100);
        pb.setProgress(0);
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        pb.setProgress(pb.getProgress()+1);
    }
}
