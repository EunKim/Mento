package com.example.user.accounting;

import android.content.Intent;
import android.opengl.EGLDisplay;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        final EditText id = (EditText)findViewById(R.id.edtid);
        final EditText password = (EditText)findViewById(R.id.edtpwd);
        Button btnconfirm = (Button)findViewById(R.id.btnlogin);
        //final TextView info1 = (TextView)findViewById(R.id.textinfo1);
        //final TextView info2 = (TextView)findViewById(R.id.textinfo2);

        btnconfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //info1.setText("ID : " + id.getText());
               // info2.setText("PWD : " + password.getText());
               // Intent intent = new Intent(this, HereActivity.class);
            }
        });



    }

}
