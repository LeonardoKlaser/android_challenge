package br.com.kunden.movies_catalog.ui.movieStore;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import java.util.ArrayList;
import java.util.List;

import br.com.kunden.movies_catalog.databinding.FragmentDashboardBinding;
import br.com.kunden.movies_catalog.dto.MovieDetailResponse;
import br.com.kunden.movies_catalog.dto.MovieResult;
import br.com.kunden.movies_catalog.dto.MovieSearchResponse;
import br.com.kunden.movies_catalog.interfaces.IRecyclerView;
import br.com.kunden.movies_catalog.models.Movie;
import br.com.kunden.movies_catalog.ui.movieInfo.MovieInfo;
import br.com.kunden.movies_catalog.utils.NetworkUtils;
import br.com.kunden.movies_catalog.views.adapter.MyMovieAdapter;

public class MovieStoreFragment extends Fragment implements IRecyclerView{

    private FragmentDashboardBinding binding;
    MovieStoreViewModel viewModel;
    private MyMovieAdapter adapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentDashboardBinding.inflate(inflater, container, false);

        return binding.getRoot();
    }

    public void onViewCreated(View view, Bundle savedInstance){
        super.onViewCreated(view, savedInstance);

        viewModel = new ViewModelProvider(this)
                .get(MovieStoreViewModel.class);

        adapter = new MyMovieAdapter(this);
        binding.recyclerMovieSearch.setLayoutManager(new LinearLayoutManager(requireContext()));
        binding.recyclerMovieSearch.setHasFixedSize(true);
        binding.recyclerMovieSearch.setAdapter(adapter);

        viewModel.getMovies().observe(getViewLifecycleOwner(), moviesReponse ->{
            if (moviesReponse != null && moviesReponse.getResults() != null) {
                adapter.setMovieList(toMovieList(moviesReponse));
            } else {
                adapter.setMovieList(new ArrayList<>());
                String error =  viewModel.getErrorMessage().toString();
                if(error != null){
                    Toast.makeText(getContext(), "Ocorreu um erro na busca: " + error, Toast.LENGTH_SHORT).show();
                }
            }
        });

        binding.btnCallTdmb.setOnClickListener(v -> {
            if (NetworkUtils.isNetworkAvailable(getContext())){
                String search = binding.txtSearchMovies.getText().toString();
                viewModel.searchedMovies(search);
            }else{
                Toast.makeText(getContext(), "Sem conexão com a internet", Toast.LENGTH_SHORT).show();
            }

        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public List<Movie> toMovieList(MovieSearchResponse result){
        List<Movie> response = new ArrayList<>();
        if (result == null || result.getResults() == null) return response;
        for(MovieResult movie : result.getResults()){
           Movie movieToPut = new Movie(
                   movie.getId(),
                   movie.getTitle(),
                   movie.getRelease_date(),
                   "",
                   movie.getOverview()
           );
           movieToPut.setImage("https://image.tmdb.org/t/p/w500" + movie.getPoster_path());
           response.add(movieToPut);
        }
        return response;
    }

    @Override
    public void OnItemClick(int position) {
        Intent intent = new Intent(getContext(), MovieInfo.class);
        Movie movieToSend = adapter.getMovieAt(position);
        viewModel.getGenreNames(movieToSend.getID()).observe(getViewLifecycleOwner(), genres -> {
            StringBuilder genresToAdd = new StringBuilder();
            for (int i = 0; i < genres.size(); i++) {
                if (i > 0) genresToAdd.append(", ");
                genresToAdd.append(genres.get(i).getName());
            }
            movieToSend.setGenre(genresToAdd.toString());
            intent.putExtra("movie", movieToSend);
            intent.putExtra("AddButton", true);
            startActivity(intent);
        });
    }

    @Override
    public void OnDeleteClick(int position) {

    }

    @Override
    public void OnEditClick(int position) {

    }
}