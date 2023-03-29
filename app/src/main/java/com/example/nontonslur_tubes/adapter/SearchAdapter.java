package com.example.nontonslur_tubes.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.nontonslur_tubes.R;
import com.example.nontonslur_tubes.model.Movie;

import java.util.List;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.MyViewHolder> {

    Context context;
    List<Movie> mData;
    MovieItemClickListener movieItemClickListener;

    public void setFilter(List<Movie> filter){
        this.mData = filter;
    }

    public SearchAdapter(Context context, List<Movie> mData, MovieItemClickListener listener) {
        this.context = context;
        this.mData = mData;
        movieItemClickListener = listener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_search, parent, false);
        return new MyViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.TvTitle.setText(mData.get(position).getTitle());
        Glide.with(holder.ImgMovie.getContext())
                .load(mData.get(position).getThumb())
                .into(holder.ImgMovie);

//        holder.ImgMovie.setImageResource(mData.get(position).getThumbnail());
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        private TextView TvTitle;
        private ImageView ImgMovie;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            TvTitle = itemView.findViewById(R.id.item_search_title);
            ImgMovie = itemView.findViewById(R.id.item_search_img);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    movieItemClickListener.onMovieClick(mData.get(getAdapterPosition()), ImgMovie);
                }
            });

        }
    }

}
