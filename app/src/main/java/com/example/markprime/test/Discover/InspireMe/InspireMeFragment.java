package com.example.markprime.test.Discover.InspireMe;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.markprime.test.FragmentInteractionListener;
import com.example.markprime.test.Model.InspireMeEventsObject;
import com.example.markprime.test.Network.NetworkManager;
import com.example.markprime.test.Network.VolleySingletonListener;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class InspireMeFragment extends Fragment
        implements InspireMeFragmentContract.View, InspireMeAdapterListener {

    private Context context;
    private FragmentInteractionListener fragmentInteractionListener;
    private InspireMeFragmentContract.Presenter presenter;

    private ImageView img_main_image, img_see_on_map;
    private TextView txt_description;
    private RecyclerView re_events;

    private InspireMeAdapter inspireMeAdapter;
    private List<InspireMeEventsObject> eventList = new ArrayList<>();

    private static final String FULL_JSON_STRING = "full_json_string";
    private JSONObject fullJsonObject;

    public InspireMeFragment() {
        // Required empty public constructor
    }

    public static InspireMeFragment newInstance(String fullJsonString) {
        InspireMeFragment fragment = new InspireMeFragment();
        Bundle args = new Bundle();
        args.putString(FULL_JSON_STRING, fullJsonString);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_inspire_me, container, false);

        presenter = new InspireMeFragmentPresenter(this);

//        fragmentInteractionListener.showNavBar();
//        fragmentInteractionListener.showBackIcon();

        setUpImageViews(view);
        setUpTextViews(view);
        setUpRecyclerViews(view);

        return view;
    }

    private void setUpImageViews(View view){
        img_main_image = view.findViewById(R.id.img_main_image);
        img_see_on_map = view.findViewById(R.id.img_see_on_map);
        try {
            Picasso.get().load(fullJsonObject.getJSONObject("image").getString("large")).into(img_main_image);
        } catch (Exception e){
            e.printStackTrace();
        }

        img_see_on_map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                fragmentInteractionListener.displayPartialEventMapFragment(inspireMeAdapter.getInspireMeEventsList());
            }
        });
    }

    private void setUpTextViews(View view){
        txt_description = view.findViewById(R.id.txt_description);
        try {
            txt_description.setText(Html.fromHtml(fullJsonObject.getString("intro")));
        } catch (Exception e){
            txt_description.setVisibility(View.GONE);
        }
    }

    private void setUpRecyclerViews(View view){
        re_events = view.findViewById(R.id.re_events);
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        re_events.setLayoutManager(layoutManager);

        try {
            inspireMeAdapter = new InspireMeAdapter(context,
                    presenter.buildInspireMeEventsList(fullJsonObject.getJSONArray("info")),
                    this);
        } catch (Exception e){
            e.printStackTrace();
        }
        re_events.setAdapter(inspireMeAdapter);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
        fragmentInteractionListener = (FragmentInteractionListener) context;
        if(getArguments() != null){
            try {
                fullJsonObject = new JSONObject(getArguments().getString(FULL_JSON_STRING));
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    @Override
    public void itemClicked(InspireMeEventsObject eventsModel) {

        NetworkManager.getInstance(context).exampleGetRequest(
                "https://www.skiddle.com/api/v1/events/search?api_key=008f1e6099ecc48e990e3776784d447b&platform=android&type=mobileapp&version=97&limit=40&offset=0&description=1&imagefilter=1&platform=android&order=date&radius=30&latitude=53.3994794&longitude=-2.524805&eventcode=4&aggs=genreids,eventcode", new VolleySingletonListener<JSONObject>() {
                    @Override
                    public void onResult(JSONObject object) {

                        try {
                            JSONArray jsonArray = object.getJSONArray("results");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                eventList.add(new InspireMeEventsObject(jsonArray.getJSONObject(i)));
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                });

////        fragmentInteractionListener.showLoader();
//        ApiService.getInstance(context).getEventDetails(eventsModel.getId(), new VolleySingletonListener<JSONObject>() {
//            @Override
//            public void onResult(JSONObject object) {
////                fragmentInteractionListener.dismissLoader();
//                try {
//                    fragmentInteractionListener.setEventProfileFrag(object.getJSONObject("results"));
//                } catch (Exception e){
//                    e.printStackTrace();
//                }
//            }
//        }, new VolleySingletonErrorListener() {
//            @Override
//            public void onErrorResult(VolleyError object) {
////                fragmentInteractionListener.dismissLoader();
//                if(object.getCause() instanceof UnknownHostException){
//                    goingToClickedNoConnection();
//                }
//            }
//        });
    }

    @Override
    public void goingToClicked(final InspireMeEventsObject eventsModel) {
//        try {
//            JSONObject jsonObject = new JSONObject();
//            jsonObject.put(Constants.ACCESSTOKEN,
//                    BaseActivity.prefs.getString(Constants.ACCESS_TOKEN, ""));
//            jsonObject.put("going", eventsModel.getGoingTo());
//            jsonObject.put("eventid", eventsModel.getId());
//            ApiService.getInstance(context).changeGoingToStatus(jsonObject, new VolleySingletonListener<JSONObject>() {
//                @Override
//                public void onResult(JSONObject object) {
//                    handleGoingToToastSuccess(eventsModel.getGoingTo());
//                }
//            }, new VolleySingletonErrorListener() {
//                @Override
//                public void onErrorResult(VolleyError object) {
//                    Toast.makeText(context, "Oops! Something went wrong", Toast.LENGTH_SHORT).show();
//                }
//            });
//        } catch (Exception e){
//            e.printStackTrace();
//        }
    }

    @Override
    public void goingToClickedNoConnection() {
//        fragmentInteractionListener.showSingleInfoDialog("Oops!",
//                "Please check your internet connection and try again");
    }

    private void handleGoingToToastSuccess(int status){
//        switch (status){
//            case 1:
//                Toast.makeText(context, "Going to this event", Toast.LENGTH_SHORT).show();
//                break;
//            case 9:
//                Toast.makeText(context, "Interested in this event", Toast.LENGTH_SHORT).show();
//                break;
//        }
    }
}
