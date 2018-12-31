package com.example.mohammedmansour.task.API.Responses;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

import com.google.gson.annotations.SerializedName;


public class PopularPeopleResponse implements Parcelable {

	@SerializedName("page")
	private int page;

	@SerializedName("total_pages")
	private int totalPages;

	@SerializedName("results")
	private List<ResultsItem> results;

	@SerializedName("total_results")
	private int totalResults;

	protected PopularPeopleResponse(Parcel in) {
		page = in.readInt();
		totalPages = in.readInt();
		results = in.createTypedArrayList(ResultsItem.CREATOR);
		totalResults = in.readInt();
	}

	public static final Creator<PopularPeopleResponse> CREATOR = new Creator<PopularPeopleResponse>() {
		@Override
		public PopularPeopleResponse createFromParcel(Parcel in) {
			return new PopularPeopleResponse(in);
		}

		@Override
		public PopularPeopleResponse[] newArray(int size) {
			return new PopularPeopleResponse[size];
		}
	};

	public void setPage(int page){
		this.page = page;
	}

	public int getPage(){
		return page;
	}

	public void setTotalPages(int totalPages){
		this.totalPages = totalPages;
	}

	public int getTotalPages(){
		return totalPages;
	}

	public void setResults(List<ResultsItem> results){
		this.results = results;
	}

	public List<ResultsItem> getResults(){
		return results;
	}

	public void setTotalResults(int totalResults){
		this.totalResults = totalResults;
	}

	public int getTotalResults(){
		return totalResults;
	}

	@Override
 	public String toString(){
		return 
			"PopularPeopleResponse{" + 
			"page = '" + page + '\'' + 
			",total_pages = '" + totalPages + '\'' + 
			",results = '" + results + '\'' + 
			",total_results = '" + totalResults + '\'' + 
			"}";
		}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(page);
		dest.writeInt(totalPages);
		dest.writeTypedList(results);
		dest.writeInt(totalResults);
	}
}