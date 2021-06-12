package org.billthefarmer.currency;

import android.app.Dialog;
import android.content.DialogInterface;
import android.text.Editable;
import android.widget.EditText;

import java.text.NumberFormat;
import java.util.Locale;

public class UpdateExtPriceCommand extends MainCommand {
	public UpdateExtPriceCommand(Main client) {
		super(client);
	}

	@Override
	public boolean execute() {
		NumberFormat numberFormat = NumberFormat.getInstance();
        numberFormat.setMinimumFractionDigits(this.client.digits);
        numberFormat.setMaximumFractionDigits(this.client.digits);

        NumberFormat englishFormat = NumberFormat.getInstance(Locale.ENGLISH);

        String extra = numberFormat.format(this.client.extraValue);

        // Open dialog
        this.client.updateDialog(R.string.update_extra, extra, R.string.decimal,
                    (dialog, id) ->
        {
            switch (id)
            {
            case DialogInterface.BUTTON_POSITIVE:
                EditText text =
                    ((Dialog) dialog).findViewById(R.id.value);
                String value = text.getText().toString();

                // Ignore empty string
                if (value.isEmpty())
                    return;

                // Try default locale
                try
                {
                    Number number = numberFormat.parse(value);
                    this.client.extraValue = number.doubleValue();
                }
                catch (Exception e)
                {
                    // Try English locale
                    try
                    {
                        Number number = englishFormat.parse(value);
                        this.client.extraValue = number.doubleValue();
                    }
                    catch (Exception ex)
                    {
                        this.client.extraValue = 1.0;
                    }
                }

                // Update display
                this.client.valueMap.put("EXT", this.client.extraValue);
                this.client.convertValue = this.client.valueMap.get(this.client.CURRENCY_NAMES[this.client.currentIndex]);
                Editable editable = this.client.editView.getEditableText();
                this.client.afterTextChanged(editable);
            }
        });

        return true;
	}
}