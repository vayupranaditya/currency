package org.billthefarmer.currency;

public abstract class MainCommand {
	public Main client;

	public MainCommand(Main client) {
		this.client = client;
	}

	public abstract boolean execute();
}