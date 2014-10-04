package edu.ucuccs.urdanetacity_crimemap;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.maps.android.clustering.ClusterManager;
import com.parse.FindCallback;
import com.parse.GetDataCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;

public class CrimeMap extends ActionBarActivity {

	LatLng Urdaneta = new LatLng(15.975802700000000000, 120.570693300000020000);
	final String Application_ID = "w9A46lBqjtRMw9WnFkJgl2xdEsmgNGmPxXcWT4Iv";
	final String Client_Key = "fD3SptdS3K6qMpzndXv3xzfnMsd14VUGNO7bgmvM";
	GoogleMap map;
	private ClusterManager<MyItem> mClusterManager;
	private ArrayList<LatLng> arrayPoints = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_crime_map);
		Parse.initialize(this, Application_ID, Client_Key);
		map = ((MapFragment) getFragmentManager().findFragmentById(
				R.id.fragmentMaps)).getMap();
		mClusterManager = new ClusterManager<MyItem>(this, map);
		map.moveCamera(CameraUpdateFactory.newLatLngZoom(Urdaneta, 17.0F));
		// LOAD ALL
		Drawable marker=getResources().getDrawable(R.drawable.marker);
		ParseQuery<ParseObject> query = new ParseQuery<ParseObject>(
				"personName");
		query.whereNotEqualTo("MarkerID", 0);
		query.findInBackground(new FindCallback<ParseObject>() {

			@Override
			public void done(List<ParseObject> listahan, ParseException arg1) {
				for (int i = 0; i < listahan.size(); i++) {

					switch (listahan.get(i).getNumber("MarkerID").intValue()) {

					case 0:
						double lat1 = listahan.get(i).getParseGeoPoint("LatLng")
								.getLatitude();
						double lon1 = listahan.get(i).getParseGeoPoint("LatLng")
								.getLongitude();
						LatLng location = new LatLng(lat1, lon1);
						String ctitle = listahan.get(i).getString("CrimeTitle")
								.toString();
						String description = listahan.get(i)
								.getString("Description").toString();
						ParseFile marker1 = (ParseFile) listahan.get(i)
								.getParseFile("Marker");
						marker1.getDataInBackground(new GetDataCallback() {
							@Override
							public void done(byte[] data, ParseException e) {
								Bitmap bitpic = BitmapFactory.decodeByteArray(
										data, 0, data.length);
								ByteArrayOutputStream stream = new ByteArrayOutputStream();
								bitpic.compress(Bitmap.CompressFormat.PNG, 100,
										stream);
								// ImageView image=(ImageView)
								// findViewById(R.id.imageView1);
								// image.setImageBitmap(bitpic);

							}
						});

						// ADDING MARKER

						Marker town = map.addMarker(new MarkerOptions()
								.position(location).title(ctitle)
								.snippet(description));

						break;

					default:
						double lat = listahan.get(i)
								.getParseGeoPoint("LatLng").getLatitude();
						double lon = listahan.get(i)
								.getParseGeoPoint("LatLng").getLongitude();
						LatLng location1 = new LatLng(lat, lon);
						String ctitle1 = listahan.get(i)
								.getString("CrimeTitle").toString();
						String description1 = listahan.get(i)
								.getString("Description").toString();
						
						Marker town1 = map.addMarker(new MarkerOptions()
								.position(location1).title(ctitle1)
								.snippet( description1)
								.icon(BitmapDescriptorFactory.fromResource(R.drawable.marker)));


						MyItem item = new MyItem(lat, lon);
						mClusterManager.addItem(item);
						break;

					}

				}

			}

		});
		/*
		 * ParseObject p = new ParseObject("personName"); ParseGeoPoint geo =
		 * new ParseGeoPoint(15.9758027,120.5706933); p.put("LatLng", geo);
		 * p.saveInBackground();
		 */

	}

}

