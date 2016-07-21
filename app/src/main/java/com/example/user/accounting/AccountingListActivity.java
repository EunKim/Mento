package com.example.user.accounting;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

/**
 * Created by user on 2016-07-21.
 */
public class AccountingListActivity extends AppCompatActivity {

    ImageButton plus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.accounting_1);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolBar);
        toolbar.setTitle(R.string.cost_management);
        toolbar.setTitleTextColor(Color.WHITE);

        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        plus = (ImageButton) findViewById(R.id.btnplus);

        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent plus = new Intent(AccountingListActivity.this, CostRequestActivity.class);
                startActivity(plus);

            }
        });

    }
        public boolean onOptionsItemSelected(MenuItem menuItem) {
            switch (menuItem.getItemId()) {
                case android.R.id.home:
                    finish();
            }
            return (super.onOptionsItemSelected(menuItem));
        }

    }

