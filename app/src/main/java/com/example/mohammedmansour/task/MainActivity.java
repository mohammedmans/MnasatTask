package com.example.mohammedmansour.task;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
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
    LinearLayoutManager linearLayoutManager;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recyclerView = findViewById(R.id.popular_people_rv);
        linearLayoutManager = new LinearLayoutManager(this);
        popularPeopleAdapter = new PopularPeopleAdapter(null,this);
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
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

        APIManager.getServices()
                .getPopularPeople(api_key)
                .enqueue(new Callback<PopularPeopleResponse>() {
                    @Override
                    public void onResponse(Call<PopularPeopleResponse> call, Response<PopularPeopleResponse> response) {

                        popularPeopleAdapter.setUpdatedData(response.body().getResults());
                    }

                    @Override
                    public void onFailure(Call<PopularPeopleResponse> call, Throwable t) {
                        ShowMessage("Error",t.getLocalizedMessage());
                    }
                });
    }

}
