package com.example.fragment;

import java.util.List;

import com.example.adapter.ListViewAdapter;
import com.example.karfai.Data;
import com.example.karfai.MainActivity;
import com.example.karfai.R;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class Additems extends Fragment{
	private View convertView;
	private MainActivity main;
	private List<Data> items;
	public Additems(MainActivity main){
		this.main=main;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		Log.d("2", "2");
		convertView = inflater.inflate(R.layout.listviewadd, container, false);
		ListView listview = (ListView) convertView.findViewById(R.id.listAdd);
		items=main.getitems();
		Log.d("jays", items.size()+"");
		ListViewAdapter adapter = new  ListViewAdapter(inflater, items);
		listview.setAdapter(adapter);
		listview.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> adapter, View v, int position,
					long id) {
				// TODO Auto-generated method stub
				Log.d("listview", "click");
				main.addListData((Data) adapter.getItemAtPosition(position));
				
				
			}
		});
		
		return convertView;
	}

}