package org.billthefarmer.currency;

import android.content.Intent;
import android.view.MenuItem;

public class SetChartDurationCommand extends ChartCommand {
	public SetChartDurationCommand(ChartActivity client, MenuItem item, int duration) {
		super(client, item, duration);
	}

	@Override
	public boolean execute() {
		this.client.range = this.intParam;
        this.menuItemParam.setChecked(true);
        this.client.updateRange();

        return true;
	}
}