package com.example.markprime.test;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

class InfoDialog extends Dialog {

    public Context context;
    public Dialog dialog;
    public Button button;
    public TextView tv_info, tv_title;
    public String title, info;
    private final DialogListener dialogListener;


    public InfoDialog(Activity activity){
        super(activity);
        this.context = activity;
        this.dialogListener = null;
    }

    public InfoDialog(Context activity, String title, String info){
        super(activity);
        this.context = activity;
        this.info = info;
        this.title = title;
        this.dialogListener = null;
    }

    public InfoDialog(Context activity, String title, String info,
                      @Nullable DialogListener listener){
        super(activity);
        this.context = activity;
        this.info = info;
        this.title = title;
        this.dialogListener = listener;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        setContentView(R.layout.dialog_info);

        button = (Button) findViewById(R.id.btn_popup);
        tv_title = findViewById(R.id.tv_popup_title);
        tv_info = findViewById(R.id.tv_popup_info);

        tv_title.setText(title);
        tv_info.setText(info);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(dialogListener !=null){
                    dialogListener.onResult(true);
                }
                dismiss();
            }
        });

    }


}
