package com.project.kanyequotes.quotes;

import com.project.kanyequotes.models.QuotesResponse;
import com.project.kanyequotes.network.GetQuotesApi;

import javax.inject.Inject;


import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class GetQuotesPresenter implements GetQuotesContract.Presenter {

	private GetQuotesContract.View view;
	private GetQuotesApi quotesApi;
	private CompositeDisposable compositeDisposable;

	@Inject
	public GetQuotesPresenter(GetQuotesApi quotesApi) {
		this.quotesApi = quotesApi;
		compositeDisposable = new CompositeDisposable();
	}

	public void getRandomQuote() {
		view.hideContentView();
		view.showLoadingView();
		quotesApi.getQuotesResponse().subscribeOn(Schedulers.io())
				.observeOn(AndroidSchedulers.mainThread())
				.subscribe(new Observer<QuotesResponse>() {
					@Override
					public void onSubscribe(Disposable d) {
						compositeDisposable.add(d);
					}

					@Override
					public void onNext(QuotesResponse quotesResponse) {
						view.hideLoadingView();
						view.showContentView();
						view.setContent(quotesResponse.getQuote());
					}

					@Override
					public void onError(Throwable e) {

					}

					@Override
					public void onComplete() {
						compositeDisposable.clear();
					}
				});
	}

	@Override
	public void attachView(GetQuotesContract.View view) {
		this.view = view;
	}

	@Override
	public void detachView(boolean retainInstance) {

	}

	@Override
	public void detachView() {

	}

	@Override
	public void destroy() {
		view = null;
	}
}
