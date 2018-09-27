package com.example.markprime.test.EventDetails;


import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.LinkProperties;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.andexert.library.RippleView;
import com.example.markprime.test.FragmentInteractionListener;
import com.example.markprime.test.Model.ArtistObject;
import com.example.markprime.test.Model.EventObject;
import com.example.markprime.test.Model.GenreObject;
import com.example.markprime.test.R;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;


public class EventDetailsFragment extends Fragment implements EventDetailsAdapterListener{

    private Context context;
    private FragmentInteractionListener fragmentInteractionListener;
    private LinearLayout venue_phone_group;
    private ImageView event_details_image, share_messenger, share_whatsApp,
            share_fb, artist_spotify, share_general, share_twitter;
    private TextView event_name, event_min_age, doors_last_entry_info_1,
            event_date, event_description, doors_open_info, doors_open,
            doors_close_info, doors_close, doors_last_entry_info, last_entry,
            venue_name, venue_add, venue_town, venue_pc, venue_phone, artist_info,
            genre_info;
    private RecyclerView re_artist_details, re_genre_details;
    private List<ArtistObject> artistList = new ArrayList<>();
    private List<GenreObject> genreList = new ArrayList<>();
    private ArtistListAdapter artistListAdapter;
    private GenreListAdapter genreListAdapter;
    private Button buy_tickets_button;

    private static final String EVENT_OBJECT = "event_object";
    private EventObject eventObject;
    private boolean artists = false;
    private boolean genres = false;


    public EventDetailsFragment() {
        // Required empty public constructor
    }

    public static EventDetailsFragment newInstance(JSONObject eventDetails) {
        EventDetailsFragment fragment = new EventDetailsFragment();
        Bundle args = new Bundle();
        args.putString(EVENT_OBJECT, eventDetails.toString());
        fragment.setArguments(args);
        return fragment;


    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_event_details, container, false);


        setupTextViews(view);
        setupImageViews(view);
        setupLayoutViews(view);
        setAdapters();
        setupButton(view);

