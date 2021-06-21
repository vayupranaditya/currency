package org.billthefarmer.currency;

import android.view.Menu;

public abstract class ChoiceDialogState {
	ChoiceDialog client;

	ChoiceDialogState(ChoiceDialog client) {
		this.client = client;
	}

	public abstract void onItemClick(int position);
}