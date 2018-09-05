package com.example.markprime.test.Checkout;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.markprime.test.Model.OptOutObject;
import com.example.markprime.test.R;

import java.util.List;

public class OptOutAdapter extends RecyclerView.Adapter<OptOutAdapter.ViewHolder>{

    private Context context;
    private List<OptOutObject> optOutObjects;
    private OptOutAdapterListener optOutAdapterListener;

    public OptOutAdapter(Context context, List<OptOutObject> optOutObjects){
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
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tv_opt_out.setText(optOutObjects.get(position).getTv_opt_out());

        holder.checkbox.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {

                optOutAdapterListener.optOutClicked();

                optOutObjects.get()
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

        public ViewHolder(View itemView) {
            super(itemView);
            tv_opt_out = itemView.findViewById(R.id.tv_opt_out);
            checkbox = itemView.findViewById(R.id.checkbox);
        }
    }
}
