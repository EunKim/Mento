package com.example.user.accounting;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by user on 2016-08-04.
 */
public class ListActivity extends AppCompatActivity {

    ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_info_2);

        list = (ListView)findViewById(R.id.listView);

        ArrayList<ListInfo> list = new ArrayList<ListInfo>();
        list.add(new ListInfo(1,"1: 김은채"));
        list.add(new ListInfo(2,"2: 201340205"));
        list.add(new ListInfo(3,"3: 010-7188-3106"));

        ArrayAdapter<ListInfo> adapter = new ArrayAdapter<ListInfo>(this,android.R.layout.simple_list_item_1,list);



    }
}
