package com.example.markprime.test.Discover;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.markprime.test.Model.DiscoverCarouselObject;
import com.example.markprime.test.R;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class DiscoverCarouselAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    private List<DiscoverCarouselObject> carouselList;
    private Context context;
    private DiscoverAdaptersListener.DiscoverCarouselListener discoverCarouselListener;

    private final int TYPE_CITY = 0;
    private final int TYPE_GENRE = 1;
    private final int TYPE_ARTIST = 2;
    private final int TYPE_EVENT = 3;

    public DiscoverCarouselAdapter(List<DiscoverCarouselObject> carouselList, Context context,
                                   DiscoverAdaptersListener.DiscoverCarouselListener discoverCarouselListener) {
        this.carouselList = carouselList;
        this.context = context;
        this.discoverCarouselListener = discoverCarouselListener;
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);

        switch (viewType) {
            case TYPE_CITY:
                return new LocationViewHolder(inflater.inflate(R.layout.item_carousel, parent, false));
            case TYPE_ARTIST:
                return new ArtistViewHolder(inflater.inflate(R.layout.item_carousel, parent, false));
            case TYPE_GENRE:
                return new GenreViewHolder(inflater.inflate(R.layout.item_carousel, parent, false));
            case TYPE_EVENT:
                return new EventViewHolder(inflater.inflate(R.layout.item_carousel, parent, false));
        }

        return null;

    }

    @Override
    public int getItemViewType(int position) {
        switch (carouselList.get(position).getmType()) {
            case "genre":
                return TYPE_GENRE;
            case "artist":
                return TYPE_ARTIST;
            case "city":
                return TYPE_CITY;
            case "event":
                return TYPE_EVENT;
        }
        return super.getItemViewType(position);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        holder.setContent(carouselList.get(position), position);
    }

    private boolean checkConnectionAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null;
    }

    private void setCarouselItemWidth(ViewHolder holder, int size) {
        holder.ll_carousel_item.getLayoutParams().width = Math.round(pxFromDp(context, size));
        holder.img_rounded_frame.getLayoutParams().width = Math.round(pxFromDp(context, size));
        holder.img_event_image.getLayoutParams().width = Math.round(pxFromDp(context, size));
        holder.fl_container.getLayoutParams().width = Math.round(pxFromDp(context, size));
        holder.view_gradient.getLayoutParams().width = Math.round(pxFromDp(context, size));
        holder.ll_carousel_item.requestLayout();
        holder.fl_container.requestLayout();
        holder.img_event_image.requestLayout();
        holder.view_gradient.requestLayout();
    }

    private void setCarouselItemHeight(ViewHolder holder, int size) {
        holder.ll_carousel_item.getLayoutParams().height = Math.round(pxFromDp(context, size));
        holder.img_rounded_frame.getLayoutParams().height = Math.round(pxFromDp(context, size));
        holder.img_event_image.getLayoutParams().height = Math.round(pxFromDp(context, size));
        holder.fl_container.getLayoutParams().height = Math.round(pxFromDp(context, size));
        holder.view_gradient.getLayoutParams().height = Math.round(pxFromDp(context, size));
        holder.ll_carousel_item.requestLayout();
        holder.fl_container.requestLayout();
        holder.img_event_image.requestLayout();
        holder.view_gradient.requestLayout();
    }

