package com.example.lenovo.retronews;

/**
 * Created by Lenovo on 8/28/2017.
 */

        import android.content.Context;
        import android.content.Intent;
        import android.content.res.AssetManager;
        import android.graphics.Typeface;
        import android.support.v7.widget.CardView;
        import android.support.v7.widget.RecyclerView;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.ImageView;
        import jp.wasabeef.glide.transformations.GrayscaleTransformation;
        import jp.wasabeef.glide.transformations.CropSquareTransformation;

        import android.widget.RelativeLayout;
        import android.widget.TextView;
        import com.bumptech.glide.Glide;
        import java.util.List;
        import java.util.Locale;

        import com.example.lenovo.retronews.R;
        import com.example.lenovo.retronews.Article;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ArticleViewHolder> {
    private List<Article> Articles;
    private int rowLayout;
    private Context context;
    Typeface font;



    public static class ArticleViewHolder extends RecyclerView.ViewHolder {

        TextView ArticleTitle;
        ImageView ArticlePhoto;
        CardView ArticleCard;
        private Context context;

        public ArticleViewHolder(View v) {
            super(v);
            context=v.getContext();
            ArticleTitle = (TextView) v.findViewById(R.id.title);
           ArticlePhoto=(ImageView)v.findViewById(R.id.photo);
            ArticleCard=(CardView) v.findViewById(R.id.cv);

        }
    }

    public NewsAdapter(List<Article> Articles, Context context) {
        this.Articles = Articles;
        this.context = context;
        font = Typeface.createFromAsset(context.getAssets(), "fonts/bo.ttf");
    }

    @Override
    public NewsAdapter.ArticleViewHolder onCreateViewHolder(ViewGroup parent,
                                                            int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_card, parent, false);
        return new ArticleViewHolder(view);
    }


    @Override
    public void onBindViewHolder(ArticleViewHolder holder, final int position) {



        holder.ArticleTitle.setText(Articles.get(position).getTitle());
        holder.ArticleTitle.setTypeface(font);
        Glide.with(this.context)
                .load(Articles.get(position).getUrlToImage())
                .override(100,100)
                .bitmapTransform(new GrayscaleTransformation(context),new CropSquareTransformation(context))
                .into(holder.ArticlePhoto);
        holder.ArticleCard.setOnClickListener(new View.OnClickListener(){
            Intent i = new Intent(context,DetailedNews.class)
                    .putExtra("Title",Articles.get(position).getTitle())
                    .putExtra("Content",Articles.get(position).getDescription())
                    .putExtra("Author",Articles.get(position).getAuthor())
                    .putExtra("Image",Articles.get(position).getUrlToImage());

            @Override
            public void onClick(View view) {
                context.startActivity(i);

            }
        } );

    }

    @Override
    public int getItemCount() {
        return Articles.size();
    }
}