package com.example.user.accounting;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    EditText id;
    EditText password;
    Button login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_1);

        login = (Button)findViewById(R.id.btnlogin);
        id = (EditText)findViewById(R.id.edtid);
        password = (EditText)findViewById(R.id.edtpwd);



        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //String reid = id.getText().toString();
               // String repwd = password.getText().toString();

                if(TextUtils.isEmpty(id.getText()) || TextUtils.isEmpty(password.getText())) {
                    Toast.makeText(MainActivity.this,"아이디와 비밀번호를 입력하세요",Toast.LENGTH_LONG).show();
                }
                else{
                    Intent login = new Intent(MainActivity.this, AccountingListActivity.class);
                    startActivity(login);
                }
            }
        });

    }



/*
    private Context CONTEXT;
    EditText id;
    EditText password;
    Button btnconfirm;
    TextView info1;
    TextView info2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_2);
        CONTEXT = this; //context 초기화

        id = (EditText)findViewById(R.id.edtid);
        password = (EditText)findViewById(R.id.edtpwd);
        btnconfirm = (Button)findViewById(R.id.btnlogin);
        TextView info1 = (TextView)findViewById(R.id.textinfo1);
        TextView info2 = (TextView)findViewById(R.id.textinfo2);

        btnconfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //info1.setText("ID : " + id.getText());
               // info2.setText("PWD : " + password.getText());
                //Intent intent = new Intent(MainActivity.this, HereActivity.class);
                //intent.putExtra("ID",id.getText().toString());
                //intent.putExtra("PWD",password.getText().toString());
                //startActivity(intent);

                String reid = id.getText().toString();
                String repwd = password.getText().toString();

                if(reid.equals("123")&&repwd.equals("000")) {

                    new httpTask().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, "login_do_get.php?id=123&pwd=000", "");
                }
                else{

                }
            }
        });

    }
    class httpTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... args) {

            String returnValue = "";
            HttpURLConnection conn = null;
            try {
                Log.e("!!!", "args[0] = " + args[0]);
                Log.e("!!!", "args[1] = " + args[1]);
                String urlString = "http://www.matescorp.com/soyu/" + args[0];
                Log.e("!!!", "urlString = " + urlString);
                URL url = new URL(urlString);

                // open connection
                conn = (HttpURLConnection) url.openConnection();
                conn.setDoInput(true);            // 입력스트림 사용여부
                conn.setDoOutput(false);            // 출력스트림 사용여부
                conn.setUseCaches(false);        // 캐시사용 여부
                conn.setReadTimeout(3000);        // 타임아웃 설정 ms단위
//                conn.setRequestMethod("GET");  // or GET
                conn.setRequestMethod("POST");

                // POST 값 전달 하기
                StringBuffer params = new StringBuffer("");
//                params.append("name=" + URLEncoder.encode(name)); //한글일 경우 URL인코딩
                params.append(args[1]);
                PrintWriter output = new PrintWriter(conn.getOutputStream());
                output.print(params.toString());
                output.close();

                // Response받기
                StringBuffer sb = new StringBuffer();
                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

                for (; ; ) {
                    String line = br.readLine();
                    if (line == null) break;
                    sb.append(line + "\n");
                }

                br.close();
                conn.disconnect();
                br = null;
                conn = null;

                returnValue = sb.toString();
            } catch (ConnectException e) {
                e.printStackTrace();
                return "ConnectException|" + args[0] + "|" + args[1];
            } catch (SocketTimeoutException e) {
                e.printStackTrace();
                return "SocketTimeoutException|" + args[0] + "|" + args[1];
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (conn!=null) {
                    conn.disconnect();
                }
            }
            return returnValue;
        }

        @Override
        protected void onPostExecute(String result) {
            result = result.trim();
            Log.e("!!!", "httpTask result = | " + result + " |");
            if (result.trim().equals("") || result.trim().equals("[]") || result.trim().equals("null")) {
                Log.e("!!!", "------");
                return;
            } else {
                try {
                    if(result.equals("success")){

                        Intent intent = new Intent(MainActivity.this, HereActivity.class);
                        intent.putExtra("ID",id.getText().toString());
                        intent.putExtra("PWD",password.getText().toString());
                        startActivity(intent);

                    }
                    Toast.makeText(CONTEXT,result, Toast.LENGTH_SHORT).show();

                } catch ( Exception e ) {
                    e.printStackTrace();
                }
            }
        }
    }

    */
}


