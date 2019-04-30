package com.tharuka.ashan.buyingandselling;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by DELL on 21-Feb-18.
 */

public class Manage  extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manage);


        Button view1=(Button)findViewById(R.id.managecustomerbtn);
        Button view2=(Button)findViewById(R.id.managelocationbtn);

        view1.setOnClickListener(this);
        view2.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {

        if (v.getId()==R.id.managecustomerbtn){
            Intent intent = new Intent(this, ManageUser.class);
            startActivity(intent);
        }
        if (v.getId()==R.id.managelocationbtn){
            Intent intent = new Intent(this, Location.class);
            startActivity(intent);
        }

    }
}