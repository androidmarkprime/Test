package com.example.markprime.test.Discover.InspireMe;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.provider.SyncStateContract;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.markprime.test.Model.InspireMeEventsObject;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import static android.support.v4.util.Preconditions.checkArgument;

public class InspireMeAdapter extends RecyclerView.Adapter<InspireMeAdapter.ViewHolder>{

private Context context;
private List<InspireMeEventsObject> inspireMeEventsList;
private InspireMeAdapterListener listener;

public InspireMeAdapter(Context context, List<InspireMeEventsObject> inspireMeEventsList,
        InspireMeAdapterListener listener){
        this.context = context;
        this.inspireMeEventsList = inspireMeEventsList;
        this.listener = listener;
        }

@NonNull
@Override
public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_inspire_me_events, parent,
        false);
        return new ViewHolder(view);
        }

@Override
public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        try {
        RequestOptions requestOptions = new RequestOptions().
        placeholder(context.getResources().getDrawable(R.drawable.ic_skiddle_placeholder)).
        error(getPlaceholder(holder)).centerCrop();

        Picasso.get().load(inspireMeEventsList.get(position).getImage().
        getString(Constants.DEFAULT)).apply(requestOptions).into(holder.img_main);
        } catch (Exception e){
        e.printStackTrace();
        holder.img_main.setImageDrawable(getPlaceholder(holder));
        }

        holder.rl_container.setOnClickListener(new View.OnClickListener() {
@Override
public void onClick(View v) {
        listener.itemClicked(inspireMeEventsList.get(holder.getAdapterPosition()));
        }
        });

        if(inspireMeEventsList.get(position).getHeaderHex() != null
        || inspireMeEventsList.get(position).getHeaderHex().equals("")){

        try {

        GradientDrawable gd = new GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM,
        new int[]{Color.TRANSPARENT, alterColor(Color.parseColor(
        inspireMeEventsList.get(position).getHeaderHex()), 0.2f)});
        gd.setCornerRadius(0f);

        holder.gradient_view.setBackground(gd);
        } catch (Exception e){
        e.printStackTrace();
        }
        }

        holder.txt_event_name.setText(Html.fromHtml(inspireMeEventsList.get(position).getName()));
        try {
        holder.txt_event_date.setText(getFormattedDate(inspireMeEventsList.get(position).getDate()));
        if(inspireMeEventsList.get(position).getVenue().getString(Constants.NAME).toLowerCase().
        contains(inspireMeEventsList.get(position).getVenue().getString(Constants.TOWN).toLowerCase())){
        holder.txt_location.setText(inspireMeEventsList.get(position).getVenue().getString(Constants.NAME));
        } else {
        holder.txt_location.setText(String.format("%s, %s", inspireMeEventsList.get(position).getVenue().getString(
        Constants.NAME), inspireMeEventsList.get(position).getVenue().getString(Constants.TOWN)));
        }
        } catch (Exception e){
        e.printStackTrace();
        }

        sortTags(holder);
        setGoingToStatus(inspireMeEventsList.get(position).getGoingTo(), holder);

        holder.rl_going_to_container.setOnClickListener(new View.OnClickListener() {
@Override
public void onClick(View v) {
        if(checkConnectionAvailable()){
        switch (inspireMeEventsList.get(holder.getLayoutPosition()).getGoingTo()){
        case 0:
        inspireMeEventsList.get(holder.getLayoutPosition()).setGoingTo(9);
        break;
        case 1:
        inspireMeEventsList.get(holder.getLayoutPosition()).setGoingTo(0);
        break;
        case 9:
        inspireMeEventsList.get(holder.getLayoutPosition()).setGoingTo(1);
        break;
        }
        notifyDataSetChanged();
        listener.goingToClicked(inspireMeEventsList.get(holder.getLayoutPosition()));
        } else {
        listener.goingToClickedNoConnection();
        }
        }
        });
        }

private Drawable getPlaceholder(ViewHolder viewHolder){
        ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) viewHolder.img_main.getLayoutParams();
        params.setMargins(0, 10, 0, 10);
        viewHolder.img_main.setLayoutParams(params);
        return context.getResources().getDrawable(R.drawable.ic_skiddle_placeholder);
        }

private void setGoingToStatus(int status, ViewHolder holder){
        switch (status){
        case 0:
        holder.img_save_event.setImageDrawable(context.getResources().getDrawable(R.drawable.icn_empty_heart));
        break;
        case 1:
        holder.img_save_event.setImageDrawable(context.getResources().getDrawable(R.drawable.icn_going_tick));
        break;
        case 9:
        holder.img_save_event.setImageDrawable(context.getResources().getDrawable(R.drawable.icn_red_heart));
        break;
        }
        }

public List<InspireMeEventsObject> getInspireMeEventsList(){
        return inspireMeEventsList;
        }

@Override
public int getItemCount() {
        return inspireMeEventsList.size();
        }

class ViewHolder extends RecyclerView.ViewHolder {

    private ImageView img_main, img_rounded_frame, img_tag_one, img_tag_two, img_tag_three,
            img_tag_four, gradient_view, img_save_event, img_tag_five;
    private RelativeLayout rl_container;
    private LinearLayout ll_first_tag, ll_second_tag, ll_third_tag,
            ll_fourth_tag, ll_tag_layout, ll_fith_tag;
    private TextView txt_event_name, txt_event_date, txt_location, txt_tag_one, txt_tag_two,
            txt_tag_three, txt_tag_four, txt_tag_five;
    private RelativeLayout rl_going_to_container;

