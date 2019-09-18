package com.project.kanyequotes.quotes;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;

import com.hannesdorfmann.mosby3.mvp.conductor.MvpController;
import com.project.kanyequotes.KanyeQuotesApplication;
import com.project.kanyequotes.R;
import com.project.kanyequotes.ViewUtils;
import com.project.kanyequotes.databinding.ConductorDisplayQuotesBinding;


public class GetQuotesController extends MvpController<GetQuotesContract.View, GetQuotesPresenter>
		implements GetQuotesContract.View {

	private ConductorDisplayQuotesBinding binding;

	@NonNull
	@Override
	protected View onCreateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container) {
		binding = DataBindingUtil.inflate(inflater, R.layout.conductor_display_quotes, container, false);
		return binding.getRoot();
	}

	@Override
	protected void onAttach(@NonNull View view) {
		super.onAttach(view);
		init();
		getPresenter().getRandomQuote();
	}

	private void init() {
		binding.refreshData.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				getPresenter().getRandomQuote();
			}
		});
	}

	@Override
	public void setContent(String response) {
		binding.quote.setText(response);
	}

	@Override
	public void showLoadingView() {
		ViewUtils.setVisible(binding.loadingAnimationView);
		binding.loadingAnimationView.playAnimation();
	}

	@Override
	public void showContentView() {
		ViewUtils.setVisible(binding.quote);
	}

	@Override
	public void hideLoadingView() {
		binding.loadingAnimationView.cancelAnimation();
		ViewUtils.setGone(binding.loadingAnimationView);

	}

	@Override
	public void hideContentView() {
		ViewUtils.setGone(binding.quote);
	}

	@NonNull
	@Override
	public GetQuotesPresenter createPresenter() {
		return KanyeQuotesApplication.getInstance().getComponent().getQuotesPresenter();
	}

	@NonNull
	@Override
	public GetQuotesPresenter getPresenter() {
		return super.getPresenter();
	}
}
