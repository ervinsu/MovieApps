package com.ervin.moviecatalogue.ui.main;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ervin.moviecatalogue.R;
import com.ervin.moviecatalogue.viewmodel.ViewModelFactory;

/**
 * A placeholder fragment containing a simple view.
 */
public class HomeListFilmsFragment extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";
    private static final String TAG = HomeListFilmsFragment.class.getSimpleName();
    private int index = 1;
    private PageViewModel pageViewModel;
    private ProgressBar progressBar;

    static HomeListFilmsFragment newInstance(int index) {
        HomeListFilmsFragment fragment = new HomeListFilmsFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_SECTION_NUMBER, index);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        pageViewModel = ViewModelProviders.of(this).get(PageViewModel.class);
        pageViewModel = obtainViewModel(getActivity());
        if (getArguments() != null) {
            index = getArguments().getInt(ARG_SECTION_NUMBER);
        }
    }

    @NonNull
    private static PageViewModel obtainViewModel(FragmentActivity activity) {
        // Use a Factory to inject dependencies into the ViewMode
        ViewModelFactory factory = ViewModelFactory.getInstance();
        return ViewModelProviders.of(activity, factory).get(PageViewModel.class);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);
        progressBar = root.findViewById(R.id.pbMainFragment);
        RecyclerView rvFilm = root.findViewById(R.id.rvListMovie);
        rvFilm.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.VERTICAL,false));
        ListFilmAdapter adapter = new ListFilmAdapter(getActivity(), index);
        rvFilm.setAdapter(adapter);

        if (index==0){
            pageViewModel.getFilmsMovie().observe(this,
                    filmModels -> {
                        if(filmModels.size()!=0) {
                            Log.d("filmmovienyasize", filmModels.size() + "");
                            progressBar.setVisibility(View.GONE);
                            adapter.setFilms(filmModels);
                        }
                    });
//            adapter.setCasters(pageViewModel.getFilmsMovie());
        }
        else {
            pageViewModel.getFilmsSeries().observe(this,
                    filmModels -> {
                        if(filmModels.size()!=0) {
                            progressBar.setVisibility(View.GONE);
                            adapter.setFilms(filmModels);
                        }
                    });
        }

        return root;
    }
}