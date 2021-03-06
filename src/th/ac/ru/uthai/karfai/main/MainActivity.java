package th.ac.ru.uthai.karfai.main;

import java.util.ArrayList;
import java.util.List;

import com.example.karfai.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

import th.ac.ru.uthai.karfai.adaptr.NavDrawerListAdapter;
import th.ac.ru.uthai.karfai.database.DatabaseManager;
import th.ac.ru.uthai.karfai.frangment.AboutUs;
import th.ac.ru.uthai.karfai.frangment.Additems;
import th.ac.ru.uthai.karfai.frangment.Calculate;
import th.ac.ru.uthai.karfai.frangment.Estimate;
import th.ac.ru.uthai.karfai.model.Data;

import com.opencsv.CSVReader;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.graphics.Point;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class MainActivity extends Activity {
	private DrawerLayout mDrawerLayout;
	private ListView mDrawerList;
	private ActionBarDrawerToggle mDrawerToggle;
	private String[] navMenuTitles;

	// nav drawer title
	private CharSequence mDrawerTitle;
	// used to store app title
	private CharSequence mTitle;

	private TypedArray navMenuIcons;
	private ArrayList<NavDrawerItem> navDrawerItems;
	private NavDrawerListAdapter adapter;

	private List<Fragment> listFragment;
	private DataConfig dataConf;
	private MainData md;
	private DatabaseManager dbm;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		md = MainData.getMainData();
		md.setDatabaseManager(this);
		md.setMa(this);
		dataConf = md.getDataConfig();
		dbm = md.getDatabaseManager();
		if (savedInstanceState == null) {
			dataConf = md.getDataConfig();
		} else {
			dataConf = (DataConfig) savedInstanceState.get("data");
		}
		dataConf.setMain(this);
		
		Display display = getWindowManager().getDefaultDisplay();
		Point size = new Point();
		display.getSize(size);
		dataConf.setDisplay_width(display.getWidth());
		dataConf.setDisplay_height(display.getHeight());
		dataConf.setConfig(getResources().getConfiguration());
		mTitle = mDrawerTitle = getTitle();

		navMenuTitles = getResources().getStringArray(R.array.nav_drawer_items);
		navMenuIcons = getResources()
				.obtainTypedArray(R.array.nav_drawer_icons);
		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.list_slidermenu);
		navDrawerItems = new ArrayList<NavDrawerItem>();

		for (int i = 0; i < navMenuTitles.length; i++) {
			navDrawerItems.add(new NavDrawerItem(navMenuTitles[i], navMenuIcons
					.getResourceId(i, -1)));
		}
		navMenuIcons.recycle();
		mDrawerList.setOnItemClickListener(new SlideMenuClickListener());

		adapter = new NavDrawerListAdapter(getApplicationContext(),
				navDrawerItems);
		mDrawerList.setAdapter(adapter);

		getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setHomeButtonEnabled(true);
		mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
				R.drawable.ic_drawer, // nav menu toggle icon
				R.string.app_name, // nav drawer open - description for
									// accessibility
				R.string.app_name // nav drawer close - description for
									// accessibility
		) {
			public void onDrawerClosed(View view) {
				getActionBar().setTitle(mTitle);
				// calling onPrepareOptionsMenu() to show action bar icons
				invalidateOptionsMenu();
			}

			public void onDrawerOpened(View drawerView) {
				getActionBar().setTitle(mDrawerTitle);
				// calling onPrepareOptionsMenu() to hide action bar icons
				invalidateOptionsMenu();
			}
		};
		mDrawerLayout.setDrawerListener(mDrawerToggle);
		displayView(0);
	}

	private class SlideMenuClickListener implements ListView.OnItemClickListener {
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			// display view for selected nav drawer item
			displayView(position);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		// if nav drawer is opened, hide the action items
		boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);
		//menu.findItem(R.id.action_settings).setVisible(!drawerOpen);
		return super.onPrepareOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        // Handle action bar actions click
        /*switch (item.getItemId()) {
        case R.id.action_settings:
            return true;
        default:
            
        }*/
        return super.onOptionsItemSelected(item);

	}

	public void displayView(int position) {
		Fragment fragment;
		switch (position) {
		case 0:
			fragment = new Calculate();
			break;
		case 1:
			fragment = new Additems();
			break;
		case 2:
			fragment = new Estimate();
			break;
		case 3:
			fragment = new AboutUs();
			break;
		

		default:
			fragment = null;
		}

		if (fragment != null) {
			FragmentManager fragmentManager = getFragmentManager();
			fragmentManager.beginTransaction().addToBackStack(null)
					.replace(R.id.frame_container, fragment).commit();

			mDrawerList.setItemChecked(position, true);
			mDrawerList.setSelection(position);
			setTitle(navMenuTitles[position]);
			mDrawerLayout.closeDrawer(mDrawerList);

		} else {
		}
	}

	public void addListData(Data item) {

		Data selectvalue = new Data();

		selectvalue.setId(dataConf.getAllDataList().size() + 1);
		selectvalue.setName(item.getName());
		selectvalue.setWat(item.getWat());
		selectvalue.setIcon(item.getIcon());
		selectvalue.changeStatusExpand(true);
		dataConf.addData(selectvalue);
		displayView(0);
	}

	public List<Data> getItemAddList() {
		return dataConf.getItemAddList();
	}

	public void remove(int position) {
		dataConf.removeData(position);
		displayView(0);
	}

	public ArrayList<Data> getAllDataList() {

		return this.dataConf.getAllDataList();
	}

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		// Sync the toggle state after onRestoreInstanceState has occurred.
		mDrawerToggle.syncState();
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		// Pass any configuration change to the drawer toggls
		mDrawerToggle.onConfigurationChanged(newConfig);
	}
	@Override
    public void setTitle(CharSequence title) {
        mTitle = title;
        getActionBar().setTitle(mTitle);
    }

}
