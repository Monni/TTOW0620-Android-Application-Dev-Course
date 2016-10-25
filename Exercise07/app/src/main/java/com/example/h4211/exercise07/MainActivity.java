package com.example.h4211.exercise07;

import android.app.ProgressDialog;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Window;
import android.view.WindowManager;

import java.util.List;

public class MainActivity extends AppCompatActivity implements asyncresponse {
    private List<Kentta> kenttaList;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        progressDialog = ProgressDialog.show(this, "Härvätään", "Juostaan viheriöillä");

        AsyncJsonParser asyncJsonParser = new AsyncJsonParser();
        asyncJsonParser.delegate = this;
        asyncJsonParser.execute("http://ptm.fi/jamk/android/golfcourses/golf_courses.json");

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        CollapsingToolbarLayout collapsingToolbarLayout;
        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbarLayout.setTitle("SGKY - Kentät");
    }

    @Override
    public void onJSONparserComplete(List<Kentta> kenttaList){
        this.kenttaList = kenttaList;
        mAdapter = new kenttaAdapter(this, kenttaList);
        mRecyclerView.setAdapter(mAdapter);
        progressDialog.dismiss();
    }
}
