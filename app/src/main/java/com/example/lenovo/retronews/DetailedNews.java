package com.example.lenovo.retronews;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import jp.wasabeef.glide.transformations.CropSquareTransformation;
import jp.wasabeef.glide.transformations.GrayscaleTransformation;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Lenovo on 9/4/2017.
 */

public class DetailedNews extends AppCompatActivity {

    String title;
    String author;
    String content;
    String imageURL;
    ImageView news_image;
    TextView news_title,news_author,news_content;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detailednews);
        news_image=(ImageView)findViewById(R.id.photo_detailed);
        news_title=(TextView)findViewById(R.id.title_detailed);
        news_author=(TextView)findViewById(R.id.author);
        news_content=(TextView)findViewById(R.id.content);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
           title = extras.getString("Title");
           author= extras.getString("Author");
           content=extras.getString("Content");
           imageURL=extras.getString("Image");
        }
        //content=content+"Shaheen lives in a cramped, two-room tenement in the congested Khatakpura Izzat Khan lane in urban Farukkhabad. Her husband Dishad sells embroidery scraps for a living. They have three daughters, aged 15, 10 and 6.\n" +
          //      "\n" +
          //      "In the dimly-lit room, Shaheen waits for Dilshad to return. Short on money, life is tough for the family. But Shaheen is also in trauma. A few weeks ago, her fourth child died a day after birth.";
           news_title.setText(title);
        Glide.with(this)
                .load(imageURL)
                .into(news_image);
        news_author.setText(author);

    }
}
