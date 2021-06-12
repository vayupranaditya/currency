package org.billthefarmer.currency;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class RefreshChartCommand extends ChartCommand {
	public RefreshChartCommand(ChartActivity client, String param1) {
		super(client, param1);
	}

	@Override
	@SuppressWarnings("deprecation")
	public boolean execute() {
		// Check connectivity before update
        ConnectivityManager manager =
            (ConnectivityManager) this.client.getSystemService(this.client.CONNECTIVITY_SERVICE);
        NetworkInfo info = manager.getActiveNetworkInfo();

        // Check connection
        if (info == null || !info.isConnected())
        {
            this.client.showToast(R.string.no_connection);
            return false;
        }

        // Check wifi
        if (this.client.wifi && info.getType() != ConnectivityManager.TYPE_WIFI)
        {
            this.client.showToast(R.string.no_wifi);
            return false;
        }

        // Check roaming
        if (!this.client.roaming && info.isRoaming())
        {
            this.client.showToast(R.string.roaming);
            return false;
        }

        // Get updating text
        String updating = this.client.getString(R.string.updating);

        // Set custom text to updating, since this may take a few secs
        if (this.client.customView != null)
            this.client.customView.setText(updating);

        // Schedule the update
        if (this.client.instance != null)
            this.client.instance.startParseTask(this.strParam);

        return true;
	}
}