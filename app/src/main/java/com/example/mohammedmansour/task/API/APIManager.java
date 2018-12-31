package com.example.mohammedmansour.task.API;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIManager {
    static Retrofit retrofitInstance;
    private static Retrofit getRetrofitInstance(){
        if (retrofitInstance == null){
            retrofitInstance = new Retrofit.Builder()
                    .baseUrl("https://api.themoviedb.org/3/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofitInstance ;
    }
    public static Services getServices(){
        return getRetrofitInstance().create(Services.class);
    }
}
