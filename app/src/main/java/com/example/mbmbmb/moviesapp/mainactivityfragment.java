package com.example.mbmbmb.moviesapp;

import android.app.Fragment;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

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

/**
 * Created by mbmbmb on 3/25/2016.
 */

public class mainactivityfragment extends Fragment {
    String moviesjsonStr = null;
    GridView mygride;
    String sortType="popular";

    public  ArrayList<components> jsmovie (String r)
            throws JSONException
    {
        ArrayList<components> List=new ArrayList<>();

        JSONObject obj =new JSONObject(r);
        JSONArray jsonArray=obj.getJSONArray("results");
        for(int i=0 ;i<jsonArray.length() ; i++){
            JSONObject ay=jsonArray.getJSONObject(i);
                                                                                                                                   String path= ay.getString("poster_path");
            String overviewtt= ay.getString("overview");
            String title= ay.getString("title");
            String rdata= ay.getString("release_date");
            double vote=ay.optDouble("vote_average");
            String back= ay.getString( "backdrop_path");

            components dd= new components();
        dd.setBackground(back);
            dd.setDate(rdata);
            dd.setOverview(overviewtt);
            dd.setPoster(path);
            dd.setTitle(title);
            dd.setRating(vote);
            List.add(dd);

        }
        return List;
    }

    @Override
    public void onResume() {
        super.onResume();
        DownaloadMovie downaloadMovie = new DownaloadMovie();
        downaloadMovie.execute();

    }
    // super.onResume();

    public View onCreateView (LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v=inflater.inflate(R.layout.fragment_main, container, false);
        mygride=(GridView)v.findViewById(R.id.mymovieGride);


       // components vv=(components )
        mygride.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                components vv = (components) adapterView.getItemAtPosition(i);
                if ((getActivity().getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) >= Configuration.SCREENLAYOUT_SIZE_LARGE) {


                    detailsfragment detailsFragment = new detailsfragment();
                    Bundle extras = new Bundle();
                    extras.putString("title",vv.getTitle());
                    extras.putString("overview",vv.getOverview());
                    extras.putString(("data"),vv.getDate());
                    extras.putString(("rate"), String.valueOf(vv.getRating()));
                    extras.putString("background",vv.getBackground());
                    detailsFragment.setArguments(extras);
                    getFragmentManager().beginTransaction().replace(R.id.frame, detailsFragment).commit();

                }
                else {
                    Intent intent = new Intent(getActivity(),Details.class);
                    intent.putExtra("title", vv.getTitle());
                    intent.putExtra("overview", vv.getOverview());
                    intent.putExtra("date", vv.getDate());
                    intent.putExtra("rate", vv.getRating());
                    intent.putExtra("background", vv.getBackground());
                    //Start details activity
                    startActivity(intent);
                }
            }
        });
        //this is "gridview" the gride view name you are looking on xml
        //nowwe need to build a Background Class (API Clas)
        return v;
    }


    public  class DownaloadMovie extends AsyncTask<Void,Void,Void> {

        @Override
        protected Void doInBackground(Void... voids) {

            // These two need to be declared outside the try/catch
            // so that they can be closed in the finally block.
            HttpURLConnection urlConnection = null;
            BufferedReader reader = null;

            // Will contain the raw JSON response as a string.



            try {
                SharedPreferences sharedPreferences= PreferenceManager.getDefaultSharedPreferences(getContext());

                String ss=sharedPreferences.getString(getString(R.string.pref_location_key),"most popular");
                String api_key="56b97ff259acaff235cab79cbd341154";

              if(ss.equals("most poular")) {

                sortType = "popular";
              }
                else if(ss.equals("top rated")) {
                 sortType="top_rated";
                  }
                final String  moviesURL =
            "https://api.themoviedb.org/3/movie/"+sortType+"?api_key="+api_key;


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


                moviesjsonStr = buffer.toString();
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

                ArrayList<components> mylist = jsmovie(moviesjsonStr);
                mygride.setAdapter(new SecondCustomApater(getActivity().getApplicationContext(),mylist));

            }catch (Exception ex){


            }


        }
    }

}





