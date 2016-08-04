package com.example.user.accounting;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
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

/**
 * Created by user on 2016-07-21.
 */
public class NewCustomerActivity extends AppCompatActivity {
    private Context CONTEXT;
    EditText newid;
    Button confirmbtn;
    EditText newpwd;
    EditText confirmpwd;
    Button newcustomer;
    TextView avtxtid;
    TextView avtxtpwd;
    int count=0;

    String insertid;
    String insertpwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_info_2);

        newid = (EditText)findViewById(R.id.newedtid);
        newpwd = (EditText)findViewById(R.id.newedtpwd);
        confirmpwd =(EditText)findViewById(R.id.confirmedtpwd);

        confirmbtn = (Button)findViewById(R.id.confirmid);
        newcustomer = (Button)findViewById(R.id.newcustomer);

        avtxtid = (TextView)findViewById(R.id.avid);
        avtxtpwd = (TextView)findViewById(R.id.avpwd);



        confirmbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String coid = newid.getText().toString();

                if(coid.equals("")){
                    Toast.makeText(NewCustomerActivity.this, "아이디를 입력해주세요", Toast.LENGTH_SHORT).show();

                }
                else{
                    new httpTask().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, "check_id_get.php?id=" + coid, "");
                }

            }
        });

        confirmpwd.addTextChangedListener(new TextWatcher() {
              @Override
              public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
              }

              @Override
              public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                  if(newpwd.getText().toString().equals(confirmpwd.getText().toString())){
                      avtxtpwd.setText("비밀번호가 일치합니다.");
                      avtxtpwd.setTextColor(Color.BLUE);
                      insertpwd = "true";

                  }
                  else{
                      avtxtpwd.setText("비밀번호가 일치하지 않습니다.");
                      avtxtpwd.setTextColor(Color.RED);
                      insertpwd = "false";
                  }
              }

              @Override
              public void afterTextChanged(Editable editable) {

              }
          });

        newcustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String coid = newid.getText().toString();
                String pwpw = newpwd.getText().toString();

                if(insertid.equals("true")&&insertpwd.equals("true")) {
                    new httpTask().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, "user_signup_get.php?id="+coid+"&pwd="+pwpw, "");
                    Toast.makeText(NewCustomerActivity.this, "회원가입 성공", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(NewCustomerActivity.this, MainActivity.class);
                    startActivity(intent);
                }
                else if(insertid.equals("false") && insertpwd.equals("true")){
                    Toast.makeText(NewCustomerActivity.this, "아이디  중복확인을 확인하세요", Toast.LENGTH_SHORT).show();
                }
                else if(insertid.equals("true") && insertpwd.equals("flase")){
                    Toast.makeText(NewCustomerActivity.this, "비밀번호를 확인하세요", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(NewCustomerActivity.this, "회원가입 실패", Toast.LENGTH_SHORT).show();
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
                        avtxtid.setText("사용불가능한 아이디입니다.");
                        avtxtid.setTextColor(Color.RED);
                        insertid = "false";
                    }
                    else {
                        //Toast.makeText(CONTEXT,result, Toast.LENGTH_SHORT).show();
                        avtxtid.setText("사용가능한 아이디입니다.");
                        avtxtid.setTextColor(Color.BLUE);
                        insertid = "true";
                    }
                } catch ( Exception e ) {
                    e.printStackTrace();
                }
            }
        }
    }
}
