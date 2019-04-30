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

public class Buy extends AppCompatActivity implements View.OnClickListener {

    private static String main ;
    String id;
    static String itemmid ;
    private static String sellId;
    private String modelName;
    static String brand;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.buy);
        id = CustomerView.id;

        if (getIntent().getExtras() != null) {
            Bundle bundle = getIntent().getExtras();
            try {
                if (bundle.getSerializable("main").toString() != null) {
                    main = bundle.getSerializable("main").toString();
                    TextView t = findViewById(R.id.typetext);
                    t.setText(main);
                }

            } catch (Exception ex) {
            }
            try {

                if (bundle.getSerializable("brand").toString() != null) {

                    itemmid = bundle.getSerializable("iid").toString();
                    brand = bundle.getSerializable("brand").toString();
                    TextView t = findViewById(R.id.brandtext);
                    t.setText(brand);
                    TextView tt = findViewById(R.id.typetext);
                    tt.setText(main);
                }

            } catch (Exception ex) {
            } try {

                if (bundle.getSerializable("modelName").toString() != null) {
                    TextView tr = findViewById(R.id.brandtext);
                    tr.setText(brand);
                    TextView ttr = findViewById(R.id.typetext);
                    ttr.setText(main);
                    sellId = bundle.getSerializable("sellId").toString();
                    modelName = bundle.getSerializable("modelName").toString();
                    String price = bundle.getSerializable("price").toString();
                    TextView t = findViewById(R.id.brandtext);
                    TextView p= findViewById(R.id.pricetext);
                    p.setText(price);
                    t.setText(brand);
                    TextView tt = findViewById(R.id.modeltext);
                    tt.setText(modelName);
                }

            } catch (Exception ex) {
            }


        }
        Button view1 = findViewById(R.id.typebtn);
        view1.setOnClickListener(this);
        Button view2 = findViewById(R.id.brandbtn);
        view2.setOnClickListener(this);
        Button view3 = findViewById(R.id.modelbtn);
        view3.setOnClickListener(this);
        Button view4 = findViewById(R.id.buyyyybtn);
        view4.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.typebtn) {
            Intent intent = new Intent(this, MainCategoryList.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("val", "buy");
            intent.putExtras(bundle);
            startActivity(intent);
            finish();
        }
        if (v.getId() == R.id.brandbtn) {
            Intent intent = new Intent(this, BrandList.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("val", "buy");
            bundle.putSerializable("main", main);
            intent.putExtras(bundle);
            startActivity(intent);
            finish();
        }
        if (v.getId() == R.id.modelbtn) {

            Intent intent = new Intent(this, ModelList.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("main", brand);
            intent.putExtras(bundle);
            startActivity(intent);
            finish();
        }
        if (v.getId() == R.id.buyyyybtn) {

            new Add().execute(CustomerView.id,sellId);
        }

    }

    class Add extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... param) {

            try {

                HttpClient httpClient = new DefaultHttpClient();
                HttpPost httpPost = new HttpPost("http://" + IpAddress.ipaddress + ":8080/BuyingSellingSystem/addBuy");
                List<NameValuePair> list = new ArrayList<>();

                list.add(new BasicNameValuePair("customerId", param[0]));
                list.add(new BasicNameValuePair("itemId", param[1]));



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
            TextView price = findViewById(R.id.pricetext);
            price.setText("Rs : 0000");
            Toast.makeText(getApplicationContext(), "Item is Yours Now :" + response, Toast.LENGTH_SHORT).show();


        }
    }
}