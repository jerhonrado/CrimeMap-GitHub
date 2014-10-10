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

		ParseQuery<ParseObject> query = new ParseQuery<ParseObject>(
				"personName");
		query.whereNotEqualTo("MarkerID", 0);
		query.findInBackground(new FindCallback<ParseObject>() {

			@Override
			public void done(List<ParseObject> listahan, ParseException arg1) {
				for (int i = 0; i < listahan.size(); i++) {
					
					double lat = listahan.get(i).getParseGeoPoint("LatLng")
							.getLatitude();
					
					double lon = listahan.get(i).getParseGeoPoint("LatLng")
							.getLongitude();
					
					switch (listahan.get(i).getNumber("MarkerID").intValue()) {

					case 1:

						LatLng location1 = new LatLng(lat, lon);
						String ctitle1 = listahan.get(i)
								.getString("CrimeTitle").toString();
						String description1 = listahan.get(i)
								.getString("Description").toString();
						// ADDING MARKER

						Marker town = map.addMarker(new MarkerOptions()
								.position(location1)
								.title(ctitle1)
								.snippet(description1)
								.icon(BitmapDescriptorFactory
										.fromResource(R.drawable.crimescene)));

						break;
					case 2:

						LatLng location2 = new LatLng(lat, lon);
						String ctitle2 = listahan.get(i)
								.getString("CrimeTitle").toString();
						String description2 = listahan.get(i)
								.getString("Description").toString();
						// ADDING MARKER

						Marker town2 = map.addMarker(new MarkerOptions()
								.position(location2)
								.title(ctitle2)
								.snippet(description2)
								.icon(BitmapDescriptorFactory
										.fromResource(R.drawable.bomb)));
						break;
					case 3:

						LatLng location3 = new LatLng(lat, lon);
						String ctitle3 = listahan.get(i)
								.getString("CrimeTitle").toString();
						String description3 = listahan.get(i)
								.getString("Description").toString();
						// ADDING MARKER

						Marker town3 = map.addMarker(new MarkerOptions()
								.position(location3)
								.title(ctitle3)
								.snippet(description3)
								.icon(BitmapDescriptorFactory
										.fromResource(R.drawable.robbery)));
						break;
					case 4:

						LatLng location4 = new LatLng(lat, lon);
						String ctitle4 = listahan.get(i)
								.getString("CrimeTitle").toString();
						String description4 = listahan.get(i)
								.getString("Description").toString();
						// ADDING MARKER

						Marker town4 = map.addMarker(new MarkerOptions()
								.position(location4)
								.title(ctitle4)
								.snippet(description4)
								.icon(BitmapDescriptorFactory
										.fromResource(R.drawable.fire)));
						break;
					case 5:

						LatLng location5 = new LatLng(lat, lon);
						String ctitle5 = listahan.get(i)
								.getString("CrimeTitle").toString();
						String description5 = listahan.get(i)
								.getString("Description").toString();
						// ADDING MARKER

						Marker town5 = map.addMarker(new MarkerOptions()
								.position(location5)
								.title(ctitle5)
								.snippet(description5)
								.icon(BitmapDescriptorFactory
										.fromResource(R.drawable.rape)));
						break;

					case 6:

						LatLng location6 = new LatLng(lat, lon);
						String ctitle6 = listahan.get(i)
								.getString("CrimeTitle").toString();
						String description6 = listahan.get(i)
								.getString("Description").toString();
						// ADDING MARKER

						Marker town6 = map.addMarker(new MarkerOptions()
								.position(location6)
								.title(ctitle6)
								.snippet(description6)
								.icon(BitmapDescriptorFactory
										.fromResource(R.drawable.abduction)));
						break;

					case 7:

						LatLng location7 = new LatLng(lat, lon);
						String ctitle7 = listahan.get(i)
								.getString("CrimeTitle").toString();
						String description7 = listahan.get(i)
								.getString("Description").toString();
						// ADDING MARKER

						Marker town7 = map.addMarker(new MarkerOptions()
								.position(location7)
								.title(ctitle7)
								.snippet(description7)
								.icon(BitmapDescriptorFactory
										.fromResource(R.drawable.torture)));
						break;

					default:
						double lat0 = listahan.get(i)
								.getParseGeoPoint("LatLng").getLatitude();
						double lon0 = listahan.get(i)
								.getParseGeoPoint("LatLng").getLongitude();
						LatLng location0 = new LatLng(lat0, lon0);
						String ctitle0 = listahan.get(i)
								.getString("CrimeTitle").toString();
						String description0 = listahan.get(i)
								.getString("Description").toString();

						Marker town0 = map.addMarker(new MarkerOptions()
								.position(location0)
								.title(ctitle0)
								.snippet(description0)
								.icon(BitmapDescriptorFactory
										.fromResource(R.drawable.marker)));

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

