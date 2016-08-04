package com.example.user.accounting;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by user on 2016-08-04.
 */
public class ListAdapter extends ArrayAdapter<ListInfo> {
    private List<ListInfo> items;
    private LayoutInflater inflater;
    public ListAdapter(
            Context context, int textViewResourceId,
            List<ListInfo> items) {
        super(context, textViewResourceId, items);
        this.items = items;
        this.inflater = (LayoutInflater)
                context.getSystemService(
                        Context.LAYOUT_INFLATER_SERVICE
                );
}

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = inflater.inflate(R.layout.list_form_2, null);
        }

        ListInfo item = (ListInfo) items.get(position);
        if (item != null) {

            ImageView imageicon = (ImageView) view.findViewById(R.id.imgicon);
            imageicon.setImageResource(item.getIcon());

            TextView textinfo = (TextView) view.findViewById(R.id.txtinfo);
            textinfo.setTypeface(Typeface.DEFAULT_BOLD);
            textinfo.setText(item.getInfo());


        }
        return view;
    }
}

