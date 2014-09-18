package edu.ucuccs.urdanetacity_crimemap;

import java.util.ArrayList;
import java.util.List;

import uk.co.senab.actionbarpulltorefresh.library.ActionBarPullToRefresh;
import uk.co.senab.actionbarpulltorefresh.library.PullToRefreshLayout;
import uk.co.senab.actionbarpulltorefresh.library.listeners.OnRefreshListener;
import android.app.Fragment;
import android.app.ListFragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

public class CrimeStatistics extends ActionBarActivity {

	protected Fragment getSampleFragment() {
		return null;
	}

	public static class SimpleListFragment extends ListFragment implements
			OnRefreshListener {

		int i = 0;

		private PullToRefreshLayout mPullToRefreshLayout;

		ArrayAdapter<String> adapter;

		List<String> list;

		@Override
		public void onViewCreated(View view, Bundle savedInstanceState) {
			// TODO Auto-generated method stub
			super.onViewCreated(view, savedInstanceState);

			list = new ArrayList<String>();
			int no = 1;
			for (int i = 0; i < 5; i++) {
				list.add("Crime: " + no++);
			}

			super.onViewCreated(view, savedInstanceState);
			ViewGroup viewGroup = (ViewGroup) view;


			mPullToRefreshLayout = new PullToRefreshLayout(
					viewGroup.getContext());


			ActionBarPullToRefresh
					.from(getActivity())
					.insertLayoutInto(viewGroup)
					.theseChildrenArePullable(android.R.id.list,
							android.R.id.empty).listener(this)
					.setup(mPullToRefreshLayout);

		}

		@Override
		public void onActivityCreated(Bundle savedInstanceState) {
			// TODO Auto-generated method stub
			super.onActivityCreated(savedInstanceState);

			adapter = new ArrayAdapter<String>(getActivity(),
					android.R.layout.simple_list_item_1, list);

			// Set the List Adapter to display the sample items
			setListAdapter(adapter);
			setListShownNoAnimation(true);
		}

		@Override
		public void onRefreshStarted(View view) {
			// TODO Auto-generated method stub

			new AsyncTask<Void, Void, Void>() {

				@Override
				protected Void doInBackground(Void... params) {
					try {
						Thread.sleep(5000); // 5 seconds
						int itemNo = list.size();
						itemNo++;
						list.add("New Crime: " + itemNo);

					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					return null;
				}

				@Override
				protected void onPostExecute(Void result) {
					super.onPostExecute(result);

					adapter.notifyDataSetChanged();

					mPullToRefreshLayout.setRefreshComplete();

				
				}
			}.execute();

		}

	}

}