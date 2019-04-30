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

import data.AdminDTO;
import data.CustomerDTO;
import data.IpAddress;

/**
 * Created by DELL on 20-Feb-18.
 */

public class CustomerProfile extends AppCompatActivity implements View.OnClickListener {
    private CustomerDTO customer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.customerprofile);


        if (getIntent().getExtras() != null) {
            Bundle bundle = getIntent().getExtras();
            String id = bundle.getSerializable("id").toString();
//
            new MyNetWorkCall().execute(id);
        }



        Button view = findViewById(R.id.pptyupdatebtn);
        View view1 = findViewById(R.id.customerprofileback);
        view.setOnClickListener(this);
        view1.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {


        if (v.getId() == R.id.customerprofileback) {
            Intent intent = new Intent(this, CustomerView.class);
            startActivity(intent);
        }
        if (v.getId() == R.id.pptyupdatebtn) {

            EditText oldP = findViewById(R.id.ppregpassword);
            EditText newP = findViewById(R.id.ppnewpassword);
            EditText id = findViewById(R.id.ppuseridtext);
            EditText firstname = findViewById(R.id.ppfirstnametext);
            EditText lastname = findViewById(R.id.pplastnametext);
            EditText number = findViewById(R.id.ppphonenumbertext);
            EditText email = findViewById(R.id.ppfemailtext);



            if (customer.getPassword().equalsIgnoreCase(oldP.getText().toString())) {
               new MyNetWorkCall2().execute(""+customer.getCustomerId(), firstname.getText().toString(), lastname.getText().toString(), number.getText().toString(),customer.getLocation(),email.getText().toString(),newP.getText().toString());
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
                HttpPost httpPost = new HttpPost("http://"+ IpAddress.ipaddress +":8080/BuyingSellingSystem/seachCustomerById");
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
            Type listType = new TypeToken<CustomerDTO>() {
            }.getType();
             customer = gson.fromJson(response, listType);

//
            EditText id = findViewById(R.id.ppuseridtext);
            EditText firstname = findViewById(R.id.ppfirstnametext);
            EditText lastname = findViewById(R.id.pplastnametext);
            EditText number = findViewById(R.id.ppphonenumbertext);
            EditText email = findViewById(R.id.ppfemailtext);

            id.setText(""+customer.getCustomerId());
            firstname.setText(customer.getFirstName());
            lastname.setText(customer.getLastName());
            number.setText(""+customer.getPhoneNumber());
            email.setText(customer.getEmail());


        }
    }

//
    class MyNetWorkCall2 extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... param) {

            try {

                HttpClient httpClient = new DefaultHttpClient();
                HttpPost httpPost = new HttpPost("http://" + IpAddress.ipaddress + ":8080/BuyingSellingSystem/updateCustomer");
                List<NameValuePair> list = new ArrayList<>();

                list.add(new BasicNameValuePair("id", param[0]));
                list.add(new BasicNameValuePair("fname", param[1]));
                list.add(new BasicNameValuePair("lname", param[2]));
                list.add(new BasicNameValuePair("number", param[3]));
                list.add(new BasicNameValuePair("location", param[4]));
                list.add(new BasicNameValuePair("email", param[5]));
                list.add(new BasicNameValuePair("password", param[6]));


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
            Type listType = new TypeToken<CustomerDTO>() {
            }.getType();
             customer = gson.fromJson(response, listType);

            EditText id = findViewById(R.id.ppuseridtext);
            EditText firstname = findViewById(R.id.ppfirstnametext);
            EditText lastname = findViewById(R.id.pplastnametext);
            EditText number = findViewById(R.id.ppphonenumbertext);
            EditText email = findViewById(R.id.ppfemailtext);

            id.setText(""+customer.getCustomerId());
            firstname.setText(customer.getFirstName());
            lastname.setText(customer.getLastName());
            number.setText(""+customer.getPhoneNumber());
            email.setText(customer.getEmail());
            Toast.makeText(getApplicationContext(), "Profile Updated ", Toast.LENGTH_SHORT).show();


        }
    }
}