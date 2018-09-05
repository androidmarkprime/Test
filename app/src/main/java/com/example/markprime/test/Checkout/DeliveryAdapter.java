package com.example.markprime.test.Checkout;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.markprime.test.Model.DeliveryObject;
import com.example.markprime.test.R;

import java.util.List;

public class DeliveryAdapter extends RecyclerView.Adapter<DeliveryAdapter.ViewHolder> {

    private Context context;
    private List<DeliveryObject> deliveryObjects;
    private DeliveryAdapterListener deliveryAdapterListener;
    public boolean RapidScan = false, Posted = false, Collect = false;

    public DeliveryAdapter (Context context, List<DeliveryObject> deliveryObjects, DeliveryAdapterListener deliveryAdapterListener){
        this.context = context;
        this.deliveryObjects = deliveryObjects;
        this.deliveryAdapterListener = deliveryAdapterListener;

    }


    @NonNull
    @Override
    public DeliveryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.model_delivery_options, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final DeliveryAdapter.ViewHolder holder, final int position) {

        holder.iv_delivery_image.setImageDrawable(deliveryObjects.get(position).getIv_delivery_image());
        holder.tv_delivery_name.setText(deliveryObjects.get(position).getTv_delivery_name());
        holder.tv_delivery_description.setText(deliveryObjects.get(position).getTv_delivery_description());
        holder.tv_delivery_price.setText(deliveryObjects.get(position).getTv_delivery_price());

        holder.ll_delivery_options.setOnClickListener(new View.OnClickListener() {



            @Override
            public void onClick(View view) {


                deliveryAdapterListener.deliveryClicked(deliveryObjects.get(holder.getAdapterPosition()));


                if(deliveryObjects.get(holder.getAdapterPosition()).getTv_delivery_name().contains("RapidScan")){


                    if(!RapidScan){

                        RapidScan = true; Posted = false; Collect = false;

                        holder.ll_delivery_options.setBackground(
                                context.getResources().getDrawable(
                                        R.drawable.ticket_deliver_seleted));

                    } else {

                        RapidScan = false; Posted = false; Collect = false;

                        holder.ll_delivery_options.setBackground(
                                context.getResources().getDrawable(
                                        R.drawable.ticket_deliver_white));

                    }

                } else if (deliveryObjects.get(holder.getAdapterPosition()).getTv_delivery_name().contains("Posted")){

                    if(!Posted){

                        Posted = true; RapidScan = false; Collect = false;

                        holder.ll_delivery_options.setBackground(
                                context.getResources().getDrawable(
                                        R.drawable.ticket_deliver_seleted));

                    } else {

                        RapidScan = false; Posted = false; Collect = false;

                        holder.ll_delivery_options.setBackground(
                                context.getResources().getDrawable(
                                        R.drawable.ticket_deliver_white));

                    }

                } else if (deliveryObjects.get(holder.getAdapterPosition()).getTv_delivery_name().contains("Collect")){

                    if(!Collect){

                        Collect = true; RapidScan = false; Posted = false;

                        holder.ll_delivery_options.setBackground(
                                context.getResources().getDrawable(
                                        R.drawable.ticket_deliver_seleted));

                    } else {

                        RapidScan = false; Posted = false; Collect = false;

                        holder.ll_delivery_options.setBackground(
                                context.getResources().getDrawable(
                                        R.drawable.ticket_deliver_white));

                    }

                    } else {

                        RapidScan = false; Posted = false; Collect = false;

                        holder.ll_delivery_options.setBackground(
                                context.getResources().getDrawable(
                                        R.drawable.ticket_deliver_white));

                    }
            }
        });

    }





    @Override
    public int getItemCount() {
        return deliveryObjects.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView iv_delivery_image;
        private TextView tv_delivery_name;
        private TextView tv_delivery_description;
        private TextView tv_delivery_price;
        private LinearLayout ll_delivery_options;

        public ViewHolder(View itemView) {
            super(itemView);

            iv_delivery_image = itemView.findViewById(R.id.iv_delivery_image);
            tv_delivery_name = itemView.findViewById(R.id.tv_delivery_name);
            tv_delivery_description = itemView.findViewById(R.id.tv_delivery_description);
            tv_delivery_price = itemView.findViewById(R.id.tv_delivery_price);
            ll_delivery_options = itemView.findViewById(R.id.ll_delivery_options);
        }
    }

}
