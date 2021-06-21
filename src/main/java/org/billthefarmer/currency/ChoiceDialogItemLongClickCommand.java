package org.billthefarmer.currency;

public class ChoiceDialogItemLongClickCommand extends ChoiceDialogCommand {
	public ChoiceDialogItemLongClickCommand(ChoiceDialog client, int position) {
		super(client, position);
	}

	@Override
	public boolean execute() {
		if (this.client.clear != null)
            this.client.clear.setEnabled(true);
        if (this.client.select != null)
            this.client.select.setEnabled(true);
        this.client.mode = Main.SELECT_MODE;

        // Start a new selection
        this.client.selectList.clear();
        this.client.selectList.add(this.position);
        this.client.adapter.notifyDataSetChanged();
        return true;
	}
}