package com.example.markprime.test.MultiRE;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.markprime.test.R;

import java.util.ArrayList;
import java.util.List;

public class Multi extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi);

        RecyclerView recyclerView = findViewById(R.id.re_multi);
        recyclerView.setLayoutManager(new LinearLayoutManager
                (this, LinearLayoutManager.VERTICAL, false)
        );

        List<Content> contents = new ArrayList<>();
        contents.add(new Content(0));
        contents.add(new Content(3));
        contents.add(new Content(1));
        contents.add(new Content(2));
        contents.add(new Content(3));
        contents.add(new Content(2));
        contents.add(new Content(3));
        contents.add(new Content(2));
        contents.add(new Content(3));
        contents.add(new Content(1));
        contents.add(new Content(2));

        MultiElementAdapter adapter = new MultiElementAdapter(contents);
        recyclerView.setAdapter(adapter);
    }


}
