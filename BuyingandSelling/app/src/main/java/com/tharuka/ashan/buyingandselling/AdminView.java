package com.tharuka.ashan.buyingandselling;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by DELL on 20-Feb-18.
 */

public class AdminView extends AppCompatActivity implements View.OnClickListener {
    private static String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_view);

        View view = findViewById(R.id.itembbn);
        View view1 = findViewById(R.id.reportbnn);
        View view2 = findViewById(R.id.adminpro);
        View view3 = findViewById(R.id.managebnn);
        View view4 = findViewById(R.id.logoutbtn);
        view.setOnClickListener(this);
        view1.setOnClickListener(this);
        view2.setOnClickListener(this);
        view3.setOnClickListener(this);
        view4.setOnClickListener(this);


        if (getIntent().getExtras() != null) {
            Bundle bundle = getIntent().getExtras();
            id = bundle.getSerializable("id").toString();
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.itembbn) {
            Intent intent = new Intent(this, Item.class);
            startActivity(intent);
        }
        if (v.getId() == R.id.logoutbtn) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
        if (v.getId() == R.id.reportbnn) {
            Intent intent = new Intent(this, Report.class);
            startActivity(intent);
        }
        if (v.getId() == R.id.adminpro) {
            Intent intent = new Intent(this, AdminProfile.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("id", id);
            intent.putExtras(bundle);
            startActivity(intent);
        }
        if (v.getId() == R.id.managebnn) {
            Intent intent = new Intent(this, Manage.class);
            startActivity(intent);
        }
    }
}