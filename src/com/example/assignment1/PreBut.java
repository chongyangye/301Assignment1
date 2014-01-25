package com.example.assignment1;



import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import com.google.gson.Gson;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class PreBut extends Activity {
	private static final String FILENAME = "file6.sav";
	private TextView titleB;
	private ListView listB;
	private Gson gson = new Gson();
	private ArrayList<HisView> tweets;
	private ArrayAdapter<HisView> adapter;
	private ArrayList<HisView> useOnly;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_pre_but);
		titleB=(TextView)findViewById(R.id.textViewHis);
		titleB.setText("History of Buttons");
		listB=(ListView)findViewById(R.id.listViewHis);
		
	}
	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		loadFromFile();
		adapter = new ArrayAdapter<HisView>(this,
				R.layout.list_item, useOnly);
		listB.setAdapter(adapter);
	}

	private void loadFromFile() {
		tweets = new ArrayList<HisView>();
		useOnly = new ArrayList<HisView>();
		try {
			FileInputStream fis = openFileInput(FILENAME);
			 
			BufferedReader in = new BufferedReader(new InputStreamReader(fis));
			String line = in.readLine();
			
			while (line != null) {
				System.out.println(line);
				HisView json=gson.fromJson(line, HisView.class);
				useOnly.add(json);
				tweets.add(json);
				line = in.readLine();
			}
			//remove duplicate
			for(int i=0;i<useOnly.size();i++){
				for(int j=0;j<tweets.size();j++){
					if(useOnly.get(i).clikName().equals(tweets.get(j).clikName())){
						if(useOnly.get(i).clic()==tweets.get(j).clic()){
							continue;
						}else{
							if(useOnly.get(i).clic()<tweets.get(j).clic()){
								useOnly.remove(useOnly.get(i));
							}else{
								//useOnly.remove(useOnly.get(j));
							}
						}
					}
				}
			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//return tweets.toArray(new String[tweets.size()]);
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.pre_but, menu);
		return true;
	}

}
