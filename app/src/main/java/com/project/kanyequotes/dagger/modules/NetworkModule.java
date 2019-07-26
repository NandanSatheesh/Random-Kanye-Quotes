package com.project.kanyequotes.dagger.modules;


import com.project.kanyequotes.network.ApiEndPoints;
import com.project.kanyequotes.network.GetQuotesApi;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class NetworkModule {

	@Provides
	@Singleton
	GetQuotesApi getQuotesApiHandler(Retrofit retroFit) {
		return retroFit.create(GetQuotesApi.class);
	}

	@Provides
	@Singleton
	Retrofit providesRetrofit(OkHttpClient okHttpClient) {
		return new Retrofit.Builder()
				.baseUrl(ApiEndPoints.API_URL)
				.addConverterFactory(GsonConverterFactory.create())
				.addCallAdapterFactory(RxJava2CallAdapterFactory.create())
				.client(okHttpClient)
				.build();
	}

	@Provides
	@Singleton
	OkHttpClient providesOkHttpClient(HttpLoggingInterceptor httpLoggingInterceptor) {
		return new OkHttpClient.Builder()
				.addInterceptor(httpLoggingInterceptor)
				.build();
	}

	@Provides
	@Singleton
	HttpLoggingInterceptor providesHttpLoggingInterceptor() {
		HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
		httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
		return httpLoggingInterceptor;
	}
}