    public ViewHolder(View itemView) {
        super(itemView);
        img_main = itemView.findViewById(R.id.img_main);
        img_rounded_frame = itemView.findViewById(R.id.img_rounded_frame);
        rl_container = itemView.findViewById(R.id.rl_container);
        txt_event_name = itemView.findViewById(R.id.txt_event_name);
        txt_event_date = itemView.findViewById(R.id.txt_event_date);
        txt_location = itemView.findViewById(R.id.txt_location);
        gradient_view = itemView.findViewById(R.id.gradient_view);
        gradient_view.bringToFront();

        ll_first_tag = itemView.findViewById(R.id.ll_first_tag);
        ll_second_tag = itemView.findViewById(R.id.ll_second_tag);
        ll_third_tag = itemView.findViewById(R.id.ll_third_tag);
        ll_fourth_tag = itemView.findViewById(R.id.ll_fourth_tag);
        ll_tag_layout = itemView.findViewById(R.id.ll_tag_layout);
        ll_fith_tag = itemView.findViewById(R.id.ll_fith_tag);

        img_tag_one = itemView.findViewById(R.id.img_tag_one);
        img_tag_two = itemView.findViewById(R.id.img_tag_two);
        img_tag_three = itemView.findViewById(R.id.img_tag_three);
        img_tag_four = itemView.findViewById(R.id.img_tag_four);
        img_save_event = itemView.findViewById(R.id.img_save_event);
        img_tag_five = itemView.findViewById(R.id.img_tag_five);

        txt_tag_one = itemView.findViewById(R.id.txt_tag_one);
        txt_tag_two = itemView.findViewById(R.id.txt_tag_two);
        txt_tag_three = itemView.findViewById(R.id.txt_tag_three);
        txt_tag_four = itemView.findViewById(R.id.txt_tag_four);
        txt_tag_five = itemView.findViewById(R.id.txt_tag_five);

        rl_going_to_container = itemView.findViewById(R.id.rl_going_to_container);

        ll_tag_layout.bringToFront();
        rl_going_to_container.bringToFront();
    }
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
        for(int i = 0; i < splitDate.length; i++){
            try {
                int dateNumber = Integer.parseInt(splitDate[i]);
                splitDate[i] = splitDate[i] + getDayOfMonthSuffix(dateNumber);
                break;
            } catch (Exception e){
                e.printStackTrace();
            }
        }

        return buildFullDateFromArray(splitDate);
    }

    private String buildFullDateFromArray(String[] splitDate){
        String completeDate = "";

        for(int i = 0; i < splitDate.length; i++){
            completeDate =  completeDate + " " + splitDate[i];
        }

        return completeDate.trim();
    }

    private String getDayOfMonthSuffix(final int n) {
        checkArgument(n >= 1 && n <= 31, "illegal day of month: " + n);
        if (n >= 11 && n <= 13) {
            return "th";
        }
        switch (n % 10) {
            case 1:  return "st";
            case 2:  return "nd";
            case 3:  return "rd";
            default: return "th";
        }
    }


    private void sortTags(ViewHolder viewHolder){
        try {
            JSONArray jsonArray = inspireMeEventsList.get(viewHolder.getLayoutPosition()).getTags();
            for(int i = 0; i < jsonArray.length(); i++){
                if(i == 0){
                    setTagAfter(viewHolder.txt_tag_one, viewHolder.ll_first_tag, viewHolder.img_tag_one, jsonArray.getJSONObject(i));
                } else if(i == 1){
                    setTagAfter(viewHolder.txt_tag_two, viewHolder.ll_second_tag, viewHolder.img_tag_two, jsonArray.getJSONObject(i));
                } else if(i == 2){
                    setTagAfter(viewHolder.txt_tag_three, viewHolder.ll_third_tag, viewHolder.img_tag_three, jsonArray.getJSONObject(i));
                } else if(i == 3){
                    setTagAfter(viewHolder.txt_tag_four, viewHolder.ll_fourth_tag, viewHolder.img_tag_four, jsonArray.getJSONObject(i));
                } else if(i == 4){
                    setTagAfter(viewHolder.txt_tag_five, viewHolder.ll_fith_tag, viewHolder.img_tag_five, jsonArray.getJSONObject(i));
                }
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    private void setTagAfter(TextView textView, LinearLayout linearLayout, ImageView imageView,
                             JSONObject object) throws Exception{
        switch (object.getString(SyncStateContract.Constants.TYPE)){
            case SyncStateContract.Constants.FEATURED:
                imageView.setImageDrawable(context.getResources().getDrawable(R.drawable.icn_featured));
                textView.setText("Featured");
                linearLayout.setBackground(context.getResources().getDrawable(R.drawable.tag_featured));
                linearLayout.setAlpha(0.9f);
                break;
            case Constants.CHOSEN:
                imageView.setImageDrawable(context.getResources().getDrawable(R.drawable.icn_chosen_for_you));
                textView.setText("Chosen For You");
                linearLayout.setBackground(context.getResources().getDrawable(R.drawable.tag_chosen_for_you));
                linearLayout.setAlpha(0.9f);
                break;
            case Constants.POPULAR:
                imageView.setImageDrawable(context.getResources().getDrawable(R.drawable.icn_popular));
                textView.setText("Popular: " + object.getString(Constants.DETAIL) + " going");
                linearLayout.setBackground(context.getResources().getDrawable(R.drawable.tag_inspire_me));
                linearLayout.setAlpha(0.9f);
                break;
            case Constants.INSPIRE_ME:
                imageView.setImageDrawable(context.getResources().getDrawable(R.drawable.icn_inspire_me));
                textView.setText("Inspire Me");
                linearLayout.setBackground(context.getResources().getDrawable(R.drawable.tag_inspire_me));
                linearLayout.setAlpha(0.9f);
                break;
        }
    }

    private boolean checkConnectionAvailable(){
        ConnectivityManager connectivityManager
                = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null;
    }

}
