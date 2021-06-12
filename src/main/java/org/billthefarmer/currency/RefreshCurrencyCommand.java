package org.billthefarmer.currency;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class RefreshCurrencyCommand extends MainCommand {
	public RefreshCurrencyCommand(Main client) {
		super(client);
	}

	@Override
	@SuppressWarnings("deprecation")
	public boolean execute() {
		// Check connectivity before refresh
        ConnectivityManager manager =
            (ConnectivityManager) this.client.getSystemService(this.client.CONNECTIVITY_SERVICE);
        NetworkInfo info = manager.getActiveNetworkInfo();

        // Check connected
        if (info == null || !info.isConnected())
        {
            if (this.client.statusView != null)
                this.client.statusView.setText(R.string.no_connection);
            return false;
        }

        // Check wifi
        if (this.client.wifi && info.getType() != ConnectivityManager.TYPE_WIFI)
        {
            if (this.client.statusView != null)
                this.client.statusView.setText(R.string.no_wifi);
            return false;
        }

        // Check roaming
        if (!this.client.roaming && info.isRoaming())
        {
            if (this.client.statusView != null)
                this.client.statusView.setText(R.string.roaming);
            return false;
        }

        // Schedule update
        if (this.client.statusView != null)
            this.client.statusView.setText(R.string.updating);

        // Start the task
        if (this.client.data != null)
            this.client.data.startParseTask(this.client.ECB_DAILY_URL);

        return true;
	}
}