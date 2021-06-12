package org.billthefarmer.currency;

import java.util.ArrayList;
import java.util.List;

public class RemoveCurrencyCommand extends MainCommand {
	public RemoveCurrencyCommand(Main client) {
		super(client);
	}

	@Override
	public boolean execute() {
		List<String> removeList = new ArrayList<>();

        // Create a list of currency names to remove
        for (int i : this.client.selectList)
            removeList.add(this.client.nameList.get(i));

        for (String name : removeList)
        {
            // Look up name
            int i = this.client.nameList.indexOf(name);

            // Remove from the lists
            this.client.flagList.remove(i);
            this.client.nameList.remove(i);
            this.client.symbolList.remove(i);
            this.client.valueList.remove(i);
            this.client.longNameList.remove(i);
        }

        // Clear list and update adapter
        this.client.selectList.clear();
        this.client.adapter.notifyDataSetChanged();

        // Restore menu
        this.client.mode = this.client.DISPLAY_MODE;
        this.client.invalidateOptionsMenu();

        return true;
	}
}