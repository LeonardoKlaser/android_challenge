package br.com.kunden.movies_catalog.ui.newMovie;

import android.app.Activity;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import java.util.ArrayList;
import java.util.List;

import br.com.kunden.movies_catalog.R;
import br.com.kunden.movies_catalog.databinding.FragmentNotificationsBinding;
import br.com.kunden.movies_catalog.models.Movie;
import br.com.kunden.movies_catalog.ui.newMovie.factory.NewMovieFactory;
import br.com.kunden.movies_catalog.utils.StringUtils;
import br.com.kunden.movies_catalog.utils.UriUtils;

public class NewMovieFragment extends Fragment {

    private FragmentNotificationsBinding binding;
    NewMovieViewModel viewModel;
    String[] item = {"Ação", "Comédia", "Drama", "Ficção", "Terror", "Romance"};
    List<String> years = new ArrayList<>();
    AutoCompleteTextView autoCompleteTextView;
    AutoCompleteTextView autoCompleteTextViewYear;
    ArrayAdapter<String> adapterItems;
    ArrayAdapter<String> adapterYears;
    String image;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentNotificationsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        //region genres
        autoCompleteTextView = binding.dropdownGenre;
        adapterItems = new ArrayAdapter<String>(getContext(), R.layout.list_item, item);

        autoCompleteTextView.setAdapter(adapterItems);

        //endregion

        //region years
        for (int i = 1990; i <= 2025; i++) {
            years.add(String.valueOf(i));
        }
        autoCompleteTextViewYear = binding.dropdownYears;
        adapterYears = new ArrayAdapter<String>(getContext(), R.layout.list_item, years);
        autoCompleteTextViewYear.setAdapter(adapterYears);

        //endregion

        NewMovieFactory factory = new NewMovieFactory(requireContext());
        viewModel = new ViewModelProvider(this, factory).get(NewMovieViewModel.class);

        binding.btnMoviePoster.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
            startActivityForResult(Intent.createChooser(intent, "Choose a poster movie"), 1);
        });

        binding.btnInsertMovie.setOnClickListener(v -> {
            Movie movieToInsert = new Movie(
                    binding.inputTitle.getText().toString(),
                    binding.dropdownYears.getText().toString(),
                    binding.dropdownGenre.getText().toString(),
                    binding.inputDescription.getText().toString(),
                    image
            );
            if(StringUtils.isNullOrBlank(movieToInsert.getTitle()) ||
                    StringUtils.isNullOrBlank(movieToInsert.getYear()) ||
                    StringUtils.isNullOrBlank(movieToInsert.getGenre()) ||
                    StringUtils.isNullOrBlank(movieToInsert.getDescricao())){
                Toast.makeText(getContext(), "Fill in all fields", Toast.LENGTH_SHORT).show();
            }else{
                viewModel.insertMovie(
                        movieToInsert
                );
                binding.inputTitle.setText("");
                binding.dropdownYears.setText("");
                binding.dropdownGenre.setText("");
                binding.inputDescription.setText("");
                binding.moviePoster.setImageURI(null);
                binding.moviePoster.setImageBitmap(null);
                Toast.makeText(getContext(), "Movie inserted successfully", Toast.LENGTH_SHORT).show();
            }
        });
        return root;
    }

    public void onActivityResult(int RequestCode, int ResultCode, Intent dados){
        super.onActivityResult(RequestCode, ResultCode, dados);
        if(ResultCode == Activity.RESULT_OK){
            if(RequestCode == 1){
                String filePath = UriUtils.copyUriToInternalStorage(getContext(), dados.getData(), "poster_" + System.currentTimeMillis() + ".jpg");
                if (filePath != null) {
                    ImageView poster = binding.moviePoster;
                    poster.setImageBitmap(BitmapFactory.decodeFile(filePath));
                    image = filePath;
                }
            }
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}