package com.example.mbmbmb.moviesapp;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;


public class ReviewCustomApater extends BaseAdapter {
    private Context contxt;

    public ArrayList<Review> reviews;

    public ReviewCustomApater(Context o, ArrayList<Review> reviews) {
        contxt = o;
        this.reviews = reviews;

    }


    @Override
    public int getCount() {
        return reviews.size();
    }

    @Override
    public Object getItem(int position) {
        return reviews.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) contxt.getSystemService(contxt.LAYOUT_INFLATER_SERVICE); //

        if (convertView == null)
            convertView = inflater.inflate(R.layout.review_list_item, null);
        TextView aut=(TextView) convertView.findViewById(R.id.review_author);
        TextView con=(TextView) convertView.findViewById(R.id.review_content);
        Review rr=reviews.get(position);
        aut.setText(rr.getAuthor());
        con.setText(rr.getContent());

        return convertView;
    }
}


