package com.example.mohammedmansour.task.API;

import com.example.mohammedmansour.task.API.Responses.PopularPeopleResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Services {
    @GET("person/popular")
    Call<PopularPeopleResponse>getPopularPeople(@Query("api_key")String api_key);
}
