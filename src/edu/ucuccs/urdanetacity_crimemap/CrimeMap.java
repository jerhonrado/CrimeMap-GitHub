package edu.ucuccs.urdanetacity_crimemap;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;

public class CrimeMap extends ActionBarActivity {

	//wala pang SHA-1 to sam
	private MapView map=null;
  private MyLocationOverlay me=null;
	
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
				
				Drawable marker=getResources().getDrawable(R.drawable.marker);
				 marker.setBounds(0, 0, marker.getIntrinsicWidth(),
                            marker.getIntrinsicHeight());
    
    map.getOverlays().add(new SitesOverlay(marker));
    
    me=new MyLocationOverlay(this, map);
    map.getOverlays().add(me);

	}

@Override
  public void onResume() {
    super.onResume();
    
    me.enableCompass();
  }    
  
  @Override
  public void onPause() {
    super.onPause();
    
    me.disableCompass();
  }    
  @Override
  protected boolean isRouteDisplayed() {
    return(false);
  }
  
   @Override
  public boolean onKeyDown(int keyCode, KeyEvent event) {
    if (keyCode == KeyEvent.KEYCODE_S) {
      map.setSatellite(!map.isSatellite());
      return(true);
    }
    else if (keyCode == KeyEvent.KEYCODE_Z) {
      map.displayZoomControls(true);
      return(true);
    }
    
    return(super.onKeyDown(keyCode, event));
  }
  private GeoPoint getPoint(double lat, double longi) {
    return(new GeoPoint((int)(lat * 300.0),
                          (int)(longi * 300.0)));
  }
   private class SitesOverlay extends ItemizedOverlay<OverlayItem> {
    private List<OverlayItem> items=new ArrayList<OverlayItem>();
    private Drawable cmarker=null;
    
    public SitesOverlay(Drawable marker) {
      super(marker);
      this.marker=marker;
      
      items.add(new OverlayItem(getPoint(15.9761,
                                          120.5711),
                                "Murder"));
      items.add(new OverlayItem(getPoint(15.9761,
                                          120.5711),
                                "Robbery"));
       items.add(new OverlayItem(getPoint(15.9761,
                                          120.5711),
                                "Homicide"));
       items.add(new OverlayItem(getPoint(15.9761,
                                          120.5711),
                                "Accident"));
      populate();
    }

}
