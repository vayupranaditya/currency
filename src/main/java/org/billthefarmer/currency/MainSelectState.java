package org.billthefarmer.currency;

import android.view.Menu;
import android.view.MenuInflater;

import java.util.List;

public class MainSelectState extends MainActivityStateInterface {
	MainSelectState(Main client) {
		super(client);
	}
	@Override
	public void onItemClick(int position)
	{
		List<Integer> selectList = this.client.selectList;
		if (selectList.contains(position))
	        selectList.remove(selectList.indexOf(position));

	    else
	        selectList.add(position);

	    // Reset mode if list empty
	    if (selectList.isEmpty())
	    {
	        this.client.mode = this.client.DISPLAY_MODE;
	        this.client.changeState();
	        this.client.invalidateOptionsMenu();
	    }

	    // Notify the adapter
	    this.client.adapter.notifyDataSetChanged();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		MenuInflater inflater = this.client.getMenuInflater();
		inflater.inflate(R.menu.select, menu);
        return true;
	}
}