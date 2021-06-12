package org.billthefarmer.currency;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONArray;

import java.text.NumberFormat;

public class MainDisplayState extends MainActivityStateInterface {
	MainDisplayState(Main client) {
		super(client);
	}

	public void onItemClick(int position)
	{
		String value;
		int oldIndex;
		double oldValue;

		NumberFormat numberFormat = NumberFormat.getInstance();
		numberFormat.setMinimumFractionDigits(this.client.digits);
		numberFormat.setMaximumFractionDigits(this.client.digits);

		// Save the current values
		oldIndex = this.client.currentIndex; // currentIndex is Main's property
		oldValue = this.client.currentValue; // currentValue is Main's property

		// Set the current currency from the list
		this.client.currentIndex = this.client.currencyNameList.indexOf(this.client.currencyNameList.get(position)); // nameList is Main's property

		this.client.currentValue = (oldValue / this.client.convertValue) *
		               this.client.valueMap.get(this.client.currencyNameList.get(this.client.currentIndex)); // convertValue & CURRENCY_NAMES are Main's properties

		this.client.convertValue = this.client.valueMap.get(this.client.currencyNameList.get(this.client.currentIndex));

		numberFormat.setGroupingUsed(false);
		value = numberFormat.format(this.client.currentValue);

		EditText editView = this.client.editView;

		if (editView != null) // editView is Main's property
		{
		    editView.setText(value);

		    // Forces select all
		    editView.clearFocus();
		    editView.requestFocus();

		    // Do it only once
		    this.client.select = false; // select is Main's property
		}

		if (this.client.flagView != null) // flagView is Main's property
		    this.client.flagView.setImageResource(this.client.CURRENCY_FLAGS[this.client.currentIndex]); // CURRENCY_FLAGAS is Main's property
		if (this.client.nameView != null) // nameView is Main's property
		    this.client.nameView.setText(this.client.CURRENCY_NAMES[this.client.currentIndex]); // CURRENCY_NAMES is Main's property
		if (this.client.symbolView != null) // symbolView is Main's property
		    this.client.symbolView.setText(this.client.CURRENCY_SYMBOLS[this.client.currentIndex]); // CURRENCY_SYMBOLS is Main's property
		if (this.client.longNameView != null) // longNameView is Main's property
		    this.client.longNameView.setText(this.client.CURRENCY_LONGNAMES[this.client.currentIndex]); // CURRENCY_LONGNAMES is Main's property

		// Remove the selected currency from the lists
		this.client.flagList.remove(position);
		this.client.nameList.remove(position);
		this.client.symbolList.remove(position);
		this.client.valueList.remove(position);
		this.client.longNameList.remove(position);

		// Add the old current currency to the start of the list
		this.client.flagList.add(0, this.client.CURRENCY_FLAGS[oldIndex]);
		this.client.nameList.add(0, this.client.CURRENCY_NAMES[oldIndex]);
		this.client.symbolList.add(0, this.client.CURRENCY_SYMBOLS[oldIndex]);
		this.client.longNameList.add(0, this.client.CURRENCY_LONGNAMES[oldIndex]);

		numberFormat.setGroupingUsed(true);
		value = numberFormat.format(oldValue);
		this.client.valueList.add(0, value);

		// Get preferences
		SharedPreferences preferences =
		    PreferenceManager.getDefaultSharedPreferences(client);

		// Get editor
		SharedPreferences.Editor editor = preferences.edit();

		// Get entries
		JSONArray nameArray = new JSONArray(this.client.nameList);
		JSONArray valueArray = new JSONArray(this.client.valueList);

		// Update preferences
		editor.putString(this.client.PREF_NAMES, nameArray.toString());
		editor.putString(this.client.PREF_VALUES, valueArray.toString());
		editor.putInt(this.client.PREF_INDEX, this.client.currentIndex);
		numberFormat.setGroupingUsed(false);
		value = numberFormat.format(this.client.currentValue);
		editor.putString(client.PREF_VALUES, value);
		editor.apply();

		// Notify the adapter
		this.client.adapter.notifyDataSetChanged();
	}

	public boolean onCreateOptionsMenu(Menu menu)
	{
		MenuInflater inflater = this.client.getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return true;
	}
}