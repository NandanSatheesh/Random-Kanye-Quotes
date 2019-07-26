package com.project.kanyequotes;

import android.app.Application;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;

import com.bluelinelabs.conductor.Conductor;
import com.bluelinelabs.conductor.Router;
import com.bluelinelabs.conductor.RouterTransaction;
import com.project.kanyequotes.dagger.AppComponent;
import com.project.kanyequotes.dagger.DaggerGraph;
import com.project.kanyequotes.dagger.Graph;
import com.project.kanyequotes.databinding.ActivityLandingBinding;
import com.project.kanyequotes.quotes.GetQuotesController;

public class LandingActivity extends BaseActivity {
	ActivityLandingBinding activityLandingBinding;
	Router router;
	private static LandingActivity instance;
	private AppComponent component;
	private Graph graphComponent;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		init();
		activityLandingBinding = DataBindingUtil.setContentView(this, R.layout.activity_landing);
		initToolbar();
		router = Conductor.attachRouter(this, activityLandingBinding.conductor, savedInstanceState);
		if (!router.hasRootController()) {
			router.setRoot(RouterTransaction.with(new GetQuotesController()));
		}
	}

	private void init() {
		instance = this;
		component = DaggerGraph.builder().build();
		graphComponent = Graph.Initializer.initialize((Application) getApplication());
		component.inject(this);
	}


	private void initToolbar() {
		setSupportActionBar(activityLandingBinding.toolbarHolder.toolbar);
		getSupportActionBar().setDisplayShowTitleEnabled(false);
	}

	@Override
	protected void setScreenTitle(String title) {
		activityLandingBinding.toolbarHolder.toolbar.setTitle(null);
	}

	@Override
	protected void hideToolbar() {
		getSupportActionBar().hide();
	}

	@Override
	public void onBackPressed() {
		if (!router.handleBack()) {
			super.onBackPressed();
		}
	}

	public static LandingActivity getInstance() {
		return instance;
	}

	public Graph getGraphComponent() {
		return graphComponent;
	}
}
