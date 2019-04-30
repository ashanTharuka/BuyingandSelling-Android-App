package com.tharuka.ashan.buyingandselling;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import data.CustomerDTO;

/**
 * Created by DELL on 20-Feb-18.
 */

public class ManageUser extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manageuser);

        if (getIntent().getExtras() != null) {
            Bundle bundle = getIntent().getExtras();
            String f = bundle.getString("f");
            String i = bundle.getString("i");
            String l = bundle.getString("l");
            String p = bundle.getString("p");

            EditText id = findViewById(R.id.xuseridtext);
            EditText fname = findViewById(R.id.xfirstnametext);
            EditText lname = findViewById(R.id.xlastnametext);
            EditText number = findViewById(R.id.xphonenumbertext);
            id.setText(i);
            fname.setText(f);
            lname.setText(l);
            number.setText(p);

        }


        Button view1 = (Button) findViewById(R.id.mviewallcus);
        view1.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.mviewallcus) {
            Intent intent = new Intent(ManageUser.this, CustomerList.class);
            startActivity(intent);
            finish();
        }
    }
}