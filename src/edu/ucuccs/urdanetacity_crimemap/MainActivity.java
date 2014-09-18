package edu.ucuccs.urdanetacity_crimemap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {

	int mPosition = -1;
	String mTitle = "";

	// Array of strings storing country names
	String[] mCategories;

	// Array of integers points to images stored in /res/drawable-ldpi/
	int[] mCategoriesImages = new int[] { R.drawable.home, R.drawable.location,
			R.drawable.stats_bars, R.drawable.gear, R.drawable.help,
			R.drawable.about };

	// Array of strings to initial counts
	String[] mCategoriesCount = new String[] { "", "", "", "", "", "" };

	private DrawerLayout mDrawerLayout;
	private ListView mDrawerList;
	private ActionBarDrawerToggle mDrawerToggle;
	private LinearLayout mDrawer;
	private List<HashMap<String, String>> mList;
	private SimpleAdapter mAdapter;
	final private String CATEGORY_DESC = "category_desc";
	final private String CATEGORY_IMG = "category_img";
	final private String CATEGORY_COUNT = "category_count";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// Getting an array of country names
		mCategories = getResources().getStringArray(R.array.naviMapStrings);

		// Title of the activity
		mTitle = (String) getTitle();

		// Getting a reference to the drawer listview
		mDrawerList = (ListView) findViewById(R.id.drawer_list);

		// Getting a reference to the sidebar drawer ( Title + ListView )
		mDrawer = (LinearLayout) findViewById(R.id.drawer);

		// Each row in the list stores country name, count and flag
		mList = new ArrayList<HashMap<String, String>>();
		for (int i = 0; i < 6; i++) {
			HashMap<String, String> hm = new HashMap<String, String>();
			hm.put(CATEGORY_DESC, mCategories[i]);
			hm.put(CATEGORY_COUNT, mCategoriesCount[i]);
			hm.put(CATEGORY_IMG, Integer.toString(mCategoriesImages[i]));
			mList.add(hm);
		}

		// Keys used in Hashmap
		String[] from = { CATEGORY_IMG, CATEGORY_DESC, CATEGORY_COUNT };

		// Ids of views in listview_layout
		int[] to = { R.id.CATEGORY_IMG, R.id.CATEGORY_DESC, R.id.CATEGORY_COUNT };

		// Instantiating an adapter to store each items
		// R.layout.drawer_layout defines the layout of each item
		mAdapter = new SimpleAdapter(this, mList, R.layout.drawer_layout, from,
				to);

		// Getting reference to DrawerLayout
		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

		// Creating a ToggleButton for NavigationDrawer with drawer event
		// listener
		mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
				R.drawable.ic_drawer, R.string.drawer_open,
				R.string.drawer_close) {

			/** Called when drawer is closed */
			public void onDrawerClosed(View view) {
				highlightSelectedCategory();
				supportInvalidateOptionsMenu();
				mDrawerLayout.closeDrawer(mDrawer); // AYAW TALAGA BOSET
			}

			/** Called when a drawer is opened */
			public void onDrawerOpened(View drawerView) {
				getSupportActionBar().setTitle("Urdaneta City - Crime Map");
				supportInvalidateOptionsMenu();
			}
		};

		// Setting event listener for the drawer
		mDrawerLayout.setDrawerListener(mDrawerToggle);

		// ItemClick event handler for the drawer items
		mDrawerList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1,
					int position, long arg3) {

				// Increment hit count of the drawer list item

				if (position < 7) { // Show fragment for countries : 0 to 4
					showFragment(position);
				}

				// Closing the drawer
				mDrawerLayout.closeDrawer(mDrawer);
			}
		});

		// Enabling Up navigation
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);

		getSupportActionBar().setDisplayShowHomeEnabled(true);

		// Setting the adapter to the listView
		mDrawerList.setAdapter(mAdapter);

	}

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		mDrawerToggle.syncState();
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (mDrawerToggle.onOptionsItemSelected(item)) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	public void showFragment(int position) {

		// Currently selected categories
		mTitle = mCategories[position];

		// Creating a fragment object

		ClassNaviFragment cFragment = new ClassNaviFragment();

		// Creating a Bundle object
		Bundle data = new Bundle();

		// Setting the index of the currently selected item of mDrawerList
		data.putInt("position", position);

		// Setting the position to the fragment
		cFragment.setArguments(data);

		// Getting reference to the FragmentManager
		FragmentManager fragmentManager = getSupportFragmentManager();

		// Creating a fragment transaction
		FragmentTransaction ft = fragmentManager.beginTransaction();

		// Adding a fragment to the fragment transaction
		ft.replace(R.id.content_frame, cFragment);

		// Committing the transaction
		ft.commit();
	}

	public void highlightSelectedCategory() {
		int selectedItem = mDrawerList.getCheckedItemPosition();

		if (selectedItem > 6) {
			mDrawerList.setItemChecked(mPosition, true);
			mDrawerLayout.closeDrawer(mDrawer);
		} else {
			mPosition = selectedItem;
		}

		if (mPosition != -1) {
			getSupportActionBar().setTitle(mCategories[mPosition]);
			
			if (mCategories[mPosition].equals("Home".toString())) {
				Intent omz = new Intent(MainActivity.this, Home.class);
				startActivity(omz);
			} else if (mCategories[mPosition].equals("Crime Map".toString())) {
				Intent omz = new Intent(MainActivity.this, CrimeMap.class);
				startActivity(omz);
			} else if (mCategories[mPosition].equals("Crime Statistics".toString())) {
				Intent omz = new Intent(MainActivity.this, CrimeStatistics.class);
				startActivity(omz);
			} else if (mCategories[mPosition].equals("Settings".toString())) {
				Intent omz = new Intent(MainActivity.this, Settings.class);
				startActivity(omz);
			} else if (mCategories[mPosition].equals("Help".toString())) {
				Intent omz = new Intent(MainActivity.this, Help.class);
				startActivity(omz);
			} else if (mCategories[mPosition].equals("About".toString())) {
				Intent omz = new Intent(MainActivity.this, About.class);
				startActivity(omz);
			}
			
		}

		

	}
}