package org.billthefarmer.currency;

public abstract class ChoiceDialogCommand {
	public ChoiceDialog client;
	public int position;

	public ChoiceDialogCommand(ChoiceDialog client) {
		this.client = client;
	}

	public ChoiceDialogCommand(ChoiceDialog client, int position) {
		this.client = client;
		this.position = position;
	}

	public abstract boolean execute();
}