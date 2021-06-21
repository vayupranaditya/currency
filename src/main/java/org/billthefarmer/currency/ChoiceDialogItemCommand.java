package org.billthefarmer.currency;

import android.content.Intent;

import java.util.ArrayList;

public class ChoiceDialogItemCommand extends ChoiceDialogCommand {
	public ChoiceDialogItemCommand(ChoiceDialog client, int position) {
		super(client, position);
	}

	@Override
	public boolean execute() {
        this.client.state.onItemClick(this.position);
        return true;
	}
}