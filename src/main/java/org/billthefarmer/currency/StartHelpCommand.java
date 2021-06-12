package org.billthefarmer.currency;

import android.content.Intent;

public class StartHelpCommand extends MainCommand {
	public StartHelpCommand(Main client) {
		super(client);
	}

	@Override
	public boolean execute() {
		// Start help activity
        Intent intent = new Intent(this.client, HelpActivity.class);
        this.client.startActivity(intent);

        return true;
	}
}