package com.example.mohammedmansour.task;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.mohammedmansour.task.API.APIManager;
import com.example.mohammedmansour.task.API.Responses.PersonImagesResponse;
import com.example.mohammedmansour.task.API.Responses.PersonResponse;
import com.example.mohammedmansour.task.API.Responses.ProfilesItem;
import com.example.mohammedmansour.task.API.Responses.ResultsItem;
import com.example.mohammedmansour.task.Adapters.PersonAdapter;
import com.example.mohammedmansour.task.Adapters.PopularPeopleAdapter;
import com.example.mohammedmansour.task.Base.BaseActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PersonActivity extends BaseActivity {
    ResultsItem resultsItem = null;
    private final static String api_key = "e43a6b476e90d7e81a60d04e7ab6192f";

    int personID;
    RatingBar ratingBar;
    TextView birthday, deathday, genre, biography;
    float rating;



    RecyclerView recyclerView;
    PersonAdapter personAdapter;
    GridLayoutManager gridLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        resultsItem = getIntent().getParcelableExtra("resultItem");
        getSupportActionBar().setTitle(resultsItem.getName());
        birthday = findViewById(R.id.birthday_tv);
        deathday = findViewById(R.id.deathday_tv);
        genre = findViewById(R.id.genre_tv);
        biography = findViewById(R.id.biography_tv);
        ratingBar = findViewById(R.id.rating_value);

        personID = resultsItem.getId();
        // Log.e("PersonID", "" + resultsItem.getId());
        APIManager.getServices().getPeople(personID, api_key)
                .enqueue(new Callback<PersonResponse>() {
                    @Override
                    public void onResponse(Call<PersonResponse> call, Response<PersonResponse> response) {
                        birthday.setText(response.body().getBirthday());
                        if (response.body().getDeathday() == null)
                            deathday.setText("None");
                        else
                            deathday.setText("" + response.body().getDeathday());
                        genre.setText(response.body().getKnownForDepartment());
                        biography.setText(response.body().getBiography());
                        ratingBar.setRating((float) response.body().getPopularity());
                    }

                    @Override
                    public void onFailure(Call<PersonResponse> call, Throwable t) {

                    }
                });
        recyclerView = findViewById(R.id.images_rv);
        recyclerView.setNestedScrollingEnabled(false);
        int noOfcolumns = calculateNoOfColumns(this);
        gridLayoutManager = new GridLayoutManager(this, noOfcolumns);
        personAdapter = new PersonAdapter(null, this);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(personAdapter);
        getPersonImages();

        personAdapter.setOnImageClickListener(new PersonAdapter.OnImageClickListener() {
            @Override
            public void onImageClick(ProfilesItem profilesItem, int position) {
                FullImageFragment fullImageFragment = new FullImageFragment();
                fullImageFragment.setImageUrl(profilesItem.getFilePath());
                fullImageFragment.show(getSupportFragmentManager(),"Full Image");
            }
        });

    }

    public void getPersonImages() {
        //ShowProgressBar();
        APIManager.getServices().getPersonImages(personID, api_key)
                .enqueue(new Callback<PersonImagesResponse>() {
                    @Override
                    public void onResponse(Call<PersonImagesResponse> call, Response<PersonImagesResponse> response) {
                        //HideProgressBar();
                        personAdapter.setUpdatedData(response.body().getProfiles());
                    }

                    @Override
                    public void onFailure(Call<PersonImagesResponse> call, Throwable t) {
                        //HideProgressBar();
                        ShowMessage("error", t.getLocalizedMessage());
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
