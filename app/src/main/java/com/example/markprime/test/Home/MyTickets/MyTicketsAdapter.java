package com.example.markprime.test.Home.MyTickets;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.markprime.test.Model.EventObject;
import com.example.markprime.test.R;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import static android.view.View.GONE;

public class MyTicketsAdapter extends RecyclerView.Adapter<MyTicketsAdapter.ViewHolder> {

    private Context context;
    private List<EventObject> eventObjects;
    private boolean showTicket = false;


    public MyTicketsAdapter (Context context, List<EventObject> eventObjects){
        this.context = context;
        this.eventObjects = eventObjects;
    }

    @NonNull
    @Override
    public MyTicketsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.model_ticket_item, parent, false);
        return new MyTicketsAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {

    Picasso.get()
                .load(eventObjects.get(position).getImageUrl())
                .noFade()
                .into(holder.iv_event_image);
        holder.tv_event_name.setText(eventObjects.get(position).getEventName());
        try {
            holder.tv_event_date.setText(getFormattedDate(eventObjects.get(position).getDate()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        holder.tv_ticket_price.setText(eventObjects.get(position).getEntryPrice());

        holder.rl_ticket_item_main.setOnClickListener(new View.OnClickListener(){
            @Override public void onClick(View view){
                    if(holder.rl_ticket_details.getVisibility() == GONE){
                    holder.rl_ticket_details.setVisibility(View.VISIBLE);
                    } else{
                        holder.rl_ticket_details.setVisibility(GONE);
                    }

                }

        });

        }

    @Override
    public int getItemCount() {
        return eventObjects.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView iv_event_image;
        private TextView tv_event_name, tv_event_date, tv_ticket_price;

        private RelativeLayout rl_ticket_item_main, rl_ticket_details;


        public ViewHolder(View itemView) {
            super(itemView);

            iv_event_image = itemView.findViewById(R.id.iv_event_image);
            tv_event_name = itemView.findViewById(R.id.tv_event_name);
            tv_event_date = itemView.findViewById(R.id.tv_event_date);
            tv_ticket_price = itemView.findViewById(R.id.tv_ticket_price);

            rl_ticket_item_main = itemView.findViewById(R.id.rl_ticket_item_main);
            rl_ticket_details = itemView.findViewById(R.id.rl_ticket_details);
        }
    }

    private String getFormattedDate(String dateString) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.UK);
        Date newDate = format.parse(dateString);
        format = new SimpleDateFormat("EEEE dd MMMM yyyy", Locale.UK);
        return format.format(newDate);
    }
}
