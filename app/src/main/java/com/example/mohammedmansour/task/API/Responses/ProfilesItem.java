package com.example.mohammedmansour.task.API.Responses;


import com.google.gson.annotations.SerializedName;

public class ProfilesItem{

	@SerializedName("file_path")
	private String filePath;

	@SerializedName("aspect_ratio")
	private double aspectRatio;

	@SerializedName("vote_average")
	private double voteAverage;

	@SerializedName("width")
	private int width;

	@SerializedName("iso_639_1")
	private Object iso6391;

	@SerializedName("vote_count")
	private int voteCount;

	@SerializedName("height")
	private int height;

	public void setFilePath(String filePath){
		this.filePath = filePath;
	}

	public String getFilePath(){
		return filePath;
	}

	public void setAspectRatio(double aspectRatio){
		this.aspectRatio = aspectRatio;
	}

	public double getAspectRatio(){
		return aspectRatio;
	}

	public void setVoteAverage(double voteAverage){
		this.voteAverage = voteAverage;
	}

	public double getVoteAverage(){
		return voteAverage;
	}

	public void setWidth(int width){
		this.width = width;
	}

	public int getWidth(){
		return width;
	}

	public void setIso6391(Object iso6391){
		this.iso6391 = iso6391;
	}

	public Object getIso6391(){
		return iso6391;
	}

	public void setVoteCount(int voteCount){
		this.voteCount = voteCount;
	}

	public int getVoteCount(){
		return voteCount;
	}

	public void setHeight(int height){
		this.height = height;
	}

	public int getHeight(){
		return height;
	}

	@Override
 	public String toString(){
		return 
			"ProfilesItem{" + 
			"file_path = '" + filePath + '\'' + 
			",aspect_ratio = '" + aspectRatio + '\'' + 
			",vote_average = '" + voteAverage + '\'' + 
			",width = '" + width + '\'' + 
			",iso_639_1 = '" + iso6391 + '\'' + 
			",vote_count = '" + voteCount + '\'' + 
			",height = '" + height + '\'' + 
			"}";
		}
}