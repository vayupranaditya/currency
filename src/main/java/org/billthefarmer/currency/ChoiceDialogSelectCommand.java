package org.billthefarmer.currency;

import android.content.Intent;

import java.util.ArrayList;

public class ChoiceDialogSelectCommand extends ChoiceDialogCommand {
	public ChoiceDialogSelectCommand(ChoiceDialog client) {
		super(client);
	}

	@Override
	public boolean execute() {
		// Return new currency list in intent
        Intent intent = new Intent();
        intent.putIntegerArrayListExtra(Main.CHOICE,
                                        (ArrayList<Integer>) this.client.selectList);
        this.client.setResult(this.client.RESULT_OK, intent);
        this.client.finish();

        return true;
	}
}