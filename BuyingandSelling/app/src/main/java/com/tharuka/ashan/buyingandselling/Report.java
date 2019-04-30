package com.tharuka.ashan.buyingandselling;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.w3c.dom.Text;

import java.io.IOException;

import data.IpAddress;

/**
 * Created by DELL on 20-Feb-18.
 */

public class Report extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.report);

        startServicecus();
        startServicesell();
        startServicebuy();
    }

    @Override
    public void onClick(View v) {

    }

    private void startServicecus() {

        class MyAsync extends AsyncTask<String, String, String> {
            ProgressDialog progress = new ProgressDialog(Report.this);

            public MyAsync() {
                progress.setTitle("Server Call");
                progress.setMessage("Connecting.. Please Wait! ");
                progress.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                progress.setIndeterminate(true);
                progress.show();
            }

            @Override
            protected String doInBackground(String... voids) {
                try {

                    HttpClient client = new DefaultHttpClient();
                    HttpGet httpget = new HttpGet("http://"+ IpAddress.ipaddress+":8080/BuyingSellingSystem/getCustomerCount");

                    ResponseHandler<String> responseHandler = new BasicResponseHandler();

                    String responce = client.execute(httpget, responseHandler);
                    return responce;
                } catch (IOException e) {
                    e.printStackTrace();

                }
                return null;
            }

            @Override
            protected void onPostExecute(String responce) {
                super.onPostExecute(responce);
                progress.dismiss();
                TextView v1=findViewById(R.id.customercounttext);
                v1.setText(responce);

            }
        }
        new MyAsync().execute();

    }

    private void startServicesell() {

        class MyAsync extends AsyncTask<String, String, String> {
            ProgressDialog progress = new ProgressDialog(Report.this);

            public MyAsync() {
                progress.setTitle("Server Call");
                progress.setMessage("Connecting.. Please Wait! ");
                progress.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                progress.setIndeterminate(true);
                progress.show();
            }

            @Override
            protected String doInBackground(String... voids) {
                try {

                    HttpClient client = new DefaultHttpClient();
                    HttpGet httpget = new HttpGet("http://"+ IpAddress.ipaddress+":8080/BuyingSellingSystem/getSellCount");

                    ResponseHandler<String> responseHandler = new BasicResponseHandler();

                    String responce = client.execute(httpget, responseHandler);
                    return responce;
                } catch (IOException e) {
                    e.printStackTrace();

                }
                return null;
            }

            @Override
            protected void onPostExecute(String responce) {
                super.onPostExecute(responce);
                progress.dismiss();
                TextView v1=findViewById(R.id.sellingcounttext);
                v1.setText(responce);
            }
        }
        new MyAsync().execute();

    }

    private void startServicebuy() {

        class MyAsync extends AsyncTask<String, String, String> {
            ProgressDialog progress = new ProgressDialog(Report.this);

            public MyAsync() {
                progress.setTitle("Server Call");
                progress.setMessage("Connecting.. Please Wait! ");
                progress.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                progress.setIndeterminate(true);
                progress.show();
            }

            @Override
            protected String doInBackground(String... voids) {
                try {

                    HttpClient client = new DefaultHttpClient();
                    HttpGet httpget = new HttpGet("http://"+ IpAddress.ipaddress+":8080/BuyingSellingSystem/getBuyCount");

                    ResponseHandler<String> responseHandler = new BasicResponseHandler();

                    String responce = client.execute(httpget, responseHandler);
                    return responce;
                } catch (IOException e) {
                    e.printStackTrace();

                }
                return null;
            }

            @Override
            protected void onPostExecute(String responce) {
                super.onPostExecute(responce);
                progress.dismiss();
                TextView v1=findViewById(R.id.buyingcounttext);
                v1.setText(responce);
            }
        }
        new MyAsync().execute();

    }
}