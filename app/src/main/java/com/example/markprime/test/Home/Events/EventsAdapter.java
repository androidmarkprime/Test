package com.example.markprime.test.Home.Events;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.example.markprime.test.Model.EventObject;
import com.example.markprime.test.R;
import com.example.markprime.test.utils.SharedPrefs;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class EventsAdapter extends RecyclerView.Adapter<EventsAdapter.ViewHolder> {

    private Context context;
    private List<EventObject> eventObjects;
    private EventsAdapterListener eventsAdapterListener;
    SharedPrefs sharedPrefs;

    public EventsAdapter (Context context, List<EventObject> eventObjects, EventsAdapterListener eventsAdapterListener) {
        this.context = context;
        this.eventObjects = eventObjects;
        this.eventsAdapterListener = eventsAdapterListener;
        sharedPrefs = new SharedPrefs();
    }

    @NonNull
    @Override
    public EventsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.model_event_list, parent, false);
        return new EventsAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {

        Picasso.get()
                .load(eventObjects.get(position).getImageUrl())
                .noFade()
                .into(holder.event_image);
        holder.event_name.setText(eventObjects.get(position).getEventName());
        try {
            holder.event_date.setText(getFormattedDate(eventObjects.get(position).getDate()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        holder.event_venue_name.setText(eventObjects.get(position).getVenueName());
        holder.event_venue_town.setText(eventObjects.get(position).getVenueTown());

        holder.ll_parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                eventsAdapterListener.eventClicked(eventObjects.get(holder.getAdapterPosition()));
            }
        });

    }


    @Override
    public int getItemCount() {
        return eventObjects.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView event_image;
        private TextView event_name;
        private TextView event_date;
        private TextView event_venue_name;
        private TextView event_venue_town;

        private LinearLayout ll_parent;


        public ViewHolder(View itemView) {
            super(itemView);

            event_image = itemView.findViewById(R.id.event_image);
            event_name = itemView.findViewById(R.id.event_name);
            event_date = itemView.findViewById(R.id.event_date);
            event_venue_name = itemView.findViewById(R.id.event_venue_name);
            event_venue_town = itemView.findViewById(R.id.event_venue_town);

            ll_parent = itemView.findViewById(R.id.ll_parent);
        }
    }

    private String getFormattedDate(String dateString) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.UK);
        Date newDate = format.parse(dateString);
        format = new SimpleDateFormat("EEEE dd MMMM yyyy", Locale.UK);
        return format.format(newDate);
    }


    public EventObject getItem(int position) {
        return eventObjects.get(position);
    }


    public long getItemId(int position) {
        return 0;
    }

    /*Checks whether a particular events exists in SharedPreferences*/
    public boolean checkEventItem(EventObject checkEvent) {
        boolean check = false;
        List<EventObject> saved = sharedPrefs.getEvents(context);
        if (saved != null) {
            for (EventObject eventObject : saved) {
                if (eventObject.equals(checkEvent)) {
                    check = true;
                    break;
                }
            }
        }
        return check;
    }


    public void add(EventObject eventObject) {
        eventObjects.add(eventObject);
        notifyDataSetChanged();
    }


    public void remove(EventObject eventObject) {
        eventObjects.remove(eventObject);
        notifyDataSetChanged();
    }

}
