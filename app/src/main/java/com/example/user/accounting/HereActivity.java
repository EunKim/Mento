package com.example.user.accounting;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by user on 2016-07-07.
 */
public class HereActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result);

        final EditText id = (EditText) findViewById(R.id.edtid);
        final EditText password = (EditText) findViewById(R.id.edtpwd);
        Button btnconfirm = (Button) findViewById(R.id.btnlogin);
        //final TextView info1 = (TextView)findViewById(R.id.textinfo1);
        //final TextView info2 = (TextView)findViewById(R.id.textinfo2);

    }
}