        return view;
    }

    private void setupButton(View view) {
        buy_tickets_button = view.findViewById(R.id.buy_tickets_button);

        buy_tickets_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buyClicked(eventObject);
            }
        });
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
        fragmentInteractionListener = (FragmentInteractionListener) context;

        if (getArguments() != null) {
            try {
                eventObject = new EventObject(new JSONObject(getArguments().getString(EVENT_OBJECT)));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();

    }

    private void setupImageViews(View view) {
        event_details_image = view.findViewById(R.id.event_details_image);

        Picasso.get()
                .load(eventObject.getImageUrl())
                .noFade()
                .into(event_details_image);


        artist_spotify = view.findViewById(R.id.artist_spotify);
        share_messenger = view.findViewById(R.id.share_messenger);
        share_whatsApp = view.findViewById(R.id.share_whatsApp);
        share_fb = view.findViewById(R.id.share_fb);
        share_twitter = view.findViewById(R.id.share_twitter);
        share_general = view.findViewById(R.id.share_general);


        share_messenger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                shareEvent("com.facebook.orca", "Check Out this event", getContext()); }

        });

        share_whatsApp.setOnClickListener(new View.OnClickListener(){
           @Override
           public void onClick(View view){
           shareEvent("com.whatsapp", "Check out this event", getContext());
           }
        });

        share_fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shareEvent("com.facebook.katana", "Check out this event", getContext());
            }
        });

        share_twitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shareEvent("com.twitter.android", "Check out this Event", getContext());
            }
        });

        share_general.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chooseShare();
            }
        });



    }


    private void setupTextViews(View view) {
        event_name = view.findViewById(R.id.event_name);
        event_date = view.findViewById(R.id.event_date);
        event_description = view.findViewById(R.id.event_description);
        doors_open_info = view.findViewById(R.id.doors_open_info);
        doors_open = view.findViewById(R.id.doors_open);
        doors_close_info = view.findViewById(R.id.doors_close_info);
        doors_close = view.findViewById(R.id.doors_close);
        doors_last_entry_info = view.findViewById(R.id.doors_last_entry_info);
        doors_last_entry_info_1 = view.findViewById(R.id.doors_last_entry_info_1);
        last_entry = view.findViewById(R.id.last_entry);
        event_min_age = view.findViewById(R.id.event_min_age);
        venue_name = view.findViewById(R.id.venue_name);
        venue_add = view.findViewById(R.id.venue_add);
        venue_town = view.findViewById(R.id.venue_town);
        venue_pc = view.findViewById(R.id.venue_pc);
        venue_phone_group = view.findViewById(R.id.venue_phone_group);
        venue_phone = view.findViewById(R.id.venue_phone);
        artist_info = view.findViewById(R.id.artist_info);
        genre_info = view.findViewById(R.id.genre_info);

        event_name.setText(eventObject.getEventName());


        try {
            event_date.setText(getFormattedDate(eventObject.getDate()));
        } catch (ParseException e) {
            e.printStackTrace();
        }


        event_description.setText(eventObject.getDescription());
        doors_open.setText(eventObject.getDoorsOpen());
        doors_close.setText(eventObject.getDoorsClose());

        if (eventObject.getLastEntry().isEmpty()) {
            doors_last_entry_info.setVisibility(View.INVISIBLE);
            doors_last_entry_info_1.setVisibility(View.INVISIBLE);
            last_entry.setVisibility(View.INVISIBLE);
        } else {
            last_entry.setText(eventObject.getLastEntry());
        }

        event_min_age.setText(eventObject.getMinAge());
        venue_name.setText(eventObject.getVenueName());
        venue_add.setText(eventObject.getVenueAdd());
        venue_town.setText(eventObject.getVenueTown());
        venue_pc.setText(eventObject.getVenuePostCode());

        if (eventObject.getVenuePhone().isEmpty()) {
            venue_phone_group.setVisibility(View.INVISIBLE);
        } else {
            venue_phone.setText(eventObject.getVenuePhone());
        }

        if (eventObject.getArtists().isNull(0)) {
            artist_info.setVisibility(View.INVISIBLE);
        } else {
            artist_info.setVisibility(View.VISIBLE);
        }


    }

    private void setupLayoutViews(View view) {

        re_artist_details = view.findViewById(R.id.re_artist_details);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        re_artist_details.setLayoutManager(linearLayoutManager);

        re_genre_details = view.findViewById(R.id.re_genre_details);
        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(context);
        linearLayoutManager1.setOrientation(LinearLayoutManager.HORIZONTAL);
        re_genre_details.setLayoutManager(linearLayoutManager1);
    }

    private void setAdapters() {
        artistListAdapter = new ArtistListAdapter(context, artistList);
        re_artist_details.setAdapter(artistListAdapter);

        getArtists();

        genreListAdapter = new GenreListAdapter(context, genreList);
        re_genre_details.setAdapter(genreListAdapter);

        getGenres();
    }

    private void getArtists() {
        if (artists == false) {
            JSONArray jsonArray = eventObject.getArtists();
            for (int i = 0; i < jsonArray.length(); i++) {
                try {
                    artistList.add(new ArtistObject(jsonArray.getJSONObject(i)));
                    artists = true;
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        } else return;
    }

    private void getGenres() {
        if (genres == false) {
            JSONArray jsonArray = eventObject.getGenres();
            for (int i = 0; i < jsonArray.length(); i++) {
                try {
                    genreList.add(new GenreObject(jsonArray.getJSONObject(i)));
                    genres = true;
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

        }
    }

    private String getFormattedDate(String dateString) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.UK);
        Date newDate = format.parse(dateString);
        format = new SimpleDateFormat("EEEE dd MMMM yyyy", Locale.UK);
        return format.format(newDate);
    }



    public static void shareEvent(String packageName, String message, Context context) {
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, message);
        sendIntent.setType("text/plain");
        sendIntent.setPackage(packageName);
        try {
            context.startActivity(sendIntent);
        } catch (android.content.ActivityNotFoundException ex) {
            ex.printStackTrace();
            try {
                context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + packageName)));
            } catch (android.content.ActivityNotFoundException anfe) {
                context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + packageName)));
            }
        }
    }

    public void chooseShare(){
        Intent sendIntent = new Intent(Intent.ACTION_SEND);
        sendIntent.setType("text/plain");
        sendIntent.putExtra(Intent.EXTRA_TEXT, "SOME TEXT");
        String title = "Event";

        startActivity(Intent.createChooser(sendIntent, "Share via.."));
    }

    @Override
    public void buyClicked(EventObject eventObject) {
        fragmentInteractionListener.openCheckOutFragment(eventObject.getFullObject());
    }
}