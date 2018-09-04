package com.example.markprime.test.EventDetails;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.markprime.test.Model.GenreObject;
import com.example.markprime.test.R;

import java.util.List;

public class GenreListAdapter extends RecyclerView.Adapter<GenreListAdapter.ViewHolder> {

    private Context context;
    private List<GenreObject> genreObjects;
    private GenreListAdapterListener genreListAdapterListener;

    public GenreListAdapter (Context context, List<GenreObject> genreObjects){
        this.context = context;
        this.genreObjects = genreObjects;

    }



    @NonNull
    @Override
    public GenreListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.model_genre_detail, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GenreListAdapter.ViewHolder holder, int position) {

        if(genreObjects.get(position).getGenreName().isEmpty()){
            holder.genre_name.setVisibility(View.INVISIBLE);
        }else{
          holder.genre_name.setText(genreObjects.get(position).getGenreName());
        }
    }

    @Override
    public int getItemCount() {
        return genreObjects.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView genre_name;

        public ViewHolder(View itemView) {
            super(itemView);

            genre_name = itemView.findViewById(R.id.genre_name);

        }
    }
}
