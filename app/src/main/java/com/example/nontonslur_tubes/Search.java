package com.example.nontonslur_tubes;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nontonslur_tubes.adapter.MovieItemClickListener;
import com.example.nontonslur_tubes.adapter.SearchAdapter;
import com.example.nontonslur_tubes.model.Movie;
import com.example.nontonslur_tubes.ui.Menu;
import com.example.nontonslur_tubes.ui.MovieDetail;
import com.example.nontonslur_tubes.util.DataSource;

import java.util.ArrayList;
import java.util.List;

public class Search extends AppCompatActivity implements MovieItemClickListener {

    List<Movie> lstMovies  = new ArrayList<>();
    private RecyclerView recyclerView;
    private SearchView searchView;
    SearchAdapter searchAdapter;
    private Button home;
    private Button profile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        recyclerView = findViewById(R.id.Rv_listSearch);
        searchView = findViewById(R.id.searchView);
        searchView.clearFocus();

        lstMovies = DataSource.getListMovies();
        searchAdapter = new SearchAdapter(this, lstMovies, this);
        recyclerView.setAdapter(searchAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                filterList(s);
                return true;
            }
        });

        String usernametxt = getIntent().getStringExtra("username");

        home = (Button) findViewById(R.id.home);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Search.this, Menu.class);
                intent.putExtra("username", usernametxt);
                startActivity(intent);
            }
        });

        profile = findViewById(R.id.profile);
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Search.this, Profile.class);

                intent.putExtra("username", usernametxt);
                startActivity(intent);
            }
        });



    }

    private void filterList(String s) {
        List<Movie> filterList = new ArrayList<>();
        if (s.isEmpty()){
            filterList = lstMovies;
        }else{
            for(Movie movie : lstMovies){
                if(movie.getTitle().toLowerCase().contains(s.toLowerCase())){
                    filterList.add(movie);
                }
        }
        }if (filterList.isEmpty()){
            Toast.makeText(this, "No Match Movie", Toast.LENGTH_SHORT).show();
        }else{
            searchAdapter.setFilter(filterList);
            searchAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onMovieClick(Movie movie, ImageView movieImageView) {
        Intent intent = new Intent(this, MovieDetail.class);
        intent.putExtra("title",movie.getTitle());
        intent.putExtra("imgURL",movie.getThumb());
        intent.putExtra("imgCover",movie.getCover());
        intent.putExtra("desc",movie.getDescription());
        intent.putExtra("linkStream", movie.getStreamLink());

        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(Search.this,
                movieImageView, "sharedName");
        startActivity(intent, options.toBundle());
    }
}