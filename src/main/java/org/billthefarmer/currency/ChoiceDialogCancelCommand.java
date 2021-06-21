package org.billthefarmer.currency;

public class ChoiceDialogCancelCommand extends ChoiceDialogCommand {
	public ChoiceDialogCancelCommand(ChoiceDialog client) {
		super(client);
	}

	@Override
	public boolean execute() {
		this.client.setResult(this.client.RESULT_CANCELED);
        this.client.finish();
        return true;
	}
}