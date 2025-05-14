package br.com.kunden.movies_catalog.ui.movieInfo;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;

import br.com.kunden.movies_catalog.R;
import br.com.kunden.movies_catalog.models.Movie;
import br.com.kunden.movies_catalog.presenters.MoviePresenter;
import br.com.kunden.movies_catalog.ui.MyMovies.MyMoviesFragment;


public class MovieInfo extends AppCompatActivity {

    private Movie _movieToShow;
    private MoviePresenter moviePresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_movie_info);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setTitle("Movies Catalog");


        _movieToShow = (Movie) getIntent().getSerializableExtra("movie");
        boolean Button = getIntent().getBooleanExtra("AddButton", false);

        if(Button){
            findViewById(R.id.btn_addToList).setVisibility(View.VISIBLE);
        }

        ImageView image = findViewById(R.id.posterMovie);
        if(!_movieToShow.getImage().contains("poster")){
            Glide.with(this)
                    .load("https://image.tmdb.org/t/p/w100" + _movieToShow.getImage())
                    .placeholder(R.drawable.placeholder_poster_movie)
                    .into(image);
        }else{
            image.setImageBitmap(BitmapFactory.decodeFile(_movieToShow.getImage()));
        }


        TextView title =  findViewById(R.id.txt_title);
        title.setText(_movieToShow.getTitle());

        TextView year =  findViewById(R.id.txt_year);
        year.setText(_movieToShow.getYear());

        TextView description =  findViewById(R.id.txt_description);
        description.setText(_movieToShow.getDescricao());

        TextView genre =  findViewById(R.id.txt_genre);
        genre.setText(_movieToShow.getGenre());
        findViewById(R.id.btn_addToList).setOnClickListener(v -> {
            moviePresenter = new MoviePresenter(this);
            moviePresenter.insertMovie(_movieToShow);
            Toast.makeText(this, "The movie has been added to your movie list", Toast.LENGTH_SHORT).show();
            findViewById(R.id.btn_addToList).setClickable(false);
            findViewById(R.id.btn_addToList).setBackgroundColor(Color.GRAY);
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
            default:break;
        }
        return true;
    }

}