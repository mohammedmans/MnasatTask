package com.example.mohammedmansour.task.API.Responses;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

import com.google.gson.annotations.SerializedName;


public class ResultsItem implements Parcelable {

	@SerializedName("popularity")
	private double popularity;

	@SerializedName("known_for")
	private List<KnownForItem> knownFor;

	@SerializedName("name")
	private String name;

	@SerializedName("profile_path")
	private String profilePath;

	@SerializedName("id")
	private int id;

	@SerializedName("adult")
	private boolean adult;

	protected ResultsItem(Parcel in) {
		popularity = in.readDouble();
		name = in.readString();
		profilePath = in.readString();
		id = in.readInt();
		adult = in.readByte() != 0;
	}

	public static final Creator<ResultsItem> CREATOR = new Creator<ResultsItem>() {
		@Override
		public ResultsItem createFromParcel(Parcel in) {
			return new ResultsItem(in);
		}

		@Override
		public ResultsItem[] newArray(int size) {
			return new ResultsItem[size];
		}
	};

	public void setPopularity(double popularity){
		this.popularity = popularity;
	}

	public double getPopularity(){
		return popularity;
	}

	public void setKnownFor(List<KnownForItem> knownFor){
		this.knownFor = knownFor;
	}

	public List<KnownForItem> getKnownFor(){
		return knownFor;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setProfilePath(String profilePath){
		this.profilePath = profilePath;
	}

	public String getProfilePath(){
		return profilePath;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setAdult(boolean adult){
		this.adult = adult;
	}

	public boolean isAdult(){
		return adult;
	}

	@Override
 	public String toString(){
		return 
			"ResultsItem{" + 
			"popularity = '" + popularity + '\'' + 
			",known_for = '" + knownFor + '\'' + 
			",name = '" + name + '\'' + 
			",profile_path = '" + profilePath + '\'' + 
			",id = '" + id + '\'' + 
			",adult = '" + adult + '\'' + 
			"}";
		}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeDouble(popularity);
		dest.writeString(name);
		dest.writeString(profilePath);
		dest.writeInt(id);
		dest.writeByte((byte) (adult ? 1 : 0));
	}
}