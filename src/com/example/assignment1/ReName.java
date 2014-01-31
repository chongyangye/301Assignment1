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

import com.google.gson.Gson;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ReName extends CreateButton {
	private static final String FILENAME = "file6.sav";
	private EditText title1;
	private TextView factorOne1;
	private	 Button buttonSave1;
	int clicke =0;
	private String titleStr2;
	private ArrayList<DataObject> tweetss;
	private Gson gson = new Gson();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.rename);
		title1 =(EditText)findViewById(R.id.title1);
		factorOne1 = (TextView)findViewById(R.id.factorOne1);
		buttonSave1 = (Button)findViewById(R.id.buttonSave1);
		//receive original name of the counter and data
		Intent intent6 = getIntent();
		clicke =intent6.getIntExtra("four", clicked);
		//original name stored in titleStr1
		titleStr2 = intent6.getStringExtra("three");
		
		factorOne1.setText("Rename");
		buttonSave1.setText("Save");
		buttonSave1.setOnClickListener(new buttonSave1Listener());
	}
	private void loadFromFile() {
		tweetss = new ArrayList<DataObject>();
		try {
			FileInputStream fis = openFileInput(FILENAME);
			 
			BufferedReader in = new BufferedReader(new InputStreamReader(fis));
			String line = in.readLine();
			
			while (line != null) {
				System.out.println(line);
				DataObject json=gson.fromJson(line, DataObject.class);
				tweetss.add(json);
		
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
	private void SaveFile(DataObject obj){
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
	
	private void cleanFile(){
		try {
            FileOutputStream fos = openFileOutput(FILENAME,
					Context.MODE_PRIVATE);
			BufferedWriter out = new BufferedWriter(new OutputStreamWriter(fos));
			out.write("");
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
	class buttonSave1Listener implements OnClickListener{

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			String titleStr1 = title1.getText().toString();
			loadFromFile();
			int checkFile=0;
			for(int i=0;i<tweetss.size();i++){
				if(tweetss.get(i).clikName().equals(titleStr1)){
					checkFile=1;
				}
			}
			if(checkFile==1){
				Toast.makeText(ReName.this,
                        "can't be the same!", Toast.LENGTH_SHORT)
                        .show();
			}
			else if (titleStr1.equals("")){
				Toast.makeText(ReName.this,
                        "can't be empty!", Toast.LENGTH_SHORT)
                        .show();
			}else{
				
				Intent intent3 = getIntent();
				intent3.putExtra("one", titleStr1);
				intent3.putExtra("two", clicke);
				cleanFile();
				for(int j=0;j<tweetss.size();j++){
					if(tweetss.get(j).clikName().equals(titleStr2)){
						DataObject obj = new DataObject(titleStr1, tweetss.get(j).date2(), tweetss.get(j).clic());
						SaveFile(obj);
					}else{
						DataObject obj = new DataObject(tweetss.get(j).clikName(), tweetss.get(j).date2(), tweetss.get(j).clic());
						SaveFile(obj);
					}
				}
				intent3.setClass(ReName.this, CreateButton.class);
				ReName.this.startActivity(intent3);
				
			}
		}
		
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		return super.onCreateOptionsMenu(menu);
	}

}
