package com.example.markprime.test.Home.MyTickets;

import android.content.Context;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.andexert.library.RippleView;
import com.example.markprime.test.FragmentInteractionListener;
import com.example.markprime.test.Home.Events.EventsAdapter;
import com.example.markprime.test.Model.ArtistObject;
import com.example.markprime.test.Model.DeliveryObject;
import com.example.markprime.test.Model.EventObject;
import com.example.markprime.test.R;
import com.example.markprime.test.utils.SharedPrefs;
import com.github.siyamed.shapeimageview.RoundedImageView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class MyTicketsFragment extends Fragment {

    private RecyclerView re_my_tickets;
    private Context context;
    private List<EventObject> ticketList = new ArrayList<>();
    private MyTicketsAdapter myTicketsAdapter;
    private FragmentInteractionListener fragmentInteractionListener;
    private RoundedImageView iv_event_image, iv_qrcode;
    private RelativeLayout rl_ticket_item_main, rl_ticket_details;
    private ConstraintLayout cl_parent;
    private TextView tv_event_name, tv_event_date, tv_barcode, tv_ticket_number,
            tv_ticket_type, tv_customer_name, tv_ticket_price;

    private static final String EVENT_OBJECT = "event_object";
    private EventObject eventObject;
    SharedPrefs sharedPrefs;
    private boolean showTicket = false;


    public MyTicketsFragment() {
        // Required empty public constructor
    }

    public static MyTicketsFragment newInstance() {
        MyTicketsFragment fragmentTwo = new MyTicketsFragment();
        Bundle args = new Bundle();
        fragmentTwo.setArguments(args);
        return fragmentTwo;
    }

    public static MyTicketsFragment newInstance(JSONObject eventDetails) {
        MyTicketsFragment fragmentTwo = new MyTicketsFragment();
        Bundle args = new Bundle();
        args.putString(EVENT_OBJECT, eventDetails.toString());
        fragmentTwo.setArguments(args);
        return fragmentTwo;
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my_tickets, container, false);

        sharedPrefs = new SharedPrefs();
        ticketList = sharedPrefs.getTickets(context);


        if(ticketList !=null){
            setupRecycler(view);

            setAdapter();
            setViews(view);
        }else{

        }
//        loadTickets();

        return view;
    }

    private void setViews(View view){

        iv_event_image = view.findViewById(R.id.iv_event_image);
        iv_qrcode = view.findViewById(R.id.iv_qrcode);
        tv_event_name = view.findViewById(R.id.tv_event_name);
        tv_event_date = view.findViewById(R.id.tv_event_date);
        tv_barcode = view.findViewById(R.id.tv_barcode);
        tv_ticket_number = view.findViewById(R.id.tv_ticket_number);
        tv_ticket_type = view.findViewById(R.id.tv_ticket_type);
        tv_customer_name = view.findViewById(R.id.tv_customer_name);
        tv_ticket_price = view.findViewById(R.id.tv_ticket_price);

        rl_ticket_item_main = view.findViewById(R.id.rl_ticket_item_main);
        rl_ticket_details = view.findViewById(R.id.rl_ticket_details);

//        cl_parent = view.findViewById(R.id.cl_parent);
//
//        cl_parent.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick (View view){
//                rl_ticket_details.setVisibility(View.VISIBLE);
//            }
//        });

//        rl_ticket_item_main.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick (View view){
//                if(showTicket = false) {
//                    rl_ticket_details.setVisibility(View.VISIBLE);
//                    showTicket = true;
//                }else{
//                    rl_ticket_details.setVisibility(View.GONE);
//                    showTicket = false;
//                }
//            }
//        });



    }

    private void setAdapter() {
        myTicketsAdapter = new MyTicketsAdapter(context, ticketList);
        re_my_tickets.setAdapter(myTicketsAdapter);
    }

    private void loadTickets() {

        List<EventObject> eventObjects = ticketList;
        eventObjects.add(new EventObject(eventObject.getFullObject()));


    }

    private void setupRecycler(View view) {
        re_my_tickets= view.findViewById(R.id.re_my_tickets);
        LinearLayoutManager llm = new LinearLayoutManager(context);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        re_my_tickets.setLayoutManager(llm);
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



}
