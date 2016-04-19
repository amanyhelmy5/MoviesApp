package com.example.mbmbmb.moviesapp;


import android.content.Context;
import android.graphics.Movie;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class SecondCustomApater extends BaseAdapter {
    private Context contxt;
    private ArrayList<Movie> data; ///

    public ArrayList<components> imgPaths;

    public SecondCustomApater(Context o,ArrayList<components> imgPaths) {
        contxt = o;
        this.imgPaths=imgPaths;
        //  data = movies;

    }


    @Override
    public int getCount() {
        return imgPaths.size();
    }

    @Override
    public Object getItem(int position) {
        return imgPaths.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater)contxt.getSystemService(contxt.LAYOUT_INFLATER_SERVICE); //

        ImageView imageView;
        if (convertView == null)

            convertView = inflater.inflate(R.layout.item, null);
        ImageView imgView= (ImageView) convertView.findViewById(R.id.imgg);

//User Pecasso Here
        components ff=imgPaths.get(position);

        Picasso.with(contxt).load("http://image.tmdb.org/t/p/w342" + ff.getPoster()).into(imgView);

        return convertView;
    }
}


