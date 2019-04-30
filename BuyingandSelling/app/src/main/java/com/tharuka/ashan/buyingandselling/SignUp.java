package com.tharuka.ashan.buyingandselling;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import data.IpAddress;

/**
 * Created by DELL on 20-Feb-18.
 */

public class SignUp extends AppCompatActivity implements View.OnClickListener {
private String locationsname=null;
private String lid=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);

        if (getIntent().getExtras() != null) {
            Bundle bundle = getIntent().getExtras();
            lid = bundle.getSerializable("lid").toString();
            locationsname = bundle.getSerializable("lname").toString();
            TextView t=findViewById(R.id.locationview);
            t.setText(locationsname);

        }


        ImageView view = (ImageView) findViewById(R.id.back);
        Button view1 = (Button) findViewById(R.id.registerbt);
        Button view2 = (Button) findViewById(R.id.locationbtn);

        view.setOnClickListener(this);
        view1.setOnClickListener(this);
        view2.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.back) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        }

        if (v.getId() == R.id.locationbtn) {
            Intent intent = new Intent(this, ViewList.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("val","sign");
            intent.putExtras(bundle);
            startActivity(intent);
            finish();
        }


        if (v.getId() == R.id.registerbt) {
            EditText firstnameText = (EditText) findViewById(R.id.firstname);
            EditText lastnametext = (EditText) findViewById(R.id.lastname);
            EditText phonenumbertext = (EditText) findViewById(R.id.phonenumber);
            EditText emailtext = (EditText) findViewById(R.id.email);
            EditText passwordtext = (EditText) findViewById(R.id.regpassword);



            String firstname = firstnameText.getText().toString();
            String lastnamae = lastnametext.getText().toString();
            String phonenumber = phonenumbertext.getText().toString();
            String email = emailtext.getText().toString();
            String password = passwordtext.getText().toString();
            String location = locationsname;

            if (firstname.isEmpty() || lastnamae.isEmpty() || phonenumber.isEmpty() || email.isEmpty() || password.isEmpty() || location.isEmpty()) {
                Toast.makeText(getApplicationContext(), "Fill All Inputs...", Toast.LENGTH_SHORT).show();
            } else {
                new MyNetWorkCall().execute(firstname, lastnamae, phonenumber, location, email, password);
            }


        }


    }

    class MyNetWorkCall extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... param) {

            try {

                HttpClient httpClient = new DefaultHttpClient();
                HttpPost httpPost = new HttpPost("http://"+ IpAddress.ipaddress+":8080/BuyingSellingSystem/addCustomer");
                List<NameValuePair> list = new ArrayList<>();
                list.add(new BasicNameValuePair("firstName", param[0]));
                list.add(new BasicNameValuePair("lastName", param[1]));
                list.add(new BasicNameValuePair("phoneNumber", param[2]));
                list.add(new BasicNameValuePair("location", param[3]));
                list.add(new BasicNameValuePair("email", param[4]));
                list.add(new BasicNameValuePair("password", param[5]));


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

            Toast.makeText(getApplicationContext(), "Account create : "+response, Toast.LENGTH_SHORT).show();

        }
    }

}
