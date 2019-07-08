package com.ervin.moviecatalogue.ui.detail;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.request.RequestOptions;
import com.ervin.moviecatalogue.R;
import com.ervin.moviecatalogue.utils.GlideApp;
import com.ervin.moviecatalogue.viewmodel.ViewModelFactory;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Objects;

public class DetailFilmActivity extends AppCompatActivity {

    public static final String ID_DETAIL_FILM = "ID_DETAIL_FILM";
    public static final String FILM_TYPE = "FILM_TYPE";

    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_film);
        Toolbar toolbar = findViewById(R.id.toolbar);
        ImageView ivBgFilm = findViewById(R.id.ivBgFilm);
        View includeLayout = findViewById(R.id.includeLayout);
        ImageView ivFilmPhoto = includeLayout.findViewById(R.id.ivFilmPhoto);
        ProgressBar progressBar = findViewById(R.id.pbDetail);
        TextView tvFilmGenre = includeLayout.findViewById(R.id.tvGenre);
        TextView tvFilmRating = includeLayout.findViewById(R.id.tvRating);
        TextView tvFilmSinopsis = includeLayout.findViewById(R.id.tvSinopsis);
        CasterFilmAdapter adapter = new CasterFilmAdapter();
        RecyclerView rvCasters = includeLayout.findViewById(R.id.rvCasters);
        rvCasters.setAdapter(adapter);
        rvCasters.setLayoutManager(new LinearLayoutManager(this,RecyclerView.HORIZONTAL,false));

        DetailFilmViewModel detailFilmViewModel = obtainViewModel(this);
        detailFilmViewModel.setFilmType(getIntent().getIntExtra(FILM_TYPE,0));
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String filmID = extras.getString(ID_DETAIL_FILM);
            if (filmID != null) {
                detailFilmViewModel.setFilmID(filmID);
                if (detailFilmViewModel.getFilm() != null) {
                    detailFilmViewModel.getFilm().observe(this,
                        filmModel -> {
                            progressBar.setVisibility(View.GONE);
                            Log.d("detailmovie",filmModel.getFilmName());
                            toolbar.setTitle(filmModel.getFilmName());
                            StringBuilder genre = new StringBuilder();
                            for (int i = 0; i < filmModel.getFimGenre().size(); i++) {
                                if (i != 0) {
                                    genre.append(", ");
                                }
                                genre.append(filmModel.getFimGenre().get(i));
                            }
                            tvFilmGenre.setText(genre.toString());
                            tvFilmRating.setText(filmModel.getFilmRating());
                            tvFilmSinopsis.setText(filmModel.getFilmSinopsis());
                            GlideApp.with(this)
                                    .load(filmModel.getFilmBackdrop())
                                    .apply(RequestOptions.placeholderOf(R.drawable.ic_loading).error(R.drawable.ic_error))
                                    .into(ivBgFilm);

                            GlideApp.with(this)
                                    .load(filmModel.getFilmImage())
                                    .apply(RequestOptions.placeholderOf(R.drawable.ic_loading).error(R.drawable.ic_error))
                                    .into(ivFilmPhoto);
                            setSupportActionBar(toolbar);
                            Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
                        });

                    detailFilmViewModel.getCasters().observe(this,
                            adapter::setCasters);

                }
            }
        }
//        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fabFavorite);
        fab.setOnClickListener(view -> Toast.makeText(DetailFilmActivity.this, R.string.LOVED_STRING, Toast.LENGTH_SHORT).show());
//        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
    }

    @NonNull
    private static DetailFilmViewModel obtainViewModel(FragmentActivity activity) {
        // Use a Factory to inject dependencies into the ViewModel
        ViewModelFactory factory = ViewModelFactory.getInstance();
        return ViewModelProviders.of(activity, factory).get(DetailFilmViewModel.class);
    }
}
