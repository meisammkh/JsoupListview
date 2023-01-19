package com.example.jsouplistview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class CustomAdapter extends BaseAdapter {

    Context context;
    List<String> newsList;
    LayoutInflater inflater;
    Typeface typeFace;

    public CustomAdapter(Context ctx, List<String> newsList ){
        this.context = ctx;
        this.newsList = newsList;
        inflater = LayoutInflater.from(ctx);
    }



    @Override
    public int getCount() {
        return 150;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @SuppressLint("ViewHolder")
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        view = inflater.inflate(R.layout.activity_listview_detail, null);
        TextView textView = (TextView) view.findViewById(R.id.headline);
        typeFace = Typeface.createFromAsset(context.getAssets(), "fonts/iransans.ttf");
        textView.setText(newsList.get(i));
        textView.setTypeface(typeFace);
        return view;
    }
}