package com.example.user.accounting;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;

/**
 * Created by user on 2016-07-21.
 */
public class CostRequestActivity extends AppCompatActivity implements onNetworkResponseListener {

    Spinner spinner;
    AccountTitleSpinnerList spinnerList;

    private TextView mDateShow;
    private int mYear;
    private int mMonth;
    private int mDay;

    static final int DATE_DIALOG_ID=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StrictMode.enableDefaults();
        setContentView(R.layout.costrequest_1);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolBar);
        toolbar.setTitle(R.string.cost_request);
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        //계정코드조회하기
        getAccountList();

        mDateShow = (TextView)findViewById(R.id.edtwhen);
        mDateShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(DATE_DIALOG_ID);
            }
        });
        final Calendar calendar = Calendar.getInstance();
        mYear = calendar.get(Calendar.YEAR);
        mMonth = calendar.get(Calendar.MONTH);
        mDay = calendar.get(Calendar.DAY_OF_MONTH);

        updateDisplay();



        //ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, spinnerList);
        //spinner1.setAdapter(adapter);


    }



    private  void updateDisplay() {
        mDateShow.setText(new StringBuilder()
                .append(mYear).append("-")
                .append(mMonth + 1).append("-")
                .append(mDay));
    }

    private DatePickerDialog.OnDateSetListener mDateSetListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            mYear = year;
            mMonth = monthOfYear;
            mDay = dayOfMonth;
            updateDisplay();
        }
    };

    @Override
    protected Dialog onCreateDialog(int id)
    {
        switch(id)
        {
            case DATE_DIALOG_ID:
                return new DatePickerDialog(this, mDateSetListener, mYear, mMonth, mDay);
        }
        return null;
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case android.R.id.home:
                finish();
        }
        return (super.onOptionsItemSelected(menuItem));
    }



    public void getAccountList() {

        JSONObject req_data = new JSONObject();
        try {
            req_data.put("USER_10","test_user1");


            CommNetwork commNetwork = new CommNetwork(this, this);
            commNetwork.requestToServer("ACCOUNT_L001", req_data);

        } catch (Exception e) { //JSONException 은 json 오류만 잡아냄 그래서 최상위 exception으로 처리
            //e.printStackTrace(); //로그로 오류로 알려줘서 오류찾기가 쉽지않음(로그를 확인해야함)
            ErrorUtil.AlertException(this, "오류가 발생하였습니다", e);
        }
    }

    @Override
    public void onSuccess(String api_key, JSONObject response) {
        //성공시
        Toast.makeText(this,"요청이 성공하였습니다.",Toast.LENGTH_SHORT).show();

        JSONArray array = null;
        try {
            array = response.getJSONArray("REC");

            spinnerList = new AccountTitleSpinnerList(array);

            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, spinnerList.getArrayList());
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner = (Spinner) findViewById(R.id.spinner);
            spinner.setAdapter(adapter);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onFailure(String api_key, String error_cd, String error_msg) {
        //실패시시
        Toast.makeText(this,"요청이 실패하였습니다.",Toast.LENGTH_SHORT).show();
    }
}

