package org.billthefarmer.currency;

import android.content.ClipData;
import android.content.ClipboardManager;

import java.text.NumberFormat;

public class CopyCurrencyPriceCommand extends MainCommand {
	public CopyCurrencyPriceCommand(Main client) {
		super(client);
	}

	@Override
	public boolean execute() {
		ClipboardManager clipboard =
            (ClipboardManager) this.client.getSystemService(this.client.CLIPBOARD_SERVICE);

        NumberFormat numberFormat = NumberFormat.getInstance();
        numberFormat.setMinimumFractionDigits(this.client.digits);
        numberFormat.setMaximumFractionDigits(this.client.digits);

        // Copy value to clip
        String clip = null;
        for (int i : this.client.selectList)
        {
            try
            {
                numberFormat.setGroupingUsed(true);
                Number number = numberFormat.parse(this.client.valueList.get(i));
                Double value = number.doubleValue();

                // Remove grouping from value
                numberFormat.setGroupingUsed(false);
                clip = numberFormat.format(value);
            }
            catch (Exception e)
            {
            }
        }

        // Copy clip to clipboard
        clipboard.setPrimaryClip(ClipData.newPlainText("Currency", clip));

        // Clear selection
        this.client.selectList.clear();
        this.client.adapter.notifyDataSetChanged();

        // Restore menu
        this.client.mode = this.client.DISPLAY_MODE;
        this.client.changeState();
        this.client.invalidateOptionsMenu();
        return true;
	}
}