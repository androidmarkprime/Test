package com.example.markprime.test.Checkout;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.markprime.test.Model.PaymentObject;
import com.example.markprime.test.R;

import java.util.List;

public class PaymentAdapter extends RecyclerView.Adapter<PaymentAdapter.ViewHolder>{

    private Context context;
    private List<PaymentObject> paymentObjects;
    private PaymentAdapterListener paymentAdapterListener;

    public PaymentAdapter (Context context, List<PaymentObject> paymentObjects){

        this.context = context;
        this.paymentObjects = paymentObjects;

    }



    @NonNull
    @Override
    public PaymentAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.model_summary_payment, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PaymentAdapter.ViewHolder holder, int position) {
        holder.tv_quantity_tickets.setText(paymentObjects.get(position).getTv_quantity_tickets());
        holder.tv_ticket_details.setText(paymentObjects.get(position).getTv_ticket_details());
        holder.tv_ticket_subtotal.setText(paymentObjects.get(position).getTv_ticket_subtotal());
    }

    @Override
    public int getItemCount() {
        return paymentObjects.size();
    }




    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tv_quantity_tickets, tv_ticket_details, tv_ticket_subtotal ;


        public ViewHolder(View itemView) {
            super(itemView);

            tv_quantity_tickets = itemView.findViewById(R.id.tv_quantity_tickets);
            tv_ticket_details =itemView.findViewById(R.id.tv_ticket_details);
            tv_ticket_subtotal = itemView.findViewById(R.id.tv_ticket_subtotal);
            }
    }

//    public static void addPaymentLinetoSummary() {
//
//
//
//
//    }

    public static void removePaymentLineFromSummary() {


    }

}
