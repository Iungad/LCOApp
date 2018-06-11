package com.ungadtech.lcoapp;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity  implements View.OnClickListener{
    private RecyclerView mainRecycler;
    private ImageView bannerAd;
    private ProgressBar loadingProgressBar;
    private QuestionAdapter recyclerAdapter;
    private RecyclerView.LayoutManager recyclerLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // initialize view
        mainRecycler = findViewById(R.id.main_recycler_view);
        bannerAd = findViewById(R.id.lco_banner_ad);
        loadingProgressBar = findViewById(R.id.question_loading_progress);
        // initialize variable
        recyclerLayoutManager = new LinearLayoutManager(this);
        mainRecycler.setLayoutManager(recyclerLayoutManager);
        mainRecycler.setHasFixedSize(true);
        // set click listener banner ad
        bannerAd.setOnClickListener(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        getQuestions();
    }

    public void getQuestions() {
        new GetQuestionTask().execute();
    }

    @Override
    public void onClick(View v) {

        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(LcoApp.HOST_NAME));
        startActivity(i);

    }


    class GetQuestionTask extends AsyncTask<Void, Void, Question[]> {


        @Override
        protected void onPreExecute() {
            loadingProgressBar.setVisibility(View.VISIBLE);
            super.onPreExecute();
        }

        @Override
        protected Question[] doInBackground(Void... voids) {
            Question[] questions = new Question[0];
            OkHttpClient client = LcoApp.getUnsafeOkHttpClient().build();

            Request request = new Request.Builder()
                    .url(LcoApp.API_URL)
                    .build();
            try {
                Response response = client.newCall(request).execute();
                QuestionArray questionArray = new Gson().fromJson(response.body().string(), QuestionArray.class);

                questions = questionArray.getQuestions();

            } catch (IOException e) {
                e.printStackTrace();
            }

            return questions;

        }

        @Override
        protected void onPostExecute(Question[] questions) {
            loadingProgressBar.setVisibility(View.GONE);
            recyclerAdapter = new QuestionAdapter(MainActivity.this,questions);
            mainRecycler.setAdapter(recyclerAdapter);
            super.onPostExecute(questions);
        }
    }
}
