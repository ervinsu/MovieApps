package com.ervin.moviecatalogue.ui.detail;

import android.annotation.SuppressLint;
import android.os.Bundle;

import com.bumptech.glide.request.RequestOptions;
import com.ervin.moviecatalogue.model.FilmModel;
import com.ervin.moviecatalogue.utils.GlideApp;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProviders;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ervin.moviecatalogue.R;
import com.google.android.material.snackbar.Snackbar;

import java.util.Objects;

public class DetailFilmActivity extends AppCompatActivity {

    public static final String ID_DETAIL_FILM = "ID_DETAIL_FILM";

    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_film);
        Toolbar toolbar = findViewById(R.id.toolbar);
        ImageView ivBgFilm = findViewById(R.id.ivBgFilm);
        View includeLayout = findViewById(R.id.includeLayout);
        ImageView ivFilmPhoto = includeLayout.findViewById(R.id.ivFilmPhoto);
        TextView tvFilmGenre = includeLayout.findViewById(R.id.tvGenre);
        TextView tvFilmDuration = includeLayout.findViewById(R.id.tvDuration);
        TextView tvFilmRating = includeLayout.findViewById(R.id.tvRating);
        TextView tvFilmSinopsis = includeLayout.findViewById(R.id.tvSinopsis);
        DetailFilmViewModel detailFilmViewModel = ViewModelProviders.of(this).get(DetailFilmViewModel.class);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String filmID = extras.getString(ID_DETAIL_FILM);
            if (filmID != null) {
                detailFilmViewModel.setFilmID(filmID);
                if(detailFilmViewModel.getFilm()!=null){
                    FilmModel filmModel = detailFilmViewModel.getFilm();
                    toolbar.setTitle(filmModel.getFilmName());
                    tvFilmDuration.setText(filmModel.getFilmDuration());
                    StringBuilder genre= new StringBuilder();
                    for(int i=0;i<filmModel.getFimGenre().size();i++){
                        if(i!=0){
                            genre.append(", ");}
                        genre.append(filmModel.getFimGenre().get(i));
                    }
                    tvFilmGenre.setText(genre.toString());
                    tvFilmRating.setText(filmModel.getFilmRating());
                    tvFilmSinopsis.setText(filmModel.getFilmSinopsis());
                    GlideApp.with(this)
                            .load(filmModel.getFilmImage())
                            .apply(RequestOptions.placeholderOf(R.drawable.ic_loading).error(R.drawable.ic_error))
                            .into(ivBgFilm);

                    GlideApp.with(this)
                            .load(filmModel.getFilmImage())
                            .apply(RequestOptions.placeholderOf(R.drawable.ic_loading).error(R.drawable.ic_error))
                            .into(ivFilmPhoto);

                }
            }
        }
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fabFavorite);
        fab.setOnClickListener(view -> Toast.makeText(DetailFilmActivity.this, "Loved", Toast.LENGTH_SHORT).show());
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
    }
}
