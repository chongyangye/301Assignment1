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
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
//History of the clickers
public class PreBut extends Activity {
	private static final String FILENAME = "file6.sav";
	private int putNum=0;
	private TextView titleB;
	private ListView listB;
	private Gson gson = new Gson();
	private ArrayList<HisView> tweets;
	private ArrayAdapter<HisView> adapter;
	private ArrayList<HisView> useOnly;
	private ArrayList<HisView> useList;
	private ArrayList<HisView> useUse;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_pre_but);
		titleB=(TextView)findViewById(R.id.textViewHis);
		titleB.setText("History of Buttons,Click Counter to Continue");
		listB=(ListView)findViewById(R.id.listViewHis);
		listB.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				putNum = useList.get(arg2).clic();
				
				Intent intt = new Intent();
				intt.putExtra("one", useList.get(arg2).clikName());
				intt.putExtra("two", putNum);
				intt.setClass(PreBut.this, CreateButton.class);
				PreBut.this.startActivity(intt);
			}
		
		});
	}
	//Onstart function
	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		loadFromFile();
		adapter = new ArrayAdapter<HisView>(this,
				R.layout.list_item, useList);
		listB.setAdapter(adapter);
	}
	//load file and remove duplicate as well as sort the arrayList
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
				//useOnly.add(json);
				tweets.add(json);
				line = in.readLine();
			}
		
			while(tweets.size()>0){
				int index1=0;
				useUse = new ArrayList<HisView>();
				String Name = tweets.get(index1).clikName();
				while(index1<tweets.size()){
					if(tweets.get(index1).clikName().equals(Name)){
						useUse.add(tweets.get(index1));
						tweets.remove(tweets.get(index1));
					}else{
						index1++;
					}
				}
				int index3=0;
				int index4=0;
				int checkkk=0;
				while(index3<useUse.size()){
					if(useUse.get(index3).clic()>index4){
						index4 = useUse.get(index3).clic();
						checkkk=index3;
						index3++;
					}else{
						index3++;
				
					}
				}
				useOnly.add(useUse.get(checkkk));	
			}
			//sort
			//useList=new ArrayList<HisView>() ;
			useList=new ArrayList<HisView>() ;
			int i=0;
			while(i<useOnly.size()){
				int min=0;
				int minNum=useOnly.get(i).clic();
				int j=0;
				while(j<useOnly.size()){
					if(useOnly.get(j).clic()<=minNum){
						min=j;
						minNum=useOnly.get(j).clic();
					}
					j++;
				}
				useList.add(useOnly.get(min));
				useOnly.remove(useOnly.get(min));
				j--;
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
