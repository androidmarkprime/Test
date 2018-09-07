package com.example.markprime.test.Checkout;

import android.content.Context;
import android.content.res.ColorStateList;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.markprime.test.Model.OptOutObject;
import com.example.markprime.test.R;

import java.util.List;

public class OptOutAdapter extends RecyclerView.Adapter<OptOutAdapter.ViewHolder>{

    private Context context;
    private List<OptOutObject> optOutObjects;
    private OptOutAdapterListener optOutAdapterListener;
    private boolean personalised = false, targetted = false;

    public OptOutAdapter(Context context, List<OptOutObject> optOutObjects, OptOutAdapterListener optOutAdapterListener){
        this.context = context;
        this.optOutObjects = optOutObjects;
        this.optOutAdapterListener = optOutAdapterListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.model_opt_out_choices, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        holder.tv_opt_out.setText(optOutObjects.get(position).getTv_opt_out());

        holder.checkbox.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {

                optOutAdapterListener.optOutClicked(optOutObjects.get(holder.getAdapterPosition()));

                if(optOutObjects.get(holder.getAdapterPosition()).getTv_opt_out().contains("personalised")){


                    if(!personalised){
                        personalised = true; targetted = false;

                        holder.ll_optOut.setBackground(
                                context.getResources().getDrawable(
                                        R.drawable.extras_turq_rounded
                                )
                        );


                    } else {

                        personalised = false; targetted = false;

                    }


                } else if (optOutObjects.get(holder.getAdapterPosition()).getTv_opt_out().contains("targetted")) {

                    if(!targetted){
                        targetted = true; personalised = false;

                        holder.ll_optOut.setBackground(
                                context.getResources().getDrawable(
                                        R.drawable.extras_turq_rounded
                                )
                        );

                    }
                } else {
                    personalised = false; targetted = false;

                    holder.ll_optOut.setBackground(
                            context.getResources().getDrawable(
                                    R.drawable.background_rounded_grey
                            )
                    );

                }








            }
        });





    }

    @Override
    public int getItemCount() {
        return optOutObjects.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tv_opt_out;
        private CheckBox checkbox;
        private LinearLayout ll_optOut;

        public ViewHolder(View itemView) {
            super(itemView);
            ll_optOut = itemView.findViewById(R.id.ll_opt_out);
            tv_opt_out = itemView.findViewById(R.id.tv_opt_out);
            checkbox = itemView.findViewById(R.id.checkbox);
        }
    }
}
