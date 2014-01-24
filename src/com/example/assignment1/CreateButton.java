package com.example.assignment1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Date;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import com.google.gson.Gson;

public class CreateButton extends Activity{

	private static final String FILENAME = "file6.sav";
	int clicked =0;
	//private EditText bodyText;
	private ListView oldTweetsList;
	//private Button button1;
	private Button button3;
	private Button button4;
	private Button button5; 
	private TextView titleView1;
	private ArrayList<DataObject> tweets;
	private ArrayAdapter<DataObject> adapter;
	private Gson gson = new Gson();
	String bodyText;
	/** Called when the activity is first created. */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.create);
		Intent intent2 = getIntent();
		final String bodyText = intent2.getStringExtra("one");
		titleView1=(TextView)findViewById(R.id.titleView);
		titleView1.setText(bodyText);
		clicked =intent2.getIntExtra("two", clicked);
		Button button1 = (Button) findViewById(R.id.button1);
		oldTweetsList = (ListView) findViewById(R.id.listView1);
		final TextView text = (TextView)findViewById(R.id.textView1);
		text.setText("\n"+clicked);
		button1 =(Button)findViewById(R.id.button1);
		button1.setText("add");
		button1.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				clicked++;
				text.setText("\n"+clicked);
				setResult(RESULT_OK);
				String text = bodyText.toString();
				//String lText=(text+ new Date(System.currentTimeMillis())+clicked).toString();
				DataObject obj = new DataObject(text,new Date(System.currentTimeMillis()),clicked);
				tweets.add(obj);
				saveInFile(obj);
				adapter.notifyDataSetChanged();
			}
		});
		button3 =(Button)findViewById(R.id.button3);
		button3.setText("reset");
		button3.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				clicked=0;
				text.setText("\n"+clicked);
			}
		});
		button4 =(Button)findViewById(R.id.button4);
		button4.setText(R.string.rename);
		button4.setOnClickListener(new button4Listener());
		
		button5 =(Button)findViewById(R.id.button5);
		button5.setText("back to main menu");
		button5.setOnClickListener(new button5Listener());
	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		loadFromFile();
		adapter = new ArrayAdapter<DataObject>(this,
				R.layout.list_item, tweets);
		oldTweetsList.setAdapter(adapter);
	}

	private void loadFromFile() {
		tweets = new ArrayList<DataObject>();
		try {
			FileInputStream fis = openFileInput(FILENAME);
			 
			BufferedReader in = new BufferedReader(new InputStreamReader(fis));
			String line = in.readLine();
			
			while (line != null) {
				System.out.println(line);
				DataObject json=gson.fromJson(line, DataObject.class);
				tweets.add(json);
		
				line = in.readLine();
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
	
	private void saveInFile(DataObject obj) {
		try {
            
             
			FileOutputStream fos = openFileOutput(FILENAME,
					Context.MODE_APPEND);
			BufferedWriter out = new BufferedWriter(new OutputStreamWriter(fos));
			String json = gson.toJson(obj);
			out.write(json+ "\n");
			//fos.write(ginf.getBytes());
			//System.out.println(ginf);
			out.close();
			fos.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	class button4Listener implements OnClickListener{
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent intent5 = getIntent();
			intent5.putExtra("three", bodyText);
			intent5.putExtra("four", clicked);
			intent5.setClass(CreateButton.this, ReName.class);
			CreateButton.this.startActivity(intent5);
			
		}
	}
	class button5Listener implements OnClickListener{
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent intent7 = new Intent();
			intent7.setClass(CreateButton.this, MainActivity.class);
			CreateButton.this.startActivity(intent7);
		}
	}

	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		return super.onCreateOptionsMenu(menu);
	}
	
}
