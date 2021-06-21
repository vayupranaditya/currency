package org.billthefarmer.currency;

import android.content.Intent;

import java.util.ArrayList;

public class ChoiceDialogItemCommand extends ChoiceDialogCommand {
	public ChoiceDialogItemCommand(ChoiceDialog client, int position) {
		super(client, position);
	}

	@Override
	public boolean execute() {
		// Check mode
        switch (this.client.mode)
        {
        // Normal
        case Main.DISPLAY_MODE:
            this.client.selectList.add(this.position);
            // Return new currency in intent
            Intent intent = new Intent();
            intent.putIntegerArrayListExtra(Main.CHOICE,
                                            (ArrayList<Integer>) this.client.selectList);
            this.client.setResult(this.client.RESULT_OK, intent);
            this.client.finish();
            break;

        // Select
        case Main.SELECT_MODE:
            if (this.client.selectList.contains(this.position))
                this.client.selectList.remove(this.client.selectList.indexOf(this.position));

            else
                this.client.selectList.add(this.position);

            if (this.client.selectList.isEmpty())
            {
                if (this.client.clear != null)
                    this.client.clear.setEnabled(false);
                if (this.client.select != null)
                    this.client.select.setEnabled(false);
                this.client.mode = Main.DISPLAY_MODE;
            }

            this.client.adapter.notifyDataSetChanged();
            break;
        }

        return true;
	}
}