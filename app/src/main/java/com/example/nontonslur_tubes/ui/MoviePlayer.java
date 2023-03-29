package com.example.nontonslur_tubes.ui;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.nontonslur_tubes.R;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.ui.StyledPlayerView;

public class MoviePlayer extends AppCompatActivity {

    private StyledPlayerView playerView;
    private ExoPlayer player;
    private String VIDEO_TEST_URL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_player);

        playerView = findViewById(R.id.movie_exo_player);
        VIDEO_TEST_URL = getIntent().getExtras().getString("linkStream");

    }

    private void iniExoPlayer(){
        player = new ExoPlayer.Builder(this).build();
        playerView.setPlayer(player);
        MediaItem mediaItem = MediaItem.fromUri(VIDEO_TEST_URL);
        player.setMediaItem(mediaItem);
        player.prepare();
        player.play();
    }

    @Override
    protected void onStart() {
        super.onStart();
        iniExoPlayer();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (player == null){
            iniExoPlayer();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (player.isPlaying()){
            player.stop();
        }
    }

}