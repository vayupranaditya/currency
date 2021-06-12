package org.billthefarmer.currency;

import android.content.Intent;

public class StartSettingCommand extends MainCommand {
	public StartSettingCommand(Main client) {
		super(client);
	}

	@Override
	public boolean execute() {
		// Start settings activity
        Intent intent = new Intent(this.client, SettingsActivity.class);
        this.client.startActivity(intent);

        return true;
	}
}