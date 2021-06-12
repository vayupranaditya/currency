package org.billthefarmer.currency;

public class ClearSelectionCommand extends MainCommand {
	public ClearSelectionCommand(Main client) {
		super(client);
	}

	@Override
	public boolean execute() {
		// Clear the list and update the adapter
        this.client.selectList.clear();
        this.client.adapter.notifyDataSetChanged();

        // Restore the menu
		this.client.mode = this.client.DISPLAY_MODE;
		this.client.changeState();
		this.client.invalidateOptionsMenu();
        return true;
	}
}