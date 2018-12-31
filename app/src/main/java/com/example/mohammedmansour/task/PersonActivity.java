package com.example.mohammedmansour.task;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.mohammedmansour.task.API.Responses.ResultsItem;

public class PersonActivity extends AppCompatActivity {
    ResultsItem resultsItem = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        resultsItem = getIntent().getParcelableExtra("resultItem");
        getSupportActionBar().setTitle(resultsItem.getName());
    }

}
