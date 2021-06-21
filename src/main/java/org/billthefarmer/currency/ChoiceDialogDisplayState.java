package org.billthefarmer.currency;


import android.content.Intent;

import java.util.ArrayList;

public class ChoiceDialogDisplayState extends ChoiceDialogState {
	ChoiceDialogDisplayState(ChoiceDialog client) {
		super(client);
	}

	public void onItemClick(int position)
	{
		this.client.selectList.add(position);
	    // Return new currency in intent
	    Intent intent = new Intent();
	    intent.putIntegerArrayListExtra(Main.CHOICE,
	                                    (ArrayList<Integer>) this.client.selectList);
	    this.client.setResult(this.client.RESULT_OK, intent);
	    this.client.finish();
	}
}