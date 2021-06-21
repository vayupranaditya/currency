package org.billthefarmer.currency;


public class ChoiceDialogSelectState extends ChoiceDialogState {
	ChoiceDialogSelectState(ChoiceDialog client) {
		super(client);
	}

	public void onItemClick(int position)
	{
		if (this.client.selectList.contains(position))
            this.client.selectList.remove(this.client.selectList.indexOf(position));

        else
            this.client.selectList.add(position);

        if (this.client.selectList.isEmpty())
        {
            if (this.client.clear != null)
                this.client.clear.setEnabled(false);
            if (this.client.select != null)
                this.client.select.setEnabled(false);
            this.client.mode = Main.DISPLAY_MODE;
            this.client.changeState();
        }

        this.client.adapter.notifyDataSetChanged();
	}
}