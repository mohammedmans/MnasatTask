package com.example.mohammedmansour.task;


import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;


/**
 * A simple {@link Fragment} subclass.
 */
public class FullImageFragment extends DialogFragment {
    private  static String imgBaseUrl = "https://image.tmdb.org/t/p/w500";
    String url;
    ImageView imageView;


    public FullImageFragment() {
        // Required empty public constructor
    }
    View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_full_image,container,false);
        imageView = view.findViewById(R.id.full_image);
        Glide.with(this).load(imgBaseUrl+url).into(imageView);
        return view;
    }
    public void setImageUrl(String url){
        this.url=url;
    }

}
