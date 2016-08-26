package com.example.mbmbmb.moviesapp;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;


public class  TrailerCustomApater extends BaseAdapter {
    private Context contxt;

    public ArrayList<Trailer> trailers;

    public TrailerCustomApater(Context o, ArrayList<Trailer> trailers) {
        contxt = o;
        this.trailers = trailers;

    }


    @Override
    public int getCount() {
        return trailers.size();
    }

    @Override
    public Object getItem(int position) {
        return trailers.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) contxt.getSystemService(contxt.LAYOUT_INFLATER_SERVICE); //

        if (convertView == null)
            convertView = inflater.inflate(R.layout.trailer_list_item, null);
        TextView tail=(TextView)convertView.findViewById(R.id.tailer);
        ImageView video=(ImageView)convertView.findViewById(R.id.video);
        Trailer tt=trailers.get(position);
        tail.setText(tt.getName());

        return convertView;
    }
}


