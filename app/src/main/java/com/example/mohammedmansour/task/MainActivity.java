package com.example.mohammedmansour.task;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.example.mohammedmansour.task.API.APIManager;
import com.example.mohammedmansour.task.API.Responses.PopularPeopleResponse;
import com.example.mohammedmansour.task.API.Responses.ResultsItem;
import com.example.mohammedmansour.task.Adapters.PopularPeopleAdapter;
import com.example.mohammedmansour.task.Base.BaseActivity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends BaseActivity {
    private final static String api_key = "e43a6b476e90d7e81a60d04e7ab6192f";
    RecyclerView recyclerView;
    PopularPeopleAdapter popularPeopleAdapter;
    GridLayoutManager gridLayoutManager;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.persons);

        recyclerView = findViewById(R.id.popular_people_rv);
        int noOfcolumns = calculateNoOfColumns(this);
        gridLayoutManager = new GridLayoutManager(this,noOfcolumns);
        popularPeopleAdapter = new PopularPeopleAdapter(null,this);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(popularPeopleAdapter);
        getPopularPeople();
        popularPeopleAdapter.setOnPersonClickListener(new PopularPeopleAdapter.OnPersonClickListener() {
            @Override
            public void onPersonClick(int position, ResultsItem resultsItem) {
                Intent intent = new Intent(activity,PersonActivity.class);
                intent.putExtra("resultItem",resultsItem);
                startActivity(intent);
            }
        });
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,SearchActivity.class));
            }
        });
    }
    public void getPopularPeople(){
        ShowProgressBar();
        APIManager.getServices()
                .getPopularPeople(api_key)
                .enqueue(new Callback<PopularPeopleResponse>() {
                    @Override
                    public void onResponse(Call<PopularPeopleResponse> call, Response<PopularPeopleResponse> response) {
                        HideProgressBar();
                        popularPeopleAdapter.setUpdatedData(response.body().getResults());
                    }

                    @Override
                    public void onFailure(Call<PopularPeopleResponse> call, Throwable t) {
                        HideProgressBar();
                        ShowMessage("Error",t.getLocalizedMessage());
                    }
                });
    }
    public static int calculateNoOfColumns(Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        float dpWidth = displayMetrics.widthPixels / displayMetrics.density;
        int scalingFactor = 180;
        int noOfColumns = (int) (dpWidth / scalingFactor);
        return noOfColumns;
    }

}
