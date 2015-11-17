package com.example.dam.navegadorweb;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MainActivity extends AppCompatActivity {

    private android.widget.Button button;
    private android.widget.TextView textView;
    private android.widget.ScrollView scrollView;
    //private android.widget.EditText editText;
    private String s;
    private android.widget.ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.progressBar = (ProgressBar) findViewById(R.id.progressBar);
        //this.editText = (EditText) findViewById(R.id.editText);
        this.scrollView = (ScrollView) findViewById(R.id.scrollView);
        this.textView = (TextView) findViewById(R.id.textView);
        this.button = (Button) findViewById(R.id.button);
    }

    private void metodo(){
        /*
        Thread t= new Thread(){
            @Override
            public void run(){
                s= leerPagina("http://example.org");
                System.out.println(s);
                textView.post(new Runnable() {
                    @Override
                    public void run() {
                        textView.setText(s);
                    }
                });

            }
        };
        t.start();
        */
        Tarea t= new Tarea(textView, progressBar);
        t.execute(new String[]{"http://example.org"});
    }
    public void descargar(View v){
        metodo();
    }

    public String leerPagina(String s){
        try {
            // TODO add your handling code here:
            URL url= new URL(s);
            BufferedReader in= new BufferedReader(new InputStreamReader(url.openStream()));
            String linea, out="";
            while ((linea = in.readLine()) !=null){
                linea = linea.trim();


                out+=linea+"\n";

            }
            in.close();

            return out;
        } catch (MalformedURLException ex) {
            Logger.getLogger(MainActivity.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(MainActivity.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void setString(String s){
        textView.setText(s);
    }



}
