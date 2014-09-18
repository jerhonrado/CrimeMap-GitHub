package edu.ucuccs.urdanetacity_crimemap;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;

public class CrimeMap extends ActionBarActivity {

	//wala pang SHA-1 to sam
	
	GoogleMap map;

	static final CameraPosition URDANETA = new CameraPosition.Builder()
			.target(new LatLng(120.570693300000020000, 15.975802700000000000))
			.zoom(17f).bearing(0).tilt(25).build();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_crime_map);

		map = ((MapFragment) getFragmentManager().findFragmentById(
				R.id.fragmentMaps)).getMap();

	}



}
