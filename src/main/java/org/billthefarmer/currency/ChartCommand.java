package org.billthefarmer.currency;

import android.view.MenuItem;

public abstract class ChartCommand {
	public ChartActivity client;
	public String strParam;
	public MenuItem menuItemParam;
	public int intParam;

	public ChartCommand(ChartActivity client) {
		this.client = client;
		this.strParam = null;
		this.menuItemParam = null;
		this.intParam = 0;
	}

	public ChartCommand(ChartActivity client, String strParam) {
		this.client = client;
		this.strParam = strParam;
		this.menuItemParam = null;
		this.intParam = 0;
	}

	public ChartCommand(ChartActivity client, MenuItem menuItemParam, int intParam) {
		this.client = client;
		this.strParam = null;
		this.menuItemParam = menuItemParam;
		this.intParam = intParam;
	}

	public abstract boolean execute();
}