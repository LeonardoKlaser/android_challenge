package br.com.kunden.movies_catalog.ui.movieEdit;

import android.app.Activity;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

import br.com.kunden.movies_catalog.R;
import br.com.kunden.movies_catalog.models.Movie;
import br.com.kunden.movies_catalog.presenters.MoviePresenter;
import br.com.kunden.movies_catalog.utils.StringUtils;
import br.com.kunden.movies_catalog.utils.UriUtils;

public class MovieEdit extends AppCompatActivity {

    private Movie _movieToShow;
    MoviePresenter moviePresenter;
    String[] item = {"Ação", "Comédia", "Drama", "Ficção", "Terror", "Romance"};
    List<String> years = new ArrayList<>();
    AutoCompleteTextView autoCompleteTextView;
    AutoCompleteTextView autoCompleteTextViewYear;
    ArrayAdapter<String> adapterItems;
    ArrayAdapter<String> adapterYears;
    String Image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_movie_edit);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        _movieToShow = (Movie) getIntent().getSerializableExtra("movie");

        EditText title = findViewById(R.id.edit_title);
        EditText description = findViewById(R.id.edit_description);
        autoCompleteTextView = findViewById(R.id.dropdownGenre);
        autoCompleteTextViewYear = findViewById(R.id.dropdownYears);

        adapterItems = new ArrayAdapter<>(this, R.layout.list_item, item);
        for (int i = 1990; i <= 2025; i++) {
            years.add(String.valueOf(i));
        }
        adapterYears = new ArrayAdapter<>(this, R.layout.list_item, years);
        autoCompleteTextView.setAdapter(adapterItems);
        autoCompleteTextViewYear.setAdapter(adapterYears);

        title.setText(_movieToShow.getTitle());
        description.setText(_movieToShow.getDescricao());
        autoCompleteTextViewYear.setText(_movieToShow.getYear());
        autoCompleteTextView.setText(_movieToShow.getGenre());

        moviePresenter = new MoviePresenter(this);

        autoCompleteTextView.setOnClickListener(v -> autoCompleteTextView.showDropDown());
        autoCompleteTextViewYear.setOnClickListener(v -> autoCompleteTextViewYear.showDropDown());


        Button btnPoster = findViewById(R.id.btn_editPoser);
        btnPoster.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
            startActivityForResult(Intent.createChooser(intent, "Choose a poster movie"), 1);
        });


        findViewById(R.id.btn_editMovie).setOnClickListener(v -> {
            boolean result;
            Movie movieToEdit = new Movie(
                    _movieToShow.getID(),
                    title.getText().toString(),
                    autoCompleteTextViewYear.getText().toString(),
                    autoCompleteTextView.getText().toString(),
                    description.getText().toString(),
                    Image
            );
            if(StringUtils.isNullOrBlank(movieToEdit.getTitle()) ||
                    StringUtils.isNullOrBlank(movieToEdit.getYear()) ||
                    StringUtils.isNullOrBlank(movieToEdit.getGenre()) ||
                    StringUtils.isNullOrBlank(movieToEdit.getDescricao())){
                Toast.makeText(this, "Fill in all fields", Toast.LENGTH_SHORT).show();
            }else{
                result = moviePresenter.edit(
                        movieToEdit
                );
                if(result){
                    Toast.makeText(this , "The movie has been edited", Toast.LENGTH_SHORT).show();
                    finish();
                }else{
                    Toast.makeText(this, "Ocurred an error", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void onActivityResult(int RequestCode, int ResultCode, Intent dados){
        super.onActivityResult(RequestCode, ResultCode, dados);
        if(ResultCode == Activity.RESULT_OK){
            if(RequestCode == 1){
                String filePath = UriUtils.copyUriToInternalStorage(this, dados.getData(), "poster_" + System.currentTimeMillis() + ".jpg");
                if (filePath != null) {
                    ImageView poster = findViewById(R.id.img_posterMovier);
                    poster.setImageBitmap(BitmapFactory.decodeFile(filePath));
                    Image = filePath;
                }
            }
        }
    }
}