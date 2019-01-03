package com.example.mohammedmansour.task.API.Responses;

import java.util.List;

import com.google.gson.annotations.SerializedName;


public class PersonImagesResponse{

	@SerializedName("profiles")
	private List<ProfilesItem> profiles;

	@SerializedName("id")
	private int id;

	public void setProfiles(List<ProfilesItem> profiles){
		this.profiles = profiles;
	}

	public List<ProfilesItem> getProfiles(){
		return profiles;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	@Override
 	public String toString(){
		return 
			"PersonImagesResponse{" + 
			"profiles = '" + profiles + '\'' + 
			",id = '" + id + '\'' + 
			"}";
		}
}