package com.example.nontonslur_tubes.util;

import androidx.annotation.NonNull;

import com.example.nontonslur_tubes.model.Movie;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class DataSource {

    static DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://nontonslur-tubes-default-rtdb.firebaseio.com/");

    public static List<Movie> getPopularMovies(){

        List<Movie> lstMovie = new ArrayList<>();
        ValueEventListener postListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(int i=1;i<4;i++){
                    DataSnapshot ds = snapshot.child(String.valueOf(i));
                    String title = ds.child("title").getValue(String.class);
                    String url = ds.child("url").getValue(String.class);
                    String desc = ds.child("desc").getValue(String.class);
                    String imageUrl = ds.child("thumb").getValue(String.class);
                    String cover = ds.child("cover").getValue(String.class);
                    lstMovie.add(new Movie(title, desc,
                            imageUrl, url,
                            cover));
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {}
        };
        databaseReference.child("Film").addValueEventListener(postListener);
        lstMovie.add(new Movie("Big Buck Bunny",
                "Big Buck Bunny tells the story of a giant rabbit with a heart bigger than himself.",
                "https://upload.wikimedia.org/wikipedia/commons/c/c5/Big_buck_bunny_poster_big.jpg",
                "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4",
                "https://i.ytimg.com/vi/aqz-KE-bpKQ/maxresdefault.jpg"));
        return lstMovie;
    }

    public static List<Movie> getWeekMovies(){
        List<Movie> lstMovie = new ArrayList<>();

        ValueEventListener postListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(int i=1;i<6;i+=2){
                    DataSnapshot ds = snapshot.child(String.valueOf(i));
                    String title = ds.child("title").getValue(String.class);
                    String url = ds.child("url").getValue(String.class);
                    String desc = ds.child("desc").getValue(String.class);
                    String imageUrl = ds.child("thumb").getValue(String.class);
                    String cover = ds.child("cover").getValue(String.class);
                    lstMovie.add(new Movie(title, desc,
                            imageUrl, url,
                            cover));
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        };
        databaseReference.child("Film").addValueEventListener(postListener);
        lstMovie.add(new Movie("Big Buck Bunny",
                "Big Buck Bunny tells the story of a giant rabbit with a heart bigger than himself.",
                "https://upload.wikimedia.org/wikipedia/commons/c/c5/Big_buck_bunny_poster_big.jpg",
                "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4",
                "https://i.ytimg.com/vi/aqz-KE-bpKQ/maxresdefault.jpg"));

        return lstMovie;
    }

    public static List<Movie> getListMovies(){
        List<Movie> lstMovie = new ArrayList<>();
        ValueEventListener postListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot ds : snapshot.getChildren()){
                    String title = ds.child("title").getValue(String.class);
                    String url = ds.child("url").getValue(String.class);
                    String desc = ds.child("desc").getValue(String.class);
                    String imageUrl = ds.child("thumb").getValue(String.class);
                    String cover = ds.child("cover").getValue(String.class);
                    lstMovie.add(new Movie(title, desc,
                            imageUrl, url,
                            cover));
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {}
        };
        databaseReference.child("Film").addValueEventListener(postListener);
        return lstMovie;
    }



}


