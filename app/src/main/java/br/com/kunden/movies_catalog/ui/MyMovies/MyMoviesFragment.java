package br.com.kunden.movies_catalog.ui.MyMovies;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import br.com.kunden.movies_catalog.databinding.FragmentHomeBinding;
import br.com.kunden.movies_catalog.interfaces.IRecyclerView;
import br.com.kunden.movies_catalog.models.Movie;
import br.com.kunden.movies_catalog.views.adapter.MyMovieAdapter;
import br.com.kunden.movies_catalog.ui.MyMovies.factory.MyMoviesFactory;
import br.com.kunden.movies_catalog.ui.movieEdit.MovieEdit;
import br.com.kunden.movies_catalog.ui.movieInfo.MovieInfo;

public class MyMoviesFragment extends Fragment implements IRecyclerView {

    private FragmentHomeBinding binding;
    private MyMoviesViewModel viewModel;
    private MyMovieAdapter adapter;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    public void onViewCreated(View view, Bundle savedInstance){
        super.onViewCreated(view, savedInstance);

        MyMoviesFactory factory = new MyMoviesFactory(requireContext());
        viewModel = new ViewModelProvider(this, factory).get(MyMoviesViewModel.class);

        adapter = new MyMovieAdapter(this);
        binding.recyclerMovies.setLayoutManager(new LinearLayoutManager(requireContext()));
        binding.recyclerMovies.setHasFixedSize(true);
        binding.recyclerMovies.setAdapter(adapter);

        viewModel.getFilteredMovies().observe(getViewLifecycleOwner(), movies -> {
            adapter.setMovieList(movies);
        });

        viewModel.loadMovies();

        binding.btnFilterMovies.setOnClickListener(v -> {
            String filter = binding.txtFilter.getText().toString();
            viewModel.filterMovies(filter);
        });

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void OnItemClick(int position) {
        Intent intent = new Intent(getContext(), MovieInfo.class);
        Movie movieToSend = adapter.getMovieAt(position);
        intent.putExtra("movie", movieToSend);
        startActivity(intent);
    }

    @Override
    public void OnDeleteClick(int position) {
        Movie movieToDelete = adapter.getMovieAt(position);
        boolean result;
        result =  viewModel.deleteMovie(movieToDelete.getID());
        if(result){
            Toast.makeText(getContext(), "The movie has been deleted", Toast.LENGTH_SHORT).show();
            viewModel.loadMovies();
        }else{
            Toast.makeText(getContext(), "Ocurred an error", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void OnEditClick(int position) {
        Intent intent = new Intent(getContext(), MovieEdit.class);
        Movie movieToEdit = adapter.getMovieAt(position);
        intent.putExtra("movie", movieToEdit);
        startActivity(intent);
    }
}