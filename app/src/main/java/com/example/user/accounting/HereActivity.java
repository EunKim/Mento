package com.example.user.accounting;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by user on 2016-07-07.
 */
public class HereActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result);

        Intent intent = getIntent();
        String id = intent.getStringExtra("ID");
        String password = intent.getStringExtra("PWD");

        final TextView info1 = (TextView)findViewById(R.id.textinfo1);
        final TextView info2 = (TextView)findViewById(R.id.textinfo2);

        info1.setText("ID : " + id);
        info2.setText("PWD : " + password);

    }
}
