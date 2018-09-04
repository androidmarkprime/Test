package com.example.markprime.test.EventDetails;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.markprime.test.Model.ArtistObject;
import com.example.markprime.test.R;


import java.util.List;
import java.util.Objects;

import static android.view.View.INVISIBLE;

public class ArtistListAdapter extends RecyclerView.Adapter<ArtistListAdapter.ViewHolder>{

    private Context context;
    private List<ArtistObject> artistObjects;

    public ArtistListAdapter (Context context, List<ArtistObject> artistObjects){
        this.context = context;
        this.artistObjects = artistObjects;

    }

    @NonNull
    @Override
    public ArtistListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.model_artist_detail, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ArtistListAdapter.ViewHolder holder, int position) {
//        if(artistObjects.get(position).getArtistImage().isEmpty()){
//            holder.artist_image.setVisibility(INVISIBLE);
//        } else {
//            Picasso.get()
//                    .load(artistObjects.get(position).getArtistImage())
//                    .noFade()
//                    .into(holder.artist_image);
//        }

        if(artistObjects.get(position).getArtistName().isEmpty()){
            holder.artist_name.setVisibility(INVISIBLE);
        }else {
            holder.artist_name.setText(artistObjects.get(position).getArtistName());
        }


        if(artistObjects.get(position).getSpotifymp3().isEmpty())
            holder.artist_spotify.setVisibility(INVISIBLE);

    }

    @Override
    public int getItemCount() {
        return artistObjects.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

//       private ImageView artist_image;
       private TextView artist_name;
       private ImageView artist_spotify;
       private LinearLayout ll_artist_parent;


        public ViewHolder(View itemView) {
            super(itemView);

            artist_name = itemView.findViewById(R.id.artist_name);
            artist_spotify = itemView.findViewById(R.id.artist_spotify);
            ll_artist_parent = itemView.findViewById(R.id.ll_artist_parent);
        }


    }

}
