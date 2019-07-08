package com.ervin.moviecatalogue.ui.detail;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.request.RequestOptions;
import com.ervin.moviecatalogue.R;
import com.ervin.moviecatalogue.data.source.local.model.CastingModel;
import com.ervin.moviecatalogue.utils.GlideApp;

import java.util.ArrayList;
import java.util.List;

public class CasterFilmAdapter extends RecyclerView.Adapter<CasterFilmAdapter.CasterFilmViewHolder> {

    private List<CastingModel> casters = new ArrayList<>();

    public List<CastingModel> getCasters() {
        return casters;
    }

    public void setCasters(List<CastingModel> casters) {
        if(this.casters.size()!=0){
            this.casters.clear();
        }
        this.casters = casters;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CasterFilmViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cv_list_caster, parent, false);
        return new CasterFilmAdapter.CasterFilmViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CasterFilmViewHolder holder, int position) {
        final CastingModel castingModel = casters.get(position);
        holder.tvCasterAs.setText(castingModel.getCastingAs());
        holder.tvCasterName.setText(castingModel.getCastingName());

        GlideApp.with(holder.itemView.getContext())
                .load(castingModel.getCastingPhoto())
                .apply(RequestOptions.placeholderOf(R.drawable.ic_loading).error(R.drawable.ic_error))
                .into(holder.ivCasterPhoto);
    }

    @Override
    public int getItemCount() {
        if(getCasters().size()==0)return 0;
        else return getCasters().size();
    }

    public class CasterFilmViewHolder extends RecyclerView.ViewHolder {
        TextView tvCasterName,tvCasterAs;
        ImageView ivCasterPhoto;
        public CasterFilmViewHolder(@NonNull View itemView) {
            super(itemView);

            ivCasterPhoto = itemView.findViewById(R.id.ivCasterPhoto);
            tvCasterName = itemView.findViewById(R.id.tvCasterName);
            tvCasterAs = itemView.findViewById(R.id.tvCasterAs);
        }
    }
}
