package com.example.adapter;

import java.util.List;

import com.example.karfai.Data;
import com.example.karfai.R;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ListViewAdapter extends BaseAdapter {
	private LayoutInflater infalInflater;
	private List<Data> items;
	private int[] imageIcon;
	
	public ListViewAdapter (LayoutInflater infalInflater,List<Data> items){
		this.infalInflater=infalInflater;
		this.items=items;
		imageIcon = new int[]{R.drawable.ic_launcher,R.drawable.delete};
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return items.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return items.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		Data value = items.get(position);
		convertView = infalInflater.inflate(R.layout.list_add, parent, false);
		TextView name = (TextView) convertView.findViewById(R.id.addname);
		String test = "delete";
		ImageView icon = (ImageView) convertView.findViewById(R.id.icon_add);
		
		//TextView wat = (TextView) convertView.findViewById(R.id.addwat);
		name.setText(value.getName());
		icon.setImageResource(imageIcon[0]);
		//wat.setText(value.getWat()+"");
		
		
		return convertView;
	}


}
