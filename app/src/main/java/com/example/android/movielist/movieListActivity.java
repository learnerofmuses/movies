package com.example.android.movielist;
/*import android.app.Dialog;
import android.app.DialogFragment;*/
//import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.RelativeLayout;
import android.widget.ArrayAdapter;
import java.lang.reflect.Array;
import android.app.Activity;
import java.util.ArrayList;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;


public class movieListActivity extends AppCompatActivity {


    public static ArrayList<String> movies;
    public static ArrayList<String> imdbCode;
    private static final String[] faveMovieList = {"The Godfather: Part II", "Alien", "Star Wars: The Empire Strikes Back", "Friday", "Avengers: Infinity War", "A Quiet Place", "Deadpool", "Love and Basketball"};
    private static final String[] urls = {"tt0071562", "tt0078748", "tt0080684", "tt0113118", "tt4154756", "tt6644200", "tt1431045", "tt0199725"};
    private ListView list;
    private TextView layout;
    private String addMovies;
    private String addURLS;
    private Button button;
    private AlertDialog dialog;
    private AdapterView.OnItemLongClickListener deleteMovie;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_list);
        movies = new ArrayList<String>();
        imdbCode = new ArrayList<String>();
        for (int i = 0; i < faveMovieList.length; i++) {
            movies.add(faveMovieList[i]);
        }

        for (int j = 0; j < urls.length; j++) {
            imdbCode.add(urls[j]);
        }
        //time to delete movies from the list
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Remove this movie from the list?");

        //here we create the yes alert dialog button
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                movies.remove(i);
                imdbCode.remove(i);
            }
        });

        //here we create the no alert dialog button
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                movies.remove(i);
                imdbCode.remove(i);
            }
        });

        dialog = builder.create();

        layout = findViewById(R.id.layout);
        list = findViewById(R.id.list);
        button = findViewById(R.id.button);

        ArrayAdapter<String> adapter;
        adapter = new ArrayAdapter<String>(this, R.layout.list_item_view, movies);
        list.setAdapter(adapter);
        list.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.imdb.com/title/" + imdbCode.get(i)));
                        startActivity(intent);
                    }
                }
        );

        list.setOnItemLongClickListener(
                new AdapterView.OnItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                        dialog.show();
                        return true;
                    }
                }
        );
    }


    public void buttonPressed(View v) {
        Intent intent = new Intent(this, add_moviesActivity.class);
        startActivityForResult(intent, 1);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        addMovies = (data.getStringExtra("MOVIE TITLE"));
        addURLS = (data.getStringExtra("MOVIE CODE"));
        movies.add(addMovies);
        imdbCode.add(addURLS);

        ArrayAdapter<String> adapter;
        adapter = new ArrayAdapter<String>(this, R.layout.list_item_view, movies);
        list.setAdapter(adapter);

    }

}
