package com.example.android.movielist;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.view.View;

import android.widget.Toast;

public class add_moviesActivity extends AppCompatActivity {

    private String addmovieTitle;
    private String addmovieCode;
    private EditText addNewMovie;
    private EditText addNewCode;





    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_movies);
        addNewMovie = findViewById(R.id.newMovie);
        addNewCode = findViewById(R.id.newCode);





    }

    /*public void onBackPressed() {
        Intent getMovieInfo = new Intent();
        Intent getMovieInfo2 = new Intent();
        getMovieInfo.putExtra("Movie Title", movieTitle.getText().toString());
        setResult(RESULT_OK, getMovieInfo);
        getMovieInfo2.putExtra("Movie ID Code", .getText().toString());
        setResult(RESULT_OK, getMovieInfo2);*/
    public void Buttonpressed(View view){
        addmovieTitle = addNewMovie.getText().toString();
        addmovieCode = addNewCode.getText().toString();
        Intent data = new Intent();
        data.putExtra("MOVIE TITLE",addmovieTitle);
        data.putExtra("MOVIE CODE", addmovieCode);
        setResult(RESULT_OK, data);
        Toast.makeText(this, "Movie was successfully added!", Toast.LENGTH_LONG).show();

        finish();


    }

}


