package com.example.lenovo.retronews;

/**
 * Created by Lenovo on 8/23/2017.
 */

import com.example.lenovo.retronews.News;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;


public interface ApiInterface {
    @GET("v1/articles")
    Call<News> getTopNews(@Query("source") String source,@Query("sortBy") String sortby,@Query("apiKey") String apikey);

}