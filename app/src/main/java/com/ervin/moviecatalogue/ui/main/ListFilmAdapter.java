package com.ervin.moviecatalogue.ui.main;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.request.RequestOptions;
import com.ervin.moviecatalogue.R;
import com.ervin.moviecatalogue.data.source.local.model.FilmModel;
import com.ervin.moviecatalogue.ui.detail.DetailFilmActivity;
import com.ervin.moviecatalogue.utils.GlideApp;

import java.util.ArrayList;
import java.util.List;

public class ListFilmAdapter extends RecyclerView.Adapter<ListFilmAdapter.ListFilmViewHolder> {

    private Activity activity;
    private List<FilmModel> films = new ArrayList<>();
    private int filmType;
    //filmType
    // 1 = movies
    // 2 = series

    public ListFilmAdapter(Activity activity, int filmType) {
        this.filmType = filmType;
        this.activity = activity;
    }

    public List<FilmModel> getFilms() {
        return films;
    }

    public void setFilms(List<FilmModel> films) {
        if(this.films.size()!=0){
            this.films.clear();
        }
        this.films = films;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ListFilmViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cv_list_film, parent, false);
        return new ListFilmViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListFilmViewHolder holder, int position) {
        final FilmModel filmModel = films.get(position);
        holder.tvFilmName.setText(filmModel.getFilmName());
        holder.tvFilmDuration.setText(filmModel.getFilmDuration());
        holder.tvFilmRating.setText(filmModel.getFilmRating());
        StringBuilder genre= new StringBuilder();
        for(int i=0;i<filmModel.getFimGenre().size();i++){
            if(i!=0){
                genre.append(", ");}
            genre.append(filmModel.getFimGenre().get(i));
        }
        holder.tvFilmGenre.setText(genre.toString());

        GlideApp.with(holder.itemView.getContext())
                .load(filmModel.getFilmImage())
                .apply(RequestOptions.placeholderOf(R.drawable.ic_loading).error(R.drawable.ic_error))
                .into(holder.ivFilmPhoto);

        holder.cardViewFilm.setOnClickListener(l->{
            Intent intent = new Intent(activity, DetailFilmActivity.class);
            intent.putExtra(DetailFilmActivity.FILM_TYPE, filmType);
            intent.putExtra(DetailFilmActivity.ID_DETAIL_FILM, filmModel.getFilmID());
            activity.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        if(getFilms().size()==0)return 0;
        else return getFilms().size();
    }

    class ListFilmViewHolder extends RecyclerView.ViewHolder{
        TextView tvFilmName,tvFilmDuration,tvFilmGenre,tvFilmRating;
        ImageView ivFilmPhoto;
        CardView cardViewFilm;
        ListFilmViewHolder(@NonNull View itemView) {
            super(itemView);
            cardViewFilm = itemView.findViewById(R.id.cvFilm);
            ivFilmPhoto = itemView.findViewById(R.id.ivFilmPhoto);
            tvFilmName = itemView.findViewById(R.id.tvFilmName);
            tvFilmDuration = itemView.findViewById(R.id.tvDuration);
            tvFilmGenre = itemView.findViewById(R.id.tvGenre);
            tvFilmRating = itemView.findViewById(R.id.tvRating);
        }
    }
}
