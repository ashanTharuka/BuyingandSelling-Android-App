package com.tharuka.ashan.buyingandselling;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import data.IpAddress;
import data.LocationDTO;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView view = (TextView) findViewById(R.id.signupbtn);
        TextView view1 = (TextView) findViewById(R.id.mailbtn);
        TextView view2 = (TextView) findViewById(R.id.loginbtn);

        view.setOnClickListener(this);
        view1.setOnClickListener(this);
        view2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.loginbtn) {
            EditText emailText = (EditText) findViewById(R.id.emailText);
            String email = emailText.getText().toString();
            EditText passwordText = (EditText) findViewById(R.id.passwordText);
            String password = passwordText.getText().toString();

            if (password.isEmpty() || email.isEmpty()) {
                Toast.makeText(getApplicationContext(), "Fill All Inputs...", Toast.LENGTH_SHORT).show();
            } else {
                new MyNetWorkCall().execute(email, password);
            }

        }

        if (v.getId() == R.id.signupbtn) {
            Intent intent = new Intent(this, SignUp.class);
            startActivity(intent);
        }
        if (v.getId() == R.id.mailbtn) {
            Intent intent = new Intent(this, Email.class);
            startActivity(intent);
        }

    }
//    private void startService() {

    class MyNetWorkCall extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... param) {

            try {

                HttpClient httpClient = new DefaultHttpClient();
                HttpPost httpPost = new HttpPost("http://"+ IpAddress.ipaddress+":8080/BuyingSellingSystem/checkUser");
                List<NameValuePair> list = new ArrayList<>();

                list.add(new BasicNameValuePair("email", param[0]));
                list.add(new BasicNameValuePair("password", param[1]));


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
            String ar[] = null;
            Gson gson = new Gson();
            Type listType = new TypeToken<String[]>() {
            }.getType();
            ar = gson.fromJson(response, listType);

            if (ar[1].equalsIgnoreCase("admin")) {

                Intent intent = new Intent(MainActivity.this, AdminView.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("id", ar[0]);
                intent.putExtras(bundle);
                startActivity(intent);

            } else if(ar[1].equalsIgnoreCase("customer")){
                Intent intent = new Intent(MainActivity.this, CustomerView.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("id", ar[0]);
                intent.putExtras(bundle);
                startActivity(intent);
            }else{
                Toast.makeText(getApplicationContext(), "Oops !!! : ", Toast.LENGTH_SHORT).show();
            }

        }
    }



}
