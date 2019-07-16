package com.project.kanyequotes;

import android.app.Application;
import android.content.Context;

import androidx.multidex.MultiDex;
import androidx.multidex.MultiDexApplication;

import com.project.kanyequotes.dagger.Graph;


public class KanyeQuotesApplication extends MultiDexApplication {
	private static KanyeQuotesApplication instance;
	private static Context context;
	private Graph component;

	@Override
	public void onCreate() {
		super.onCreate();
		context = this;
		init();
	}

	private void init() {
		instance = this;
		MultiDex.install(getInstanceContext());
		component = Graph.Initializer.initialize((Application) getInstanceContext());
	}

	public static KanyeQuotesApplication getInstance() {
		return instance;
	}

	public static Context getInstanceContext() {
		return context;
	}

	public Graph getComponent() {
		return component;
	}

	@Override
	protected void attachBaseContext(Context base) {
		super.attachBaseContext(base);
		MultiDex.install(this);
	}
}
