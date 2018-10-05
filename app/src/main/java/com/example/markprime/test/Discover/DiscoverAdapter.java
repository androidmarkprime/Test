package com.example.markprime.test.Discover;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Parcel;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.markprime.test.Model.DiscoverCarouselObject;
import com.example.markprime.test.Model.DiscoverObject;
import com.example.markprime.test.R;
import com.squareup.picasso.Picasso;

import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class DiscoverAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    private final int TYPE_CAROUSEL = 0;
    private final int TYPE_INSPIRE_ME = 1;
    private final int TYPE_SINGLE_EVENT = 2;

    private final String CAROUSEL = "carousel";
    private final String INSPIRE_ME = "singleinspireme";
    private final String SINGLE_EVENT = "single";

    private List<DiscoverObject> discoverObjectList;
    private Context context;

    private DiscoverAdaptersListener.DiscoverAdapterListener discoverAdapterListener;
    private DiscoverAdaptersListener.DiscoverCarouselListener carouselListener;

    DiscoverAdapter(List<DiscoverObject> discoverModelList, Context context,
                    DiscoverAdaptersListener.DiscoverAdapterListener discoverAdapterListener,
                    DiscoverAdaptersListener.DiscoverCarouselListener carouselListener) {
        this.discoverObjectList = discoverModelList;
        this.context = context;
        this.discoverAdapterListener = discoverAdapterListener;
        this.carouselListener = carouselListener;
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        switch (viewType) {
            case TYPE_CAROUSEL:
                return new CarouselViewHolder(inflater.inflate(
                        R.layout.item_discover_carousel, parent, false));
            case TYPE_INSPIRE_ME:
                return new InspireMeViewHolder(inflater.inflate(R.layout.item_discover_inspire,
                        parent, false));
            case TYPE_SINGLE_EVENT:
                return new SingleEvent(inflater.inflate(R.layout.item_discover_single_event,
                        parent, false));
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        holder.setContent(discoverObjectList.get(position), position);
    }

    @Override
    public int getItemCount() {
        return discoverObjectList.size();
    }

    @Override
    public int getItemViewType(int position) {
        switch (discoverObjectList.get(position).getmType()) {
            case CAROUSEL:
                return TYPE_CAROUSEL;
            case INSPIRE_ME:
                return TYPE_INSPIRE_ME;
            case SINGLE_EVENT:
                return TYPE_SINGLE_EVENT;
        }
        return super.getItemViewType(position);
    }

    class CarouselViewHolder extends BaseViewHolder {

        private TextView txt_carousel_label;
        private RecyclerView re_carousel_items;
        private Button btn_see_more;
        private LinearLayout ll_carousel_item;

        private DiscoverCarouselAdapter adapter;
        private List<DiscoverCarouselObject> carouselModelList = new ArrayList<>();

        CarouselViewHolder(View itemView) {
            super(itemView);
            txt_carousel_label = itemView.findViewById(R.id.txt_carousel_label);
            re_carousel_items = itemView.findViewById(R.id.re_carousel_items);
            btn_see_more = itemView.findViewById(R.id.btn_see_more);
            ll_carousel_item = itemView.findViewById(R.id.ll_carousel_item);

            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
            linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
            re_carousel_items.setLayoutManager(linearLayoutManager);
        }

        @Override
        public void setContent(final DiscoverObject content, final int position) {
            super.setContent(content, position);
            txt_carousel_label.setText(content.getmTitle());

            if (content.getmInfo() != null) {
                for (int i = 0; i < content.getmInfo().length(); i++) {
                    try {
                        carouselModelList.add(new DiscoverCarouselObject(content.getmInfo().getJSONObject(i)));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                adapter = new DiscoverCarouselAdapter(carouselModelList, context, new DiscoverAdaptersListener.DiscoverCarouselListener() {
                    @Override
                    public void carouselItemClicked(DiscoverCarouselObject carouselModel) {
//                        Answers.getInstance().logCustom(new CustomEvent(
//                                "Select from carousel").
//                                putCustomAttribute("Type", getCarouselType()));
//                        carouselListener.carouselItemClicked(carouselModel);
                    }

                    @Override
                    public void goingToClicked(DiscoverCarouselObject carouselModel) {
                        try {
                            for (int i = 0; i < content.getmFullObject().getJSONArray("info").length(); i++) {
                                if (carouselModel.getmId().equalsIgnoreCase(content.getmFullObject().
                                        getJSONArray("info").getJSONObject(i).
                                        getString("id"))) {
                                    content.getmFullObject().
                                            getJSONArray("info").getJSONObject(i).put("goingto", carouselModel.getGoingTo());
                                    break;
                                }
                            }
                            carouselListener.goingToClicked(carouselModel);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void goingToNoConnection() {
                        carouselListener.goingToNoConnection();
                    }
                });
                re_carousel_items.setAdapter(adapter);

                btn_see_more.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
//                        Answers.getInstance().logCustom(new CustomEvent(
//                                "See more carousel").
//                                putCustomAttribute("Type", getCarouselType()));
                        discoverAdapterListener.seeMoreClicked(discoverObjectList.
                                get(position).getmFullObject(), new DiscoverSeeMoreListener() {
                            @Override
                            public int describeContents() {
                                return 0;
                            }

                            @Override
                            public void writeToParcel(Parcel dest, int flags) {

                            }

                            @Override
                            public void updateDiscoverGoingToStatus(String ID, int goingTo) {
                                for (int i = 0; i < carouselModelList.size(); i++) {
                                    if (ID.equalsIgnoreCase(carouselModelList.get(i).getmId())) {
                                        carouselModelList.get(i).setGoingTo(goingTo);
                                        notifyDataSetChanged();
                                        try {
                                            if (ID.equalsIgnoreCase(content.getmFullObject().
                                                    getJSONArray("info").getJSONObject(i).
                                                    getString("id"))) {
                                                content.getmFullObject().
                                                        getJSONArray("info").getJSONObject(i).put("goingto", goingTo);
                                                break;
                                            }
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                    }
                                }
                            }
                        });
                    }
                });

                if (checkAllLocations() || checkAllArtists() || checkAllGenres() || checkShowButton(content)) {
                    btn_see_more.setVisibility(View.GONE);
                }
            } else {
                ll_carousel_item.setVisibility(View.GONE);
            }

        }

        private boolean checkAllLocations() {
            for (int i = 0; i < carouselModelList.size(); i++) {
                if (!carouselModelList.get(i).getmType().equals("city")) {
                    return false;
                }
            }
            return true;
        }

        private boolean checkAllArtists() {
            for (int i = 0; i < carouselModelList.size(); i++) {
                if (!carouselModelList.get(i).getmType().equals("artist")) {
                    return false;
                }
            }
            return true;
        }

        private boolean checkAllGenres() {
            for (int i = 0; i < carouselModelList.size(); i++) {
                if (!carouselModelList.get(i).getmType().equals("genre")) {
                    return false;
                }
            }
            return true;
        }

        private String getCarouselType() {
            if (checkAllLocations()) {
                return "city";
            } else if (checkAllGenres()) {
                return "genre";
            } else if (checkAllArtists()) {
                return "artist";
            } else {
                return "";
            }
        }

        private boolean checkShowButton(DiscoverObject content) {
            for (int i = 0; i < carouselModelList.size(); i++) {
                if (content.getmInfo().length() < 5) {
                    return true;
                }
            }

            return false;
        }
    }

    class InspireMeViewHolder extends BaseViewHolder {

        private ImageView img_main;
        private LinearLayout ll_container, ll_tag;
        private RelativeLayout rl_container;

        InspireMeViewHolder(View itemView) {
            super(itemView);
            img_main = itemView.findViewById(R.id.img_main);
            ll_container = itemView.findViewById(R.id.ll_container);
            rl_container = itemView.findViewById(R.id.rl_container);
            ll_tag = itemView.findViewById(R.id.ll_tag);
        }

        @Override
        public void setContent(final DiscoverObject content, int position) {
            super.setContent(content, position);
            ll_container.setAlpha(0.9f);
            ll_container.bringToFront();
            try {
                Picasso.get().load(content.getImage().getString("default")).into(img_main);
            } catch (Exception e) {
                e.printStackTrace();
            }
            rl_container.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    discoverAdapterListener.inspireMeClicked(content.getmFullObject());
//                    Answers.getInstance().logCustom(new CustomEvent("Select Inspire me").
//                            putCustomAttribute("Name", content.getmTitle()));
                }
            });

        }
    }

    class SingleEvent extends BaseViewHolder {

        private LinearLayout ll_container, ll_first_tag, ll_second_tag, ll_third_tag,
                ll_fourth_tag, ll_tag_layout, ll_text;
        private ImageView img_main, img_tag_one, img_tag_two, img_tag_three, img_tag_four,
                img_save_event;
        private TextView txt_event_name, txt_event_date, txt_location, txt_chosen_title,
                txt_tag_one, txt_tag_two, txt_tag_three, txt_tag_four;
        private View gradient_view;
        private RelativeLayout rl_going_to_container;

        SingleEvent(View itemView) {
            super(itemView);
            ll_container = itemView.findViewById(R.id.ll_container);
            ll_first_tag = itemView.findViewById(R.id.ll_first_tag);
            ll_second_tag = itemView.findViewById(R.id.ll_second_tag);
            ll_third_tag = itemView.findViewById(R.id.ll_third_tag);
            ll_fourth_tag = itemView.findViewById(R.id.ll_fourth_tag);
            ll_tag_layout = itemView.findViewById(R.id.ll_tag_layout);
            ll_text = itemView.findViewById(R.id.ll_text);

            img_main = itemView.findViewById(R.id.img_main);
            img_tag_one = itemView.findViewById(R.id.img_tag_one);
            img_tag_two = itemView.findViewById(R.id.img_tag_two);
            img_tag_three = itemView.findViewById(R.id.img_tag_three);
            img_tag_four = itemView.findViewById(R.id.img_tag_four);
            img_save_event = itemView.findViewById(R.id.img_save_event);

            txt_event_name = itemView.findViewById(R.id.txt_event_name);
            txt_event_date = itemView.findViewById(R.id.txt_event_date);
            txt_location = itemView.findViewById(R.id.txt_location);
            txt_chosen_title = itemView.findViewById(R.id.txt_chosen_title);
            txt_tag_one = itemView.findViewById(R.id.txt_tag_one);
            txt_tag_two = itemView.findViewById(R.id.txt_tag_two);
            txt_tag_three = itemView.findViewById(R.id.txt_tag_three);
            txt_tag_four = itemView.findViewById(R.id.txt_tag_four);

            rl_going_to_container = itemView.findViewById(R.id.rl_going_to_container);

            gradient_view = itemView.findViewById(R.id.gradient_view);
            ll_container.bringToFront();
            rl_going_to_container.bringToFront();
        }

        @Override
        public void setContent(final DiscoverObject content, int position) {
            if (!content.getmTitle().equals("")) {
                txt_chosen_title.setVisibility(View.VISIBLE);
                txt_chosen_title.setText(content.getmTitle());
            }
            super.setContent(content, position);
            try {
                Picasso.get().load(content.getmInfo().getJSONObject(0).
                        getJSONObject("image").getString("default")).into(img_main);
            } catch (Exception e) {
                e.printStackTrace();
            }

            ll_container.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
//                        Answers.getInstance().logCustom(new CustomEvent("Select single event").
//                                putCustomAttribute("ID", content.getmInfo().getJSONObject(0).
//                                        getString("id")));
                        discoverAdapterListener.singleEventClicked(content.getmInfo().
                                getJSONObject(0).getString("id"));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });

            try {
                if (content.getmInfo().getJSONObject(0).
                        getString("headerhex") != null && !content.getmInfo().getJSONObject(0).
                        getString("headerhex").equals("")) {

                    GradientDrawable gd = new GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM,
                            new int[]{Color.TRANSPARENT, alterColor(Color.parseColor(
                                    content.getmInfo().getJSONObject(0).
                                            getString("headerhex")), 0.2f)});
                    gd.setCornerRadius(0f);

                    gradient_view.setBackground(gd);
                    gradient_view.bringToFront();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

            try {
                txt_event_name.setText(Html.fromHtml(content.getmInfo().getJSONObject(
                        0).getString("name")));
                txt_event_date.setText(getFormattedDate(content.getmInfo().
                        getJSONObject(0).getString("date")));
                txt_location.setText(String.format("%s, %s", content.getmInfo().getJSONObject(
                        0).getJSONObject("venue").getString("name"), content.getmInfo().getJSONObject(0).getJSONObject("venue").
                        getString("town")));
            } catch (Exception e) {
                e.printStackTrace();
            }

            sortTags(content);
            handleStatus(content);

            rl_going_to_container.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (checkConnectionAvailable()) {
                        try {
                            switch (content.getmInfo().getJSONObject(0).getInt("goingto")) {
                                case 0:
                                    content.getmInfo().getJSONObject(0).put("goingto", 9);
                                    break;
                                case 1:
                                    content.getmInfo().getJSONObject(0).put("goingto", 0);
                                    break;
                                case 9:
                                    content.getmInfo().getJSONObject(0).put("goingto", 1);
                                    break;
                            }
                            notifyDataSetChanged();
                            discoverAdapterListener.goingToSingleEventClicked(content);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    } else {
                        carouselListener.goingToNoConnection();
                    }
                }
            });
        }

        private void sortTags(DiscoverObject content) {
//            try {
//                JSONArray jsonArray = content.getmInfo().getJSONObject(0).getJSONArray("tags");
//                for (int i = 0; i < jsonArray.length(); i++) {
//                    if (i == 0) {
//                        setTagAfter(txt_tag_one, ll_first_tag, img_tag_one, jsonArray.getJSONObject(i));
//                    } else if (i == 1) {
//                        setTagAfter(txt_tag_two, ll_second_tag, img_tag_two, jsonArray.getJSONObject(i));
//                    } else if (i == 2) {
//                        setTagAfter(txt_tag_three, ll_third_tag, img_tag_three, jsonArray.getJSONObject(i));
//                    } else if (i == 3) {
//                        setTagAfter(txt_tag_four, ll_fourth_tag, img_tag_four, jsonArray.getJSONObject(i));
//                    }
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
        }

        private void setTagAfter(TextView textView, LinearLayout linearLayout, ImageView imageView,
                                 JSONObject object) throws Exception {
//            switch (object.getString("type")) {
//                case Constants.FEATURED:
//                    textView.setText("Featured");
//                    imageView.setImageDrawable(context.getResources().getDrawable(R.drawable.icn_featured));
//                    linearLayout.setBackground(context.getResources().getDrawable(R.drawable.tag_featured));
//                    linearLayout.setAlpha(0.9f);
//                    textView.bringToFront();
//                    break;
//                case Constants.CHOSEN:
//                    imageView.setImageDrawable(context.getResources().getDrawable(R.drawable.icn_chosen_for_you));
//                    textView.setText("Chosen For You");
//                    linearLayout.setBackground(context.getResources().getDrawable(R.drawable.tag_chosen_for_you));
//                    linearLayout.setAlpha(0.9f);
//                    break;
//                case Constants.POPULAR:
//                    imageView.setImageDrawable(context.getResources().getDrawable(R.drawable.icn_popular));
//                    textView.setText("Popular: " + object.getString(Constants.DETAIL) + " going");
//                    linearLayout.setBackground(context.getResources().getDrawable(R.drawable.tag_inspire_me));
//                    linearLayout.setAlpha(0.9f);
//                    break;
//                case Constants.INSPIRE_ME:
//                    imageView.setImageDrawable(context.getResources().getDrawable(R.drawable.icn_inspire_me));
//                    textView.setText("Inspire Me");
//                    linearLayout.setBackground(context.getResources().getDrawable(R.drawable.tag_inspire_me));
//                    linearLayout.setAlpha(0.9f);
//                    break;
//            }
        }

        private void handleStatus(DiscoverObject content) {
//            try {
//                switch (content.getmInfo().getJSONObject(0).getInt("goingto")) {
//                    case 0:
//                        img_save_event.setImageDrawable(context.getResources().
//                                getDrawable(R.drawable.icn_empty_heart));
//                        break;
//                    case 1:
//                        img_save_event.setImageDrawable(context.getResources().
//                                getDrawable(R.drawable.icn_going_tick));
//                        break;
//                    case 9:
//                        img_save_event.setImageDrawable(context.getResources().
//                                getDrawable(R.drawable.icn_red_heart));
//                        break;
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }

        }

        private int alterColor(int color, float factor) {
        int a = (color & (0xFF << 24)) >> 24;
        int r = (int) (((color & (0xFF << 16)) >> 16) * factor);
        int g = (int) (((color & (0xFF << 8)) >> 8) * factor);
        int b = (int) ((color & 0xFF) * factor);
        return Color.argb(a, r, g, b);
        }

        private String getFormattedDate(String dateString) throws Exception {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.UK);
            Date newDate = format.parse(dateString);
            format = new SimpleDateFormat("EEEE d MMMM YYYY", Locale.UK);

            String[] splitDate = format.format(newDate).split(" ");
            for (int i = 0; i < splitDate.length; i++) {
                try {
                    int dateNumber = Integer.parseInt(splitDate[i]);
//                    splitDate[i] = splitDate[i] + getDayOfMonthSuffix(dateNumber);
                    break;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            return buildFullDateFromArray(splitDate);
        }

        private String buildFullDateFromArray(String[] splitDate) {
            String completeDate = "";

            for (int i = 0; i < splitDate.length; i++) {
                completeDate = completeDate + " " + splitDate[i];
            }

            return completeDate.trim();
        }

//        private String getDayOfMonthSuffix(final int n) {
//            checkArgument(n >= 1 && n <= 31, "illegal day of month: " + n);
//            if (n >= 11 && n <= 13) {
//                return "th";
//            }
//            switch (n % 10) {
//                case 1:
//                    return "st";
//                case 2:
//                    return "nd";
//                case 3:
//                    return "rd";
//                default:
//                    return "th";
//            }
//        }

        private boolean checkConnectionAvailable() {
            ConnectivityManager connectivityManager
                    = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            return activeNetworkInfo != null;
        }
    }
}
