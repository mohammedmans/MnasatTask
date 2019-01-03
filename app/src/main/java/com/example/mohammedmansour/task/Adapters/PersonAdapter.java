package com.example.mohammedmansour.task.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.mohammedmansour.task.API.Responses.ProfilesItem;
import com.example.mohammedmansour.task.R;

import java.util.List;

public class PersonAdapter extends RecyclerView.Adapter<PersonAdapter.customViewHolder> {
    List<ProfilesItem> profilesItems;
    OnImageClickListener onImageClickListener;

    public List<ProfilesItem> getProfilesItems() {
        return profilesItems;
    }

    public OnImageClickListener getOnImageClickListener() {
        return onImageClickListener;
    }

    public void setOnImageClickListener(OnImageClickListener onImageClickListener) {
        this.onImageClickListener = onImageClickListener;
    }

    public void setProfilesItems(List<ProfilesItem> profilesItems) {
        this.profilesItems = profilesItems;
    }

    Context context;

    public PersonAdapter(List<ProfilesItem> profilesItems, Context context) {
        this.profilesItems = profilesItems;
        this.context = context;
    }

    @NonNull
    @Override
    public customViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.person_item_view, parent, false);
        customViewHolder customViewHolder = new customViewHolder(view);
        return customViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull customViewHolder customViewHolder, final int i) {
        final ProfilesItem profilesItem = profilesItems.get(i);
        String imgBaseUrl = "https://image.tmdb.org/t/p/w500";
        imgBaseUrl = imgBaseUrl + profilesItem.getFilePath();
        Glide.with(context).load(imgBaseUrl).into(customViewHolder.imageView);
        customViewHolder.parnet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onImageClickListener != null) {
                    onImageClickListener.onImageClick(profilesItem, i);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        if (profilesItems == null) return 0;
        return profilesItems.size();

    }

    public void setUpdatedData(List<ProfilesItem> profilesItems) {
        this.profilesItems = profilesItems;
        notifyDataSetChanged();
    }

    public class customViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        View parnet;

        public customViewHolder(@NonNull View view) {
            super(view);
            imageView = view.findViewById(R.id.images);
            parnet = view;
        }
    }

    public interface OnImageClickListener {
        void onImageClick(ProfilesItem profilesItem, int position);
    }
}
