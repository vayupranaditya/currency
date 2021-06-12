package org.billthefarmer.currency;

import com.github.mikephil.charting.data.Entry;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Map;

public class InvertChartCommand extends ChartCommand {
	public InvertChartCommand(ChartActivity client) {
		super(client);
	}

	@Override
	public boolean execute() {
		SimpleDateFormat dateParser =
            new SimpleDateFormat(Main.DATE_FORMAT, Locale.getDefault());

        // Get updating text
        String updating = this.client.getString(R.string.updating);

        // Set flag
        this.client.invert = !this.client.invert;

        // Reverse currency indices
        int index = this.client.firstIndex;
        this.client.firstIndex = this.client.secondIndex;
        this.client.secondIndex = index;

        // Update names
        this.client.firstName = Main.CURRENCY_NAMES[this.client.firstIndex];
        this.client.secondName = Main.CURRENCY_NAMES[this.client.secondIndex];

        // Set custom text to updating, since this may take a few secs
        if (this.client.customView != null)
            this.client.customView.setText(updating);

        // Clear the entry list
        this.client.entryList.clear();

        // Iterate through the dates
        for (String key : this.client.histMap.keySet())
        {
            float day = 0;

            // Parse the date and turn it into a day number
            try
            {
                Date date = dateParser.parse(key);
                day = date.getTime() / this.client.MSEC_DAY;
            }

            // Ignore invalid dates
            catch (Exception e)
            {
                continue;
            }

            // Get the map for each date
            Map<String, Double> entryMap = this.client.histMap.get(key);
            float value;

            // Get the value for each date
            try
            {
                double first = entryMap.get(this.client.firstName);
                double second = entryMap.get(this.client.secondName);
                value = (float) (first / second);
            }

            // Ignore missing values
            catch (Exception e)
            {
                continue;
            }

            // Add the entry to the list
            this.client.entryList.add(0, new Entry(day, value));
        }

        // Check the chart
        if (this.client.chart != null)
        {
            // Add the data to the chart and refresh
            this.client.dataSet.setValues(this.client.entryList);
            this.client.lineData.notifyDataChanged();
            this.client.chart.notifyDataSetChanged();
            this.client.chart.invalidate();

            // Get todays date
            Date today = new Date();
            // Get the start date
            long start = (today.getTime() / this.client.MSEC_DAY) - this.client.range;

            // Reset chart
            this.client.chart.fitScreen();
            // Set the range
            this.client.chart.setVisibleXRangeMaximum(this.client.range);
            this.client.chart.moveViewToX(start);
        }

        // Restore the custom view to the current currencies
        String label = this.client.secondName + "/" + this.client.firstName;
        if (this.client.customView != null)
            this.client.customView.setText(label);

        return true;
	}
}