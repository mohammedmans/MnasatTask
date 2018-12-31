package com.example.mohammedmansour.task.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.mohammedmansour.task.API.Responses.ResultsItem;
import com.example.mohammedmansour.task.R;

import java.util.List;

public class PopularPeopleAdapter extends RecyclerView.Adapter<PopularPeopleAdapter.customViewHolder> {

    List<ResultsItem>resultsItems;
    Context context;
    OnPersonClickListener onPersonClickListener;

    public OnPersonClickListener getOnPersonClickListener() {
        return onPersonClickListener;
    }

    public void setOnPersonClickListener(OnPersonClickListener onPersonClickListener) {
        this.onPersonClickListener = onPersonClickListener;
    }

    public PopularPeopleAdapter(List<ResultsItem> resultsItems, Context context) {
        this.resultsItems = resultsItems;
        this.context = context;
    }

    @NonNull
    @Override
    public customViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.people_item_view,parent,false);
        customViewHolder customViewHolder = new customViewHolder(view);
        return customViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull customViewHolder customViewHolder, final int i) {
        final ResultsItem resultsItem = resultsItems.get(i);
        customViewHolder.people_name.setText((CharSequence) resultsItem.getName());
        String imgBaseUrl = "https://image.tmdb.org/t/p/w500";
        imgBaseUrl = imgBaseUrl + resultsItem.getProfilePath();
        Glide.with(context).load(imgBaseUrl).into(customViewHolder.profile_path_img);
        customViewHolder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onPersonClickListener!=null)
                    onPersonClickListener.onPersonClick(i,resultsItem);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (resultsItems==null)return 0;
        return resultsItems.size();
    }
    public void setUpdatedData(List<ResultsItem>resultsItems){
        this.resultsItems = resultsItems;
        notifyDataSetChanged();
    }

    class customViewHolder extends RecyclerView.ViewHolder{
        TextView people_name;
        ImageView profile_path_img;
        View parent;
        public customViewHolder(@NonNull View view) {
            super(view);
            people_name = view.findViewById(R.id.peo_name_tv);
            profile_path_img = view.findViewById(R.id.profile_path_img);
            parent = view;
        }
    }
    public interface OnPersonClickListener{
        void onPersonClick(int position , ResultsItem resultsItem);
    }
}
