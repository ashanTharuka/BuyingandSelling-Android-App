package com.tharuka.ashan.buyingandselling;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import data.IpAddress;

/**
 * Created by DELL on 20-Feb-18.
 */

public class Email extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.email);
        ImageView view = (ImageView) findViewById(R.id.emailback);
        Button b1 = (Button) findViewById(R.id.button3);

        view.setOnClickListener(this);
        b1.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.emailback) {
            Intent intent = new Intent(this,MainActivity.class);
            startActivity(intent);
        }
        finish();
        if (v.getId() == R.id.button3) {
            EditText emailText = (EditText) findViewById(R.id.femailtext);
            String email = emailText.getText().toString();
            new MyNetWorkCall().execute(email);
        }
        finish();
    }



    class MyNetWorkCall extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... param) {

            try {

                HttpClient httpClient = new DefaultHttpClient();
                HttpPost httpPost = new HttpPost("http://"+ IpAddress.ipaddress+":8080/BuyingSellingSystem/sendEMail");
                List<NameValuePair> list = new ArrayList<>();
                list.add(new BasicNameValuePair("email", param[0]));



                httpPost.setEntity(new UrlEncodedFormEntity(list));
                ResponseHandler<String> responseHandler = new BasicResponseHandler();
                String response = httpClient.execute(httpPost, responseHandler);

                return response;

            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(String response) {
            super.onPostExecute(response);
            Intent intent = new Intent(Email.this, MainActivity.class);
            startActivity(intent);





        }
    }



}