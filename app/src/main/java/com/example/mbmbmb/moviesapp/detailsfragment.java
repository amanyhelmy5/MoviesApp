package com.example.mbmbmb.moviesapp;

import android.app.Fragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class detailsfragment extends Fragment {
    private ImageView imageView;
    private TextView overviewText;
    private TextView dateText;
    private RatingBar rating;
    String ReviewJson;

    String TrailerJson;
    int id;
    ListView trailer;
ListView review;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_detailsfragment, container, false);

        Bundle bundle = this.getArguments();

        // Intent intent = getActivity().getIntent();
        if(bundle != null) {
            String title = bundle.getString("title");
            String image = bundle.getString("background");
            String overview = bundle.getString("overview");
            String date = bundle.getString("date");
             id=bundle.getInt("id");
            double bb = bundle.getDouble("rate", 0.0);
            getActivity().setTitle(title);
            overviewText = (TextView) v.findViewById(R.id.over);
            dateText = (TextView) v.findViewById(R.id.rre);
            rating = (RatingBar) v.findViewById(R.id.rab);
            imageView = (ImageView) v.findViewById(R.id.mm);
            overviewText.setText(overview);
            dateText.setText(date);
            rating.setRating((float) bb / 2);
            Picasso.with(getActivity()).load("http://image.tmdb.org/t/p/w780/" + image).into(imageView);

            trailer=(ListView) v.findViewById(R.id.trailer);
            review=(ListView) v.findViewById(R.id.reviw);
            TrailerJson mff=new TrailerJson();
            mff.execute();
            ReviewJson kdfs=new ReviewJson();
            kdfs.execute();
        }
        return v;

    }
    public class TrailerJson  extends AsyncTask<Void,Void,Void> {


        public ArrayList<Trailer> trailerjsion(String r)
                throws JSONException {
            ArrayList<Trailer> List = new ArrayList<>();

            JSONObject obj = new JSONObject(r);
            JSONArray jsonArray = obj.getJSONArray("results");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject ay = jsonArray.getJSONObject(i);
                String name = ay.getString("name");
                String key = ay.getString("key");
                Trailer t = new Trailer();
                t.setName(name);
                t.setUrl(key);
            }
            return List;
        }


        @Override
        protected Void doInBackground(Void... voids) {
            HttpURLConnection urlConnection = null;
            BufferedReader reader = null;


            try {

                String api_key = "56b97ff259acaff235cab79cbd341154";

                final String moviesURL =
                        " http://api.themoviedb.org/3/movie/" + id + "/videos?api_key=56b97ff259acaff235cab79cbd341154";

                URL url = new URL(moviesURL);

                // Create the request to OpenWeatherMap, and open the connection
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.connect();

                // Read the input stream into a String
                InputStream inputStream = urlConnection.getInputStream();
                StringBuffer buffer = new StringBuffer();
                if (inputStream == null) {
                    // Nothing to do.
                    return null;
                }
                reader = new BufferedReader(new InputStreamReader(inputStream));

                String line;
                while ((line = reader.readLine()) != null) {
                    // Since it's JSON, adding a newline isn't necessary (it won't affect parsing)
                    // But it does make debugging a *lot* easier if you print out the completed
                    // buffer for debugging.

                    buffer.append(line + "\n");
                }


                TrailerJson = buffer.toString();
            } catch (IOException e) {
                // Log.e(LOG_TAG, "Error ", e);
                // If the code didn't successfully get the weather data, there's no point in attemping
                // to parse it.
                return null;

            } finally {
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (final IOException e) {
                        //  Log.e(LOG_TAG, "Error closing stream", e);
                    }
                }
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            try {
                ArrayList<Trailer> mylist = trailerjsion(TrailerJson);
                trailer.setAdapter(new TrailerCustomApater(getActivity().getApplicationContext(), mylist));


            } catch (Exception ex) {


            }


        }
    }
    public class ReviewJson extends AsyncTask<Void,Void,Void> {


        public ArrayList<Review> reviewsjsion (String r)
                throws JSONException
        {
            ArrayList<Review> List=new ArrayList<>();

            JSONObject obj =new JSONObject(r);
            JSONArray jsonArray=obj.getJSONArray("results");
            for(int i=0 ;i<jsonArray.length() ; i++){
                JSONObject ay=jsonArray.getJSONObject(i);
                String author= ay.getString("author");
                String content=ay.getString("content");
                String url =ay.getString("url");
                int id=ay.getInt("id");
                Review rr=new Review();
                rr.setAuthor(author);
                rr.setUrl(url);
                rr.setContent(content);
                rr.setId(id);
            }
            return List;
        }


        @Override
        protected Void doInBackground(Void... voids) {
            HttpURLConnection urlConnection = null;
            BufferedReader reader = null;


            try {

                String api_key="56b97ff259acaff235cab79cbd341154";

                final String  moviesURL =
                        " http://api.themoviedb.org/3/movie/"+id+"/reviews?api_key=56b97ff259acaff235cab79cbd341154";

                URL url = new URL( moviesURL);

                // Create the request to OpenWeatherMap, and open the connection
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.connect();

                // Read the input stream into a String
                InputStream inputStream = urlConnection.getInputStream();
                StringBuffer buffer = new StringBuffer();
                if (inputStream == null) {
                    // Nothing to do.
                    return null;
                }
                reader = new BufferedReader(new InputStreamReader(inputStream));

                String line;
                while ((line = reader.readLine()) != null) {
                    // Since it's JSON, adding a newline isn't necessary (it won't affect parsing)
                    // But it does make debugging a *lot* easier if you print out the completed
                    // buffer for debugging.

                    buffer.append(line + "\n");
                }


                ReviewJson = buffer.toString();
            } catch (IOException e) {
                // Log.e(LOG_TAG, "Error ", e);
                // If the code didn't successfully get the weather data, there's no point in attemping
                // to parse it.
                return null;

            } finally {
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (final IOException e) {
                        //  Log.e(LOG_TAG, "Error closing stream", e);
                    }
                }
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            try {
                ArrayList<Review> mylist = reviewsjsion(ReviewJson);
                review.setAdapter(new ReviewCustomApater(getActivity().getApplicationContext(),mylist));




            }catch (Exception ex){


            }


        }
    }
}