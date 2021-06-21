////////////////////////////////////////////////////////////////////////////////
//
//  Currency - An android currency converter.
//
//  Copyright (C) 2016  Bill Farmer
//
//  This program is free software: you can redistribute it and/or modify
//  it under the terms of the GNU General Public License as published by
//  the Free Software Foundation, either version 3 of the License, or
//  (at your option) any later version.
//
//  This program is distributed in the hope that it will be useful,
//  but WITHOUT ANY WARRANTY; without even the implied warranty of
//  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
//  GNU General Public License for more details.
//
//  You should have received a copy of the GNU General Public License
//  along with this program.  If not, see <http://www.gnu.org/licenses/>.
//
//  Bill Farmer  william j farmer [at] yahoo [dot] co [dot] uk.
//
///////////////////////////////////////////////////////////////////////////////

package org.billthefarmer.currency;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// Choice dialog
public class  ChoiceDialog extends Activity
    implements View.OnClickListener, AdapterView.OnItemClickListener,
    AdapterView.OnItemLongClickListener
{

    public Button clear;
    public Button select;

    public List<Integer> selectList;

    public ChoiceAdapter adapter;

    public int mode = Main.DISPLAY_MODE;

    public ChoiceDialogState state;

    // On create
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        state = new ChoiceDialogDisplayState(this);

        // Get preferences
        SharedPreferences preferences =
            PreferenceManager.getDefaultSharedPreferences(this);

        boolean theme = preferences.getBoolean(Main.PREF_DARK, true);

        if (!theme)
            setTheme(R.style.DialogLightTheme);

        setContentView(R.layout.choose);

        // Find views
        ListView listView = findViewById(R.id.list);

        Button cancel = findViewById(R.id.cancel);
        clear = findViewById(R.id.clear);
        select = findViewById(R.id.select);

        // Set the listeners
        if (listView != null)
        {
            listView.setOnItemClickListener(this);
            listView.setOnItemLongClickListener(this);
        }

        if (cancel != null)
            cancel.setOnClickListener(this);

        if (clear != null)
            clear.setOnClickListener(this);

        if (select != null)
            select.setOnClickListener(this);

        selectList = new ArrayList<>();

        // Populate the lists
        List<Integer> flagList = Arrays.asList(Main.CURRENCY_FLAGS);
        List<Integer> longNameList = Arrays.asList(Main.CURRENCY_LONGNAMES);
        List<String> nameList = Arrays.asList(Main.CURRENCY_NAMES);

        // Create the adapter
        adapter = new ChoiceAdapter(this, R.layout.choice, flagList,
                                    nameList, longNameList, selectList);

        // Set the adapter
        if (listView != null)
            listView.setAdapter(adapter);
    }

    // On restore
    @Override
    public void onRestoreInstanceState(Bundle savedState)
    {
        List<Integer> list =
            savedState.getIntegerArrayList(Main.SAVE_SELECT);

        if (list != null)
        {
            // Update the selection list
            selectList.addAll(list);
        }

        // Disable buttons if empty
        if (selectList.isEmpty())
        {
            if (clear != null)
                clear.setEnabled(false);
            if (select != null)
                select.setEnabled(false);
            mode = Main.DISPLAY_MODE;
            this.changeState();
        }

        // Enable buttons if selection
        else
        {
            if (clear != null)
                clear.setEnabled(true);
            if (select != null)
                select.setEnabled(true);
            mode = Main.SELECT_MODE;
            this.changeState();
        }

        // Notify adapter
        adapter.notifyDataSetChanged();
        super.onRestoreInstanceState(savedState);
    }

    // On save
    @Override
    public void onSaveInstanceState(Bundle outState)
    {
        super.onSaveInstanceState(outState);

        // Save the selection list
        outState.putIntegerArrayList(Main.SAVE_SELECT,
                                     (ArrayList<Integer>) selectList);
    }

    // On click
    // Command pattern
    @Override
    public void onClick(View v)
    {
        int id = v.getId();

        switch (id)
        {
        // Cancel
        case R.id.cancel:
            executeCommand(new ChoiceDialogCancelCommand(this));
            break;

        // Clear
        case R.id.clear:
            executeCommand(new ChoiceDialogClearCommand(this));
            break;

        // Select
        case R.id.select:
            executeCommand(new ChoiceDialogSelectCommand(this));
            break;
        }
    }

    // On item click
    // State pattern
    @Override
    public void onItemClick(AdapterView<?> parent, View view,
                            int position, long id)
    {
        executeCommand(new ChoiceDialogItemCommand(this, position));
    }

    // On item long click
    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view,
                                   int position, long id)
    {
        return executeCommand(new ChoiceDialogItemLongClickCommand(this, position));
    }

    private boolean executeCommand(ChoiceDialogCommand command) {
        return command.execute();
    }

    public void changeState() {
        if (mode == Main.SELECT_MODE) {
            this.state = new ChoiceDialogSelectState(this);
        } else {
            this.state = new ChoiceDialogDisplayState(this);
        }
    }
}
