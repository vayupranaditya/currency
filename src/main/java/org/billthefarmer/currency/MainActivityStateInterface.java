package org.billthefarmer.currency;

import android.view.Menu;

public abstract class MainActivityStateInterface {
	Main client;

	MainActivityStateInterface(Main client) {
		this.client = client;
	}

	public abstract void onItemClick(int position);

	public abstract boolean onCreateOptionsMenu(Menu menu);
}