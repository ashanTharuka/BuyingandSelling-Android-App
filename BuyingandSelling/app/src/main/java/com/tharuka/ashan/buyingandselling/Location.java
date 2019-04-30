package com.tharuka.ashan.buyingandselling;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
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
import data.ItemDTO;
import data.LocationDTO;

/**
 * Created by DELL on 22-Feb-18.
 */

public class Location extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.location);

        if (getIntent().getExtras() != null) {
            Bundle bundle = getIntent().getExtras();
            String lname = bundle.getSerializable("lname").toString();
            String lid = bundle.getSerializable("lid").toString();
            EditText t = findViewById(R.id.locid);
            EditText tt = findViewById(R.id.locname);
            t.setText(lid);
            tt.setText(lname);

        }

        Button view1 = findViewById(R.id.locviewallbtn);
        view1.setOnClickListener(this);
        Button view2 = findViewById(R.id.locaddbtn);
        view2.setOnClickListener(this);
        Button view3 = findViewById(R.id.locupdatebtn);
        view3.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.locviewallbtn) {
            Intent intent = new Intent(this, ViewList.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("val", "loc");
            intent.putExtras(bundle);
            startActivity(intent);
        }
        if (v.getId() == R.id.locaddbtn) {
           EditText name=findViewById(R.id.locname);
           new Add().execute(name.getText().toString());
        }
        if (v.getId() == R.id.locupdatebtn) {
           EditText id=findViewById(R.id.locid);
           EditText name=findViewById(R.id.locname);

           new Update().execute(id.getText().toString(),name.getText().toString());
        }
    }

    class Add extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... param) {

            try {

                HttpClient httpClient = new DefaultHttpClient();
                HttpPost httpPost = new HttpPost("http://" + IpAddress.ipaddress + ":8080/BuyingSellingSystem/addLocation");
                List<NameValuePair> list = new ArrayList<>();

                list.add(new BasicNameValuePair("locationName", param[0]));


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

            Toast.makeText(getApplicationContext(), response, Toast.LENGTH_SHORT).show();


        }
    }

    class Update extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... param) {

            try {

                HttpClient httpClient = new DefaultHttpClient();
                HttpPost httpPost = new HttpPost("http://" + IpAddress.ipaddress + ":8080/BuyingSellingSystem/updateLocation");
                List<NameValuePair> list = new ArrayList<>();

                list.add(new BasicNameValuePair("id", param[0]));
                list.add(new BasicNameValuePair("locationName", param[1]));


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

          ;
            Toast.makeText(getApplicationContext(), "Item Update "+response, Toast.LENGTH_SHORT).show();


        }
    }

}