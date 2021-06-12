package org.billthefarmer.currency;

import android.content.Intent;

public class AddCurrencyCommand extends MainCommand {
	public AddCurrencyCommand(Main client) {
		super(client);
	}

	@Override
	public boolean execute() {
		// Start the choice dialog
        Intent intent = new Intent(this.client, ChoiceDialog.class);
		this.client.startActivityForResult(intent, 0);

        return true;
	}
}