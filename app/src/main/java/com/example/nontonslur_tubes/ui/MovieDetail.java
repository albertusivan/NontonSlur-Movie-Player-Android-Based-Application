package com.example.nontonslur_tubes.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.nontonslur_tubes.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MovieDetail extends AppCompatActivity {

    private ImageView MovieThumbnailImg, MovieCoverImg;
    private TextView tv_title, tv_desc;
    private FloatingActionButton play_fab;
    private String streamLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        iniViews();
    }

    void iniViews(){
        play_fab = findViewById(R.id.play_fab);
        String movieTitle = getIntent().getExtras().getString("title");
        String imageResourceId = getIntent().getExtras().getString("imgURL");
        String imageCoverId = getIntent().getExtras().getString("imgCover");
        String desc = getIntent().getExtras().getString("desc");
        String linkStream = getIntent().getExtras().getString("linkStream");

        MovieThumbnailImg = findViewById(R.id.detail_movie_img);
        Glide.with(this).load(imageResourceId).into(MovieThumbnailImg);
//        MovieThumbnailImg.setImageResource(imageResourceId);
        MovieCoverImg = findViewById(R.id.detail_movie_cover);
        Glide.with(this).load(imageCoverId).fitCenter().into(MovieCoverImg);
//        MovieCoverImg.setImageResource(imageCoverId);
        tv_title = findViewById(R.id.detail_movie_title);
        tv_title.setText(movieTitle);
        tv_desc = findViewById(R.id.detail_movie_desc);
        tv_desc.setText(desc);

        MovieCoverImg.setAnimation(AnimationUtils.loadAnimation(this, R.anim.scale_animation));
        play_fab.setAnimation(AnimationUtils.loadAnimation(this,R.anim.scale_animation));

        play_fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MovieDetail.this, MoviePlayer.class);
                intent.putExtra("linkStream", linkStream);
                startActivity(intent);
            }
        });
    }
}