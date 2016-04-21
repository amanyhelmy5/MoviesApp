package com.example.mbmbmb.moviesapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.app.Fragment;

import com.squareup.picasso.Picasso;

public class detailsfragment extends Fragment {
    private ImageView imageView;
    private TextView overviewText;
    private TextView dateText;
    private RatingBar rating;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_detailsfragment, container, false);

        //Bundle bundle = this.getArguments();

        Intent intent = getActivity().getIntent();

        String title = intent.getStringExtra("title");
        String image = intent.getStringExtra("background");
        String overview = intent.getStringExtra("overview");
        String date = intent.getStringExtra("date");
        double rr = intent.getDoubleExtra("rate", 0.0);
        getActivity().setTitle(title);
        overviewText = (TextView) v.findViewById(R.id.over);
        dateText = (TextView) v.findViewById(R.id.rre);
        rating = (RatingBar) v.findViewById(R.id.rab);
        imageView = (ImageView) v.findViewById(R.id.mm);
        overviewText.setText(overview);
        dateText.setText(date);
        rating.setRating((float) rr / 2);


        Picasso.with(getActivity()).load("http://image.tmdb.org/t/p/w780/" + image).into(imageView);

        return v;

    }
}