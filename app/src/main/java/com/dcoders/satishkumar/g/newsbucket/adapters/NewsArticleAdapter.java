package com.dcoders.satishkumar.g.newsbucket.adapters;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.dcoders.satishkumar.g.newsbucket.FullDetailActivity;
import com.dcoders.satishkumar.g.newsbucket.R;
import com.dcoders.satishkumar.g.newsbucket.mainDataClasses.MyDataClass;

import java.util.List;

import static com.dcoders.satishkumar.g.newsbucket.FullDetailActivity.AUTHOR;
import static com.dcoders.satishkumar.g.newsbucket.FullDetailActivity.DESCRIPTION;
import static com.dcoders.satishkumar.g.newsbucket.FullDetailActivity.EXTERNALURL;
import static com.dcoders.satishkumar.g.newsbucket.FullDetailActivity.IMAGE;
import static com.dcoders.satishkumar.g.newsbucket.FullDetailActivity.TITLE;

public class NewsArticleAdapter extends RecyclerView.Adapter<NewsArticleAdapter.ViewHolder> {

    private Context context;
    private int position;
    private List<MyDataClass> articleList;

    public NewsArticleAdapter(Context context, List<MyDataClass> articleList) {
        this.context = context;
        this.articleList = articleList;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.article_item_view,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.titleTv.setText(articleList.get(position).getNtitle());
        if (articleList.get(position).getImageUrl()==null)
        {
            holder.articleImage.setImageResource(R.drawable.newsbucketnullimage);
        }
        else {
            Glide.with(context)
                    .load(articleList.get(position).getImageUrl())
                    .into(holder.articleImage);
        }

    }

    @Override
    public int getItemCount() {
        return articleList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView articleImage;
        TextView titleTv;
        private ViewHolder(View itemView) {
            super(itemView);
            articleImage=itemView.findViewById(R.id.article_image_view);
            titleTv=itemView.findViewById(R.id.article_title_tv);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            position=getAdapterPosition();
            updateWidget();
            Intent intent=new Intent(context, FullDetailActivity.class);
            intent.putExtra(TITLE,articleList.get(position).getNtitle());
            intent.putExtra(DESCRIPTION,articleList.get(position).getNdescription());
            intent.putExtra(IMAGE,articleList.get(position).getImageUrl());
            intent.putExtra(EXTERNALURL,articleList.get(position).getUrl());
            intent.putExtra(AUTHOR,articleList.get(position).getAuthor());
            context.startActivity(intent);

        }
    }
    private void updateWidget()
    {
        SharedPreferences sharedPreferences=context.getSharedPreferences("SATISH",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString(TITLE,articleList.get(position).getNtitle());
        editor.putString(IMAGE,articleList.get(position).getImageUrl());
        editor.apply();
    }
}
