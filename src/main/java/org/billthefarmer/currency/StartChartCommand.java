package org.billthefarmer.currency;

import android.content.Intent;

import java.util.ArrayList;
import java.util.List;

public class StartChartCommand extends MainCommand {
	public StartChartCommand(Main client) {
		super(client);
	}

	@Override
	public boolean execute() {
		Intent intent = new Intent(this.client, ChartActivity.class);
        List<Integer> list = new ArrayList<>();

        // Add the current index
        list.add(this.client.currentIndex);

        // Add the select list to the list
        for (int index : this.client.selectList)
        {
            String name = this.client.nameList.get(index);
            list.add(this.client.currencyNameList.indexOf(name));
        }

        // Put the list
        intent.putIntegerArrayListExtra(this.client.CHART_LIST,
                                        (ArrayList<Integer>) list);

        // Start chart activity
        this.client.startActivity(intent);

        // Clear select list and update adapter
        this.client.selectList.clear();
        this.client.adapter.notifyDataSetChanged();

        // Restore menu
        this.client.mode = this.client.DISPLAY_MODE;
        this.client.invalidateOptionsMenu();

        return true;
	}
}