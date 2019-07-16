package com.project.kanyequotes.quotes;

import com.hannesdorfmann.mosby3.mvp.MvpPresenter;
import com.hannesdorfmann.mosby3.mvp.MvpView;

public interface GetQuotesContract {

	interface View extends MvpView {

		void showLoadingView();

		void showContentView();

		void hideLoadingView();

		void hideContentView();

		void setContent(String response);

	}

	interface Presenter extends MvpPresenter<View> {

	}
}
