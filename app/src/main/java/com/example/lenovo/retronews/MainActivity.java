package com.example.lenovo.retronews;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.List;

import jp.wasabeef.glide.transformations.CropSquareTransformation;
import jp.wasabeef.glide.transformations.GrayscaleTransformation;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private final static String API_KEY = "bb170e423a7647bf8f889939621bd2a3";
    ImageView logo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        logo=(ImageView)findViewById(R.id.logo);
        Glide.with(this)
                .load(R.drawable.bbc)
                .into(logo);
        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);
        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Call<News> call = apiService.getTopNews("al-jazeera-english","top",API_KEY);
        call.enqueue(new Callback<News>() {
            @Override
            public void onResponse(Call<News>call, Response<News> response) {
                List<Article> news = response.body().getArticles();
                recyclerView.setAdapter(new NewsAdapter(news,getApplicationContext()));
                Log.d("TAG", "Number of news received: " + news.size());
            }

            @Override
            public void onFailure(Call<News>call, Throwable t) {
                // Log error here since request failed
                Log.e("TAG", t.toString());
            }
        });
    }
}
