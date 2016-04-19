package com.example.mbmbmb.moviesapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class Details extends AppCompatActivity {
    private ImageView imageView;
    private TextView overviewText;
    private TextView dateText;
    private RatingBar rating;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        Intent intent=getIntent();
        String title = intent.getStringExtra("title");
        String image = intent.getStringExtra("background");
        String overview=intent.getStringExtra("overview");
        String date=intent.getStringExtra("date");
        double  rr=intent.getDoubleExtra("rate",0.0);
        setTitle(title);
        overviewText=(TextView)findViewById(R.id.over);
        dateText=(TextView)findViewById(R.id.rre);
        rating=(RatingBar) findViewById(R.id.rab);
        imageView = (ImageView) findViewById(R.id.mm);
        overviewText.setText(overview);
        dateText.setText(date);
        rating.setRating((float) rr / 2);


         Picasso.with(this).load("http://image.tmdb.org/t/p/w780/"+image).into(imageView);

    }
}
