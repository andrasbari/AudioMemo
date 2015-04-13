package com.baandmazso.audiomemo;




import java.sql.SQLException;

import com.baandmazso.audiomemo.adapter.CustomListAdapter;
import com.baandmazso.audiomemo.model.DataManager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;

public class Settings extends Activity {
	private DataManager dm;
	private CustomListAdapter cla;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.settings);
		ListView listView1 = (ListView) findViewById(R.id.listView1);
		dm = DataManager.getInstance(getApplicationContext());
		
		try {
			cla = new CustomListAdapter(getApplicationContext(), dm.getUsers());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		listView1.setAdapter(cla);
		
		listView1.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1,
					int position, long arg3) {
				// TODO Auto-generated method stub
				Toast.makeText(getApplicationContext(),
						String.valueOf(cla.list.get(position).getName()),
						Toast.LENGTH_SHORT).show();
				//Listábol kiválasztott felhasználó nevének és id-nak átadása a MainActivity-nek
				Intent intent = new Intent("Játékos átadás");
				intent.putExtra("name", String.valueOf(cla.list.get(position).getName()));
				intent.putExtra("id",cla.list.get(position).getId());
				LocalBroadcastManager.getInstance(getApplicationContext()).sendBroadcast(intent);
				finish();
			}
		});
		
		/*listView1.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
					int position, long arg3) {
				try {
					dm.deleteUser(cla.list.get(position));
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				cla.list.remove(position);
				cla.notifyDataSetChanged();
				return false;
			}
		});*/

		
		TextView tvNewPlayer = (TextView) findViewById(R.id.textView2);
		
		tvNewPlayer.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getApplicationContext(),NewPlayer.class);
				startActivity(intent);
				finish();
				
			}
		});
	}

	
}
