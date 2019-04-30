package com.tharuka.ashan.buyingandselling;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

import data.AdminDTO;
import data.IpAddress;

/**
 * Created by DELL on 20-Feb-18.
 */

public class AdminProfile extends AppCompatActivity implements View.OnClickListener {
    private static String adminId;

    AdminDTO admin = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.adminprofile);


        if (getIntent().getExtras() != null) {
            Bundle bundle = getIntent().getExtras();
            adminId = bundle.getSerializable("id").toString();
            new MyNetWorkCall().execute(adminId);
        }

        Button btn = (Button) findViewById(R.id.aupdateadminbtn);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.aupdateadminbtn) {
            EditText oldP = findViewById(R.id.aregpassword);
            EditText newP = findViewById(R.id.anewpassword);
            EditText firstnametext = findViewById(R.id.afirstnametext);
            EditText femailtext = findViewById(R.id.afemailtext);
            if (admin.getPassword().equalsIgnoreCase(oldP.getText().toString())) {
                String newPass = newP.getText().toString();
                String name = firstnametext.getText().toString();
                String email = femailtext.getText().toString();
                new MyNetWorkCall2().execute(admin.getAdminId(), name, email, newPass);
            } else {
                Toast.makeText(getApplicationContext(), "Wrong Old Password !!! : ", Toast.LENGTH_SHORT).show();
            }

        }
    }

    class MyNetWorkCall extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... param) {

            try {

                HttpClient httpClient = new DefaultHttpClient();
                HttpPost httpPost = new HttpPost("http://"+ IpAddress.ipaddress +":8080/BuyingSellingSystem/seachAdminById");
                List<NameValuePair> list = new ArrayList<>();

                list.add(new BasicNameValuePair("id", param[0]));


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
            Gson gson = new Gson();
            Type listType = new TypeToken<AdminDTO>() {
            }.getType();
            admin = gson.fromJson(response, listType);

            EditText id = findViewById(R.id.auseridtext);
            EditText firstnametext = findViewById(R.id.afirstnametext);
            EditText femailtext = findViewById(R.id.afemailtext);

            id.setText(admin.getAdminId());
            firstnametext.setText(admin.getAdminName());
            femailtext.setText(admin.getEmail());


        }
    }

    class MyNetWorkCall2 extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... param) {

            try {

                HttpClient httpClient = new DefaultHttpClient();
                HttpPost httpPost = new HttpPost("http://" + IpAddress.ipaddress+ ":8080/BuyingSellingSystem/updateAdmin");
                List<NameValuePair> list = new ArrayList<>();

                list.add(new BasicNameValuePair("id", param[0]));
                list.add(new BasicNameValuePair("name", param[1]));
                list.add(new BasicNameValuePair("email", param[2]));
                list.add(new BasicNameValuePair("password", param[3]));


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
            Gson gson = new Gson();
            Type listType = new TypeToken<AdminDTO>() {
            }.getType();
            AdminDTO ad = gson.fromJson(response, listType);

            EditText id = findViewById(R.id.auseridtext);
            EditText firstnametext = findViewById(R.id.afirstnametext);
            EditText femailtext = findViewById(R.id.afemailtext);

            id.setText(ad.getAdminId());
            firstnametext.setText(ad.getAdminName());
            femailtext.setText(ad.getEmail());
            Toast.makeText(getApplicationContext(), "Profile Updated ", Toast.LENGTH_SHORT).show();


        }
    }
}