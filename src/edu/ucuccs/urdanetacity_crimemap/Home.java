package edu.ucuccs.urdanetacity_crimemap;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class Home extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		

        // Add the Sample Fragment if there is one
        Fragment sampleFragment = getSampleFragment();
        if (sampleFragment != null) {
            getFragmentManager().beginTransaction()
                    .replace(android.R.id.content, sampleFragment).commit();
        }
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		
		  getMenuInflater().inflate(R.menu.base_menu, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	
		
		
		  switch (item.getItemId()) {
          case R.id.action_listview:
              Toast.makeText(this, "Pull to refresh in Crime Statistics", Toast.LENGTH_SHORT).show();
              Intent i=new Intent(this,CrimeStatistics.class);
              startActivity(i);
              
             
              return true;    
      }
		
		return super.onOptionsItemSelected(item);
	}

	
	//This method will override by child class. Then base class can get the fragment
	protected Fragment getSampleFragment() {
        return null;
    }
	
	
}