package com.project.kanyequotes.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class QuotesResponse implements Parcelable {

	@SerializedName("quote")
	@Expose
	private String quote;

	protected QuotesResponse(Parcel in) {
		quote = in.readString();
	}

	public static final Creator<QuotesResponse> CREATOR = new Creator<QuotesResponse>() {
		@Override
		public QuotesResponse createFromParcel(Parcel in) {
			return new QuotesResponse(in);
		}

		@Override
		public QuotesResponse[] newArray(int size) {
			return new QuotesResponse[size];
		}
	};

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(quote);
	}

	public String getQuote() {
		return quote;
	}

	public void setQuote(String quote) {
		this.quote = quote;
	}
}
