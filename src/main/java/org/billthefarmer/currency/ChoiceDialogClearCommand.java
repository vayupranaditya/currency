package org.billthefarmer.currency;

public class ChoiceDialogClearCommand extends ChoiceDialogCommand {
	public ChoiceDialogClearCommand(ChoiceDialog client) {
		super(client);
	}

	@Override
	public boolean execute() {
		if (this.client.clear != null)
	        this.client.clear.setEnabled(false);
	    if (this.client.select != null)
	        this.client.select.setEnabled(false);
	    this.client.mode = Main.DISPLAY_MODE;
	    this.client.changeState();

	    // Start a new selection
	    this.client.selectList.clear();
	    this.client.adapter.notifyDataSetChanged();

	    return true;
	}
}