//    private void setGoingToInfo(ViewHolder holder, DiscoverCarouselObject carouselModel) {
//        switch (carouselModel.getGoingTo()) {
//            case 0:
//                holder.img_going_to.setImageDrawable(context.getResources().
//                        getDrawable(R.drawable.icn_empty_heart));
//                break;
//            case 1:
//                holder.img_going_to.setImageDrawable(context.getResources().
//                        getDrawable(R.drawable.icn_going_tick));
//                break;
//            case 9:
//                holder.img_going_to.setImageDrawable(context.getResources().
//                        getDrawable(R.drawable.icn_red_heart));
//                break;
//        }
//    }

    private void setTagsTextAnBackground(JSONObject object, TextView textView, ImageView imageView,
                                         LinearLayout linearLayout) {
//        try {
//            switch (object.getString(Constants.TYPE)) {
//                case Constants.FEATURED:
//                    linearLayout.setBackground(context.getResources().getDrawable(R.drawable.tag_featured));
//                    textView.setVisibility(View.GONE);
//                    imageView.setImageDrawable(context.getResources().getDrawable(R.drawable.icn_featured));
//                    linearLayout.setAlpha(0.9f);
//                    break;
//                case Constants.CHOSEN:
//                    linearLayout.setBackground(context.getResources().getDrawable(R.drawable.tag_chosen_for_you));
//                    textView.setVisibility(View.GONE);
//                    imageView.setImageDrawable(context.getResources().getDrawable(R.drawable.icn_chosen_for_you));
//                    linearLayout.setAlpha(0.9f);
//                    break;
//                case Constants.POPULAR:
//                    linearLayout.setBackground(context.getResources().getDrawable(R.drawable.tag_inspire_me));
//                    if (Integer.parseInt(object.getString(Constants.DETAIL)) > 1000) {
//                        textView.setText(divideDetailByThousand(object.getString(Constants.DETAIL)));
//                    } else {
//                        textView.setText(object.getString(Constants.DETAIL));
//                    }
//                    textView.setVisibility(View.VISIBLE);
//                    imageView.setImageDrawable(context.getResources().getDrawable(R.drawable.icn_popular));
//                    linearLayout.setAlpha(0.9f);
//                    break;
//                case Constants.INSPIRE_ME:
//                    linearLayout.setBackground(context.getResources().getDrawable(R.drawable.tag_inspire_me));
//                    textView.setVisibility(View.GONE);
//                    imageView.setImageDrawable(context.getResources().getDrawable(R.drawable.icn_inspire_me));
//                    linearLayout.setAlpha(0.9f);
//                    break;
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }

    private String divideDetailByThousand(String detail) {
        double numberDetail = Double.parseDouble(detail) / 1000;
        DecimalFormat format = new DecimalFormat("0.0");
        return format.format(numberDetail) + "k+";
    }

    private boolean checkAllLocations() {
        for (int i = 0; i < carouselList.size(); i++) {
            if (!carouselList.get(i).getmType().equals("city")) {
                return false;
            }
        }
        return true;
    }

    private boolean checkAllArtists() {
        for (int i = 0; i < carouselList.size(); i++) {
            if (!carouselList.get(i).getmType().equals("artist")) {
                return false;
            }
        }
        return true;
    }

    private boolean checkAllGenres() {
        for (int i = 0; i < carouselList.size(); i++) {
            if (!carouselList.get(i).getmType().equals("genre")) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int getItemCount() {
        if (checkAllLocations() || checkAllArtists() || checkAllGenres()) {
            return carouselList.size();
        } else {
            if (carouselList.size() >= 3 && carouselList.size() < 5) {
                return 3;
            } else if (carouselList.size() > 5) {
                return 5;
            }
            return carouselList.size();
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView txt_event_name, txt_date, txt_location, txt_tag_one, txt_tag_two,
                txt_tag_three, txt_tag_four, txt_center;
        private ImageView img_event_image, img_tag_one, img_tag_two, img_tag_three, img_tag_four,
                img_going_to, img_rounded_frame, img_small_icon;
        private LinearLayout ll_carousel_item, ll_tags, ll_first_tag, ll_second_tag,
                ll_third_tag, ll_fourth_tag;
        private RelativeLayout rl_going_to_container;
        private View view_gradient;
        private FrameLayout fl_container;

        public ViewHolder(View itemView) {
            super(itemView);
            txt_event_name = itemView.findViewById(R.id.txt_event_name);
            txt_date = itemView.findViewById(R.id.txt_date);
            txt_location = itemView.findViewById(R.id.txt_location);
            txt_tag_one = itemView.findViewById(R.id.txt_tag_one);
            txt_tag_two = itemView.findViewById(R.id.txt_tag_two);
            txt_tag_three = itemView.findViewById(R.id.txt_tag_three);
            txt_tag_four = itemView.findViewById(R.id.txt_tag_four);
            txt_center = itemView.findViewById(R.id.txt_center);

            img_event_image = itemView.findViewById(R.id.img_event_image);
            img_tag_one = itemView.findViewById(R.id.img_tag_one);
            img_tag_two = itemView.findViewById(R.id.img_tag_two);
            img_tag_three = itemView.findViewById(R.id.img_tag_three);
            img_tag_four = itemView.findViewById(R.id.img_tag_four);
            img_going_to = itemView.findViewById(R.id.img_going_to);
            img_rounded_frame = itemView.findViewById(R.id.img_rounded_frame);
            img_small_icon = itemView.findViewById(R.id.img_small_icon);

            ll_carousel_item = itemView.findViewById(R.id.ll_carousel_item);
            ll_tags = itemView.findViewById(R.id.ll_tags);
            ll_first_tag = itemView.findViewById(R.id.ll_first_tag);
            ll_second_tag = itemView.findViewById(R.id.ll_second_tag);
            ll_third_tag = itemView.findViewById(R.id.ll_third_tag);
            ll_fourth_tag = itemView.findViewById(R.id.ll_fourth_tag);

            view_gradient = itemView.findViewById(R.id.view_gradient);

            fl_container = itemView.findViewById(R.id.fl_container);

            rl_going_to_container = itemView.findViewById(R.id.rl_going_to_container);
            rl_going_to_container.bringToFront();
        }
    }

    private String getFormattedShortDate(String dateString) throws Exception {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.UK);
        Date newDate = format.parse(dateString);
        format = new SimpleDateFormat("EEE d MMM", Locale.UK);

        String[] splitDate = format.format(newDate).split(" ");
        for (int i = 0; i < splitDate.length; i++) {
            try {
                int dateNumber = Integer.parseInt(splitDate[i]);
//                splitDate[i] = splitDate[i] + getDayOfMonthSuffix(dateNumber);
                break;
            } catch (Exception e) {
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

//    private String getDayOfMonthSuffix(final int n) {
//        checkArgument(n >= 1 && n <= 31, "illegal day of month: " + n);
//        if (n >= 11 && n <= 13) {
//            return "th";
//        }
//        switch (n % 10) {
//            case 1:
//                return "st";
//            case 2:
//                return "nd";
//            case 3:
//                return "rd";
//            default:
//                return "th";
//        }
//    }

    public float pxFromDp(final Context context, final float dp) {
        return dp * context.getResources().getDisplayMetrics().density;
    }

    class GenreViewHolder extends BaseViewHolder {

        private TextView txt_event_name, txt_date, txt_location, txt_tag_one, txt_tag_two,
                txt_tag_three, txt_tag_four, txt_center;
        private ImageView img_event_image, img_tag_one, img_tag_two, img_tag_three, img_tag_four,
                img_going_to, img_rounded_frame, img_small_icon;
        private LinearLayout ll_carousel_item, ll_tags, ll_first_tag, ll_second_tag,
                ll_third_tag, ll_fourth_tag;
        private RelativeLayout rl_going_to_container;
        private View view_gradient;
        private FrameLayout fl_container;

        public GenreViewHolder(View itemView) {
            super(itemView);
            txt_event_name = itemView.findViewById(R.id.txt_event_name);
            txt_date = itemView.findViewById(R.id.txt_date);
            txt_location = itemView.findViewById(R.id.txt_location);
            txt_tag_one = itemView.findViewById(R.id.txt_tag_one);
            txt_tag_two = itemView.findViewById(R.id.txt_tag_two);
            txt_tag_three = itemView.findViewById(R.id.txt_tag_three);
            txt_tag_four = itemView.findViewById(R.id.txt_tag_four);
            txt_center = itemView.findViewById(R.id.txt_center);

            img_event_image = itemView.findViewById(R.id.img_event_image);
            img_tag_one = itemView.findViewById(R.id.img_tag_one);
            img_tag_two = itemView.findViewById(R.id.img_tag_two);
            img_tag_three = itemView.findViewById(R.id.img_tag_three);
            img_tag_four = itemView.findViewById(R.id.img_tag_four);
            img_going_to = itemView.findViewById(R.id.img_going_to);
            img_rounded_frame = itemView.findViewById(R.id.img_rounded_frame);
            img_small_icon = itemView.findViewById(R.id.img_small_icon);

            ll_carousel_item = itemView.findViewById(R.id.ll_carousel_item);
            ll_tags = itemView.findViewById(R.id.ll_tags);
            ll_first_tag = itemView.findViewById(R.id.ll_first_tag);
            ll_second_tag = itemView.findViewById(R.id.ll_second_tag);
            ll_third_tag = itemView.findViewById(R.id.ll_third_tag);
            ll_fourth_tag = itemView.findViewById(R.id.ll_fourth_tag);

            view_gradient = itemView.findViewById(R.id.view_gradient);

            fl_container = itemView.findViewById(R.id.fl_container);

            rl_going_to_container = itemView.findViewById(R.id.rl_going_to_container);
            rl_going_to_container.bringToFront();
        }

        @Override
        public void setContent(DiscoverCarouselObject content, int position) {
            super.setContent(content, position);
            txt_event_name.setText(Html.fromHtml(content.getmName()));
            ll_tags.bringToFront();

            setCarouselItemWidth(240);
            setCarouselItemHeight(140);


            try {
                Picasso.get().load(content.getmImage().
                        getString("default")).into(img_event_image);
                txt_date.setText(getFormattedShortDate(content.getmDate()));
                txt_location.setText(carouselList.get(position).getmVenue().getString("town"));
            } catch (Exception e) {
                e.printStackTrace();
            }

            ll_carousel_item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    discoverCarouselListener.carouselItemClicked(carouselList.get(getAdapterPosition()));
                }
            });

            txt_location.setVisibility(View.GONE);
            txt_date.setVisibility(View.GONE);
            rl_going_to_container.setVisibility(View.GONE);
            txt_center.setText(carouselList.get(position).getmName());
            txt_center.setVisibility(View.VISIBLE);
            txt_event_name.setVisibility(View.GONE);
            view_gradient.setVisibility(View.VISIBLE);
            view_gradient.bringToFront();
            txt_center.bringToFront();
            img_rounded_frame.bringToFront();
            img_small_icon.setVisibility(View.GONE);
        }

        private void setCarouselItemWidth(int size) {
            ll_carousel_item.getLayoutParams().width = Math.round(pxFromDp(context, size));
            img_rounded_frame.getLayoutParams().width = Math.round(pxFromDp(context, size));
            img_event_image.getLayoutParams().width = Math.round(pxFromDp(context, size));
            fl_container.getLayoutParams().width = Math.round(pxFromDp(context, size));
            view_gradient.getLayoutParams().width = Math.round(pxFromDp(context, size));
            ll_carousel_item.requestLayout();
            fl_container.requestLayout();
            img_event_image.requestLayout();
            view_gradient.requestLayout();
        }

        private void setCarouselItemHeight(int size) {
            ll_carousel_item.getLayoutParams().height = Math.round(pxFromDp(context, size));
            img_rounded_frame.getLayoutParams().height = Math.round(pxFromDp(context, size));
            img_event_image.getLayoutParams().height = Math.round(pxFromDp(context, size));
            fl_container.getLayoutParams().height = Math.round(pxFromDp(context, size));
            view_gradient.getLayoutParams().height = Math.round(pxFromDp(context, size));
            ll_carousel_item.requestLayout();
            fl_container.requestLayout();
            img_event_image.requestLayout();
            view_gradient.requestLayout();
        }
    }

    class LocationViewHolder extends BaseViewHolder {

        private TextView txt_event_name, txt_date, txt_location, txt_tag_one, txt_tag_two,
                txt_tag_three, txt_tag_four, txt_center;
        private ImageView img_event_image, img_tag_one, img_tag_two, img_tag_three, img_tag_four,
                img_going_to, img_rounded_frame, img_small_icon;
        private LinearLayout ll_carousel_item, ll_tags, ll_first_tag, ll_second_tag,
                ll_third_tag, ll_fourth_tag;
        private RelativeLayout rl_going_to_container;
        private View view_gradient;
        private FrameLayout fl_container;

        public LocationViewHolder(View itemView) {
            super(itemView);
            txt_event_name = itemView.findViewById(R.id.txt_event_name);
            txt_date = itemView.findViewById(R.id.txt_date);
            txt_location = itemView.findViewById(R.id.txt_location);
            txt_tag_one = itemView.findViewById(R.id.txt_tag_one);
            txt_tag_two = itemView.findViewById(R.id.txt_tag_two);
            txt_tag_three = itemView.findViewById(R.id.txt_tag_three);
            txt_tag_four = itemView.findViewById(R.id.txt_tag_four);
            txt_center = itemView.findViewById(R.id.txt_center);

            img_event_image = itemView.findViewById(R.id.img_event_image);
            img_tag_one = itemView.findViewById(R.id.img_tag_one);
            img_tag_two = itemView.findViewById(R.id.img_tag_two);
            img_tag_three = itemView.findViewById(R.id.img_tag_three);
            img_tag_four = itemView.findViewById(R.id.img_tag_four);
            img_going_to = itemView.findViewById(R.id.img_going_to);
            img_rounded_frame = itemView.findViewById(R.id.img_rounded_frame);
            img_small_icon = itemView.findViewById(R.id.img_small_icon);

            ll_carousel_item = itemView.findViewById(R.id.ll_carousel_item);
            ll_tags = itemView.findViewById(R.id.ll_tags);
            ll_first_tag = itemView.findViewById(R.id.ll_first_tag);
            ll_second_tag = itemView.findViewById(R.id.ll_second_tag);
            ll_third_tag = itemView.findViewById(R.id.ll_third_tag);
            ll_fourth_tag = itemView.findViewById(R.id.ll_fourth_tag);

            view_gradient = itemView.findViewById(R.id.view_gradient);

            fl_container = itemView.findViewById(R.id.fl_container);

            rl_going_to_container = itemView.findViewById(R.id.rl_going_to_container);
            rl_going_to_container.bringToFront();
        }

        @Override
        public void setContent(DiscoverCarouselObject content, int position) {
            super.setContent(content, position);
            txt_event_name.setText(Html.fromHtml(content.getmName()));
            ll_tags.bringToFront();

            setCarouselItemWidth(210);

//            RequestOptions requestOptions = new RequestOptions().
//                    placeholder(context.getResources().getDrawable(R.drawable.ic_skiddle_placeholder)).
//                    error(R.drawable.ic_skiddle_placeholder).centerCrop();

            try {
                Picasso.get().load(content.getmImage()
                        .getString("default"))
                        .into(img_event_image);
                txt_date.setText(getFormattedShortDate(content.getmDate()));
                txt_location.setText(carouselList.get(position).getmVenue().getString("town"));
            } catch (Exception e) {
                e.printStackTrace();
            }

            ll_carousel_item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    discoverCarouselListener.carouselItemClicked(carouselList.get(getAdapterPosition()));
                }
            });

            txt_location.setVisibility(View.GONE);
            txt_date.setVisibility(View.GONE);
            rl_going_to_container.setVisibility(View.GONE);
            txt_center.setText(content.getmName());
            txt_center.setVisibility(View.VISIBLE);
            txt_event_name.setVisibility(View.GONE);
            view_gradient.setVisibility(View.VISIBLE);
            view_gradient.bringToFront();
            txt_center.bringToFront();
            img_rounded_frame.bringToFront();
            img_small_icon.setVisibility(View.GONE);
        }

        private void setCarouselItemWidth(int size) {
            ll_carousel_item.getLayoutParams().width = Math.round(pxFromDp(context, size));
            img_rounded_frame.getLayoutParams().width = Math.round(pxFromDp(context, size));
            img_event_image.getLayoutParams().width = Math.round(pxFromDp(context, size));
            fl_container.getLayoutParams().width = Math.round(pxFromDp(context, size));
            view_gradient.getLayoutParams().width = Math.round(pxFromDp(context, size));
            ll_carousel_item.requestLayout();
            fl_container.requestLayout();
            img_event_image.requestLayout();
            view_gradient.requestLayout();
        }

    }

    class ArtistViewHolder extends BaseViewHolder {

        private TextView txt_event_name, txt_date, txt_location, txt_tag_one, txt_tag_two,
                txt_tag_three, txt_tag_four, txt_center;
        private ImageView img_event_image, img_tag_one, img_tag_two, img_tag_three, img_tag_four,
                img_going_to, img_rounded_frame, img_small_icon;
        private LinearLayout ll_carousel_item, ll_tags, ll_first_tag, ll_second_tag,
                ll_third_tag, ll_fourth_tag;
        private RelativeLayout rl_going_to_container;
        private View view_gradient;
        private FrameLayout fl_container;

        public ArtistViewHolder(View itemView) {
            super(itemView);
            txt_event_name = itemView.findViewById(R.id.txt_event_name);
            txt_date = itemView.findViewById(R.id.txt_date);
            txt_location = itemView.findViewById(R.id.txt_location);
            txt_tag_one = itemView.findViewById(R.id.txt_tag_one);
            txt_tag_two = itemView.findViewById(R.id.txt_tag_two);
            txt_tag_three = itemView.findViewById(R.id.txt_tag_three);
            txt_tag_four = itemView.findViewById(R.id.txt_tag_four);
            txt_center = itemView.findViewById(R.id.txt_center);

            img_event_image = itemView.findViewById(R.id.img_event_image);
            img_tag_one = itemView.findViewById(R.id.img_tag_one);
            img_tag_two = itemView.findViewById(R.id.img_tag_two);
            img_tag_three = itemView.findViewById(R.id.img_tag_three);
            img_tag_four = itemView.findViewById(R.id.img_tag_four);
            img_going_to = itemView.findViewById(R.id.img_going_to);
            img_rounded_frame = itemView.findViewById(R.id.img_rounded_frame);
            img_small_icon = itemView.findViewById(R.id.img_small_icon);

            ll_carousel_item = itemView.findViewById(R.id.ll_carousel_item);
            ll_tags = itemView.findViewById(R.id.ll_tags);
            ll_first_tag = itemView.findViewById(R.id.ll_first_tag);
            ll_second_tag = itemView.findViewById(R.id.ll_second_tag);
            ll_third_tag = itemView.findViewById(R.id.ll_third_tag);
            ll_fourth_tag = itemView.findViewById(R.id.ll_fourth_tag);

            view_gradient = itemView.findViewById(R.id.view_gradient);

            fl_container = itemView.findViewById(R.id.fl_container);

            rl_going_to_container = itemView.findViewById(R.id.rl_going_to_container);
            rl_going_to_container.bringToFront();
        }

        @Override
        public void setContent(DiscoverCarouselObject content, int position) {
            super.setContent(content, position);
            txt_event_name.setText(Html.fromHtml(content.getmName()));
            ll_tags.bringToFront();

//            RequestOptions requestOptions = new RequestOptions().
//                    placeholder(context.getResources().getDrawable(R.drawable.ic_skiddle_placeholder)).
//                    error(R.drawable.ic_skiddle_placeholder).centerCrop();

            try {
                Picasso.get()
                        .load(content.getmImage()
                        .getString("default"))
                        .into(img_event_image);
                txt_date.setText(getFormattedShortDate(content.getmDate()));
                txt_location.setText(carouselList.get(position).getmVenue().getString("town"));
            } catch (Exception e) {
                e.printStackTrace();
            }

            ll_carousel_item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    discoverCarouselListener.carouselItemClicked(carouselList.get(getAdapterPosition()));
                }
            });

            txt_location.setVisibility(View.GONE);
            txt_date.setVisibility(View.GONE);
            rl_going_to_container.setVisibility(View.GONE);
            txt_center.setText(content.getmName());
            txt_center.setVisibility(View.VISIBLE);
            txt_event_name.setVisibility(View.GONE);
            view_gradient.setVisibility(View.VISIBLE);
            view_gradient.bringToFront();
            txt_center.bringToFront();
            img_rounded_frame.bringToFront();
            img_small_icon.setVisibility(View.GONE);

        }

    }

    class EventViewHolder extends BaseViewHolder {

        private TextView txt_event_name, txt_date, txt_location, txt_tag_one, txt_tag_two,
                txt_tag_three, txt_tag_four, txt_center, txt_tag_five;
        private ImageView img_event_image, img_tag_one, img_tag_two, img_tag_three, img_tag_four,
                img_going_to, img_rounded_frame, img_small_icon, img_tag_five;
        private LinearLayout ll_carousel_item, ll_tags, ll_first_tag, ll_second_tag,
                ll_third_tag, ll_fourth_tag, ll_fith_tag;
        private RelativeLayout rl_going_to_container;
        private View view_gradient;
        private FrameLayout fl_container;

        public EventViewHolder(View itemView) {
            super(itemView);
            txt_event_name = itemView.findViewById(R.id.txt_event_name);
            txt_date = itemView.findViewById(R.id.txt_date);
            txt_location = itemView.findViewById(R.id.txt_location);
            txt_tag_one = itemView.findViewById(R.id.txt_tag_one);
            txt_tag_two = itemView.findViewById(R.id.txt_tag_two);
            txt_tag_three = itemView.findViewById(R.id.txt_tag_three);
            txt_tag_four = itemView.findViewById(R.id.txt_tag_four);
            txt_center = itemView.findViewById(R.id.txt_center);
            txt_tag_five = itemView.findViewById(R.id.txt_tag_five);

            img_event_image = itemView.findViewById(R.id.img_event_image);
            img_tag_one = itemView.findViewById(R.id.img_tag_one);
            img_tag_two = itemView.findViewById(R.id.img_tag_two);
            img_tag_three = itemView.findViewById(R.id.img_tag_three);
            img_tag_four = itemView.findViewById(R.id.img_tag_four);
            img_going_to = itemView.findViewById(R.id.img_going_to);
            img_rounded_frame = itemView.findViewById(R.id.img_rounded_frame);
            img_small_icon = itemView.findViewById(R.id.img_small_icon);
            img_tag_five = itemView.findViewById(R.id.img_tag_five);

            ll_carousel_item = itemView.findViewById(R.id.ll_carousel_item);
            ll_tags = itemView.findViewById(R.id.ll_tags);
            ll_first_tag = itemView.findViewById(R.id.ll_first_tag);
            ll_second_tag = itemView.findViewById(R.id.ll_second_tag);
            ll_third_tag = itemView.findViewById(R.id.ll_third_tag);
            ll_fourth_tag = itemView.findViewById(R.id.ll_fourth_tag);
            ll_fith_tag = itemView.findViewById(R.id.ll_fith_tag);

            view_gradient = itemView.findViewById(R.id.view_gradient);

            fl_container = itemView.findViewById(R.id.fl_container);

            rl_going_to_container = itemView.findViewById(R.id.rl_going_to_container);
            rl_going_to_container.bringToFront();
        }

        @Override
        public void setContent(final DiscoverCarouselObject content, int position) {
            super.setContent(content, position);

            txt_event_name.setText(Html.fromHtml(content.getmName()));
            ll_tags.bringToFront();

//            setGoingToInfo(carouselList.get(position));

            if (content.getTags().length() > 0) {
                setTags(carouselList.get(position).getTags());
            }

//            RequestOptions requestOptions = new RequestOptions().
//                    placeholder(context.getResources().getDrawable(R.drawable.ic_skiddle_placeholder)).
//                    error(R.drawable.ic_skiddle_placeholder).centerCrop();

            try {
                Picasso.get().load(content.getmImage()
                        .getString("default"))
                        .into(img_event_image);
                txt_date.setText(getFormattedShortDate(content.getmDate()));
                txt_location.setText(carouselList.get(position).getmVenue().getString("town"));
            } catch (Exception e) {
                e.printStackTrace();
            }

            ll_carousel_item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    discoverCarouselListener.carouselItemClicked(carouselList.get(getAdapterPosition()));
                }
            });

            rl_going_to_container.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (checkConnectionAvailable()) {
                        switch (carouselList.get(getLayoutPosition()).getGoingTo()) {
                            case 0:
                                carouselList.get(getLayoutPosition()).setGoingTo(9);
                                break;
                            case 1:
                                carouselList.get(getLayoutPosition()).setGoingTo(0);
                                break;
                            case 9:
                                carouselList.get(getLayoutPosition()).setGoingTo(1);
                                break;
                        }
                        notifyDataSetChanged();
                        discoverCarouselListener.goingToClicked(content);
                    } else {
                        discoverCarouselListener.goingToNoConnection();
                    }
                }
            });
        }

        private void setTags(JSONArray tags) {
            for (int i = 0; i < tags.length(); i++) {
                try {
                    if (i == 0) {
                        setTagsTextAnBackground(tags.getJSONObject(i),
                                txt_tag_one, img_tag_one, ll_first_tag);
                    } else if (i == 1) {
                        setTagsTextAnBackground(tags.getJSONObject(i),
                                txt_tag_two, img_tag_two, ll_second_tag);
                    } else if (i == 2) {
                        setTagsTextAnBackground(tags.getJSONObject(i),
                                txt_tag_three, img_tag_three, ll_third_tag);
                    } else if (i == 3) {
                        setTagsTextAnBackground(tags.getJSONObject(i),
                                txt_tag_four, img_tag_four, ll_fourth_tag);
                    } else if (i == 4) {
                        setTagsTextAnBackground(tags.getJSONObject(i),
                                txt_tag_five, img_tag_five, ll_fith_tag);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

//        private void setGoingToInfo(DiscoverCarouselObject carouselModel){
//            switch (carouselModel.getGoingTo()){
//                case 0:
//                    img_going_to.setImageDrawable(context.getResources().
//                            getDrawable(R.drawable.icn_empty_heart));
//                    break;
//                case 1:
//                    img_going_to.setImageDrawable(context.getResources().
//                            getDrawable(R.drawable.icn_going_tick));
//                    break;
//                case 9:
//                    img_going_to.setImageDrawable(context.getResources().
//                            getDrawable(R.drawable.icn_red_heart));
//                    break;
//            }
//        }
//
//    }


    }
}