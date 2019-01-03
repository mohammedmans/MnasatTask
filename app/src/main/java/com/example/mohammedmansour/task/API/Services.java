package com.example.mohammedmansour.task.API;

import com.example.mohammedmansour.task.API.Responses.PersonImagesResponse;
import com.example.mohammedmansour.task.API.Responses.PersonResponse;
import com.example.mohammedmansour.task.API.Responses.PopularPeopleResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface Services {
    @GET("person/popular")
    Call<PopularPeopleResponse>getPopularPeople(@Query("api_key")String api_key);

    @GET("person/{id}")
    Call<PersonResponse>getPeople(@Path("id") int id,@Query("api_key")String api_key);

    @GET("person/{id}/images")
    Call<PersonImagesResponse>getPersonImages(@Path("id") int id, @Query("api_key")String api_key);


}
