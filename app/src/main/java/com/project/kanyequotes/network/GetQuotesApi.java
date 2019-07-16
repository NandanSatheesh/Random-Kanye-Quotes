package com.project.kanyequotes.network;

import com.project.kanyequotes.models.QuotesResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface GetQuotesApi {

	@GET("/")
	Observable<QuotesResponse> getQuotesResponse();
}
